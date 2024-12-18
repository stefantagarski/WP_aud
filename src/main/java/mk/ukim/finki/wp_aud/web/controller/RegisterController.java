package mk.ukim.finki.wp_aud.web.controller;

import mk.ukim.finki.wp_aud.model.enumerations.Role;
import mk.ukim.finki.wp_aud.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.wp_aud.model.exceptions.PasswordsDontMatchException;
import mk.ukim.finki.wp_aud.service.AuthService;
import mk.ukim.finki.wp_aud.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService service;

    public RegisterController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public String registerPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        return "register";
    }

    @PostMapping
    public String register(@RequestParam String username, @RequestParam String password,
                           @RequestParam String repeatPassword,
                           @RequestParam String name, @RequestParam String surname,
                           @RequestParam Role role) {
        try {
            service.register(username, password, repeatPassword, name, surname, role);
            return "redirect:/login";
        } catch (InvalidArgumentsException | PasswordsDontMatchException e) {
            return "redirect:/register?error=" + e.getMessage();
        }
    }
}
