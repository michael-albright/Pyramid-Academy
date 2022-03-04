package com.example.humansversegoblinsgui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameApplication extends Application {

    GameSetUp game;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameSetUp.class.getResource("gamePlay.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Humans Verse Goblins");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) { launch(); }

}
