
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import javacarcel.Carcel;
import javax.swing.JTextField;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author xp
 */
public class JAXB {
    
    Carcel miCarcel;
     
   
    
    public int abrir_XML_JAXB(File fichero) {
        JAXBContext contexto;
        try {
            //Crea una instancia JAXB
            contexto = JAXBContext.newInstance(Carcel.class);
            //Crea un objeto Unmarsheller.
            Unmarshaller u = contexto.createUnmarshaller();
            //Deserializa (unmarshal) el fichero
            miCarcel = (Carcel) u.unmarshal(fichero);
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public String recorrerJAXByMostrar() {
        String datos_nodo[] = null;
        String cadena_resultado = "";
        //Crea una lista con objetos de tipo libro.
        List<Carcel.Preso> lCarcel = miCarcel.getPreso();
        //Recorre la lista para sacar los valores.
        for (int i = 0; i < lCarcel.size(); i++) {
            cadena_resultado = cadena_resultado + "\n" + "Código de Preso: " + lCarcel.get(i).getCodigoPreso();
            cadena_resultado = cadena_resultado + "\n" + "El DNI es: " + lCarcel.get(i).getDNI();   
            cadena_resultado = cadena_resultado + "\n" + "El Nombre es: " + lCarcel.get(i).getNombre();
            cadena_resultado = cadena_resultado + "\n" + "El Apellido es: " + lCarcel.get(i).getApellidos();
            cadena_resultado = cadena_resultado + "\n" + "La Fecha De Nacimiento es: " + lCarcel.get(i).getFechaNacimiento();
            cadena_resultado = cadena_resultado + "\n" + "La Condena se realizo en: " + lCarcel.get(i).getCondenadoEn();
            cadena_resultado = cadena_resultado + "\n" + "La Nacionalidad es: " + lCarcel.get(i).getNacionalidad();
            cadena_resultado = cadena_resultado + "\n" + "La Altura es: " + lCarcel.get(i).getAltura();
            cadena_resultado = cadena_resultado + "\n" + "El Color de Piel es: " + lCarcel.get(i).getColorPiel();
            cadena_resultado = cadena_resultado + "\n" + "Su Banda es: " + lCarcel.get(i).getBandaAsociacion();
            cadena_resultado = cadena_resultado + "\n ----------------------------------";
        }
        return cadena_resultado;
    }
    
    public void cambiarDato(String presoNombre, String nuevoNombre, String cambio){
        JAXBContext contexto; 
        String datos_nodo[] = null;
        String cadena_resultado = "";
        //Crea una lista con objetos de tipo libro.
        List<Carcel.Preso> lCarcel = miCarcel.getPreso();
        switch(cambio){
            case "DNI": 
                for (int i = 0; i < lCarcel.size(); i++) {
            if(lCarcel.get(i).getCodigoPreso().equals(presoNombre.trim())){
                lCarcel.get(i).setDNI(nuevoNombre.trim());
                 }
            } ; break;
            case "Nombre": 
                for (int i = 0; i < lCarcel.size(); i++) {
            if(lCarcel.get(i).getCodigoPreso().equals(presoNombre.trim())){
                lCarcel.get(i).setNombre(nuevoNombre.trim());
                 }
            } ; break;
            case "Apellidos": 
                for (int i = 0; i < lCarcel.size(); i++) {
            if(lCarcel.get(i).getCodigoPreso().equals(presoNombre.trim())){
                lCarcel.get(i).setApellidos(nuevoNombre.trim());
                 }
            } ; break;
            case "Fecha Nacimiento": 
                for (int i = 0; i < lCarcel.size(); i++) {
            if(lCarcel.get(i).getCodigoPreso().equals(presoNombre.trim())){
                lCarcel.get(i).setFechaNacimiento(nuevoNombre.trim());
                 }
            } ; break;
            case "Condenado en": 
                for (int i = 0; i < lCarcel.size(); i++) {
            if(lCarcel.get(i).getCodigoPreso().equals(presoNombre.trim())){
                lCarcel.get(i).setCondenadoEn(nuevoNombre.trim());
                 }
            } ; break;
            case "Nacionalidad": 
                for (int i = 0; i < lCarcel.size(); i++) {
            if(lCarcel.get(i).getCodigoPreso().equals(presoNombre.trim())){
                lCarcel.get(i).setNacionalidad(nuevoNombre.trim());
                 }
            } ; break;
            case "Altura": 
                for (int i = 0; i < lCarcel.size(); i++) {
            if(lCarcel.get(i).getCodigoPreso().equals(presoNombre.trim())){
                lCarcel.get(i).setAltura(nuevoNombre.trim());
                 }
            } ; break;
            case "Color de Piel": 
                for (int i = 0; i < lCarcel.size(); i++) {
            if(lCarcel.get(i).getCodigoPreso().equals(presoNombre.trim())){
                lCarcel.get(i).setColorPiel(nuevoNombre.trim());
                 }
            } ; break;
            case "Banda": 
                for (int i = 0; i < lCarcel.size(); i++) {
            if(lCarcel.get(i).getCodigoPreso().equals(presoNombre.trim())){
                lCarcel.get(i).setBandaAsociacion(nuevoNombre.trim());
                 }
            } ; break;
            
        }
        //Recorre la lista para sacar los valores.
        
           
       try {
            
           JAXBContext context = JAXBContext.newInstance(Carcel.class);
    Marshaller m = context.createMarshaller();
    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    m.marshal(miCarcel, new FileOutputStream("Carcel.xml"));
        } catch (Exception ex) {
            ex.printStackTrace();
           
        }
    }
    
   
}
