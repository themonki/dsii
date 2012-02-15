    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Dao.DaoEmpleado;
import Dao.DaoEstacion;
import Dao.DaoMedida;
import Dao.DaoReclamo;

import Entidades.Auxiliar;
import Entidades.EstacionPrincipal;
import Entidades.Medida;
import Entidades.Reclamo;
import Utilidades.BeanContent;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

/**
 *
 * @author Yerminson Gonzalez
 */
@ManagedBean
@SessionScoped
public class BeanReclamo implements Serializable {

   

   

    private int ticket = 0;
    private String fecha;
    private String descripcion;

   
   
    private String motivo;
    private String estado;
    private String auxiliarRecibe;
    private String usuarioRealiza;
    private boolean renderTableSearch;
    private boolean rendeEstadoReclamo;
    private String tipoPasajero;
    private boolean disableIdentificacion;
    private int countValidator;
    private String action;
    private String nombreUsuario;
    private String nombreEstacion;
    private List<Medida> medidas;
    private int idMedida;
    private String accionMedida;
    
     public List<Medida> getMedidas() {
        return medidas;
    }

    public void setMedidas(List<Medida> medidas) {
        this.medidas = medidas;
    }
    
     public String getNombreEstacion() {
        return nombreEstacion;
    }

    public void setNombreEstacion(String nombreEstacion) {
        this.nombreEstacion = nombreEstacion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
        this.renderTableSearch = true;//debe ser provisional
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public void setEstado(String estado) {
        this.estado = estado;
        this.renderTableSearch = true;//debe ser provisional
    }

    public void setAuxiliarRecibe(String auxiliarRecibe) {
        this.auxiliarRecibe = auxiliarRecibe;
    }

    public void setUsuarioRealiza(String usuarioRealiza) {
        this.usuarioRealiza = usuarioRealiza;
    }

    public void setRenderTableSearch(boolean renderTableSearch) {
        this.renderTableSearch = renderTableSearch;
    }

    public void setDisableIdentificacion(boolean disableIdentificacion) {
        this.disableIdentificacion = disableIdentificacion;
    }

    public int getTicket() {
        return ticket;
    }

    public String getFecha() {
        return fecha;
    }

    public boolean getDisableIdentificacion() {
        return disableIdentificacion;
    }

    public boolean getRenderTableSearch() {
        return renderTableSearch;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getEstado() {
        return estado;

    }

    public String getAuxiliarRecibe() {
        return this.auxiliarRecibe;
    }

    public String getUsuarioRealiza() {
        return this.usuarioRealiza;
    }

    public void setTipoPasajero(String tipoPasajero) {

        this.tipoPasajero = tipoPasajero;
        if (this.tipoPasajero.equals("Generica")) {
            this.disableIdentificacion = true;
            usuarioRealiza = null;

        } else if (this.tipoPasajero.equals("Personalizada")) {
            this.disableIdentificacion = false;
        }
    }

    public String getTipoPasajero() {
        return this.tipoPasajero;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
     public String getAccionMedida() {
        return accionMedida;
    }

    public void setAccionMedida(String accionMedida) {
        this.accionMedida = accionMedida;
    }

    public int getIdMedida() {
        return idMedida;
    }

    public void setIdMedida(int idMedida) {
        this.idMedida = idMedida;
    }

 
    public BeanReclamo() {

        disableIdentificacion = false;
        countValidator = 0;
        renderTableSearch = false;


    }
     public boolean getRendeEstadoReclamo() {
        return rendeEstadoReclamo;
    }

    public void setRendeEstadoReclamo(boolean rendeEstadoReclamo) {
        this.rendeEstadoReclamo = rendeEstadoReclamo;
    }

    public String createReClamo() {

        validate();

        if (countValidator > 0) {
            countValidator = 0;
            return null;

        }

        FacesContext context = FacesContext.getCurrentInstance();
        EmployeeHolder empleadoHolder = (EmployeeHolder) context.getApplication().evaluateExpressionGet(context, "#{employeeHolder}", EmployeeHolder.class);
        String idAuxiliar = empleadoHolder.getCurrentEmpleado().getId();
        auxiliarRecibe = idAuxiliar;

        if (context.getMessageList().size() > 0) {
            return null;
        }
        BeanContent content = (BeanContent) context.getApplication().evaluateExpressionGet(context, "#{beanContent}", BeanContent.class);
        int result;
        DaoReclamo daoReclamo = new DaoReclamo();
        Reclamo reclamo = new Reclamo();
        reclamo.setTicket(ticket);

        Date fechaHoy = new Date();

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        fecha = formato.format(fechaHoy);
        reclamo.setFecha(fecha);
        System.out.println(fecha);
        reclamo.setDescripcion(descripcion.trim());
        reclamo.setMotivo(motivo.trim());
        estado = "Iniciado";
        reclamo.setEstado(estado);

        reclamo.setAuxiliarRecibe(auxiliarRecibe);

        if (tipoPasajero.equals("Generica")) {
            usuarioRealiza = "0000001";
        }
        System.out.println(usuarioRealiza);
        reclamo.setUsuarioRealiza(usuarioRealiza);



        System.out.println("Añadiendo reclamo");
        result = daoReclamo.saveReclamo(reclamo);
        if (result == 0) {
            content.setResultOperation("El reclamo no pudo ser creado.");
            content.setImage("./resources/fail.png");
            return "resultOperation";
        }


        content.setResultOperation("El reclamo fue creado con exito. Con numero de ticket " + daoReclamo.lastTicketId());
        content.setImage("./resources/ok.png");
        daoReclamo = null;
        // clearStates();
        return "resultOperation";
    }
    
    
    
     public String updateReclamo() {

        

        FacesContext context = FacesContext.getCurrentInstance();
        EmployeeHolder empleadoHolder = (EmployeeHolder) context.getApplication().evaluateExpressionGet(context, "#{employeeHolder}", EmployeeHolder.class);
        String idOperario = empleadoHolder.getCurrentEmpleado().getId();
        

        if (context.getMessageList().size() > 0) {
            return null;
        }
        BeanContent content = (BeanContent) context.getApplication().evaluateExpressionGet(context, "#{beanContent}", BeanContent.class);
        int result;
        DaoReclamo daoReclamo = new DaoReclamo();
        Reclamo reclamo = new Reclamo();
        reclamo.setTicket(ticket);     
        reclamo.setFecha(fecha);       
        reclamo.setDescripcion(descripcion.trim());
        reclamo.setMotivo(motivo.trim());   
        System.out.println(estado);
        reclamo.setEstado(estado);
        reclamo.setAuxiliarRecibe(auxiliarRecibe);      
        reclamo.setUsuarioRealiza(usuarioRealiza);

        DaoMedida daoMedida = new DaoMedida();
        
        daoMedida.insertarMedidasReclamo(medidas, idOperario, ticket);


        System.out.println("Actualizando reclamo");
        result = daoReclamo.updateReclamo(reclamo);
        if (result == -1) {
            content.setResultOperation("El reclamo no pudo ser actualizado.");
            content.setImage("./resources/fail.png");
            return "resultOperation";
        }


        content.setResultOperation("El reclamo fue actualizado con exito. Con numero de ticket " + reclamo.getTicket());
        content.setImage("./resources/ok.png");
        daoReclamo = null;
        // clearStates();
        return "resultOperation";
    }
    
    

    public List<Reclamo> getFindReclamo() {
        DaoReclamo daoReclamo = new DaoReclamo();
        List<Reclamo> reclamos;
        System.out.println(estado);

        reclamos = daoReclamo.findReclamoPorEstado(estado);

        daoReclamo = null;

        List<Reclamo> reclamosfiltrados = new ArrayList<Reclamo>();
        System.out.println("Ticket para filtar " + ticket);
        if (ticket == 0) {

            reclamosfiltrados = reclamos;

        } else {

            for (int i = 0; i < reclamos.size(); i++) {

                System.out.println("Ticket para comparar " + reclamos.get(i).getTicket());

                int valor = reclamos.get(i).getTicket();
                if (valor == ticket) {

                    reclamosfiltrados.add(reclamos.get(i));
                    System.out.println("entre");
                }

            }

        }

        System.out.println("Tamaño de filtrado " + reclamosfiltrados.size());
        if (reclamosfiltrados.isEmpty()) {
            this.renderTableSearch = false;
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("No hay reclamos con este estado."));
            
            return null;
        } else {
            return reclamosfiltrados;
        }
    }

    
     public void getFindMedida() {
        DaoMedida daoMedida = new DaoMedida();
       
        System.out.println(estado);

        medidas = daoMedida.findAllMedidas(ticket);

        daoMedida = null;

      
       
 

        System.out.println("Tamaño de filtrado " + medidas.size());
        if (medidas.isEmpty()) {
            Medida medida = new Medida(0, "Aun no se han añadido medidas para este reclamo");
            
            medidas.add(medida);
        }
            
        
    }

    public List<SelectItem> getAvailableTipoPasajero() {
        List<SelectItem> avaiableTipoUsuario = new ArrayList<SelectItem>();
        avaiableTipoUsuario.add(new SelectItem("Personalizada"));
        avaiableTipoUsuario.add(new SelectItem("Generica"));
        return avaiableTipoUsuario;
    }

    public List<SelectItem> getAvailableEstadosReclamo() {
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
        if (descripcion.trim().length() > 200) {
            context.addMessage(null, new FacesMessage("La descripcion no debe exceder los 200 caracteres."));
            countValidator = 1;

        }

        // Se usara con el dao de reclamo pero debe ser con el dao de usuario Temporal!!!
        DaoReclamo daoReclamo = new DaoReclamo();

        if (daoReclamo.usuarioValido(usuarioRealiza).equals("") && usuarioRealiza != null) {
            context.addMessage(null, new FacesMessage("El id del usuario  no se encuentra en la base de datos."));
            countValidator = 1;

        }



    }
    
    
    public void addMedidaReclamo()
    {
     
        FacesContext context = FacesContext.getCurrentInstance();
        if(medidas.size() == 1)
        {
        
            if(medidas.get(0).getId() == 0)
            {
            
                medidas.remove(0);
                
            }            
            
        }
        
        Medida medida = new Medida();
        
        
        String idCadena = "";
        int indiceFinalId = -1;
        for(int i=0;i<accionMedida.length();i++)
        {
            char letra =accionMedida.charAt(i);
            
            if(letra != '.')
            {
                idCadena += letra;
            
            }else
            {
                indiceFinalId = i+1;
                break;
            
            }           
            
            
        
        }
        
        
        idMedida = Integer.parseInt(idCadena);        
     
        medida.setId(idMedida);
        medida.setAccion(accionMedida.substring(indiceFinalId,accionMedida.length()));
        
        if(medidas.contains(medida))
        {
        
            context.addMessage(null, new FacesMessage("Al reclamo ya se le ha asignado esa medida."));
            
        }else
        {       
             medidas.add(medida); 
             context.addMessage(null, new FacesMessage("Medida agregada con exito recuerde guardar los cambios."));
        }
        
       
        
    }
    
     public void removeMedidaReclamo()
    {
        
        FacesContext context = FacesContext.getCurrentInstance();
        Application app = context.getApplication();
        Medida medida= (Medida) app.evaluateExpressionGet(context, "#{medida}", Medida.class);
      
        
        medidas.remove(medida);
        
        
         if(medidas.isEmpty())
        {
        
            medida = new Medida(0, "Aun no se han añadido medidas para este reclamo");
            
            medidas.add(medida);      
            
        }
        
    }

    public void update(String l) {

        if (l.equals("1")) {
            this.clearStates();

        } else if (l.equals("2")) {
            this.renderTableSearch = false;
            this.rendeEstadoReclamo = true;
             this.clearStates();
            this.action = "Editar";
        } else if (l.equals("3")) {
           
            this.rendeEstadoReclamo = true;
             this.clearStates();
            this.action = "Eliminar";
        } else if (l.equals("4")) {
          
            this.rendeEstadoReclamo = true;
            this.clearStates();
            this.action = "Detalle";
        }else if (l.equals("5")) {
          
            this.rendeEstadoReclamo = false;
            estado = "";
            this.clearStates();
            this.action = "Detalle";
        }
        



        System.out.println("accion actual " + this.action);



    }

    public void statesForFindReturn(ActionEvent e) {
        this.clearStates();
        System.out.println("Retornando de  " + this.action);
    }

    public String getLinkAction() {
        String link = "";
        if (this.action.equals("Detalle")) {
            this.prepareDataClaim();
            link = "detailClaim";
        } else if (this.action.equals("Eliminar")) {
            link = "eraseClaim";
        } else if (this.action.equals("Editar")) {
            this.prepareDataClaim();
            link = "editClaim";
        }

        return link;
    }

    private Reclamo getCurrentReclamo() {
        FacesContext context = FacesContext.getCurrentInstance();
        Application app = context.getApplication();
        Reclamo reclamo = (Reclamo) app.evaluateExpressionGet(context, "#{claim}", Reclamo.class);
        return reclamo;
    }

    public void findReclamoTicket()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        DaoReclamo daoReclamo = new DaoReclamo();
    
        Reclamo reclamo = daoReclamo.findReclamo(ticket);
        
        System.out.println(reclamo);
        
        if(reclamo.getDescripcion() == null)
        {
        
            context.addMessage(null,new FacesMessage("La busqueda no arrojo resultados"));
           
            renderTableSearch = false;
            return;
        
        }else{

        
        
        this.ticket = reclamo.getTicket();
        this.fecha = reclamo.getFecha();
        this.descripcion = reclamo.getDescripcion();
        this.motivo = reclamo.getMotivo();
        this.estado = reclamo.getEstado();
        this.auxiliarRecibe = reclamo.getAuxiliarRecibe();
        this.usuarioRealiza = reclamo.getUsuarioRealiza();
        
       
        
        this.nombreUsuario = daoReclamo.usuarioValido(usuarioRealiza);
        DaoEmpleado daoEmpleado = new DaoEmpleado();
         
        Auxiliar auxiliar = daoEmpleado.findAuxiliarId(this.auxiliarRecibe,true);
        
        DaoEstacion daoEstacion = new DaoEstacion();
        
        EstacionPrincipal estacionPrincipal = daoEstacion.findEstacionPrincipal(auxiliar.getTrabajaEn());
        nombreEstacion = estacionPrincipal.getNombre(); 
        getFindMedida();       
        
        }
    
    
    
    
    }
    
    private void prepareDataClaim() {
        Reclamo reclamo = this.getCurrentReclamo();
        
        System.out.println(reclamo);

        this.ticket = reclamo.getTicket();
        this.fecha = reclamo.getFecha();
        this.descripcion = reclamo.getDescripcion();
        this.motivo = reclamo.getMotivo();
        this.estado = reclamo.getEstado();
        this.auxiliarRecibe = reclamo.getAuxiliarRecibe();
        this.usuarioRealiza = reclamo.getUsuarioRealiza();
        
        DaoReclamo daoReclamo = new DaoReclamo();
        
        this.nombreUsuario = daoReclamo.usuarioValido(usuarioRealiza);
        DaoEmpleado daoEmpleado = new DaoEmpleado();
         
        Auxiliar auxiliar = daoEmpleado.findAuxiliarId(this.auxiliarRecibe,true);
        
        DaoEstacion daoEstacion = new DaoEstacion();
        
        EstacionPrincipal estacionPrincipal = daoEstacion.findEstacionPrincipal(auxiliar.getTrabajaEn());
        nombreEstacion = estacionPrincipal.getNombre(); 
        getFindMedida();
        
        


    }
    
    

     public String findLinkClaims(String l)
    {
        String link = null;
        if(l.equals("1"))
        {
            link = "newClaim";
            
            
           
        }
        else if(l.equals("2"))
        {
            link = "editClaim";
            
            
        }
        else if(l.equals("3"))
        {
            link = "newMeasure";           
           
        }
        else if(l.equals("4"))
        {
            link = "findClaim";        
           
        }
        else if(l.equals("5"))
        {
            link = "deleteClaim";        
           
        }
        
        return link;
    }
    public void clearStates() {
        this.ticket = 0;
        this.descripcion = "";
        this.fecha = "";
        this.descripcion = "";
        this.motivo = "";
        this.estado = "";
        this.auxiliarRecibe = "";
        this.usuarioRealiza = "";
        this.countValidator = 0;
        this.renderTableSearch = false;
    }
}
