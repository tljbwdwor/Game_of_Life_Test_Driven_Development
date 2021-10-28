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
}