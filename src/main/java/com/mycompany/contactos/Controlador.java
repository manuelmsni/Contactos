/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.contactos;

/**
 *
 * @author Vespertino
 */
public class Controlador {
    
    Modelo mod;
    Vista vis;

    public Controlador(){
        mod = new Modelo();
        vis = new Vista();
        bucleDePrograma();
    }
    
    public void bucleDePrograma(){
        boolean continuar;
        do{
            continuar = gestionaMenu();
        }while(continuar);
    }
    
    private boolean gestionaMenu(){
        int option = vis.menu();
        switch(option){
            case 1: // Cargar contactos
                cargaContactos();
                break;
            case 2: // Crea contacto
                creaContacto();
                break;
            case 3: // Actualizar contacto
                actualizaContacto();
                break;
            case 4: // Borrar contacto

                break;
            case 5: // Listar contactos
                mod.listaContactos();
                break;
            case 6: // Guardar contactos
                mod.guardaContactos();
                break;
            case 666: // Salir
                return false;
            default: // No es una opción válida
                System.out.println("Introduce una opción válida.");
                return true;
        }
        return true;
    }
        
    private void cargaContactos(){
        boolean sustituir = vis.solicitaBooleano("¿Deseas sustituir los contactos existentas por los del archivo?");
        boolean contactos = mod.cagaContactosDesde(sustituir);
        if(contactos == false){
            System.out.println("El archivo no contiene datos de Contactos válidos.");
        } else {
            System.out.println("Se han añadido los contactos.");
        }
        
    }
    
    private void creaContacto(){
        Contacto temp;
        do{
            temp = vis.creaContacto();
            if(temp == null){
                System.out.println("No se ha podido crear el contacto.");
            }else if(!mod.registraContacto(temp)){
                System.out.println("Ese número ya ha sido registrado anteriormente.");
                temp = null;
            }
        }while(temp == null);
    }
    
    public void actualizaContacto(){
        int num = vis.solicitaEntero("Introduce el número del contacto a modificar:");
        String nuevoNombre = vis.solicitaString("Introduce el nuevo nombre del contacto:");
        int nuevoNum = vis.solicitaEntero("Introduce el nuevo número del contacto:");
        boolean cambiado = mod.modificaContacto(num, nuevoNombre, nuevoNum);
        if(!cambiado){
            System.out.println("No se ha podido modificar el contacto.");
        }else{
            System.out.println("Contacto modificado");
        }
    }

}