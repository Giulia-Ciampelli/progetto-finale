package org.lessons.java.games.gamer_hub.controller.api;

import java.util.List;
import java.util.Optional;

import org.lessons.java.games.gamer_hub.model.Game;
import org.lessons.java.games.gamer_hub.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/games")
public class GameRestController {
    
    @Autowired
    private GameService gameService;

    // index
    @GetMapping
    public List<Game> index(@RequestParam(required = false) String name) {
        List<Game> games = gameService.findAll();

        if (name != null && !name.isBlank()) {
            return gameService.findByName(name);
        }

        return games;
    }

    // show
    @GetMapping("/{id}")
    public ResponseEntity<Game> show(@PathVariable int id) {
        Optional<Game> gameAttempt = gameService.findById(id);

        if (gameAttempt.isEmpty()) {
            return new ResponseEntity<Game>(HttpStatusCode.valueOf(404));
        }

        return new ResponseEntity<Game>(gameAttempt.get(), HttpStatusCode.valueOf(200));
    }

    // // create
    // @PostMapping
    // public ResponseEntity<Game> store(@Valid @RequestBody Game game) {
    //     return new ResponseEntity<Game>(gameService.create(game), HttpStatusCode.valueOf(201));
    // }

    // // edit
    // @PutMapping("/{id}")
    // public ResponseEntity<Game> update(@PathVariable int id, @Valid @RequestBody Game game) {
    //     Optional<Game> gameAttempt = gameService.findById(id);

    //     if (gameAttempt.isEmpty()) {
    //         return new ResponseEntity<Game>(HttpStatusCode.valueOf(404));
    //     }

    //     game.setId(id);
    //     return new ResponseEntity<Game>(gameService.update(game), HttpStatusCode.valueOf(201));
    // }

    // // delete
    // @DeleteMapping("/{id}")
    // public ResponseEntity<Game> delete(@Valid @PathVariable int id) {
    //     Optional<Game> gameAttempt = gameService.findById(id);

    //     if (gameAttempt.isEmpty()) {
    //         return new ResponseEntity<Game>(HttpStatusCode.valueOf(404));
    //     }

    //     gameService.deleteById(id);
    //     return new ResponseEntity<>(HttpStatusCode.valueOf(204));
    // }
}
