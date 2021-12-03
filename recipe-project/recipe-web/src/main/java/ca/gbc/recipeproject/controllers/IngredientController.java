package ca.gbc.recipeproject.controllers;

import ca.gbc.recipeproject.model.Ingredient;
import ca.gbc.recipeproject.model.Recipe;
import ca.gbc.recipeproject.model.User;
import ca.gbc.recipeproject.services.springdatajpa.IngredientSDJpaService;
import ca.gbc.recipeproject.services.springdatajpa.RecipeSDJpaService;
import ca.gbc.recipeproject.services.springdatajpa.UserSDJpaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.Set;

@Controller
public class IngredientController {
    private final RecipeSDJpaService recipeSDJpaService;
    private final UserSDJpaService userSDJpaService;
    private IngredientSDJpaService ingredientSDJpaService;


    public IngredientController(RecipeSDJpaService recipeSDJpaService, UserSDJpaService userSDJpaService, IngredientSDJpaService ingredientSDJpaService) {

        this.recipeSDJpaService = recipeSDJpaService;
        this.userSDJpaService = userSDJpaService;
        this.ingredientSDJpaService = ingredientSDJpaService;

    }

    // INDEX / DISPLAY ALL INGREDIENTS
    @RequestMapping({"", "/ingredient", "/ingredient/index", "/index.html"})
    public String listIngredients(Model model) {
        User user = userSDJpaService.findById(1L);
        model.addAttribute("user", user);
        model.addAttribute("ingredients", ingredientSDJpaService.findAll());
        return "/ingredient/index";

    }
    //SAVE TO CART
    @PostMapping(value = "/ingredient/cart")
    public String cart(User user, Model model) {
        userSDJpaService.findById(1L).setShoppingList( user.getShoppingList());
        userSDJpaService.save(userSDJpaService.findById(1L));
        model.addAttribute("user", user);
        return "/ingredient/ingredientConfirm";
    }

    //CREATE/SAVE

    @RequestMapping({"/ingredient/create", "/ingredient/create.html"})
    public String create(Model model) {
        Ingredient ingredient = new Ingredient();
        model.addAttribute("ingredient", ingredient);
        return "/ingredient/create";
    }

    @PostMapping(value = "/ingredient/save")
    public String save(Ingredient ingredient, Model model) {
        ingredientSDJpaService.save(ingredient);
        model.addAttribute("ingredient", ingredient);
        return "/ingredient/ingredientConfirm";
    }

}
