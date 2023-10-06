/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.contactos;

import java.util.Scanner;

/**
 *
 * @author Vespertino
 */
public class Vista { //Gestiona la solicitud de datos y devuelve la información introducida por el usuario
    
    Scanner sc;
    
    public Vista(){
        sc = new Scanner(System.in);
    }
    
    /**
     * Entrada de datos / feedback para el Controlador
     * @param option Opción a seleccionar
     * @return Devuelve los códigos de error 
     */
    public int menu(){
        System.out.println("\nMenu\n----------------------------\n1: Cargar contactos\n2: Crea contacto\n3: Actualizar contacto\n4: Borrar contacto\n5: Listar contactos\n6: Guardar contactos\n666: Salir\n----------------------------\n");
        Integer respuesta = solicitaEntero("Introduce el entero correspondiente:");
        return respuesta;
    }
    
    public String solicitaRuta(){
        System.out.println("Introduce la ruta del archivo.");
        return pideDatos();
    }
    
    public Contacto creaContacto(){
        String nombre = solicitaString("Introduce el nombre:");
        int telefono = solicitaEntero("Introduce el teléfono:");
        return new Contacto(nombre , telefono);
    }
    
    public boolean solicitaBooleano(String mensaje){
        System.out.println(mensaje);
        System.out.println("Introduce Si / No");
        String respuesta = pideDatos();
        while(!(respuesta.equalsIgnoreCase("si") || respuesta.equalsIgnoreCase("no"))){
            System.out.println("No es una respuesta válida.\n\nIntroduce Si / No");
            respuesta = pideDatos();
        }
        if(respuesta.equalsIgnoreCase("si")){
            return true;
        }
        return false;
    }
    
    public String solicitaString(String mensaje){
        System.out.println(mensaje);
        return pideDatos();
    }
    
    public int solicitaEnteroEnRango(String mensaje, int min, int max){
        int salida;
        boolean estaEnElRango;
        do{
            salida = solicitaEntero(mensaje);
            estaEnElRango = min <= salida && salida <= max;
            if (!estaEnElRango) System.out.println("El número seleccionado no está en el rango [" + min + ", " + max + "], introduce un número en el rango, límites incluidos:");
        }while(!estaEnElRango);
        return salida;
    }
    
    public int solicitaEntero(String mensaje){
        System.out.println(mensaje);
        String respuesta = pideDatos();
        while(!respuesta.matches("-?\\d+")){
            System.out.println("No has introducido un entero válido, prueba otra vez.");
            respuesta = sc.nextLine();
        }
        return Integer.parseInt(respuesta);
    }
    
    private String pideDatos(){
        String respuesta = sc.nextLine();
        while(respuesta.isBlank()){
            System.out.println("No has introducido nada, prueba otra vez.");
            respuesta = sc.nextLine();
        }
        return respuesta;
    }
    
}
