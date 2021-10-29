package com.tlj;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ConsoleTest {

    GameBoard gameBoard = new GameBoard(100,100);

    @Test
    void shouldRetrieveXAxisValueFromUser() {
        assertEquals(15,get_x_axis_from_user());
    }
}