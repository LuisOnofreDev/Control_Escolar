/**
 *Descripción: Conexion entre el programa y la base de datos.
 * @author Luis Fernando Onofre Pedroza
 * @version 1.0
 * @since 2019/02/23
 */

package alumno.modelo;


import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class conexionMSQL {
    private Connection connection;
    private String url = "jdbc:mysql://localhost/Alumnos";
    private String usuario = "root";
    private String contrasena = "LUISfernando05";

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Metodo que establece la concexion con la base de datos.
     */
    public void establecerConexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection(url, usuario, contrasena);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(conexionMSQL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(conexionMSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo que cierra la conexion con la base de datos.
     */
    public void cerrarConexion(){
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(conexionMSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
