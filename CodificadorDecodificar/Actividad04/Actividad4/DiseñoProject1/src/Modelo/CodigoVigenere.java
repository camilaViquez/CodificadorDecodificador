package Modelo;

import libcomunicacion.DTOTraduccion;
import java.util.ArrayList;

/**
 *
 * @author tanzanita
 */
public class CodigoVigenere extends StrategyAlgoritmo {
    
    //Decalracion de varaibles locales
    String alfabeto ;
    String cifrado ;
    
    
    /**
     * Metodo heredado que traduce una frase
     * @param informacion Conjunto de informacon a procesar
     * @return frase traducida.
     */
    @Override
    public String realizarTraduccion(DTOTraduccion informacion) {
        alfabeto = informacion.getAlfabeto();
        cifrado = String.valueOf(informacion.getCifra());
        int tipoTraduccion = informacion.getTipoTraduccion();
        String fraseEntrada = informacion.getFraseEntrada();
        ArrayList<String> fraseSalida = new ArrayList();
        String resultado = "";
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
     * Metodo que codifica una frase con el modo vigenere
     * @param fraseEntrada frase de entrada por el usuario
     * @return frase decodificada
     */
    public String codificar(String fraseEntrada){
        fraseEntrada = fraseEntrada.toLowerCase();
        String resultado = "";
        int primera = Integer.parseInt(String.valueOf(cifrado.charAt(0)));
        int segunda = Integer.parseInt(String.valueOf(cifrado.charAt(1)));
        boolean cambiardor = true;
        String caracter;
        int posChar;
        int newPos;
        for (int i = 0; i < fraseEntrada.length();i++){
            caracter = String.valueOf(fraseEntrada.charAt(i));
            if (caracter.equals(" ") == true || caracter.equals("\n") == true) {
                if(caracter.equals(" ")){
                    resultado += " ";
                }else{
                    resultado += "\n";
                }
                cambiardor = true;
            }else{
                posChar = alfabeto.indexOf(caracter);
                if (cambiardor == true){
                    newPos = posChar + primera;
                    if(newPos >= alfabeto.length()){
                        newPos = newPos % alfabeto.length();
                    }
                    resultado += alfabeto.charAt(newPos);
                    cambiardor = false;
                }else{
                    newPos = posChar + segunda;
                    if(newPos >= alfabeto.length()){
                        newPos = newPos % alfabeto.length();
                    }
                    resultado += alfabeto.charAt(newPos);
                    cambiardor = true;
                }
            }

        }
        return resultado;

    }

    /**
     * Metodo que decodifica una frase con el modo vigenere
     * @param fraseEntrada frase de entrada por el usuario
     * @return frase codificada
     */
    public String decodificar(String fraseEntrada){
        fraseEntrada = fraseEntrada.toLowerCase();
        String resultado = "";
        int primera = Integer.parseInt(String.valueOf(cifrado.charAt(0)));
        int segunda = Integer.parseInt(String.valueOf(cifrado.charAt(1)));
        boolean cambiardor = true;
        String caracter;
        int posChar;
        int newPos;
        for (int i = 0; i < fraseEntrada.length();i++){
            caracter = String.valueOf(fraseEntrada.charAt(i));
            if (caracter.equals(" ") == true || caracter.equals("\n") == true) {
                if(caracter.equals(" ")){
                    resultado += " ";
                }else{
                    resultado += "\n";
                }
                cambiardor = true;
            }else{
                posChar = alfabeto.indexOf(caracter);
                if (cambiardor == true){
                    newPos = posChar - primera;
                    if(newPos < 0){
                        newPos =   alfabeto.length() - newPos - primera - 2;
                    }
                    resultado += alfabeto.charAt(newPos);
                    cambiardor = false;
                }else{
                    newPos = posChar - segunda;
                    if(newPos < 0){
                        newPos =   alfabeto.length() - newPos - segunda -1;
                    }
                    resultado += alfabeto.charAt(newPos);
                    cambiardor = true;
                }
            }

        }
        return resultado;

    }
}
