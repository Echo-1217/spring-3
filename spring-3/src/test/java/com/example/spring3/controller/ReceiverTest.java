package com.example.spring3.controller;

import com.example.spring3.Spring3Application;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

// 讓測試運行於 Spring 測試環境
@RunWith(SpringRunner.class)
// 獲取啟動主程式、加載配置，確定啟用 Spring Boot
@SpringBootTest(classes = Spring3Application.class)
public class ReceiverTest {

}
