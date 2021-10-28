package com.tlj;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameBoardTest {

    @Test
    void createdGameBoardShouldNotReturnNull() {
        assertNotNull(gameBoard);
    }
}