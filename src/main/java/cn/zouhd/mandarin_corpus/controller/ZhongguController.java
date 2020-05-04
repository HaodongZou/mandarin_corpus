package cn.zouhd.mandarin_corpus.controller;

import cn.zouhd.mandarin_corpus.entities.Guangyun;
import cn.zouhd.mandarin_corpus.repositories.GuangyunRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/zhonggu")
public class ZhongguController {

    @Autowired
    GuangyunRepo guangyunRepo;

    @GetMapping("/guangyuns")
    public String guangyunsPage(@RequestParam Integer pageNum, Model model){
//        Guangyun guangyunList = guangyunRepo.findById(1).orElse(null);
        PageRequest pageRequest = PageRequest.of(pageNum - 1, 15);
        List<Guangyun> guangyunList = guangyunRepo.findAll(pageRequest).toList();
        model.addAttribute("results", guangyunList)
                .addAttribute("pageNum", pageNum);

        return "zhonggu/guangyun";
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
