package com.tlj;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class JavaFxSceneBuilder extends VBox {

    private Button generationButton;
    private Button clearButton;
    private Canvas canvas;
    private GameBoard gameBoard;

    public JavaFxSceneBuilder() {

        generationButton = new Button("Evolve!");
        generationButton.setStyle("-fx-background-color: #9af086; ");
        this.generationButton.setOnAction(actionEvent -> {
            gameBoard.evolve();
        });

        clearButton = new Button("Extinction!");
        clearButton.setStyle("-fx-background-color: #ff524f; ");
        this.clearButton.setOnAction(actionEvent -> {
            gameBoard.clear_board();
        });

        this.canvas = new Canvas(600, 600);

        this.getChildren().addAll(this.generationButton, this.clearButton, this.canvas);

        this.gameBoard = new GameBoard(40,40);
    }
}
