package com.jky.resourceshare.service.impl;

import com.jky.resourceshare.dao.HelloMapper;
import com.jky.resourceshare.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private HelloMapper helloMapper;

    @Override
    public List<Map<String,String>> hello(){
        List<Map<String,String>> list = helloMapper.hello();
        return list;
    }

    @Override
    public List<Map<String,String>> helloWorld(){
        String id = "1";
        List<Map<String,String>> list = helloMapper.helloWorld(id);
        return list;
    }
}
