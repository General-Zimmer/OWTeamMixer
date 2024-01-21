package me.gzimmer.owteammixer.model;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Match {
    private final ArrayList<Player> team1 = new ArrayList<>(6);
    private final ArrayList<Player> team2 = new ArrayList<>(6);

    public Match() {
    }

    public void registerPlayerTeam1(Player player) {
        team1.add(player);
        player.setMatch(this);
    }

    public void registerPlayerTeam2(Player player) {
        team2.add(player);
        player.setMatch(this);
    }

    public boolean unregisterPlayer(Player player) {
        if (team1.remove(player)) {
            player.setMatch(null);
            return true;
        }
        else if (team2.remove(player)) {
            player.setMatch(null);
            return true;
        } else
            return false;
    }

    public void endMatch() {
        for (Player player : team1) {
            player.setMatch(null);
        }
        team1.clear();
        for (Player player : team2) {
            player.setMatch(null);
        }
        team2.clear();
    }
}
