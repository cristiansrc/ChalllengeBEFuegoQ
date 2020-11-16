/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cristhiam.reina.challengebefuegoq.pojo.response;

import com.cristhiam.reina.challengebefuegoq.pojo.Position;


/**
 *
 * @author Cristhiam Reina <cristiansrc@gmail.com>
 */
public class TopSecretResponse extends Response{
    private String message;
    private Position position;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
}
