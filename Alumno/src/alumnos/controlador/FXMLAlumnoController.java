/**
 *Descripción: Controlador del FXML del programa Alumno
 * @author Luis Fernando Onofre Pedroza
 * @version 1.0
 * @since 2019/02/23
 */

package alumnos.controlador;

import alumno.modelo.conexionMSQL;
import alumno.modelo.AlumnoSQL;
import java.awt.event.ActionEvent;
import java.beans.EventHandler;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class FXMLAlumnoController {
    private ObservableList <AlumnoSQL> listaAlumnos;
    
    private conexionMSQL conexion;
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonAgregar;

    @FXML
    private Button buttonEditar;

    @FXML
    private Button buttonEliminar;

    @FXML
    private Button buttonGuardar;

    @FXML
    private Button buttonLimpiar;

    @FXML
    private Button buttonSalir;

    @FXML
    private Label label;
    
    @FXML
    private TableView<AlumnoSQL> tablaAlumnos;

    @FXML
    private TableColumn<AlumnoSQL, String> tableColumnMaterno;

    @FXML
    private TableColumn<AlumnoSQL, String> tableColumnMatricula;

    @FXML
    private TableColumn<AlumnoSQL, String> tableColumnNombre;

    @FXML
    private TableColumn<AlumnoSQL, String> tableColumnPaterno;

    @FXML
    private TextField textApellidoMaterno;

    @FXML
    private TextField textApellidoPaterno;

    @FXML
    private TextField textMatricula;

    @FXML
    private TextField textNombre;


    @FXML
    //Metodo que inicializa todos los elementos.
    void initialize() {
        assert buttonAgregar != null : "fx:id=\"buttonAgregar\" was not injected: check your FXML file 'FXMLAlumno.fxml'.";
        assert buttonEditar != null : "fx:id=\"buttonEditar\" was not injected: check your FXML file 'FXMLAlumno.fxml'.";
        assert buttonEliminar != null : "fx:id=\"buttonEliminar\" was not injected: check your FXML file 'FXMLAlumno.fxml'.";
        assert buttonGuardar != null : "fx:id=\"buttonGuardar\" was not injected: check your FXML file 'FXMLAlumno.fxml'.";
        assert buttonLimpiar != null : "fx:id=\"buttonLimpiar\" was not injected: check your FXML file 'FXMLAlumno.fxml'.";
        assert buttonSalir != null : "fx:id=\"buttonSalir\" was not injected: check your FXML file 'FXMLAlumno.fxml'.";
        assert label != null : "fx:id=\"label\" was not injected: check your FXML file 'FXMLAlumno.fxml'.";
        assert tablaAlumnos != null : "fx:id=\"tablaAlumnos\" was not injected: check your FXML file 'FXMLAlumno.fxml'.";
        assert tableColumnMaterno != null : "fx:id=\"tableColumnMaterno\" was not injected: check your FXML file 'FXMLAlumno.fxml'.";
        assert tableColumnMatricula != null : "fx:id=\"tableColumnMatricula\" was not injected: check your FXML file 'FXMLAlumno.fxml'.";
        assert tableColumnNombre != null : "fx:id=\"tableColumnNombre\" was not injected: check your FXML file 'FXMLAlumno.fxml'.";
        assert tableColumnPaterno != null : "fx:id=\"tableColumnPaterno\" was not injected: check your FXML file 'FXMLAlumno.fxml'.";
        assert textApellidoMaterno != null : "fx:id=\"textApellidoMaterno\" was not injected: check your FXML file 'FXMLAlumno.fxml'.";
        assert textApellidoPaterno != null : "fx:id=\"textApellidoPaterno\" was not injected: check your FXML file 'FXMLAlumno.fxml'.";
        assert textMatricula != null : "fx:id=\"textMatricula\" was not injected: check your FXML file 'FXMLAlumno.fxml'.";
        assert textNombre != null : "fx:id=\"textNombre\" was not injected: check your FXML file 'FXMLAlumno.fxml'.";

        conexion = new conexionMSQL();
        conexion.establecerConexion();
        listaAlumnos= FXCollections.observableArrayList();
        //Llenar lista
        AlumnoSQL.llenarTabla(conexion.getConnection(), listaAlumnos);
        //Enlazarla con la tabla
        tablaAlumnos.setItems(listaAlumnos);
        tableColumnMatricula.setCellValueFactory(new PropertyValueFactory<AlumnoSQL, String>
        ("matricula"));
        tableColumnNombre.setCellValueFactory(new PropertyValueFactory<AlumnoSQL, String>
        ("nombre"));
        tableColumnPaterno.setCellValueFactory(new PropertyValueFactory<AlumnoSQL, String>
        ("paterno"));
        tableColumnMaterno.setCellValueFactory(new PropertyValueFactory<AlumnoSQL, String>
        ("materno"));
        escuchador();
        conexion.cerrarConexion();
    }
    
    /**
     * Metodo que sirve como Listener cada que seleccionamos un registro.
     */
public void escuchador(){
      tablaAlumnos.getSelectionModel().selectedItemProperty().addListener(
      new ChangeListener<AlumnoSQL>(){
        @Override
        public void changed(ObservableValue<? extends AlumnoSQL> observable, AlumnoSQL valorAnterior
                , AlumnoSQL valorSeleccionado) {
          if(valorSeleccionado!=null){
          textMatricula.setDisable(true);
          textMatricula.setText(valorSeleccionado.getMatricula());
          textNombre.setText(valorSeleccionado.getNombre());
          textApellidoPaterno.setText(valorSeleccionado.getPaterno());
          textApellidoMaterno.setText(valorSeleccionado.getMaterno());
          buttonAgregar.setDisable(true);
          buttonEliminar.setDisable(false);
          buttonEditar.setDisable(false);
          }
        }  
      }
      );
         
    }
    
/**
 * Metodo para limpiar el texto que hay en los campos de texto.
 */
    public void limpiar(){
      textMatricula.setText(null);
      textNombre.setText(null);
      textApellidoPaterno.setText(null);
      textApellidoMaterno.setText(null);
      buttonAgregar.setDisable(false);
      buttonEliminar.setDisable(true);
      buttonEditar.setDisable(true);
      textMatricula.setDisable(false);
      initialize();
    }
    
    /**
     * Metodo que agrega un estudiante a la base de datos.
     */
    public void agregar(){
        if(textMatricula.getText()==null || textMatricula.getText()==""){
                Alert mensaje = new Alert(Alert.AlertType.ERROR);
                mensaje.setContentText("¡Faltan valores en los campos! >:c");
                mensaje.showAndWait();
        }else{
            AlumnoSQL estudiante = new AlumnoSQL(textMatricula.getText(),
            textNombre.getText(), textApellidoPaterno.getText(), textApellidoMaterno.getText());
            conexion.establecerConexion();
            int resultado = estudiante.agregarEstudiante(conexion.getConnection());
            conexion.cerrarConexion();
            if(resultado==1){
                listaAlumnos.add(estudiante);
                Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                mensaje.setContentText("Estudiante agregado");
                mensaje.show();
                limpiar();
            }
        }
    }
    
    /**
     * Metodo que modifica un registro.
     */
    public void editar(){
        if(textMatricula.getText()==null || textMatricula.getText()==""){
                Alert mensaje = new Alert(Alert.AlertType.ERROR);
                mensaje.setContentText("¡No hay estudiantes! >:c");
                mensaje.showAndWait();
        }else{
            AlumnoSQL estudiante = new AlumnoSQL(textMatricula.getText(),
            textNombre.getText(), textApellidoPaterno.getText(), textApellidoMaterno.getText());
            conexion.establecerConexion();
            int resultado = estudiante.modificarEstudiante(conexion.getConnection());
            conexion.cerrarConexion();
            if(resultado==1){
                listaAlumnos.set(tablaAlumnos.getSelectionModel().getSelectedIndex(),estudiante);
                Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                mensaje.setContentText("Estudiante editado");
                mensaje.show();
                limpiar();
            }
        }
    }
    
    /**
     * Metodo que elimina un registro seleccionado.
     */
    public void eliminar(){
        if(textMatricula.getText()==null || textMatricula.getText()==""){
                Alert mensaje = new Alert(Alert.AlertType.ERROR);
                mensaje.setContentText("¡No hay estudiantes! >:c");
                mensaje.showAndWait();
        }else{
            conexion.establecerConexion();
            int resultado = tablaAlumnos.getSelectionModel().getSelectedItem().eliminarEstudiante
                (conexion.getConnection());
            conexion.cerrarConexion();
            if(resultado==1){
                listaAlumnos.remove(tablaAlumnos.getSelectionModel().getSelectedIndex());
                Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                mensaje.setContentText("Estudiante eliminado");
                mensaje.show();
                limpiar();
            } 
        }
    }
    
    /**
     * Metodo que guarda los registros en un archivo.
     */
    public void guardarEnArchivo(){
        try{
            ObjectOutputStream ois = new ObjectOutputStream (new FileOutputStream ("Estudiante.obj")
            );
            int tamano= listaAlumnos.size();
            for(int i=0; i<tamano; i++){
                ois.writeObject(listaAlumnos.get(i));
            }
            ois.close();
            Alert mensaje = new Alert(Alert.AlertType.ERROR);
                mensaje.setContentText("No se guardó la información.");
                mensaje.show();
        }catch(IOException ee1){
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                mensaje.setContentText("Estudiante archivado.");
                mensaje.show();
        }
    }
    
    /**
     * Metodo que cierra el programa.
     */
    public void exit(){
        Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
        mensaje.setContentText("¡Hasta Pronto! :D");
        mensaje.showAndWait();
        System.exit(0);
    }
    
    /**
     * Metodo que muestra la ventana de Materia
     * @throws Exception 
     */
    public void ventanaMateria() throws Exception {
        Stage stage= new Stage();
        Parent root2 = FXMLLoader.load(getClass().getResource("/alumnos/interfaz/FXMLMateria.fxml"));
        Scene scene2 = new Scene(root2);

        stage.setScene(scene2);
        stage.show();
    }
    
    public void ventanaHorarioMateria() throws Exception {
        Stage stage= new Stage();
        Parent root2 = FXMLLoader.load(getClass().getResource("/alumnos/interfaz/FXMLHorarioMat.fxml"));
        Scene scene2 = new Scene(root2);

        stage.setScene(scene2);
        stage.show();
    }
}
