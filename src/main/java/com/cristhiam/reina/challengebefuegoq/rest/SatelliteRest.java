/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cristhiam.reina.challengebefuegoq.rest;

import com.cristhiam.reina.challengebefuegoq.controller.SatelliteController;
import com.cristhiam.reina.challengebefuegoq.pojo.Satellite;
import com.cristhiam.reina.challengebefuegoq.pojo.request.TopSecretRequest;
import com.cristhiam.reina.challengebefuegoq.pojo.request.TopSecretSplitRequest;
import com.cristhiam.reina.challengebefuegoq.pojo.response.Response;
import com.cristhiam.reina.challengebefuegoq.pojo.response.TopSecretResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author Cristhiam Reina <cristiansrc@gmail.com>
 */

@RestController
@RequestMapping("quasar")
public class SatelliteRest {
    @Autowired
    private SatelliteController satelliteController;
    
    private Satellite kenobi = new Satellite();
    private Satellite skywalker = new Satellite();
    private Satellite sato = new Satellite();
    
    @PostMapping("/topsecret")
    public ResponseEntity<TopSecretResponse> postTopSecret(@RequestBody TopSecretRequest topSecretRequest){
        TopSecretResponse topSecretResponse = new TopSecretResponse();
        
        try {
            topSecretResponse.setPosition(satelliteController.getTrilaterationLocation(topSecretRequest.getSatellites()));
            topSecretResponse.setMessage(satelliteController.getMessage(topSecretRequest.getSatellites()));
            topSecretResponse.setHttpStatus(HttpStatus.OK);
        } catch (Exception ex) {
            topSecretResponse.setHttpStatus(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(topSecretResponse, topSecretResponse.getHttpStatus());
    }
    
    private TopSecretResponse getTopSecretResponse(boolean boleanSatelliteName){
        TopSecretResponse topSecretResponse = new TopSecretResponse();
        
        if(!boleanSatelliteName || kenobi.getName() == null || skywalker.getName() == null || sato.getName() == null){
            topSecretResponse.setHttpStatus(HttpStatus.NO_CONTENT);
        } else {
            try {
                topSecretResponse.setPosition(satelliteController.getTrilaterationLocation(kenobi.getDistance(), skywalker.getDistance(), sato.getDistance()));
                topSecretResponse.setMessage(satelliteController.getMessage(kenobi.getMessage(), skywalker.getMessage(), sato.getMessage()));
            } catch (Exception ex) {
                topSecretResponse.setHttpStatus(HttpStatus.NO_CONTENT);
            }
            
            topSecretResponse.setHttpStatus(HttpStatus.OK);
        }
        
        return topSecretResponse;
    }
    
    @PostMapping("/topsecret_split/{satellite_name}")
    public ResponseEntity<TopSecretResponse> postTopSecretSplit(
            @PathVariable("satellite_name") String satelliteName, 
            @RequestBody TopSecretSplitRequest topSecretSplitRequest
    ){
        
        boolean boleanSatelliteName = false;
        
        switch(satelliteName) {
            case "kenobi":
                boleanSatelliteName = true;
                kenobi = topSecretSplitRequest;
                kenobi.setName("kenobi");
                break;
            case "skywalker":
                skywalker = topSecretSplitRequest;
                skywalker.setName("skywalker");
                break;
            case "sato":
                sato = topSecretSplitRequest;
                sato.setName("sato");
                break;
        }
        
        TopSecretResponse topSecretResponse = getTopSecretResponse(boleanSatelliteName);
        
        return new ResponseEntity<>(topSecretResponse, topSecretResponse.getHttpStatus());
    }
    
    @GetMapping("/topsecret_split")
    public ResponseEntity<TopSecretResponse> postTopSecretSplit(){
        TopSecretResponse topSecretResponse = getTopSecretResponse(true);
        return new ResponseEntity<>(topSecretResponse, topSecretResponse.getHttpStatus());
    }
}
