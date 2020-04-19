package cn.zouhd.mandarin_corpus.controller;

import cn.zouhd.mandarin_corpus.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    ExcelService excelService;

    @GetMapping("/import")
    public String excel(Model model){
        model.addAttribute("activeUrl", "importExcel");
        return "dataOperation/excel";
    }


    @PostMapping("/import")
    public String importExcel(@RequestParam MultipartFile file){
        String fileName = file.getOriginalFilename();

        boolean finished = false;

        System.out.println("进入了importExcel...");
        try {
            finished = excelService.excelBatchImport(fileName, file);
            System.out.println("执行了excelService....");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "404";
    }
}
