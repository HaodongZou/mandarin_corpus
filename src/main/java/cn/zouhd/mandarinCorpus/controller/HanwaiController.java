package cn.zouhd.mandarinCorpus.controller;

import cn.zouhd.mandarinCorpus.entities.Hanwai;
import cn.zouhd.mandarinCorpus.entities.Subcategory;
import cn.zouhd.mandarinCorpus.entities.Template;
import cn.zouhd.mandarinCorpus.repositories.HanwaiRepo;
import cn.zouhd.mandarinCorpus.repositories.SubcategoryRepo;
import cn.zouhd.mandarinCorpus.repositories.TemplateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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
    public String searchPage(@PathVariable String abbr,
                             Model model,
                             @RequestParam @Nullable Integer pageNum,
                             @RequestParam @Nullable String word,
                             HttpServletResponse response){
        if ((StringUtils.isEmpty(word) && pageNum == null)){
            response.setStatus(400);
            return "error/4xx";
        }
        List<Subcategory> byNameLike = subcategoryRepo.findByAbbr(abbr);

        //如果参数word为空，pageNum非空
        if (StringUtils.isEmpty(word) && pageNum != null){
            if (byNameLike.size() != 1) {
                response.setStatus(400);
                return "error/4xx";
            }
            model.addAttribute("subcategory", byNameLike.get(0).getName())
                    .addAttribute("category", byNameLike.get(0).getCategory());

            List<Hanwai> notnull;
            Pageable pageable = PageRequest.of(pageNum - 1, 15);

            switch (byNameLike.get(0).getName()){
                case "语言自迩集" :
                    notnull = hanwaiRepo.findByZejShengNotNullOrZejYinNotNullOrZejYunNotNull(pageable);
                    break;
                case "寻津录" :
                    notnull = hanwaiRepo.findByXjlShengNotNullOrXjlYinNotNullOrXjlYunNotNull(pageable);
                    break;
                case "华英文义津逮" :
                    notnull = hanwaiRepo.findByHywyjdShengNotNullOrHywyjdYinNotNullOrHywyjdYunNotNull(pageable);
                    break;
                case "唐话纂要" :
                    notnull = hanwaiRepo.findByThzyBiaoyinNotNullOrThzyNiyinNotNull(pageable);
                    break;
                case "四声通解" :
                    notnull = hanwaiRepo.findBySstjJinsuyinNotNullOrSstjShengdiaoNotNullOrSstjZhuyinNotNull(pageable);
                    break;
                case "华英启蒙谚解" :
                    notnull = hanwaiRepo.findByHyqmyjShengNotNullOrHyqmyjYunNotNull(pageable);
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
        Hanwai hanwai = new Hanwai();
        hanwai.setWord(word);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("word",
                        ExampleMatcher.GenericPropertyMatcher
                                .of(ExampleMatcher.StringMatcher.CONTAINING));
        Example<Hanwai> example = Example.of(hanwai, matcher);
        List<Hanwai> results = hanwaiRepo.findAll(example);
        List<Template> templates = new ArrayList<>();
        for (Hanwai result : results) {
            Template template = templateRepo.findById(result.getId()).orElse(null);
            templates.add(template);
        }

        model.addAttribute("results", results)
                .addAttribute("templates",templates);
        return "common/results";



    }

}
