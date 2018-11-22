package Modelo;

import libcomunicacion.DTOTraduccion;
import java.util.ArrayList;

/**
 *
 * @author tanzanita
 */
public class CodigoTelefonico extends StrategyAlgoritmo{
    
    //Declaraciones de variables locales
    private ArrayList<String> lista = new ArrayList();
    
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
     * Metodo que codifica una frase con el modo de codigo telefonico
     * @param fraseEntrada frase de entrada por el usuario
     * @return frase decodificada
     */
    public String codificar(String fraseEntrada){
        llenarLista();
        fraseEntrada = fraseEntrada.toLowerCase();
        String resultado = "";
        String caracterFrase;
        for (int i = 0; i < fraseEntrada.length();i++){
            caracterFrase = String.valueOf(fraseEntrada.charAt(i));
            if (caracterFrase.equals(" ") == true || caracterFrase.equals("\n") == true) {
                if(caracterFrase.equals("\n")){
                    resultado += "\n";
                }else{
                    if(caracterFrase.equals(" ")){
                        resultado += "* ";
                    }else{
                        resultado += " ";
                    }
                }
            }else{
                for (int w = 0; w < lista.size();w++){
                    if (String.valueOf(lista.get(w)).contains(caracterFrase)){
                        int pos = String.valueOf(lista.get(w)).indexOf(caracterFrase);
                        resultado += String.valueOf(w+2)+ String.valueOf(pos+1) + " ";
                    }
                }

            }

        }
        return resultado;

    }

    /**
     * Metodo que decodifica una frase con el modo de codigo telefonico
     * @param fraseEntrada frase de entrada por el usuario
     * @return frase codificada
     */
    public String decodificar(String fraseEntrada){
        llenarLista();
        fraseEntrada = fraseEntrada.toLowerCase();
        String resultado = "";
        String caracterFrase;
        String num1;
        String num2;
        for (int i = 0; i < fraseEntrada.length();i++){
            num1 = String.valueOf(fraseEntrada.charAt(i));
            if (num1.equals(" ") || num1.equals("*") || num1.equals("\n") ) {
                if(num1.equals("*")){
                    resultado += " ";
                }else{
                    if(num1.equals("\n")){
                        resultado += "\n";
                    }else{
                        resultado += "";
                        try {
                            if (String.valueOf(fraseEntrada.charAt(i+1)).equals(" ")){
                                resultado += "\n";
                            }
                        }catch (Exception e){}
                    }

                }
            }else{
                num2 = String.valueOf(fraseEntrada.charAt(i+1));
                resultado += String.valueOf(lista.get(Integer.parseInt(num1)-2)).charAt(Integer.parseInt(num2)-1);
                i++;
            }
        }
        return resultado;
    }

    /**
     * Metodo que carga a un arreglo la estructura de un telefono
     */
    private void llenarLista(){
        lista.add("abc");lista.add("def");lista.add("ghi");lista.add("jkl");
        lista.add("mno");lista.add("pqrs");lista.add("tuv");lista.add("wxyz");
    }
}

