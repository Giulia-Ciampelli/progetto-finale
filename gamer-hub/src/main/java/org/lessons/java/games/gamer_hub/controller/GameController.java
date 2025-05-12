package org.lessons.java.games.gamer_hub.controller;

import java.util.List;

import org.lessons.java.games.gamer_hub.model.Game;
import org.lessons.java.games.gamer_hub.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/games")
public class GameController {
    
    @Autowired
    private GameService gameService;

    //TODO: configura l'autenticazione
    @GetMapping
    public String index(Authentication authentication, Model model) {
        List<Game> games = gameService.findAll();
        model.addAttribute("games", games);
        return "games/index";
    }
}
