/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Dao.DaoMedida;
import Entidades.Medida;
import Utilidades.BeanContent;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author yerminson
 */
@ManagedBean
@RequestScoped
public class BeanMedida implements Serializable{
    
    private Integer id;
    private String accion;
    private int countValidator ;

    public BeanMedida() {
    }

  

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    
    public String createMedida() {

        validate();

        if (countValidator > 0) {
            countValidator = 0;
            return null;

        }

        FacesContext context = FacesContext.getCurrentInstance();
       
        
        

        if (context.getMessageList().size() > 0) {
            return null;
        }
        BeanContent content = (BeanContent) context.getApplication().evaluateExpressionGet(context, "#{beanContent}", BeanContent.class);
        int result;
        DaoMedida daoMedida = new DaoMedida();
        Medida medida = new Medida();
      
        medida.setAccion(accion);

      

        System.out.println("Añadiendo reclamo");
        result = daoMedida.saveMedida(medida);
        if (result == 0) {
            content.setResultOperation("La medida no pudo ser creado.");
            content.setImage("./resources/fail.png");
            return "resultOperation";
        }


        content.setResultOperation("La medida fue creada con exito. Con numero de identificacion " + daoMedida.lastTicketId());
        content.setImage("./resources/ok.png");
        daoMedida = null;
        // clearStates();
        return "resultOperation";
    }
    
    
     public List<Medida> getFindMedida() {
        DaoMedida daoMedida = new DaoMedida();
        List<Medida> medidas;
       

        medidas = daoMedida.findAllMedidas();

        daoMedida = null;

       
        
        System.out.println("Tamaño medidas encontradas " + medidas.size());
        if (medidas.isEmpty()) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("No hay reclamos con este estado."));
            
            return null;
        } else {
            return medidas;
        }
    }

    private void validate() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (accion.trim().length() > 50) {
            context.addMessage(null, new FacesMessage("El motivo no debe exceder los 15 caracteres."));
            countValidator = 1;
        }         


    }
    
}
