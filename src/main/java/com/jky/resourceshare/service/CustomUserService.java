package com.jky.resourceshare.service;


import org.springframework.security.core.userdetails.UserDetails;

public interface CustomUserService {
    /*UserDetails findUserByName(String userName);*/
    UserDetails loadUserByUsername(String loginName);
}
