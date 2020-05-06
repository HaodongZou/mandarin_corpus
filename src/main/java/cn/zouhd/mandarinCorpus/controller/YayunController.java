package cn.zouhd.mandarinCorpus.controller;

import cn.zouhd.mandarinCorpus.entities.Subcategory;
import cn.zouhd.mandarinCorpus.repositories.SubcategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/yayun")
public class YayunController {

    @Autowired
    SubcategoryRepo subcategoryRepo;

    @GetMapping("/{category}")
    public String baojuan(Model model, @PathVariable String category){
        String likeCategory;
        switch (category){
            case "baojuan" : likeCategory = "%宝卷%";break;
            case "qinqiang" : likeCategory = "%秦腔%";break;
            case "quzici" : likeCategory = "%曲子词%";break;
            case "zaju" : likeCategory = "%杂剧%";break;
            default: likeCategory = "错误";
        }
        List<Subcategory> all = subcategoryRepo.findByCategoryLike(likeCategory);
        model.addAttribute("results", all);

        return "common/subcategorySearch";
    }

    @GetMapping("/{category}/search")
    @ResponseBody
    public List<Subcategory> search(@RequestParam String name, Model model,@PathVariable String category){
        String likeCategory;
        switch (category){
            case "baojuan" : likeCategory = "%宝卷%";break;
            case "qinqiang" : likeCategory = "%秦腔%";break;
            case "quzici" : likeCategory = "%曲子词%";break;
            case "zaju" : likeCategory = "%杂剧%";break;
            default: likeCategory = "错误";
        }
        String likeName = "%" + name + "%";
        return subcategoryRepo.findByCategoryLikeAndNameLike(likeCategory, likeName);
    }

}
