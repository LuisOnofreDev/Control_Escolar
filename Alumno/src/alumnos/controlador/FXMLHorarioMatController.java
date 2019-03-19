/**
 *Descripción: Controlador del FXML de la interfaz Horario Materia
 * @author Luis Fernando Onofre Pedroza
 * @version 1.0
 * @since 2019/03/17
 */
package alumnos.controlador;

import alumno.modelo.HorarioMatSQL;
import alumno.modelo.MateriaSQL;
import alumno.modelo.conexionMSQL;
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
import javafx.stage.Stage;


public class FXMLHorarioMatController {

    
    private ObservableList <MateriaSQL> listaMateria;
    
    private ObservableList <HorarioMatSQL> listaHorarioMateria;
    
    private conexionMSQL conexion;
    
    @FXML
    private Button ButtonAgregar;

    @FXML
    private Button ButtonEditar;

    @FXML
    private Button ButtonEliminar;

    @FXML
    private Button ButtonSalir;

    @FXML
    private Button buttonLimpiar;
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<HorarioMatSQL> tablaHorarioMat;
    
    @FXML
    private TableView<MateriaSQL> tablaMateria;

    @FXML
    private TableColumn<HorarioMatSQL,String> tableColumnDia;

    @FXML
    private TableColumn<HorarioMatSQL,String> tableColumnHoraFin;

    @FXML
    private TableColumn<HorarioMatSQL,String> tableColumnHoraInicio;

    @FXML
    private TableColumn<MateriaSQL,String> tableColumnNRCEE;

    @FXML
    private TableColumn<HorarioMatSQL,String> tableColumnNRCH;

    @FXML
    private TableColumn<MateriaSQL,String> tableColumnNombreEE;

    @FXML
    private TableColumn<HorarioMatSQL,String> tableColumnSalon;

    @FXML
    private TextField textDia;

    @FXML
    private TextField textHoraFin;

    @FXML
    private TextField textHoraInicio;

    @FXML
    private TextField textNRC;

    @FXML
    private TextField textSalon;


    @FXML
    void initialize() {
        assert ButtonAgregar != null : "fx:id=\"ButtonAgregar\" was not injected: check your FXML file 'FXMLHorarioMat.fxml'.";
        assert ButtonEditar != null : "fx:id=\"ButtonEditar\" was not injected: check your FXML file 'FXMLHorarioMat.fxml'.";
        assert ButtonEliminar != null : "fx:id=\"ButtonEliminar\" was not injected: check your FXML file 'FXMLHorarioMat.fxml'.";
        assert ButtonSalir != null : "fx:id=\"ButtonSalir\" was not injected: check your FXML file 'FXMLHorarioMat.fxml'.";
        assert buttonLimpiar != null : "fx:id=\"buttonLimpiar\" was not injected: check your FXML file 'FXMLHorarioMat.fxml'.";
        assert tablaHorarioMat != null : "fx:id=\"tablaHorarioMat\" was not injected: check your FXML file 'FXMLHorarioMat.fxml'.";
        assert tablaMateria != null : "fx:id=\"tablaMateria\" was not injected: check your FXML file 'FXMLHorarioMat.fxml'.";
        assert tableColumnDia != null : "fx:id=\"tableColumnDia\" was not injected: check your FXML file 'FXMLHorarioMat.fxml'.";
        assert tableColumnHoraFin != null : "fx:id=\"tableColumnHoraFin\" was not injected: check your FXML file 'FXMLHorarioMat.fxml'.";
        assert tableColumnHoraInicio != null : "fx:id=\"tableColumnHoraInicio\" was not injected: check your FXML file 'FXMLHorarioMat.fxml'.";
        assert tableColumnNRCEE != null : "fx:id=\"tableColumnNRCEE\" was not injected: check your FXML file 'FXMLHorarioMat.fxml'.";
        assert tableColumnNRCH != null : "fx:id=\"tableColumnNRCH\" was not injected: check your FXML file 'FXMLHorarioMat.fxml'.";
        assert tableColumnNombreEE != null : "fx:id=\"tableColumnNombreEE\" was not injected: check your FXML file 'FXMLHorarioMat.fxml'.";
        assert tableColumnSalon != null : "fx:id=\"tableColumnSalon\" was not injected: check your FXML file 'FXMLHorarioMat.fxml'.";
        assert textDia != null : "fx:id=\"textDia\" was not injected: check your FXML file 'FXMLHorarioMat.fxml'.";
        assert textHoraFin != null : "fx:id=\"textHoraFin\" was not injected: check your FXML file 'FXMLHorarioMat.fxml'.";
        assert textHoraInicio != null : "fx:id=\"textHoraInicio\" was not injected: check your FXML file 'FXMLHorarioMat.fxml'.";
        assert textNRC != null : "fx:id=\"textNRC\" was not injected: check your FXML file 'FXMLHorarioMat.fxml'.";
        assert textSalon != null : "fx:id=\"textSalon\" was not injected: check your FXML file 'FXMLHorarioMat.fxml'.";

        conexion = new conexionMSQL();
        conexion.establecerConexion();
        listaMateria= FXCollections.observableArrayList();
        //Llenar lista
        MateriaSQL.llenarTabla(conexion.getConnection(), listaMateria);
        //Enlazarla con la tabla
        tablaMateria.setItems(listaMateria);
        tableColumnNRCEE.setCellValueFactory(new PropertyValueFactory<MateriaSQL, String>
        ("nrc"));
        tableColumnNombreEE.setCellValueFactory(new PropertyValueFactory<MateriaSQL, String>
        ("nombre"));
        conexion.cerrarConexion();
        
        conexion = new conexionMSQL();
        conexion.establecerConexion();
        listaHorarioMateria= FXCollections.observableArrayList();
        //Llenar lista
        HorarioMatSQL.llenarTabla(conexion.getConnection(), listaHorarioMateria);
        //Enlazarla con la tabla
        tablaHorarioMat.setItems(listaHorarioMateria);
        tableColumnNRCH.setCellValueFactory(new PropertyValueFactory<HorarioMatSQL, String>
        ("nrc"));
        tableColumnSalon.setCellValueFactory(new PropertyValueFactory<HorarioMatSQL, String>
        ("salon"));
        tableColumnHoraInicio.setCellValueFactory(new PropertyValueFactory<HorarioMatSQL, String>
        ("horaInicio"));
        tableColumnHoraFin.setCellValueFactory(new PropertyValueFactory<HorarioMatSQL, String>
        ("horaFin"));
        tableColumnDia.setCellValueFactory(new PropertyValueFactory<HorarioMatSQL, String>
        ("dia"));
        escuchador();
        conexion.cerrarConexion();
    }
    
