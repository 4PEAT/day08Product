package sdacademy.day08product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting(Model model) {
        model.addAttribute("name", "Alex");
        model.addAttribute("items", List.of("Coffee", "Tea", "Water", "Juice"));
        model.addAttribute("isLogged", false);
        model.addAttribute("role", "Editor");
        return "greeting";
    }
}
