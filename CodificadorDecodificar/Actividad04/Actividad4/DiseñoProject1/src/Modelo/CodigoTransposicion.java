package Modelo;

import libcomunicacion.DTOTraduccion;
import java.util.ArrayList;

/**
 *
 * @author tanzanita
 */
public class CodigoTransposicion extends StrategyAlgoritmo{

    /**
     * Metodo heredado que traduce una frase
     * @param informacion Conjunto de informacon a procesar
     * @return frase traducida.
     */
    @Override
    public String realizarTraduccion(DTOTraduccion informacion) {

        int tipoTraduccion = informacion.getTipoTraduccion();
        String fraseEntrada = informacion.getFraseEntrada();
        ArrayList<String> fraseSalida = new ArrayList();
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
     * Metodo que codifica una frase con el modo de transposicion
     * @param fraseEntrada frase de entrada por el usuario
     * @return frase decodificada
     */
    public String codificar(String fraseEntrada){
        String resultado = "";
        String caracter;
        String aux = "";
        Boolean cambiador = true;
        fraseEntrada = fraseEntrada.toLowerCase();
        for (int i = 0; i < fraseEntrada.length(); i++) {
            caracter = String.valueOf(fraseEntrada.charAt(i));
            if (caracter.equals(" ") == true || caracter.equals("\n") == true) {
                if(caracter.equals(" ")){
                    resultado += " ";
                }else{
                    resultado += "\n";
                }
                cambiador = false;
            } else {
                if (cambiador == false){
                    aux = aux + resultado;
                    resultado = caracter ;
                    cambiador = true;
                }else{
                    resultado = caracter + resultado;
                }
            }

        }
        aux = aux + resultado;
        return aux;

    }

    /**
     * Metodo que decodifica una frase con el modo de transposicion
     * @param fraseEntrada frase de entrada por el usuario
     * @return frase decodificada
     */
    public String decodificar(String fraseEntrada){
        String resultado = "";
        String caracter;
        String aux = "";
        Boolean cambiador = true;
        for (int i = 0; i < fraseEntrada.length(); i++) {
            caracter = String.valueOf(fraseEntrada.charAt(i));
            if (caracter.equals(" ") == true || caracter.equals("\n") == true) {
                if(caracter.equals(" ")){
                    resultado += " ";
                }else{
                    resultado += "\n";
                }
                cambiador = false;
            } else {
                if (cambiador == false){
                    aux = aux + resultado ;
                    resultado = caracter ;
                    cambiador = true;
                }else{
                    resultado =  caracter + resultado  ;
                }
            }

        }
        aux = aux + resultado;
        return aux;

    }

}

