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
import Dao.DaoUser;
import Entidades.Usuario;

import Entidades.TarjetaPersonalizada;

@ManagedBean
@SessionScoped
public class BeanUserCard implements Serializable 
{
     private FacesContext context;
    private String id;
    private String tipoId;
    private String nombre;
    private String apellido;
    private String direccion;
    private String email;
    private String fecha_nacimiento;
    private String telefono;
    private String adquiere_tarjeta;
    private String password;
    private boolean estado;

    public String getAdquiere_tarjeta() {
        return adquiere_tarjeta;
    }

    public void setAdquiere_tarjeta(String adquiere_tarjeta) {
        this.adquiere_tarjeta = adquiere_tarjeta;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipoId() {
        return tipoId;
    }

    public void setTipoId(String tipoId) {
        this.tipoId = tipoId;
    }
    
    
    public String createUser() {
        
        context = FacesContext.getCurrentInstance();
        //validate();
        if (context.getMessageList().size() > 0) {
            return null;
        }
        BeanContent content = (BeanContent) context.getApplication().evaluateExpressionGet(context, "#{beanContent}", BeanContent.class);
        int result = 0;
        DaoUser daoUser = new DaoUser();





        java.util.Date date = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        fecha = sdf.format(date);


        content.setResultOperation("El Empleado fue creado con exito.");
        if (validate()) {




            if (result == 0) {

                context.addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "El registro de la tarjeta no  fue creado ", null));

            } else {
                context.addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "Creado Con Exito ", null));
            }

            //clearStates();

        }


        return null;

    }
    
    
    
}