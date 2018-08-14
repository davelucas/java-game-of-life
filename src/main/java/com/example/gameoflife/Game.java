package com.example.gameoflife;

import java.util.Arrays;
import java.util.stream.Stream;

import static java.util.stream.IntStream.range;

public class Game {
    public final int rows;
    public final int columns;
    private boolean[][] board;

    public Game(int rows, int columns, Cell[] activeCells) {
        try {
            this.rows = rows;
            this.columns = columns;
            board = new boolean[columns][rows];

            Arrays.stream(activeCells).forEach(cell -> board[cell.x][cell.y] = true);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCellException(e);
        }
    }

    public Cell[] toggle(Cell cell) {
        try {
            board[cell.x][cell.y] = !board[cell.x][cell.y];
            return activeCellsFrom(board);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCellException(e);
        }
    }

    public Cell[] nextGeneration() {
        final boolean[][] newBoard = new boolean[columns][rows];

        allCellsOnBoard().forEach(cell -> {
            final int neighbourCount = neighbourCount(board, cell.x, cell.y);
            if (board[cell.x][cell.y]) {
                if (neighbourCount == 2 || neighbourCount == 3) newBoard[cell.x][cell.y] = true;
            } else {
                newBoard[cell.x][cell.y] = neighbourCount == 3 || board[cell.x][cell.y];
            }
        });

        board = newBoard;
        return activeCellsFrom(board);
    }

    private int neighbourCount(boolean[][] board, int x, int y) {
        int count = 0;

        for (int yi = y - 1; yi <= y + 1; yi++) {
            if (withinVerticalSpace(yi)) {
                for (int xi = x - 1; xi <= x + 1; xi++) {
                    if (withinHorizontalSpace(xi)) {
                        if (!(x == xi && y == yi)) {
                            if (board[xi][yi]) count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    private Cell[] activeCellsFrom(boolean[][] board) {
        return allCellsOnBoard()
            .filter(cell -> board[cell.x][cell.y])
            .toArray(Cell[]::new);
    }

    private Stream<Cell> allCellsOnBoard() {
        return range(0, columns).boxed().flatMap(x -> range(0, rows).boxed().map(y -> new Cell(x, y)));
    }

    private boolean withinHorizontalSpace(int xi) {
        return xi >= 0 && xi < columns;
    }

    private boolean withinVerticalSpace(int y) {
        return y >= 0 && y < rows;
    }
}
