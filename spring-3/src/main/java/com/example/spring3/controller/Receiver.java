package com.example.spring3.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
    private static  final Logger logger = LogManager.getLogger(Receiver.class);

    @JmsListener(destination = "response")
    public  void receiveFromResponse(String message){
        logger.info("Message received from response queue---\n"+message);
    }

    @JmsListener(destination = "request")
    public  void receiveFromRequest(String message){
        logger.info("Message received from {} queue---\n",message);
    }
}
