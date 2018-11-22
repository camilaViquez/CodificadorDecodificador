package Modelo;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import libcomunicacion.DTOTraduccion;
import org.xml.sax.SAXException;

/**
 * Created by Sebastian on 9/12/2018.
 */
public class GuardaXML implements IEscribir {
    
    
    /**
     * Metodo que guarda la frase de salida en un XML
     * @param texto datos a guardar
     * @return estado de realizacion
     */
    @Override
    public boolean guardarTraduccion(DTOTraduccion texto){
        String path ="C:/Users/Sebastian/Desktop/CodificadorDecodificar/Bitacor"
                + "a/DAOXml.xml";
        try {
            File f  = null;
            try{
                f = new File(path);
            }catch (Exception e){
                f  = null;
            }
            com.itextpdf.text.Document document = new com.itextpdf.text.Document();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc;
            if (f.exists() && !f.isDirectory()) {
                doc = builder.parse(new FileInputStream("C:/Users/Sebastian/Desk"
                        + "top/CodificadorDecodificar/Bitacora/DAOXml.xml"));
                Node root = doc.getFirstChild();
                //Element rootElement = doc.createElement("BitacoraXML");
                //doc.appendChild(rootElement);
                // body elements
                Element body = doc.createElement("Elemento");
                root.appendChild(body);
                Element fecha = doc.createElement("Fecha");
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); //define formato para fecha y hora
                fecha.appendChild(doc.createTextNode(sdf.format(cal.getTime())));
                body.appendChild(fecha);
                Element salida = doc.createElement("Salida");
                salida.appendChild(doc.createTextNode(String.valueOf(texto.getAlfabeto())));
                body.appendChild(salida);
            }else{
                doc = builder.newDocument();
                Element root = doc.createElement("Salida");
                doc.appendChild(root);
            }
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("C:/Users/Sebastian/"
                    + "Desktop/CodificadorDecodificar/Bitacora/DAOXml.xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);
            System.out.println("File saved!");
        } catch (ParserConfigurationException | TransformerException | SAXException | IOException ex) {
            Logger.getLogger(GuardaXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}