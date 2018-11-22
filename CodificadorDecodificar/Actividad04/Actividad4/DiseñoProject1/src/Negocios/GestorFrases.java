/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocios;

import java.util.ArrayList;

/**
 *
 * @author Sebastian
 */
public class GestorFrases {
    
    /**
     * Metodo qu
     * @param alfabeto
     * @param cantChars
     * @return 
     */
    public  String generarFraseMetodo3(String alfabeto, int cantChars){
        int numero;
        String resultado = "";
        if (alfabeto.contains(" ")){
            alfabeto = alfabeto.replaceAll(" ", "");
            alfabeto = alfabeto + " ";
        }
        for(int i = 0 ; i < cantChars;i++){
             numero = (int) (Math.random() * alfabeto.length());
             resultado += String.valueOf(alfabeto.charAt(numero));
        } 
        return resultado;
    }
    
    public  String generarFraseMetodo2(String alfabeto, int cantChars){
        int numero;
        String resultado = "";
        boolean sirve;
        System.out.println(alfabeto);
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
    
    public  String generarFraseMetodo1(String alfabeto, int cantChars){
        int numero;
        String resultado = "";
        boolean sirve;        
        int pasadoPos = 150000000;
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
                        if(!(numero == pasadoPos + 1)){
                            listaChars.add(String.valueOf(alfabeto.charAt(numero)));
                            resultado += String.valueOf(alfabeto.charAt(numero));
                            sirve = false;
                            pasadoPos = numero;
                        }else{
                            //Si es el siguiente que continue !!
                        }
                    }
                }            
            }
        }else{
            System.out.print("La cantidad de caracteres no permite generar este metodo.");
        }
        return resultado;
    }
}
