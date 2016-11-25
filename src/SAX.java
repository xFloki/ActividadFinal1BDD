
import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author xp
 */
public class SAX {

    ManejadorSAX sh;
    File ficheroXML;
    SAXParser parser;

    public int abrir_XML_SAX(File fichero) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
//Se crea un objeto SAXParser para interpretar el documento XML.
            parser = factory.newSAXParser();
//Se crea una instancia del manejador que será el que recorra
//el documento XML secuencialmente
            sh = new ManejadorSAX();
            ficheroXML = fichero;
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    class ManejadorSAX extends DefaultHandler {

        int ultimoelement;
        String cadena_resultado = "";

        public ManejadorSAX() {
            ultimoelement = 0;
        }

        @Override
        public void startElement(String uri, String localName, String qName,
                Attributes atts) throws SAXException {
            if (qName.equals("Carcel")) {
                cadena_resultado = cadena_resultado + "\n **************************** \n Cargando Presos Encontrados: \n **************************** ";
               
            }
            // Usamos el metodo trim en los string para que nos elimine los espacios en blancos que tenga antes y despues del contenido 
            // de esta manera podemos ordenar el texto mejor simplemente usando un unico salto de linea
            else if (qName.equals("Preso")) {
                cadena_resultado = cadena_resultado.trim() + "\n\nC�digo de preso: " + atts.getValue(atts.getQName(0)) + "\n ";
                ultimoelement = 1;
            } else if (qName.equals("DNI")) {
                ultimoelement = 2;
                cadena_resultado = cadena_resultado.trim() + "\nEl DNI es: ";
            } else if (qName.equals("Nombre")) {
                ultimoelement = 3;
                cadena_resultado = cadena_resultado.trim() + "\nEl nombre es: ";
            } else if (qName.equals("Apellidos")) {
                ultimoelement = 4;
                cadena_resultado = cadena_resultado.trim() + "\nEl apellido es: ";
            } else if (qName.equals("Fecha_nacimiento")) {
                ultimoelement = 5;
                cadena_resultado = cadena_resultado.trim() + "\nFecha nacimiento: ";
            } else if (qName.equals("Condenado_en")) {
                ultimoelement = 6;
                cadena_resultado = cadena_resultado.trim() + "\nCondenado en: ";
            } else if (qName.equals("Nacionalidad")) {
                ultimoelement = 7;
                cadena_resultado = cadena_resultado.trim() + "\nNacionalidad: ";
            } else if (qName.equals("Altura")) {
                ultimoelement = 8;
                cadena_resultado = cadena_resultado.trim() + "\nAltura: ";
            } else if (qName.equals("ColorPiel")) {
                ultimoelement = 9;
                cadena_resultado = cadena_resultado.trim() + "\nColor de piel: ";
            } else if (qName.equals("BandaAsociacion")) {
                ultimoelement = 10;
                cadena_resultado = cadena_resultado.trim() + "\nBanda: ";
            }
            
        }

//Cuando en este ejemplo se detecta el final de un elemento <libro>,
//se pone un línea discontinua en la salida.
        @Override
        public void endElement(String uri, String localName, String qName)
                throws SAXException {
            if (qName.equals("Preso")) {
                System.out.println("He encontrado el final de un elemento.");
                cadena_resultado = cadena_resultado.trim() + "\n -------------------";
            }
        }

//Cuando se detecta una cadena de texto posterior a uno de los elementos
//<titulo> o <autor> entonces guarda ese texto en la variable correspondiente.
        @Override
        public void characters(char[] ch, int start, int length) throws
                SAXException {
            if (ultimoelement == 2) {
                for (int i = start; i < length + start; i++) {
                    cadena_resultado = cadena_resultado + ch[i];
                }
            } else if (ultimoelement == 3) {
                for (int i = start; i < length + start; i++) {
                    cadena_resultado = cadena_resultado + ch[i];
                }
            } else if (ultimoelement == 4) {
                for (int i = start; i < length + start; i++) {
                    cadena_resultado = cadena_resultado + ch[i];
                }
            } else if (ultimoelement == 5) {
                for (int i = start; i < length + start; i++) {
                    cadena_resultado = cadena_resultado + ch[i];
                }
            } else if (ultimoelement == 6) {
                for (int i = start; i < length + start; i++) {
                    cadena_resultado = cadena_resultado + ch[i];
                }
            } else if (ultimoelement == 7) {
                for (int i = start; i < length + start; i++) {
                    cadena_resultado = cadena_resultado + ch[i];
                }
            } else if (ultimoelement == 8) {
                for (int i = start; i < length + start; i++) {
                    cadena_resultado = cadena_resultado + ch[i];
                }
            } else if (ultimoelement == 9) {
                for (int i = start; i < length + start; i++) {
                    cadena_resultado = cadena_resultado + ch[i];
                }
            } else if (ultimoelement == 10) {
                for (int i = start; i < length + start; i++) {
                    cadena_resultado = cadena_resultado + ch[i];
                }
            }
            
        }
    }

    public String recorrerSAX(File fXML, ManejadorSAX sh, SAXParser parser) {
        try {
            parser.parse(fXML, sh);
            return sh.cadena_resultado;
        } catch (SAXException e) {
            e.printStackTrace();
            return "Error al parsear con SAX";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al parsear con SAX";
        }
    }
}
