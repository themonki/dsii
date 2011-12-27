/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author leonardo
 */
public class EstacionParadero {

    private Integer idEstacion;

    public EstacionParadero() {
    }

    public EstacionParadero(Integer idEstacion) {
        this.idEstacion = idEstacion;
    }

    public Integer getIdEstacion() {
        return idEstacion;
    }

    public void setIdEstacion(Integer idEstacion) {
        this.idEstacion = idEstacion;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstacionParadero)) {
            return false;
        }
        EstacionParadero other = (EstacionParadero) object;
        if ((this.idEstacion == null && other.idEstacion != null) || (this.idEstacion != null && !this.idEstacion.equals(other.idEstacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EstacionParadero[ idEstacion=" + idEstacion + " ]";
    }
}
