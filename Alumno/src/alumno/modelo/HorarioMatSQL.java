/**
 *Descripción: Programa que contiene metodos con sentencias SQL para Horario Materia
 * @author Luis Fernando Onofre Pedroza
 * @version 1.0
 * @since 2019/03/17
 */
package alumno.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;


public class HorarioMatSQL extends HorarioMat implements DAOHorarioMat{
    public HorarioMatSQL(String nrc, String salon, String horaInicio, String horaFin, String dia) {
        super(nrc, salon, horaInicio, horaFin, dia);
    }
    
    /**
     * Metodo que agrega un horario en la base datos.
     * @param connection una conección con la base de datos.
     * @return Lo que devuelve es la cantidad de registros afectados.
     */
    @Override
    public int agregarHorarioMateria(Connection connection){
    try {
      PreparedStatement instruccion = connection.prepareStatement("INSERT INTO horarioMat (salon,"
              + " horaInicio, horaFin, dia, idmateria) SELECT * FROM (SELECT ? , ? , ? , ? ,(SELECT "
              + "idmateria FROM materia WHERE nrc= ? )) AS tmp WHERE NOT EXISTS (SELECT salon, "
              + "horaInicio, horaFin, dia FROM horarioMat WHERE (dia = ? AND idmateria=(SELECT "
              + "idmateria FROM materia WHERE nrc= ? )) OR (salon= ? AND horaInicio= ? AND horaFin= ? "
              + "AND dia = ? )) LIMIT 1;");
      instruccion.setString(1, salon.get());//Sustituir el signo de interrogacion por lo que contiene
                                          //la propiedad de matricula
      instruccion.setString(2, horaInicio.get());
      instruccion.setString(3, horaFin.get());
      instruccion.setString(4, dia.get());
      instruccion.setString(5, nrc.get());
      instruccion.setString(6, dia.get());
      instruccion.setString(7, nrc.get());
      instruccion.setString(8, salon.get());
      instruccion.setString(9, horaInicio.get());
      instruccion.setString(10, horaFin.get());
      instruccion.setString(11, dia.get());
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
     * Metodo que elimina un horario en la base datos.
     * @param connection una conección con la base de datos.
     * @return Lo que devuelve es la cantidad de registros afectados.
     */  
    @Override
    public int eliminarHorarioMateria(Connection connection){
        try {
         PreparedStatement instruccion = connection.prepareStatement("DELETE FROM horarioMat WHERE "
              + "idmateria = (SELECT idmateria FROM materia WHERE nrc= ?) AND dia = ?");
         instruccion.setString(1, nrc.get());
         instruccion.setString(2, dia.get());
         return instruccion.executeUpdate(); //Lo que devuelve es la cantidad de registros afectados
        } catch (SQLException ex) {
        Logger.getLogger(MateriaSQL.class.getName()).log(Level.SEVERE, null, ex);
        return 0;
    }
  }
    
   /**
   * Metodo que llena la tabla con registros de la base de datos.
   * @param connection una conección con la base de datos.
   * @param listaHorarioMateria arrayList de tipo materia.
   */
    public static void llenarTabla(Connection connection, ObservableList<HorarioMatSQL>
            listaHorarioMateria){
      try {
        Statement statement = connection.createStatement();
        ResultSet resultado = statement.executeQuery("SELECT m.nrc, h.salon, h.horaInicio, "
                + "h.horaFin, h.dia FROM materia m, horarioMat h where h.idmateria=m.idmateria");
        while(resultado.next()){
              listaHorarioMateria.add(new HorarioMatSQL(
              resultado.getString("nrc"),
              resultado.getString("salon"),
              resultado.getString("horaInicio"),
              resultado.getString("horaFin"),
              resultado.getString("dia")
            )
          );
        }
      } catch (SQLException ex) {
        Logger.getLogger(HorarioMatSQL.class.getName()).log(Level.SEVERE, null, ex);
      }
  }  
}

