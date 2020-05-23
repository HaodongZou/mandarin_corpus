package cn.zouhd.mandarinCorpus.controller;

import cn.zouhd.mandarinCorpus.entities.Subcategory;
import cn.zouhd.mandarinCorpus.entities.Template;
import cn.zouhd.mandarinCorpus.entities.Yunshu;
import cn.zouhd.mandarinCorpus.repositories.SubcategoryRepo;
import cn.zouhd.mandarinCorpus.repositories.TemplateRepo;
import cn.zouhd.mandarinCorpus.repositories.YunshuRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author zouhd
 */
@Controller
@RequestMapping("/yunshu")
public class YunshuController {
    
    @Autowired
    SubcategoryRepo subcategoryRepo;
    
    @Autowired
    TemplateRepo templateRepo;
    
    @Autowired
    YunshuRepo yunshuRepo;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/{abbr}")
    public String searchPage(@PathVariable String abbr,
                             @RequestParam @Nullable Integer pageNum,
                             @RequestParam @Nullable String word,
                             @RequestParam @Nullable Integer id,
                             @RequestParam @Nullable Boolean sheetSearch,
                             Model model,
                             HttpServletResponse response,
                             HttpServletRequest request){
        if ((StringUtils.isEmpty(word) && pageNum == null && id == null && sheetSearch == null)){
            response.setStatus(400);
            return "error/4xx";
        }
        model.addAttribute("activeUrl", "yunshu");

        // 确定路径对应的目录以及父目录
        List<Subcategory> byNameLike = subcategoryRepo.findByAbbr(abbr);
        if (byNameLike.size() != 1) {
            response.setStatus(400);
            return "error/4xx";
        }
        String subcategory = byNameLike.get(0).getName();
        String category = byNameLike.get(0).getCategory();

        //从检索结果页面返回查找记录在表中的位置
        String sheetFlag = (String) request.getAttribute("sheetFlag");
        if ( !"no".equals(sheetFlag)) {
            if (sheetSearch != null && sheetSearch) {
                if (id == null) {
                    response.setStatus(400);
                    return "error/4xx";
                }
                // 需要查找的记录
                Yunshu yunshu = yunshuRepo.findById(id).orElse(null);
                if (yunshu == null) {
                    response.setStatus(404);
                    return "error/4xx";
                }
                switch (subcategory) {
                    case "国音常用字汇":
                        pageNum = (yunshuRepo.findGycyzhPage(id) - 1) / 15 + 1;
                        break;
                    case "京音字汇":
                        pageNum = (yunshuRepo.findJyzhPage(id) - 1) / 15 + 1;
                        break;
                    default:
                        response.setStatus(404);
                        return "error/4xx";

                }

                request.setAttribute("sheetFlag", "no");

                return "forward:/yunshu/" + abbr + "?pageNum=" + pageNum;
            }
        }



        //如果参数word为空，pageNum非空
        if (StringUtils.isEmpty(word) && pageNum != null){

            model.addAttribute("subcategory", subcategory)
                    .addAttribute("category", category);

            List<Yunshu> notnull;
            Pageable pageable = PageRequest.of(pageNum - 1, 15);

            switch (subcategory){
                case "国音常用字汇" :
                    notnull = yunshuRepo.findByGycyzhShengNotNullOrGycyzhYunNotNullOrGycyzhYinNotNull(pageable);
                    break;
                case "京音字汇" :
                    notnull = yunshuRepo.findByJyzhDiaoNotNullOrJyzhShengNotNullOrJyzhYunNotNull(pageable);
                    break;
                default:
                    response.setStatus(404);
                    return "error/4xx";

            }

            model.addAttribute("results", notnull)
                    .addAttribute("pageNum", pageNum);


            return "common/search";
        }

        //当word不为空（无论pageNum是否为空）
        Yunshu yunshu = new Yunshu();
        yunshu.setWord(word);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("word",
                        ExampleMatcher.GenericPropertyMatcher
                                .of(ExampleMatcher.StringMatcher.CONTAINING));
        Example<Yunshu> example = Example.of(yunshu, matcher);
        List<Yunshu> results = yunshuRepo.findAll(example);
        List<Template> templates = new ArrayList<>();
        Iterator<Yunshu> iterator = results.iterator();
        switch (subcategory){
            case "国音常用字汇" :
                while (iterator.hasNext()) {
                    Yunshu result = iterator.next();
                    if (result.getGycyzhSheng() == null && result.getGycyzhYin() == null && result.getGycyzhYun() == null){
                        iterator.remove();
                    }else{
                        Template template = templateRepo.findById(result.getId()).orElse(null);
                        templates.add(template);
                    }
                }
                break;
            case "京音字汇" :
                while (iterator.hasNext()) {
                    Yunshu result = iterator.next();
                    if (result.getJyzhDiao() == null && result.getJyzhSheng() == null && result.getGycyzhYin() == null){
                        iterator.remove();
                    }else{
                        Template template = templateRepo.findById(result.getId()).orElse(null);
                        templates.add(template);
                    }
                }
                break;
            default:
                response.setStatus(404);
                return "error/4xx";

        }

        model.addAttribute("results", results)
                .addAttribute("templates",templates)
                .addAttribute("subcategory", subcategory)
                .addAttribute("category", category);
        return "common/results";



    }
}
