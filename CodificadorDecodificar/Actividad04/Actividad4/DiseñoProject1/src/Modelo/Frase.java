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
public class Frase {
    //Declaracion de variables locales
    DTOTraduccion capsula;
    Alfabeto alfabeto;

    public Frase() {
    }

    /**
     * Constructor default
     * @param dto datos del sistema
     * @param alf alfabeto
     */
    public Frase(DTOTraduccion dto, Alfabeto alf) {
        this.capsula = dto;
        this.alfabeto = alf;
    }

    //Setters y Getters
    public DTOTraduccion getDto() {
        return capsula;
    }

    public void setDto(DTOTraduccion dto) {
        this.capsula = dto;
    }

    public Alfabeto getAlf() {
        return alfabeto;
    }

    public void setAlf(Alfabeto alf) {
        this.alfabeto = alf;
    }
    
}
