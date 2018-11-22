package Negocios;

import Modelo.*;
import java.io.IOException;
import java.util.ArrayList;
import libcomunicacion.DTOTraduccion;
/**
 * Created by Sebastian on 9/12/2018.
 */
public class Controlador implements  IValidable, IEscribir {

    //Declaracion de variables locales
    private ArrayList<Alfabeto> alfabetosActuales;
    private DTOTraduccion capsulaInfo;
    private GestorAlfabeto gestorAlfa;
    private GestorAlgoritmo gestorAlgoritmo;
    private GestorInformacion gestorInfo = new GestorInformacion();
    private StrategyAlgoritmo algoritmo ;
    private int accion ;


    /**
     * Constructor default
     */
    public Controlador() {
        gestorAlfa = new GestorAlfabeto();
        gestorInfo = new GestorInformacion();
        alfabetosActuales = new ArrayList<Alfabeto>();
        
    }

    /**
     * Constructor para realizar las traducciones
     * @param capsulaInfo datos del sistema
     */
   public Controlador(DTOTraduccion capsulaInfo) {
       gestorAlfa = new GestorAlfabeto();
       alfabetosActuales = new ArrayList<Alfabeto>();
       this.capsulaInfo = capsulaInfo;
    }
   
   /**
    * Constructor para manejar los eventos de creacion y eliminacion
    * @param accion 
    */
   public Controlador(int accion){
       this.accion = accion;
       gestorInfo = new GestorInformacion();
       gestorAlfa = new GestorAlfabeto();
   }
   
   /**
    * Constructor para manejar los eventos de creacion de alfabetos
    * @param accion
    * @param nombre
    * @param simbolos 
    */
   public Controlador(int accion,String nombre,String simbolos ){
       this.accion = accion;
       gestorInfo = new GestorInformacion();
       gestorAlfa = new GestorAlfabeto(nombre, simbolos);
   }
   
   /**
    * Constructor para manejar los eventos de eliminacion de alfabetos
    * @param accion proceso
    * @param nombreEliminar nombre del alfabeto a eliminar 
    */
   public Controlador(int accion,String nombreEliminar){
       this.accion = accion;
       gestorInfo = new GestorInformacion();
       gestorAlfa = new GestorAlfabeto(nombreEliminar);
   }


   /**
    * Metodo que delega las funciones necesarias para obtener la frase de/codificada.
    * @param capsula
    * @param tipo
    * @return frase de salida
    */
    public DTOTraduccion realizarTraduccion (DTOTraduccion capsula){
        ArrayList<String> result = new ArrayList<>();
        for (String dato : capsula.getTipoAlgoritmos()) {
                this.algoritmo = getAlgoritmo(dato);
                result.add(this.algoritmo.realizarTraduccion(capsula));
            }
        capsula.setFraseSalida(result);
        return capsula;
    }
    
    /**
     * Metodo para obtener todos los algortimos disponibles
     * @param tipo nombre del algortimo
     * @return 
     */
    public static StrategyAlgoritmo getAlgoritmo(String tipo){
        StrategyAlgoritmo algoritmo = null;
        try{
            //recupera el paquete donde se encuentra la clase base
            String paquete = StrategyAlgoritmo.class.getPackage().getName();
            String laInstancia = String.valueOf(paquete+".Codigo"+tipo);
            algoritmo = (StrategyAlgoritmo) Class.forName(laInstancia).newInstance();
        }catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            algoritmo = null;
        }
        
