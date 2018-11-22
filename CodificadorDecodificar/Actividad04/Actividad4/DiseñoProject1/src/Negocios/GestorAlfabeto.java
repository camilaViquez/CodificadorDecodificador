package Negocios;

import Modelo.Alfabeto;
import java.io.IOException;
import libcomunicacion.DTOTraduccion;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebastian on 9/12/2018.
 */
public class GestorAlfabeto implements IValidable{
    private final String path = "C:/Users/Sebastian/Desktop/CodificadorDecodificar/BDAlfabeto.txt";
    public String nombreAlfa;
    public String simboloAlfa;

    /**
     * Constructor default
     */
    public GestorAlfabeto() { }    
    
    /**
     * Constructor especializado en crear nuevos Alfabetos
     * @param nombreAlfa
     * @param simboloAlfa 
     */
    public GestorAlfabeto(String nombreAlfa, String simboloAlfa) {
        this.nombreAlfa = nombreAlfa;
        this.simboloAlfa = simboloAlfa;
    }  
    
    /**
     * Constructor creado para eliminar alfabetos
     * @param nombreAlfa 
     */
    public GestorAlfabeto(String nombreAlfa) {
        this.nombreAlfa = nombreAlfa;
    }
    
    /**
     * Metodo que obtiene los Albetos de la Base de Datos
     * @return
     * @throws IOException 
     */
    public String[] bajarAlfabetos() throws IOException{
        DAOTXT baseDatosAlfabeto = new DAOTXT();
        String[] result = baseDatosAlfabeto.leer(path).split("\n"),resultado2;
        String nombre = "", simbolos = "";
        for (int i = 0; i < result.length; i++) {
            resultado2 = result[i].split("~");
            nombre = resultado2[0];
            simbolos = resultado2[1];
            Alfabeto alfa = new Alfabeto(nombre, simbolos);
        }
        return result;
    }

    /**
     * Metodo que crea un Alfabeto
     */
    public void crearAlfabeto(){
        DAOTXT baseDatos = new DAOTXT();
        baseDatos.agregar(path,nombreAlfa,simboloAlfa);
    }

    /**
     * Metodo que elimina un Alfabeto
     */
    public void eliminarAlfabeto() throws IOException{
        DAOTXT baseDatos = new DAOTXT();
        baseDatos.eliminar(path,nombreAlfa);
    }

    /**
     * Metodo que valida un Alfabeto
     * @param obj
     * @return 
     */
    @Override
    public boolean validar(Object obj) {
        System.out.println("Validar dentro gestor");
        Alfabeto alfa= (Alfabeto) obj;
        return true;
    }

    /**
     * Metodo que valida una frase con respecto al alfabeto 
     * @param obj
     * @return 
     */
    public boolean validarfrase(Object obj){
        System.out.println("Validacion de frase ingresada por el usuario, gestorAlfabeto");
        DTOTraduccion capsula= (DTOTraduccion) obj;
        String entrada = capsula.getFraseEntrada();
        //Hay que comparar que este bien con el alfabeto
        return true;
    }
}
