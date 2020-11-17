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
import com.cristhiam.reina.challengebefuegoq.pojo.response.TopSecretResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author Cristhiam Reina <cristiansrc@gmail.com>
 */

@RestController
@RequestMapping("/quasar")
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
            topSecretResponse.setResponseMessage("El mensaje y la posicion se determinaron.");
        } catch (Exception ex) {
            topSecretResponse.setHttpStatus(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(topSecretResponse, topSecretResponse.getHttpStatus());
    }
    
    private TopSecretResponse getTopSecretResponse(boolean boleanSatelliteName){
        TopSecretResponse topSecretResponse = new TopSecretResponse();
        
        if(!boleanSatelliteName || kenobi.getName() == null || skywalker.getName() == null || sato.getName() == null){
            topSecretResponse.setHttpStatus(HttpStatus.NOT_FOUND);
        } else {
            try {
                topSecretResponse.setPosition(satelliteController.getTrilaterationLocation(kenobi.getDistance(), skywalker.getDistance(), sato.getDistance()));
                topSecretResponse.setMessage(satelliteController.getMessage(kenobi.getMessage(), skywalker.getMessage(), sato.getMessage()));
            } catch (Exception ex) {
                topSecretResponse.setHttpStatus(HttpStatus.NOT_FOUND);
            }
            
            topSecretResponse.setHttpStatus(HttpStatus.OK);
        }
        
        return topSecretResponse;
    }
    
    @PostMapping("/topsecret-split/{satellite_name}")
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
                boleanSatelliteName = true;
                skywalker = topSecretSplitRequest;
                skywalker.setName("skywalker");
                break;
            case "sato":
                boleanSatelliteName = true;
                sato = topSecretSplitRequest;
                sato.setName("sato");
                break;
        }
        
        TopSecretResponse topSecretResponse = getTopSecretResponse(boleanSatelliteName);
        
        if(!boleanSatelliteName){
            topSecretResponse.setResponseMessage("La informacion enviada no corresponde a un satelite valido");
        }else if(topSecretResponse.getResponseCode() != HttpStatus.OK.value()){
            topSecretResponse.setResponseMessage("Se recibio la informacion de un satelite correctamente, pero no hay suficiente informacion para para determinar el mensaje y la ubicacion.");
        } else {
            topSecretResponse.setResponseMessage("El mensaje y la posicion se determinaron.");
        }
        
        return new ResponseEntity<>(topSecretResponse, topSecretResponse.getHttpStatus());
    }
    
    @GetMapping("/topsecret-split")
    public ResponseEntity<TopSecretResponse> postTopSecretSplit(){
        TopSecretResponse topSecretResponse = getTopSecretResponse(true);
        
        if(topSecretResponse.getResponseCode() != HttpStatus.OK.value()){
            topSecretResponse.setResponseMessage("No hay suficiente informacion para determinar el mensaje y la ubicacion del satelite.");
        } else {
            topSecretResponse.setResponseMessage("El mensaje y la posicion se determinaron.");
        }
        
        return new ResponseEntity<>(topSecretResponse, topSecretResponse.getHttpStatus());
    }
    
    @DeleteMapping("/topsecret-split/{satellite_name}")
    public ResponseEntity<TopSecretResponse> deleteTopSecretSplit(@PathVariable("satellite_name") String satelliteName){
        
        boolean boleanSatelliteName = false;
        
        switch(satelliteName) {
            case "kenobi":
                boleanSatelliteName = true;
                kenobi = new Satellite();
                break;
            case "skywalker":
                boleanSatelliteName = true;
                skywalker = new Satellite();
                break;
            case "sato":
                boleanSatelliteName = true;
                sato = new Satellite();
                break;
        }
        
        
        TopSecretResponse topSecretResponse = new TopSecretResponse();
        
        if(boleanSatelliteName){
            topSecretResponse.setHttpStatus(HttpStatus.OK);
            topSecretResponse.setResponseMessage("La informacion del satelite enviado se elimino correctamente");
        } else {
            topSecretResponse.setHttpStatus(HttpStatus.NOT_FOUND);
            topSecretResponse.setResponseMessage("El satelite enviado no se encontro");
        }
        
        return new ResponseEntity<>(topSecretResponse, topSecretResponse.getHttpStatus());
        
    }
    
    @DeleteMapping("/topsecret-split")
    public ResponseEntity<TopSecretResponse> deleteTopSecretSplitAll(){
        
        kenobi = new Satellite();
        skywalker = new Satellite();
        sato = new Satellite();
        
        TopSecretResponse topSecretResponse = new TopSecretResponse();
        topSecretResponse.setHttpStatus(HttpStatus.OK);
        
        topSecretResponse.setResponseMessage("La informacion de todos los satelites se elimino correctamente.");
        
        return new ResponseEntity<>(topSecretResponse, topSecretResponse.getHttpStatus());
        
    }
}
