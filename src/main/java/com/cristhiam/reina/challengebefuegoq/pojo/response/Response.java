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

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
    
    public String getResponse() {
        return httpStatus.name();
    }

    public Integer getRespnseCode() {
        return httpStatus.value();
    }
}
