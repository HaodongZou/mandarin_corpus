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
import java.util.Iterator;
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
                             @RequestParam @Nullable Integer pageNum,
                             @RequestParam @Nullable String word,
                             @RequestParam @Nullable Integer id,
                             @RequestParam @Nullable Boolean sheetSearch,
                             Model model,
                             HttpServletResponse response){
        if ((StringUtils.isEmpty(word) && pageNum == null && id == null && sheetSearch == null)){
            response.setStatus(400);
            return "error/4xx";
        }

        // 确定路径对应的目录以及父目录
        List<Subcategory> byNameLike = subcategoryRepo.findByAbbr(abbr);
        if (byNameLike.size() != 1) {
            response.setStatus(400);
            return "error/4xx";
        }
        String subcategory = byNameLike.get(0).getName();
        String category = byNameLike.get(0).getCategory();

        //从检索结果页面返回查找记录在表中的位置
        if (sheetSearch != null && sheetSearch){
            if (id == null){
                response.setStatus(400);
                return "error/4xx";
            }
            // 需要查找的记录
            Hanwai hanwai = hanwaiRepo.findById(id).orElse(null);
            if (hanwai == null){
                response.setStatus(404);
                return "error/4xx";
            }
            Integer searchPageNum = 0;
            List<Hanwai> findList;
            pageNum = 1;
            Pageable pageable = PageRequest.of(pageNum - 1, 15);
            switch (subcategory){
                case "语言自迩集" :
                    do {
                        findList = hanwaiRepo.findByZejShengNotNullOrZejYinNotNullOrZejYunNotNull(pageable);
                        pageable = PageRequest.of(++pageNum -1, 15);
                    }while (!findList.contains(hanwai));
                    break;
                case "寻津录" :
                    do {
                        findList = hanwaiRepo.findByXjlShengNotNullOrXjlYinNotNullOrXjlYunNotNull(pageable);
                        pageable = PageRequest.of(++pageNum -1, 15);
                    }while (!findList.contains(hanwai));
                    break;
                case "华英文义津逮" :
                    do {
                        findList = hanwaiRepo.findByHywyjdShengNotNullOrHywyjdYinNotNullOrHywyjdYunNotNull(pageable);
                        pageable = PageRequest.of(++pageNum -1, 15);
                    }while (!findList.contains(hanwai));
                    break;
                case "唐话纂要" :
                    do {
                        findList = hanwaiRepo.findByThzyBiaoyinNotNullOrThzyNiyinNotNull(pageable);
                        pageable = PageRequest.of(++pageNum -1, 15);
                    }while (!findList.contains(hanwai));
                    break;
                case "四声通解" :
                    do {
                        findList = hanwaiRepo.findBySstjJinsuyinNotNullOrSstjShengdiaoNotNullOrSstjBeizhuNotNull(pageable);
                        pageable = PageRequest.of(++pageNum -1, 15);
                    }while (!findList.contains(hanwai));
                    break;
                case "华英启蒙谚解" :
                    do {
                        findList = hanwaiRepo.findByHyqmyjShengNotNullOrHyqmyjYunNotNull(pageable);
                        pageable = PageRequest.of(++pageNum -1, 15);
                    }while (!findList.contains(hanwai));
                    break;
                default:
                    response.setStatus(404);
                    return "error/4xx";

            }
            pageNum -= 1;

            model.addAttribute("subcategory", subcategory)
                    .addAttribute("category", category)
                    .addAttribute("results", findList)
                    .addAttribute("pageNum", pageNum);
        }



        //如果参数word为空，pageNum非空
        if (StringUtils.isEmpty(word) && pageNum != null){

            model.addAttribute("subcategory", subcategory)
                    .addAttribute("category", category);

            List<Hanwai> notnull;
            Pageable pageable = PageRequest.of(pageNum - 1, 15);

            switch (subcategory){
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
                    notnull = hanwaiRepo.findBySstjJinsuyinNotNullOrSstjShengdiaoNotNullOrSstjBeizhuNotNull(pageable);
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
        Iterator<Hanwai> iterator = results.iterator();
        switch (subcategory){
            case "语言自迩集" :
                while (iterator.hasNext()) {
                    Hanwai result = iterator.next();
                    if (result.getZejSheng() == null && result.getZejYin() == null && result.getZejYun() == null){
                        iterator.remove();
                    }else{
                        Template template = templateRepo.findById(result.getId()).orElse(null);
                        templates.add(template);
                    }
                }
                break;
            case "寻津录" :
                while (iterator.hasNext()) {
                    Hanwai result = iterator.next();
                    if (result.getXjlSheng() == null && result.getXjlYin() == null && result.getXjlYun() == null){
                        iterator.remove();
                    }else{
                        Template template = templateRepo.findById(result.getId()).orElse(null);
                        templates.add(template);
                    }
                }
                break;
            case "华英文义津逮" :
                while (iterator.hasNext()) {
                    Hanwai result = iterator.next();
                    if (result.getHywyjdSheng() == null && result.getHywyjdYin() == null && result.getHywyjdYun() == null){
                        iterator.remove();
                    }else{
                        Template template = templateRepo.findById(result.getId()).orElse(null);
                        templates.add(template);
                    }
                }
                break;
            case "唐话纂要" :
                while (iterator.hasNext()) {
                    Hanwai result = iterator.next();
                    if (result.getThzyBiaoyin() == null && result.getThzyNiyin() == null){
                        iterator.remove();
                    }else{
                        Template template = templateRepo.findById(result.getId()).orElse(null);
                        templates.add(template);
                    }
                }
                break;
            case "四声通解" :
                while (iterator.hasNext()) {
                    Hanwai result = iterator.next();
                    if (result.getSstjJinsuyin() == null && result.getSstjShengdiao() == null && result.getSstjBeizhu() == null){
                        iterator.remove();
                    }else{
                        Template template = templateRepo.findById(result.getId()).orElse(null);
                        templates.add(template);
                    }
                }
                break;
            case "华英启蒙谚解" :
                while (iterator.hasNext()) {
                    Hanwai result = iterator.next();
                    if (result.getHyqmyjSheng() == null && result.getHyqmyjYun() == null){
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
