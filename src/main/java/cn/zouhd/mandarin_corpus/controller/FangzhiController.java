package cn.zouhd.mandarin_corpus.controller;

import cn.zouhd.mandarin_corpus.entities.Fangzhi;
import cn.zouhd.mandarin_corpus.repositories.FangzhiRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/fangzhi")
public class FangzhiController {

    @Autowired
    FangzhiRepo fangzhiRepo;

    @GetMapping("/search")
    @ResponseBody
    public List<Fangzhi> fangzhi(@RequestParam String fangzhi){
        String name = "%" + fangzhi + "%";
        List<Fangzhi> list = fangzhiRepo.findByNameLike(name);

//        if (list == null){
//            Fangzhi fangzhi1 = new Fangzhi();
//            fangzhi1.setName(" ");
//            list.add(fangzhi1);
//        }
        return list;

    }
}
