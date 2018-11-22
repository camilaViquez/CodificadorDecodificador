package Modelo;

import Negocios.IValidable;

import java.util.Objects;

/**
 * Created by Sebastian on 9/12/2018.
 */
public class Alfabeto implements IValidable {

    //Declaracion de las variables locales
    private String nombre;
    private String simbolos;

    /**
     * Constructor de un alfabeto
     * @param nombre
     * @param simbolos 
     */
    public Alfabeto(String nombre, String simbolos) {
        this.nombre = nombre;
        this.simbolos = simbolos;
    }

    //Gettters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSimbolos() {
        return simbolos;
    }

    public void setSimbolos(String simbolos) {
        this.simbolos = simbolos;
    }

    @Override
    public boolean validar(Object obj){
        if (this.equals(obj)) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Alfabeto other = (Alfabeto) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.simbolos, other.simbolos)) {
            return false;
        }
        return true;
    }

    /**
     * Metodo que retorna un string con todos los parametros de la clase
     * @return string parametros
     */
    @Override
    public String toString() {
        return "Alfabeto :\n" +
                "Nombre ='" + nombre + '\n' +
                "Simbolos ='" + simbolos + '\n' +
                '}';
    }

}
