package cn.zouhd.mandarinCorpus.controller;

import cn.zouhd.mandarinCorpus.entities.Hanwai;
import cn.zouhd.mandarinCorpus.entities.Subcategory;
import cn.zouhd.mandarinCorpus.repositories.HanwaiRepo;
import cn.zouhd.mandarinCorpus.repositories.SubcategoryRepo;
import cn.zouhd.mandarinCorpus.repositories.TemplateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/hanwai")
public class HanwaiController {

    @Autowired
    SubcategoryRepo subcategoryRepo;

    @Autowired
    TemplateRepo templateRepo;

    @Autowired
    HanwaiRepo hanwaiRepo;

    @GetMapping("/{abbr}")
    public String searchPage(@PathVariable String abbr, Model model,@RequestParam Integer pageNum){
        List<Subcategory> byNameLike = subcategoryRepo.findByAbbr(abbr);
        if (byNameLike.size() == 1){
            model.addAttribute("subcategory", byNameLike.get(0).getName())
                    .addAttribute("category", byNameLike.get(0).getCategory());
        }
        else{
            return "404";
        }

        List<Hanwai> notnull;
        Pageable pageable = PageRequest.of(pageNum - 1, 15);

        switch (byNameLike.get(0).getName()){
            case "语言自迩集" :
                notnull = hanwaiRepo.findByZejShengNotNullOrZejYinNotNullOrZejYunNotNull(pageable);
                break;
            case "寻津录" :
                notnull = hanwaiRepo.findByXjlShengNotNullOrXjlYinNotNullOrXjlYunNotNull(pageable);
                break;
            default: return "404";
        }

        model.addAttribute("results", notnull)
                .addAttribute("pageNum", pageNum);


        return "common/search";

    }
}
