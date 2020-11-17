/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cristhiam.reina.challengebefuegoq.controller;

import com.cristhiam.reina.challengebefuegoq.constants.Constants;
import com.cristhiam.reina.challengebefuegoq.pojo.Position;
import com.cristhiam.reina.challengebefuegoq.pojo.Satellite;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Cristhiam Reina <cristiansrc@gmail.com>
 */
@Repository
public class SatelliteControllerImpl implements SatelliteController {

    @Value("${satelite.kenobi.x}")
    private float kenobiX;

    @Value("${satelite.kenobi.y}")
    private double kenobiY;

    @Value("${satelite.skywalker.x}")
    private double skywalkerX;

    @Value("${satelite.skywalker.y}")
    private double skywalkerY;

    @Value("${satelite.sato.x}")
    private double satoX;

    @Value("${satelite.sato.y}")
    private double satoY;

    @Override
    public Position getTrilaterationLocation(List<Satellite> satellites) throws Exception {
        double distanceKenobi = 0;
        double distanceSkywalker = 0;
        double distanceSato = 0;

        int flag = 0;

        for (int i = 0; i < 3; i++) {
            Satellite satellite = satellites.get(i);

            if (satellite.getName().equalsIgnoreCase(Constants.SATELLITE_KENOBI)) {
                distanceKenobi = satellite.getDistance();
                flag++;
            }

            if (satellite.getName().equalsIgnoreCase(Constants.SATELLITE_SKYWALKER)) {
                distanceSkywalker = satellite.getDistance();
                flag++;
            }

            if (satellite.getName().equalsIgnoreCase(Constants.SATELLITE_SATO)) {
                distanceSato = satellite.getDistance();
                flag++;
            }

        }

        if (flag != 3) {
            throw new Exception(Constants.MSG_INDETERMINATE_MESSAGE);
        }

        return getTrilaterationLocation(distanceKenobi, distanceSkywalker, distanceSato);
    }

    @Override
    public Position getTrilaterationLocation(double distanceKenobi, double distanceSkywalker, double distanceSato) throws Exception {

        double[] P1 = new double[2];
        double[] P2 = new double[2];
        double[] P3 = new double[2];
        double[] ex = new double[2];
        double[] ey = new double[2];
        double[] p3p1 = new double[2];
        double jval = 0;
        double temp = 0;
        double ival = 0;
        double p3p1i = 0;
        double triptx;
        double tripty;
        double xval;
        double yval;
        double t1;
        double t2;
        double t3;
        double t;
        double exx;
        double d;
        double eyy;

        P1[0] = kenobiX;
        P1[1] = kenobiY;
        //POINT 2 
        P2[0] = skywalkerX;
        P2[1] = skywalkerY;
        //POINT 3 
        P3[0] = satoX;
        P3[1] = satoY;

        for (int i = 0; i < P1.length; i++) {
            t1 = P2[i];
            t2 = P1[i];
            t = t1 - t2;
            temp += (t * t);
        }
        d = Math.sqrt(temp);
        for (int i = 0; i < P1.length; i++) {
            t1 = P2[i];
            t2 = P1[i];
            exx = (t1 - t2) / (Math.sqrt(temp));
            ex[i] = exx;
        }

        for (int i = 0; i < P3.length; i++) {
            t1 = P3[i];
            t2 = P1[i];
            t3 = t1 - t2;
            p3p1[i] = t3;
        }

        for (int i = 0; i < ex.length; i++) {
            t1 = ex[i];
            t2 = p3p1[i];
            ival += (t1 * t2);
        }

        for (int i = 0; i < P3.length; i++) {
            t1 = P3[i];
            t2 = P1[i];
            t3 = ex[i] * ival;
            t = t1 - t2 - t3;
            p3p1i += (t * t);
        }

        for (int i = 0; i < P3.length; i++) {
            t1 = P3[i];
            t2 = P1[i];
            t3 = ex[i] * ival;
            eyy = (t1 - t2 - t3) / Math.sqrt(p3p1i);
            ey[i] = eyy;
        }

        for (int i = 0; i < ey.length; i++) {
            t1 = ey[i];
            t2 = p3p1[i];
            jval += (t1 * t2);
        }
        xval = (Math.pow(distanceKenobi, 2) - Math.pow(distanceSkywalker, 2) + Math.pow(d, 2)) / (2 * d);
        yval = ((Math.pow(distanceKenobi, 2) - Math.pow(distanceSato, 2) + Math.pow(ival, 2) + Math.pow(jval, 2)) / (2 * jval)) - ((ival / jval) * xval);
        t1 = kenobiX;
        t2 = ex[0] * xval;
        t3 = ey[0] * yval;
        triptx = t1 + t2 + t3;
        t1 = kenobiY;
        t2 = ex[1] * xval;
        t3 = ey[1] * yval;
        tripty = t1 + t2 + t3;
        return new Position(triptx, tripty);
    }

