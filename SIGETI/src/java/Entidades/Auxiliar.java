/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author leonardo
 */
public class Auxiliar {

    public static String id;
    public String idJefe;
    public Integer trabajaEn;

    public Auxiliar() {
    }

    public Auxiliar(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdJefe() {
        return idJefe;
    }

    public void setIdJefe(String idJefe) {
        this.idJefe = idJefe;
    }

    public int getTrabajaEn() {
        return trabajaEn;
    }

    public void setTrabajaEn(Integer trabajaEn) {
        this.trabajaEn = trabajaEn;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Auxiliar)) {
            return false;
        }
        Auxiliar other = (Auxiliar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Auxiliar[ id=" + id + " idJefe=" + idJefe + " trabajaEn=" + trabajaEn + " ]";
    }
}
