package com.tlj;

public abstract class ConsoleLaunchpad extends Console {
    public static void main(String[] args) {
        obtain_board_parameters_from_user();
        set_cell_status_from_user_input();
        run_game();
    }
}
