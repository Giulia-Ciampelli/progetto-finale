package org.lessons.java.games.gamer_hub.controller;

import java.util.List;

import org.lessons.java.games.gamer_hub.model.Game;
import org.lessons.java.games.gamer_hub.model.OnSale;
import org.lessons.java.games.gamer_hub.service.GameService;
import org.lessons.java.games.gamer_hub.service.OnSaleService;
import org.lessons.java.games.gamer_hub.service.PlatformService;
import org.lessons.java.games.gamer_hub.service.TagService;
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
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private OnSaleService saleService;

    @Autowired
    private TagService tagService;

    @Autowired
    private PlatformService platformService;

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
        model.addAttribute("sales", game.getSales());
        model.addAttribute("tags", game.getTags());
        return "games/show";
    }

    // #region ricerche personalizzate
    @GetMapping("/search-by-name")
    public String searchByName(@RequestParam(name = "name") String name, Model model) {
        List<Game> games = gameService.findByName(name);
        model.addAttribute("games", games);
        return "games/index";
    }

    // #endregion ricerche personalizzate

    // create
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("game", new Game());
        model.addAttribute("tags", tagService.findAll());
        model.addAttribute("tags", platformService.findAll());
        return "games/create-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("game") Game formGame, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("tags", tagService.findAll());
            model.addAttribute("tags", platformService.findAll());
            return "games/create-edit";
        }

        gameService.create(formGame);
        return "redirect:/games";
    }

    // update
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("game", gameService.getById(id));
        model.addAttribute("tags", tagService.findAll());
        model.addAttribute("tags", platformService.findAll());
        model.addAttribute("edit", true);
        return "games/create-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable int id, @Valid @ModelAttribute("game") Game formGame,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("tags", tagService.findAll());
            model.addAttribute("tags", platformService.findAll());
            return "games/create-edit";
        }

        gameService.create(formGame);
        return "redirect:/games";
    }

    // delete
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        Game game = gameService.getById(id);

        for (OnSale saleToDelete : game.getSales()) {
            saleService.delete(saleToDelete);
        }

        gameService.delete(game);
        return "redirect:/games";
    }

    // show saldi
    @GetMapping("/{id}/sale")
    public String onSale(@PathVariable int id, Model model) {
        OnSale sale = new OnSale();
        sale.setGame(gameService.getById(id));
        model.addAttribute("sale", sale);
        return "sales/create-edit";
    }
}
