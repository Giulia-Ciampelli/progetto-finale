package org.lessons.java.games.gamer_hub.controller.api;

import java.util.List;
import java.util.Optional;

import org.lessons.java.games.gamer_hub.model.Game;
import org.lessons.java.games.gamer_hub.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/games")
public class GameRestController {

    // TODO: fai pagina login, finisci rest controller, sistema layout index di tag e piattaforme
    
    @Autowired
    private GameService gameService;

    // index
    @GetMapping
    public List<Game> index() {
        List<Game> games = gameService.findAll();
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

    // create

    // edit

    // delete
}
