package me.gzimmer.owteammixer;

import javafx.application.Application;
import me.gzimmer.owteammixer.controller.Controller;
import me.gzimmer.owteammixer.gui.Gui;

/**
 * Hello world!
 *
 */
public class ZimApp {
    public static void main(String[] args) {
        Controller.init();
        Application.launch(Gui.class);
    }
}
