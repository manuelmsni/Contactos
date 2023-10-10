/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.contactos;

import java.io.File;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import java.io.StringWriter;
import javax.xml.transform.stream.StreamResult;

/**
 *
 * @author Vespertino
 */
public class XML_DOM_Parser implements Persistencia{
    
    private String ruta;
    
    public XML_DOM_Parser(String ruta){
        this.ruta = ruta;
    }
    
    // La desventaja de DOM es que carga todo en memoria
    public Contacto[] parsear_XML_A_Contactos() {
        if(ruta == null || ruta.isBlank())return null;
        
        // Crea una fábrica de documentos
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        Document documento = null;
        try{
              documento = dbFactory.newDocumentBuilder().parse(new File(ruta));
        }catch(Exception e){
              return null;
        }
        // Obtén la lista de nodos <Contacto>
        NodeList listaContactos = documento.getElementsByTagName("Contacto");
        int numContactos = listaContactos.getLength();
        if(numContactos == 0) return null;
        // Declara e instancia el array de salida vacío
        Contacto[] contactos = new Contacto[numContactos];
        // Recorre los nodos <Contacto> y crea objetos Contacto
        for (int i = 0; i < numContactos; i++) {
              Element elementoContacto = (Element) listaContactos.item(i);
              String nombre = elementoContacto.getElementsByTagName("nombre").item(0).getTextContent();
              int telefono = Integer.parseInt(elementoContacto.getElementsByTagName("telefono").item(0).getTextContent());
              contactos[i] = new Contacto(nombre, telefono);
        }
        System.out.println("\nContactos cargados mediante DOM.\n");
        return contactos;
    }
    
    public boolean parsear_Contactos_A_XML(Contacto[] contactos) {
        if (contactos == null || contactos.length == 0) {
            return false;
        }
        try {
            // Crea una fábrica de documentos
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();

            // Crea un documento XML
            Document doc = docBuilder.newDocument();

            // Crea el elemento raíz
            Element rootElement = doc.createElement("Contactos");
            doc.appendChild(rootElement);

            // Recorre el array de Contactos y crea elementos XML para cada uno
            for (Contacto contacto : contactos) {
                Element contactoElement = doc.createElement("Contacto");

                // Crea elementos para nombre y teléfono y asigna valores
                Element nombreElement = doc.createElement("nombre");
                nombreElement.appendChild(doc.createTextNode(contacto.getNombre()));
                contactoElement.appendChild(nombreElement);

                Element telefonoElement = doc.createElement("telefono");
                telefonoElement.appendChild(doc.createTextNode(String.valueOf(contacto.getTelefono())));
                contactoElement.appendChild(telefonoElement);

                // Agrega el elemento de contacto al elemento raíz
                rootElement.appendChild(contactoElement);
            }

             // Convierte el documento XML a una cadena
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(ruta));
            transformer.transform(source, result);

            System.out.println("\nContactos guardados mediante DOM.\n");
            return true;
        } catch (ParserConfigurationException | javax.xml.transform.TransformerException e) {
            e.printStackTrace();
            return false;
        }
    }

}