     /**
     * Metodo que sirve como Listener cada que seleccionamos un registro.
     */
    public void escuchador(){
          tablaHorarioMat.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<HorarioMatSQL>(){
              @Override
              public void changed(ObservableValue<? extends HorarioMatSQL> observable, HorarioMatSQL 
                      valorAnterior, HorarioMatSQL valorSeleccionado) {
                      if(valorSeleccionado!=null){
                      //textNRC.setDisable(true);
                      textNRC.setText(valorSeleccionado.getNrc());
                      textSalon.setText(valorSeleccionado.getSalon());
                      textHoraInicio.setText(valorSeleccionado.getHoraInicio());
                      textHoraFin.setText(valorSeleccionado.getHoraFin());
                      textDia.setText(valorSeleccionado.getDia());
                      ButtonAgregar.setDisable(false);
                      ButtonEliminar.setDisable(false);
                      ButtonEditar.setDisable(false);
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
      textSalon.setText(null);
      textHoraInicio.setText(null);
      textHoraFin.setText(null);
      textDia.setText(null);
      ButtonAgregar.setDisable(false);
      ButtonEliminar.setDisable(true);
      ButtonEditar.setDisable(true);
      initialize();
    }
    
    /**
     * Metodo que agrega un horario de materia a la base de datos.
     */
    public void agregar(){
        if(textNRC.getText()==null || textNRC.getText()==""){
                Alert mensaje = new Alert(Alert.AlertType.ERROR);
                mensaje.setContentText("¡Faltan valores en los campos! >:c");
                mensaje.showAndWait();
        }else{
            HorarioMatSQL horarioM = new HorarioMatSQL(textNRC.getText(),
            textSalon.getText(), textHoraInicio.getText(), textHoraFin.getText(), 
                    textDia.getText());
            conexion.establecerConexion();
            int resultado = horarioM.agregarHorarioMateria(conexion.getConnection());
            conexion.cerrarConexion();
            if(resultado==1){
                listaHorarioMateria.add(horarioM);
                Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                mensaje.setContentText("Horario de Materia agregado");
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
                mensaje.setContentText("No existe el registro! >:c");
                mensaje.showAndWait();
        }else{
            conexion.establecerConexion();
            int resultado = tablaHorarioMat.getSelectionModel().getSelectedItem().eliminarHorarioMateria
        (conexion.getConnection());
            conexion.cerrarConexion();
            if(resultado==1){
                listaHorarioMateria.remove(tablaHorarioMat.getSelectionModel().getSelectedIndex());
                Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                mensaje.setContentText("Horario de materia eliminado");
                mensaje.show();
                limpiar();
            } 
        }
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