package ca.gbc.recipeproject.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
public class User extends BaseEntity {

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL")
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private Set<Recipe> createdRecipes = new HashSet<>();

    @Column(name = "STATUS")
    private boolean status;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Meal> meals = new HashSet<>();

    // assignment 2 requirements
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_FAVOURITE", joinColumns = @JoinColumn(name = "USER_ID"),
    inverseJoinColumns = @JoinColumn(name = "RECIPE_ID"))
    private Set<Recipe> favouriteRecipes = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Ingredient> shoppingList = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
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
