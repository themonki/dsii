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
import javax.faces.model.SelectItem;

/*
 *
 * @author yerminson
 */

@ManagedBean
@SessionScoped
public class BeanRuta implements Serializable{

    public boolean isRenderTable() {
        return renderTable;
    }

    public void setRenderTable(boolean renderTable) {
        this.renderTable = renderTable;
    }
    
    private String nombre;
    private String descripcion;
    private boolean estado;
    private boolean renderTable;

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
    List<Estacion> estacionesRuta;
   

    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        List<EstacionParadero> estacioonesParadedos = daoEstacion.findAllEstacionParadero();
        
        
        for(int i=0;i<estacionesPrincipales.size();i++)
        {     
            estaciones.add(estacionesPrincipales.get(i));
            
        }
        for(int i=0;i<estacioonesParadedos.size();i++)
        {        
            estaciones.add(estacioonesParadedos.get(i));
            
        }
        
        estacionesRuta = new ArrayList<Estacion>();
       
        if(estacionesRuta.isEmpty())
        {
        
             estacionesRuta.add(new Estacion(0, "No hay paraderos asignados", true));
        }

        daoEstacion = null;     
       
        return estaciones;   
        
    }
     
       public void addMedidaReclamo()
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
       
        public void removeMedidaReclamo()
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
        idEstacion = 0;
        if(estacionesRuta != null)
        estacionesRuta.clear();
        ubicacion="";
        idEstacion = 0;
        
        estado=true;        

        renderTable=false;
    }
    
}
