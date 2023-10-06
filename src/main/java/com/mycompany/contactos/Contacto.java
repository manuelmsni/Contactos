/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.contactos;

import java.util.Objects;

/**
 *
 * @author Vespertino
 */
public class Contacto {
    
    String nombre;
    int telefono;
    
    public Contacto(String nombre, int telefono){
        this.nombre = nombre;
        this.telefono = telefono;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public int getTelefono(){
        return telefono;
    }
    
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contacto contacto = (Contacto) o;
        return telefono == contacto.telefono;
    }
    
    public int hashCode() {
        return Objects.hash(telefono);
    }
    
    public String toString() {
        return "Contacto{" +
                "nombre='" + nombre + '\'' +
                ", telefono=" + telefono +
                '}';
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public void setTelefono(int telefono){
        this.telefono = telefono;
    }
}