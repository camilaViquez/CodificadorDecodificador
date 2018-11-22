package Modelo;

import libcomunicacion.DTOTraduccion;

/**
 *
 * @author tanzanita
 */
public class ContextAlgoritmo {
    
    //Declaracion de variables locales
    private StrategyAlgoritmo strategy;

    /**
     * Constructor de la clase
     * @param strategy 
     */
    public ContextAlgoritmo(StrategyAlgoritmo strategy){
        this.strategy = strategy;
    }

    /**
     * Metodo que realiza la traduccion del sistema
     * @param informacion capsula con la info del sistema
     * @return 
     */
    public String executeStrategy(DTOTraduccion informacion){
        return strategy.realizarTraduccion(informacion);
    }

}