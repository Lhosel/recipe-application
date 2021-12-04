package ca.gbc.recipeproject.bootstrap;

import ca.gbc.recipeproject.model.*;
import ca.gbc.recipeproject.services.springdatajpa.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserSDJpaService userSDJpaService;
    private final RecipeSDJpaService recipeSDJpaService;
    private final MealSDJpaService mealSDJpaService;
    private final IngredientSDJpaService ingredientSDJpaService;
    private final EventsSDJpaService eventsSDJpaService;
    private final RoleSDJpaService roleSDJpaService;

    public DataLoader(UserSDJpaService userSDJpaService, RecipeSDJpaService recipeSDJpaService, MealSDJpaService mealSDJpaService, IngredientSDJpaService ingredientSDJpaService, EventsSDJpaService eventsSDJpaService, RoleSDJpaService roleSDJpaService) {

        this.userSDJpaService = userSDJpaService;
        this.recipeSDJpaService = recipeSDJpaService;
        this.mealSDJpaService = mealSDJpaService;
        this.ingredientSDJpaService = ingredientSDJpaService;
        this.eventsSDJpaService = eventsSDJpaService;
        this.roleSDJpaService = roleSDJpaService;

    }

    @Override
    public void run(String... args) throws Exception {

        Role role = new Role();
        role.setName("admin");

        User user1 = new User();
        user1.setUsername("Kunga");
        user1.setPassword("$2a$10$ks4XAfX96H3QcHhEjql0KO9.GHjWy1UeK50s/jQsK0XME4YuFs3WW");
        user1.setEmail("kunga@gbc.ca");
        user1.setStatus(true);
        userSDJpaService.save(user1);
        roleSDJpaService.save(role);
        user1.addRole(role);

        User user2 = new User();
        user2.setUsername("Dominic");
        user2.setPassword("$2a$10$ks4XAfX96H3QcHhEjql0KO9.GHjWy1UeK50s/jQsK0XME4YuFs3WW");
        user2.setEmail("dom@gbc.ca");
        user2.setStatus(true);
        userSDJpaService.save(user2);
        user2.addRole(role);

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
        recipeSDJpaService.save(recipe2);
        recipe1.addIngredient(pepper);
        recipeSDJpaService.save(recipe1);

        // adding ingredients to shopping lists:
        user1.addToList(salt);
        user1.addToList(pepper);
        user2.addToList(pepper);


        // adding meals
        Meal meal1 = new Meal();
        meal1.setMealName("Breakfast");
        meal1.setRecipe(recipe2);
        meal1.setUser(user1);
        meal1.setDate(new Date());

        Meal meal2 = new Meal();
        meal2.setMealName("Lunch");
        meal2.setRecipe(recipe2);
        meal2.setUser(user1);
        meal2.setDate(new Date());

        Meal meal3 = new Meal();
        meal3.setMealName("Lunch");
        meal3.setRecipe(recipe2);
        meal3.setUser(user2);
        meal3.setDate(new Date());

        // adding events
        Events event1 = new Events();
        event1.setEventName("Potluck ONE");
        event1.setUser(user1);

        Events event2 = new Events();
        event2.setEventName("Potluck TWO");
        event2.setUser(user1);

        eventsSDJpaService.save(event1);
        eventsSDJpaService.save(event2);

        mealSDJpaService.save(meal1);
        mealSDJpaService.save(meal2);
        mealSDJpaService.save(meal3);

        userSDJpaService.save(user1);
        userSDJpaService.save(user2);


        System.out.println("Saving to database!");
        System.out.println("Loaded Recipes...");

    }

}
