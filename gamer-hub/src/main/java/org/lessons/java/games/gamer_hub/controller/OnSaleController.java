package org.lessons.java.games.gamer_hub.controller;

import org.lessons.java.games.gamer_hub.model.OnSale;
import org.lessons.java.games.gamer_hub.service.OnSaleService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/sales")
public class OnSaleController {
    
    @Autowired
    private OnSaleService saleService;

    // create
    @PostMapping("/create")
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
