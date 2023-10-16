/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.contactos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Vespertino
 */
public class Object_Parser implements Persistencia{
    
    private String ruta;
    
    public Object_Parser(String ruta){
        this.ruta = "datos.bin";
    }
    
    public Contacto[] recuperaContactos() {
        if(ruta == null || ruta.isBlank())return null;
        Contacto[] contactos = null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
            contactos = (Contacto[]) ois.readObject();
            System.out.println("\nContactos deserializados..\n");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return contactos;
    }
    
    public boolean guardaContactos(Contacto[] contactos) {
        if (contactos == null || contactos.length == 0) return false;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta))) {
            oos.writeObject(contactos);
            System.out.println("\nContactos serializados y guardados..\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}