/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author leonardo
 */
public class TarjetaPersonalizada extends Tarjeta{

    private Integer credito;

    public TarjetaPersonalizada() {
        super();
    }

    public TarjetaPersonalizada(String pin) {
        super(pin);
    }

    public Integer getCredito() {
        return credito;
    }

    public void setCredito(Integer credito) {
        this.credito = credito;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TarjetaPersonalizada)) {
            return false;
        }
        TarjetaPersonalizada other = (TarjetaPersonalizada) object;
        if ((this.pin == null && other.pin != null) || (this.pin != null && !this.pin.equals(other.pin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TarjetaPersonalizada[ pin=" + pin + " credito=" + credito + " ]";
    }
}
