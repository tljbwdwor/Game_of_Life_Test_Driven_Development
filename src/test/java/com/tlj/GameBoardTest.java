package com.tlj;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameBoardTest {

    GameBoard gameBoard = new GameBoard(5,8);
    GameBoard gameBoard2 = new GameBoard(3,1);

    @Test
    public void createdGameBoardShouldNotReturnNull() {
        assertNotNull(gameBoard);
    }

    @Test
    public void createdGameBoardShouldReturnCorrectXValue() {
        assertEquals(5,gameBoard.x_axis);
    }

    @Test
    public void createdGameBoardShouldReturnCorrectYValue() {
        assertEquals(8,gameBoard.y_axis);
    }

    @Test
    public void gameBoardShouldPrintInConsoleAsExpected() {
        assertEquals("|...|", gameBoard2.print_board_in_console());
    }

    @Test
    public void cellStateShouldbeSetToDeadOr0() {
        assertEquals(0,gameBoard.get_cell_state(2,2));
    }

    @Test
    public void cellStateShouldBeChangedBySetMethod() {
        assertEquals(0,gameBoard.get_cell_state(2,2));
        gameBoard.set_cell_state(1,1,1);
        assertEquals(1,gameBoard.get_cell_state(1,1));
    }

    @Test
    public void outOfBoundsCellShouldDefaultToDeadOr0() {
        assertEquals(0,gameBoard.get_cell_state(10,10));
    }

    @Test
    public void outOfBoundsCellShouldNotBeAbleToBeSetToAliveOr1() {
        gameBoard.set_cell_state(10,10,1);
        assertEquals(0,gameBoard.get_cell_state(10,10));
    }

    @Test
    public void printBoardInConsoleMethodShouldHandleLiveAndDeadCellsGraphically() {
        gameBoard2.set_cell_state(1,0,1);
        assertEquals("|.*.|",gameBoard2.print_board_in_console());
    }

    @Test
    public void methodShouldExistForSettingStatusToDeadOr0() {
        gameBoard.set_cell_state(2,2,1);
        assertEquals(1,gameBoard.get_cell_state(2,2));
        gameBoard.set_cell_state_to_dead(2,2);
        assertEquals(0,gameBoard.get_cell_state(2,2));
    }

    @Test
    public void methodShouldExistForSettingStatusToAliveOr1() {
        gameBoard.set_cell_state(2,2,0);
        assertEquals(0,gameBoard.get_cell_state(2,2));
        gameBoard.set_cell_state_to_alive(2,2);
        assertEquals(1,gameBoard.get_cell_state(2,2));
    }
}