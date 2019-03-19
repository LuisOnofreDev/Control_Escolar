/**
 *Descripción: Programa de la Materia
 * @author Luis Fernando Onofre Pedroza
 * @version 1.0
 * @since 2019/03/14
 */

package alumno.modelo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Materia {
    protected StringProperty nrc;
    protected StringProperty nombre;
    protected StringProperty creditos;
    protected StringProperty horasTeoricas;
    protected StringProperty horasPracticas;

    public Materia(String nrc, String nombre, String creditos, String horasTeoricas, String horasPracticas) {
        this.nrc = new SimpleStringProperty(nrc);
        this.nombre = new SimpleStringProperty(nombre);
        this.creditos = new SimpleStringProperty (creditos);
        this.horasTeoricas = new SimpleStringProperty (horasTeoricas);
        this.horasPracticas = new SimpleStringProperty (horasPracticas);
    }

    public String getNrc() {
        return nrc.get();
    }

    public String getNombre() {
        return nombre.get();
    }

    public String getCreditos() {
        return creditos.get();
    }

    public String getHorasTeoricas() {
        return horasTeoricas.get();
    }

    public String getHorasPracticas() {
        return horasPracticas.get();
    }

    public void setNrc(String nrc) {
        this.nrc = new SimpleStringProperty (nrc);
    }

    public void setNombre(String nombre) {
        this.nombre = new SimpleStringProperty (nombre);
    }

    public void setCreditos(String creditos) {
        this.creditos = new SimpleStringProperty (creditos);
    }

    public void setHorasTeoricas(String horasTeoricas) {
        this.horasTeoricas = new SimpleStringProperty (horasTeoricas);
    }

    public void setHorasPracticas(String horasPracticas) {
        this.horasPracticas = new SimpleStringProperty (horasPracticas);
    }

    @Override
    public String toString() {
        return "Materia{" + "nrc=" + nrc + ", nombre=" + nombre + ", creditos=" + creditos + ", horasTeoricas=" + horasTeoricas + ", horasPracticas=" + horasPracticas + '}';
    }
    
    
    
}
