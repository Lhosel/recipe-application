package ca.gbc.recipeproject.model;

import javax.persistence.*;

@Entity
@Table(name = "INGREDIENTS")
public class Ingredient extends BaseEntity {

    // assignment 2 addition
    @Column(name = "INGREDIENT_NAME")
    private String ingredientName;

    @Column(name = "AMOUNT")
    private double amount;

    @ManyToOne
    @JoinColumn(name = "RECIPE_ID")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public Ingredient(String ingredientName, double amount) {
        this.ingredientName = ingredientName;
        this.amount = amount;
    }

    public Ingredient() {

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

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

}