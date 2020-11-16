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
 *
 * @author Cristhiam Reina <cristiansrc@gmail.com>
 */
public interface SatelliteController {
    
    Position getTrilaterationLocation(List<Satellite> satellites) throws Exception;
    
    Position getTrilaterationLocation(double distanceKenobi, double distanceSkywalker, double distanceSato) throws Exception;
    
    String getMessage(List<Satellite> satellites) throws Exception;
    
    String getMessage(List<String> kenobi, List<String> skywalker, List<String> sato) throws Exception;
}
