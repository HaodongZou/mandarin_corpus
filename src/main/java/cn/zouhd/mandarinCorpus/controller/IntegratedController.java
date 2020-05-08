package cn.zouhd.mandarinCorpus.controller;

import cn.zouhd.mandarinCorpus.entities.Template;
import cn.zouhd.mandarinCorpus.repositories.TemplateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/integrated")
public class IntegratedController {

    @Autowired
    TemplateRepo templateRepo;

    @GetMapping("search")
    public String searchPage(@RequestParam String word, Model model){
        List<Template> list = templateRepo.findByWord(word);
        model.addAttribute("templates",list)
                .addAttribute("results", list)
                .addAttribute("subcategory", "综合搜索");


        return "common/results";

    }
}
