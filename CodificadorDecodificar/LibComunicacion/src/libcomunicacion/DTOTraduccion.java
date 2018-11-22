package libcomunicacion;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Sebastian on 9/12/2018.
 */
public class DTOTraduccion implements Serializable{
    
    private int comunicacion ;
    private String fraseEntrada ;
    private int tipoTraduccion ;
    private String[] tipoAlgoritmos ;
    private String[] metodoGuardado ;
    private String[] tipoAlfabeto ;
    private String alfabeto;
    private String palabraClave;
    private int cifra;
    private ArrayList<String> fraseSalida ;
    private int cantidadChars;
    private int tipoGenerar;

    /**
     * Metodo contructor principal enfocado en el procesamiento.
     * @param fraseEntrada
     * @param tipoTraduccion
     * @param tipoAlgoritmos
     * @param metodoGuardado
     * @param tipoAlfabeto
     * @param alfabeto
     * @param palabraClave
     * @param cifra 
     */
    public DTOTraduccion(int comu,String fraseEntrada, int tipoTraduccion, 
            String[] tipoAlgoritmos, String[] metodoGuardado, String[] tipoAlfabeto,
            String alfabeto, String palabraClave, int cifra) {
        this.comunicacion = comu;
        this.fraseEntrada = fraseEntrada;
        this.tipoTraduccion = tipoTraduccion;
        this.tipoAlgoritmos = tipoAlgoritmos;
        this.metodoGuardado = metodoGuardado;
        this.tipoAlfabeto = tipoAlfabeto;
        this.alfabeto = alfabeto;
        this.palabraClave = palabraClave;
        this.cifra = cifra;
    }
    
    /**
     * Metodo constructor enfocado en la generacion de frases
     * @param comunicacion
     * @param fraseEntrada
     * @param cantidadChars
     * @param tipoGenerar 
     */
    public DTOTraduccion(int comunicacion, String fraseEntrada, int cantidadChars, int tipoGenerar, String alfabeto) {
        this.comunicacion = comunicacion;
        this.fraseEntrada = fraseEntrada;
        this.cantidadChars = cantidadChars;
        this.tipoGenerar = tipoGenerar;
        this.alfabeto = alfabeto;
        this.fraseSalida = new ArrayList<>();
    }

    /**
     * Metodo que extrae los datos para cargar al sistema en el arranque.
     * @param comunicacion 
     */
    public DTOTraduccion(int comunicacion) {
        this.comunicacion = comunicacion;
    }
    
    /**
     * Metodo constructor que manda a guardar la información
     * @param comunicacion
     * @param salida 
     */
    public DTOTraduccion(int comunicacion, String salida, String[] metodos) {
        this.comunicacion = comunicacion;
        this.alfabeto = salida;
        this.metodoGuardado = metodos;
    }
    
    


    //Getters y Setters
    public String getFraseEntrada() {
        return fraseEntrada;
    }

    public void setFraseEntrada(String fraseEntrada) {
        this.fraseEntrada = fraseEntrada;
    }

    public int getTipoTraduccion() {
        return tipoTraduccion;
    }

    public void setTipoTraduccion(int tipoTraduccion) {
        this.tipoTraduccion = tipoTraduccion;
    }

    public String[] getTipoAlgoritmos() {
        return tipoAlgoritmos;
    }

    public void setTipoAlgoritmos(String[] tipoAlgoritmos) {
        this.tipoAlgoritmos = tipoAlgoritmos;
    }

    public String[] getMetodoGuardado() {
        return metodoGuardado;
    }

    public void setMetodoGuardado(String[] metodoGuardado) {
        this.metodoGuardado = metodoGuardado;
    }

    public String[] getTipoAlfabeto() {
        return tipoAlfabeto;
    }

    public void setTipoAlfabeto(String[] tipoAlfabeto) {
        this.tipoAlfabeto = tipoAlfabeto;
    }

    public String getAlfabeto() {
        return alfabeto;
    }

    public void setAlfabeto(String alfabeto) {
        this.alfabeto = alfabeto;
    }

    public String getPalabraClave() {
        return palabraClave;
    }

    public void setPalabraClave(String palabraClave) {
        this.palabraClave = palabraClave;
    }

    public int getCifra() {
        return cifra;
    }

    public void setCifra(int cifra) {
        this.cifra = cifra;
    }

    public ArrayList<String> getFraseSalida() {
        return fraseSalida;
    }

    public void setFraseSalida(ArrayList<String> fraseSalida) {
        this.fraseSalida = fraseSalida;
    }

    public int getComunicacion() {
        return comunicacion;
    }

    public void setComunicacion(int comunicacion) {
        this.comunicacion = comunicacion;
    }

    public int getCantidadChars() {
        return cantidadChars;
    }

    public void setCantidadChars(int cantidadChars) {
        this.cantidadChars = cantidadChars;
    }

    public int getTipoGenerar() {
        return tipoGenerar;
    }

    public void setTipoGenerar(int tipoGenerar) {
        this.tipoGenerar = tipoGenerar;
    }
    
    

    /**
     * Metodo con los datos de la clase.
     * @return String con las informacion de los atributos.
     */
    @Override
    public String toString() {
        return "DTOTraduccion" + '\n'
                + "Comunicacion=" + comunicacion + '\n'
                + "fraseEntrada=" + fraseEntrada + '\n'
                + " tipoTraduccion=" + tipoTraduccion+  '\n'
                + " tipoAlgoritmos=" + tipoAlgoritmos.toString() + '\n'
                + " metodoGuardado=" + metodoGuardado.toString() + '\n'
                + " tipoAlfabeto=" + tipoAlfabeto.toString() + '\n'
                + " alfabeto=" + alfabeto + '\n'
                + " palabraClave=" + palabraClave + '\n'
                + " cifra=" + cifra + '\n'
                + " cantidadChars=" + cantidadChars + '\n'
                + " tipoGenerar=" + tipoGenerar + '\n'
                + " fraseSalida=" + fraseSalida + '\n';
    }    
}
