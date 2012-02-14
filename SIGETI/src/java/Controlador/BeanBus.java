/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Dao.DaoBus;
import Entidades.Bus;
import Utilidades.BeanContent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.swing.JOptionPane;

/**
 *
 * @author Edgar Andres Moncada
 */
@ManagedBean
@SessionScoped
public class BeanBus implements Serializable {
    
    private String matricula;
    private String tipo;
    private Integer capacidad;
    private String idInterno;
    private boolean estado;
    private String perteneceRuta;
    private FacesContext context;
    /**/
    private boolean isRenderTableSearch;
    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public boolean isIsRenderTableSearch() {
        return isRenderTableSearch;
    }

    public void setIsRenderTableSearch(boolean isRenderTableSearch) {
        this.isRenderTableSearch = isRenderTableSearch;
    }
    
    public boolean isRenderTableSearch() {       
        return isRenderTableSearch;
    }

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
        isRenderTableSearch=true;
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
        
        if(!validate()){
            return null;
        }
        context = FacesContext.getCurrentInstance();
        
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
        
        result = daoBus.saveBus(bus);
        if(result == 0){
            content.setResultOperation("El Bus no pudo ser creado.");
            return "resultOperation";
        }            
        
        daoBus = null;
        content.setResultOperation("El Bus fue creado con exito.");
        this.clearStates();
        return "resultOperation";
    }
    
    public List<SelectItem> getAvailableRutaPertenece(){
        List<SelectItem> availableRutaPertenece = new ArrayList<SelectItem>();
        //DaoRuta daoRuta = new DaoRuta();
        
        availableRutaPertenece.add(new SelectItem("E31"));
        availableRutaPertenece.add(new SelectItem("E37"));

        return availableRutaPertenece;
    }
    
    public boolean validate(){
        return true;
    }
    
    public List<SelectItem> getAvailableEstado(){
        List<SelectItem> availableRutaPertenece = new ArrayList<SelectItem>();
        
        availableRutaPertenece.add(new SelectItem("Reparacion"));
        availableRutaPertenece.add(new SelectItem("Mantenimiento"));
        availableRutaPertenece.add(new SelectItem("Funcionando"));

        return availableRutaPertenece;
    }
    
    public void clearStates(){
        this.capacidad=0;
        this.estado=false;
        this.idInterno="";
        this.matricula="";
        this.perteneceRuta="";
        this.tipo="";
    }
    
    public void statesForNew(ActionEvent e){
        this.clearStates();
    }
    
    public void statesForErase(ActionEvent e){
        this.clearStates();
        this.action = "Eliminar";
    }
    
    public void statesForEdit(ActionEvent e){

        this.isRenderTableSearch=false;
        this.clearStates();
        this.action = "Editar";
    }
    
    public void statesForFind(ActionEvent e){
        this.clearStates();
        this.action = "Detalle";
    }
    
    public List<Bus> getFindBuses(){        
        DaoBus daoBus = new DaoBus();
        Bus b = daoBus.consultarBus(matricula);
        List<Bus> buses = new ArrayList<Bus>();
        buses.add(b);
        return buses;
    }
    
    public String getLinkAction() {
        String link = "";
        if (this.action.equals("Detalle")) {
            //this.prepareDataEmployee();
            link = "detailBus";
        } else if (this.action.equals("Eliminar")) {
            link = "eraseBus";
        } else if (this.action.equals("Editar")) {
            //this.prepareDataEmployee();
            link = "editBus";
        }

        return link;
    }
}
