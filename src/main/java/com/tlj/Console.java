package com.tlj;

import java.util.Scanner;

public class Console {

    private static final Scanner scanner = new Scanner(System.in);
    private static final GameBoard gameboard = new GameBoard(100,100);


    public int get_x_axis_from_user() {
        int x;
        do {
            System.out.println("Enter number between 1 and 100 for x-axis value.");
            x = scanner.nextInt();
        } while ((x < 1) || (x > 100));
        System.out.println("value for x: " + x);
        gameboard.set_x_axis(x);
        return x;
    }
}
