package cn.zouhd.mandarinCorpus.controller;

import cn.zouhd.mandarinCorpus.entities.Hanwai;
import cn.zouhd.mandarinCorpus.entities.Template;
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

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/integrated")
public class IntegratedController {

    @Autowired
    TemplateRepo templateRepo;

    @Autowired
    HanwaiRepo hanwaiRepo;

    @Autowired
    YunshuRepo yunshuRepo;

    @GetMapping("/search")
    public String searchPage(@RequestParam String word, Model model){
        List<Template> list = templateRepo.findByWordLike("%" + word + "%");
        model.addAttribute("templates",list)
                .addAttribute("results", list)
                .addAttribute("subcategory", "综合搜索");


        return "common/results";
    }

    @GetMapping("/word")
    public String wordDetail(@RequestParam Integer id, Model model, HttpServletResponse response){
        Template template = templateRepo.findById(id).orElse(null);
        // 如果找不到该ID，返回404
        if (template == null){
            response.setStatus(404);
            return "error/4xx";
        }

        Yunshu yunshu = yunshuRepo.findById(id).orElse(null);
        Hanwai hanwai = hanwaiRepo.findById(id).orElse(null);

        model.addAttribute("template", template)
                .addAttribute("yunshu", yunshu)
                .addAttribute("hanwai", hanwai);

        return "common/word";
    }
}
