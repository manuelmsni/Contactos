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

    public Contacto[] parsear_XML_A_Contactos() {
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
                        contacto = new Contacto("", 0);
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

    public boolean parsear_Contactos_A_XML(Contacto[] contactos) {
        try {
            OutputStream outputStream = new FileOutputStream(ruta);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));

            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            writer.newLine();
            writer.write("<Contactos>");
            writer.newLine();

            for (Contacto contacto : contactos) {
                writer.write("  <Contacto>");
                writer.newLine();

                writer.write("    <nombre>" + contacto.getNombre() + "</nombre>");
                writer.newLine();
                writer.write("    <telefono>" + contacto.getTelefono() + "</telefono>");
                writer.newLine();

                writer.write("  </Contacto>");
                writer.newLine();
            }

            writer.write("</Contactos>");
            writer.newLine();
            writer.flush();
            writer.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("\nContactos guardados mediante SAX.\n");
        return true;
    }
    
}
