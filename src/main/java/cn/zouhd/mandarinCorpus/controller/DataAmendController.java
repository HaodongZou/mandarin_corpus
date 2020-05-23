package cn.zouhd.mandarinCorpus.controller;

import cn.zouhd.mandarinCorpus.entities.Hanwai;
import cn.zouhd.mandarinCorpus.entities.Subcategory;
import cn.zouhd.mandarinCorpus.entities.Yunshu;
import cn.zouhd.mandarinCorpus.repositories.HanwaiRepo;
import cn.zouhd.mandarinCorpus.repositories.TemplateRepo;
import cn.zouhd.mandarinCorpus.repositories.YunshuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/dataAmend")
public class DataAmendController {

    @Autowired
    TemplateRepo templateRepo;

    @Autowired
    HanwaiRepo hanwaiRepo;

    @Autowired
    YunshuRepo yunshuRepo;

    @GetMapping
    public String dataAmend(Model model){
        model.addAttribute("activeUrl", "dataAmend");
        return "dataOperation/dataAmend";
    }


    @GetMapping("/search")
    @ResponseBody
    public List<Yunshu> findWord(@RequestParam String word){
        String yunshuWord = "%" + word + "%";
        return yunshuRepo.findByWordLike(yunshuWord);
    }


}
