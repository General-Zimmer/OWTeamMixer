package me.gzimmer.owteammixer.storage;


import me.gzimmer.owteammixer.model.Player;

import java.io.Serializable;
import java.util.Set;

public interface IStorage extends Serializable {

    Set<Player> getPlayers();
    void addPlayer(Player player);

    void addPlayers(String players);

    void removePlayer(Player player);

}
