/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Entidades.EstacionParadero;
import Entidades.EstacionPrincipal;
import Dao.DaoEstacion;

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

/**
 *
 * @author andrea
 */

@ManagedBean
@SessionScoped
public class BeanEstacion implements Serializable{

    String tipo;
    String nombre;
    String ubicacion;
    String idOperario;
    Integer id;
    boolean estado;

    boolean disableNombre;
    boolean disableIdOperario;

    boolean renderTable;
    String nombreBusqueda;

    public BeanEstacion()
    {
        disableIdOperario=true;
        disableNombre=true;

    }

    public void clearBeanEstacion()
    {
        tipo="";
        nombre="";
        ubicacion="";
        idOperario="";
        id=0;
        estado=false;

        disableIdOperario=true;
        disableNombre=true;

        renderTable=false;
    }

    public String getTipo()
    {
        return tipo;
    }

    public String getNombre()
    {
        return nombre;
    }
    public String getUbicacion()
    {
        return ubicacion;
    }

    public String getIdOperario()
    {
        return idOperario;
    }

    public Integer getId()
    {
        return id;
    }

    public boolean getEstado()
    {
        return estado;
    }

    public boolean getdisableNombre()
    {
        return disableNombre;
    }

    public boolean getdisableIdOperario()
    {
        return disableIdOperario;
    }

    public boolean getRenderTable()
    {
        return renderTable;
    }

    public String getNombreBusqueda()
    {
        return nombreBusqueda;
    }

    public void setTipo(String tipoNew)
    {
        tipo= tipoNew;

        if(tipo.equals("paradero"))
        {
            disableIdOperario=true;
            disableNombre=true;
        }else if(tipo.equals("principal"))
        {
            disableIdOperario=false;
            disableNombre=false;
        }
    }

    public void setNombre(String nombreNew)
    {
        nombre= nombreNew.trim();
    }

    public void setUbicacion(String ubicacionNew)
    {
        ubicacion=ubicacionNew.trim();
    }

    public void setIdOperario(String idOperarioNew)
    {
        idOperario=idOperarioNew;
    }

    public void setId(Integer idNew)
    {
        id=idNew;
    }

    public void setEstado(boolean estadoNew)
    {
        estado=estadoNew;
    }

    public void setdisableNombre(boolean disableNombreNew)
    {
        disableNombre=disableNombreNew;
    }

    public void setdisableIdOperario(boolean disableIdOperarioNew)
    {
        disableIdOperario=disableIdOperarioNew;
    }

    public void setRenderTable(boolean renderTableNew)
    {
        renderTable= renderTableNew;
    }

    public void setNombreBusqueda(String nombreBusquedaNew)
    {
        nombreBusqueda=nombreBusquedaNew.trim();
    }

    public boolean existEstacion(String ubicacion)
    {
        DaoEstacion daoEstacion= new DaoEstacion();
        boolean exist= daoEstacion.existEstacion(ubicacion);
        return exist;
    }

    public String createEstacion()
    {
        if(validar()>0)
        {
            return null;
        }

        FacesContext context = FacesContext.getCurrentInstance();
        BeanContent content = (BeanContent) context.getApplication().evaluateExpressionGet(context, "#{beanContent}", BeanContent.class);

        if(existEstacion(ubicacion))
        {
            context.addMessage(null, new FacesMessage("La ubicacion ya existe, por favor verifique que sea una nueva ubicacion."));
            return null;
        }

        

        DaoEstacion daoEstacion= new DaoEstacion();

        int result=0;
        if(tipo.equals("paradero"))
        {
            EstacionParadero estacion= new EstacionParadero();
            estacion.setId(id);
            estacion.setUbicacion(ubicacion);
            estacion.setEstado(estado);

            result= daoEstacion.saveEstacionParadero(estacion);
        }else if(tipo.equals("principal"))
        {
            EstacionPrincipal estacion = new EstacionPrincipal();
            estacion.setId(id);
            estacion.setUbicacion(ubicacion);
            estacion.setEstado(estado);
            estacion.setNombre(nombre);
            estacion.setIdOperario(idOperario);

            result= daoEstacion.saveEstacionPrincipal(estacion);
        }

        if(result==0)
        {
            content.setResultOperation("La estacion no pudo ser creada.");
            content.setImage("./resources/fail.png");
            clearBeanEstacion();
            return "resultOperation";
        }else{
            content.setResultOperation("La estacion fue creada con exito.");
            content.setImage("./resources/ok.png");
            clearBeanEstacion();
            return "resultOperation";
        }
        
        
    }

    public List<SelectItem> getTipos()
    {
        List<SelectItem> tipos= new ArrayList<SelectItem>();

        tipos.add(new SelectItem("paradero"));
        tipos.add(new SelectItem("principal"));

        return tipos;
    }

    public int validar()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        int errores=0;
        if(ubicacion.trim().length()>20)
        {
            context.addMessage(null, new FacesMessage("La ubicacion debe tener maximo 20 caracteres."));
            errores++;
        }
        if(!tipo.equals("paradero"))
            if(nombre.trim().length()>50)
            {
                context.addMessage(null, new FacesMessage("El nombre debe tener maximo 50 caracteres."));
                errores++;
            }

        return errores;
    }

    
    public List<EstacionPrincipal> findEstacionesPrincipales()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        BeanContent content = (BeanContent) context.getApplication().evaluateExpressionGet(context, "#{beanContent}", BeanContent.class);
        DaoEstacion daoEstacion = new DaoEstacion();
        List<EstacionPrincipal> estaciones= daoEstacion.findEstacionesPrincipales(nombreBusqueda);

        if(estaciones.size()>0)
        {
            /*for(int i=0; i<estaciones.size(); i++)
            {
                context.addMessage(null, new FacesMessage("Estacion No.: " + estaciones.get(i).getId()
                        + " Ubicacion: "+ estaciones.get(i).getUbicacion()
                        + " Estado: "+ estaciones.get(i).getEstado()
                        + " Nombre: " + estaciones.get(i).getNombre()
                        + " Id Operario: " + estaciones.get(i).getIdOperario()));
            }*/
            renderTable=true;

            return estaciones;

        }else{
            context.addMessage(null, new FacesMessage("No se encontraron coincidencias en la base de datos, por favor verifique su busqueda"));
            renderTable=false;
            return null;
            //return "resultOperation";

        }
    }

    public EstacionPrincipal getEstacionPrincipal()
    {
        FacesContext context= FacesContext.getCurrentInstance();
        Application app= context.getApplication();
        EstacionPrincipal estacion  = (EstacionPrincipal) app.evaluateExpressionGet(context, "#{estacion}", EstacionPrincipal.class);

        return estacion;
    }

    public void detalleEstacion()
    {
        EstacionPrincipal estacion= getEstacionPrincipal();
        id= estacion.getId();
        ubicacion= estacion.getUbicacion();
        estado= estacion.getEstado();
        nombre= estacion.getNombre();
        idOperario= estacion.getIdOperario();
    }

    public void actionFindEstacionPrincipal()
    {
        this.findEstacionesPrincipales();
    }
}


