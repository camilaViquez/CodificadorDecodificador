package Modelo;

import java.io.*;
import libcomunicacion.DTOTraduccion;

/**
 * Created by Sebastian on 9/12/2018.
 */
public class GuardaTXT implements IEscribir{
    
    /**
     * Metodo que guarda la frase de salida en un TXT
     * @param texto datos a guardar
     * @return estado de realizacion
     */
    @Override
    public boolean guardarTraduccion(DTOTraduccion texto){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("C:/Users/Sebastian/Desktop/CodificadorDeco"
                    + "dificar/Bitacora/DAOTXT.txt",true);
            pw = new PrintWriter(fichero);
            int contador=0;
            pw.println(texto.getAlfabeto());
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                // Nuevamente aprovechamos el finally para
                // asegurarnos que se cierra el fichero.
                if (null != fichero)
                    fichero.close();
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        }
        
    }
}


