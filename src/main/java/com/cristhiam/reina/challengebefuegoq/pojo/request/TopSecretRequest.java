/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cristhiam.reina.challengebefuegoq.pojo.request;

import com.cristhiam.reina.challengebefuegoq.pojo.Satellite;
import java.util.List;

/**
 *
 * @author Cristhiam Reina <cristiansrc@gmail.com>
 */
public class TopSecretRequest {
    private List<Satellite> satellites;

    public List<Satellite> getSatellites() {
        return satellites;
    }

    public void setSatellites(List<Satellite> satellites) {
        this.satellites = satellites;
    }

}
