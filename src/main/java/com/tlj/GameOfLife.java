package com.tlj;

public class GameOfLife {
    int[][] cell;
    int x_axis;
    int y_axis;

    public GameOfLife(int x_axis, int y_axis) {
        this.x_axis = x_axis;
        this.y_axis = y_axis;
        this.cell = new int[x_axis][y_axis];
    }

    public int get_x_axis() {
        return this.x_axis;
    }

    public int get_y_axis() {
        return this.y_axis;
    }

    public void set_x_axis(int x) {
        this.x_axis = x;
    }

    public void set_y_axis(int y) {
        this.y_axis = y;
    }

    public String print_board_in_console() {
        String output = "";
        for (int y = 0; y < y_axis; y++) {
            StringBuilder board = new StringBuilder("|");
            for (int x = 0; x < x_axis; x++) {
                if (this.cell[x][y] == 0) {
                    board.append(".");
                } else {
                    board.append("*");
                }
            }
            board.append("|");
            System.out.print(board);
            System.out.print("\n");
            output = board.toString();
        }
        return output;
    }

    public int get_cell_state(int x, int y) {
        if ((x < 0) || (x >= x_axis)) {
            return 0;
        }
        if ((y < 0) || (y >= y_axis)) {
            return 0;
        }
        return this.cell[x][y];
    }

    public void set_cell_state(int x, int y, int cellState) {
        if ((x < 0) || (x >= x_axis)) {
            return;
        }
        if ((y < 0) || (y >= y_axis)) {
            return;
        }
        this.cell[x][y] = cellState;
    }

    public void set_cell_state_to_dead(int x, int y) {
        set_cell_state(x, y, 0);
    }

    public void set_cell_state_to_alive(int x, int y) {
        set_cell_state(x, y, 1);
    }

    public void clear_board() {
        for (int y = 0; y < y_axis; y++) {
            for (int x = 0; x < x_axis; x++) {
                set_cell_state_to_dead(x,y);
            }
        }
    }

    public int count_living_neighbours(int x, int y) {
        int output = 0;

        output += get_cell_state(x-1, y-1);
        output += get_cell_state(x, y-1);
        output += get_cell_state(x+1, y-1);

        output += get_cell_state(x-1, y);
        output += get_cell_state(x+1, y);

        output += get_cell_state(x-1, y+1);
        output += get_cell_state(x, y+1);
        output += get_cell_state(x+1, y+1);

        return output;
    }

    public void evolve_to_next_generation() {
        int[][] newGeneration = new int[x_axis][y_axis];

        for (int y = 0; y < y_axis; y++) {
            for (int x = 0; x < x_axis; x++) {

                int livingNeighbours = count_living_neighbours(x,y);

                if (this.cell[x][y] == 1) {
                    if ((livingNeighbours < 2) || (livingNeighbours > 3)) {
                        newGeneration[x][y] = 0;
                    }else
                        newGeneration[x][y] = 1;
                } else {
                    if (livingNeighbours == 3) {
                        newGeneration[x][y] = 1;
                    }
                }
            }
        }
        this.cell = newGeneration;
    }
}
