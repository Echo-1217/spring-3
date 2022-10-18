package com.example.spring3.controller;

import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Data
public class Receiver {
    private static final Logger logger = LogManager.getLogger(Receiver.class);

    private Boolean receiveResponse = false;

    @JmsListener(destination = "response")
    public void receiveFromResponse(String message) {

        logger.info("Message received from response queue---\n" + message);
    }

    @JmsListener(destination = "request")
    public void receiveFromRequest(String message) {
        logger.info("Message received from {} queue---\n", message);

        if (null != message) {
            logger.info("{} : processing....\n", message);
            this.receiveResponse = true;
        } else {
            logger.info(" message is null \n");
            this.receiveResponse = false;
        }

    }


}
