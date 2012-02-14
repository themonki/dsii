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
    private String id="";
    private String tipoId="";
    private String nombre="";
    private String apellido="";
    private String direccion="";
    private String email="";
    private String fecha_nacimiento="";
    private String telefono="";
    private String adquiere_tarjeta="";
    private String password="";
    private boolean estado=true;

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


        



       

        content.setResultOperation("El Empleado fue creado con exito.");
        if (validate()) {
            
            Usuario user = new Usuario();
            
            user.setAdquiereTarjeta(adquiere_tarjeta);
            user.setId(id);
            user.setTipoId(tipoId);
            user.setNombre(nombre);
            user.setApellido(apellido);
            
            user.setTelefono(telefono);
            user.setDireccion(direccion);
            user.setPassword(password);
            user.setFechaNacimiento(fecha_nacimiento);
            
            
            user.setEmail(email);
            user.setEstado(estado);
            
           result = daoUser.saveUser(user);
            
            




            if (result == 0) {

                context.addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "El registro del usuario no  fue creado ", null));

            } else {
                context.addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "Creado Con Exito ", null));
            }

            //clearStates();

        }


        return null;

    }
    
    public boolean validate(){
        
        
        boolean validar=true; 
        
       
    if(id==null || id.equals("")){
           context.addMessage(null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "Es obligatorio el campo documento .", null));
           
           validar=false;
                    
    }
    
    if(adquiere_tarjeta==null || adquiere_tarjeta.equals("")){
           context.addMessage(null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "Es obligatorio el campo Tarjeta Id .", null));
           validar=false;
                    
    }
    
     if(password==null || password.equals("")){
           context.addMessage(null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "Es obligatorio el campo password .", null));
           validar=false;
                    
    }
     
     
     if(validar){
         
         try {

                int valor = Integer.parseInt(id);
                if (!(valor >= 0)) {
                    context.addMessage(null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "El id debe ser un numero positivo.", null));
                    validar = false;

                };

            } catch (NumberFormatException e) {
                context.addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "El id debe ser un numero.", null));
                validar = false;
            }
         
            try {

                int valor = Integer.parseInt(telefono);
                if (!(valor >= 0)) {
                    context.addMessage(null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "El telefono debe ser un numero positivo.", null));
                    validar = false;

                };

            } catch (NumberFormatException e) {
                context.addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "El telefono debe ser un numero.", null));
                validar = false;
            }
            
              try {

                int valor = Integer.parseInt(adquiere_tarjeta);
                if (!(valor >= 0)) {
                    context.addMessage(null, new FacesMessage(
                            FacesMessage.SEVERITY_ERROR, "Tarjeta Id debe ser un numero positivo.", null));
                    validar = false;

                };

            } catch (NumberFormatException e) {
                context.addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "Tarjeta Id debe ser un numero.", null));
                validar = false;
            }
              
              
         
        
         
         
     
     }
    
    
    
    
    
    
    
    
        return validar;
        
    }
    
    
    
}