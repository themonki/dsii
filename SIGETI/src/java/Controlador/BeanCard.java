package Controlador;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Felipe
 */
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import Utilidades.BeanContent;
import Dao.DaoCard;
import Dao.DaoEmpleado;
import Dao.DaoEstacion;
import Entidades.Auxiliar;
import Entidades.EstacionPrincipal;
import Entidades.Tarjeta;

import Entidades.TarjetaPersonalizada;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.faces.model.SelectItem;

@ManagedBean
@SessionScoped
public class BeanCard implements Serializable {

    private String actual = "";
    private String costo = "0";
    private String pin = "";
    private String estacion = "";
    private String tipo ="1";
    private String numberPassages = "0";
    private boolean estado = true;// solo se modifica si se va a borrar ; 
    private String cedulaPasajero = "";
    private String isFind = "false";
    private String fecha = "";
    private int recarga =0;
    private int  credito=0;
    private String isPersonal = "false";
    private String nombrePasajero="";
    private String isFindList = "false";

    public String getIsFindList() {
        return isFindList;
    }

    public void setIsFindList(String isFindList) {
        this.isFindList = isFindList;
    }

    public String getNombrePasajero() {
        return nombrePasajero;
    }

    public void setNombrePasajero(String nombrePasajero) {
        this.nombrePasajero = nombrePasajero;
    }

    public String getIsPersonal() {
        return isPersonal;
    }

    public void setIsPersonal(String isPersonal) {
        this.isPersonal = isPersonal;
    }
    

    public int getCredito() {
        return credito;
    }

    public void setCredito(int credito) {
        this.credito = credito;
    }

    public int getRecarga() {
        return recarga;
    }

    public void setRecarga(int recarga) {
        this.recarga = recarga;
    }
   
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getIsFind() {
        return isFind;
    }

    public void setIsFind(String isFind) {
        this.isFind = isFind;
    }

    public String getCedulaPasajero() {
        return cedulaPasajero;
    }

