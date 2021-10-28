package com.tlj;

public class GameBoard {
    int[][] size;
    int x_axis;
    int y_axis;

    public GameBoard(int x_axis, int y_axis) {
        this.x_axis = x_axis;
        this.y_axis = y_axis;
        this.size = new int[x_axis][y_axis];
    }
}
