package Modelo;

import java.util.ArrayList;
import libcomunicacion.DTOTraduccion;

/**
 *Clase que contiene el algortimo binario
 * @author Sebastian
 */
public class CodigoBinario extends StrategyAlgoritmo{
    //Variables de clase
    private String [] numerosBinarios = {"00000","00001","00010","00011","00100","00101","00110","00111","01000","01001","01010","01011","01100",
            "01101","01110","01111","10000","10001","10010","10011","10100","10101","10110","10111","11000","11001"};
    private ArrayList numBinarios = new ArrayList();
    private String alfabeto = "abcdefghijklmnopqrstuvwxyz";

    /**
     * Metodo heredado que traduce una frase
     * @param informacion Conjunto de informacon a procesar
     * @return frase traducida.
     */
    @Override
    public String realizarTraduccion(DTOTraduccion informacion) {
        int tipoTraduccion = informacion.getTipoTraduccion();
        String fraseEntrada = informacion.getFraseEntrada();
        String resultado;
        //Codificar
        //Resultado de frase traducida se almacena en el mismo DTOTraduccion
        if(tipoTraduccion == 0){
            resultado = codificar(fraseEntrada);
        }else{
            //Decodificar
            resultado = decodificar(fraseEntrada);

        }
        return resultado;
    }

    /**
     * Metodo que codifica una frase binaria
     * @param fraseEntrada frase de entrada por el usuario
     * @return frase decodificada
     */
    public String codificar(String fraseEntrada){
        String resultado = "";
        fraseEntrada = fraseEntrada.toLowerCase();
        char caracter = 0;
        int posChar;
        for (int i = 0; i < fraseEntrada.length(); i++) {
            caracter = fraseEntrada.charAt(i);
            if (String.valueOf(caracter).equals(" ") == true || String.valueOf(caracter).equals("\n") == true) {
                if(String.valueOf(caracter).equals(" ")){
                    try{
                        resultado =  resultado.substring(0,resultado.length()-1)+"*";
                    }catch(Exception e){}
                }else{
                    resultado += "\n";
                }
            }else{
                posChar = alfabeto.indexOf(caracter);
                try {
                resultado += numerosBinarios[posChar] + " ";
                }catch (Exception e){}
            }
        }
        return resultado;
    }

    /**
     * Metodo que decodifica una frase binaria
     * @param fraseEntrada frase de entrada por el usuario
     * @return frase decodificada
     */
    public String decodificar(String fraseEntrada){
        llenado();
        fraseEntrada = fraseEntrada.toLowerCase();
        fraseEntrada = fraseEntrada.replace("*","cambio");
        String[] aux = fraseEntrada.split("cambio");
        String[] aux2 ;
        int pos =0;
        String resultado = "";
        int posChar;

        for (int i = 0; i < aux.length; i++) {
            aux2 = aux[i].split(" ");
            for (int y = 0; y < aux2.length; y++) {
                if (String.valueOf(aux2[y].charAt(0)).equals("\n")) {
                    try{
                        aux2[y] = String.valueOf(aux2[y]).substring(1,aux2[y].length());
                        resultado += "\n";
                    }catch(Exception e){}
                }
                pos = numBinarios.indexOf(aux2[y]);
                resultado += alfabeto.charAt(pos);
            }
            resultado += " ";
        }
        return resultado;
    }

    /**
     * Metodo que alista los binarios para ser procesados
     */
    private void llenado(){
        for (int w = 0; w < numerosBinarios.length; w++) {
            numBinarios.add(numerosBinarios[w]);
        }
    }
}
