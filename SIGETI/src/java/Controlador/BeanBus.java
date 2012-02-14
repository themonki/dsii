/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Dao.DaoBus;
import Entidades.Bus;
import Utilidades.BeanContent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.swing.JOptionPane;

/**
 *
 * @author Edgar Andres Moncada
 */
@ManagedBean
@RequestScoped
public class BeanBus {
    
    private String matricula;
    private String tipo;
    private Integer capacidad;
    private String idInterno;
    private boolean estado;
    private String perteneceRuta;
    private FacesContext context;


    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getIdInterno() {
        return idInterno;
    }

    public void setIdInterno(String idInterno) {
        this.idInterno = idInterno;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getPerteneceRuta() {
        return perteneceRuta;
    }

    public void setPerteneceRuta(String perteneceRuta) {
        this.perteneceRuta = perteneceRuta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Creates a new instance of BeanBus
     */
    public BeanBus() {
        
    }
    
    public String createBus(){
        context = FacesContext.getCurrentInstance();
        //validate();
        if (context.getMessageList().size() > 0) {
            return null;
        }
        BeanContent content = (BeanContent) context.getApplication().evaluateExpressionGet(context, "#{beanContent}", BeanContent.class);
        int result;
        DaoBus daoBus = new DaoBus();
        Bus bus = new Bus();
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
            content.setImage("./resources/fail.png");
            return "resultOperation";
        }            
        
        daoBus = null;
        content.setResultOperation("El Bus fue creado con exito.");
        content.setImage("./resources/ok.png");
        return "resultOperation";
    }
}
