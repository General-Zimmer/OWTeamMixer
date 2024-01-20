package me.gzimmer.owteammixer.gui.gcontroller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import me.gzimmer.owteammixer.controller.Controller;
import me.gzimmer.owteammixer.observers.IStorageObserver;
import me.gzimmer.owteammixer.model.Player;

public class MainWindowGController implements IStorageObserver {

    @FXML
    private Button btnQuickBnet;

    @FXML
    private ListView<Player> lwPlayers;

    @FXML
    private TextField quickBnet;

    @Override
    public void update() {
        lwPlayers.getItems().clear();
        lwPlayers.getItems().addAll(Controller.getPlayers());
    }

    @FXML
    public void onQuickBnet() {
        String bnets = quickBnet.getText().replace("#", "-");
        Controller.addPlayers(bnets);
    }
}
