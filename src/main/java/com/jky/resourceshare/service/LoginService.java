package com.jky.resourceshare.service;


import org.springframework.security.core.userdetails.UserDetails;

public interface LoginService {
    /*UserDetails findUserByName(String userName);*/
    UserDetails loadUserByUsername(String userName);
}
