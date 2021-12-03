package ca.gbc.recipeproject.controllers;

import ca.gbc.recipeproject.model.User;
import ca.gbc.recipeproject.services.springdatajpa.IngredientSDJpaService;
import ca.gbc.recipeproject.services.springdatajpa.MealSDJpaService;
import ca.gbc.recipeproject.services.springdatajpa.RecipeSDJpaService;
import ca.gbc.recipeproject.services.springdatajpa.UserSDJpaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    private final RecipeSDJpaService recipeSDJpaService;
    private final UserSDJpaService userSDJpaService;
    private final IngredientSDJpaService ingredientSDJpaService;
    private final MealSDJpaService mealSDJpaService;


    public UserController(RecipeSDJpaService recipeSDJpaService, UserSDJpaService userSDJpaService,
                          IngredientSDJpaService ingredientSDJpaService, MealSDJpaService mealSDJpaService) {

        this.recipeSDJpaService = recipeSDJpaService;
        this.userSDJpaService = userSDJpaService;
        this.ingredientSDJpaService = ingredientSDJpaService;
        this.mealSDJpaService = mealSDJpaService;

    }

    @RequestMapping("/profile/list")
    public String showCart(Model model) {
        User user = userSDJpaService.findById(1L);
        model.addAttribute("ingredients", user.getShoppingList());
        return "/profile/list";

    }
}