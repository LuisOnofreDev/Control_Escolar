/**
 *Descripción: Programa que contiene metodos con sentencias SQL para Materia
 * @author Luis Fernando Onofre Pedroza
 * @version 1.0
 * @since 2019/03/14
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

public class MateriaSQL extends Materia implements DAOMateria{

    public MateriaSQL(String nrc, String nombre, String creditos, String horasTeoricas, String 
            horasPracticas) {
        super(nrc, nombre, creditos, horasTeoricas, horasPracticas);
    }

  /**
   * Metodo que agrega a una materia en la base datos.
   * @param connection una conección con la base de datos.
   * @return Lo que devuelve es la cantidad de registros afectados
   */
    @Override
  public int agregarMateria(Connection connection){
    try {
      PreparedStatement instruccion = connection.prepareStatement("INSERT INTO materia(nrc, nombre,"
          + " creditos, horasTeoricas, horasPracticas) VALUES (?, ?, ?, ?, ?)");
      instruccion.setString(1, nrc.get());//Sustituir el signo de interrogacion por lo que contiene
                                          //la propiedad de matricula
      instruccion.setString(2, nombre.get());
      instruccion.setString(3, creditos.get());
      instruccion.setString(4, horasTeoricas.get());
      instruccion.setString(5, horasPracticas.get());
      return instruccion.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(MateriaSQL.class.getName()).log(Level.SEVERE, null, ex);
      Alert mensaje = new Alert(Alert.AlertType.ERROR);
                mensaje.setContentText("Error");
                mensaje.showAndWait();
      return 0;
      
    }
  }
  
  /**
   * Metodo que modifica a una materia en la base datos.
   * @param connection una conección con la base de datos.
   * @return Lo que devuelve es la cantidad de registros afectados
   */
    @Override
  public int modificarMateria(Connection connection){
    try {
      PreparedStatement instruccion = connection.prepareStatement("UPDATE materia SET nombre = ?"
              + ", creditos = ?, horasTeoricas = ?, horasPracticas = ? WHERE nrc = ?");
      instruccion.setString(1, nombre.get()); //Sustituir el signo de interrogacion por lo que 
                                              //contiene la propiedad de matricula
      instruccion.setString(2, creditos.get());
      instruccion.setString(3, horasTeoricas.get());
      instruccion.setString(4, horasPracticas.get());
      instruccion.setString(5, nrc.get());
      return instruccion.executeUpdate();
    } catch (SQLException ex) {
      Logger.getLogger(MateriaSQL.class.getName()).log(Level.SEVERE, null, ex);
      return 0;
    }
  }
  
  /**
   * Metodo que elimina a una materia en la base datos.
   * @param connection una conección con la base de datos.
   * @return 
   */
    @Override
  public int eliminarMateria(Connection connection){
    try {
      PreparedStatement instruccion = connection.prepareStatement("DELETE FROM materia WHERE nrc = "
              + "?");
      instruccion.setString(1, nrc.get());
      return instruccion.executeUpdate(); //Lo que devuelve es la cantidad de registros afectados
    } catch (SQLException ex) {
      Logger.getLogger(MateriaSQL.class.getName()).log(Level.SEVERE, null, ex);
      return 0;
    }
  }
  
  /**
   * Metodo que llena la tabla con registros de la base de datos.
   * @param connection una conección con la base de datos.
   * @param listaMateria arrayList de tipo materia.
   */
  public static void llenarTabla(Connection connection, ObservableList<MateriaSQL>listaMateria){
      try {
        Statement statement = connection.createStatement();
        ResultSet resultado = statement.executeQuery("SELECT nrc, nombre, creditos, horasTeoricas,"
                + " horasPracticas FROM materia");
        while(resultado.next()){
          listaMateria.add(new MateriaSQL(
              resultado.getString("nrc"),
              resultado.getString("nombre"),
              resultado.getString("creditos"),
              resultado.getString("horasTeoricas"),
              resultado.getString("horasPracticas")
            )
          );
        }
      } catch (SQLException ex) {
        Logger.getLogger(MateriaSQL.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
  
 }
