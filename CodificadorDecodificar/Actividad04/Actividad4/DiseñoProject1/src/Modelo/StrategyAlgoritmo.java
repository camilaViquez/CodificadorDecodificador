/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import libcomunicacion.DTOTraduccion;

/**
 *
 * @author Sebastian
 */
public abstract class StrategyAlgoritmo {
    
    /**
     * Firma de metodo que realizara los procesos de de/codificacion
     * @param informacion datos del sistema
     * @return cadena con la frase traduccida
     */
    public abstract String realizarTraduccion(DTOTraduccion informacion);
    
}
