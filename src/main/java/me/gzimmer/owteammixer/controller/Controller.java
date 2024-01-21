package me.gzimmer.owteammixer.controller;

import me.gzimmer.owteammixer.gui.Gui;
import me.gzimmer.owteammixer.model.Match;
import me.gzimmer.owteammixer.model.Player;
import me.gzimmer.owteammixer.storage.IStorage;
import me.gzimmer.owteammixer.storage.Storage;

import java.util.*;

public abstract class Controller {
    private static IStorage storage;



    public static void init() {
        String path = System.getProperty("user.home") + "\\AppData\\Local";
        storage = new Storage();

    }


    public static Match createMatch() {
        Match match = new Match();

        Set<Player> noTeamPlayers = getNoTeamPlayers();

        int playersLeft = noTeamPlayers.size();

        int teamSize = 5;
        // If there's less than 10, do something
        if (playersLeft < 10 && playersLeft >= 2) {
            teamSize = playersLeft / 2;

            pickRandomTeams(noTeamPlayers, teamSize, match, Team.TEAM2);
            Iterator<Player> iterator = noTeamPlayers.iterator();
            while (iterator.hasNext()) {
                Player player = iterator.next();
                match.registerPlayerTeam1(player);
                iterator.remove();
            }
        } else if (playersLeft >= 10) {
            pickRandomTeams(noTeamPlayers, teamSize, match, Team.TEAM1);
            pickRandomTeams(noTeamPlayers, teamSize, match, Team.TEAM2);
        } else {
            Gui.getInstance().notifyObservers();
            return null;
        }

        storage.addMatch(match);
        Gui.getInstance().notifyObservers();
        return match;
    }


    private static void pickRandomTeams(Set<Player> noTeamPlayers, int teamSize, Match match, Team team) {
        List<Player> teamList = pickRandomPlayers(noTeamPlayers, teamSize);
        if (team == Team.TEAM1) {
            for (Player player : teamList) {
                match.registerPlayerTeam1(player);
                noTeamPlayers.remove(player);
            }
        } else {
            for (Player player : teamList) {
                match.registerPlayerTeam2(player);
                noTeamPlayers.remove(player);
            }
        }
    }

    private static List<Player> pickRandomPlayers(Set<Player> players, int teamSize) {
        ArrayList<Player> playersList = new ArrayList<>();
        Iterator<Player> iterator = players.iterator();
        while (teamSize > 0) {
            Player player = iterator.next();
            if (Math.random() < 0.01) {
                playersList.add(player);
                iterator.remove();
                teamSize--;
            }
            if (!iterator.hasNext()) {
                iterator = players.iterator();
            }
        }
        return playersList;
    }

    public static HashSet<Player> getNoTeamPlayers() {
        HashSet<Player> noTeamPlayers = new HashSet<>();
        for (Player player : storage.getPlayers()) {
            if (player.getMatch() == null) {
                noTeamPlayers.add(player);
            }
        }
        return noTeamPlayers;
    }


    public static void addPlayers(String[] bnets) {
        storage.addPlayers(bnets);
        Gui.getInstance().notifyObservers();
    }

    public static Set<Player> getAllPlayers() {
        return storage.getPlayers();
    }

    public static void endMatch(Match match) {
        match.endMatch();
        storage.removeMatch(match);
        Gui.getInstance().notifyObservers();
    }

    public static Set<Match> getMatches() {
        return storage.getMatches();
    }
}

enum Team {
    TEAM1, TEAM2
}
