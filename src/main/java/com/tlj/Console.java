package com.tlj;

import java.util.Scanner;

public class Console {

    private static final Scanner scanner = new Scanner(System.in);
    private static final GameOfLife gameOfLife = new GameOfLife(100, 100);


    public static int set_x_axis_from_user_input() {
        int x;
        do {
            System.out.println("Enter number between 1 and 100 for x-axis value.");
            x = scanner.nextInt();
        } while ((x < 1) || (x > 100));
        System.out.println("value for x: " + x);
        gameOfLife.set_x_axis(x);
        return x;
    }

    public static int set_y_axis_from_user_input() {
        int y;
        do {
            System.out.println("Enter number between 1 and 100 for y-axis value.");
            y = scanner.nextInt();
        } while ((y < 1) || (y > 100));
        System.out.println("value for y: " + y);
        gameOfLife.set_y_axis(y);
        return y;
    }

    public static void obtain_board_parameters_from_user() {
        System.out.println("You have initialised a board size of " + set_x_axis_from_user_input() + "" +
                " x " + set_y_axis_from_user_input() + ".");
    }

    public static int obtain_number_of_live_cells_from_user_input() {
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
        } while ((x < 1) || (x > gameOfLife.get_x_axis()));
        do {
            System.out.println("Enter y coordinate for live cell");
            y = scanner.nextInt();
        } while ((y < 1) || (y > gameOfLife.get_y_axis()));
        gameOfLife.set_cell_state_to_alive((x - 1), (y - 1));
    }

    public static void set_cell_status_from_user_input() {
        int number = obtain_number_of_live_cells_from_user_input();

        for (int i = 0; i < number; i++) {
            set_cell_status_to_alive_in_console();
        }
    }

    public static int obtain_number_of_generations_from_user() {
        int generations;
        System.out.println("Enter number of generations");
        generations = scanner.nextInt();
        return generations;
    }

    public static void run_game() {
        gameOfLife.x_axis = gameOfLife.get_x_axis();
        gameOfLife.y_axis = gameOfLife.get_y_axis();
        int generations = obtain_number_of_generations_from_user();

        for (int i = 0; i < generations; i++) {
            System.out.println("Generation " + (i + 1));
            gameOfLife.print_board_in_console();
            gameOfLife.evolve_to_next_generation();
            System.out.println("----------\n");
        }
    }
}
