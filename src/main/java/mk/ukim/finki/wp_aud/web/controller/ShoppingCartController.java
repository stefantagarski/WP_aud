package mk.ukim.finki.wp_aud.web.controller;

import jakarta.servlet.http.HttpSession;
import mk.ukim.finki.wp_aud.model.ShoppingCart;
import mk.ukim.finki.wp_aud.model.User;
import mk.ukim.finki.wp_aud.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public String getCartPage(@RequestParam(required = false) String error, HttpSession session, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        User user = (User) session.getAttribute("user");

        ShoppingCart cart = shoppingCartService.getActiveShoppingCart(user.getUsername());

        model.addAttribute("products", shoppingCartService.listAllProductsInShoppingCart(cart.getId()));

        model.addAttribute("bodyContent", "shopping-cart");

        return "master-template";

    }

    @PostMapping("/add-product/{id}")
    public String addProductToCart(@PathVariable Long id, HttpSession session) {
        try {
            User user = (User) session.getAttribute("user");
            this.shoppingCartService.addProductShoppingCart(user.getUsername(), id);
            return "redirect:/shopping-cart";
        } catch (RuntimeException exception) {
            return "redirect:/shopping-cart?error=" + exception.getMessage();
        }
    }
}
