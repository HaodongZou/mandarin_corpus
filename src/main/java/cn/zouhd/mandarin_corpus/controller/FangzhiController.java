package cn.zouhd.mandarin_corpus.controller;

import cn.zouhd.mandarin_corpus.entities.Fangzhi;
import cn.zouhd.mandarin_corpus.repositories.FangzhiRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/fangzhi")
public class FangzhiController {

    @Autowired
    FangzhiRepo fangzhiRepo;

    @GetMapping
    public String fangzhi(Model model){
        List<Fangzhi> all = fangzhiRepo.findAll();
        model.addAttribute("results", all)
            .addAttribute("activeUrl", "fangzhi");
        return "fangzhi/search";
    }

    @GetMapping("/search")
    @ResponseBody
    public List<Fangzhi> fangzhiSearch(@RequestParam String fangzhi){
        String name = "%" + fangzhi + "%";


        return fangzhiRepo.findByNameLike(name);

    }
}
