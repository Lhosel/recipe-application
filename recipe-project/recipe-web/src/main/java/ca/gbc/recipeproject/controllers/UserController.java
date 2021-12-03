package ca.gbc.recipeproject.controllers;

import ca.gbc.recipeproject.model.Events;
import ca.gbc.recipeproject.model.Ingredient;
import ca.gbc.recipeproject.model.Recipe;
import ca.gbc.recipeproject.model.User;
import ca.gbc.recipeproject.services.springdatajpa.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
public class UserController {

    private final RecipeSDJpaService recipeSDJpaService;
    private final UserSDJpaService userSDJpaService;
    private final IngredientSDJpaService ingredientSDJpaService;
    private final MealSDJpaService mealSDJpaService;
    private final EventsSDJpaService eventsSDJpaService;


    public UserController(RecipeSDJpaService recipeSDJpaService, UserSDJpaService userSDJpaService,
                          IngredientSDJpaService ingredientSDJpaService, MealSDJpaService mealSDJpaService,
                          EventsSDJpaService eventsSDJpaService) {

        this.recipeSDJpaService = recipeSDJpaService;
        this.userSDJpaService = userSDJpaService;
        this.ingredientSDJpaService = ingredientSDJpaService;
        this.mealSDJpaService = mealSDJpaService;
        this.eventsSDJpaService = eventsSDJpaService;

    }
    // List shoppling cart ingredients

    @RequestMapping("/profile/list")
    public String showCart(Model model) {
        User user = userSDJpaService.findById(1L);
        model.addAttribute("ingredients", user.getShoppingList());
        return "/profile/list-cart";
    }

    // List All Events

    @RequestMapping("/profile/events")
    public String showEvents(Model model) {
        User user = userSDJpaService.findById(1L);
        model.addAttribute("events", user.getEventList());
        return "/profile/list-events";
    }



    //CREATE/SAVE Event

    @RequestMapping({"/profile/event/create", "/profile/event/create.html"})
    public String create(Model model) {
        Events event = new Events();
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("event", event);
        return "/profile/create-event";
    }

    @PostMapping(value = "/profile/event/save")
    public String save(Events event, User user, Model model) {
        event.setEventDate(new Date());
        event.setUser(userSDJpaService.findById(1L));
        user.getEventList().add(event);
        userSDJpaService.findById(1L).setEventList(user.getEventList());
        userSDJpaService.save(userSDJpaService.findById(1L));
        model.addAttribute("event", event);
        return "/profile/profileConfirm";
    }


    // UPDATE Event
    @GetMapping("/profile/event/edit/{id}")
    public String showUpdateEventForm(@PathVariable("id") long id, Model model)
    {
        Events event = eventsSDJpaService.findById(id);

        model.addAttribute("event", event);
        return "/profile/update-event";
    }

    // DELETE Event

    @GetMapping("/profile/event/delete/{id}")
    public String deleteEvent(@PathVariable("id") long id, Model model) {
        Events event = eventsSDJpaService.findById(id);

        eventsSDJpaService.delete(event);
        return "redirect:/profile/events";
    }


    @PostMapping("/profile/event/edit/{id}")
    public String updateEvent(@PathVariable("id") long id, Events event, Model model) {
        event.setUser(userSDJpaService.findById(1L));
        eventsSDJpaService.save(event);
        return "redirect:/profile/events";
    }


    //Delete Event




}