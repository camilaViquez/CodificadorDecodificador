package Modelo;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import libcomunicacion.DTOTraduccion;

/**
 * Created by Sebastian on 9/12/2018.
 */
public class GuardaPDF implements IEscribir {
    
    /**
     * Metodo que guarda la frase de salida en un PDF
     * @param texto datos a guardar
     * @return estado de realizacion
     */
    @Override
    public boolean guardarTraduccion(DTOTraduccion texto){
        String path = "C:/Users/Sebastian/Desktop/CodificadorDecodificar/Bitacora/DAOPdf.pdf";
        File f  = null;
        try{
            f = new File(path);
        }catch (Exception e){
            f  = null;
        }
        Document document = new Document();
        if (f.exists() && !f.isDirectory()) {
            f.delete();
            try {
                PdfWriter.getInstance(document, new FileOutputStream(new File(path)));
                document.open();
                Paragraph p = new Paragraph();
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); //define formato para fecha y hora
                p.add("Fecha: " + sdf.format(cal.getTime()) + '\n');
                p.add(texto.getAlfabeto()+ '\n');
                p.setAlignment(Element.ALIGN_LEFT);
                document.add(p);
                document.close();
            } catch (DocumentException ex) {
                Logger.getLogger(GuardaPDF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GuardaPDF.class.getName()).log(Level.SEVERE, null, ex);
            } 
        } else {            
            try {
                PdfWriter.getInstance(document, new FileOutputStream(new File(path)));
                document.open();
                Paragraph p = new Paragraph();
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); //define formato para fecha y hora
                p.add("Fecha: " + sdf.format(cal.getTime()) + '\n');
                p.add(texto.getAlfabeto()+ '\n');
                p.setAlignment(Element.ALIGN_LEFT);
                document.add(p);
                document.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GuardaPDF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GuardaPDF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DocumentException ex) {
                Logger.getLogger(GuardaPDF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

}