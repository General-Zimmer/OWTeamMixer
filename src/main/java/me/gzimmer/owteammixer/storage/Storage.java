package me.gzimmer.owteammixer.storage;


import me.gzimmer.owteammixer.model.Player;
import me.gzimmer.owteammixer.util.UtilCommon;

import java.io.Serializable;
import java.util.HashSet;

public class Storage implements IStorage, Serializable {

    private final String apiPath = "https://overfast-api.tekrop.fr/players/{player_id}/stats/summary";

    private final HashSet<Player> players = new HashSet<>();

    @Override
    public HashSet<Player> getPlayers() {
        return new HashSet<>(players);
    }

    @Override
    public void addPlayer(Player player) {
        players.add(player);
    }

    @Override
    public void addPlayers(String bnets) {
        String[] bnetArray = bnets.trim().split(",");
        for (String bnet : bnetArray) {
            Player player = UtilCommon.getOnlinePlayer(bnet, getPlayerSummaryPath(bnet));
            addPlayer(player);
        }
    }

    @Override
    public void removePlayer(Player player) {

    }

    public String getPlayerSummaryPath(String bnet) {
        return apiPath.replace("{player_id}", bnet);
    }
}
