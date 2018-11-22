package servidor;

import Negocios.Controlador;
import libcomunicacion.DTOTraduccion;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
**/
public class Servidor {
    
    private ServerSocket servidor;   // objeto que se encarga de atender peticiones externas
    private  int PUERTO = 5000; // numero de puerto por donde va a atender peticiones

    // la conexion de escritura del servidor
    private OutputStream conexionSalida;
    private ObjectOutputStream flujoSalida;

    // la conexion de lectura del servidor
    private InputStream conexionEntrada;
    private ObjectInputStream flujoEntrada;

    // socket que contiene la conexion con el cliente
    private Socket cliente;         // el proceso cliente que esta atendiendo...

    public int getPUERTO() {
        return PUERTO;
    }

    public void setPUERTO(int PUERTO) {
        this.PUERTO = PUERTO;
    }

    /**
     * Metodo que esta escuchando cualquier solicitud del cliente.
     * @param log
     * @throws InstantiationException 
     */
    public void inicialiceServidor(JTextArea log) throws InstantiationException{
        try {
            servidor = new ServerSocket(PUERTO);
            while (true){
                //System.out.println("Esperando una solicitud de un cliente...");
                log.setText(log.getText()+ "\nEsperando una solicitud de un cliente...");
                cliente = servidor.accept();  // acepta la solicitud de un cliente

                log.setText(log.getText()+ "\nEstableciendo canal de escritura...");
                //System.out.println("Estableciendo canal de escritura...");
                // se establece DE PRIMERO  el canal de comunicacion-Escritura
                conexionSalida =  cliente.getOutputStream();
                flujoSalida = new ObjectOutputStream(conexionSalida);

                //System.out.println("Estableciendo canal de lectura ...");
                log.setText(log.getText()+ "\nEstableciendo canal de lectura...");
                // se establece DE SEGUNDO el canal de comunicacion-Lectura
                conexionEntrada = cliente.getInputStream();
                flujoEntrada = new ObjectInputStream(conexionEntrada);

                // atender la peticion...
                log.setText(log.getText()+ "\nAtendiendo peticion...");
                procesePeticion(log);
                log.setText(log.getText()+ "\nDesconectando...");
                log.setCaretPosition(log.getText().length());  // manda el log al final
                flujoEntrada.close();
                flujoSalida.close();
                cliente.close();
            }
        } catch (IOException ex) {
            System.out.println("Problemas creando el servidor en el puerto "+ PUERTO);
        }
    }

    private void procesePeticion(JTextArea log) throws InstantiationException {
        
        try { 
            DTOTraduccion capsula = (DTOTraduccion) flujoEntrada.readObject();
           
            if(capsula.getComunicacion()== 1){ //Procesar DE/CODIFICAR
                proceseCaso1(capsula);
            }
            if(capsula.getComunicacion() == 2){ // Autocrear FRASE                
                proceseCaso2(capsula);
            }
            if(capsula.getComunicacion() == 3){ // Carga los datos default al sistema
                proceseCaso3(capsula);
            }
            if(capsula.getComunicacion() == 4){ // GuardarDatos
                proceseCaso4(capsula);
            }
            if(capsula.getComunicacion() == 5){ // Obtiene el alfabeto
                proceseCaso5(capsula);
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
        
    }

    /**
     * Metodo que manda a de/codificar.
     */
    private void proceseCaso1(DTOTraduccion capsula){
        try{
            //System.out.println("LO QUE MANDAN AL INICIO: \n"+ capsula.toString());
            ArrayList<String> fraseTraduccida = new ArrayList<>();
            Controlador mainController = new Controlador(capsula);
            capsula = mainController.realizarTraduccion(capsula);
            //System.out.println("LO QUE SE ENTREGA: \n"+ capsula.toString());
            flujoSalida.writeObject(capsula);
        } catch (IOException ex) {
            System.out.println("Problemas leyendo o escribiendo en el flujo entrada/salida........"+ex);
        } 
    }
    
    /**
     * Metodo que autocrea la frase por el usuario.
     */
    private void proceseCaso2(DTOTraduccion capsula) throws IOException{            
            Controlador mainController = new Controlador(capsula);
            capsula.setFraseEntrada(mainController.obtenerFrase(capsula));
            flujoSalida.writeObject(capsula);
    }
    
    /**
     * Metodo que carga los datos default al sistema 
     */
    private void proceseCaso3(DTOTraduccion capsula){
        try{
            Controlador mainController = new Controlador(capsula);
            capsula = mainController.obtenerDatosPersistentes();
            flujoSalida.writeObject(capsula);
        } catch (IOException ex) {
            System.out.println("Problemas leyendo o escribiendo en el flujo entrada/salida........"+ex);
        } 
    }
    
    /**
     * Metodo que manda a guadrdar los datos.
     */
    private void proceseCaso4(DTOTraduccion capsula){
        try{
            boolean aux = false;
            Controlador mainController = new Controlador(capsula);
            aux = mainController.guardarTraduccion(capsula);
            System.out.println(aux);
            flujoSalida.writeObject(capsula);
        } catch (IOException ex) {
            System.out.println("Problemas leyendo o escribiendo en el flujo entrada/salida........"+ex);
        } 
    }
    
    /**
     * Metodo que obtiene el alfabeto.
     */
    private void proceseCaso5(DTOTraduccion capsula){
        try{
            Controlador mainController = new Controlador(capsula);
            
            flujoSalida.writeObject(capsula);
        } catch (IOException ex) {
            System.out.println("Problemas leyendo o escribiendo en el flujo entrada/salida........"+ex);
        } 
    }

}
