package administrador;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import libcomunicacion.DTOTraduccion;

public class Cliente {

    //Declaracion de variables locales a la clase
    private String HOST =  "localhost";
    private int PUERTO= 5000;
    private InputStream conexionEntrada;
    private ObjectInputStream flujoLectura;
    private OutputStream conexionSalida;
    private ObjectOutputStream flujoEscritura;

    /**
     * Metodo constructor vacío.
     */
    public Cliente() {
    }
    
    //Getters y Setters
    public String getHOST() {
        return HOST;
    }

    public void setHOST(String HOST) {
        this.HOST = HOST;
    }

    public int getPUERTO() {
        return PUERTO;
    }
    
    public void setPUERTO(int PUERTO) {
        this.PUERTO = PUERTO;
    }

    /**
     * 
     * @param objeto
     * @return DTOTraduccion maneja toda la información del sistema
     * @throws UnknownHostException 
     */
    public DTOTraduccion conecteServidor(DTOTraduccion objeto) throws UnknownHostException{
        try {
            
            Socket cliente = new Socket(HOST, PUERTO);
            //Establece mecanismo de comunicacion con el servidor - Lectura..
            System.out.println("Estableciendo comunicacion de lectura con el server...");
            conexionEntrada = cliente.getInputStream();
            flujoLectura = new ObjectInputStream(conexionEntrada);
            System.out.println("Estableciendo comunicacion de escritura con el server...");
            conexionSalida = cliente.getOutputStream();
            flujoEscritura = new ObjectOutputStream(conexionSalida);

            // procesar la gestion a solicitar
            flujoEscritura.writeObject(objeto);
            flujoEscritura.flush();
            
            // recupera la respuesta del servidor...
            objeto = (DTOTraduccion) flujoLectura.readObject();
  
            flujoEscritura.close();
            flujoLectura.close();
            cliente.close();
        }catch (ClassNotFoundException  ex) {
            System.out.println("Conectandose a un servidor desconocido");
        }catch (UnknownHostException ex) {
            System.out.println("Conectandose a un servidor desconocido");
        }catch (IOException ex) {
            System.out.println("Problemas con los flujos de entrada / salida");
        }
        return objeto;
    }
}
