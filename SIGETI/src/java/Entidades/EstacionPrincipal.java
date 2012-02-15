/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author leonardo
 */
public class EstacionPrincipal extends Estacion{

    private String nombre;
    private String idOperario;

    public EstacionPrincipal() {
        super();
    }

    public EstacionPrincipal(Integer idEstacion) {
        super(idEstacion);
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
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id 
                + "." + nombre
               ;
    }
}
