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
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

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
    private String auxiliarRecibe;
    private String usuarioRealiza;
    private boolean isRenderTableSearch;
    private String tipoPasajero;
    private boolean isDisableIdentificacion;

    public boolean isRenderTableSearch() {
        return isRenderTableSearch;
    }

    public void setRenderTableSearch(boolean isRenderTableSearch) {
        this.isRenderTableSearch = isRenderTableSearch;
    }

    public boolean isDisableIdentificacion() {
        return isDisableIdentificacion;
    }

    public void setDisableIdentificacion(boolean isDisableIdentificacion) {
        this.isDisableIdentificacion = isDisableIdentificacion;
    }

    public Integer getTicket() {
        return ticket;
    }

    public void setTicket(Integer ticket) {
        this.ticket = ticket;
        this.isRenderTableSearch = true;//debe ser provisional
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

        } else if (this.tipoPasajero.equals("Personalizada")) {
            this.isDisableIdentificacion = false;
        }



    }

    public String getTipoPasajero() {
        return this.tipoPasajero;
    }

    public String createReClamo() {
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

        Calendar calendario = Calendar.getInstance();

        String dia = Integer.toString(calendario.get(Calendar.DATE));
        String mes = Integer.toString(calendario.get(Calendar.MONTH));
        String anio = Integer.toString(Calendar.YEAR);

        fecha = "" + anio + "-" + mes + "-" + dia + "";

        reclamo.setFecha(fecha);
        System.out.println(fecha);
        reclamo.setDescripcion(descripcion.trim());
        reclamo.setMotivo(motivo.trim());
        estado = "Iniciado";
        reclamo.setEstado(estado);

        reclamo.setAuxiliarRecibe(auxiliarRecibe);
        
        if(usuarioRealiza == null)
        {
            usuarioRealiza = "0000001";
        }
        reclamo.setUsuarioRealiza(usuarioRealiza);



        System.out.println("Añadiendo reclamo");
        result = daoReclamo.saveReclamo(reclamo);
        if (result == 0) {
            content.setResultOperation("El reclamo no pudo ser creado.");
            return "resultOperation";
        }


        content.setResultOperation("El reclamo fue creado con exito. Con numero de ticket " + daoReclamo.lastTicketId());
        daoReclamo = null;
        return "resultOperation";
    }

    public List<Reclamo> getFindReclamo() {
        DaoReclamo daoReclamo = new DaoReclamo();
        List<Reclamo> reclamos;
        System.out.println(ticket);
        if (this.ticket == null) {
            reclamos = daoReclamo.findAllReclamos();
        } else {
            Reclamo reclamo = daoReclamo.findReclamo(ticket);
            reclamos = new ArrayList<Reclamo>();
            reclamos.add(reclamo);
        }
        daoReclamo = null;
        if (reclamos.get(0).getTicket() == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("No existe empleado con la identificación proporcionada."));
            this.isRenderTableSearch = false;
            return null;
        } else {
            return reclamos;
        }
    }

    public List<SelectItem> getAvailableTipoPasajero() 
    {
        List<SelectItem> avaiableTipoUsuario = new ArrayList<SelectItem>();
        avaiableTipoUsuario.add(new SelectItem("Personalizada"));
        avaiableTipoUsuario.add(new SelectItem("Generica"));
        return avaiableTipoUsuario;
    }
}
