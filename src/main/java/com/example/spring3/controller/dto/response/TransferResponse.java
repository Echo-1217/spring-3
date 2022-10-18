package com.example.spring3.controller.dto.response;

import com.example.spring3.model.entity.MGNI;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@NoArgsConstructor
//@XmlRootElement(name = "Detail")
//@XmlAccessorType(XmlAccessType.FIELD)
public class TransferResponse {
    private MGNI mgni;
    private String message;
}