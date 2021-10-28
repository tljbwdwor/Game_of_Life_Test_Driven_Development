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

    public String print_board_in_console() {
        String output = "";
        for (int y = 0; y < y_axis; y++) {
            StringBuilder board = new StringBuilder("|");
            for (int x = 0; x < x_axis; x++) {
                board.append(".");
            }
            board.append("|");
            output = board.toString();
        }
        return output;
    }

    public int get_cell_state(int x_axis, int y_axis) {
        return 0;
    }
}
