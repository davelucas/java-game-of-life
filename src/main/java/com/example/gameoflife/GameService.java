package com.example.gameoflife;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class GameService {

    private Map<String, Game> games = new HashMap<>();

    public String newBoard(int rows, int columns, Cell[] activeCells) {
        final String key = UUID.randomUUID().toString();
        games.put(key, new Game(rows, columns, activeCells));
        return key;
    }

    public Cell[] toggle(String id, Cell cell) {
        final Game game = games.get(id);

        if(game == null) throw new UnknownGameException();

        return game.toggle(cell);
    }

    public Cell[] nextGeneration(String id) {
        final Game game = games.get(id);

        if(game == null) throw new UnknownGameException();

        return game.nextGeneration();
    }
}
