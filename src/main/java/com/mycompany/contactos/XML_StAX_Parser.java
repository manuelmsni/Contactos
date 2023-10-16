/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.contactos;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 *
 * @author manuelmsni
 */
public class XML_StAX_Parser implements Persistencia{
    
    private String ruta;
    
    public XML_StAX_Parser(String ruta){
        this.ruta = ruta;
    }
    
    public Contacto[] recuperaContactos() {
        List<Contacto> contactos = new ArrayList<>();

        try {
            FileInputStream fileInputStream = new FileInputStream(ruta);
            XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
            XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(fileInputStream);

            Contacto contacto = null;
            String elementoActual = "";

            while (xmlStreamReader.hasNext()) {
                int evento = xmlStreamReader.next();

                switch (evento) {
                    case XMLStreamConstants.START_ELEMENT:
                        elementoActual = xmlStreamReader.getLocalName();
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        String contenido = xmlStreamReader.getText().trim();
                        if (!contenido.isEmpty()) {
                            if ("nombre".equals(elementoActual)) {
                                contacto = new Contacto(contenido, 0);
                            } else if ("telefono".equals(elementoActual) && contacto != null) {
                                contacto.setTelefono(Integer.parseInt(contenido));
                            }
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        if ("Contacto".equals(xmlStreamReader.getLocalName()) && contacto != null) {
                            contactos.add(contacto);
                        }
                        break;
                }
            }

            xmlStreamReader.close();
            fileInputStream.close();
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }
        System.out.println("\nContactos cargados mediante StAX.\n");
        return contactos.toArray(new Contacto[0]);
    }
        
    public boolean guardaContactos(Contacto[] contactos) {
        try {
            OutputStream outputStream = new FileOutputStream(ruta);
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
            XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(outputStream);

            xmlStreamWriter.writeStartDocument("UTF-8", "1.0");
            xmlStreamWriter.writeStartElement("Contactos");

            for (Contacto contacto : contactos) {
                xmlStreamWriter.writeStartElement("Contacto");

                xmlStreamWriter.writeStartElement("nombre");
                xmlStreamWriter.writeCharacters(contacto.getNombre());
                xmlStreamWriter.writeEndElement();

                xmlStreamWriter.writeStartElement("telefono");
                xmlStreamWriter.writeCharacters(String.valueOf(contacto.getTelefono()));
                xmlStreamWriter.writeEndElement();

                xmlStreamWriter.writeEndElement(); // Cerrar "Contacto"
            }

            xmlStreamWriter.writeEndElement(); // Cerrar "Contactos"
            xmlStreamWriter.writeEndDocument();

            xmlStreamWriter.flush();
            xmlStreamWriter.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("\nContactos guardados mediante StAX.\n");
        return true;
    }
}
