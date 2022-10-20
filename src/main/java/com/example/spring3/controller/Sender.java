package com.example.spring3.controller;

import com.example.spring3.controller.dto.request.CreateRequest;
import com.example.spring3.controller.dto.request.DeleteRequest;
import com.example.spring3.controller.dto.request.ReadRequest;
import com.example.spring3.controller.dto.request.UpdateRequest;
import com.example.spring3.controller.dto.response.ReadResponse;
import com.example.spring3.controller.dto.response.TransferResponse;
import com.example.spring3.service.TransferService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.jms.Queue;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "api/v1/transfer")
@Validated
@Slf4j
public class Sender {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Queue responseQueue;

    @Autowired
    private Queue requestQueue;


    @Autowired
    private TransferService transferService;

    @Autowired
    private ObjectMapper mapper;

    @GetMapping("/find/All")
    public ReadResponse getAllTransfer() {
        log.info("getAllTransfer()");
        ReadResponse response = new ReadResponse();
        try {
            // send request to queue
            jmsTemplate.convertAndSend(requestQueue, requestQueue.getQueueName() + "\t[/find/All]");
            response = transferService.getAllTransfer();
            jmsTemplate.convertAndSend(responseQueue, mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));
            log.info(String.valueOf(responseQueue));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @GetMapping(path = "/find/Dynamic")
    public ReadResponse readTransfer(@Valid @RequestBody ReadRequest request) {
        log.info("readTransfer()");
        ReadResponse response = new ReadResponse();
        try {
            // send request to queue
            jmsTemplate.convertAndSend(requestQueue, requestQueue.getQueueName() + "\t[/find/Dynamic]");

            // send response
            response = transferService.readTransfer(request);
            jmsTemplate.convertAndSend(this.responseQueue, mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @PostMapping("/create")
    public TransferResponse createTransfer(@Valid @RequestBody CreateRequest request) {
        log.info("createTransfer()");
        TransferResponse response = new TransferResponse();
        try {
            // send request to queue
            jmsTemplate.convertAndSend(requestQueue, requestQueue.getQueueName() + "\t[/create]");

            // send response
            response = transferService.createTransfer(request);
            jmsTemplate.convertAndSend(responseQueue, mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));
            log.info(String.valueOf(responseQueue));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @PostMapping("/update")
    public TransferResponse updateTransfer(@Valid @RequestBody UpdateRequest request) {
        log.info("updateTransfer()");
        TransferResponse response = new TransferResponse();
        try {
            // send request to queue
            jmsTemplate.convertAndSend(requestQueue, requestQueue.getQueueName() + "\t[/update]");

            // send response
            response = transferService.updateMGNI(request);
            jmsTemplate.convertAndSend(this.responseQueue, mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));
            log.info(String.valueOf(responseQueue));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @DeleteMapping("/delete")
    public TransferResponse deleteTransfer(@Valid @RequestBody DeleteRequest request) {
        log.info("deleteTransfer()");
        TransferResponse response = new TransferResponse();
        try {
            // send request to queue
            jmsTemplate.convertAndSend(requestQueue, requestQueue.getQueueName() + "\t[/delete]");

            // send response
            response = transferService.deleteTransfer(request);
            jmsTemplate.convertAndSend(this.responseQueue, mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));
            log.info(String.valueOf(responseQueue));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}

