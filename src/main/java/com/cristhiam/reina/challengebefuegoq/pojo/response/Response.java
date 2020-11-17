/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cristhiam.reina.challengebefuegoq.pojo.response;

import org.springframework.http.HttpStatus;

/**
 *
 * @author Cristhiam Reina <cristiansrc@gmail.com>
 */
public abstract class Response {
    
    private HttpStatus httpStatus = HttpStatus.OK;
    private Integer responseCode;
    private String responseMessage;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        responseCode = httpStatus.value();
        this.httpStatus = httpStatus;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
       
}
