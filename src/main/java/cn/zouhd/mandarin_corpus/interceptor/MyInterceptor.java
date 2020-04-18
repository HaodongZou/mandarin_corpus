package cn.zouhd.mandarin_corpus.interceptor;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginUser = request.getSession().getAttribute("loginUser");

        //TODO：用户名为任意非空值即可，需要修改为数据库中的值
        if (loginUser == null){
            request.setAttribute("msg", "没有访问权限，请登录后重试");
            request.getRequestDispatcher("/login").forward(request, response);
            return false;
        }

        return true;

    }
}
