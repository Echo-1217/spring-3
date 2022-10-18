package com.example.spring3.controller;

import com.example.spring3.Spring3Application;
import com.example.spring3.controller.dto.response.ReadResponse;
import com.example.spring3.controller.dto.response.TransferResponse;
import com.example.spring3.model.entity.MGNI;
import com.example.spring3.service.TransferService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// 讓測試運行於 Spring 測試環境
@RunWith(SpringRunner.class)
// 獲取啟動主程式、加載配置，確定啟用 Spring Boot
@SpringBootTest(classes = Spring3Application.class)
public class SenderTest {
    // 測試 sender 需要建立假的 MVC 環境
    private MockMvc mockMvc;

    @Autowired
    private Sender sender;

    // 使用 MockBean 製作假元件
    @MockBean
    private TransferService service;

    // before 的 function 會在一開始比 Test 先執行
    @Before
    public void setup() throws Exception {

        // standaloneSetup 表示通過參數指定一組控制器，這樣就不需要從上下文獲取
        mockMvc = MockMvcBuilders.standaloneSetup(sender).build();
    }

    @Test
    public void readTransfer() throws Exception {
        // Arrange (初始化) 設定參數、期望回傳值
        String strResponse = "[{\"id\":\"MGI20220666666\",\"time\":\"2022-10-14 10:03:11\",\"type\":\"1\",\"cmNo\":\"F62S\",\"kacType\":\"2\",\"bankNo\":\"123\",\"ccy\":\"USD\",\"pvType\":\"1\",\"bicaccNo\":\"2200546\",\"amt\":20221014.0000,\"ctName\":\"Echo\",\"ctTel\":\"0987654321\",\"status\":\"0\",\"cashiList\":[{\"mgniId\":\"MGI20221014100311170\",\"accNo\":\"01\",\"ccy\":\"USD\",\"amt\":20221014.0000}],\"utime\":\"2022-10-14 10:03:11\",\"itype\":\"1\",\"preason\":\"test\"}]";
        ObjectMapper om = new ObjectMapper();


        ReadResponse expected = new ReadResponse();

        expected.setMgniList(om.readValue(strResponse, new TypeReference<>() {
        }));


        // mocking
        when(this.service.readTransfer(any())).thenReturn(expected);

        // 將 object 轉為 JSON 的格式
        String expectedStr = om.writeValueAsString(expected);

        // Act (行為)
        // 取得 function 的實際回傳值
        ResultActions resultActions =
                // perform(request) 為做一個請求的建立，get(url) 為 request 的連結
                mockMvc.perform(
                                MockMvcRequestBuilders.get("/api/v1/transfer/find/Dynamic")
                                        .content("{\"id\":\"\",\n" +
                                                "    \"cmNo\": \"\",\n" +
                                                "    \"bicaccNo\": \"\"}")// @RequestBody
                                        .contentType(MediaType.APPLICATION_JSON)
                        )
                        // 輸出整個回應結果訊息
                        .andDo(print())
                        .andExpect(status().isOk());
        // 取得回傳物件
        String actual = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("expectedStr\n" + expectedStr + "\n");
        System.out.println("actual: \n" + actual);

        // Assert (驗證結果)
        Assert.assertEquals(expectedStr, actual);
    }

    @Test
    public void createTransfer() throws Exception {
        String strResponse = "{\"id\":\"MGI2022010181020666\",\"time\":\"2022-10-18 10:20:66\",\"type\":\"1\",\"cmNo\":\"F62S\",\"kacType\":\"2\",\"bankNo\":\"123\",\"ccy\":\"USD\",\"pvType\":\"1\",\"bicaccNo\":\"2200546\",\"amt\":20221014.0000,\"ctName\":\"Echo\",\"ctTel\":\"0987654321\",\"status\":\"0\",\"cashiList\":[{\"mgniId\":\"MGI2022010181020666\",\"accNo\":\"01\",\"ccy\":\"USD\",\"amt\":20221014.0000}],\"utime\":\"2022-10-18 10:20:66\",\"itype\":\"1\",\"preason\":\"test\"}";
        // Arrange (初始化) 設定參數、期望回傳值
        ObjectMapper om = new ObjectMapper();

        TransferResponse expected = new TransferResponse();
        expected.setMgni(om.readValue(strResponse,MGNI.class));
        expected.setMessage("test Junit");
        // mocking
        when(this.service.createTransfer(any())).thenReturn(expected);

        // 將 object 轉為 JSON 的格式
        String expectedStr = om.writeValueAsString(expected);

        // Act (行為)
        // 取得 function 的實際回傳值
        ResultActions resultActions =
                // perform(request) 為做一個請求的建立，get(url) 為 request 的連結
                mockMvc.perform(
                                MockMvcRequestBuilders.post("/api/v1/transfer/create")
                                        .content("{\n" +
                                                "    \"cmNo\": \"test\",\n" +
                                                "    \"kacType\": \"1\",\n" +
                                                "    \"bankNo\": \"123\",\n" +
                                                "    \"ccy\": \"ttt\",\n" +
                                                "    \"pvType\": \"1\",\n" +
                                                "    \"bicaccNo\": \"test\",\n" +
                                                "    \"acc\": [\n" +
                                                "        {\n" +
                                                "            \"test\": 20221018\n" +
                                                "        }\n" +
                                                "        ,\n" +
                                                "        {\n" +
                                                "            \"test\": 20221018\n" +
                                                "        }\n" +
                                                "    ],\n" +
                                                "    \"itype\": \"\"\n" +
                                                "}")// @RequestBody
                                        .contentType(MediaType.APPLICATION_JSON)
                        )
                        // 輸出整個回應結果訊息
                        .andDo(print())
                        .andExpect(status().isOk());
        // 取得回傳物件
        String actual = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("expectedStr\n" + expectedStr + "\n");
        System.out.println("actual: \n" + actual);

        // Assert (驗證結果)
        Assert.assertEquals(expectedStr, actual);
    }
}

