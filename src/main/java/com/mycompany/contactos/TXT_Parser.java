/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.contactos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author manuelmsni
 */
public class TXT_Parser implements Persistencia{
    
    private String ruta;
    
    public TXT_Parser(String ruta){
        this.ruta = ruta;
    }
    
    public Contacto[] recuperaContactos() {
        if(ruta == null || ruta.isBlank())return null;
        Contacto[] contactos;
        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
            String primeraLinea = reader.readLine();
            if (primeraLinea == null) {
                System.out.println("El archivo está vacío.");
                return null;
            }

            int numContactos = Integer.parseInt(primeraLinea);
            contactos = new Contacto[numContactos];

            for (int i = 0; i < numContactos; i++) {
                String linea = reader.readLine();
                String[] partes = linea.split(";");
                if (partes.length == 2) {
                    String nombre = partes[0];
                    int telefono = Integer.parseInt(partes[1]);
                    contactos[i] = new Contacto(nombre, telefono);
                }
            }
            System.out.println("Contactos deserializados.");
            return contactos;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean guardaContactos(Contacto[] contactos) {
        if (contactos == null || contactos.length == 0) return false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            writer.write(String.valueOf(contactos.length));
            writer.newLine();

            for (Contacto contacto : contactos) {
                writer.write(contacto.getNombre() + ";" + contacto.getTelefono());
                writer.newLine();
            }
            System.out.println("Contactos guardados correctamente.");
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
