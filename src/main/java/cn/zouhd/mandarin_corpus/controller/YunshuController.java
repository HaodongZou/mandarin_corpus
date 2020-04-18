package cn.zouhd.mandarin_corpus.controller;

import cn.zouhd.mandarin_corpus.repositories.YunshuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class YunshuController {

    @Autowired
    YunshuRepo yunshuRepo;

    @GetMapping("/yunshu")
    public String yunshuPage(Model model){
        model.addAttribute("activeUrl", "yunshuPage");
        return "yunshu/yunshu";

    }

    @GetMapping("/yunshu/results")
    public String results(Model model){
        Object yunshuSearch = model.getAttribute("yunshuSearch");
        if (StringUtils.isEmpty(yunshuSearch)){
            model.addAttribute("msg", "请输入要查询的文字");
            return "yunshu/yunshu";
        }

        return "yunshu/results";
    }
}
