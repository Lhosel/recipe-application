package ca.gbc.recipeproject.controllers;

import ca.gbc.recipeproject.model.*;
import ca.gbc.recipeproject.services.ExcelFileService;
import ca.gbc.recipeproject.services.springdatajpa.*;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Set;

@Controller
public class UserController {

    private final UserSDJpaService userSDJpaService;
    private final EventsSDJpaService eventsSDJpaService;
    private final IngredientSDJpaService ingredientSDJpaService;
    private final ExcelFileService excelFileService;


    public UserController(UserSDJpaService userSDJpaService, EventsSDJpaService eventsSDJpaService, IngredientSDJpaService ingredientSDJpaService
    , ExcelFileService excelFileService) {

        this.userSDJpaService = userSDJpaService;
        this.eventsSDJpaService = eventsSDJpaService;
        this.ingredientSDJpaService = ingredientSDJpaService;
        this.excelFileService = excelFileService;

    }

    @RequestMapping({"/profile", "/profile/index"})
    public String showIndex(Model model) {
        User user = userSDJpaService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", user);
        return "/profile/index";
    }



    // List Favourite Recipes
    @RequestMapping("/profile/recipes")
    public String showFavourites(Model model) {

        User user = userSDJpaService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("recipes", user.getFavouriteRecipes());

        return "/profile/favourite-recipes";
    }

    // List shopping cart ingredients
    @RequestMapping("/profile/list")
    public String showCart(Model model) {

        User user = userSDJpaService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("ingredients", user.getShoppingList());
        model.addAttribute("ingredientList", ingredientSDJpaService.findAll());

        return "/profile/list-cart";
    }

    @GetMapping("/profile/list/download")
    public void downloadExcelFile(HttpServletResponse response) throws IOException {
        Set<Ingredient> shoppingList = userSDJpaService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getShoppingList();
        ByteArrayInputStream byteArrayInputStream = excelFileService.export(shoppingList);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=shoppingList.xlsx");
        IOUtils.copy(byteArrayInputStream, response.getOutputStream());
    }

    // Delete ingredient from cart
    @GetMapping("/profile/list/{id}/remove")
    public String deleteIngredient(@PathVariable("id") long id, Model model) {
        Ingredient ingredient = ingredientSDJpaService.findById(id);
        User user = userSDJpaService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        user.getShoppingList().remove(ingredient);
        userSDJpaService.save(user);
        return "redirect:/profile/list";
    }




    // List All Events

    @RequestMapping("/profile/events")
    public String showEvents(Model model) {

        User user = userSDJpaService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
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
        event.setUser(userSDJpaService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        userSDJpaService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getEventList().add(event);
        userSDJpaService.save(userSDJpaService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
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

        event.setUser(userSDJpaService.getUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        eventsSDJpaService.save(event);
        return "redirect:/profile/events";

    }

}