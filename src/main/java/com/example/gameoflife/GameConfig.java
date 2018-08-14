package com.example.gameoflife;

public class GameConfig {
    public int rows;
    public int columns;
    public Cell[] activeCells;

    public GameConfig() {
        this(0, 0, new Cell[0]);
    }

    public GameConfig(int rows, int columns, Cell[] activeCells) {
        this.rows = rows;
        this.columns = columns;
        this.activeCells = activeCells;
    }
}
