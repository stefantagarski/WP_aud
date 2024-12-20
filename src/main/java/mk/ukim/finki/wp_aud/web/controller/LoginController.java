package mk.ukim.finki.wp_aud.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp_aud.model.exceptions.InvalidCredentialsException;
import mk.ukim.finki.wp_aud.model.User;
import mk.ukim.finki.wp_aud.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final AuthService service;

    public LoginController(AuthService service) {
        this.service = service;
    }

    @GetMapping
    public String loginPage() {
        return "login";
    }

    @PostMapping
    public String login(HttpServletRequest request, Model model) {
        User user;
        try {
            user = service.login(request.getParameter("username"), request.getParameter("password"));
            request.getSession().setAttribute("user", user);
            return "redirect:/home";
        } catch (InvalidCredentialsException e) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }
}
