package administrador;

import libcomunicacion.DTOTraduccion;
import java.net.UnknownHostException;


public class AdmCliente {
    
    /**
     * 
     * @param Objeto De tipo DTOTraduccion contiene toda la infomarcion del sistema.
     * @return Lista con los Strings 
     */
    public DTOTraduccion getDatosServidor(DTOTraduccion Objeto){
        Cliente c = new Cliente();
        c.setHOST("localhost");
        c.setPUERTO(5000);
        try {
            Objeto = c.conecteServidor(Objeto);
            return Objeto;
        } catch (UnknownHostException ex) {
            return null;
        }
       
    }
}
