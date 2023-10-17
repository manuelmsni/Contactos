/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.contactos;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author manuelmsni
 */
public class XML_SAX_Parser implements Persistencia{
    
    private String ruta;
    
    public XML_SAX_Parser(String ruta){
        this.ruta = ruta;
    }

    public Contacto[] recuperaContactos() {
        List<Contacto> contactos = new ArrayList<>();

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {
                private String elementoActual;
                private Contacto contacto;

                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    elementoActual = qName;
                    if ("Contacto".equals(qName)) {
                        contacto = new Contacto();
                    } 
                }

                public void characters(char[] ch, int start, int length) throws SAXException {
                    String contenido = new String(ch, start, length).trim();
                    if (!contenido.isEmpty()) {
                        if ("nombre".equals(elementoActual)) {
                            contacto.setNombre(contenido);
                        } else if ("telefono".equals(elementoActual)) {
                            contacto.setTelefono(Integer.parseInt(contenido));
                        }
                    }
                }

                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if ("Contacto".equals(qName)) {
                        contactos.add(contacto);
                        contacto = null;
                    }
                }
            };

            FileInputStream fileInputStream = new FileInputStream(ruta);
            saxParser.parse(fileInputStream, handler);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\nContactos cargados mediante SAX.\n");
        return contactos.toArray(new Contacto[0]);
    }

    public boolean guardaContactos(Contacto[] contactos) {
        XML_DOM_Parser persistencia = new XML_DOM_Parser(ruta);
        System.out.println("\nSe ha empleado DOM para guardar los contactos.\n");
        return persistencia.guardaContactos(contactos);
    }
    
}
