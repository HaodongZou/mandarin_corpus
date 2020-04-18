package cn.zouhd.mandarin_corpus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/main")
    public String mainPage(Model model){
        model.addAttribute("activeUrl", "mainPage");
        return "dashboard";
    }
}
