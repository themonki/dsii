/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Dao.DaoBus;
import Dao.DaoRuta;
import Entidades.Bus;
import Entidades.Ruta;
import Utilidades.BeanContent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;

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
    private String estadoFisico;
    /**/
    private boolean isRenderTableSearch;
    private String action;
    private int indexEstadoFisico;
    private int indexPerteneceRuta;

    public int getIndexPerteneceRuta() {
        return indexPerteneceRuta;
    }

    public void setIndexPerteneceRuta(int indexPerteneceRuta) {
        this.indexPerteneceRuta = indexPerteneceRuta;
    }

    public int getIndexEstadoFisico() {
        return indexEstadoFisico;
    }

    public void setIndexEstadoFisico(int indexTipo) {
        this.indexEstadoFisico = indexTipo;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    
    public String getEstadoFisico() {
        return estadoFisico;
    }

    public void setEstadoFisico(String estadoFisico) {
        this.estadoFisico = estadoFisico;
        if(tipo.equals("Reparacion")){
            indexEstadoFisico=0;
        }else if(tipo.equals("Mantenimiento")){
            indexEstadoFisico=1;
        }else if(tipo.equals("Funcionando")){
            indexEstadoFisico=2;
        }
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

    public void setEstado(String estado) {
        if(estado.equals("Habilitado"))
            this.estado = true;
        else this.estado = false;
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
        
        FacesContext context = FacesContext.getCurrentInstance();
        
        if (context.getMessageList().size() > 0) {
            return null;
        }
        BeanContent content = (BeanContent) context.getApplication().evaluateExpressionGet(context, "#{beanContent}", BeanContent.class);
        int result;
        DaoBus daoBus = new DaoBus();
        Bus bus = new Bus();
        bus.setMatricula(matricula.trim());
        bus.setCapacidad(capacidad);
        bus.setEstado(true);
        bus.setIdInterno(idInterno.trim());
        bus.setPerteneceRuta(perteneceRuta.trim());
        bus.setTipo(tipo.trim());
        bus.setEstadoFisico(estadoFisico);
        
        result = daoBus.saveBus(bus);
        daoBus = null;
        if(result == 0){
            content.setResultOperation("El Bus no pudo ser creado.");
            content.setImage("./resources/fail.png");
            return "resultOperation";
        }    
        content.setResultOperation("El Bus fue creado con exito.");
        this.clearStates();
        content.setImage("./resources/ok.png");
        return "resultOperation";
    }
    
    public String updateBus(){
        FacesContext context = FacesContext.getCurrentInstance();
        BeanContent content = (BeanContent) context.getApplication().evaluateExpressionGet(context, "#{beanContent}", BeanContent.class);
        int result;
        DaoBus daoBus = new DaoBus();
        Bus b= new Bus();
        b.setCapacidad(capacidad);
        b.setEstado(estado);
        b.setEstadoFisico(estadoFisico.trim());
        b.setPerteneceRuta(perteneceRuta.trim());
        b.setTipo(tipo.trim());
        b.setIdInterno(idInterno.trim());
        b.setMatricula(matricula.trim());
        result = daoBus.updateBus(b);
        
        if (result == 0) {
            content.setResultOperation("El Bus no pudo ser actualizado.");
            this.clearStates();
            return "resultOperation";
        }
        
        daoBus = null;
        content.setResultOperation("El Bus fue actualizado con éxito.");
        this.clearStates();
        return "resultOperation";        
    }
    
    public String eraseBus(){
        FacesContext context = FacesContext.getCurrentInstance();
        BeanContent content = (BeanContent) context.getApplication().evaluateExpressionGet(context, "#{beanContent}", BeanContent.class);
        int result;
        DaoBus daoBus = new DaoBus();
        Bus b= new Bus();
        b.setCapacidad(capacidad);
        b.setEstado(estado);
        b.setEstadoFisico(estadoFisico.trim());
        b.setPerteneceRuta(perteneceRuta.trim());
        b.setTipo(tipo.trim());
        b.setIdInterno(idInterno.trim());
        b.setMatricula(matricula.trim());
        result = daoBus.downBus(b);
        daoBus = null;
        if (result == 0) {
            content.setResultOperation("El Bus no pudo ser eliminado.");
            this.clearStates();
            return "resultOperation";
        }
        content.setResultOperation("El Bus fue eliminado con éxito.");
        this.clearStates();
        return "resultOperation";        
    
    }
    
    public List<SelectItem> getAvailableRutaPertenece(){
        List<SelectItem> availableRutaPertenece = new ArrayList<SelectItem>();
        DaoRuta daoRuta = new DaoRuta();
        List<Ruta> nombresRuta = daoRuta.consultarAllRutas();
        
        for(int i = 0; i< nombresRuta.size(); i++){
            if(nombresRuta.get(i).getNombre().equals(perteneceRuta)){
                indexPerteneceRuta=i;
            }
            availableRutaPertenece.add(new SelectItem(nombresRuta.get(i).getNombre()));
        }
        
        return availableRutaPertenece;
    }
    
    public List<SelectItem> getAvailableEstadoFisico(){
        List<SelectItem> availableEstadoFisico = new ArrayList<SelectItem>();
        
        availableEstadoFisico.add(new SelectItem("Reparacion"));
        availableEstadoFisico.add(new SelectItem("Mantenimiento"));
        availableEstadoFisico.add(new SelectItem("Funcionando"));

        return availableEstadoFisico;
    }
    
    public List<SelectItem> getAvailableEstado(){
        List<SelectItem> availableEstado = new ArrayList<SelectItem>();
        
        availableEstado.add(new SelectItem("Habilitado"));
        availableEstado.add(new SelectItem("Deshabilitado"));       

        return availableEstado;
    }
    
    public List<SelectItem> getAvailableTipo(){
        List<SelectItem> availableTipo = new ArrayList<SelectItem>();
        
        availableTipo.add(new SelectItem("Alimentador"));
        availableTipo.add(new SelectItem("Padron"));       
        availableTipo.add(new SelectItem("Articulado"));

        return availableTipo;
    }
    
    public void clearStates(){
        this.capacidad=0;
        this.estado=true;
        this.estadoFisico="";
        this.idInterno="";
        this.matricula="";
        this.perteneceRuta="";
        this.tipo="";
    }
    
    public void statesForNew(ActionEvent e){
        this.clearStates();
    }
    
    public void statesForErase(ActionEvent e){
        this.isRenderTableSearch=false;
        this.clearStates();
        this.action = "Eliminar";
    }
    
    public void statesForEdit(ActionEvent e){
        this.isRenderTableSearch=false;
        this.clearStates();
        this.action = "Editar";
    }
    
    public void statesForFind(ActionEvent e){
        this.isRenderTableSearch=false;
        this.clearStates();
        this.action = "Detalle";
    }
    
    public void statesForFindReturn(ActionEvent e) {
        this.clearStates();
    }
    
    public List<Bus> getFindBuses(){        
        DaoBus daoBus = new DaoBus();
        Bus b = new Bus();
        
        b.setMatricula(matricula.trim());
        b.setIdInterno(idInterno.trim());
        b.setTipo(tipo.trim());
        List<Bus> buses = daoBus.consultarBuses(b);
        return buses;
    }
    
    public String getLinkAction() {
        String link = "";
        if (this.action.equals("Detalle")) {
            this.preparateDataBus();
            link = "detailBus";
        } else if (this.action.equals("Eliminar")) {
            this.preparateDataBus();
            link = "eraseBus";
        } else if (this.action.equals("Editar")) {
            this.preparateDataBus();
            link = "editBus";
        }

        return link;
    }
    
    private Bus getCurrentBus() {
        FacesContext context = FacesContext.getCurrentInstance();
        Application app = context.getApplication();
        Bus bus = (Bus) app.evaluateExpressionGet(context, "#{bus}", Bus.class);
        return bus;
    }
    
    public void preparateDataBus(){
        Bus bus = getCurrentBus();
        this.matricula = bus.getMatricula();
        this.capacidad = bus.getCapacidad();
        this.estado = bus.getEstado();
        this.estadoFisico = bus.getEstadoFisico();
        this.perteneceRuta = bus.getPerteneceRuta();
        this.tipo = bus.getTipo();
        this.idInterno = bus.getIdInterno();        
    }
   public void validateMatricula(FacesContext context, UIComponent component, Object value) throws 
            ValidatorException {
       String valorMatricula = value.toString();
        if(valorMatricula.length()>10){
            throw new ValidatorException(new FacesMessage("Se execede el tamaño maximo de la Matricula (10)"));
        }
        
        DaoBus daoBus = new DaoBus();
        boolean result = daoBus.consultarMatriculaBus(valorMatricula);
        
        if(result){
                throw new ValidatorException(new FacesMessage("La Matricula ya existe"));
        }        
    }
    
    public void validateIdInterno(FacesContext context, UIComponent component, Object value) throws 
            ValidatorException {
       String valorIdInterno = value.toString();
        if(valorIdInterno.length()>20){
            throw new ValidatorException(new FacesMessage("Se execede el tamaño maximo del Identificador Interno (20)"));
        }        
        DaoBus daoBus = new DaoBus();
        boolean result = daoBus.consultarMatriculaBus(valorIdInterno);
        
        if(result){
                throw new ValidatorException(new FacesMessage("El Identificador Interno ya existe"));
        }        
    }
    
    
    
    
}
