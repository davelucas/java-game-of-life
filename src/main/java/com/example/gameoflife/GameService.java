package com.example.gameoflife;

import org.springframework.stereotype.Component;

@Component
public class GameService {
    public String newBoard(int rows, int columns, Cell[] activeCells) {
        return "";
    }

    public Cell[] toggleCell(String id, Cell cell) {
        return new Cell[0];
    }

    public Cell[] nextGeneration(String id) {
        return new Cell[0];
    }
}
