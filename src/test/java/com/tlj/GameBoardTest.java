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

    @Test
    public void clearBoardShouldResultInAllCellsRevertingToDead() {
        gameBoard.set_cell_state_to_alive(2,2);
        gameBoard.set_cell_state_to_alive(2,3);
        gameBoard.set_cell_state_to_alive(2,4);
        assertEquals(1,gameBoard.get_cell_state(2,2));
        assertEquals(1,gameBoard.get_cell_state(2,3));
        assertEquals(1,gameBoard.get_cell_state(2,4));
        gameBoard.clear_board();
        assertEquals(0,gameBoard.get_cell_state(2,2));
        assertEquals(0,gameBoard.get_cell_state(2,3));
        assertEquals(0,gameBoard.get_cell_state(2,4));
    }

    @Test
    public void livingNeighbourCountShouldReturnCorrectNumber() {
        gameBoard.set_cell_state_to_alive(2,2);
        gameBoard.set_cell_state_to_alive(2,3);
        gameBoard.set_cell_state_to_alive(2,4);
        assertEquals(3,gameBoard.count_living_neighbours(3,3));
    }

    @Test
    public void livingCellWithFewerThan2LivingNeighboursShouldDieInNextGeneration() {
        gameBoard.set_cell_state_to_dead(1,1);
        gameBoard.set_cell_state_to_dead(1,2);
        gameBoard.set_cell_state_to_dead(1,3);
        gameBoard.set_cell_state_to_dead(2,1);
        gameBoard.set_cell_state_to_alive(2,2);
        gameBoard.set_cell_state_to_alive(2,3);
        gameBoard.set_cell_state_to_dead(3,1);
        gameBoard.set_cell_state_to_dead(3,2);
        gameBoard.set_cell_state_to_dead(3,3);
        gameBoard.evolve();
        assertEquals(0,gameBoard.get_cell_state(2,2));
    }

    @Test
    public void livingCellWithMoreThan3LivingNeighboursShouldDieFromOverpopulation() {
        gameBoard.set_cell_state_to_dead(1,1);
        gameBoard.set_cell_state_to_alive(1,2);
        gameBoard.set_cell_state_to_alive(1,3);
        gameBoard.set_cell_state_to_alive(2,1);
        gameBoard.set_cell_state_to_alive(2,2);
        gameBoard.set_cell_state_to_alive(2,3);
        gameBoard.set_cell_state_to_dead(3,1);
        gameBoard.set_cell_state_to_dead(3,2);
        gameBoard.set_cell_state_to_dead(3,3);
        gameBoard.evolve();
        assertEquals(0,gameBoard.get_cell_state(2,2));
    }

    @Test
    public void livingCellWith2LivingNeighboursShouldSurvive() {
        gameBoard.set_cell_state_to_dead(1,1);
        gameBoard.set_cell_state_to_dead(1,2);
        gameBoard.set_cell_state_to_dead(1,3);
        gameBoard.set_cell_state_to_alive(2,1);
        gameBoard.set_cell_state_to_alive(2,2);
        gameBoard.set_cell_state_to_alive(2,3);
        gameBoard.set_cell_state_to_dead(3,1);
        gameBoard.set_cell_state_to_dead(3,2);
        gameBoard.set_cell_state_to_dead(3,3);
        gameBoard.evolve();
        assertEquals(1,gameBoard.get_cell_state(2,2));
    }

    @Test
    public void livingCellWith3LivingNeighboursShouldSurvive() {
        gameBoard.set_cell_state_to_dead(1,1);
        gameBoard.set_cell_state_to_dead(1,2);
        gameBoard.set_cell_state_to_alive(1,3);
        gameBoard.set_cell_state_to_alive(2,1);
        gameBoard.set_cell_state_to_alive(2,2);
        gameBoard.set_cell_state_to_alive(2,3);
        gameBoard.set_cell_state_to_dead(3,1);
        gameBoard.set_cell_state_to_dead(3,2);
        gameBoard.set_cell_state_to_dead(3,3);
        gameBoard.evolve();
        assertEquals(1,gameBoard.get_cell_state(2,2));
    }

    @Test
    public void deadCellShouldRemainDeadIfFewerThan3LiveNeighbours() {
        gameBoard.set_cell_state_to_dead(1,1);
        gameBoard.set_cell_state_to_dead(1,2);
        gameBoard.set_cell_state_to_dead(1,3);
        gameBoard.set_cell_state_to_alive(2,1);
        gameBoard.set_cell_state_to_dead(2,2);
        gameBoard.set_cell_state_to_alive(2,3);
        gameBoard.set_cell_state_to_dead(3,1);
        gameBoard.set_cell_state_to_dead(3,2);
        gameBoard.set_cell_state_to_dead(3,3);
        gameBoard.evolve();
        assertEquals(0,gameBoard.get_cell_state(2,2));
    }

    @Test
    public void deadCellShouldReviveWith3LiveNeighbours() {
        gameBoard.set_cell_state_to_dead(1,1);
        gameBoard.set_cell_state_to_dead(1,2);
        gameBoard.set_cell_state_to_alive(1,3);
        gameBoard.set_cell_state_to_alive(2,1);
        gameBoard.set_cell_state_to_dead(2,2);
        gameBoard.set_cell_state_to_alive(2,3);
        gameBoard.set_cell_state_to_dead(3,1);
        gameBoard.set_cell_state_to_dead(3,2);
        gameBoard.set_cell_state_to_dead(3,3);
        gameBoard.evolve();
        assertEquals(1,gameBoard.get_cell_state(2,2));
    }

    @Test
    public void deadCellShouldStayDeadIfMoreThan3LiveNeighbours() {
        gameBoard.set_cell_state_to_dead(1,1);
        gameBoard.set_cell_state_to_dead(1,2);
        gameBoard.set_cell_state_to_alive(1,3);
        gameBoard.set_cell_state_to_alive(2,1);
        gameBoard.set_cell_state_to_dead(2,2);
        gameBoard.set_cell_state_to_alive(2,3);
        gameBoard.set_cell_state_to_alive(3,1);
        gameBoard.set_cell_state_to_dead(3,2);
        gameBoard.set_cell_state_to_dead(3,3);
        gameBoard.evolve();
        assertEquals(0,gameBoard.get_cell_state(2,2));
    }

    @Test
    public void testForAKnownPatternToEmergeHorizontalToVerticalAndBackAgain() {
        gameBoard.set_cell_state_to_dead(1,1);
        gameBoard.set_cell_state_to_dead(1,2);
        gameBoard.set_cell_state_to_dead(1,3);
        gameBoard.set_cell_state_to_alive(2,1);
        gameBoard.set_cell_state_to_alive(2,2);
        gameBoard.set_cell_state_to_alive(2,3);
        gameBoard.set_cell_state_to_dead(3,1);
        gameBoard.set_cell_state_to_dead(3,2);
        gameBoard.set_cell_state_to_dead(3,3);
        gameBoard.evolve();
        assertEquals(0,gameBoard.get_cell_state(1,1));
        assertEquals(1,gameBoard.get_cell_state(1,2));
        assertEquals(0,gameBoard.get_cell_state(1,3));
        assertEquals(0,gameBoard.get_cell_state(2,1));
        assertEquals(1,gameBoard.get_cell_state(2,2));
        assertEquals(0,gameBoard.get_cell_state(2,3));
        assertEquals(0,gameBoard.get_cell_state(3,1));
        assertEquals(1,gameBoard.get_cell_state(3,2));
        assertEquals(0,gameBoard.get_cell_state(3,3));
        gameBoard.evolve();
        assertEquals(0,gameBoard.get_cell_state(1,1));
        assertEquals(0,gameBoard.get_cell_state(1,2));
        assertEquals(0,gameBoard.get_cell_state(1,3));
        assertEquals(1,gameBoard.get_cell_state(2,1));
        assertEquals(1,gameBoard.get_cell_state(2,2));
        assertEquals(1,gameBoard.get_cell_state(2,3));
        assertEquals(0,gameBoard.get_cell_state(3,1));
        assertEquals(0,gameBoard.get_cell_state(3,2));
        assertEquals(0,gameBoard.get_cell_state(3,3));
    }
}