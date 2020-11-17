/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cristhiam.reina.challengebefuegoq.controller;

import com.cristhiam.reina.challengebefuegoq.pojo.Position;
import com.cristhiam.reina.challengebefuegoq.pojo.Satellite;
import java.util.List;

/**
 * Aqui se encuentra la logica de negocio
 * @author Cristhiam Reina <cristiansrc@gmail.com>
 */
public interface SatelliteController {

    /**
     * Este metodo devuelve la ubicacion a partir de la informacion de los satelites enviada.
     * @param satellites lista con la informacion de los satelites
     * @return la posicion en x e y
     * @throws Exception 
     */
    Position getTrilaterationLocation(List<Satellite> satellites) throws Exception;

    /**
     * Este metodo devuelve la ubicacion a partir de las distancias de los satelites
     * @param distanceKenobi Distancia al satellite de kenobi
     * @param distanceSkywalker Distancia al satellite de Skywalker
     * @param distanceSato Distancia al satellite de sato
     * @return la posicion en x e y
     * @throws Exception 
     */
    Position getTrilaterationLocation(double distanceKenobi, double distanceSkywalker, double distanceSato) throws Exception;

    /**
     * Este metodo devuelve el mensaje a partir de la informacion enviada por los satelites.
     * @param satellites lista con la informacion de los satelites
     * @return Cadena de texto con el mensaje determinado
     * @throws Exception 
     */
    String getMessage(List<Satellite> satellites) throws Exception;

    /**
     * Este metodo devuelve el mensaje a partir de la informacion enviada por los satelites.
     * @param kenobi lista de las palabras del mensajes enviadas por el satellite de kenobi
     * @param skywalker lista de las palabras del mensajes enviadas por el satellite de skywalker
     * @param sato lista de las palabras del mensajes enviadas por el satellite de sato
     * @return Cadena de texto con el mensaje determinado
     * @throws Exception 
     */
    String getMessage(List<String> kenobi, List<String> skywalker, List<String> sato) throws Exception;
}
