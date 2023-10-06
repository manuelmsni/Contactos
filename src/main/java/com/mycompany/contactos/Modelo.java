/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.contactos;

import java.util.ArrayList;

/**
 *
 * @author Vespertino
 */
public class Modelo { // Datos y lógica de negocio
    
    private Agenda agenda;
    
    public Modelo() {
        agenda = new Agenda();
    }
    
    public boolean cagaContactosDesde(boolean sustituir){
        
        //Transformo los datos de disco en un Array de Contactos
        Contacto[] contactos = XML_Parser.parsearXmlAContactosMedianteDOM();
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
        XML_Parser.convertirContactosAXMLMedianteDOM(agenda.getContactos());
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
