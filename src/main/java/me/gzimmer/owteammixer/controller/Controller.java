package me.gzimmer.owteammixer.controller;

import me.gzimmer.owteammixer.gui.Gui;
import me.gzimmer.owteammixer.model.Player;
import me.gzimmer.owteammixer.storage.IStorage;
import me.gzimmer.owteammixer.storage.Storage;

import java.util.Set;

public abstract class Controller {
    private static IStorage storage;



    public static void init() {
        String path = System.getProperty("user.home") + "\\AppData\\Local";
        storage = new Storage();

    }

    public static void addPlayers(String players) {
        storage.addPlayers(players);
        Gui.getInstance().notifyObservers();
    }

    public static Set<Player> getPlayers() {
        return storage.getPlayers();
    }
}