    public void setCedulaPasajero(String cedulaPasajero) {
        this.cedulaPasajero = cedulaPasajero;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getEstacion() {
        return estacion;
    }

    public void setEstacion(String estacion) {
        this.estacion = estacion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getNumberPassages() {
        return numberPassages;
    }

    public void setNumberPassages(String numberPassages) {
        this.numberPassages = numberPassages;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public String reloadCard(){
    
        FacesContext context;
        context = FacesContext.getCurrentInstance();
        //validate();
        if (context.getMessageList().size() > 0) {
            return null;
        }
        
       
       EmployeeHolder empleadoHolder = (EmployeeHolder) context.getApplication().evaluateExpressionGet(context, "#{employeeHolder}", EmployeeHolder.class);
        String idAuxiliar = empleadoHolder.getCurrentEmpleado().getId();
        

       DaoEmpleado daoEmpleado = new DaoEmpleado();
         
        Auxiliar auxiliar = daoEmpleado.findAuxiliarId(idAuxiliar,true);
        
       
    
        if (recarga<=0){
              context.addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "La recarga debe ser mayor a 0.", null));
              
              return null;
        
        
        }
        DaoCard dao = new DaoCard(); 
        
        
        
        int result = 0;
        
        if(auxiliar==null || auxiliar.getTrabajaEn()==null){
            context.addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "Usted no puede realizar esta operacion.", null));
            return null;
        }else{
            result = dao.reloadCard(pin,recarga,auxiliar.getTrabajaEn());
        }
        
        
        if (result ==0){
             context.addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "La recarga no se realizo.", null));
        
        
        }
        else {
              context.addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "La recarga fue exitosa.", null));
              clearStates();
        
        
        }
           
    
        
        return null;
    
    }
    
    
    

    public String createCard() {
        FacesContext context;
        context = FacesContext.getCurrentInstance();
        //validate();
        if (context.getMessageList().size() > 0) {
            return null;
        }
        BeanContent content = (BeanContent) context.getApplication().evaluateExpressionGet(context, "#{beanContent}", BeanContent.class);
        int result = 0;
        DaoCard daoCard = new DaoCard();





        java.util.Date date = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        fecha = sdf.format(date);


        content.setResultOperation("El Empleado fue creado con exito.");
        content.setImage("./resources/ok.png");
        if (validate()) {



            int tipoTarjeta = Integer.parseInt(tipo);



            Tarjeta tarjeta = new TarjetaPersonalizada();
            tarjeta.setCosto(Integer.parseInt(costo));
            tarjeta.setEstacionVenta(Integer.parseInt(estacion));
            tarjeta.setEstado(true);
            tarjeta.setFechaVenta(fecha);//fecha
            tarjeta.setPin(pin);
            tarjeta.setTipo(tipoTarjeta);
            tarjeta.setSaldo(Integer.parseInt(numberPassages));

            result = daoCard.saveCard(tarjeta);

           


            if (result == 0) {

                context.addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "El registro de la tarjeta no  fue creado ", null));

            } else {
                context.addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "Creado Con Exito ", null));
            }

            clearStates();

        }


        return null;

    }

    public String eraseCard() {
        FacesContext context;

        context = FacesContext.getCurrentInstance();
        //validate();
        if (context.getMessageList().size() > 0) {
            return null;
        }
        BeanContent content = (BeanContent) context.getApplication().evaluateExpressionGet(context, "#{beanContent}", BeanContent.class);
        int result = 0;
        DaoCard daoCard = new DaoCard();

        daoCard.logicalErase(pin);

        context.addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_ERROR, "El registro de la tarjeta fue deshabilitado del sistema", null));






        return null;

    }
    //--------------------------
    
     private List <Tarjeta> tarjetas;

    public List<Tarjeta> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(List<Tarjeta> tarjetas) {
        this.tarjetas = tarjetas;
    }
    //------------------------

    
    public void detalleCard(Tarjeta tarjeta){
        
        
       
        pin=tarjeta.getPin();
           if (tarjeta.getCosto() != null) {
            costo = Integer.toString(tarjeta.getCosto());
        }
        if (tarjeta.getEstacionVenta() != null) {
            estacion = Integer.toString(tarjeta.getEstacionVenta());
        }
        if (tarjeta.getTipo() != null) {
            tipo = Integer.toString(tarjeta.getTipo());
            
        }
        if (tarjeta.getSaldo() != null) {
            numberPassages = Integer.toString(tarjeta.getSaldo());
        }

        fecha = tarjeta.getFechaVenta();
        estado=tarjeta.getEstado();
        
    
    }
    public String findCard() {

        FacesContext context;
        context = FacesContext.getCurrentInstance();
        //validate();
        credito=0;
        isPersonal = "false";
        numberPassages="0";
        isFindList = "false";
       

        

        if (context.getMessageList().size() > 0) {
            return null;
        }
        BeanContent content = (BeanContent) context.getApplication().evaluateExpressionGet(context, "#{beanContent}", BeanContent.class);

        int result = 0;
        DaoCard daoCard = new DaoCard();
        if(cedulaPasajero.equals("")&& pin.equals("") )
        
        {
             tarjetas = daoCard.findCardsFromName(nombrePasajero);
          
             
                
                if(tarjetas!=null&&tarjetas.size()>0)
                isFindList = "true";
             
             return null ; 
            
            
        
        }

        if (!cedulaPasajero.equals("")) {

            String temp;
            temp = daoCard.findCardFromUser(cedulaPasajero);
            if (temp != null) {

                pin = temp;
            }

        }

        Tarjeta tarjeta = daoCard.findCard(pin);
        TarjetaPersonalizada tarjetapersonal= daoCard.findCardCustom(pin);
        
       
       
        
        pin = tarjeta.getPin();

        if (pin == null) {
            context.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "La consulta no arrojo resultados.", null));

            clearStates();

            isFind = "false";
            return null;


        }
        if (tarjeta.getEstado() == false) {
            

            context.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "El registro de la tarjeta fue deshabilitado del sistema", null));
            return null;


        }
        
         if (tarjetapersonal.getCredito()!=null){ isPersonal = "true";credito=tarjetapersonal.getCredito();}



        isFind = "false";
        if (actual.equals("eraseCard") || actual.equals("editCard")) {
            isFind = "true";
        }
        

        if (tarjeta.getCosto() != null) {
            costo = Integer.toString(tarjeta.getCosto());
        }
        if (tarjeta.getEstacionVenta() != null) {
            estacion = Integer.toString(tarjeta.getEstacionVenta());
        }
        if (tarjeta.getTipo() != null) {
            tipo = Integer.toString(tarjeta.getTipo());
            
        }
        if (tarjeta.getSaldo() != null) {
            numberPassages = Integer.toString(tarjeta.getSaldo());
        }

        fecha = tarjeta.getFechaVenta();
        estado=tarjeta.getEstado();



        return null;
    }

    private boolean validate() {
        
           FacesContext context;
        context = FacesContext.getCurrentInstance();
        
        boolean validar = true;

        if (pin.equals("")) {
            context.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "El campo Pin no puede estar vacio.", null));
            validar = false;


        };
        if (estacion.equals("")) {
            context.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "El campo Estacion Id no puede estar vacio.", null));
            validar = false;

        };


        if (validar) {
            try {

                int valor = Integer.parseInt(pin);
                if (!(valor >= 0)) {
                    context.addMessage(null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "El Pin debe ser un numero positivo.", null));
                    validar = false;

                };

            } catch (NumberFormatException e) {
                context.addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "El Pin debe ser un numero.", null));
                validar = false;
            }
            try {

                int valor = Integer.parseInt(numberPassages);
                if (!(valor >= 0)) {
                    context.addMessage(null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "El numero de pasajes debe ser un numero positivo.", null));
                    validar = false;

                }

            } catch (NumberFormatException e) {
                context.addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "El numero de pasajes debe ser un numero.", null));
                validar = false;
            }
            try {


                int valor = Integer.parseInt(estacion);
                if (!(valor >= 0)) {
                    context.addMessage(null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "El id de la estacion debe ser un numero positivo.", null));
                    validar = false;


                }

            } catch (NumberFormatException e) {
                context.addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "El id de la estacion debe ser un numero.", null));
                validar = false;
            }

            try {

                int valor = Integer.parseInt(costo);
                if (!(valor >= 0)) {

                    context.addMessage(null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "El costo de la tarjeta debe ser un numero positivo.", null));

                    validar = false;



                }

            } catch (NumberFormatException e) {
                context.addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "El costo de la tarjeta debe ser un numero.", null));
                validar = false;
            }

        }


        return validar;


    }
    public String editCard(){
           FacesContext context;
      
        
          context = FacesContext.getCurrentInstance();
        //validate();
        if (context.getMessageList().size() > 0) {
            return null;
        }
        BeanContent content = (BeanContent) context.getApplication().evaluateExpressionGet(context, "#{beanContent}", BeanContent.class);
        int result = 0;
        DaoCard daoCard = new DaoCard();
        
        
        
        
        Tarjeta tarjeta = new TarjetaPersonalizada();
        tarjeta.setCosto(Integer.parseInt(costo));
        tarjeta.setEstacionVenta(Integer.parseInt(estacion));
        tarjeta.setEstado(true);
        tarjeta.setFechaVenta(fecha);//fecha
        tarjeta.setPin(pin);
        tarjeta.setTipo(Integer.parseInt(tipo));
        tarjeta.setSaldo(Integer.parseInt(numberPassages));

        daoCard.editCard(tarjeta);

        context.addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_ERROR, "La modificiacion fue exitosa", null));
        

        isFind="false";
        clearStates();
         
        return null; 
    
    }

    public void refresh(String actual) {

        if (!this.actual.equals(actual)) {

            clearStates();


            this.actual = actual;

        };

    }

    public void clearStates() {


        if (tarjetas!=null){
            tarjetas.clear();
        };
        pin = "";
        tipo = "";
        numberPassages = "0";
        estado = true;
        estacion = "";
        costo = "0";
        fecha = "";
        cedulaPasajero="";
        recarga=0;
        credito=0;
        nombrePasajero="";
        isFindList="false";
        isFind="false";

    }
    
    public List<SelectItem> getAvailableEstacionPrincipal() {
        List<SelectItem> availableEstacion = new ArrayList<SelectItem>();

        DaoEstacion daoEstacion = new DaoEstacion();
        List<EstacionPrincipal> estaciones = daoEstacion.findAllEstacionPrincipal();
        daoEstacion = null;

        for (int i = 0; i < estaciones.size(); i++) {
            EstacionPrincipal estacion_ = estaciones.get(i);
            availableEstacion.add(new SelectItem(Integer.toString(estacion_.getId()), estacion_.getNombre()));
        }
        return availableEstacion;
    }
}
