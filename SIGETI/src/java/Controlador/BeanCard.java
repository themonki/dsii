package Controlador;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Felipe
 */
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpSession;
import Utilidades.BeanContent;
import Dao.DaoCard;

@ManagedBean
@SessionScoped

public class BeanCard  implements Serializable {

     
    private String pin;
    private String tipo;
    private String numberPassages;
    private boolean estado;
    private FacesContext context;
    
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
   
    
    
    public String createCard(){
        context = FacesContext.getCurrentInstance();
        //validate();
        if (context.getMessageList().size() > 0) {
            return null;
        }
        BeanContent content = (BeanContent) context.getApplication().evaluateExpressionGet(context, "#{beanContent}", BeanContent.class);
        int result;
        DaoCard daoCard = new DaoCard();
        
        System.err.println("hola "+pin+" tipo:"+tipo+" pasajes  "+ numberPassages);
        content.setResultOperation("El Empleado fue creado con exito.");
       
        /*Bus bus = new Bus();
        bus.setMatricula(matricula.trim());
        bus.setCapacidad(capacidad);
        bus.setEstado(estado);
        bus.setIdInterno(idInterno.trim());
        bus.setPerteneceRuta(perteneceRuta.trim());
        bus.setTipo(tipo.trim());
        
        JOptionPane.showMessageDialog(null, "Vamooss");
        result = daoBus.saveBus(bus);
        if(result == 0){
            content.setResultOperation("El Bus no pudo ser creado.");
            return "resultOperation";
        }            
        
        daoBus = null;
        content.setResultOperation("El Bus fue creado con exito.");*/
         context.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Creado Con Exito ", null));
         
         clearStates();
        return null;
        
    }

    public void clearStates() {


        pin = "";
        tipo = "";
        numberPassages = "";
        estado = false;

    }
    
    

    
    
    
}
