package me.gzimmer.owteammixer.storage;


import lombok.Getter;
import me.gzimmer.owteammixer.model.Match;
import me.gzimmer.owteammixer.model.Player;
import me.gzimmer.owteammixer.util.UtilCommon;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Storage implements IStorage, Serializable {

    @Getter
    private final String apiPath = "https://overfast-api.tekrop.fr/players/{player_id}/summary";
    private final HashSet<Player> players = new HashSet<>();
    private final HashSet<Match> matches = new HashSet<>();

    @Override
    public Set<Player> getPlayers() {
        return new HashSet<>(players);
    }

    @Override
    public void addPlayer(Player player) {
        players.add(player);
    }

    @Override
    public void addPlayers(String[] bnets) {

        for (String bnet : bnets) {
            Player player = UtilCommon.getOnlinePlayer(getPlayerSummaryPath(bnet), bnet);
            addPlayer(player);
        }
    }

    @Override
    public void removePlayer(Player player) {
        players.remove(player);
    }

    @Override
    public String getPlayerSummaryPath(String bnet) {
        return apiPath.replace("{player_id}", bnet);
    }

    @Override
    public void addMatch(Match match) {
        matches.add(match);
    }

    @Override
    public void removeMatch(Match match) {
        matches.remove(match);
    }

    @Override
    public Set<Match> getMatches() {
        return new HashSet<>(matches);
    }
}
