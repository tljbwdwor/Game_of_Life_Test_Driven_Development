package com.tlj;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameOfLifeTest {

    GameOfLife gameOfLife = new GameOfLife(5,8);
    GameOfLife gameOfLife2 = new GameOfLife(3,1);

    @Test
    public void createdGameBoardShouldNotReturnNull() {
        assertNotNull(gameOfLife);
    }

    @Test
    public void createdGameBoardShouldReturnCorrectXValue() {
        assertEquals(5, gameOfLife.x_axis);
    }

    @Test
    public void createdGameBoardShouldReturnCorrectYValue() {
        assertEquals(8, gameOfLife.y_axis);
    }

    @Test
    public void gameBoardShouldPrintInConsoleAsExpected() {
        assertEquals("|...|", gameOfLife2.print_board_in_console());
    }

    @Test
    public void cellStateShouldbeSetToDeadOr0() {
        assertEquals(0, gameOfLife.get_cell_state(2,2));
    }

    @Test
    public void cellStateShouldBeChangedBySetMethod() {
        assertEquals(0, gameOfLife.get_cell_state(2,2));
        gameOfLife.set_cell_state(1,1,1);
        assertEquals(1, gameOfLife.get_cell_state(1,1));
    }

    @Test
    public void outOfBoundsCellShouldDefaultToDeadOr0() {
        assertEquals(0, gameOfLife.get_cell_state(10,10));
    }

    @Test
    public void outOfBoundsCellShouldNotBeAbleToBeSetToAliveOr1() {
        gameOfLife.set_cell_state(10,10,1);
        assertEquals(0, gameOfLife.get_cell_state(10,10));
    }

    @Test
    public void printBoardInConsoleMethodShouldHandleLiveAndDeadCellsGraphically() {
        gameOfLife2.set_cell_state(1,0,1);
        assertEquals("|.*.|", gameOfLife2.print_board_in_console());
    }

    @Test
    public void methodShouldExistForSettingStatusToDeadOr0() {
        gameOfLife.set_cell_state(2,2,1);
        assertEquals(1, gameOfLife.get_cell_state(2,2));
        gameOfLife.set_cell_state_to_dead(2,2);
        assertEquals(0, gameOfLife.get_cell_state(2,2));
    }

    @Test
    public void methodShouldExistForSettingStatusToAliveOr1() {
        gameOfLife.set_cell_state(2,2,0);
        assertEquals(0, gameOfLife.get_cell_state(2,2));
        gameOfLife.set_cell_state_to_alive(2,2);
        assertEquals(1, gameOfLife.get_cell_state(2,2));
    }

    @Test
    public void clearBoardShouldResultInAllCellsRevertingToDead() {
        gameOfLife.set_cell_state_to_alive(2,2);
        gameOfLife.set_cell_state_to_alive(2,3);
        gameOfLife.set_cell_state_to_alive(2,4);
        assertEquals(1, gameOfLife.get_cell_state(2,2));
        assertEquals(1, gameOfLife.get_cell_state(2,3));
        assertEquals(1, gameOfLife.get_cell_state(2,4));
        gameOfLife.clear_board();
        assertEquals(0, gameOfLife.get_cell_state(2,2));
        assertEquals(0, gameOfLife.get_cell_state(2,3));
        assertEquals(0, gameOfLife.get_cell_state(2,4));
    }

    @Test
    public void livingNeighbourCountShouldReturnCorrectNumber() {
        gameOfLife.set_cell_state_to_alive(2,2);
        gameOfLife.set_cell_state_to_alive(2,3);
        gameOfLife.set_cell_state_to_alive(2,4);
        assertEquals(3, gameOfLife.count_living_neighbours(3,3));
    }

    @Test
    public void livingCellWithFewerThan2LivingNeighboursShouldDieInNextGeneration() {
        gameOfLife.set_cell_state_to_dead(1,1);
        gameOfLife.set_cell_state_to_dead(1,2);
        gameOfLife.set_cell_state_to_dead(1,3);
        gameOfLife.set_cell_state_to_dead(2,1);
        gameOfLife.set_cell_state_to_alive(2,2);
        gameOfLife.set_cell_state_to_alive(2,3);
        gameOfLife.set_cell_state_to_dead(3,1);
        gameOfLife.set_cell_state_to_dead(3,2);
        gameOfLife.set_cell_state_to_dead(3,3);
        gameOfLife.evolve_to_next_generation();
        assertEquals(0, gameOfLife.get_cell_state(2,2));
    }

    @Test
    public void livingCellWithMoreThan3LivingNeighboursShouldDieFromOverpopulation() {
        gameOfLife.set_cell_state_to_dead(1,1);
        gameOfLife.set_cell_state_to_alive(1,2);
        gameOfLife.set_cell_state_to_alive(1,3);
        gameOfLife.set_cell_state_to_alive(2,1);
        gameOfLife.set_cell_state_to_alive(2,2);
        gameOfLife.set_cell_state_to_alive(2,3);
        gameOfLife.set_cell_state_to_dead(3,1);
        gameOfLife.set_cell_state_to_dead(3,2);
        gameOfLife.set_cell_state_to_dead(3,3);
        gameOfLife.evolve_to_next_generation();
        assertEquals(0, gameOfLife.get_cell_state(2,2));
    }

    @Test
    public void livingCellWith2LivingNeighboursShouldSurvive() {
        gameOfLife.set_cell_state_to_dead(1,1);
        gameOfLife.set_cell_state_to_dead(1,2);
        gameOfLife.set_cell_state_to_dead(1,3);
        gameOfLife.set_cell_state_to_alive(2,1);
        gameOfLife.set_cell_state_to_alive(2,2);
        gameOfLife.set_cell_state_to_alive(2,3);
        gameOfLife.set_cell_state_to_dead(3,1);
        gameOfLife.set_cell_state_to_dead(3,2);
        gameOfLife.set_cell_state_to_dead(3,3);
        gameOfLife.evolve_to_next_generation();
        assertEquals(1, gameOfLife.get_cell_state(2,2));
    }

    @Test
    public void livingCellWith3LivingNeighboursShouldSurvive() {
        gameOfLife.set_cell_state_to_dead(1,1);
        gameOfLife.set_cell_state_to_dead(1,2);
        gameOfLife.set_cell_state_to_alive(1,3);
        gameOfLife.set_cell_state_to_alive(2,1);
        gameOfLife.set_cell_state_to_alive(2,2);
        gameOfLife.set_cell_state_to_alive(2,3);
        gameOfLife.set_cell_state_to_dead(3,1);
        gameOfLife.set_cell_state_to_dead(3,2);
        gameOfLife.set_cell_state_to_dead(3,3);
        gameOfLife.evolve_to_next_generation();
        assertEquals(1, gameOfLife.get_cell_state(2,2));
    }

    @Test
    public void deadCellShouldRemainDeadIfFewerThan3LiveNeighbours() {
        gameOfLife.set_cell_state_to_dead(1,1);
        gameOfLife.set_cell_state_to_dead(1,2);
        gameOfLife.set_cell_state_to_dead(1,3);
        gameOfLife.set_cell_state_to_alive(2,1);
        gameOfLife.set_cell_state_to_dead(2,2);
        gameOfLife.set_cell_state_to_alive(2,3);
        gameOfLife.set_cell_state_to_dead(3,1);
        gameOfLife.set_cell_state_to_dead(3,2);
        gameOfLife.set_cell_state_to_dead(3,3);
        gameOfLife.evolve_to_next_generation();
        assertEquals(0, gameOfLife.get_cell_state(2,2));
    }

    @Test
    public void deadCellShouldReviveWith3LiveNeighbours() {
        gameOfLife.set_cell_state_to_dead(1,1);
        gameOfLife.set_cell_state_to_dead(1,2);
        gameOfLife.set_cell_state_to_alive(1,3);
        gameOfLife.set_cell_state_to_alive(2,1);
        gameOfLife.set_cell_state_to_dead(2,2);
        gameOfLife.set_cell_state_to_alive(2,3);
        gameOfLife.set_cell_state_to_dead(3,1);
        gameOfLife.set_cell_state_to_dead(3,2);
        gameOfLife.set_cell_state_to_dead(3,3);
        gameOfLife.evolve_to_next_generation();
        assertEquals(1, gameOfLife.get_cell_state(2,2));
    }

    @Test
    public void deadCellShouldStayDeadIfMoreThan3LiveNeighbours() {
        gameOfLife.set_cell_state_to_dead(1,1);
        gameOfLife.set_cell_state_to_dead(1,2);
        gameOfLife.set_cell_state_to_alive(1,3);
        gameOfLife.set_cell_state_to_alive(2,1);
        gameOfLife.set_cell_state_to_dead(2,2);
        gameOfLife.set_cell_state_to_alive(2,3);
        gameOfLife.set_cell_state_to_alive(3,1);
        gameOfLife.set_cell_state_to_dead(3,2);
        gameOfLife.set_cell_state_to_dead(3,3);
        gameOfLife.evolve_to_next_generation();
        assertEquals(0, gameOfLife.get_cell_state(2,2));
    }

    @Test
    public void testForAKnownPatternToEmergeHorizontalToVerticalAndBackAgain() {
        gameOfLife.set_cell_state_to_dead(1,1);
        gameOfLife.set_cell_state_to_dead(1,2);
        gameOfLife.set_cell_state_to_dead(1,3);
        gameOfLife.set_cell_state_to_alive(2,1);
        gameOfLife.set_cell_state_to_alive(2,2);
        gameOfLife.set_cell_state_to_alive(2,3);
        gameOfLife.set_cell_state_to_dead(3,1);
        gameOfLife.set_cell_state_to_dead(3,2);
        gameOfLife.set_cell_state_to_dead(3,3);
        gameOfLife.evolve_to_next_generation();
        assertEquals(0, gameOfLife.get_cell_state(1,1));
        assertEquals(1, gameOfLife.get_cell_state(1,2));
        assertEquals(0, gameOfLife.get_cell_state(1,3));
        assertEquals(0, gameOfLife.get_cell_state(2,1));
        assertEquals(1, gameOfLife.get_cell_state(2,2));
        assertEquals(0, gameOfLife.get_cell_state(2,3));
        assertEquals(0, gameOfLife.get_cell_state(3,1));
        assertEquals(1, gameOfLife.get_cell_state(3,2));
        assertEquals(0, gameOfLife.get_cell_state(3,3));
        gameOfLife.evolve_to_next_generation();
        assertEquals(0, gameOfLife.get_cell_state(1,1));
        assertEquals(0, gameOfLife.get_cell_state(1,2));
        assertEquals(0, gameOfLife.get_cell_state(1,3));
        assertEquals(1, gameOfLife.get_cell_state(2,1));
        assertEquals(1, gameOfLife.get_cell_state(2,2));
        assertEquals(1, gameOfLife.get_cell_state(2,3));
        assertEquals(0, gameOfLife.get_cell_state(3,1));
        assertEquals(0, gameOfLife.get_cell_state(3,2));
        assertEquals(0, gameOfLife.get_cell_state(3,3));
    }

    @Test
    public void shouldReturnXAxisValue() {
        assertEquals(5, gameOfLife.get_x_axis());
    }

    @Test
    public void shouldReturnYAxisValue() {
        assertEquals(8, gameOfLife.get_y_axis());
    }

    @Test
    public void shouldUpdateXAxisWithNewValue() {
        gameOfLife.set_x_axis(10);
        assertEquals(10, gameOfLife.get_x_axis());
    }

    @Test
    public void shouldUpdateYAxisWithNewValue() {
        gameOfLife.set_y_axis(20);
        assertEquals(20, gameOfLife.get_y_axis());
    }
}