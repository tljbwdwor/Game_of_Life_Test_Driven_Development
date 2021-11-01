package com.tlj;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFxLaunchpad extends Application {

    @Override
    public void start(Stage stage) {
        JavaFxScene javaFxScene = new JavaFxScene();
        Scene scene = new Scene(javaFxScene, 600, 725);
        stage.setScene(scene);
        stage.show();
        javaFxScene.show_game_board();
    }

    public static void main(String[] args) {
        launch();
    }
}
