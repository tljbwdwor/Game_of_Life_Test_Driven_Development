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

public class JavaFxScene extends VBox {

    private final TextField textFieldX;
    private final TextField textFieldY;

    private final Canvas canvas;
    public int defaultCanvasSizeX = 600;
    public int defaultCanvasSizeY = 600;
    private final Affine affine;

    private final GameOfLife gameOfLife;

    public JavaFxScene() {
        this.canvas = new Canvas(defaultCanvasSizeX, defaultCanvasSizeY);
        this.canvas.setOnMousePressed(this::set_cell_to_alive);
        this.canvas.setOnMouseDragged(this::set_cell_to_alive);

        this.gameOfLife = new GameOfLife(100, 100);
        float x_value = gameOfLife.x_axis;
        float y_value = gameOfLife.y_axis;

        this.affine = new Affine();
        this.affine.appendScale(defaultCanvasSizeX / x_value, defaultCanvasSizeY / y_value);

        textFieldX = new TextField();
        textFieldX.setPromptText("New x-axis value between 1 and 100.");

        textFieldY = new TextField();
        textFieldY.setPromptText("New y-axis value between 1 and 100.");

        Button generationButton = new Button("Evolve!");
        generationButton.setStyle("-fx-background-color: #9af086; ");
        generationButton.setOnAction(actionEvent -> {
            gameOfLife.evolve_to_next_generation();
            show_game_board();
        });

        Button clearButton = new Button("Extinction!");
        clearButton.setStyle("-fx-background-color: #ff524f; ");
        clearButton.setOnAction(actionEvent -> {
            gameOfLife.clear_board();
            show_game_board();
        });

        Button setSizeButton = new Button("Set board size");
        setSizeButton.setStyle("-fx-background-color: #71BFEE");
        setSizeButton.setOnAction(actionEvent -> {
            gameOfLife.clear_board();
            set_new_board_x_and_y_axes();
            set_scale(get_new_x_axis_from_text_field(), get_new_y_axis_from_text_field());
            show_game_board();
        });

        this.getChildren().addAll(generationButton, clearButton, setSizeButton, this.textFieldX,
                this.textFieldY, this.canvas);
    }

    public int get_new_x_axis_from_text_field() {
        return Integer.parseInt(textFieldX.getText());
    }

    public int get_new_y_axis_from_text_field() {
        return Integer.parseInt(textFieldY.getText());
    }

    public void set_new_board_x_and_y_axes() {
        int x = get_new_x_axis_from_text_field();
        int y = get_new_y_axis_from_text_field();
        gameOfLife.set_x_axis(x);
        gameOfLife.set_y_axis(y);
    }

    public void show_game_board() {
        fill_live_squares_with_colour();
        draw_grid_lines();
    }

    public void fill_live_squares_with_colour() {
        GraphicsContext graphics = this.canvas.getGraphicsContext2D();
        graphics.setTransform(this.affine);
        graphics.setFill(Color.BLACK);
        graphics.fillRect(0, 0, defaultCanvasSizeX, defaultCanvasSizeY);
        graphics.setFill(Color.GREENYELLOW);

        for (int x = 0; x < gameOfLife.x_axis; x++) {
            for (int y = 0; y < gameOfLife.y_axis; y++) {
                if (this.gameOfLife.get_cell_state(x, y) == 1) {
                    graphics.fillRect(x, y, 1, 1);
                }
            }
        }
    }

    public void draw_grid_lines() {
        GraphicsContext graphics = this.canvas.getGraphicsContext2D();
        graphics.setStroke(Color.WHITE);
        graphics.setLineWidth(0.05f);

        for (int x = 0; x < this.gameOfLife.x_axis; x++) {
            graphics.strokeLine(x, 0, x, gameOfLife.y_axis);
        }

        for (int y = 0; y < this.gameOfLife.y_axis; y++) {
            graphics.strokeLine(0, y, gameOfLife.x_axis, y);
        }
    }

    public void set_cell_to_alive(MouseEvent event) {
        double mouseX = event.getX();
        double mouseY = event.getY();

        try{
            Point2D target = this.affine.inverseTransform(mouseX,mouseY);
            int x = (int) target.getX();
            int y = (int) target.getY();

            this.gameOfLife.set_cell_state_to_alive(x,y);
            show_game_board();
        } catch (NonInvertibleTransformException e) {
            System.out.println("Mouse transform failure");
            e.printStackTrace();
        }
    }

    public void set_canvas_size(int x, int y) {
        double percentageDifference;
        if (x == y) {
            this.canvas.setWidth(defaultCanvasSizeX);
            this.canvas.setHeight(defaultCanvasSizeY);
        } else
            if (x < y) {
                this.canvas.setHeight(defaultCanvasSizeY);
                percentageDifference = ((double) x / (double) y);
                this.canvas.setWidth((int)(percentageDifference * defaultCanvasSizeX));
        } else
            {
                this.canvas.setWidth(defaultCanvasSizeX);
                percentageDifference = ((double)y / (double) x);
                this.canvas.setHeight((int)(percentageDifference * defaultCanvasSizeY));
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
}
