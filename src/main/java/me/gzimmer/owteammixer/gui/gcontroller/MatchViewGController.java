package me.gzimmer.owteammixer.gui.gcontroller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import me.gzimmer.owteammixer.controller.Controller;
import me.gzimmer.owteammixer.gui.Gui;
import me.gzimmer.owteammixer.model.Match;
import me.gzimmer.owteammixer.model.Player;
import me.gzimmer.owteammixer.observers.IStorageObserver;

public class MatchViewGController implements IStorageObserver {

    @FXML
    private ListView<Player> lwTeam1;

    @FXML
    private ListView<Player> lwTeam2;

    private Match match;



    @FXML
    public void endMatch() {
        Controller.endMatch(match);
        onClose();
    }

    @FXML
    public void onClose() {
        Gui gui = Gui.getInstance();
        gui.switchToHovedMenu();
    }

    public void update(Match match) {
        this.match = match;
        lwTeam1.getItems().clear();
        lwTeam1.getItems().addAll(match.getTeam1());
        lwTeam2.getItems().clear();
        lwTeam2.getItems().addAll(match.getTeam2());
    }

    @Override
    public void update() {

    }
}
