/**
 *Descripción: Interfaz con los metodos SQL
 *             Guarda los registros en una BDD y archivos.
 * @author Luis Fernando Onofre Pedroza
 * @version 1.0
 * @since 2019/02/23
 */
package alumno.modelo;

import java.sql.Connection;

public interface DAOEstudiante {
     public int agregarEstudiante(Connection connection);
     public int modificarEstudiante(Connection connection);
     public int eliminarEstudiante(Connection connection);
}
