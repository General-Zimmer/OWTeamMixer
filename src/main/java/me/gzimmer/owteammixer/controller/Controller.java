package me.gzimmer.owteammixer.controller;

import me.gzimmer.owteammixer.storage.IStorage;
import me.gzimmer.owteammixer.storage.Storage;

public abstract class Controller {
    private static IStorage storage;



    public static void init() {
        String path = System.getProperty("user.home") + "\\AppData\\Local";
        storage = new Storage();

    }

    public static void addPlayers(String players) {
        storage.addPlayers(players);
    }
}
