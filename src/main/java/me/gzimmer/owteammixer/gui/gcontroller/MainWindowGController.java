package me.gzimmer.owteammixer.gui.gcontroller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import me.gzimmer.owteammixer.controller.Controller;
import me.gzimmer.owteammixer.gui.Gui;
import me.gzimmer.owteammixer.model.Match;
import me.gzimmer.owteammixer.model.Player;
import me.gzimmer.owteammixer.observers.IStorageObserver;


public class MainWindowGController implements IStorageObserver {

    @FXML
    private ListView<Match> lwMatches;

    @FXML
    private ListView<Player> lwPlayers;

    @FXML
    private TextArea quickBnet;

    @Override
    public void update() {
        lwPlayers.getItems().clear();
        lwPlayers.getItems().addAll(Controller.getNoTeamPlayers());
        lwMatches.getItems().clear();
        lwMatches.getItems().addAll(Controller.getMatches());
    }

    @FXML
    public void onQuickBnet() {
        String[] bnets = quickBnet.getText().replace("#", "-").replace(" ", "").replace("\n", "").split(",");
        Controller.addPlayers(bnets);
        quickBnet.clear();
    }

    @FXML
    public void clickMatch(MouseEvent mouseEvent) {
        Match match = lwMatches.getSelectionModel().getSelectedItem();
        if (mouseEvent.getClickCount() == 2 && lwMatches.getSelectionModel().getSelectedItem() != null) {
            Gui gui = Gui.getInstance();
            gui.getMatchViewGController().update(match);
            gui.switchToMatchView();
        }
    }

    @FXML
    public void createMatch() {
        Controller.createMatch();
    }



}
