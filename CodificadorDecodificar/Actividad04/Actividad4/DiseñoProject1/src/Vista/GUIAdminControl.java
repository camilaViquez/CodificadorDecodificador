package Vista;

import Negocios.Controlador;
import java.io.IOException;
import libcomunicacion.DTOTraduccion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javax.swing.*;
import java.util.ArrayList;

public class GUIAdminControl {
    
    @FXML    private TextArea entrada;
    @FXML    private TextArea salida;
    @FXML    private TextArea tipoAlgoritmo;
    @FXML    private TextArea tipoAlfabeto;
    @FXML    private TextArea tipoGuardado;
    @FXML    private TextField simbolo;
    @FXML    private TextField palabraClave;
    @FXML    private Spinner<Integer> cantCaracteres; 
    @FXML    private Spinner<Integer> cifra;
    @FXML    private RadioButton rCodificar;
    @FXML    private RadioButton rDecodificar;
    @FXML    private RadioButton metodo1;
    @FXML    private RadioButton metodo2;
    @FXML    private RadioButton metodo3;
    @FXML    private RadioButton metodo4;
    
    /**
     * Método que se ejcuta cuando se da click al bóton de ejecutar.
     * @param event 
     */
    public void ejecutar(ActionEvent event) {
        try{
            //Declaracion de variables locales.
            String fraseEntrada = entrada.getText();
            int tipoTraduccion = 0 ;
            String[] tipoAlgo = this.tipoAlgoritmo.getText().split("\n");
            String[] tipoAlfabeto = this.tipoAlfabeto.getText().split("\n");
            String[] tipoMetodo = this.tipoGuardado.getText().split("\n");
            String alfabeto = this.simbolo.getText();
            String palabraClave = this.palabraClave.getText();
            int cifra = this.cifra.getValue();
            int dato1 = 0, dato2 = 0, dato3 = 0, dato4 = 0, dato5 = 0, dato6 = 0, dato7 = 0;
            
            //Estructuras de control y aceptación
            if(fraseEntrada.equals("") == true){
                JOptionPane.showMessageDialog(null, "¡Debe existir una frase de "
                        + "entrada para procesar!", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            }
            if (rCodificar.isSelected() == false && rDecodificar.isSelected() == false) {
                JOptionPane.showMessageDialog(null, "¡Debe seleccionar un modo "
                        + "de traducción!", "Mensaje de Error", JOptionPane.ERROR_MESSAGE); //Tipo de mensaje
            } else {
                if (rCodificar.isSelected() == true) {
                    tipoTraduccion = 0; //codificar
                } else {
                    tipoTraduccion = 1; //decodificar
                }
                dato1 = 1;
            }
            if(tipoAlgo.length < 1){
                JOptionPane.showMessageDialog(null, "¡Debe seleccionar al menos "
                        + "un tipo algortimo!", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            }else{
                dato2 = 1;
            }
            if(tipoAlfabeto.equals("") == true){
                JOptionPane.showMessageDialog(null, "¡Debe seleccionar solo un "
                        + "tipo de alfabeto!", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            }else{
                dato3 = 1;
            }
            if(tipoMetodo.length < 1){
                JOptionPane.showMessageDialog(null, "¡Debe seleccionar al menos "
                        + "un tipo método de guardado!", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            }else{
                dato5 = 1;
            }
            if(palabraClave.equals("") == true){
                JOptionPane.showMessageDialog(null, "¡Debe escribir una palabra "
                        + "clave!", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            }else{
                dato6 = 1;
            }
            if(simbolo.equals("") == true){
                JOptionPane.showMessageDialog(null, "¡Debe cargar el alfabeto!",
                        "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            }else{
                dato7 = 1;
            }
            //Aquí se transmite la info.
            if (dato1 != 0 && dato2 != 0 && dato3 != 0 &&  dato5 != 0 &&  dato6 != 0 &&  dato7 != 0) {
                DTOTraduccion capsula = new DTOTraduccion(1,fraseEntrada, tipoTraduccion,
                        tipoAlgo, tipoMetodo, tipoAlfabeto, alfabeto, palabraClave, cifra);
                ArrayList<String> respuesta;
                Controlador mainController = new Controlador(capsula);
                capsula = mainController.realizarTraduccion(capsula);
                respuesta = capsula.getFraseSalida();
                //System.out.println("LO QUE SE ENVIO\n"+capsula.toString());
                if (!respuesta.isEmpty()){
                    printear(capsula);
                }else{
                    JOptionPane.showMessageDialog(null, "¡Error del sistema, vuelva"
                            + " a intentarlo!", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }catch (Exception error){
            JOptionPane.showMessageDialog(null, "¡Error, revise los datos de "
                    + "entrada!", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Metodo que muestra los datos procesados finales en la frase salida del GUI.
     * @param capsula Datos generales y actuales de procesamiento.
     */
    private void printear(DTOTraduccion capsula){
        ArrayList<String> frasesTraducida;
        String result = "";
        frasesTraducida = capsula.getFraseSalida();
        result = "Frase de entrada   = " + capsula.getFraseEntrada() +"\n"
                + "Tipo de Traducción [Codificar = 1 / Decodificar = 2] = "+ String.valueOf(capsula.getTipoTraduccion()+1)+"\n"
                + "Nombre de alfabeto = "+ capsula.getTipoAlfabeto()[0]+"\n"
                + "Alfabeto = "+ capsula.getAlfabeto()+"\n\n";
        for (int i = 0; i <= frasesTraducida.size()-1; i++){
            result += capsula.getTipoAlgoritmos()[i]+" : "+frasesTraducida.get(i)+"\n";
        }       
        salida.setText(result);
    }

    /**
     * Metodo que settea todos los valores iniciales cunado le programa arranca.
     */
    @FXML
    private void initialize() throws IOException {
        entrada.setText("");
        cargarDatosPersistentes();
        SpinnerValueFactory<Integer> cantCaract = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,1000,1);
        SpinnerValueFactory<Integer> cifra = new SpinnerValueFactory.IntegerSpinnerValueFactory(10,99,10);
        this.cantCaracteres.setValueFactory(cantCaract);
        this.cifra.setValueFactory(cifra);
        palabraClave.setText("Encode");
        entrada.setDisable(true);
  }
    
    /**
     * Metodo que carga los metodos de guardado persistentes del sistema.
     */
    public void cargarDatosPersistentes() throws IOException {
        DTOTraduccion capsula = new DTOTraduccion(3);
        Controlador mainController = new Controlador(capsula);
        capsula = mainController.obtenerDatosPersistentes();
        String imprimir = "";
        for (String tipoAlfabeto : capsula.getTipoAlfabeto()) {
            imprimir += tipoAlfabeto.split("~")[0] + "\n";
        }
        imprimir = imprimir.substring(0, imprimir.length()-1);
        tipoAlfabeto.setText(imprimir);
        imprimir = "";
        for (String tipoAlgoritmo1 : capsula.getTipoAlgoritmos()) {
            imprimir += tipoAlgoritmo1 + "\n";
        }
        imprimir = imprimir.substring(0, imprimir.length()-1);
        tipoAlgoritmo.setText(imprimir);
        imprimir = "";
        for (String metodoGuardado : capsula.getMetodoGuardado()) {
            imprimir += metodoGuardado + "\n";
        }
        imprimir = imprimir.substring(0, imprimir.length()-1);
        tipoGuardado.setText(imprimir);
    }
    
    /**
     * Metodo que carga todos los alfabetos, algortimos y metos de guardado dispnibles por el sistema(persistentes).
     * @param event 
     */
    public void clickedActualizar(ActionEvent event) throws IOException {
        cargarDatosPersistentes();
    }

    /**
     * Metodo que pone todos los parametros default como si se hubiera reiniciado el programa.
     * @param event 
     */
    public void clear(ActionEvent event) throws IOException {
        entrada.clear();
        salida.clear();
        rCodificar.setSelected(false);
        rDecodificar.setSelected(false);
        metodo1.setSelected(false);
        metodo2.setSelected(false);
        metodo3.setSelected(false);
        metodo4.setSelected(false);
        cantCaracteres.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1000, 1));
        entrada.setDisable(true);
        simbolo.clear();
        cargarDatosPersistentes();
        
    } 
    
    /**
     * Metodo que escucha el boton de cargar los simbolo en el sistema
     * @param event
     * @throws IOException 
     */
    public void clickedCargar2(ActionEvent event) throws IOException {
        DTOTraduccion capsula = new DTOTraduccion(3);
        Controlador mainController = new Controlador(capsula);
        capsula = mainController.obtenerDatosPersistentes();
        String imprimir = "";        
        String[] into = tipoAlfabeto.getText().split("\n");
        if (into.length == 1){
            for (String tipoAlfabeto : capsula.getTipoAlfabeto()) {
                String nombre = tipoAlfabeto.split("~")[0];
                String entradaNombre = this.tipoAlfabeto.getText().replaceAll("\n", "");
                if (nombre.equals(entradaNombre)){
                    simbolo.setText(tipoAlfabeto.split("~")[1]);
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "¡Error, debe escoger un Alfabet"
                    + "o!", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    /**
     * Metodo que habilita el radiobutton de codificado
     * @param event 
     */
    public void clickedCodi(ActionEvent event) {
        if (rCodificar.isSelected() == true) {
            rDecodificar.setSelected(false);
        }
    }

    /**
     * Metodo que habilita el radiobutton de decodificado
     * @param event 
     */
    public void clickedDecodi(ActionEvent event) {
        if (rDecodificar.isSelected() == true) {
            rCodificar.setSelected(false);
        }
    }
    
    /**
     * Metodo que habilita el radiobutton del metodo 1
     * @param event 
     */
    public void clickedMetodo1(ActionEvent event) {
        if (metodo1.isSelected() == true) {
            metodo2.setSelected(false);
            metodo3.setSelected(false);
            metodo4.setSelected(false);
            entrada.setDisable(true);
        }
    }
    
    /**
     * Metodo que habilita el radiobutton del metodo 2
     * @param event 
     */
    public void clickedMetodo2(ActionEvent event) {
        if (metodo2.isSelected() == true) {
            metodo1.setSelected(false);
            metodo3.setSelected(false);
            metodo4.setSelected(false);
            entrada.setDisable(true);
        }
    }
    
    /**
     * Metodo que habilita el radiobutton del metodo 3
     * @param event 
     */
    public void clickedMetodo3(ActionEvent event) {
        if (metodo3.isSelected() == true) {
            metodo2.setSelected(false);
            metodo1.setSelected(false);
            metodo4.setSelected(false);
            entrada.setDisable(true);
        }
    }
    
    /**
     * Metodo que habilita el radiobutton del metodo 4
     * @param event 
     */
    public void clickedMetodo4(ActionEvent event) {
        if (metodo4.isSelected() == true) {
            metodo2.setSelected(false);
            metodo3.setSelected(false);
            metodo1.setSelected(false);
            entrada.setDisable(false);
        }
    }
    
    /**
     * Metodo que abre el systemfile y muestra los archivos
     * @param event 
     */
    public void clickedVerBitacora(ActionEvent event) throws IOException {
        Controlador mainController = new Controlador(5);
        mainController.procesarAdminActions();
    }
    
    /**
     * Metodo que escucha el boton de agregar alfabeto
     * @param event
     * @throws IOException 
     */
    public void clickedAgregarAlfa(ActionEvent event) throws IOException {
        DTOTraduccion capsula = new DTOTraduccion(3);
        Controlador mainController2 = new Controlador(capsula);
        capsula = mainController2.obtenerDatosPersistentes();
        try{
            String name = JOptionPane.showInputDialog(null, "Escriba el nombre "
                    + "del nuevo alfabeto!\n Nota : no puede ser repetido.");
            if(!name.equals("")){
                String[] into = tipoAlfabeto.getText().split("\n");
                Boolean bandera = false;
                for (String nombre : into) {
                    if(name.equals(nombre)){
                        bandera = true;
                    }
                }
                if(bandera == false){
                    String simbolos = JOptionPane.showInputDialog(null, "Escriba"
                            + " los simbolos del nuevo alfabeto!\n Nota : no pue"
                            + "de haber caracteres repetido.");
                    if(!simbolos.equals("")){
                        for (String tipoAlfabet : capsula.getTipoAlfabeto()) {
                            String nombre = tipoAlfabet.split("~")[1];
                            if(nombre.equals(simbolos)){
                                bandera = true;
                            }                            
                        }
                        if(bandera == false){
                            Controlador mainController = new Controlador(3, name, simbolos);
                            mainController.procesarAdminActions();
                            JOptionPane.showMessageDialog(null, "¡Alfabeto agreg"
                                    + "ado exitosamente!", "", JOptionPane.INFORMATION_MESSAGE);
                        }else{
                             JOptionPane.showMessageDialog(null, "¡Error, simbo"
                                     + "los repetido!", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "¡Error, simbolos no"
                                + " pueden ser vacío!", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "¡Error, nombre repetido"
                            + "!", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "¡Error, nombre no puede ser"
                        + " vacío!", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "¡Error, datos incorrecto!", "Me"
                    + "nsaje de Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    /**
     * Metodo que escucha el boton de eliminar alfabeto
     * @param event
     * @throws IOException 
     */
    public void clickedEliminarAlfa(ActionEvent event) {
        try{
            String name = JOptionPane.showInputDialog(null, "Escriba el nombre "
                    + "del alfabeto que va a eliminar!\n Nota : no puede ser repetido.");
            if(!name.equals("")){
                String[] into = tipoAlfabeto.getText().split("\n");
                Boolean bandera = false;
                for (String nombre : into) {
                    if(name.equals(nombre)){
                        bandera = true;
                    }
                }
                if(bandera == true){
                    Controlador mainController = new Controlador(4,name);
                    mainController.procesarAdminActions();
                    JOptionPane.showMessageDialog(null, "¡Alfabeto eliminado ex"
                            + "itosamente!", "", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(null, "¡Error, nombre no exis"
                            + "te en el sistema!", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "¡Error, nombre no puede ser"
                        + " vacío!", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "¡Error, datos incorrecto!", "Me"
                    + "nsaje de Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Metodo que escucha el boton de agregar algortimo
     * @param event
     * @throws IOException 
     */
    public void clickedAgregarAlgo(ActionEvent event) throws IOException {
        Controlador mainController = new Controlador(1);
        mainController.procesarAdminActions();
    }
    
    /**
     * Metodo que escucha el boton de eliminar algortimo
     * @param event
     * @throws IOException 
     */
    public void clickedEliminarAlgo(ActionEvent event) throws IOException {
        Controlador mainController = new Controlador(2);
        mainController.procesarAdminActions();
    }
    
    /**
     * Metodo que genera una nueva ventana con informacion general del sistema para el usuario
     * @param event 
     */
    public void clickedayuda(ActionEvent event) {
        String resultado = "                                                    *** Centro de Control ***\n"
                + "A continuación, se mostraran la manera en que se debe ejecutar el sistema :\n"
                + " * Tipo de Traducción        : El usuario debe escoger si desea de/codificar.\n"
                + " * Tipo de Algoritmo         : El sistema desplegará los algoritmos disponibles.\n"
                + "                                     Mantenga los algoritmos que quiera aplicar y los otros borrelos.\n"
                + "                                     De ser necesario utilice la palabra clave y la cifra para complemetar los algortimos que lo utilicen.\n"
                + "                                     Nota : cada algoritmo debe estar bien escrito y separado por un cambio de linea.\n"
                + " * Tipo de Alfabeto          : El sistema desplegará los alfabetos disponibles.\n"
                + "                                     Escoga el alfabeto que desee y borre los otros en que no desee.\n"
                + "                                     Nota : debe escoger solo un alfabeto de los que el sistema le suminitre.\n"
                + " * Método de Guardado        : El sistema desplegará los métodos de guardado disponibles.\n"
                + "                                     Mantenga los métodos que quiera aplicar y los otros borrelos.\n"   
                + "                                     Nota : cada método debe estar bien escrito y separado por un cambio de linea.\n"
                + " * Botón de Actualizar       : Presione para actualizar los parámetros disponibles en el sistema.\n"
                + " * Botón de Cagar            : El sistema muestra el alfabeto con el se va a trabajar.\n"
                + " * Generar Frase             : Este segmento de completar los siguientes pasos:\n"
                + "                                     1 - Seleccione la cantidad de caracteres que va tener la frase.\n"
                + "                                     2 - Seleccione alguno de los 3 diferentes métodos de creación de frases.\n"
                + "                                     3 - Si desea crearlo seleccione la 4 opción y esccribala.\n"
                + "                                     4 - Botón Generar : Presione el botón generar para que la frase se cree en la caja de abajo.\n"
                + " * Frase de Salida           : Muestra la frase final de/codificada.\n"
                + " * Botón de Limpiar          : El sistema pone todo los valores default.\n"
                + " * Botón de Ejecutar         : El sistema ejecuta las instruciones que el usuario le suministró.\n"
                + " * Botón de Guardar          : El sistema guarda las frases de salida procesadas.\n";
        JOptionPane.showMessageDialog(null, resultado);
    }
    
    /**
     * Metodo que manda a guardar las frases  generadas según los métodos seleccionados previamente. 4
     * @param event 
     */
    public void clickedGuardar(ActionEvent event) {
        String datosProcesados = salida.getText();
        String[] metodoGuardado = tipoGuardado.getText().split("\n");
        if(!datosProcesados.equals("")&& metodoGuardado.length != 0){
            DTOTraduccion capsula = new DTOTraduccion(4, datosProcesados, metodoGuardado);
            boolean aux = false;
            Controlador mainController = new Controlador(capsula);
            aux = mainController.guardarTraduccion(capsula);
            System.out.println(aux);
            JOptionPane.showMessageDialog(null, "¡Se ha guardo los resultados"
                    + "!","", JOptionPane.INFORMATION_MESSAGE);
        }       
        
    }
      
    /**
     * Metodo que genera la frase de entrada a procesar.
     * @param event 
     */
    public void clickedGenerarFrase(ActionEvent event) {
        try{
            if (!simbolo.getText().equals("")){
                int num = cantCaracteres.getValue();
                if (num < 1){
                    Integer.valueOf("hola");
                }
                try{
                    boolean escogido = false;
                    if (metodo1.isSelected()){ 
                        entrada.setText(mandarGenerar(num,1));
                        escogido = true;
                    }
                    if (metodo2.isSelected()){ 
                        entrada.setText(mandarGenerar(num,2));
                        escogido = true;
                    }
                    if (metodo3.isSelected()){ 
                        entrada.setText(mandarGenerar(num,3));
                        escogido = true;
                    }
                    if(escogido == false){
                        Integer.valueOf("hola");
                    }
                }catch (Exception error){
                JOptionPane.showMessageDialog(null, "Error, debe escoger un método, para generar la frase!","" ,JOptionPane.ERROR_MESSAGE);
                 }
                    
            }else{
                JOptionPane.showMessageDialog(null, "¡Debe cargar los simbolos primero!", "Mensaje de Error", JOptionPane.ERROR_MESSAGE);
            }              
            
            
        }catch(Exception error) {
            JOptionPane.showMessageDialog(null, "Error, la cantidad de caracteres debe ser un entero positivo!","", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    /**
     * Metodo que generaFrases con diferentes metodos
     * @param cantCaracteres numero de caracteres en la frase
     * @param tipoMetodo metodo utilizado
     * @return 
     */
    public String mandarGenerar(int cantCaracteres, int tipoMetodo){
        DTOTraduccion capsula = new DTOTraduccion(2, "", cantCaracteres, tipoMetodo,simbolo.getText());
        Controlador mainController = new Controlador(capsula);
        capsula.setFraseEntrada(mainController.obtenerFrase(capsula));
        return capsula.getFraseEntrada();
    }
}
