package ca.gbc.recipeproject.controllers;

import ca.gbc.recipeproject.services.springdatajpa.RecipeSDJpaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {

    private final RecipeSDJpaService recipeSDJpaService;


    public RecipeController(RecipeSDJpaService recipeSDJpaService) {

        this.recipeSDJpaService = recipeSDJpaService;

    }

    @RequestMapping({"", "/recipe", "/recipe/index", "/index.html"})
    public String listRecipes(Model model) {

        model.addAttribute("recipes", recipeSDJpaService.findAll());
        return "/recipe/index";

    }
}
