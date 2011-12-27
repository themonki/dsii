/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author leonardo
 */
public class TarjetaGenerica extends Tarjeta {

    public TarjetaGenerica() {
        super();
    }

    public TarjetaGenerica(String pin) {
        super(pin);
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TarjetaGenerica)) {
            return false;
        }
        TarjetaGenerica other = (TarjetaGenerica) object;
        if ((this.pin == null && other.pin != null) || (this.pin != null && !this.pin.equals(other.pin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TarjetaGenerica[ pin=" + pin + " ]";
    }
}
