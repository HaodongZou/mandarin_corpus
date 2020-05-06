package cn.zouhd.mandarinCorpus.controller;

import cn.zouhd.mandarinCorpus.entities.Subcategory;
import cn.zouhd.mandarinCorpus.repositories.FangzhiRepo;
import cn.zouhd.mandarinCorpus.repositories.SubcategoryRepo;
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

    @Autowired
    SubcategoryRepo subcategoryRepo;

    @GetMapping
    public String fangzhi(Model model){
        List<Subcategory> all = subcategoryRepo.findByCategoryLike("%方志%");
        model.addAttribute("results", all)
            .addAttribute("activeUrl", "fangzhi");
        return "common/subcategorySearch";
    }

    @GetMapping("/search")
    @ResponseBody
    public List<Subcategory> fangzhiSearch(@RequestParam String name){
        String fangzhiName = "%" + name + "%";
        return subcategoryRepo.findByCategoryLikeAndNameLike("%方志%", fangzhiName);
    }
}
