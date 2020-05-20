package com.jky.resourceshare.controller.login;

import com.jky.resourceshare.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/login")
public class LoginContoller {

    @Autowired
    private CustomUserService customUserService;

    @RequestMapping("/findUserByName")
    // 返回json字符串的时候，采用这个注解
    //@ResponseBody
    public UserDetails findUserByName(HttpServletRequest request) {
        System.out.println(request.getParameter("username"));
        System.out.println(request.getParameter("password"));
        String loginName = "admin";//
        String password = "654321";//
        String userName = "";
        UserDetails userDetails = customUserService.loadUserByUsername(loginName);

        return userDetails;
    }
}
