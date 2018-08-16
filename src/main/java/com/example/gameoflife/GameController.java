package com.example.gameoflife;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
public class GameController {

    private final GameService gameService;

    @ExceptionHandler(value = {UnknownGameException.class})
    public ResponseEntity handleUnknownGameException(UnknownGameException e)  {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = {InvalidCellException.class})
    public ResponseEntity handleInvalidCellException(InvalidCellException e)  {
        return ResponseEntity.badRequest().build();
    }

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public GameId newBoard(@RequestBody GameConfig gameConfig) {
        return new GameId(gameService.newBoard(gameConfig.rows, gameConfig.columns, gameConfig.activeCells));
    }

    @PostMapping("{id}/toggleCell")
    @ResponseStatus(HttpStatus.OK)
    public Cell[] toggleCell(@PathVariable GameId id, @RequestBody Cell cell) {
        return gameService.toggle(id.id, cell);
    }

    @GetMapping("{id}/nextGeneration")
    public Cell[] nextGeneration(@PathVariable GameId id) {
        return gameService.nextGeneration(id.id);
    }
}
