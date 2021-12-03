package ca.gbc.recipeproject.controllers;

import ca.gbc.recipeproject.model.Ingredient;
import ca.gbc.recipeproject.model.Recipe;
import ca.gbc.recipeproject.model.User;
import ca.gbc.recipeproject.repositories.IngredientRepository;
import ca.gbc.recipeproject.services.springdatajpa.IngredientSDJpaService;
import ca.gbc.recipeproject.services.springdatajpa.RecipeSDJpaService;
import ca.gbc.recipeproject.services.springdatajpa.UserSDJpaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class RecipeController {

    private final RecipeSDJpaService recipeSDJpaService;
    private final UserSDJpaService userSDJpaService;
    private IngredientSDJpaService ingredientSDJpaService;


    public RecipeController(RecipeSDJpaService recipeSDJpaService, UserSDJpaService userSDJpaService, IngredientSDJpaService ingredientSDJpaService) {

        this.recipeSDJpaService = recipeSDJpaService;
        this.userSDJpaService = userSDJpaService;
        this.ingredientSDJpaService = ingredientSDJpaService;

    }

    // INDEX / DISPLAY ALL RECIPES
    @RequestMapping({"", "/recipe", "/recipe/index", "/index.html"})
    public String listRecipes(Model model) {

        model.addAttribute("user", userSDJpaService.findById(1L));
        model.addAttribute("recipes", recipeSDJpaService.findAll());
        return "/recipe/index";

    }

    // SEARCH

    @RequestMapping(value = {"/recipe/search", "/recipe/search.html"}, method = RequestMethod.GET)
    public String searchRecipes(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "/recipe/search";
    }

    @RequestMapping(value = "/recipe/search", method = RequestMethod.POST)
    public String searchRecipes(Model model, HttpServletRequest request) {
        String searchWord = request.getParameter("name");
        Set<Recipe> result = recipeSDJpaService.findByName(searchWord.toLowerCase());
        model.addAttribute("count", result.size());
        model.addAttribute("searchString", "Search Results for:  " + searchWord);
        if (result.isEmpty()) {
            System.out.println("This is empty!");
        } else {
            model.addAttribute("recipes", result);
        }
        return "/recipe/search";
    }

    // DETAILS

    @RequestMapping({"/recipe/{id}", "/{id}", "/recipe/recipe/{id}"})
    public String findRecipe(Model model, @PathVariable Long id) {
        Recipe recipe = recipeSDJpaService.findById(id);
        model.addAttribute("user", userSDJpaService.findById(1L));
        model.addAttribute("findRecipe", recipe);
        return "/recipe/details";
    }

    // View Ingredients
    @RequestMapping({"/recipe/{id}/ingredients", "/{id}/ingredients", "/recipe/recipe/{id}/ingredients"})
    public String getRecipeIngredients(Model model, @PathVariable Long id) {
        Recipe recipe = recipeSDJpaService.findById(id);
        model.addAttribute("findRecipe", recipe);
        return "/ingredient/recipeIngredients";
    }

    // View Steps
    @RequestMapping({"/recipe/{id}/steps", "/{id}/steps", "/recipe/recipe/{id}/steps"})
    public String getRecipeSteps(Model model, @PathVariable Long id) {
        Recipe recipe = recipeSDJpaService.findById(id);
        model.addAttribute("findRecipe", recipe);
        return "/ingredient/recipeSteps";
    }



    //CREATE/SAVE

    @RequestMapping({"/recipe/create", "/recipe/create.html"})
    public String create(Model model) {
        Recipe recipe = new Recipe();
        Set<Ingredient> ingredientsList = ingredientSDJpaService.findAll();
        model.addAttribute("ingredientList", ingredientsList);
        model.addAttribute("recipe", recipe);
        return "/recipe/create";
    }

    @PostMapping(value = "/recipe/save")
    public String save(Recipe recipe, Model model) {
        System.out.println(recipe.toString());
        recipe.setAuthor(userSDJpaService.findById(1L));
        recipe.setCreationDate(new Date());
        System.out.println(recipe.getIngredients().size());
        recipeSDJpaService.save(recipe);
        model.addAttribute("recipe", recipe);
        return "/recipe/recipeConfirm";
    }

    // FAVOURITE RECIPE
    @GetMapping("/recipe/recipe/{id}/favourite")
    public String favouriteRecipe(@PathVariable("id") long id, Model model)
    {
        Recipe recipe = recipeSDJpaService.findById(id);

        System.out.println("Recipe: " + recipe.getRecipeName());
        User user  = userSDJpaService.findById(1L);
        user.addToFavourite(recipe);
        userSDJpaService.save(user);
        model.addAttribute("recipe", recipe);
        return "redirect:/recipe/index";
    }

    @GetMapping("/recipe/recipe/{id}/unfavourite")
    public String UnfavouriteRecipe(@PathVariable("id") long id, Model model)
    {
        Recipe recipe = recipeSDJpaService.findById(id);
        User user  = userSDJpaService.findById(1L);
        user.getFavouriteRecipes().remove(recipe);
        userSDJpaService.save(user);
        model.addAttribute("recipe", recipe);
        return "redirect:/recipe/index";
    }








}
