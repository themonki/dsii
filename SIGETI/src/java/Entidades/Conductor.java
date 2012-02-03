/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author leonardo
 */
public class Conductor extends Empleado{

    private String licencia;
    private String conduceBus;

    public void setConduceBus(String conduceBus) {
        this.conduceBus = conduceBus;
    }

    public String getConduceBus() {
        return conduceBus;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public String getLicencia() {
        return licencia;
    }
    
    public Conductor() {
        super();
    }

    public Conductor(String id) {
        super(id);
        this.id = id;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conductor)) {
            return false;
        }
        Conductor other = (Conductor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Conductor[ id=" + id + " ]";
    }
}