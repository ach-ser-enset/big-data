package com.example.clickproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClickController {
    @Autowired
    private ClickKafkaProducer clickKafkaProducer;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/click")
    public String click(@RequestParam String userId, Model model) {
        clickKafkaProducer.sendClick(userId);
        model.addAttribute("message", "Clic envoyé pour l'utilisateur " + userId);
        return "index";
    }
}
