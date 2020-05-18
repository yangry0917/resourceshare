package com.jky.resourceshare.controller.login;

import com.jky.resourceshare.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginContoller {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/findUserByName")
    // 返回json字符串的时候，采用这个注解
    //@ResponseBody
    public UserDetails findUserByName() {
        String userName = "";
        UserDetails userDetails = loginService.loadUserByUsername(userName);

        return userDetails;
    }
}
