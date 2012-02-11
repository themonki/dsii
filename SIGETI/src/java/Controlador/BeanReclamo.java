/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Dao.DaoReclamo;
import Entidades.Reclamo;
import Utilidades.BeanContent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.swing.JOptionPane;

/**
 *
 * @author Yerminson Gonzalez
 */
@ManagedBean
@RequestScoped
public class BeanReclamo {
    
    private Integer ticket;
    private String fecha;
    private String descripcion;
    private String motivo;
    private String estado;
    private FacesContext context;

   

    public BeanReclamo(Integer ticket) {
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
    public BeanReclamo() {
    }
    
    public String createReClamo(){
        context = FacesContext.getCurrentInstance();
        //validate();
        if (context.getMessageList().size() > 0) {
            return null;
        }
        BeanContent content = (BeanContent) context.getApplication().evaluateExpressionGet(context, "#{beanContent}", BeanContent.class);
        int result;
        DaoReclamo daoBus = new DaoReclamo();
        Reclamo reclamo = new Reclamo();
        reclamo.setTicket(ticket);
        reclamo.setFecha(fecha);
        reclamo.setDescripcion(descripcion.trim());
        reclamo.setMotivo(motivo);
        reclamo.setEstado(estado);
       
        
        JOptionPane.showMessageDialog(null, "Añadiendo reclamo");
        result = daoBus.saveReclamo(reclamo);
        if(result == 0){
            content.setResultOperation("El reclamo no pudo ser creado.");
            return "resultOperation";
        }            
        
        daoBus = null;
        content.setResultOperation("El reclamo fue creado con exito.");
        return "resultOperation";
    }
}
