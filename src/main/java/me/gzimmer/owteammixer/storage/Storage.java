package me.gzimmer.owteammixer.storage;


import me.gzimmer.owteammixer.model.Player;
import me.gzimmer.owteammixer.util.UtilCommon;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Storage implements IStorage, Serializable {

    private final String apiPath = "https://overfast-api.tekrop.fr/players/{player_id}/summary";

    private final HashSet<Player> players = new HashSet<>();

    @Override
    public Set<Player> getPlayers() {
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
            Player player = UtilCommon.getOnlinePlayer(getPlayerSummaryPath(bnet), bnet);
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
