/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author leonardo
 */
public class Estacion {

    protected  Integer id;
    private  String ubicacion;
    private boolean estado;

    public Estacion() {
    }

    public Estacion(Integer id) {
        this.id = id;
    }

    public Estacion(Integer id, String ubicacion, boolean estado) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estacion)) {
            return false;
        }
        Estacion other = (Estacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Estacion[ id=" + id 
                + " ubicacion=" + ubicacion
                + " estado=" + estado + " ]";
    }
}
