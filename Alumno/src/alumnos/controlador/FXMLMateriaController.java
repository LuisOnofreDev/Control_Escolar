/**
 *Descripción: Controlador del FXML de la interfaz Materia
 * @author Luis Fernando Onofre Pedroza
 * @version 1.0
 * @since 2019/03/14
 */

package alumnos.controlador;

import alumno.modelo.MateriaSQL;
import alumno.modelo.conexionMSQL;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class FXMLMateriaController {
    private ObservableList <MateriaSQL> listaMateria;
    
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
    private TableView<MateriaSQL> tablaMaterias;

    @FXML
    private TableColumn<MateriaSQL, String> tableColumnCreditos;

    @FXML
    private TableColumn<MateriaSQL, String> tableColumnHPracticas;

    @FXML
    private TableColumn<MateriaSQL, String> tableColumnHTeoricas;

    @FXML
    private TableColumn<MateriaSQL, String> tableColumnNRC;

    @FXML
    private TableColumn<MateriaSQL, String> tableColumnNombreMat;

    @FXML
    private TextField textCreditos;

    @FXML
    private TextField textHPracticas;

    @FXML
    private TextField textHTeoricas;

    @FXML
    private TextField textNRC;

    @FXML
    private TextField textNombreMat;


    @FXML
    void initialize() {
        assert buttonAgregar != null : "fx:id=\"buttonAgregar\" was not injected: check your FXML file 'FXMLMateria.fxml'.";
        assert buttonEditar != null : "fx:id=\"buttonEditar\" was not injected: check your FXML file 'FXMLMateria.fxml'.";
        assert buttonEliminar != null : "fx:id=\"buttonEliminar\" was not injected: check your FXML file 'FXMLMateria.fxml'.";
        assert buttonGuardar != null : "fx:id=\"buttonGuardar\" was not injected: check your FXML file 'FXMLMateria.fxml'.";
        assert buttonLimpiar != null : "fx:id=\"buttonLimpiar\" was not injected: check your FXML file 'FXMLMateria.fxml'.";
        assert buttonSalir != null : "fx:id=\"buttonSalir\" was not injected: check your FXML file 'FXMLMateria.fxml'.";
        assert tablaMaterias != null : "fx:id=\"tablaMaterias\" was not injected: check your FXML file 'FXMLMateria.fxml'.";
        assert tableColumnCreditos != null : "fx:id=\"tableColumnCreditos\" was not injected: check your FXML file 'FXMLMateria.fxml'.";
        assert tableColumnHPracticas != null : "fx:id=\"tableColumnHPracticas\" was not injected: check your FXML file 'FXMLMateria.fxml'.";
        assert tableColumnHTeoricas != null : "fx:id=\"tableColumnHTeoricas\" was not injected: check your FXML file 'FXMLMateria.fxml'.";
        assert tableColumnNRC != null : "fx:id=\"tableColumnNRC\" was not injected: check your FXML file 'FXMLMateria.fxml'.";
        assert tableColumnNombreMat != null : "fx:id=\"tableColumnNombreMat\" was not injected: check your FXML file 'FXMLMateria.fxml'.";
        assert textCreditos != null : "fx:id=\"textCreditos\" was not injected: check your FXML file 'FXMLMateria.fxml'.";
        assert textHPracticas != null : "fx:id=\"textHPracticas\" was not injected: check your FXML file 'FXMLMateria.fxml'.";
        assert textHTeoricas != null : "fx:id=\"textHTeoricas\" was not injected: check your FXML file 'FXMLMateria.fxml'.";
        assert textNRC != null : "fx:id=\"textNRC\" was not injected: check your FXML file 'FXMLMateria.fxml'.";
        assert textNombreMat != null : "fx:id=\"textNombreMat\" was not injected: check your FXML file 'FXMLMateria.fxml'.";

        conexion = new conexionMSQL();
        conexion.establecerConexion();
        listaMateria= FXCollections.observableArrayList();
        //Llenar lista
        MateriaSQL.llenarTabla(conexion.getConnection(), listaMateria);
        //Enlazarla con la tabla
        tablaMaterias.setItems(listaMateria);
        tableColumnNRC.setCellValueFactory(new PropertyValueFactory<MateriaSQL, String>
        ("nrc"));
        tableColumnNombreMat.setCellValueFactory(new PropertyValueFactory<MateriaSQL, String>
        ("nombre"));
        tableColumnCreditos.setCellValueFactory(new PropertyValueFactory<MateriaSQL, String>
        ("creditos"));
        tableColumnHTeoricas.setCellValueFactory(new PropertyValueFactory<MateriaSQL, String>
        ("horasTeoricas"));
        tableColumnHPracticas.setCellValueFactory(new PropertyValueFactory<MateriaSQL, String>
        ("horasPracticas"));
        escuchador();
        conexion.cerrarConexion();
    }
    
    
     /**
     * Metodo que sirve como Listener cada que seleccionamos un registro.
     */
    public void escuchador(){
          tablaMaterias.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<MateriaSQL>(){
              @Override
              public void changed(ObservableValue<? extends MateriaSQL> observable, MateriaSQL valorAnterior
                      , MateriaSQL valorSeleccionado) {
                      if(valorSeleccionado!=null){
                      textNRC.setDisable(true);
                      textNRC.setText(valorSeleccionado.getNrc());
                      textNombreMat.setText(valorSeleccionado.getNombre());
                      textCreditos.setText(valorSeleccionado.getCreditos());
                      textHTeoricas.setText(valorSeleccionado.getHorasTeoricas());
                      textHPracticas.setText(valorSeleccionado.getHorasPracticas());
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
      textNRC.setText(null);
      textNombreMat.setText(null);
      textCreditos.setText(null);
      textHTeoricas.setText(null);
      textHPracticas.setText(null);
      buttonAgregar.setDisable(false);
      buttonEliminar.setDisable(true);
      buttonEditar.setDisable(true);
      textNRC.setDisable(false);
      initialize();
    }
    
    /**
     * Metodo que agrega un estudiante a la base de datos.
     */
    public void agregar(){
        if(textNRC.getText()==null || textNRC.getText()==""){
                Alert mensaje = new Alert(Alert.AlertType.ERROR);
                mensaje.setContentText("¡Faltan valores en los campos! >:c");
                mensaje.showAndWait();
        }else{
            MateriaSQL estudiante = new MateriaSQL(textNRC.getText(),
            textNombreMat.getText(), textCreditos.getText(), textHTeoricas.getText(), 
                    textHPracticas.getText());
            conexion.establecerConexion();
            int resultado = estudiante.agregarMateria(conexion.getConnection());
            conexion.cerrarConexion();
            if(resultado==1){
                listaMateria.add(estudiante);
                Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                mensaje.setContentText("Materia agregada");
                mensaje.show();
                limpiar();
            }
        }
    }
    
    /**
     * Metodo que modifica un registro.
     */
    public void editar(){
        if(textNRC.getText()==null || textNRC.getText()==""){
                Alert mensaje = new Alert(Alert.AlertType.ERROR);
                mensaje.setContentText("¡No hay Materias! >:c");
                mensaje.showAndWait();
        }else{
            MateriaSQL materia = new MateriaSQL(textNRC.getText(),
            textNombreMat.getText(), textCreditos.getText(), textHTeoricas.getText(), textHPracticas.getText());
            conexion.establecerConexion();
            int resultado = materia.modificarMateria(conexion.getConnection());
            conexion.cerrarConexion();
            if(resultado==1){
                listaMateria.set(tablaMaterias.getSelectionModel().getSelectedIndex(),materia);
                Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                mensaje.setContentText("Materia editada");
                mensaje.show();
                limpiar();
            }
        }
    }
    
    /**
     * Metodo que elimina un registro seleccionado.
     */
    public void eliminar(){
        if(textNRC.getText()==null || textNRC.getText()==""){
                Alert mensaje = new Alert(Alert.AlertType.ERROR);
                mensaje.setContentText("¡No hay materias! >:c");
                mensaje.showAndWait();
        }else{
            conexion.establecerConexion();
            int resultado = tablaMaterias.getSelectionModel().getSelectedItem().eliminarMateria
        (conexion.getConnection());
            conexion.cerrarConexion();
            if(resultado==1){
                listaMateria.remove(tablaMaterias.getSelectionModel().getSelectedIndex());
                Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                mensaje.setContentText("materia eliminada");
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
            ObjectOutputStream ois = new ObjectOutputStream (new FileOutputStream ("Materia.obj")
            );
            int tamano= listaMateria.size();
            for(int i=0; i<tamano; i++){
                ois.writeObject(listaMateria.get(i));
            }
            ois.close();
            Alert mensaje = new Alert(Alert.AlertType.ERROR);
                mensaje.setContentText("No se guardó la información.");
                mensaje.show();
        }catch(IOException ee1){
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                mensaje.setContentText("Materia archivado.");
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
     * Metodo que muestra la ventana de Estudiante
     * @throws Exception 
     */
    public void ventanaEstudiante() throws Exception {
        Stage stage= new Stage();
        Parent root1 = FXMLLoader.load(getClass().getResource("/alumnos/interfaz/FXMLAlumno.fxml"));
        Scene scene1 = new Scene(root1);

        stage.setScene(scene1);
        stage.show();
    }

}