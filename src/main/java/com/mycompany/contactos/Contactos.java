/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.contactos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author Vespertino
 */
public class Contactos {
    
    public static void main(String[] args){
        
        Properties propiedades = new Properties();

        try (InputStream archivoEntrada = new FileInputStream("configuracion.conf")) {
            propiedades.load(archivoEntrada);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        String persistencia = propiedades.getProperty("Persistencia");
        String rutaXML = propiedades.getProperty("RutaGuardadoXML");
        String rutaTXT = propiedades.getProperty("RutaGuardadoTXT");
        String rutaBIN = propiedades.getProperty("RutaGuardadoObjetos");
        
        Modelo m = null;
        
        switch(persistencia){
            case "DOM":
                m = Modelo.newModeloDOM(rutaXML);
                break;
            case "SAX":
                m = Modelo.newModeloSAX(rutaXML);
                break;
            case "JAXB":
                m = Modelo.newModeloJAXB(rutaXML);
                break;
            case "Obj":
                m = Modelo.newModeloObjetos(rutaBIN);
                break;
            case "TXT":
                m = Modelo.newModeloTXT(rutaTXT);
                break;
            default:
                m = Modelo.newModeloDOM(rutaXML);
                break;
        }
        
        Controlador c = new Controlador(m, new Vista());
        
        /*
        try {
            OutputStream archivoSalida = new FileOutputStream("configuracion.conf");
            propiedades.store(archivoSalida, "Configuración del programa");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        */
        
    }
}