package org.lessons.java.games.gamer_hub.controller;

import java.util.List;

import org.lessons.java.games.gamer_hub.model.OnSale;
import org.lessons.java.games.gamer_hub.service.OnSaleService;
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
@RequestMapping("/sales")
public class OnSaleController {
    
    @Autowired
    private OnSaleService saleService;

    // index
    @GetMapping
    public String index(Authentication authentication, Model model) {
        List<OnSale> sales = saleService.findAll();
        model.addAttribute("sales", sales);
        return "sales/index";
    }

    // ricerche personalizzate
    @GetMapping("/search-by-name")
    public String searchByName(@RequestParam(name = "name") String name, Model model) {
        List<OnSale> sales = saleService.findByName(name);
        model.addAttribute("sales", sales);
        return "sales/index";
    }

    // create
    @GetMapping("/create")
    public String store(@Valid @ModelAttribute("sale") OnSale formSale, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "sales/create-edit";
        }

        saleService.create(formSale);
        return "redirect:/games/" + formSale.getGame().getId();
    }

    // edit
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("sale", saleService.getById(id));
        model.addAttribute("edit", true);
        return "sales/create-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("sale") OnSale formSale, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "sales/create-edit";
        }

        saleService.update(formSale);
        return "redirect:/games/" + formSale.getGame().getId();
    }
}
