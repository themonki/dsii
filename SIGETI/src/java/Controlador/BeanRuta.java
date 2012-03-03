/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Dao.DaoEstacion;
import Dao.DaoRuta;
import Entidades.Estacion;
import Entidades.EstacionParadero;
import Entidades.EstacionPrincipal;
import Entidades.Ruta;
import Utilidades.BeanContent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

/*
 *
 * @author yerminson
 */

@ManagedBean
@SessionScoped
public class BeanRuta implements Serializable{
    private int countValidator;
    private Ruta rutaSeleccion;
    private List<Estacion> estacionesRuta;
    private String nombre;
    private String descripcion;
    private boolean estado;
    private boolean renderTable;
    private boolean renderMap;
    private String rutaMaps;
    private String action;
    private Estacion estacion;
    private boolean buscarEstaciones;
    private boolean isCreando = true;

    public boolean isBuscarEstaciones() {
        return buscarEstaciones;
    }

    public void setBuscarEstaciones(boolean buscarEstaciones) {
        this.buscarEstaciones = buscarEstaciones;
    }

    public Estacion getEstacion() {
        return estacion;
    }

    public void setEstacion(Estacion estacion) {
        this.estacion = estacion;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }


    public boolean isRenderTable() {
        return renderTable;
    }

    public void setRenderTable(boolean renderTable) {
        this.renderTable = renderTable;
    }
    
    public boolean isRenderMap()
    {
        return renderMap;
    }

    public void setRutaMaps(String rutaMapsNew)
    {
        rutaMaps=rutaMapsNew;
    }

    public String getRutaMaps()
    {
        return rutaMaps;
    }

    public void setRenderMap(boolean renderMapNew)
    {
        renderMap= renderMapNew;
    }

    public int getIdEstacion() {
        return idEstacion;
    }

    public void setIdEstacion(int idEstacion) {
        this.idEstacion = idEstacion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String idUbicacion) {
        this.ubicacion = idUbicacion;
    }
    private int idEstacion;
    private String ubicacion;

    public List<Estacion> getEstacionesRuta() {
        return estacionesRuta;
    }

    public void setEstacionesRuta(List<Estacion> estacionesRuta) {
        this.estacionesRuta = estacionesRuta;
    }
    
   
    public Ruta getRutaSeleccion()
    {
        return rutaSeleccion;
    }
    

