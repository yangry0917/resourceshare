package com.jky.resourceshare.common.util.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jky.resourceshare.entity.RespBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("customAuthenticationFailHander")
public class CustomAuthenticationFailHander extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    private ObjectMapper objectMapper;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        // TODO Auto-generated method stub
        logger.info("登录失败");

        //以Json格式返回
        /*Map<String,String> map=new HashMap<>();
        map.put("code", "201");
        map.put("msg", "登录失败");
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());*/
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(RespBean.error("登录失败")));
    }
}