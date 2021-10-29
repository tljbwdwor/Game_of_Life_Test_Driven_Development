package com.tlj;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFxLaunchpad extends Application {

    @Override
    public void start(Stage stage) {
        JavaFxSceneBuilder javaFxSceneBuilder = new JavaFxSceneBuilder();
        Scene scene = new Scene(javaFxSceneBuilder, 600, 650);
        stage.setScene(scene);
        stage.show();
        javaFxSceneBuilder.showGameBoard();
    }

    public static void main(String[] args) {
        launch();
    }
}
