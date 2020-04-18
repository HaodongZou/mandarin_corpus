package cn.zouhd.mandarin_corpus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ZhongjinguController {

    @GetMapping("/zhongjingu")
    public String zhongjinguPage(Model model){
        model.addAttribute("activeUrl", "zhongjinguPage");
        return "zhongjingu/zhongjingu";
    }
}
