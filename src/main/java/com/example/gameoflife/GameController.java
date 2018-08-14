package com.example.gameoflife;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public GameId newBoard(@RequestBody GameConfig gameConfig) {
        // TODO: Implement this method to return a GameId for a new game insatnce
        return null;
    }

    @PostMapping("{id}/toggleCell")
    @ResponseStatus(HttpStatus.OK)
    public Cell[] toggleCell(@PathVariable GameId id, @RequestBody Cell cell) {
        // TODO: Implement this method to toggle the given cell for the GameId
        return new Cell[0];
    }

    @GetMapping("{id}/nextGeneration")
    public Cell[] nextGeneration(@PathVariable GameId id) {
        // TODO: Implement this method to get the next generation of game of life for the GameId according to the rules in the README
        return new Cell[0];
    }
}
