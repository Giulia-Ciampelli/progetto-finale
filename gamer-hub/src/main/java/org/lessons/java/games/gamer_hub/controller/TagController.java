package org.lessons.java.games.gamer_hub.controller;

import java.util.List;

import org.lessons.java.games.gamer_hub.model.Game;
import org.lessons.java.games.gamer_hub.model.Tag;
import org.lessons.java.games.gamer_hub.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    // index
    @GetMapping
    public String index(Model model) {
        List<Tag> tags = tagService.findAll();
        model.addAttribute("tags", tags);
        return "tags/index";
    }

    // show
    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {
        model.addAttribute("tag", tagService.getById(id));
        return "tags/show";
    }

    // ricerche personalizzate
    @GetMapping("/search-by-name")
    public String searchByName(@RequestParam(name = "name") String name, Model model) {
        List<Tag> tags = tagService.findByName(name);
        model.addAttribute("tags", tags);
        return "tags/index";
    }

    // create
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("tag", new Tag());
        return "tags/create-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("tag") Tag formTag, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "tags/create-edit";
        }

        tagService.create(formTag);
        return "redirect:/tags";
    }

    // edit
    @GetMapping("/edit/{id}")
    public String eidt(@PathVariable int id, Model model) {
        model.addAttribute("tag", tagService.getById(id));
        model.addAttribute("edit", true);
        return "tags/create-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("tag") Tag formTag, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "tags/create-edit";
        }

        tagService.update(formTag);
        return "redirect:/tags";
    }

    // delete
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        Tag tagToDelete = tagService.getById(id);

        for (Game linkedGame : tagToDelete.getGames()) {
            linkedGame.getTags().remove(tagToDelete);
        }

        tagService.delete(tagToDelete);
        return "redirect:/tags";
    }
}
