package cn.zouhd.mandarin_corpus.controller;

import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model, HttpSession session){
        //TODO：这里使用了硬编码的密码123456，且用户名为任意非空值，后期需要修改为数据库中的数据
        if (!StringUtils.isEmpty(username) && "123456".equals(password)){
            session.setAttribute("loginUser", username);

            return "redirect:/main";
        }
        model.addAttribute("msg", "用户名或密码不正确，请重试");
        return "index";


    }
}
