package com.jky.resourceshare.service.impl;


import com.jky.resourceshare.dao.LoginMapper;
import com.jky.resourceshare.entity.User;
import com.jky.resourceshare.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService, UserDetailsService {
    @Autowired
    private LoginMapper loginMapper;

    /*public  UserDetails findUserByName(String userName){
        return loadUserByUsername(userName);
    }*/

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = loginMapper.findUserByUsername(userName);
        if(user == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        return user;
    }
}
