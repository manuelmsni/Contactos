/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.contactos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vespertino
 */
public class Modelo { // Datos y lógica de negocio
    
    private Agenda agenda;
    
    private Persistencia persistencia;
    
    private Modelo() {
        agenda = new Agenda();
    }
    
    public static Modelo newModeloDOM(String ruta){
        Modelo m = new Modelo();
        m.setPersistencia(new XML_DOM_Parser(ruta));
        return m;
    }
    
    public static Modelo newModeloSAX(String ruta){
        Modelo m = new Modelo();
        m.setPersistencia(new XML_SAX_Parser(ruta));
        return m;
    }
    
    public static Modelo newModeloStAX(String ruta){
        Modelo m = new Modelo();
        m.setPersistencia(new XML_StAX_Parser(ruta));
        return m;
    }
    
    private void setPersistencia(Persistencia p){
        persistencia = p;
    }
    
    public boolean cagaContactosDesde(boolean sustituir){
        
        //Transformo los datos de disco en un Array de Contactos
        Contacto[] contactos = persistencia.parsear_XML_A_Contactos();
        if(contactos == null) return false;
        
        if(sustituir) agenda.reseteaAgenda();
        
        for(Contacto c: contactos){
            agenda.addContacto(c);
        }
        
        return true;
    }
    
    public boolean guardaContactos(){
        int size = agenda.getSize();
        if(size == 0){
            System.out.println("La lista de contactos está vacía");
            return false;
        }
        persistencia.parsear_Contactos_A_XML(agenda.getContactos());
        return true;
    }
    
    public boolean registraContacto(Contacto c){
        return agenda.addContacto(c);
    }
    
    public boolean modificaContacto(int telefono, String nuevoNombre, int nuevoTelefono){
        Contacto viejo = agenda.getContactoPorTelefono(telefono);
        if(viejo == null) return false;
        return agenda.modificaContacto(viejo, nuevoNombre, nuevoTelefono);
    }
    
    public void listaContactos(){
        System.out.print(agenda.toString());
    }
    
}