        return algoritmo;
    }
    
    /**
     * Metodo donde se toman desiciones de procesos generales
     * @throws IOException 
     */
    public void procesarAdminActions() throws IOException{
        switch (accion){
            case 1:
                gestorInfo.agregarAlgoritmo();
                break;
            case 2:
                gestorInfo.eliminarAlgoritmo();
                break;
            case 3:
                gestorAlfa.crearAlfabeto();
                break;
            case 4:
                gestorAlfa.eliminarAlfabeto();
                break;
            case 5:
                gestorInfo.showBitacora();
                break;
        }
    }
    
    /**
     * Metodo que manda a generar un frase random con diferentes metodos.
     * @param capsula
     * @return frase de entrada
     */
    public String obtenerFrase (DTOTraduccion capsula){
        String result = "";
        GestorFrases gestor = new GestorFrases();
        switch (capsula.getTipoGenerar()){
            case 1 :
                result = gestor.generarFraseMetodo1(capsula.getAlfabeto(), capsula.getCantidadChars());
                break;
            case 2 :
                result = gestor.generarFraseMetodo2(capsula.getAlfabeto(), capsula.getCantidadChars());
                break;
            case 3 :
                result = gestor.generarFraseMetodo3(capsula.getAlfabeto(), capsula.getCantidadChars());
                break;
        }
        return result;
    }

    /**
     * Metodo que extrae todos los datos persistentes al sistema
     * @return informacion a desplegar
     * @throws IOException 
     */
    public DTOTraduccion obtenerDatosPersistentes() throws IOException{
        DTOTraduccion info = new DTOTraduccion(0);
        String[] informacion = new String[gestorInfo.getAlgoritmos(capsulaInfo).length];
        informacion = new String[gestorInfo.getAlfabetos(capsulaInfo).length];
        for (int i = 0; i < informacion.length; i++) {
            informacion[i] = gestorInfo.getAlfabetos(capsulaInfo)[i];
        } 
        info.setTipoAlfabeto(informacion);
        informacion = new String[gestorInfo.getAlgoritmos(capsulaInfo).length];
        for (int i = 0; i < informacion.length; i++) {
            informacion[i] = gestorInfo.getAlgoritmos(capsulaInfo)[i];
        }        
        info.setTipoAlgoritmos(informacion);
        informacion = new String[gestorInfo.getGuardados(capsulaInfo).length];
        for (int i = 0; i < informacion.length; i++) {
            informacion[i] = gestorInfo.getGuardados(capsulaInfo)[i];
        }        
        info.setMetodoGuardado(informacion);
        return info;
    }
    
    //Getters y Stters
    public boolean validarAlfabeto(Alfabeto alfa){
        return gestorAlfa.validar(alfa);
    }

    public boolean validarFraseEntrada(String entrada){
        return gestorAlfa.validarfrase(entrada);
    }

    //Getters y Setters
    public ArrayList<Alfabeto> getAlfabetosActuales() {
        return alfabetosActuales;
    }

    public void setAlfabetosActuales(ArrayList<Alfabeto> alfabetosActuales) {
        this.alfabetosActuales = alfabetosActuales;
    }

    public DTOTraduccion getCapsulaInfo() {
        return capsulaInfo;
    }

    public void setCapsulaInfo(DTOTraduccion capsulaInfo) {
        this.capsulaInfo = capsulaInfo;
    }

    //Implementacion de interfaces
    //Entrada: recibe el DTO y valida contenido
    //Salida boolena, true si es alfabeto valido
    @Override
    public boolean validar(Object obj){
        return true;
    }

    /**
     * Metodo que manda a guardar la traduccion segun el modo necesario
     * @param capsula datos a guardar
     * @return  estado de aceptacion
     */
    @Override
    public boolean guardarTraduccion(DTOTraduccion capsula) {
        IEscribir guardar = new GuardaTXT(); //Default metodo de guardado
        boolean estado = false;
        for (String dato : capsula.getMetodoGuardado()) {
            if(dato.equals("XML")){
                guardar = new GuardaXML();
            }
            if(dato.equals("TXT")){
                guardar = new GuardaTXT();
            }
            if(dato.equals("PDF")){
                guardar = new GuardaPDF();
            }
            estado = guardar.guardarTraduccion(capsula);
        }
        
        return estado;
    }
}
