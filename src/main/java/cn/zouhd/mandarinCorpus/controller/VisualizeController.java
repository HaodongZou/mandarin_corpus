package cn.zouhd.mandarinCorpus.controller;

import cn.zouhd.mandarinCorpus.repositories.HanwaiRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/visualize")
public class VisualizeController {
    @Autowired
    HanwaiRepo hanwaiRepo;

    @GetMapping
    public String visualize(Model model){
        Integer zej = hanwaiRepo.countByZejShengNotNullOrZejYinNotNullOrZejYunNotNull();
        Integer xjl = hanwaiRepo.countByXjlShengNotNullOrXjlYinNotNullOrXjlYunNotNull();
        Integer hywyjd = hanwaiRepo.countByHywyjdShengNotNullOrHywyjdYinNotNullOrHywyjdYunNotNull();
        Integer thzy = hanwaiRepo.countByThzyBiaoyinNotNullOrThzyNiyinNotNull();
        Integer sstj = hanwaiRepo.countBySstjJinsuyinNotNullOrSstjShengdiaoNotNullOrSstjBeizhuNotNull();
        Integer hyqmyj = hanwaiRepo.countByHyqmyjShengNotNullOrHyqmyjYunNotNull();
        model.addAttribute("zejNum", zej)
                .addAttribute("xjlNum", xjl)
                .addAttribute("hywyjdNum", hywyjd)
                .addAttribute("thzyNum",thzy)
                .addAttribute("sstjNum", sstj)
                .addAttribute("hyqmyjNum", hyqmyj);
        return "dataOperation/visualize";
    }

}
