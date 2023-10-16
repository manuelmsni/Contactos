/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.contactos;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author Vespertino
 */
public interface Persistencia {

    public Contacto[] recuperaContactos();
    
    public boolean guardaContactos(Contacto[] contactos);
    
    /*
    private static String leeFichero(String filePath){
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(filePath));
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
   private static boolean guardarDatos(String datos){
        try (FileWriter escritor = new FileWriter("datos.txt")) {
            escritor.write(datos);
            System.out.println("Los contactos se han guardado.");
            return true;
        } catch (IOException e) {
            System.err.println("Hubo un error al escribir en el archivo: " + e.getMessage());
        }
        return false;
    }
    */
}
