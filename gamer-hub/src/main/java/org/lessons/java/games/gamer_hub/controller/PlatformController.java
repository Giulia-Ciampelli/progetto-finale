package org.lessons.java.games.gamer_hub.controller;

import java.util.List;

import org.lessons.java.games.gamer_hub.model.Game;
import org.lessons.java.games.gamer_hub.model.Platform;
import org.lessons.java.games.gamer_hub.service.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class PlatformController {
    
    @Autowired
    private PlatformService platformService;

    // index
    @GetMapping
    public String index(Model model) {
        List<Platform> platforms = platformService.findAll();
        model.addAttribute("platforms", platforms);
        return "platforms/index";
    }

    // show
    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {
        model.addAttribute("platform", platformService.getById(id));
        return "platforms/show";
    }

    // ricerche personalizzate
    @GetMapping("/search-by-name")
    public String searchByName(@RequestParam(name = "name") String name, Model model) {
        List<Platform> platforms = platformService.findByName(name);
        model.addAttribute("platforms", platforms);
        return "platforms/index";
    }

    // create
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("platform", new Platform());
        return "platforms/create-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("platform") Platform formPlatform, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "platforms/create-edit";
        }

        platformService.create(formPlatform);
        return "redirect:/platforms";
    }

    // edit
    @GetMapping("/edit/{id}")
    public String eidt(@PathVariable int id, Model model) {
        model.addAttribute("platform", platformService.getById(id));
        model.addAttribute("edit", true);
        return "platforms/create-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("platform") Platform formPlatform, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "platforms/create-edit";
        }

        platformService.update(formPlatform);
        return "redirect:/platforms";
    }

    // delete
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        Platform platformToDelete = platformService.getById(id);

        for (Game linkedGame : platformToDelete.getGames()) {
            linkedGame.getPlatforms().remove(platformToDelete);
        }

        platformService.delete(platformToDelete);
        return "redirect:/platforms";
    }
}
