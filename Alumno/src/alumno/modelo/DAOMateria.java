/**
 *Descripción: Interfaz con los metodos SQL de Materia
 *             Guarda los registros en una BDD y archivos.
 * @author Luis Fernando Onofre Pedroza
 * @version 1.0
 * @since 2019/02/23
 */
package alumno.modelo;

import java.sql.Connection;

public interface DAOMateria{
     public int agregarMateria(Connection connection);
     public int modificarMateria(Connection connection);
     public int eliminarMateria(Connection connection);
}