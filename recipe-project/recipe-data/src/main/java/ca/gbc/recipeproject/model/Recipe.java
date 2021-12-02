package ca.gbc.recipeproject.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "RECIPES")
public class Recipe extends BaseEntity {

    @Column(name = "RECIPE_NAME")
    private String recipeName;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "SERVINGS")
    private int servings;

    @Column(name = "DIRECTIONS")
    private String directions;

    @Column(name = "PREP_TIME")
    private int prepTime;

    @Column(name = "COOK_TIME")
    private int cookTime;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User author;

    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @Column(name = "VIEW")
    private boolean view;

    private User user;

    // assignment 2 requirements
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    @ManyToMany
    private Set<User> favoriteByUsers = new HashSet<>();

    public Recipe addIngredient(Ingredient ingredient) {
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    public int getCookTime() {
        return cookTime;
    }

    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isView() {
        return view;
    }

    public void setView(boolean view) {
        this.view = view;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Set<User> getFavoriteByUsers() {
        return favoriteByUsers;
    }

    public void setFavoriteByUsers(Set<User> favoriteByUsers) {
        this.favoriteByUsers = favoriteByUsers;
    }

}
