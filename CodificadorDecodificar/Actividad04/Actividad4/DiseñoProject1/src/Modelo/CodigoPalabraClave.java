package Modelo;

import libcomunicacion.DTOTraduccion;
import java.util.ArrayList;

/**
 *
 * @author tanzanita
 */
public class CodigoPalabraClave extends StrategyAlgoritmo{
    //Declaracion de variables locales
    String alfabeto;

    /**
     * Metodo heredado que traduce una frase
     * @param informacion Conjunto de informacon a procesar
     * @return frase traducida.
     */
    @Override
    public String realizarTraduccion(DTOTraduccion informacion) {
        int tipoTraduccion = informacion.getTipoTraduccion();
        String fraseEntrada = informacion.getFraseEntrada().toLowerCase();
        this.alfabeto = informacion.getAlfabeto().toLowerCase();
        String resultado = "";
        String clave = "";
        //Codificar
        //Resultado de frase traducida se almacena en el mismo DTOTraduccion
        if(tipoTraduccion == 0){
            resultado = codificar(fraseEntrada,informacion.getPalabraClave());
        }else{
            //Decodificar
            resultado = decodificar(fraseEntrada, informacion.getPalabraClave());
        }
        return resultado;
    }


    /**
     * Metodo que codifica una frase con metodo palabra clave
     * @param fraseEntrada frase de entrada por el usuario
     * @return frase decodificada
     */
    public String codificar(String fraseEntrada, String clave){
        ArrayList listaClave = palabraClave(clave);
        fraseEntrada = fraseEntrada.toLowerCase();
        String resultado = "";
        String caracterFrase;
        String caracterClave;
        int acum = 0;
        int x = 0;
        for (int i = 0; i < fraseEntrada.length(); i++) {
            caracterFrase = String.valueOf(fraseEntrada.charAt(i));
            if (caracterFrase.equals(" ") == true || caracterFrase.equals("\n") == true){
                if(caracterFrase.equals(" ")){
                    resultado += " ";
                }else{
                    resultado += "\n";
                }
                acum = 0;
            }else{
                if (acum == listaClave.size()){
                    acum =0;
                }
                caracterClave = String.valueOf(listaClave.get(acum));

                x = alfabeto.indexOf(caracterClave) + alfabeto.indexOf(caracterFrase) + 1;
                if (x >= alfabeto.length()) {
                    x = x - alfabeto.length();
                    resultado += alfabeto.charAt(x);
                } else {
                    resultado += alfabeto.charAt(x);
                }
                acum++;
            }
        }
        return resultado;
    }

    /**
     * Metodo que decodifica una frase con el metodo palabra clave
     * @param fraseEntrada frase de entrada por el usuario
     * @return frase decodificada
     */
    public String decodificar(String fraseEntrada, String clave){
        ArrayList listaClave = palabraClave(clave);
        fraseEntrada = fraseEntrada.toLowerCase();;
        String resultado = "";
        String caracterFrase;
        String caracterClave;
        int acum = 0;
        int x = 0;
        for (int i = 0; i < fraseEntrada.length(); i++) {
            caracterFrase = String.valueOf(fraseEntrada.charAt(i));
            if (caracterFrase.equals(" ") == true || caracterFrase.equals("\n") == true){
                if(caracterFrase.equals(" ")){
                    resultado += " ";
                }else{
                    resultado += "\n";
                }
                acum = 0;
            }else{
                if (acum == listaClave.size()){
                    acum =0;
                }
                caracterClave = String.valueOf(listaClave.get(acum));

                x = alfabeto.indexOf(caracterFrase) - alfabeto.indexOf(caracterClave) -1 ;
                if (x < 0) {
                    x = x + 26;
                    resultado += alfabeto.charAt(x);
                } else {
                    resultado += alfabeto.charAt(x);
                }
                acum++;
            }
        }
        return resultado;

    }
    /**
     * Metodo que inyecta la palabra clave en un arraylist
     * @param clave
     * @return arreglo con la palabra clave
     */
    private ArrayList palabraClave(String clave) {
        ArrayList listaClave = new ArrayList();
        for (int i = 0; i < clave.length(); i++) {
            listaClave.add(String.valueOf(clave.charAt(i)));
        }
        return listaClave;
    }
}
