/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Dao.DaoReclamo;
import Entidades.Reclamo;
import Utilidades.BeanContent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author Yerminson Gonzalez
 */
@ManagedBean
@SessionScoped
public class BeanReclamo  {

    private Integer ticket = 0;
    private String fecha;
    private String descripcion;
    private String motivo;
    private String estado;
    private String auxiliarRecibe;
    private String usuarioRealiza;
    private Boolean renderTableSearch = false;
    private String tipoPasajero;
    private Boolean isDisableIdentificacion = false;
    private Integer countValidator = 0;
    

    public boolean getRenderTableSearch() {
        return renderTableSearch;
    }

    public void setRenderTableSearch(boolean renderTableSearch) {
        this.renderTableSearch = renderTableSearch;
    }

    public boolean isDisableIdentificacion() {
        return isDisableIdentificacion;
    }

    public void setDisableIdentificacion(boolean isDisableIdentificacion) {
        this.isDisableIdentificacion = isDisableIdentificacion;
    }

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
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
         this.renderTableSearch = true;//debe ser provisional
    }

    public void setAuxiliarRecibe(String auxiliarRecibe) {
        this.auxiliarRecibe = auxiliarRecibe;
    }

    public String getAuxiliarRecibe() {
        return this.auxiliarRecibe;
    }

    public void setUsuarioRealiza(String usuarioRealiza) {
        this.usuarioRealiza = usuarioRealiza;
    }

    public String getUsuarioRealiza() {
        return this.usuarioRealiza;
    }

    public void setTipoPasajero(String tipoPasajero) {

        this.tipoPasajero = tipoPasajero;
        if (this.tipoPasajero.equals("Generica")) {
            this.isDisableIdentificacion = true;
            usuarioRealiza = null;

        } else if (this.tipoPasajero.equals("Personalizada")) {
            this.isDisableIdentificacion = false;
        }



    }

    public String getTipoPasajero() {
        return this.tipoPasajero;
    }

    public String createReClamo() {
        
        validate();
        
        if(countValidator > 0)
        {
            countValidator = 0;
            return null;
        
        }
        
        
        FacesContext context = FacesContext.getCurrentInstance();
        EmployeeHolder empleadoHolder = (EmployeeHolder) context.getApplication().evaluateExpressionGet(context, "#{employeeHolder}", EmployeeHolder.class);
        String idAuxiliar = empleadoHolder.getCurrentEmpleado().getId();
        auxiliarRecibe = idAuxiliar;


        //validate();
        if (context.getMessageList().size() > 0) {
            return null;
        }
        BeanContent content = (BeanContent) context.getApplication().evaluateExpressionGet(context, "#{beanContent}", BeanContent.class);
        int result;
        DaoReclamo daoReclamo = new DaoReclamo();
        Reclamo reclamo = new Reclamo();
        reclamo.setTicket(ticket);

        Date fechaHoy = new Date();
        
        SimpleDateFormat formato = new  SimpleDateFormat("yyyy-MM-dd");
        fecha = formato.format(fechaHoy);
        reclamo.setFecha(fecha);
        System.out.println(fecha);
        reclamo.setDescripcion(descripcion.trim());
        reclamo.setMotivo(motivo.trim());
        estado = "Iniciado";
        reclamo.setEstado(estado);

        reclamo.setAuxiliarRecibe(auxiliarRecibe);
        
        if(tipoPasajero.equals("Generica"))
        {
            usuarioRealiza = "0000001";
        }
        System.out.println(usuarioRealiza);
        reclamo.setUsuarioRealiza(usuarioRealiza);



        System.out.println("Añadiendo reclamo");
        result = daoReclamo.saveReclamo(reclamo);
        if (result == 0) {
            content.setResultOperation("El reclamo no pudo ser creado.");
            return "resultOperation";
        }


        content.setResultOperation("El reclamo fue creado con exito. Con numero de ticket " + daoReclamo.lastTicketId());
        daoReclamo = null;
        clearStates();
        return "resultOperation";
    }

    public List<Reclamo> getFindReclamo() {
        DaoReclamo daoReclamo = new DaoReclamo();
        List<Reclamo> reclamos;
        System.out.println(estado);
        
        reclamos = daoReclamo.findReclamoPorEstado(estado);            
        
        daoReclamo = null;
        
         List<Reclamo> reclamosfiltrados = new ArrayList<Reclamo> ();
         System.out.println("Ticket para filtar "+ticket);
         if(ticket == 0)
         {
         
                reclamosfiltrados = reclamos;
         
         }else
         {
         
             for (int i = 0; i < reclamos.size(); i++) {

                 System.out.println("Ticket para comparar "+reclamos.get(i).getTicket());

                 int valor = reclamos.get(i).getTicket();
                 if ( valor == ticket) {
                     
                     reclamosfiltrados.add(reclamos.get(i));
                     System.out.println("entre");
                 }

             }
         
         }
        
         System.out.println("Tamaño de filtrado "+reclamosfiltrados.size());
        if (reclamosfiltrados.isEmpty()) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("No hay reclamos con este estado."));
            this.renderTableSearch = false;
            return null;
        } else {
            return reclamosfiltrados;
        }
    }

    public List<SelectItem> getAvailableTipoPasajero() 
    {
        List<SelectItem> avaiableTipoUsuario = new ArrayList<SelectItem>();
        avaiableTipoUsuario.add(new SelectItem("Personalizada"));
        avaiableTipoUsuario.add(new SelectItem("Generica"));
        return avaiableTipoUsuario;
    }
    
    public List<SelectItem> getAvailableEstadosReclamo() 
    {
        List<SelectItem> avaliableEstadosReclamo = new ArrayList<SelectItem>();
        avaliableEstadosReclamo.add(new SelectItem("Iniciado"));
        avaliableEstadosReclamo.add(new SelectItem("Tramite"));
        avaliableEstadosReclamo.add(new SelectItem("Finalizado"));
        return avaliableEstadosReclamo;
    }
    
    private void validate() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (motivo.trim().length() > 50) {
            context.addMessage(null, new FacesMessage("El motivo no debe exceder los 15 caracteres."));
            countValidator = 1;
        }
        if(descripcion.trim().length() > 200)
        {
            context.addMessage(null, new FacesMessage("La descripcion no debe exceder los 200 caracteres."));
            countValidator = 1;
        
        }
        
        // Se usara con el dao de reclamo pero debe ser con el dao de usuario Temporal!!!
        DaoReclamo daoReclamo = new DaoReclamo();
        
        if(!daoReclamo.usuarioValido(usuarioRealiza) && usuarioRealiza != null)
        {
            context.addMessage(null, new FacesMessage("El id del usuario  no se encuentra en la base de datos."));
            countValidator = 1;
        
        }
        
        
    
    }
       void clearStates()
    {
        this.ticket = 0;
        this.descripcion = "";
        this.fecha = "";
        this.descripcion = "";
        this.motivo= "";
        this.estado = "";
        this.auxiliarRecibe = "";
        this.usuarioRealiza = "";       
        this.countValidator = 0;
    }
    
   
}
