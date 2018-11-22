package Modelo;

/**
 * Created by Sebastian on 9/12/2018.
 */
public abstract class GestorAlgoritmo {

    //Declaracion de variables locales
    private String frase;
    private String fraseTraducida;

    /**
     * Firma del metodo codificar
     * @param capsula info del sistema a procesar
     * @return 
     */
    abstract public String codificar(String capsula);

    /**
     * Firma del metodo decoficar
     * @param capsula info del sistema a procesar
     * @return 
     */
    abstract public String decodificar(String capsula);

}
