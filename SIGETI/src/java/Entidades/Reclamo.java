/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;

/**
 *
 * @author leonardo
 */
public class Reclamo implements Serializable{

    private Integer ticket;
    private String fecha;
    private String descripcion;
    private String motivo;
    private String estado;
    private String auxiliarRecibe;
    private String usuarioRealiza;

    public Reclamo() {
    }

    public Reclamo(Integer ticket) {
        this.ticket = ticket;
    }

    public Integer getTicket() {
        return ticket;
    }

    public void setTicket(Integer ticket) {
        this.ticket = ticket;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
       
    public void setAuxiliarRecibe(String auxiliarRecibe)
    {
        this.auxiliarRecibe = auxiliarRecibe;                
    }
    
    public String getAuxiliarRecibe()
    {
           return this.auxiliarRecibe;
    }

    public void setUsuarioRealiza(String usuarioRealiza)
    {
        this.usuarioRealiza = usuarioRealiza;                
    }
    
    public String getUsuarioRealiza()
    {
           return this.usuarioRealiza;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reclamo)) {
            return false;
        }
        Reclamo other = (Reclamo) object;
        if ((this.ticket == null && other.ticket != null) || (this.ticket != null && !this.ticket.equals(other.ticket))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Reclamo[ ticket=" + ticket 
                + " fecha=" + fecha
                + " descripcion=" + descripcion
                + " motivo=" + motivo 
                + " estado=" + estado  + " ]";
    }
}
