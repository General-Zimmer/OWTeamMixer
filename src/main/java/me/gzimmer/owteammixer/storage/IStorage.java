package me.gzimmer.owteammixer.storage;


import me.gzimmer.owteammixer.model.Player;

import java.io.Serializable;
import java.util.HashSet;

public interface IStorage extends Serializable {

    HashSet<Player> getPlayers();
    void addPlayer(Player player);

    void addPlayers(String players);

    void removePlayer(Player player);

}
