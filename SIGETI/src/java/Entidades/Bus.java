/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

/**
 *
 * @author leonardo
 */
public class Bus {

    private String matricula;
    private String tipo;
    private Integer capacidad;
    private String idInterno;
    private boolean estado;
    private String perteneceRuta;
    private String estadoFisico;

    public String getEstadoFisico() {
        return estadoFisico;
    }

    public void setEstadoFisico(String estadoFisico) {
        this.estadoFisico = estadoFisico;
    }

    public Bus() {
    }

    public Bus(String matricula) {
        this.matricula = matricula;
    }

    public Bus(String matricula, String idInterno, boolean estado) {
        this.matricula = matricula;
        this.idInterno = idInterno;
        this.estado = estado;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getTipo() {
        return tipo;
    }

    public String getPerteneceRuta() {
        return perteneceRuta;
    }
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public String getIdInterno() {
        return idInterno;
    }

    public void setIdInterno(String idInterno) {
        this.idInterno = idInterno;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public void setPerteneceRuta(String perteneceRuta) {
        this.perteneceRuta = perteneceRuta;
    }
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bus)) {
            return false;
        }
        Bus other = (Bus) object;
        if ((this.matricula == null && other.matricula != null) || (this.matricula != null && !this.matricula.equals(other.matricula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Bus[ matricula=" + matricula + " tipo=" + tipo + " capacidad=" + capacidad
                + " idInterno=" + idInterno + " estado=" + estado + " estadoFisico=" + estadoFisico + " ]";
    }
}
