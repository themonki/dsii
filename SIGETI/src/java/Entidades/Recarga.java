/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author leonardo
 */
public class Recarga {

    private String pin;
    private Integer idEstacion;
    private String fecha;
    private String hora;
    private Integer valor;

    public Recarga(String pin, Integer idEstacion, String fecha, String hora) {
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
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
