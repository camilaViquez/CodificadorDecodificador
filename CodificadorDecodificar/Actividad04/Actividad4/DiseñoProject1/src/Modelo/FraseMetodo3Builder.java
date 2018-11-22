/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import libcomunicacion.DTOTraduccion;

/**
 *
 * @author Sebastian
 */
public class FraseMetodo3Builder extends FraseBuilder{
    
    @Override
     public void buildDTOTraduccion(DTOTraduccion dto) {
        String alf = frase.getAlf().getSimbolos();
//        int cantChars = frase.getAlf().getCantChar();
//        String resultado = generarFraseMetodo3(alf, cantChars);
//        //Guardar resultado en el dto
//        frase.getDto().setFraseEntrada(resultado);
    }

    @Override
    public void buildAlfabeto(Alfabeto alf) {
        frase.setAlf(alf);
        
    }

   
public static String generarFraseMetodo3(String alfabeto, int cantChars){
        int numero;
        String resultado = "";
        boolean sirve;
        ArrayList<String> listaChars = new ArrayList<>(); 
        if (alfabeto.contains(" ")){
            alfabeto = alfabeto.replaceAll(" ", "");
            alfabeto = alfabeto + " ";
        }
        if (cantChars <= alfabeto.length()){
            for(int i = 0 ; i < cantChars;i++){            
                sirve = true;
                while (sirve){
                    numero = (int) (Math.random() * alfabeto.length());
                    if(!listaChars.contains(String.valueOf(alfabeto.charAt(numero)))){
                        listaChars.add(String.valueOf(alfabeto.charAt(numero)));
                        resultado += String.valueOf(alfabeto.charAt(numero));
                        sirve = false;
                    }
                }            
            }
        }else{
            System.out.print("La cantidad de caracteres no permite generar este metodo.");
        }
        return resultado;
    }    
}
