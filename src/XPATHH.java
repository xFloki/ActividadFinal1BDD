import java.io.File;
import java.io.FileInputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author xp
 */
public class XPATHH {

    public String EjecutaXPath(File archivo, String consulta) {
        String salida = "";
        Node node;
        String datos_nodo[] = null;
        try {
//Crea un objeto DocumentBuilderFactory para el DOM (JAXP)
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//Crear un árbol DOM (parsear) con el archivo LibrosXML.xml
            Document XMLDoc = factory.newDocumentBuilder().parse(archivo);
//Crea el objeto XPath
            XPath xpath = XPathFactory.newInstance().newXPath();
//Crea un XPathExpression con la consulta deseada
            XPathExpression exp = xpath.compile(consulta);
//Ejecuta la consulta indicando que se ejecute sobre el DOM y que devolverá
//el resultado como una lista de nodos.
            Object result = exp.evaluate(XMLDoc, XPathConstants.NODESET);
            NodeList nodeList = (NodeList) result;
//Ahora recorre la lista para sacar los resultados
            for (int i = 0; i < nodeList.getLength(); i++) {
                node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    //Es un nodo libro
                    if (node.getNodeName() =="Preso") {

                        datos_nodo = procesarLibro(node);
                        salida = salida + "\n " + "Codigo preso: " + datos_nodo[0];
                        salida = salida + "\n " + "El DNI es: " + datos_nodo[1];
                        salida = salida + "\n " + "El Nombre es: " + datos_nodo[2];
                        salida = salida + "\n " + "El Apellido es: " + datos_nodo[3];
                        salida = salida + "\n " + "Fecha nacimiento: " + datos_nodo[4];
                        salida = salida + "\n " + "Condenado en: " + datos_nodo[5];
                        salida = salida + "\n " + "Nacionalidad: " + datos_nodo[6];
                        salida = salida + "\n " + "Altura: " + datos_nodo[7];
                        salida = salida + "\n " + "Color de piel: " + datos_nodo[8];
                        salida = salida + "\n " + "Banda: " + datos_nodo[9];                      
                        salida = salida + "\n ************************************";
                    }
                    if (node.getNodeName() == "DNI" ||
                            node.getNodeName() == "Nombre" ) {
                        
                        salida = salida + "\n" + node.getChildNodes().item(0).getTextContent();
                    }

                    

                }
                // else if(node.getNodeType()==Node.ATTRIBUTE_NODE){}
                else{
                    salida = salida + "\n" + node.getTextContent();
                }

            }

            return salida;
        } catch (Exception ex) {

            return "Error Fatal";

            ///Libros/Libro/Titulo[.='El capote'] 
        }

    }

    protected String[] procesarLibro(Node n) {
        String datos[] = new String[10];
        Node ntemp = null;
        int contador = 1;
//Obtiene el valor del primer atributo del nodo (uno en este ejemplo)
        datos[0] = n.getAttributes().item(0).getNodeValue();
//Obtiene los hijos del Libro (titulo y autor)
        NodeList nodos = n.getChildNodes();
        for (int i = 0; i < nodos.getLength(); i++) {
            ntemp = nodos.item(i);
            if (ntemp.getNodeType() == Node.ELEMENT_NODE) {
//IMPORTANTE: para obtener el texto con el tÃ­tulo y autor se accede al
// nodo TEXT hijo de ntemp y se saca su valor.
                datos[contador] = ntemp.getChildNodes().item(0).getNodeValue();
                contador++;
            }
        }
        return datos;
    }
}