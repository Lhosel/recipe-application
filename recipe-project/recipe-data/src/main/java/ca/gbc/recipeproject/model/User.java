package ca.gbc.recipeproject.model;

import java.util.HashSet;
import java.util.Set;

public class User extends BaseEntity {

    private String username;
    private String password;
    private String email;
    private Set<Recipe> createdRecipes;
    private boolean status;
    private Set<Meal> meals;

    // assignment 2 requirements
    private Set<Recipe> favouriteRecipes = new HashSet<>();
    private Set<Ingredient> shoppingList = new HashSet<>();
    private Set<Events> eventList = new HashSet<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Recipe> getCreatedRecipes() {
        return createdRecipes;
    }

    public void setCreatedRecipes(Set<Recipe> createdRecipes) {
        this.createdRecipes = createdRecipes;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Set<Meal> getMeals() {
        return meals;
    }

    public void setMeals(Set<Meal> meals) {
        this.meals = meals;
    }

    public Set<Recipe> getFavouriteRecipes() {
        return favouriteRecipes;
    }

    public void setFavouriteRecipes(Set<Recipe> favouriteRecipes) {
        this.favouriteRecipes = favouriteRecipes;
    }

    public Set<Ingredient> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(Set<Ingredient> shoppingList) {
        this.shoppingList = shoppingList;
    }

    public Set<Events> getEventList() {
        return eventList;
    }

    public void setEventList(Set<Events> eventList) {
        this.eventList = eventList;
    }

}
