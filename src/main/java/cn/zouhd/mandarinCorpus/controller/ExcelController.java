package cn.zouhd.mandarinCorpus.controller;

import cn.zouhd.mandarinCorpus.entities.Hanwai;
import cn.zouhd.mandarinCorpus.listener.HanwaiExcelDataListener;
import cn.zouhd.mandarinCorpus.repositories.HanwaiRepo;
import com.alibaba.excel.EasyExcel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * The type Excel controller.
 *
 * @author zouhd
 * @date 2020 -05-11 11:47:44
 */
@Controller
@RequestMapping("/excel")
public class ExcelController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelController.class);

    @Autowired
    HanwaiRepo hanwaiRepo;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Excel string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/import")
    public String excel(Model model){
        model.addAttribute("activeUrl", "importExcel");
        return "dataOperation/excel";
    }


    /**
     * Import excel string.
     *
     * @param file the file
     * @return the string
     * @throws IOException the io exception
     */
    @PostMapping("/import")
    public String importExcel(@RequestParam MultipartFile file, Model model){
        String fileName = file.getOriginalFilename();
        LOGGER.info("开始。。。。。。。");

        try {
            EasyExcel.read(file.getInputStream(), Hanwai.class, new HanwaiExcelDataListener(hanwaiRepo)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("msg",e.getMessage() + ":" + fileName)
            .addAttribute("success", false);
            return "dataOperation/excel";
        }
        LOGGER.info("结束。。。。。。。");
        model.addAttribute("msg", "上传成功：" + fileName)
        .addAttribute("success", true);
        return "dataOperation/excel";
    }
}
