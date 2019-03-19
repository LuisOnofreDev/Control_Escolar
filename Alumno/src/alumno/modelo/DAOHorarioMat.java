/**
 *Descripción: Interfaz con los metodos SQL de Horario Materia
 *             Guarda los registros en una BDD y archivos.
 * @author Luis Fernando Onofre Pedroza
 * @version 1.0
 * @since 2019/03/17
 */
package alumno.modelo;

import java.sql.Connection;

public interface DAOHorarioMat {
    public int agregarHorarioMateria(Connection connection);
    public int eliminarHorarioMateria(Connection connection);
}
