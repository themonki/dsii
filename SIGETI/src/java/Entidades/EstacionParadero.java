/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author leonardo
 */
public class EstacionParadero extends Estacion{

    public EstacionParadero() {
        super();
    }

    public EstacionParadero(Integer idEstacion) {
        super(idEstacion);
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstacionParadero)) {
            return false;
        }
        EstacionParadero other = (EstacionParadero) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  id + "." +super.getUbicacion();
    }
}
