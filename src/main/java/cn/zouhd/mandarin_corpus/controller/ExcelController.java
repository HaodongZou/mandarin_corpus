package cn.zouhd.mandarin_corpus.controller;

import cn.zouhd.mandarin_corpus.service.ExcelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/import")
    public String excel(Model model){
        model.addAttribute("activeUrl", "importExcel");
        return "dataOperation/excel";
    }


    @PostMapping("/import")
    public String importExcel(@RequestParam MultipartFile file, Model model){
        String fileName = file.getOriginalFilename();


        try {
            excelService.excelBatchImport(fileName, file);
            logger.info("成功上传了一个文件：" + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg",e.getMessage() + ":" + fileName)
            .addAttribute("success", false);
            return "dataOperation/excel";
        }


        model.addAttribute("msg", "上传成功：" + fileName)
        .addAttribute("success", true);
        return "dataOperation/excel";
    }
}
