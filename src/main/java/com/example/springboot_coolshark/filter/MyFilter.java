package com.example.springboot_coolshark.filter;

import com.example.springboot_coolshark.pojo.vo.UserVO;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//urlPatterns配置拦截路径
@WebFilter(filterName = "MyFilter" ,urlPatterns = {"/admin.html","/insertBanner.html","/product/delete"})
public class MyFilter implements Filter {
    //过滤器初始化时执行的方法
    public void init(FilterConfig config) throws ServletException {

    }

    //过滤器销毁时执行的方法
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //类型转换
        HttpServletRequest rt = (HttpServletRequest) request;
        HttpServletResponse re = (HttpServletResponse) response;
        //得到会话对象
        HttpSession session = rt.getSession();
        //得到登录成功时保存的用户对象
        UserVO userVO = (UserVO) session.getAttribute("user");
        if (userVO!=null){//代表登录了
            chain.doFilter(request, response);//放行
        }else {//未登录
            re.sendRedirect("/login.html");//如果未登录则重定向到登录页面
        }
        //请求资源之前执行的位置
//        chain.doFilter(request, response);//放行 运行执行请求的资源
        //请求资源之后执行的位置

    }
}