    @Override
    public String getMessage(List<Satellite> satellites) throws Exception {
        List<String> strKenobi = new ArrayList<>();
        List<String> strSkywalker = new ArrayList<>();
        List<String> strSato = new ArrayList<>();

        int flag = 0;

        for (int i = 0; i < 3; i++) {
            Satellite satellite = satellites.get(i);

            if (satellite.getName().equalsIgnoreCase(Constants.SATELLITE_KENOBI)) {
                strKenobi = satellite.getMessage();
                flag++;
            }

            if (satellite.getName().equalsIgnoreCase(Constants.SATELLITE_SKYWALKER)) {
                strSkywalker = satellite.getMessage();
                flag++;
            }

            if (satellite.getName().equalsIgnoreCase(Constants.SATELLITE_SATO)) {
                strSato = satellite.getMessage();
                flag++;
            }

        }

        if (flag != 3) {
            throw new Exception(Constants.MSG_INDETERMINATE_MESSAGE);
        }

        return getMessage(strKenobi, strSkywalker, strSato);
    }

    @Override
    public String getMessage(List<String> kenobi, List<String> skywalker, List<String> sato) throws Exception {
        StringBuilder sb = new StringBuilder();

        if (kenobi.size() == skywalker.size() && kenobi.size() == sato.size()) {
            int iterations = kenobi.size();

            for (int i = 0; i < iterations; i++) {
                String str = Constants.STR_EMPTY;

                if (kenobi.get(i) != null && !kenobi.get(i).equalsIgnoreCase(Constants.STR_EMPTY)) {
                    str = kenobi.get(i);
                }

                if (skywalker.get(i) != null && !skywalker.get(i).equalsIgnoreCase(Constants.STR_EMPTY)) {

                    if (!str.equalsIgnoreCase(Constants.STR_EMPTY) && !str.equalsIgnoreCase(skywalker.get(i))) {
                        throw new Exception(Constants.MSG_INDETERMINATE_MESSAGE);
                    }

                    str = skywalker.get(i);
                }

                if (skywalker.get(i) != null && !skywalker.get(i).equalsIgnoreCase(Constants.STR_EMPTY)) {

                    if (!str.equalsIgnoreCase(Constants.STR_EMPTY) && !str.equalsIgnoreCase(skywalker.get(i))) {
                        throw new Exception();
                    }

                    str = skywalker.get(i);
                }

                if (skywalker.get(i) != null && !skywalker.get(i).equalsIgnoreCase(Constants.STR_EMPTY)) {

                    if (!str.equalsIgnoreCase(Constants.STR_EMPTY) && !str.equalsIgnoreCase(skywalker.get(i))) {
                        throw new Exception(Constants.MSG_INDETERMINATE_MESSAGE);
                    }

                    str = skywalker.get(i);
                }

                if (sato.get(i) != null && !sato.get(i).equalsIgnoreCase(Constants.STR_EMPTY)) {

                    if (!str.equalsIgnoreCase(Constants.STR_EMPTY) && !str.equalsIgnoreCase(sato.get(i))) {
                        throw new Exception(Constants.MSG_INDETERMINATE_MESSAGE);
                    }

                    str = sato.get(i);
                }

                if (!str.equalsIgnoreCase(Constants.STR_EMPTY)) {
                    sb.append(str);
                    sb.append(Constants.STR_SPACE);
                } else {
                    throw new Exception(Constants.MSG_INDETERMINATE_MESSAGE);
                }
            }

        } else {
            throw new Exception(Constants.MSG_INDETERMINATE_MESSAGE);
        }

        return sb.toString();
    }
}
