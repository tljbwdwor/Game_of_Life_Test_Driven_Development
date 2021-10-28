package com.tlj;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.awt.*;

public class JavaFxSceneBuilder extends VBox {

    private Button evolveButton;
    private Button clearButton;

    private Canvas canvas;

    private GameBoard gameBoard;

    public JavaFxSceneBuilder() {

        evolveButton = new Button("Evolve");
        evolveButton.setStyle("-fx-background-color: #9af086");
        this.evolveButton.setOnAction(actionEvent -> {
            gameBoard.evolve();
        });

        clearButton = new Button("Clear Board");
        clearButton.setStyle("-fx-background-color: #ff524f");
        this.clearButton.setOnAction(actionEvent -> {
            gameBoard.clear_board();
        });
    }
}
