/**
 *Descripción: Programa que lleva control de estudiantes. CRUD (Create, Read, Update, Delete).
 *             Guarda los registros en una BDD y archivos.
 * @author Luis Fernando Onofre Pedroza
 * @version 1.0
 * @since 2019/02/23
 */

package alumno;

import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Alumno extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("/alumnos/interfaz/FXMLAlumno.fxml"));
    Scene scene = new Scene(root);
    
    stage.setScene(scene);
    stage.show();
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }
  
}