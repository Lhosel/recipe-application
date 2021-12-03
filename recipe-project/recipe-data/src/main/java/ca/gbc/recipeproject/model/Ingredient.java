package ca.gbc.recipeproject.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "INGREDIENTS")
public class Ingredient extends BaseEntity {

    // assignment 2 addition
    @Column(name = "INGREDIENT_NAME")
    private String ingredientName;

    @Column(name = "AMOUNT")
    private double amount;

    @ManyToMany(mappedBy = "ingredients")
    private Set<Recipe> recipe;

    @ManyToMany(mappedBy = "shoppingList")
    private Set<User> user = new HashSet<>();

    public Ingredient(String ingredientName, double amount) {
        this.ingredientName = ingredientName;
        this.amount = amount;
    }

    public Ingredient() {

    }

    public String show() {
        return    amount  + " x " + ingredientName;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Set<Recipe> getRecipe() {
        return recipe;
    }

    public void setRecipe(Set<Recipe> recipe) {
        this.recipe = recipe;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }
}