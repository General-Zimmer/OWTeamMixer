package me.gzimmer.owteammixer.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import me.gzimmer.owteammixer.observers.IStorageObserver;
import me.gzimmer.owteammixer.thread.AutoSave;

import java.net.URL;
import java.util.HashSet;
import java.util.NoSuchElementException;

public class Gui extends Application {

    @Getter
    private static Gui instance;
    private final HashSet<IStorageObserver> observers = new HashSet<>();
    private Thread autoSave;


    public Gui() {
        if (instance != null) {
            throw new IllegalStateException("Cannot create more than one instance Gui");
        }
        instance = this;
    }

    @Getter
    private Stage stageHovedMenu;

    @Override
    public void start(Stage stage) throws Exception {
        final int STAGE = 0;
        final int CONTROLLER = 1;

        Object[] stageHovedMenu = createStage("mainWindow.fxml", stage);
        this.stageHovedMenu = (Stage) stageHovedMenu[STAGE];
        this.stageHovedMenu.show();

        autoSave = new AutoSave(10);
        autoSave.start();
    }

    public void notifyObservers() {
        for (IStorageObserver observer : observers) {
            observer.update();
        }
    }

    private Object[] createStage(String resource) throws Exception {
        Stage stage = new Stage();
        return createStage(resource, stage);
    }

    private <T extends IStorageObserver> Object[] createStage(String resource, Stage stage) throws Exception {
        Object[] returnArray = new Object[2];

        returnArray[0] = stage;
        URL fxmlFileNameVisFadIndhold = this.getClass().getResource(resource);
        if (fxmlFileNameVisFadIndhold == null) throw new NoSuchElementException("FXML file not found");
        FXMLLoader sceneLoader = new FXMLLoader(fxmlFileNameVisFadIndhold);
        Parent parent = sceneLoader.load();
        T controller = sceneLoader.getController();
        returnArray[1] = controller;
        registerObserver(controller);
        Scene sceneFadIndhold = new Scene(parent);
        stage.setScene(sceneFadIndhold);
        return returnArray;
    }
    /**
     * Tilføjer en observer
     * @param observer observer
     */
    public void registerObserver(IStorageObserver observer) {
        observers.add(observer);
    }

    /**
     * Fjerner en observer fra listen med observers.
     * @param observer observer
     */
    public void unregisterObserver(IStorageObserver observer) {
        observers.remove(observer);
    }

    /**
     * Sørger for at stoppe vores Auto Save funktion når gui'en bliver lukket.
     * @throws Exception Exception
     */
    @Override
    public void stop() throws Exception {
        super.stop();
        autoSave.interrupt();
        //todo REMEMBER TO ADD SAVE MECHANIC!!!
    }

}
