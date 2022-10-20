package com.example.spring3.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Data
@Slf4j
public class Receiver {

    private Boolean receiveResponse = false;

    @JmsListener(destination = "response")
    public void receiveFromResponse(String message) {

        log.info("Message received from response queue---\n" + message);
    }

    @JmsListener(destination = "request")
    public void receiveFromRequest(String message) {
        log.info("Message received from {} queue---\n", message);

        if (null != message) {
            log.info("{} : processing....\n", message);
            this.receiveResponse = true;
        } else {
            log.info(" message is null \n");
            this.receiveResponse = false;
        }

    }


}
