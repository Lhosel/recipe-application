package ca.gbc.recipeproject.controllers;

import ca.gbc.recipeproject.model.User;
import ca.gbc.recipeproject.services.springdatajpa.UserSDJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    UserSDJpaService userSDJpaService;
    private final PasswordEncoder passwordEncoder;

    public IndexController(UserSDJpaService userSDJpaService, PasswordEncoder passwordEncoder) {

        this.userSDJpaService = userSDJpaService;
        this.passwordEncoder = passwordEncoder;

    }

    @RequestMapping({"", "/", "index.html"})
    public String success() {
        return "redirect:/recipe";
    }

    @RequestMapping(value = {"/register", "register.html"}, method = RequestMethod.GET)
    public String register(Model model) {

        User user = new User();
        model.addAttribute("user", user);

        return "/register";

    }

    @PostMapping(value = "/saved")
    public String saved(User user, Model model) {

        if(userSDJpaService.getUserByUsername(user.getUsername()) != null) {

            model.addAttribute("message", "Username is Not Unique");
            return "/register";

        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(true);
        userSDJpaService.save(user);

        return "/login";

    }

    @RequestMapping(value = {"", "/", "/login", "/login.html"}, method = RequestMethod.GET)
    public String loginPage() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "/login";
        }

        return "redirect:/recipe";

    }

    @RequestMapping({"/logout", "logout.html"})
    public String logout() {
        return "/logout";
    }

}