    public void setRutaSeleccion(Ruta rutaSeleccionNew)
    {
        rutaSeleccion=rutaSeleccionNew;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
        renderTable=true;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

     public String createRuta() {

         validate();
         
         if(countValidator > 0)
         {
 
             countValidator = 0;
             return null;
         }
      
        FacesContext context = FacesContext.getCurrentInstance();
              
        BeanContent content = (BeanContent) context.getApplication().evaluateExpressionGet(context, "#{beanContent}", BeanContent.class);
        int result;
        DaoRuta daoRuta = new DaoRuta();
        Ruta ruta = new Ruta();
        
        ruta.setNombre(nombre);
        ruta.setDescripcion(descripcion);
        estado = true;
        ruta.setEstado(estado);
       
        result = daoRuta.saveRuta(ruta);
        daoRuta.insertarEstacionesRuta(estacionesRuta, nombre);
        if (result == 0) {
            content.setResultOperation("La ruta no pudo ser creada.");
            content.setImage("./resources/fail.png");
            return "resultOperation";
        }

       

        content.setResultOperation("La ruta fue creada con exito.");
        content.setImage("./resources/ok.png");
        daoRuta = null;
        // clearStates();
        return "resultOperation";
    }
     public List<SelectItem> getAvailableEstadosRuta() {
        List<SelectItem> avaliableEstadosRuta = new ArrayList<SelectItem>();
        avaliableEstadosRuta.add(new SelectItem("Activo"));
        avaliableEstadosRuta.add(new SelectItem("Inactivo"));        
        return avaliableEstadosRuta;
    }
     
     public List<Estacion> getFindEstacion() {
        DaoEstacion daoEstacion = new DaoEstacion();
       
        List<Estacion> estaciones = new ArrayList<Estacion>();
        System.out.println(estado);

        List<EstacionPrincipal> estacionesPrincipales = daoEstacion.findAllEstacionPrincipal();
        List<EstacionParadero> estacioonesParaderos = daoEstacion.findAllEstacionParadero();
        
         System.out.println(estacionesPrincipales.size());
        for(int i=0;i<estacionesPrincipales.size();i++)
        {     
            estaciones.add(estacionesPrincipales.get(i));
            
        }
        
         System.out.println(estacioonesParaderos.size());
        for(int i=0;i<estacioonesParaderos.size();i++)
        {        
            estaciones.add(estacioonesParaderos.get(i));
            
        }
        
       

        daoEstacion = null;     
       
        return estaciones;   
        
    }
     
       public void addEstacionRuta()
    {
     
        FacesContext context = FacesContext.getCurrentInstance();
        if(estacionesRuta.size() == 1)
        {
        
            if(estacionesRuta.get(0).getId() == 0)
            {
            
                estacionesRuta.remove(0);
                
            }            
            
        }
        
        Estacion estacion = new Estacion();
        
        
        String idCadena = "";
        int indiceFinalId = -1;
        for(int i=0;i<ubicacion.length();i++)
        {
            char letra =ubicacion.charAt(i);
            
            if(letra != '.')
            {
                idCadena += letra;
            
            }else
            {
                indiceFinalId = i+1;
                break;
            
            }           
            
            
        
        }
        
        
        idEstacion = Integer.parseInt(idCadena);        
     
        estacion.setId(idEstacion);
        estacion.setUbicacion(ubicacion.substring(indiceFinalId,ubicacion.length()));
        estacion.setEstado(true);
        
        if(estacionesRuta.contains(estacion))
        {
        
            context.addMessage(null, new FacesMessage("A la ruta ya se le ha asignado esa estacion."));
            
        }else
        {       
             estacionesRuta.add(estacion); 
             context.addMessage(null, new FacesMessage("Estacion agregada con exito recuerde guardar los cambios."));
        }
        
    }
       
        public void removeEstacionRuta()
    {
        
        FacesContext context = FacesContext.getCurrentInstance();
        Application app = context.getApplication();
        Estacion estacion= (Estacion) app.evaluateExpressionGet(context, "#{estacion}", Estacion.class);
      
        
        estacionesRuta.remove(estacion);
        
        
         if(estacionesRuta.isEmpty())
        {
        
            estacion = new Estacion(0, "Aun no se han añadido estaciones para esta ruta",true);
            
            estacionesRuta.add(estacion);      
            
        }
        
    }
        
        
     public void clearBeanRuta()
    {
       
        if(estacionesRuta == null)
        {
             estacionesRuta = new ArrayList<Estacion>();

            if(estacionesRuta.isEmpty())
            {

                 estacionesRuta.add(new Estacion(0, "No hay paraderos asignados", true));
            }
        
        
        }else
        {
        
             estacionesRuta.clear();
             estacionesRuta.add(new Estacion(0, "No hay paraderos asignados", true));
        
        }
       
        nombre = "";
        descripcion="";
        idEstacion = 0;
        ubicacion = "";
        
        estado=true;        

        renderTable=false;

        renderMap=false;
        rutaSeleccion=null;
        rutaMaps="";
    }
    
     
     
       private int  validate() {
        FacesContext context = FacesContext.getCurrentInstance();
        
       
        
        if (this.nombre.trim().length() > 50) {
            context.addMessage(null, new FacesMessage("El motivo no debe exceder los 15 caracteres."));
            countValidator = 1;
        }
        if (descripcion.trim().length() > 200) {
            context.addMessage(null, new FacesMessage("La descripcion no debe exceder los 200 caracteres."));
            countValidator = 1;

        }
        
        if(!isCreando) return 0;
        
        // Se usara con el dao de reclamo pero debe ser con el dao de usuario Temporal!!!
        DaoRuta daoRuta = new DaoRuta();

        if (!daoRuta.rutaValida(nombre).equals("") ) {
            context.addMessage(null, new FacesMessage("El nombre de la ruta ya existe seleccione otro"));
            countValidator = 1;

        }

        return 0;

    }

    public List<SelectItem> findAllRutas()
    {
        DaoRuta daoRuta= new DaoRuta();
        List<Ruta> rutas= daoRuta.consultarAllRutas();

        List<SelectItem> rutas_items= new ArrayList<SelectItem>();

        for(int i=0; i<rutas.size() ; i++)
        {
            Ruta ruta= rutas.get(i);
            rutas_items.add(new SelectItem(ruta.getNombre()));
        }

        return rutas_items;
    }

    public void consultarRuta()
    {
        renderMap=true;

        rutaMaps="from: ";
        DaoRuta daoRuta= new DaoRuta();
        List<Estacion> estaciones = daoRuta.estacionesRuta(nombre);

        for(int i=0; i<estaciones.size(); i++)
        {
            rutaMaps+= estaciones.get(i).getUbicacion()
                    + ", Cali, Valle del Cauca, Colombia ";
            if(i!=estaciones.size()-1)
                rutaMaps+=" to: ";
        }

        System.err.print("RUTA: "+ rutaMaps);
        Ruta ruta = daoRuta.getRuta(nombre);
        descripcion=ruta.getDescripcion();
        estado=ruta.getEstado();
    }
    
    
    public void statesForErase(ActionEvent e){
        this.renderTable=false;        
        this.action = "Eliminar";
        isCreando=false;
    }
    
    public void statesForEdit(ActionEvent e){
        this.renderTable=false;
        this.action = "Editar";
        isCreando=false;
    }
    
    public void statesForFindReturn(ActionEvent e){
        this.renderTable=false;
    }
    
    public String getLinkAction() {
        String link = "";
        if (this.action.equals("Eliminar")) {
            this.preparateDataRuta();
            link = "eraseRoute";
        } else if (this.action.equals("Editar")) {
            this.preparateDataRuta();
            link = "editRoute";
        }

        return link;
    }
    private Ruta getCurrentRuta() {
        FacesContext context = FacesContext.getCurrentInstance();
        Application app = context.getApplication();
        Ruta ruta = (Ruta) app.evaluateExpressionGet(context, "#{ruta}", Ruta.class);
        return ruta;
    }
    
    public void preparateDataRuta(){
        Ruta ruta = getCurrentRuta();
        this.estado = ruta.getEstado();
        this.nombre = ruta.getNombre();
        this.descripcion = ruta.getDescripcion();
        DaoRuta daoRuta = new DaoRuta();
        this.estacionesRuta = daoRuta.estacionesRuta(nombre.trim());
    }
    
    public List<Ruta> consultarRutaNormal(){
        DaoRuta daoRuta = new DaoRuta();        
        List<Ruta> buses = null;
        if(buscarEstaciones){
            
            buses = daoRuta.consultarRutasEstacion(estacion.getId().toString());
        }else{
            buses = new ArrayList<Ruta>();
            buses.add(daoRuta.getRuta(nombre.trim()));            
        }       
        return buses;
        
    }
    
    public String updateRuta(){
        FacesContext context = FacesContext.getCurrentInstance();
        BeanContent content = (BeanContent) context.getApplication().evaluateExpressionGet(context, "#{beanContent}", BeanContent.class);
        validate();
         
         if(countValidator > 0)
         {
 
             countValidator = 0;
             return null;
         }
        int result;
        DaoRuta daoRuta = new DaoRuta();
        Ruta ruta= new Ruta();
        ruta.setDescripcion(descripcion.trim());
        ruta.setEstado(estado);
        ruta.setNombre(nombre.trim());
        result = daoRuta.updateRuta(ruta);        
        daoRuta.insertarEstacionesRuta(estacionesRuta, nombre);
        
        if (result == 0) {
            content.setResultOperation("La Ruta no pudo ser actualizada.");
            this.clearBeanRuta();
            content.setImage("./resources/fail.png");
            return "resultOperation";
        }
        
        daoRuta = null;
        content.setResultOperation("La Ruta fue actualizada con éxito.");
        content.setImage("./resources/ok.png");
        this.clearBeanRuta();
        return "resultOperation";        
    }
    
     public String eraseRuta(){
        FacesContext context = FacesContext.getCurrentInstance();
        BeanContent content = (BeanContent) context.getApplication().evaluateExpressionGet(context, "#{beanContent}", BeanContent.class);
        validate();
         
         if(countValidator > 0)
         {
 
             countValidator = 0;
             return null;
         }
        int result;
        DaoRuta daoRuta = new DaoRuta();
        Ruta ruta= new Ruta();
        ruta.setDescripcion(descripcion.trim());
        ruta.setEstado(estado);
        ruta.setNombre(nombre.trim());
        result = daoRuta.eraseRuta(ruta);
        
        
        if (result == 0) {
            content.setResultOperation("La Ruta no pudo ser eliminada.");
            this.clearBeanRuta();
            content.setImage("./resources/fail.png");
            return "resultOperation";
        }
        
        daoRuta = null;
        content.setResultOperation("La Ruta fue eliminada con éxito.");
        content.setImage("./resources/ok.png");
        this.clearBeanRuta();
        return "resultOperation";        
    }
    
    
}
