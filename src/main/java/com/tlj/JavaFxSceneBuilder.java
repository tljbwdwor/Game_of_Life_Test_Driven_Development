package com.tlj;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

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

    public void showGameBoard() {
        GraphicsContext graphics = this.canvas.getGraphicsContext2D();

        graphics.setFill(Color.DARKGRAY);
        graphics.fillRect(0, 0, 600, 600);

        graphics.setFill(Color.GREENYELLOW);
        for (int x = 0; x < gameBoard.x_axis; x++) {
            for (int y = 0; y < gameBoard.y_axis; y++) {
                if (this.gameBoard.get_cell_state(x,y) == 1) {
                    graphics.fillRect(x, y, 1, 1);
                }
            }
        }
    }
}
