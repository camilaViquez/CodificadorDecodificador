package libcomunicacion;

import java.io.Serializable;


/**
 *
 * @author Sebastian Salas
 */
public class OBJComunicacion implements Serializable{
    //Variables de clase
    Object datoEntrada;
    Object datoSalida;

    /**
     * Constructor de la clase
     * @param datoEntrada 
     */
    public OBJComunicacion(Object datoEntrada) {
        this.datoEntrada = datoEntrada;
    }

    //Getters  y Setter
    public Object getDatoEntrada() {
        return datoEntrada;
    }

    public void setDatoEntrada(Object datoEntrada) {
        this.datoEntrada = datoEntrada;
    }

    public Object getDatoSalida() {
        return datoSalida;
    }

    public void setDatoSalida(Object datoSalida) {
        this.datoSalida = datoSalida;
    }

}
