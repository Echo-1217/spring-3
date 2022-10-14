package com.example.spring3.repository;

import com.example.spring3.Spring3Application;
import com.example.spring3.model.MgniRepository;
import com.example.spring3.model.entity.MGNI;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

// 讓測試運行於 Spring 測試環境
@RunWith(SpringRunner.class)
// 獲取啟動主程式、加載配置，確定啟用 Spring Boot
@SpringBootTest(classes = Spring3Application.class)
public class MgniRepositoryTest {

    @Autowired
    MgniRepository repository;

    // 有 @Test 的 function 才會在 JUnit Test 時被執行
    @Test
    public void getMGNITest() {
        // Arrange (初始化)，設定參數、期望回傳值
        String expected = "MGI20221013160014051";

        // Act (行為)，取得 function 的實際回傳值
        if (repository.findById("MGI20221013160014051").isEmpty()) {
            return;
        }
        MGNI actual = repository.findById("MGI20221013160014051").get();

        // Assert (驗證結果)
        // JUnit 原生寫法
        Assert.assertEquals(expected, actual.getId());

        // AssertJ 寫法
        Assertions.assertThat(actual.getId()).isEqualTo(expected);
    }

}
