package Modelo;
        
import libcomunicacion.DTOTraduccion;

public interface IEscribir {
    
    /**
     * Firma del metodo para guardar las traducciones
     * @param datos informacion a guardar
     * @return estado de aceptacion
     */
    public boolean guardarTraduccion(DTOTraduccion datos);
    
}
