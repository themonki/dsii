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
import Dao.DaoUser;
import Entidades.Usuario;

@ManagedBean
@SessionScoped
public class BeanUserCard implements Serializable {

    private String id = "";
    private String tipoId = "";
    private String nombre = "";
    private String apellido = "";
    private String direccion = "";
    private String email = "";
    private String fecha_nacimiento = "";
    private String telefono = "";
    private String adquiere_tarjeta = "";
    private String password = "";
    private boolean estado = true;
    private int year = 0;
    private int month = 0;
    private int day = 0;

    private String consulta="false";

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

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

    public void clearStates() {

        id = "";
        tipoId = "";
        nombre = "";
        apellido = "";
        direccion = "";
        email = "";
        fecha_nacimiento = "";
        telefono = "";
        adquiere_tarjeta = "";
        password = "";
        estado = true;
        year = 0;
        month = 0;
        day = 0;
        consulta="false";



    }
    String actual = "";

    public void refresh(String actual) {

        if (!this.actual.equals(actual)) {

            clearStates();

            this.actual = actual;

        }

    }
    
    public String findUser(){
        
            FacesContext context;
        context = FacesContext.getCurrentInstance();
        //validate();


        if (context.getMessageList().size() > 0) {
            return null;
        }
        BeanContent content = (BeanContent) context.getApplication().evaluateExpressionGet(context, "#{beanContent}", BeanContent.class);

        Usuario user = new Usuario(); 
        DaoUser dao = new DaoUser(); 
        user=dao.findUser(id);
        
        if(user.getId()==null){ 
                    context.addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "La consulta no arrojo resultados", null));
            
            return null;
        
        }
       
        
        
     
        String [] espacios = user.getPassword().split(" ");
        boolean a = password.equals(espacios[0]);
        
        if (!a){
                    context.addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "El password no es valido ", null));
                    return null ; 
        }
        
        consulta="true";
         id = user.getId();
        tipoId =user.getTipoId();
        nombre = user.getNombre();
        apellido = user.getApellido();
        direccion = user.getDireccion();
        email = user.getDireccion();
        fecha_nacimiento = user.getFechaNacimiento();
        telefono = user.getTelefono();
        adquiere_tarjeta = user.getAdquiereTarjeta();
        password = user.getPassword();
        estado = true;
        
        
        try {
        String []  fechas= fecha_nacimiento.split("-");
        year = Integer.parseInt(fechas[0]);
        month =Integer.parseInt( fechas[1]);
        day = Integer.parseInt(fechas[2]);
        }
        catch (Exception e){
        }
        
        
        System.err.println("holaaaaaaa  "+user);
        
        
        return  null ; 
    }

    
    public String editUserCard (){
          FacesContext context;

        context = FacesContext.getCurrentInstance();
        //validate();
        if (context.getMessageList().size() > 0) {
            return null;
        }
        BeanContent content = (BeanContent) context.getApplication().evaluateExpressionGet(context, "#{beanContent}", BeanContent.class);
        int result = 0;
        DaoUser daoUser = new DaoUser();



        content.setResultOperation("El Empleado fue creado con exito.");
        content.setImage("./resources/ok.png");
        if (validate()) {

            fecha_nacimiento = "" + year + "-" + month + "-" + day;

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

            result = daoUser.editUser(user);






            if (result == 0) {

                context.addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "El registro del usuario no  fue modificado ", null));

            } else {
                context.addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "Modificado Con Exito ", null));
            }

            clearStates();

        }


        return null;
      
    
    }
    public String createUser() {

        FacesContext context;

        context = FacesContext.getCurrentInstance();
        //validate();
        if (context.getMessageList().size() > 0) {
            return null;
        }
        BeanContent content = (BeanContent) context.getApplication().evaluateExpressionGet(context, "#{beanContent}", BeanContent.class);
        int result = 0;
        DaoUser daoUser = new DaoUser();



        content.setResultOperation("El Empleado fue creado con exito.");
        content.setImage("./resources/ok.png");
        if (validate()) {

            fecha_nacimiento = "" + year + "-" + month + "-" + day;

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

    public boolean validate() {
        FacesContext context;
        context = FacesContext.getCurrentInstance();

        boolean validar = true;


        if (id == null || id.equals("")) {
            context.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Es obligatorio el campo documento .", null));

            validar = false;

        }

        if (adquiere_tarjeta == null || adquiere_tarjeta.equals("")) {
            context.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Es obligatorio el campo Tarjeta Id .", null));
            validar = false;

        }

        if (password == null || password.equals("")) {
            context.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Es obligatorio el campo password .", null));
            validar = false;

        }


        if (validar) {

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

                System.err.println("telefono1::::: " + telefono);

                if (telefono != null && !telefono.equals("")) {

                    System.err.println("telefono::::: " + telefono);
                    int valor = Integer.parseInt(telefono);
                    if (!(valor >= 0)) {
                        context.addMessage(null, new FacesMessage(
                                FacesMessage.SEVERITY_ERROR, "El telefono debe ser un numero positivo.", null));
                        validar = false;


                    }
                }

            } catch (NumberFormatException e) {
                context.addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "El telefono debe ser un numero." + telefono, null));
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





            //----------fecha 




            java.util.Date date = new java.util.Date();

            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy");

            String fecha = sdf.format(date);

            int yearActual = Integer.parseInt(fecha);


            if (year < 1900 || year > yearActual) {
                context.addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "El año no es valido.", null));
                validar = false;


            }

            if (month <= 0 || month > 12) {
                context.addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "El mes no es valido.", null));
                validar = false;


            }

            if (day <= 0 || day > 31) {
                context.addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "El dia no es valido.", null));
                validar = false;


            }

            if (!(password.length() >= 4)) {
                context.addMessage(null, new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "El password debe ser minimo de 4 caracteres.", null));
                validar = false;

            }


        }

        return validar;

    }
}