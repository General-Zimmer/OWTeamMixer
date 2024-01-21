package me.gzimmer.owteammixer.storage;


import me.gzimmer.owteammixer.model.Match;
import me.gzimmer.owteammixer.model.Player;

import java.io.Serializable;
import java.util.Set;

public interface IStorage extends Serializable {

    Set<Player> getPlayers();
    void addPlayer(Player player);

    void addPlayers(String[] bnets);

    void removePlayer(Player player);

    String getPlayerSummaryPath(String bnet);

    void addMatch(Match match);

    void removeMatch(Match match);

    Set<Match> getMatches();

}
