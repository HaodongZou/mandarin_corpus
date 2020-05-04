package cn.zouhd.mandarin_corpus.controller;

import cn.zouhd.mandarin_corpus.entities.Guangyun;
import cn.zouhd.mandarin_corpus.entities.Yunshu;
import cn.zouhd.mandarin_corpus.repositories.GuangyunRepo;
import cn.zouhd.mandarin_corpus.repositories.YunshuRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author zouhd
 */
@Controller
@RequestMapping("/yunshu")
public class YunshuController {


    private YunshuRepo yunshuRepo;
    private GuangyunRepo guangyunRepo;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public YunshuController(YunshuRepo yunshuRepo, GuangyunRepo guangyunRepo) {
        this.yunshuRepo = yunshuRepo;
        this.guangyunRepo = guangyunRepo;
    }

    @GetMapping
    public String yunshuPage(Model model){
        model.addAttribute("activeUrl", "yunshuPage");
        List<Yunshu> all = yunshuRepo.findAll();
        model.addAttribute("results", all);
        return "yunshu/yunshu";

    }

    @GetMapping("/results")
    public String results(@RequestParam String yunshuSearch, Model model){
        model.addAttribute("activeUrl", "yunshuPage");
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
            return "yunshu/yunshu";
        }
//        不是数字ID则搜索字符
        String searchLike = "%" + yunshuSearch + "%";
        List<Yunshu> results = yunshuRepo.findByWordLike(searchLike);
        if (results == null || results.size() == 0){
            model.addAttribute("msg", "查询不到结果");
            return "yunshu/yunshu";
        }
        model.addAttribute("results", results);
        return "yunshu/yunshu";
    }

    @GetMapping("/guangyuns")
    public String guangyunsPage(@RequestParam Integer pageNum, Model model){
//        Guangyun guangyunList = guangyunRepo.findById(1).orElse(null);
        PageRequest pageRequest = PageRequest.of(pageNum - 1, 15);
        List<Guangyun> guangyunList = guangyunRepo.findAll(pageRequest).toList();
        model.addAttribute("results", guangyunList)
                .addAttribute("pageNum", pageNum);

        return "yunshu/guangyun";
    }

    @GetMapping("/guangyun")
    public String guangyun(@RequestParam() String word, Model model){
        String wordLike = "%" + word + "%";
        List<Guangyun> byWordLike = guangyunRepo.findByWordLike(wordLike);
        for (Guangyun guangyun : byWordLike) {
            while(guangyun.getZhushi().contains("上同")){
                guangyun.setZhushi(guangyunRepo.findById(guangyun.getId() - 1).orElse(null).getZhushi());
            }
        }
        model.addAttribute("results", byWordLike);
        return "searchResult";
    }


}
