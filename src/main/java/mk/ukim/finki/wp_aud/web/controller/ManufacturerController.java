package mk.ukim.finki.wp_aud.web.controller;

import mk.ukim.finki.wp_aud.service.ManufacturerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/manufacturers")
public class ManufacturerController {

    private final ManufacturerService service;

    public ManufacturerController(ManufacturerService service) {
        this.service = service;
    }

    @GetMapping
    public String getManufacturers(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("manufacturers", service.findAll());
        model.addAttribute("bodyContent", "manufacturers");
        return "master-template";
    }
}
