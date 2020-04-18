package cn.zouhd.mandarin_corpus.controller;

import cn.zouhd.mandarin_corpus.entities.Yunshu;
import cn.zouhd.mandarin_corpus.repositories.YunshuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

/**
 * @author zouhd
 */
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
    public String results(@RequestParam String yunshuSearch, Model model){
        StringUtils.trimAllWhitespace(yunshuSearch);
        if (StringUtils.isEmpty(yunshuSearch)){
            model.addAttribute("msg", "请输入要查询的文字或ID");
            return "yunshu/yunshu";
        }

//        判断搜索内容是否为数字ID
        if (yunshuSearch.matches("[0-9]+")){
            Yunshu result = yunshuRepo.findById(Integer.valueOf(yunshuSearch)).orElse(null);
            if (result == null){
                model.addAttribute("msg", "查询不到结果");
                return "yunshu/yunshu";
            }
            model.addAttribute("results", result);
            return "yunshu/results";
        }
//        不是数字ID则搜索字符
        String searchLike = "%" + yunshuSearch + "%";
        List<Yunshu> results = yunshuRepo.findByCharacterLike((String) searchLike);
        if (results == null){
            model.addAttribute("msg", "查询不到结果");
            return "yunshu/yunshu";
        }
        model.addAttribute("results", results);
        return "yunshu/results";
    }
}
