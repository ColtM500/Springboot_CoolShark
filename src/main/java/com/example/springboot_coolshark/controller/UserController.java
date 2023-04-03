package com.example.springboot_coolshark.controller;

import com.example.springboot_coolshark.mapper.UserMapper;
import com.example.springboot_coolshark.pojo.dto.UserLoginDTO;
import com.example.springboot_coolshark.pojo.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@RestController
public class UserController {
    @Autowired(required = false)
    UserMapper mapper;

    @RequestMapping("/login")
    public int login(@RequestBody UserLoginDTO userLoginDTO, HttpServletResponse response,HttpSession session){
        System.out.println("userLoginDTO = " + userLoginDTO);
        UserVO userVO = mapper.selectByUsername(userLoginDTO.getUsername());
        if (userVO!=null){
            if (userVO.getPassword().equals(userLoginDTO.getPassword())){
                //把登录成功的用户对象保存到会话对象里面
                session.setAttribute("user",userVO);

                //判断是否需要记住用户名和密码
                if (userLoginDTO.getRem()){
                    //创建cookie 把用户名和密码保存进去
                    Cookie c1 = new Cookie("username", userLoginDTO.getUsername());
                    Cookie c2 = new Cookie("password", userLoginDTO.getPassword());
                    //设置保存时间 单位是秒
                    c1.setMaxAge(60*60*24*30);//60*60一小时 *24一天 *30一个月
                    response.addCookie(c1);
                    response.addCookie(c2);
                }
                return 1;
            }
            return 2;
        }
        return 3;
    }

    @RequestMapping("/currentUser")
    public UserVO currentUser(HttpSession session){
        return (UserVO) session.getAttribute("user");
    }

    @RequestMapping("/logout")
    public void logout(HttpSession session){
        session.removeAttribute("user");
    }
}
