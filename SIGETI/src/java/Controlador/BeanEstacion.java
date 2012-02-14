/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Entidades.Estacion;
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
import javax.faces.event.ActionEvent; //.......................
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
        nombre= nombreNew;
    }

    public void setUbicacion(String ubicacionNew)
    {
        ubicacion=ubicacionNew;
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

    public boolean existEstacion(String ubicacion)
    {
        DaoEstacion daoEstacion= new DaoEstacion();
        boolean exist= daoEstacion.existEstacion(ubicacion);
        return exist;
    }

    public String createEstacion()
    {
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
            return "resultOperation";
        }else{
            content.setResultOperation("La estacion fue creada con exito.");
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
}


