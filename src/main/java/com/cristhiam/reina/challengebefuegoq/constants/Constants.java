/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cristhiam.reina.challengebefuegoq.constants;

/**
 *
 * @author Cristhiam Reina <cristiansrc@gmail.com>
 */
public class Constants {
    
    //Nombre de los satelites permitidos
    public static final String SATELLITE_KENOBI = "kenobi";
    public static final String SATELLITE_SKYWALKER = "skywalker";
    public static final String SATELLITE_SATO = "sato";
    
    //Mensajes
    public static final String MSG_INDETERMINATE_MESSAGE = "No se puede determinar la posicion";
    public static final String MSG_DETERMINATE_MASSAGE = "El mensaje y la posicion se determinaron.";
    public static final String MSG_INVALID_SATELLITE = "La informacion enviada no corresponde a un satelite valido";
    public static final String MSG_CORRECT_SATELLITE_INCOMPLETE_INFORMATION = "Se recibio la informacion de un satelite correctamente, pero no hay suficiente informacion para para determinar el mensaje y la ubicacion.";
    public static final String MSG_INCOMPLETE_INFORMATION = "No hay suficiente informacion para determinar el mensaje y la ubicacion del satelite.";
    public static final String MSG_DELETE_SUCCESS = "La informacion del satelite enviado se elimino correctamente";
    public static final String MSG_SATELLITE_NOT_EXIST = "El satelite enviado no se encontro";
    public static final String MSG_DELETE_SUCCESS_ALL = "La informacion de todos los satelites se elimino correctamente.";
    
    //String basics
    public static final String STR_EMPTY = "";
    public static final String STR_SPACE = " ";

    private Constants() {}
}
