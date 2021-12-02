package ca.gbc.recipeproject.bootstrap;

import ca.gbc.recipeproject.model.Ingredient;
import ca.gbc.recipeproject.model.Recipe;
import ca.gbc.recipeproject.model.User;
import ca.gbc.recipeproject.services.springdatajpa.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserSDJpaService userSDJpaService;
    private final RecipeSDJpaService recipeSDJpaService;
    private final MealSDJpaService mealSDJpaService;
    private final IngredientSDJpaService ingredientSDJpaService;
    private final EventsSDJpaService eventsSDJpaService;

    public DataLoader(UserSDJpaService userSDJpaService, RecipeSDJpaService recipeSDJpaService, MealSDJpaService mealSDJpaService, IngredientSDJpaService ingredientSDJpaService, EventsSDJpaService eventsSDJpaService) {

        this.userSDJpaService = userSDJpaService;
        this.recipeSDJpaService = recipeSDJpaService;
        this.mealSDJpaService = mealSDJpaService;
        this.ingredientSDJpaService = ingredientSDJpaService;
        this.eventsSDJpaService = eventsSDJpaService;

    }

    @Override
    public void run(String... args) throws Exception {

        User user1 = new User();
        user1.setUsername("Kunga");
        user1.setPassword("123");
        user1.setEmail("kunga@gbc.ca");
        userSDJpaService.save(user1);

        User user2 = new User();
        user1.setUsername("Dominic");
        user1.setPassword("123");
        user1.setEmail("dom@gbc.ca");
        userSDJpaService.save(user2);

        Ingredient salt = new Ingredient("salt", 20);

        Ingredient pepper = new Ingredient("pepper", 10);

        Recipe recipe1 = new Recipe();
        recipe1.setRecipeName("Steamed Pork Buns");
        recipe1.setDescription("This is a chinese steamed pork bun.");
        recipe1.setServings(2);
        recipe1.setDirections("Do this then that...");
        recipe1.setPrepTime(30);
        recipe1.setCookTime(60);
        recipe1.setAuthor(user1);
        recipeSDJpaService.save(recipe1);

        ingredientSDJpaService.save(salt);
        recipe1.addIngredient(salt);
        salt.setRecipe(recipe1);
        recipeSDJpaService.save(recipe1);

        Recipe recipe2 = new Recipe();
        recipe2.setRecipeName("Banh Mi");
        recipe2.setDescription("This is sandwich.");
        recipe2.setServings(1);
        recipe2.setDirections("Do this after that...");
        recipe2.setPrepTime(20);
        recipe2.setCookTime(40);
        recipe2.setAuthor(user2);
        recipeSDJpaService.save(recipe2);

        ingredientSDJpaService.save(pepper);
        recipe2.addIngredient(pepper);
        pepper.setRecipe(recipe2);
        recipeSDJpaService.save(recipe2);

        System.out.println("Saving to database!");
        System.out.println("Loaded Recipes...");

    }
}
