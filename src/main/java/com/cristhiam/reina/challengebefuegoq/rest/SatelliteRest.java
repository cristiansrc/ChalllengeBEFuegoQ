/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cristhiam.reina.challengebefuegoq.rest;

import com.cristhiam.reina.challengebefuegoq.constants.Constants;
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
 * Clase que contiene la capa de servicios de la aplicacion
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

    /**
     * Este metodo recibe la informacion de la distancia y los fragmentos de mensajes enviados por los satellites y devuelve el mensaje determinado y la posicion de x e y
     * @param topSecretRequest recibe la informacion 
     * @return la informacion del mensaje y la posicion de x e y
     */
    @PostMapping("/topsecret")
    public ResponseEntity<TopSecretResponse> postTopSecret(@RequestBody TopSecretRequest topSecretRequest) {
        TopSecretResponse topSecretResponse = new TopSecretResponse();

        try {
            topSecretResponse.setPosition(satelliteController.getLocation(topSecretRequest.getSatellites()));
            topSecretResponse.setMessage(satelliteController.getMessage(topSecretRequest.getSatellites()));
            topSecretResponse.setHttpStatus(HttpStatus.OK);
            topSecretResponse.setResponseMessage(Constants.MSG_DETERMINATE_MASSAGE);
        } catch (Exception ex) {
            topSecretResponse.setHttpStatus(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(topSecretResponse, topSecretResponse.getHttpStatus());
    }

    /**
     * devuelve la posicion y el mensaje determinado en el caso en el que se encuentre la suficiente informacion.
     * @param boleanSatelliteName indica si se envio un satellite correcto
     * @return la informacion del mensaje y la posicion
     */
    private TopSecretResponse getTopSecretResponse(boolean boleanSatelliteName) {
        TopSecretResponse topSecretResponse = new TopSecretResponse();

        if (!boleanSatelliteName || kenobi.getName() == null || skywalker.getName() == null || sato.getName() == null) {
            topSecretResponse.setHttpStatus(HttpStatus.NOT_FOUND);
        } else {
            try {
                topSecretResponse.setPosition(satelliteController.getLocation(kenobi.getDistance(), skywalker.getDistance(), sato.getDistance()));
                topSecretResponse.setMessage(satelliteController.getMessage(kenobi.getMessage(), skywalker.getMessage(), sato.getMessage()));
            } catch (Exception ex) {
                topSecretResponse.setHttpStatus(HttpStatus.NOT_FOUND);
            }

            topSecretResponse.setHttpStatus(HttpStatus.OK);
        }

        return topSecretResponse;
    }

    /**
     * Este metodo recibe la informacion de un satelite y en el caso de tener toda la informacion devuelve la ubicacion y el mensaje determinado
     * @param satelliteName Nombre del satelite
     * @param topSecretSplitRequest Body de la peticion del servicios
     * @return 
     */
    @PostMapping("/topsecret-split/{satellite_name}")
    public ResponseEntity<TopSecretResponse> postTopSecretSplit(
            @PathVariable("satellite_name") String satelliteName,
            @RequestBody TopSecretSplitRequest topSecretSplitRequest
    ) {

        boolean boleanSatelliteName = false;

        switch (satelliteName) {
            case Constants.SATELLITE_KENOBI:
                boleanSatelliteName = true;
                kenobi = topSecretSplitRequest;
                kenobi.setName(Constants.SATELLITE_KENOBI);
                break;
            case Constants.SATELLITE_SKYWALKER:
                boleanSatelliteName = true;
                skywalker = topSecretSplitRequest;
                skywalker.setName(Constants.SATELLITE_SKYWALKER);
                break;
            case Constants.SATELLITE_SATO:
                boleanSatelliteName = true;
                sato = topSecretSplitRequest;
                sato.setName(Constants.SATELLITE_SATO);
                break;
        }

        TopSecretResponse topSecretResponse = getTopSecretResponse(boleanSatelliteName);

        if (!boleanSatelliteName) {
            topSecretResponse.setResponseMessage(Constants.MSG_INVALID_SATELLITE);
        } else if (topSecretResponse.getResponseCode() != HttpStatus.OK.value()) {
            topSecretResponse.setResponseMessage(Constants.MSG_CORRECT_SATELLITE_INCOMPLETE_INFORMATION);
        } else {
            topSecretResponse.setResponseMessage(Constants.MSG_DETERMINATE_MASSAGE);
        }

        return new ResponseEntity<>(topSecretResponse, topSecretResponse.getHttpStatus());
    }

    /**
     * Este metodo devuelve el mensaje y la ubicacion si hay informacion disponible
     * @return mensaje determinado y la ubicacion
     */
    @GetMapping("/topsecret-split")
    public ResponseEntity<TopSecretResponse> postTopSecretSplit() {
        TopSecretResponse topSecretResponse = getTopSecretResponse(true);

        if (topSecretResponse.getResponseCode() != HttpStatus.OK.value()) {
            topSecretResponse.setResponseMessage(Constants.MSG_INCOMPLETE_INFORMATION);
        } else {
            topSecretResponse.setResponseMessage(Constants.MSG_DETERMINATE_MASSAGE);
        }

        return new ResponseEntity<>(topSecretResponse, topSecretResponse.getHttpStatus());
    }

    /**
     * Este metodo elimina la informacion de un satelite
     * @param satelliteName El nombre del satelite
     * @return 
     */
    @DeleteMapping("/topsecret-split/{satellite_name}")
    public ResponseEntity<TopSecretResponse> deleteTopSecretSplit(@PathVariable("satellite_name") String satelliteName) {

        boolean boleanSatelliteName = false;

        switch (satelliteName) {
            case Constants.SATELLITE_KENOBI:
                boleanSatelliteName = true;
                kenobi = new Satellite();
                break;
            case Constants.SATELLITE_SKYWALKER:
                boleanSatelliteName = true;
                skywalker = new Satellite();
                break;
            case Constants.SATELLITE_SATO:
                boleanSatelliteName = true;
                sato = new Satellite();
                break;
        }

        TopSecretResponse topSecretResponse = new TopSecretResponse();

        if (boleanSatelliteName) {
            topSecretResponse.setHttpStatus(HttpStatus.OK);
            topSecretResponse.setResponseMessage(Constants.MSG_DELETE_SUCCESS);
        } else {
            topSecretResponse.setHttpStatus(HttpStatus.NOT_FOUND);
            topSecretResponse.setResponseMessage(Constants.MSG_SATELLITE_NOT_EXIST);
        }

        return new ResponseEntity<>(topSecretResponse, topSecretResponse.getHttpStatus());

    }

    /**
     * Elimina la informacion de los tres satelites
     * @return 
     */
    @DeleteMapping("/topsecret-split")
    public ResponseEntity<TopSecretResponse> deleteTopSecretSplitAll() {

        kenobi = new Satellite();
        skywalker = new Satellite();
        sato = new Satellite();

        TopSecretResponse topSecretResponse = new TopSecretResponse();
        topSecretResponse.setHttpStatus(HttpStatus.OK);

        topSecretResponse.setResponseMessage(Constants.MSG_DELETE_SUCCESS_ALL);

        return new ResponseEntity<>(topSecretResponse, topSecretResponse.getHttpStatus());
    }
}
