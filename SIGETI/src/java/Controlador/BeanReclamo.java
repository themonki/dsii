/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Dao.DaoReclamo;
import Entidades.Reclamo;
import Utilidades.BeanContent;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
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
    
    private boolean isRenderTableSearch = false;
   

    public void setRenderTableSearch(boolean isRenderTableSearch) {
        this.isRenderTableSearch = isRenderTableSearch;
        
    }

    public boolean isRenderTableSearch() {
        return isRenderTableSearch;
    }


   

    public BeanReclamo(Integer ticket) {
        this.ticket = ticket;
    }

    public Integer getTicket() {
        return ticket;
    }

    public void setTicket(Integer ticket) {
        this.ticket = ticket;
        this.isRenderTableSearch =true;//debe ser provicional
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
        FacesContext context = FacesContext.getCurrentInstance();
        //validate();
        if (context.getMessageList().size() > 0) {
            return null;
        }
        BeanContent content = (BeanContent) context.getApplication().evaluateExpressionGet(context, "#{beanContent}", BeanContent.class);
        int result;
        DaoReclamo daoReclamo = new DaoReclamo();
        Reclamo reclamo = new Reclamo();
        reclamo.setTicket(ticket);
        
        Calendar calendario = new GregorianCalendar();
        
        calendario.setTime(new Date());
       
        
        String dia = Integer.toString(calendario.get(Calendar.DATE));
        String mes = Integer.toString(calendario.get(Calendar.MONTH));
        String anio = Integer.toString(Calendar.YEAR);
        
        fecha = ""+anio+"-"+mes+"-"+dia+"";
        
                
        reclamo.setFecha(fecha);
        System.out.println(fecha);
        reclamo.setDescripcion(descripcion.trim());
        reclamo.setMotivo(motivo);
        estado = "Iniciado";
        reclamo.setEstado(estado);
       
        System.out.println("Añadiendo reclamo");
        result = daoReclamo.saveReclamo(reclamo);
        if(result == 0){
            content.setResultOperation("El reclamo no pudo ser creado.");
            return "resultOperation";
        }            
        
       
        content.setResultOperation("El reclamo fue creado con exito. Con numero de ticket "+daoReclamo.lastTicketId());
         daoReclamo = null;
        return "resultOperation";
    }
    
      public List<Reclamo> getFindReclamo()
    {
        DaoReclamo daoReclamo = new DaoReclamo();
        List<Reclamo> reclamos;
        System.out.println(ticket);
        if(this.ticket == null)
        {
            reclamos = daoReclamo.findAllReclamos();
        }else
        {
            Reclamo reclamo = daoReclamo.findReclamo(ticket);
            reclamos = new ArrayList<Reclamo>();
            reclamos.add(reclamo);
        }
        daoReclamo = null;
        if(reclamos.get(0).getTicket() == null)
        {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("No existe empleado con la identificación proporcionada."));
            this.isRenderTableSearch = false;
            return null;
        }else
        {
            return reclamos;
        } 
    }

      public List<SelectItem> getAvailableCargo() {
        
        List<SelectItem> medidasDisponibles = new ArrayList<SelectItem>();

        

        return medidasDisponibles;
    }
}
