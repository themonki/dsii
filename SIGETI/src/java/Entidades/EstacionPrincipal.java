/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author leonardo
 */
public class EstacionPrincipal {

    private Integer idEstacion;
    private String nombre;
    private String idOperario;

    public EstacionPrincipal() {
    }

    public EstacionPrincipal(Integer idEstacion) {
        this.idEstacion = idEstacion;
    }

    public Integer getIdEstacion() {
        return idEstacion;
    }

    public void setIdEstacion(Integer idEstacion) {
        this.idEstacion = idEstacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdOperario() {
        return idOperario;
    }

    public void setIdOperario(String idOperario) {
        this.idOperario = idOperario;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstacionPrincipal)) {
            return false;
        }
        EstacionPrincipal other = (EstacionPrincipal) object;
        if ((this.idEstacion == null && other.idEstacion != null) || (this.idEstacion != null && !this.idEstacion.equals(other.idEstacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EstacionPrincipal[ idEstacion=" + idEstacion 
                + " nombre=" + nombre
                + " idOperario=" + idOperario + " ]";
    }
}
