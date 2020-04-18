package cn.zouhd.mandarin_corpus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HanwaiController {
    @GetMapping("/hanwai")
    public String hanwaiPage(Model model){
        model.addAttribute("activeUrl", "hanwaiPage");
        return "hanwai/hanwai";
    }
}
