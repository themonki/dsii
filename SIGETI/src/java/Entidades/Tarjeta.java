/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author leonardo
 */
public class Tarjeta {

    protected String pin;
    private Integer saldo;//en numero de pasajes -1 si debe 1 etc..
    private boolean estado;
    private Integer tipo;
    private Integer costo;
    private String fechaVenta;
    private Integer estacionVenta;

    public Tarjeta() {
    }

    public Tarjeta(String pin) {
        this.pin = pin;
    }

    public Tarjeta(String pin, boolean estado) {
        this.pin = pin;
        this.estado = estado;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getCosto() {
        return costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Integer getEstacionVenta() {
        return estacionVenta;
    }

    public void setEstacionVenta(Integer estacionVenta) {
        this.estacionVenta = estacionVenta;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tarjeta)) {
            return false;
        }
        Tarjeta other = (Tarjeta) object;
        if ((this.pin == null && other.pin != null) || (this.pin != null && !this.pin.equals(other.pin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tarjeta[ pin=" + pin 
                + " saldo=" + saldo 
                + " estado=" + estado
                + " tipo=" + tipo
                + " costo=" + costo 
                + " fechaVenta=" + fechaVenta
                + " estacionVenta=" + estacionVenta + " ]";
    }
}
