package com.example.gameoflife;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class GameTest {
    @Test
    public void initialisesAGameWithTheProvidedDimensions() {
        final Cell[] cells = {};
        Game game = new Game(3,2, cells);

        assertThat(game.rows).isEqualTo(3);
        assertThat(game.columns).isEqualTo(2);
    }

    @Test(expected = InvalidCellException.class)
    public void throwsInvalidCellExceptionIfAnActiveCellIsNotOnTheBoard() {
        final Cell[] cells = {
            new Cell(1,1)
        };

        new Game(1,1, cells);
    }

    @Test
    public void togglesACellOnIfItIsCurrentlyOff() {
        Game game = new Game(1,1, new Cell[0]);
        final Cell[] cells = game.toggle(new Cell(0, 0));

        assertThat(cells).containsExactly(new Cell(0, 0));
    }

    @Test
    public void togglesACellOffIfItIsCurrentlyOn() {
        Game game = new Game(1,1, new Cell[]{new Cell(0, 0)});
        final Cell[] cells = game.toggle(new Cell(0, 0));

        assertThat(cells).isEmpty();
    }

    @Test(expected = InvalidCellException.class)
    public void throwsInvalidCellExceptionIfTheToggledCellIsNotOnTheBoard() {
        Game game = new Game(1,1, new Cell[]{new Cell(0, 0)});
        game.toggle(new Cell(1, 0));
    }

    // Any live cell with fewer than two live neighbors dies, as if by under population
    @Test
    public void aLiveCellWithFewerThanTwoLiveNeighboursDies() {
        Game game = new Game(2,2, new Cell[]{new Cell(0, 0)});

        final Cell[] cells = game.nextGeneration();

        assertThat(cells).isEmpty();
    }

    // Any live cell with two live neighbors lives on to the next generation
    @Test
    public void aLiveCellWithTwoLiveNeighboursLivesOnToTheNextGeneration() {
        Game game = new Game(2,2, new Cell[]{
            new Cell(0, 0),
            new Cell(0, 1),
            new Cell(1, 0)
        });

        final Cell[] cells = game.nextGeneration();

        assertThat(cells).contains(new Cell(0, 0));
    }

    // Any live cell with three live neighbors lives on to the next generation
    @Test
    public void aLiveCellWithThreeLiveNeighboursLivesOnToTheNextGeneration() {
        Game game = new Game(2,2, new Cell[]{
            new Cell(0, 0),
            new Cell(0, 1),
            new Cell(1, 0),
            new Cell(1, 1)
        });

        final Cell[] cells = game.nextGeneration();

        assertThat(cells).contains(new Cell(0, 0));
    }

    // Any live cell with more than three live neighbors dies, as if by overpopulation
    @Test
    public void aLiveCellWithMoreThanThreeLiveNeighboursDies() {
        Game game = new Game(2,3, new Cell[]{
            new Cell(0, 0),
            new Cell(1, 0),
            new Cell(2, 0),
            new Cell(0, 1),
            new Cell(1, 1)
        });

        final Cell[] cells = game.nextGeneration();

        assertThat(cells).doesNotContain(new Cell(1, 0));
    }

    // Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction
    @Test
    public void aDeadCellWithExactlyThreeLiveNeighboursBecomesAlive() {
        Game game = new Game(2,2, new Cell[]{
            new Cell(0, 1),
            new Cell(1, 0),
            new Cell(1, 1)
        });

        final Cell[] cells = game.nextGeneration();

        assertThat(cells).contains(new Cell(0, 0));
    }

    @Test
    public void aDeadCellWithLessThanThreeLiveNeighboursRemainsDead() {
        Game game = new Game(2,2, new Cell[]{
            new Cell(0, 1),
            new Cell(1, 0)
        });

        final Cell[] cells = game.nextGeneration();

        assertThat(cells).doesNotContain(new Cell(0, 0));
    }

    @Test
    public void aDeadCellWithMoreThanThreeLiveNeighboursRemainsDead() {
        Game game = new Game(2,3, new Cell[]{
            new Cell(0, 0),
            new Cell(2, 0),
            new Cell(0, 1),
            new Cell(1, 1)
        });

        final Cell[] cells = game.nextGeneration();

        assertThat(cells).doesNotContain(new Cell(1, 0));
    }

}