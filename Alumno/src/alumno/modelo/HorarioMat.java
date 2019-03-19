/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alumno.modelo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class HorarioMat {
    protected StringProperty nrc;
    protected StringProperty salon;
    protected StringProperty horaInicio;
    protected StringProperty horaFin;
    protected StringProperty dia;

    public HorarioMat(String nrc, String salon, String horaInicio, String horaFin, String dia) {
        this.nrc = new SimpleStringProperty (nrc);
        this.salon = new SimpleStringProperty(salon);
        this.horaInicio = new SimpleStringProperty(horaInicio);
        this.horaFin = new SimpleStringProperty(horaFin);
        this.dia = new SimpleStringProperty(dia);
    }

    public String getNrc() {
        return nrc.get();
    }

    public String getSalon() {
        return salon.get();
    }

    public String getHoraInicio() {
        return horaInicio.get();
    }

    public String getHoraFin() {
        return horaFin.get();
    }

    public String getDia() {
        return dia.get();
    }

    public void setNrc(String nrc) {
        this.nrc = new SimpleStringProperty(nrc);
    }

    public void setSalon(String salon) {
        this.salon = new SimpleStringProperty(salon);
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = new SimpleStringProperty(horaInicio);
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = new SimpleStringProperty(horaFin);
    }

    public void setDia(String dia) {
        this.dia = new SimpleStringProperty(dia);
    }

    @Override
    public String toString() {
        return "HorarioMat{" + "nrc=" + nrc + ", salon=" + salon + ", horaInicio=" + horaInicio + ", horaFin=" + horaFin + ", dia=" + dia + '}';
    }
    
    
}
