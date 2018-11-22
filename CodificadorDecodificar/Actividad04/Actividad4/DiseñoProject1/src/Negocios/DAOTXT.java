/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocios;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/**
 *
 * @author Sebastian
 */
public class DAOTXT {
  
    /**
     * Metodo que elimina un alfabeto de la base de datos
     * @param path ruta donde se encuentra el alfabeto
     * @param nombreEliminar nombre del alfabeto a eliminar
     * @throws FileNotFoundException
     * @throws IOException 
     */
  public void eliminar(String path, String nombreEliminar) throws FileNotFoundException, IOException{
       FileReader reader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line,result = "";
        String[] aux;
        while ((line = bufferedReader.readLine()) != null) {
            aux = line.split("~");
            if(!aux[0].equals(nombreEliminar)){
                result += line+"\n";
            }
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(path));
        bw.write("");
        bw.close();
        FileWriter writer = new FileWriter(path, true);
        writer.write(result);
        writer.close();
        reader.close();
  }
  
  /**
   * Metodo que modifica la base de datos
   * @param path ruta donde se encuentra
   * @param nombreModificar 
   * @param nuevoAlfabeto
   * @throws FileNotFoundException
   * @throws IOException 
   */
  public void modificar(String path, String nombreModificar, String nuevoAlfabeto) throws FileNotFoundException, IOException{
        FileReader reader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line,result = "";
        String[] aux;
        while ((line = bufferedReader.readLine()) != null) {
            aux = line.split("~");
            if(aux[0].equals(nombreModificar)){
                result += aux[0]+"~"+nuevoAlfabeto+"\n";
            }else{
                result += line+"\n";
            }
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(path));
        bw.write("");
        bw.close();
        FileWriter writer = new FileWriter(path, true);
        writer.write(result);
        writer.close();
        reader.close();
  }
  
  /**
   * Metodo que obtiene todo la informacion de la base de datos
   * @param path ruta donde se encuentra los datos persistentes
   * @return
   * @throws FileNotFoundException
   * @throws IOException 
   */
  public String leer (String path) throws FileNotFoundException, IOException{
        FileReader reader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line,result = "";
        while ((line = bufferedReader.readLine()) != null) {
            result += line+"\n";
        }
        reader.close();
        return result;
  }
  
  /**
   * Metodo que agrega a la base de datos un nuevo alfabeto
   * @param path ruta de ubicacion
   * @param nombre nuevo alfabeto
   * @param alfabeto nuevos simbolos disponibles
   * @return 
   */
  public boolean agregar(String path, String nombre, String alfabeto){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(path,true);
            pw = new PrintWriter(fichero);
            int contador=0;
            pw.println(nombre + "~" + alfabeto);

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
      return false;
  
}
}
