package com.tlj;

import java.util.Scanner;

public class Console {

    private static final Scanner scanner = new Scanner(System.in);
    private static final GameBoard gameboard = new GameBoard(100, 100);


    public static int get_x_axis_from_user() {
        int x;
        do {
            System.out.println("Enter number between 1 and 100 for x-axis value.");
            x = scanner.nextInt();
        } while ((x < 1) || (x > 100));
        System.out.println("value for x: " + x);
        gameboard.set_x_axis(x);
        return x;
    }

    public static int get_y_axis_from_user() {
        int y;
        do {
            System.out.println("Enter number between 1 and 100 for y-axis value.");
            y = scanner.nextInt();
        } while ((y < 1) || (y > 100));
        System.out.println("value for y: " + y);
        gameboard.set_y_axis(y);
        return y;
    }

    public static void obtain_and_confirm_board_parameters_to_user() {
        System.out.println("You have initialised a board size of " + get_x_axis_from_user() + "" +
                " x " + get_y_axis_from_user() + ".");
    }

    public static int select_number_of_live_cells() {
        int live_cells;
        System.out.println("How many live cells would you like to start with?");
        return live_cells = scanner.nextInt();
    }

    public static void set_cell_status_to_alive_in_console() {
        int x;
        int y;
        do {
            System.out.println("Enter x coordinate for live cell");
            x = scanner.nextInt();
        } while ((x < 1) || (x > gameboard.get_x_axis()));
        do {
            System.out.println("Enter y coordinate for live cell");
            y = scanner.nextInt();
        } while ((y < 1) || (y > gameboard.get_y_axis()));
        gameboard.set_cell_state_to_alive((x - 1), (y - 1));
    }

    public static void user_sets_cell_status() {
        int number = select_number_of_live_cells();

        for (int i = 0; i < number; i++) {
            set_cell_status_to_alive_in_console();
        }
    }

    public static void run_game() {
        gameboard.x_axis = gameboard.get_x_axis();
        gameboard.y_axis = gameboard.get_y_axis();
        int generations;

        System.out.println("Enter number of generations");
        generations = scanner.nextInt();

        for (int i = 0; i < generations; i++) {
            System.out.println("Generation " + (i + 1));
            gameboard.print_board_in_console();
            gameboard.evolve();
            System.out.println("----------\n");
        }
    }

    public static void main(String[] args) {
        obtain_and_confirm_board_parameters_to_user();
        user_sets_cell_status();
        run_game();
    }
}
