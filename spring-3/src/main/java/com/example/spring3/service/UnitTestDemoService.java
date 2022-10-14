package com.example.spring3.service;

import org.springframework.stereotype.Service;

@Service
public class UnitTestDemoService {

    private String privateFunction(String str,Long id) {
        return str;
    }

    static public String staticFunction(String str) {
        return str;
    }
}