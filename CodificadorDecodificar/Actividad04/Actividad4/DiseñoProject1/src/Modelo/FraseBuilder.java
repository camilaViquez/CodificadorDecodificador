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
abstract public class FraseBuilder {
    
    //Declaracion de variables locales
    protected Frase frase;
    public Frase getEstilo(){return frase;}
    public void createNewFrase(){frase = new Frase();}
    
    /**
     * Firma de metodo por ejecutar 
     * @param dto 
     */
    public abstract void buildDTOTraduccion (DTOTraduccion dto);
    
    /**
     * Firma de metodo que construira un alfabeto 
     * @param alf 
     */
    public abstract void buildAlfabeto (Alfabeto alf);
}