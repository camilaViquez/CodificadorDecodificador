package Negocios;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import libcomunicacion.DTOTraduccion;

public class GestorInformacion {
    
    String pathAlgoritmos = "C:/Users/Sebastian/Desktop/CodificadorDecodificar/proyecto1Servidor/build/classes/Modelo";
    
    /**
     * Metodo que obtiene todos los archivos en los que el usuario puede procesar las frases.
     * Estos archivos inician con "Codigo" por lo que se les busca con ese String.
     * @param capsula estructura para encapsular la informacion.
     * @return arreglo de String con los algoritmos disponibles.
     */
    public String[] getAlgoritmos(DTOTraduccion capsula){
        ArrayList<String> archivos = new ArrayList<String>();
        ArrayList<String> archivosObtenidos = new ArrayList<String>();
        String[] datosListos;
        File carpeta = new File("C:/Users/Sebastian/Desktop/CodificadorDecodifi"
                + "car/proyecto1Servidor/build/classes/Modelo");
        archivos = listarFicherosPorCarpeta(carpeta);
        int acum = 0;
        for (int i = 0; i < archivos.size();i++) {
            if (archivos.get(i).contains("Codigo")){
                acum++;
                archivosObtenidos.add(archivos.get(i).replace("Codigo","").replace(".class",""));
            }
        }
        datosListos = new String[archivosObtenidos.size()];
        for (int i = 0; i < archivosObtenidos.size();i++) {
            datosListos[i] = archivosObtenidos.get(i);
        }
        return datosListos;
    }
    
    /**
     * Metodo que manda a llamar a la clase gestorAlfabetos para obtener la info necesitada
     * @param capsula estructura para guardar los datos
     * @return los alfabetos disponibles para poder usar.
     */
    public String[] getAlfabetos(DTOTraduccion capsula) throws IOException{
        String[] datosListos;
        GestorAlfabeto gestor = new GestorAlfabeto();
        datosListos = gestor.bajarAlfabetos();
        return datosListos;
    }
    
    /**
     * Metodo que obtiene todos los archivos en los que el usuario puede guardar las salidas.
     * Estos archivos inician con "Guarda" por lo que se les busca con ese String.
     * @param capsula
     * @return arreglo de String con los metodos de guardado disponibles.
     */
    public String[] getGuardados(DTOTraduccion capsula){
        ArrayList<String> archivos = new ArrayList<String>();
        ArrayList<String> archivosObtenidos = new ArrayList<String>();
        String[] datosListos;
        File carpeta = new File("C:/Users/Sebastian/Desktop/CodificadorDecodificar"
                + "/proyecto1Servidor/build/classes/Modelo");
        archivos = listarFicherosPorCarpeta(carpeta);
        int acum = 0;
        for (int i = 0; i < archivos.size();i++) {
            if (archivos.get(i).contains("Guarda")){
                acum++;
                archivosObtenidos.add(archivos.get(i).replace("Guarda","").replace(".class", ""));
            }
        }
        datosListos = new String[archivosObtenidos.size()];
        for (int i = 0; i < archivosObtenidos.size();i++) {
            datosListos[i] = archivosObtenidos.get(i);
        }
        return datosListos;
    }
    
    /**
     * Metodo que saca todos los archivos de un directorio.
     * @param carpeta ruta la cual se desea buscar los datos.
     * @return ista con los archivos recuperados.
     */
    public ArrayList listarFicherosPorCarpeta(File carpeta) {
        ArrayList archivos = new ArrayList();
        for (File ficheroEntrada : carpeta.listFiles()) {
            if (ficheroEntrada.isDirectory()) {
                listarFicherosPorCarpeta(ficheroEntrada);
            } else {
                archivos.add(ficheroEntrada.getName());
            }
        }
        return archivos;
    }
    
    /**
     * Metodo que abre los archivos de la bitacora
     */
    public void showBitacora(){
        try{            
            JFileChooser abrir = new JFileChooser();
            abrir.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int selecciona = abrir.showDialog(null,"Abrir");
            String dir = abrir.getSelectedFile().getParent()+"/"
                    +abrir.getSelectedFile().getName();
            if(selecciona == JFileChooser.APPROVE_OPTION){
                File file = new File(dir);  
                Desktop.getDesktop().open(file);
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error, el sistema no puede realizar la peticion!","", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Metodo que agrega un algoritmo al archivo del programa
     */
    public void agregarAlgoritmo(){
        try{            
            JFileChooser abrir = new JFileChooser();
            abrir.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int selecciona = abrir.showDialog(null,"Abrir");
            String dir = abrir.getSelectedFile().getParent()+"/"
                    +abrir.getSelectedFile().getName();
            if(selecciona == JFileChooser.APPROVE_OPTION){
                File ficheroCopiar = new File(dir);
                File ficheroDestino = new File(pathAlgoritmos, ficheroCopiar.getName());
                if (ficheroCopiar.exists()) {
                    ficheroCopiar.renameTo(ficheroDestino);
                     JOptionPane.showMessageDialog(null, "El algoritmo se ha agregado exitosamente, actualice el sistema para ver los cambios!","", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Error, el sistema no puede realizar la peticion!","", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error, el sistema no puede realizar la peticion!","", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Metodo que elimina un algoritmo del archivo del programa
     */
    public void eliminarAlgoritmo(){
        try{            
            JFileChooser abrir = new JFileChooser();
            abrir.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int selecciona = abrir.showDialog(null,"Abrir");
            String dir = abrir.getSelectedFile().getParent()+"/"
                    +abrir.getSelectedFile().getName();
            if(selecciona == JFileChooser.APPROVE_OPTION){
                File file = new File(dir);  
                file.delete();
                JOptionPane.showMessageDialog(null, "El algoritmo se ha borrado exitosamente, actualice el sistema para ver los cambios!","", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error, el sistema no puede realizar la peticion!","", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
