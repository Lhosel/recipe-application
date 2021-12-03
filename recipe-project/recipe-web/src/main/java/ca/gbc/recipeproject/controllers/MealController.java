package ca.gbc.recipeproject.controllers;

import ca.gbc.recipeproject.model.Ingredient;
import ca.gbc.recipeproject.model.Meal;
import ca.gbc.recipeproject.model.Recipe;
import ca.gbc.recipeproject.services.springdatajpa.IngredientSDJpaService;
import ca.gbc.recipeproject.services.springdatajpa.MealSDJpaService;
import ca.gbc.recipeproject.services.springdatajpa.RecipeSDJpaService;
import ca.gbc.recipeproject.services.springdatajpa.UserSDJpaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.Set;

@Controller
public class MealController {

    private final RecipeSDJpaService recipeSDJpaService;
    private final UserSDJpaService userSDJpaService;
    private final IngredientSDJpaService ingredientSDJpaService;
    private final MealSDJpaService mealSDJpaService;


    public MealController(RecipeSDJpaService recipeSDJpaService, UserSDJpaService userSDJpaService,
                          IngredientSDJpaService ingredientSDJpaService, MealSDJpaService mealSDJpaService) {

        this.recipeSDJpaService = recipeSDJpaService;
        this.userSDJpaService = userSDJpaService;
        this.ingredientSDJpaService = ingredientSDJpaService;
        this.mealSDJpaService = mealSDJpaService;

    }


    // INDEX / DISPLAY ALL MEALS
    @RequestMapping({"", "/meal", "/meal/index", "/index.html"})
    public String listMeals(Model model) {

        model.addAttribute("meals", mealSDJpaService.findAll());
        return "/meal/index";
    }

    //CREATE/SAVE

    @RequestMapping({"/meal/create", "/meal/create.html"})
    public String create(Model model) {
        Meal meal = new Meal();
        Set<Recipe> recipeList = recipeSDJpaService.findAll();
        model.addAttribute("recipeList", recipeList);
        model.addAttribute("meal", meal);
        return "/meal/create";
    }

    @PostMapping(value = "/meal/save")
    public String save(Meal meal, Model model) {
        meal.setUser(userSDJpaService.findById(1L));
        meal.setDate(new Date());
        mealSDJpaService.save(meal);
        model.addAttribute("meal", meal);
        return "/meal/mealConfirm";
    }

}
