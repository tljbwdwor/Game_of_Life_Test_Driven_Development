package com.tlj;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;

public class JavaFxSceneBuilder extends VBox {

    private Button generationButton;
    private Button clearButton;
    private Button setSizeButton;
    private Canvas canvas;
    private GameBoard gameBoard;
    private Affine affine;
    private TextField textFieldX;
    private TextField textFieldY;
    public int canvasSizeX = 600;
    public int canvasSizeY = 600;

    public JavaFxSceneBuilder() {

        this.canvas = new Canvas(canvasSizeX, canvasSizeY);
        this.canvas.setOnMousePressed(this::set_cell_to_alive);
        this.canvas.setOnMouseDragged(this::set_cell_to_alive);

        generationButton = new Button("Evolve!");
        generationButton.setStyle("-fx-background-color: #9af086; ");
        this.generationButton.setOnAction(actionEvent -> {
            gameBoard.evolve();
            show_game_board();
        });

        clearButton = new Button("Extinction!");
        clearButton.setStyle("-fx-background-color: #ff524f; ");
        this.clearButton.setOnAction(actionEvent -> {
            gameBoard.clear_board();
            show_game_board();
        });

        textFieldX = new TextField();
        textFieldX.setPromptText("New x-axis value between 1 and 100.");

        textFieldY = new TextField();
        textFieldY.setPromptText("New y-axis value between 1 and 100.");

        this.gameBoard = new GameBoard(100, 100);
        float x_value = gameBoard.x_axis;
        float y_value = gameBoard.y_axis;

        this.affine = new Affine();
        this.affine.appendScale(600 / x_value, 600 / y_value);

        setSizeButton = new Button("Set board size");
        setSizeButton.setStyle("-fx-background-color: #71BFEE");
        this.setSizeButton.setOnAction(actionEvent -> {
            gameBoard.clear_board();
            int new_x_axis = Integer.parseInt(textFieldX.getText());
            int new_y_axis = Integer.parseInt(textFieldY.getText());
            gameBoard.set_x_axis(new_x_axis);
            gameBoard.set_y_axis(new_y_axis);
            set_scale(new_x_axis, new_y_axis);
            show_game_board();
        });

        this.getChildren().addAll(this.generationButton, this.clearButton, this.setSizeButton,
                this.textFieldX, this.textFieldY, this.canvas);
    }

    public void set_canvas_size(int x, int y) {
        if (x < y) {
            this.canvas.setWidth((x * 6) * 2);
        } else
            if (x > y) {
                this.canvas.setHeight((y * 6) * 2);
            }
    }

    public void set_scale(int grid_size_x, int grid_size_y) {
        if (grid_size_x == grid_size_y) {
            this.affine.appendScale((this.canvas.getWidth() / 6) / (float) grid_size_x,
                    (this.canvas.getHeight() / 6) / (float) grid_size_y);
        } else
            if (grid_size_x > grid_size_y) {
                this.affine.appendScale((this.canvas.getWidth() / 6) / (float) grid_size_x,
                        (this.canvas.getWidth() / 6) / (float) grid_size_x);
                set_canvas_size(grid_size_x, grid_size_y);
            }
            else
                {
                    this.affine.appendScale((this.canvas.getWidth() / 6) / (float) grid_size_y,
                            (this.canvas.getWidth() / 6) / (float) grid_size_y);
                    set_canvas_size(grid_size_x, grid_size_y);
                }
    }

    public void set_cell_to_alive(MouseEvent event) {
        double mouseX = event.getX();
        double mouseY = event.getY();

        try{
            Point2D target = this.affine.inverseTransform(mouseX,mouseY);
            int x = (int) target.getX();
            int y = (int) target.getY();

            this.gameBoard.set_cell_state_to_alive(x,y);
            show_game_board();
        } catch (NonInvertibleTransformException e) {
            System.out.println("Mouse transform failure");
            e.printStackTrace();
        }
    }

    public void show_game_board() {
        GraphicsContext graphics = this.canvas.getGraphicsContext2D();

        graphics.setTransform(this.affine);
        graphics.setFill(Color.BLACK);
        graphics.fillRect(0, 0, 600, 600);

        graphics.setFill(Color.GREENYELLOW);
        for (int x = 0; x < gameBoard.x_axis; x++) {
            for (int y = 0; y < gameBoard.y_axis; y++) {
                if (this.gameBoard.get_cell_state(x,y) == 1) {
                    graphics.fillRect(x, y, 1, 1);
                }
            }
        }

        graphics.setStroke(Color.WHITE);
        graphics.setLineWidth(0.05f);
        for (int x = 0; x < this.gameBoard.x_axis; x++) {
            graphics.strokeLine(x, 0, x, gameBoard.y_axis);
        }

        for (int y = 0; y < this.gameBoard.y_axis; y++) {
            graphics.strokeLine(0, y, gameBoard.x_axis, y);
        }
    }
}
