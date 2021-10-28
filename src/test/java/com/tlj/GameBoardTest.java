package com.tlj;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameBoardTest {

    GameBoard gameBoard = new GameBoard(5,8);

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
        GameBoard gameBoard = new GameBoard(3,1);
        assertEquals("|...|", gameBoard.print_board_in_console());
    }

    @Test
    public void cellStateShouldbeSetToDeadOr0() {
        assertEquals(0,gameBoard.get_cell_state(2,2));
    }

    @Test
    public void cellStateShouldBeChangedBySetMethod() {
        assertEquals(0,gameBoard.get_cell_state(2,2));
        gameBoard.set_cell_state(2,2,1);
        assertEquals(1,gameBoard.get_cell_state(2,2));
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
        GameBoard gameBoard = new GameBoard(3,1);
        gameBoard.set_cell_state(2,1,1);
        assertEquals("|.*.|",gameBoard.print_board_in_console());
    }
}