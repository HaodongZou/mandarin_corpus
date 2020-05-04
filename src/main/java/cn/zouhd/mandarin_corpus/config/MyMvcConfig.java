package cn.zouhd.mandarin_corpus.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("dashboard");
        registry.addViewController("/login").setViewName("index");

        registry.addViewController("/main").setViewName("dashboard");

        registry.addViewController("/yunshu").setViewName("yunshu/yunshu");

        registry.addViewController("/excel/import").setViewName("dataOperation/excel");

        registry.addViewController("/excel/export").setViewName("dataOperation/exportExcel");

    }

}
