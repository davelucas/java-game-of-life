package com.example.gameoflife;

public class Cell {
    public final int x;
    public final int y;

    public Cell() {
        this(0,0);
    }

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
