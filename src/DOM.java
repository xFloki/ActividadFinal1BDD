
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.File;
import java.io.FileOutputStream;
import javax.swing.JFileChooser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author xp
 */
public class DOM {

    Document doc;

    public int abrir_XML_DOM(File fichero) {

        doc = null;
        //doc representa al árbol DOM
        try {
//Se crea un objeto DocumentBuiderFactory.
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//El modelo DOM no debe contemplar los comentarios que tenga el XML.
            factory.setIgnoringComments(true);
//Ignora los espacios en blanco que tenga el documento
            factory.setIgnoringElementContentWhitespace(true);
//Crea un objeto DocumentBuilder para cargar en él la estructura de árbol DOM
//a partir del XML seleccionado.
            DocumentBuilder builder = factory.newDocumentBuilder();
//Interpreta (parsear) el documento XML (File) y genera el DOM equivalente.
            doc = (Document) builder.parse(fichero);
//Ahora doc apunta al árbol DOM listo para ser recorrido.
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
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
//IMPORTANTE: para obtener el texto con el título y autor se accede al
// nodo TEXT hijo de ntemp y se saca su valor.
                datos[contador] = ntemp.getChildNodes().item(0).getNodeValue();
                contador++;
            }
        }
        return datos;
    }

    public String recorrerDOMyMostrar(Document doc) {
        String datos_nodo[] = null;
        String salida = "";
        Node node;
//Obtiene el primero nodo del DOM (primer hijo)
        Node raiz = doc.getFirstChild();
//Obtiene una lista de nodos con todos los nodos hijo del raíz.
        NodeList nodelist = raiz.getChildNodes();
//Procesa los nodos hijo
        for (int i = 0; i < nodelist.getLength(); i++) {
            node = nodelist.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
//Es un nodo libro
                datos_nodo = procesarLibro(node);
                salida = salida + "\n " + "Codigo preso: " + datos_nodo[0];
                salida = salida + "\n " + "El DNI es: " + datos_nodo[1];
                salida = salida + "\n " + "El Nombre es: " + datos_nodo[2];
                salida = salida + "\n " + "El Apellidos es: " + datos_nodo[3];
                salida = salida + "\n " + "Fecha Nacimiento: " + datos_nodo[4];
                salida = salida + "\n " + "Condenado en: " + datos_nodo[5];
                salida = salida + "\n " + "Nacionalidad: " + datos_nodo[6];
                salida = salida + "\n " + "Altura: " + datos_nodo[7];
                salida = salida + "\n " + "Color de Piel: " + datos_nodo[8];
                salida = salida + "\n " + "Banda: " + datos_nodo[9];
                salida = salida + "\n -------------------";
            }
        }
        return salida;
    }

    public int annadirDOM(String titulo, String autor, String anno) {
        try {
            //Se crea un nodo tipo Element con nombre ‘titulo’(<Titulo>)
            Node ntitulo = doc.createElement("Titulo");
            //Se crea un nodo tipo texto con el título del libro
            Node ntitulo_text = doc.createTextNode(titulo);
            //Se añade el nodo de texto con el título como hijo del elemento Titulo
            ntitulo.appendChild(ntitulo_text);
            //Se hace lo mismo que con título a autor (<Autor>)
            Node nautor = doc.createElement("Autor");
            Node nautor_text = doc.createTextNode(autor);
            nautor.appendChild(nautor_text);
            //Se crea un nodo de tipo elemento (<libro>)
            Node nlibro = doc.createElement("Libro");
            //Al nuevo nodo libro se le añade un atributo publicado_en
            ((Element) nlibro).setAttribute("publicado_en", anno);
            //Se añade a libro el nodo autor y titulo creados antes
            nlibro.appendChild(ntitulo);
            nlibro.appendChild(nautor);
            //Se obtiene el primer nodo del documento y a él se le añade como
            //hijo el nodo libro que ya tiene colgando todos sus hijos y atributos creados antes.
            Node raiz = doc.getChildNodes().item(0);
            raiz.appendChild(nlibro);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public int cambiarTitulo(String titulo, String nuevoTitulo){
        //NO FUNCIONA, EN PROGRESO  
        Node node;
        NodeList nodes = doc.getElementsByTagName("Titulo");
        for (int i = 0; i < nodes.getLength(); i++) {
            node = nodes.item(i);
            if(node.getTextContent().equalsIgnoreCase(titulo)){
                node.setTextContent(nuevoTitulo);
                return 0;
            }
   
        }
        return -1;
    }
    
    public int guardarDOMcomoFILE() {
        try{
        //Crea un fichero llamado salida.xml
        JFileChooser chooser = new JFileChooser();
        int retrival = chooser.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
              File archivoSeleccionado = chooser.getSelectedFile();
             
               File archivo_xml = new File(archivoSeleccionado + ".xml");
        //Especifica el formato de salida
        OutputFormat format = new OutputFormat(doc);
        //Especifica que la salida esté indentada.
        format.setIndenting(true);
        //Escribe el contenido en el FILE
        XMLSerializer serializer = new XMLSerializer(new
        FileOutputStream(archivo_xml) , format);
        serializer.serialize(doc);       
        
        }
       
       
        return 0;
        }
        catch(Exception e) {
         return -1;
        }
        }

}
