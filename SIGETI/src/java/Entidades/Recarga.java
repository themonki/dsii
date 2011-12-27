/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author leonardo
 */
public class Recarga {

    public String pin;
    public Integer idEstacion;
    public Date fecha;
    public Time hora;
    public Integer valor;

    public Recarga(String pin, Integer idEstacion, Date fecha, Time hora) {
        this.pin = pin;
        this.idEstacion = idEstacion;
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Integer getIdEstacion() {
        return idEstacion;
    }

    public void setIdEstacion(Integer idEstacion) {
        this.idEstacion = idEstacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }


    @Override
    public String toString() {
        return "Recarga[ " + "pin=" + pin 
                + " idEstacion=" + idEstacion
                + " fecha=" + fecha
                + " hora=" + hora
                + " valor=" + valor  + " ]";
    }
}
