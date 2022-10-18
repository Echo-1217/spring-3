package com.example.spring3.service;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import org.junit.Test;
import com.example.spring3.Spring3Application;
import org.junit.runner.RunWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Spring3Application.class)
public class UnitTestDemoServiceTest {

    @Autowired
    private UnitTestDemoService service;

    @Test
    // 測試 private 方法
    public void privateFunctionTest() throws IllegalAccessException, IllegalArgumentException,
            InvocationTargetException, NoSuchMethodException,
            SecurityException, ClassNotFoundException {
        String expected = "private";
        // 取得 class
        Class<?> clazz = Class.forName("com.example.spring3.service.UnitTestDemoService");
        // 取得方法，第一個參數為 function 的名稱，方法有幾個參數後面就有幾個相對應的參數型態
        Method method = clazz.getDeclaredMethod("privateFunction",
                String.class, Long.class);
        // 將 private method 改成 accessible
        method.setAccessible(true);
        // 調用方法取得回傳值
        String response = method.invoke(service, expected, 10L).toString();
        assertEquals(expected, response);
    }

    @Test
    // mock static 方法
    public void staticFunctionTest() {
        // 使用 MockedStatic 製作假元件
        MockedStatic<UnitTestDemoService> mocked = Mockito.mockStatic(UnitTestDemoService.class);
        String response;
        // 當沒有設定回傳值時，回傳值為 null
        response = UnitTestDemoService.staticFunction("static");
        assertEquals(null, response);
        // 設定 function 的回傳值
        mocked.when(() -> UnitTestDemoService.staticFunction(any())).thenReturn("static");
        response = UnitTestDemoService.staticFunction("test");
        assertEquals("static", response);
    }
}