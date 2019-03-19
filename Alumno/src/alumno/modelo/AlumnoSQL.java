/**
 *Descripción: Programa que contiene metodos con sentencias SQL
 * @author Luis Fernando Onofre Pedroza
 * @version 1.0
 * @since 2019/02/23
 */

package alumno.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class AlumnoSQL extends Estudiante implements DAOEstudiante{

    public AlumnoSQL(String matricula, String nombre, String paterno, String materno) {
        super(matricula, nombre, paterno, materno);
    }

  /**
   * Metodo que agrega a un estudiante en la base datos.
   * @param connection una conección con la base de datos.
   * @return Lo que devuelve es la cantidad de registros afectados
   */
    @Override
  public int agregarEstudiante(Connection connection){
    try {
      PreparedStatement instruccion = connection.prepareStatement("INSERT INTO estudiante(matricula, nombre, "
          + "paterno, materno) VALUES (?, ?, ?, ?)");
      instruccion.setString(1, matricula.get());//Sustituir el signo de interrogacion por lo que contiene la propiedad de matricula
      instruccion.setString(2, nombre.get());
      instruccion.setString(3, paterno.get());
      instruccion.setString(4, materno.get());
      return instruccion.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(AlumnoSQL.class.getName()).log(Level.SEVERE, null, ex);
      Alert mensaje = new Alert(Alert.AlertType.ERROR);
                mensaje.setContentText("Matricula Duplicada");
                mensaje.showAndWait();
      return 0;
      
    }
  }
  
  /**
   * Metodo que modifica a un estudiante en la base datos.
   * @param connection una conección con la base de datos.
   * @return Lo que devuelve es la cantidad de registros afectados
   */
    @Override
  public int modificarEstudiante(Connection connection){
    try {
      PreparedStatement instruccion = connection.prepareStatement("UPDATE estudiante SET nombre = ?, paterno = ?, materno = ? WHERE matricula = ?");
      instruccion.setString(1, nombre.get()); //Sustituir el signo de interrogacion por lo que contiene la propiedad de matricula
      instruccion.setString(2, paterno.get());
      instruccion.setString(3, materno.get());
      instruccion.setString(4, matricula.get());
      return instruccion.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(AlumnoSQL.class.getName()).log(Level.SEVERE, null, ex);
      return 0;
    }
  }
  
  /**
   * Metodo que elimina a un estudiante en la base datos.
   * @param connection una conección con la base de datos.
   * @return 
   */
    @Override
  public int eliminarEstudiante(Connection connection){
    try {
      PreparedStatement instruccion = connection.prepareStatement("DELETE FROM estudiante WHERE matricula = ?");
      instruccion.setString(1, matricula.get());
      return instruccion.executeUpdate(); //Lo que devuelve es la cantidad de registros afectados
    } catch (SQLException ex) {
      Logger.getLogger(AlumnoSQL.class.getName()).log(Level.SEVERE, null, ex);
      return 0;
    }
  }
  
  /**
   * Metodo que llena la tabla con registros de la base de datos.
   * @param connection una conección con la base de datos.
   * @param listaEstudiante arrayList de tipo estudiante.
   */
  public static void llenarTabla(Connection connection, ObservableList<AlumnoSQL>listaEstudiante){
      try {
        Statement statement = connection.createStatement();
        ResultSet resultado = statement.executeQuery("SELECT matricula, nombre, paterno, materno FROM estudiante");
        while(resultado.next()){
          listaEstudiante.add(new AlumnoSQL(
              resultado.getString("matricula"),
              resultado.getString("nombre"),
              resultado.getString("paterno"),
              resultado.getString("materno")
            )
          );
        }
      } catch (SQLException ex) {
        Logger.getLogger(AlumnoSQL.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
  
 }
