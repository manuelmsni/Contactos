/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.contactos;

import java.util.ArrayList;
import javax.swing.AbstractListModel;

/**
 *
 * @author Vespertino
 */
public class Agenda extends AbstractListModel<Contacto>{
    
    ArrayList<Contacto> contactos;
    
    public Agenda() {
        reseteaAgenda();
    }
    
    public Contacto[] getContactos(){
        if(contactos.size() == 0){
            return null;
        }
        Contacto[] temp = new Contacto[contactos.size()];
        return contactos.toArray(temp);
    }
    
    public boolean reseteaAgenda(){
        if(contactos!=null)if(contactos.size()>0)return false;
        this.contactos = new ArrayList<Contacto>();
        actualiza();
        return true;
    }
    
    public Contacto getContactoPorTelefono(int telefono){
        for(Contacto c: contactos){
            if(c.getTelefono() == telefono) return c;
        }
        return null;
    }
    
    public boolean modificaContacto(Contacto c, String nombre, int telefono){
        if(c.getTelefono() != telefono && getContactoPorTelefono(telefono) != null){
            return false;
        }
        boolean changed = false;
        if(!c.getNombre().equals(nombre)){
            c.setNombre(nombre);
            changed = true;
        }
        if(c.getTelefono() != telefono){
            c.setTelefono(telefono);
            changed = true;
        }
        if(changed){
            actualiza();
            return true;
        }
        return false;
    }
    
    public boolean addContacto(Contacto c){
        if(c == null) return false;
        if(contactos.indexOf(c) == -1){
            contactos.add(c);
            actualiza();
            return true;
        }
        return false;
    }
    
    public Contacto buscaContactoPorTelefono(int telefono){
        int pos = contactos.indexOf(new Contacto("", telefono));
        if(pos == -1)return null;
        return contactos.get(pos);
    }
    
    public Contacto getElementAt(int index){
        return contactos.get(index);
    }
    
    public int getSize(){
        return contactos.size();
    }
    
    public void actualiza(){
        fireContentsChanged(this, 0, getSize() - 1);
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        String salto = System.getProperty("line.separator");
        sb.append("\nContactos\n========="+salto);
        for(Contacto c: contactos){
            sb.append(c.toString()+salto);
        }
        return sb.toString();
    }
}
