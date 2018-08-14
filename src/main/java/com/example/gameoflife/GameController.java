package com.example.gameoflife;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {

    private final GameService service;

    @Autowired
    public GameController(GameService service) {
        this.service = service;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Game newBoard(@RequestBody GameConfig gameConfig) {
        return new Game(service.newBoard(gameConfig.rows, gameConfig.columns, gameConfig.activeCells));
    }

    @PostMapping("{id}/toggleCell")
    @ResponseStatus(HttpStatus.OK)
    public Cell[] toggleCell(@PathVariable String id, @RequestBody Cell cell) {
        return service.toggleCell(id, cell);
    }

    @GetMapping("{id}/nextGeneration")
    public Cell[] nextGeneration(@PathVariable String id) {
        return service.nextGeneration(id);
    }
}
