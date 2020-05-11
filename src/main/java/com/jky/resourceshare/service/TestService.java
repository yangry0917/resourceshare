package com.jky.resourceshare.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface TestService {
    List<Map<String,String>> hello();

    List<Map<String,String>> helloWorld();
}
