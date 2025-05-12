package org.lessons.java.games.gamer_hub.controller;

import java.util.List;

import org.lessons.java.games.gamer_hub.model.Game;
import org.lessons.java.games.gamer_hub.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    // index
    @GetMapping
    public String index(Authentication authentication, Model model) {
        List<Game> games = gameService.findAll();
        model.addAttribute("games", games);
        model.addAttribute("username", authentication.getName());
        return "games/index";
    }

    // show
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Game game = gameService.getById(id);
        model.addAttribute("game", game);
        return "games/show";
    }

    // TODO: aggiungi ricerca per nome, ricorda di mettere gli attributi anche per
    // le altre tabelle
    // #region ricerche personalizzate
    // #endregion ricerche personalizzate

    // create
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("game", new Game());
        return "games/create-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("game") Game formGame, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "games/create-edit";
        }
        gameService.create(formGame);
        return "redirect:/games";
    }

    // update
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("game", gameService.getById(id));
        model.addAttribute("edit", true);
        return "games/create-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable int id, @Valid @ModelAttribute("game") Game formGame, BindingResult bindingResult, Model model) {
        
        if (bindingResult.hasErrors()) {
            return "games/create-edit";
        }
        gameService.create(formGame);
        return "redirect:/games";
    }

    // delete
}
