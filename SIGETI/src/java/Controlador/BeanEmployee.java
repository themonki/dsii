/**
 * Clase controladora que conecta la vista con la lógia.
 * @author Leoanrdo Ríos
 */

package Controlador;

import Dao.DaoEmpleado;
import Dao.DaoEstacion;
import Entidades.Auxiliar;
import Entidades.Conductor;
import Entidades.Director;
import Entidades.Empleado;
import Entidades.EstacionPrincipal;
import Entidades.Operario;
import Utilidades.BeanContent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

@ManagedBean
@SessionScoped
public class BeanEmployee implements Serializable{

    private String nombre;
    private String nombre2;
    private String apellido;
    private String apellido2;
    private String tipoId;
    private String identificacion;
    private String telefono;
    private String direccion;
    private String email;
    private String fechaNacimiento;
    private String fechaNacimientoAno;
    private String fechaNacimientoMes;
    private String fechaNacimientoDia;
    private String fechaIngreso;
    private String fechaIngresoAno;
    private String fechaIngresoMes;
    private String fechaIngresoDia;
    private String salario;
    private String cargo;
    private String login;
    private String password;
    private String passwordConfirmar;
    private boolean estado;
    private String licencia;
    private String identificacionJefe;
    private String lugarTrabajo;
    //usado para control y calculos
    private boolean isDisableLicencia = true;
    private boolean isDisableIdJefe = true;
    private boolean isDisableEstacion = true;
    private int countValidator;

    public void setIsDisableEstacion(boolean isDisableEstacion) {
        this.isDisableEstacion = isDisableEstacion;
    }

    public void setIsDisableIdJefe(boolean isDisableIdJefe) {
        this.isDisableIdJefe = isDisableIdJefe;
    }

    public void setIsDisableLicencia(boolean isDisableLicencia) {
        this.isDisableLicencia = isDisableLicencia;
    }

    public boolean isDisableEstacion() {
        return isDisableEstacion;
    }

    public boolean isDisableIdJefe() {
        return isDisableIdJefe;
    }

    public boolean isDisableLicencia() {
        return isDisableLicencia;
    }
  
    public String getFechaIngresoAno() {
        //System.out.println("ger fia");
        return fechaIngresoAno;
    }

    public String getFechaIngresoDia() {
        //System.out.println("get fid");
        return fechaIngresoDia;
    }

    public String getFechaIngresoMes() {
        //System.out.println("get fim");
        return fechaIngresoMes;
    }

    public String getFechaNacimientoAno() {
        //System.out.println("get fna");
        return fechaNacimientoAno;
    }

    public String getFechaNacimientoDia() {
        //System.out.println("get fnd");
        return fechaNacimientoDia;
    }

    public String getFechaNacimientoMes() {
        //System.out.println("get fnm");
        return fechaNacimientoMes;
    }

    public void setFechaIngresoAno(String fechaIngresoAno) {
        //System.out.println("set fia");
        this.fechaIngresoAno = fechaIngresoAno;
    }

    public void setFechaIngresoDia(String fechaIngresoDia) {
        //System.out.println("set fid");
        this.fechaIngresoDia = fechaIngresoDia;
    }

    public void setFechaIngresoMes(String fechaIngresoMes) {
        //System.out.println("set fim");
        this.fechaIngresoMes = fechaIngresoMes;
    }

    public void setFechaNacimientoAno(String fechaNacimientoAno) {
        //System.out.println("set fna");
        this.fechaNacimientoAno = fechaNacimientoAno;
    }

    public void setFechaNacimientoDia(String fechaNacimientoDia) {
        //System.out.println("set fnd");
        this.fechaNacimientoDia = fechaNacimientoDia;
    }

    public void setFechaNacimientoMes(String fechaNacimientoMes) {
        //System.out.println("set fnm");
        this.fechaNacimientoMes = fechaNacimientoMes;
    }

    public String getIdentificacionJefe() {
        //System.out.println("get id jefe");
        return identificacionJefe;
    }

    public String getLugarTrabajo() {
        //System.out.println("get lugar trabajo");
        return lugarTrabajo;
    }

    public String getLicencia() {
        //System.out.println("get licencia");
        return licencia;
    }

    public void setLicencia(String licencia) {
        //System.out.println("set licencia");
        this.licencia = licencia;
    }

    public String getPasswordConfirmar() {
        //System.out.println("get pass conf");
        return passwordConfirmar;
    }

    public void setPasswordConfirmar(String passwordConfirmar) {
        //System.out.println("set pass conf");
        this.passwordConfirmar = passwordConfirmar;
    }

    public void setNombre2(String nombre2) {
        //System.out.println("set nombre2");
        this.nombre2 = nombre2;
    }

    public boolean isEstado() {
        //System.out.println("get estado");
        return estado;
    }

    public void setLogin(String login) {
        //System.out.println("get login");
        this.login = login;
    }

    public void setPassword(String password) {
        //System.out.println("get pass");
        this.password = password;
    }

    public String getLogin() {
        //System.out.println("get login");
        return login;
    }

    public String getPassword() {
        //System.out.println("get pass");
        return password;
    }

    public String getApellido() {
        //System.out.println("get apellido");
        return apellido;
    }

    public String getApellido2() {
        //System.out.println("get apellido2");
        return apellido2;
    }

    public String getCargo() {
        //System.out.println("get cargo");
        return cargo;
    }

    public String getDireccion() {
        //System.out.println("get dir");
        return direccion;
    }

    public String getEmail() {
        //System.out.println("get email");
        return email;
    }

    public String getIdentificacion() {
        //System.out.println("get id");
        return identificacion;
    }

    public String getTipoId() {
        //System.out.println("get tipo id");
        return tipoId;
    }

    public String getNombre() {
        //System.out.println("get nmbre");
        return nombre;
    }

    public String getNombre2() {
        //System.out.println("get nombre2");
        return nombre2;
    }

    public String getSalario() {
        //System.out.println("get salario");
        return salario;
    }

    public String getTelefono() {
        //System.out.println("get telefono");
        return telefono;
    }

    public boolean getEstado() {
        //System.out.println("get estado");
        return estado;
    }

    public void setIdentificacionJefe(String identificacionJefe) {
        //System.out.println("set id jefe");
        if(!identificacionJefe.equals("---"))
        {
            this.identificacionJefe = identificacionJefe.split(" -")[0];
        }
    }

    public void setLugarTrabajo(String lugarTrabajo) {
        if(!lugarTrabajo.equals("---"))
        {
            String idEstacion = lugarTrabajo.split(" -")[0];
            this.lugarTrabajo = idEstacion;
        }else
        {
            this.lugarTrabajo = "-1";
        }
    }

    public void setApellido(String apellido) {
        System.out.println("set apellido");
        this.apellido = apellido;
    }

    public void setApellido2(String apellido2) {
        //System.out.println("set apelldio2");
        this.apellido2 = apellido2;
    }

    public void setCargo(String cargo) {
        //System.out.println("set cargo");
        this.cargo = cargo;
        if(this.cargo.equals("Auxiliar"))
        {
            this.isDisableEstacion = false;
            this.isDisableIdJefe = false;
            this.isDisableLicencia = true;
        }else if(this.cargo.equals("Operario"))
        {
            this.isDisableEstacion = true;
            this.isDisableIdJefe = false;
            this.isDisableLicencia = true;
        }else if(this.cargo.equals("Conductor"))
        {
            this.isDisableEstacion = true;
            this.isDisableIdJefe = true;
            this.isDisableLicencia = false;
        }else
        {
            this.isDisableEstacion = true;
            this.isDisableIdJefe = true;
            this.isDisableLicencia = true;
        }
    }

    public void setDireccion(String direccion) {
        //System.out.println("set direcion");
        this.direccion = direccion;
    }

    public void setEmail(String email) {
        //System.out.println("set email");
        this.email = email;
    }

    public void setIdentificacion(String identificacion) {
        //System.out.println("set id");
        this.identificacion = identificacion;
    }

    public void setTipoId(String tipoId) {
        //System.out.println("set tipo id");
        this.tipoId = tipoId;
    }

    public void setNombre(String nombre) {
        //System.out.println("set nombre");
        this.nombre = nombre;
    }

    public void setSalario(String salario) {
        //System.out.println("set salario");
        this.salario = salario;
    }

    public void setTelefono(String telefono) {
        //System.out.println("set telefono");
        this.telefono = telefono;
    }

    public void setEstado(boolean estado) {
        //System.out.println("set estado");
        this.estado = estado;
    }

    public List<SelectItem> getAvailableCargo() {
        FacesContext context = FacesContext.getCurrentInstance();
        EmployeeHolder empleadoHolder = (EmployeeHolder) context.getApplication().evaluateExpressionGet(context, "#{employeeHolder}", EmployeeHolder.class);
        int rol = empleadoHolder.getCurrentEmpleado().getRol();
        List<SelectItem> availableCargos = new ArrayList<SelectItem>();

        if (rol == 0) //Administrador    
        {
            availableCargos.add(new SelectItem("Administrador"));
            availableCargos.add(new SelectItem("Director"));
            availableCargos.add(new SelectItem("Operario"));
            availableCargos.add(new SelectItem("Auxiliar"));
            availableCargos.add(new SelectItem("Conductor"));
        } else if (rol == 1) //Director
        {
            availableCargos.add(new SelectItem("Operario"));
            availableCargos.add(new SelectItem("Auxiliar"));
            availableCargos.add(new SelectItem("Conductor"));
        } else if (rol == 2) //Operario
        {
            availableCargos.add(new SelectItem("Auxiliar"));
        }

        return availableCargos;
    }

    public List<SelectItem> getAvailableTipoId() {
        List<SelectItem> availableTipoId = new ArrayList<SelectItem>();
        availableTipoId.add(new SelectItem("CC"));

        return availableTipoId;
    }

    public List<SelectItem> getAvailableEstacionPrincipal(){
        List<SelectItem> availableEstacion = new ArrayList<SelectItem>();

        DaoEstacion daoEstacion = new DaoEstacion();
        Vector<EstacionPrincipal> estaciones = daoEstacion.findAllEstacionPrincipal();
        daoEstacion = null;
        availableEstacion.add(new SelectItem("---"));
        for(int i=0;i<estaciones.size();i++)
        {
            String nombreEstacion = estaciones.get(i).getId() + " - " + estaciones.get(i).getNombre();
            availableEstacion.add(new SelectItem(nombreEstacion));
        } 
        return availableEstacion;
    }
    
    public List<SelectItem> getAvailableJefe(){

        List<SelectItem> availableJefe = new ArrayList<SelectItem>();

        availableJefe.add(new SelectItem("---"));
        DaoEmpleado daoEmpleado = new DaoEmpleado();
        if(this.cargo.equals("Operario"))
        {
            System.out.println("entro a available jefe para operario");
           Vector<Director> directores = daoEmpleado.findAllDirector();
           for(int i=0;i<directores.size();i++)
           {
               Director director = directores.get(i);
               String nombreDir = director.getId() + " - " + director.getNombre() + " " + director.getApellido();
               availableJefe.add(new SelectItem(nombreDir));
           }
        }else if(this.cargo.equals("Auxiliar"))
        {
           Vector<Operario> operarios = daoEmpleado.findAllOperario();
           for(int i=0;i<operarios.size();i++)
           {
               Operario operario = operarios.get(i);
               String nombreDir = operario.getId() + " - " + operario.getNombre() + " " + operario.getApellido();
               availableJefe.add(new SelectItem(nombreDir));
           } 
        }
        daoEmpleado = null;
            
        return availableJefe;
    }
    
    public String createUser() {
        this.validate();
        if (this.countValidator > 0) {
            return null;
        }
        FacesContext context = FacesContext.getCurrentInstance();
        BeanContent content = (BeanContent) context.getApplication().evaluateExpressionGet(context, "#{beanContent}", BeanContent.class);
        int result;
        DaoEmpleado daoEmpleado = new DaoEmpleado();
        Empleado empleado = new Empleado();
        empleado.setNombre(nombre.trim());
        empleado.setNombre2(nombre2.trim());
        empleado.setApellido(apellido.trim());
        empleado.setApellido2(apellido2.trim());
        empleado.setTipoId(tipoId.trim());
        empleado.setId(identificacion.trim());
        empleado.setTelefono(telefono.trim());
        empleado.setDireccion(direccion.trim());
        empleado.setEmail(email.trim());
        empleado.setFechaNacimiento(fechaNacimiento);
        empleado.setFechaIngreso(fechaIngreso);
        if(salario.trim().equals("")){
            empleado.setSalario(-1);
        }else{
            empleado.setSalario(Integer.parseInt(salario.trim()));
        }
        int rol = -1;
        if (cargo.equals("Administrador")) {
            rol = 0;
        } else if (cargo.equals("Director")) {
            rol = 1;
        } else if (cargo.equals("Operario")) {
            rol = 2;
        } else if (cargo.equals("Auxiliar")) {
            rol = 3;
        } else if (cargo.equals("Conductor")) {
            rol = 4;
        }
        empleado.setRol(rol);
        empleado.setLogin(login.trim());
        empleado.setPassword(password.trim());
        empleado.setEstado(estado);
        result = daoEmpleado.saveEmpleado(empleado);
        if(result == 0){
            content.setResultOperation("El Empleado no pudo ser creado.");
            this.clearStates();
            return "resultOperation";
        }
            
        if (rol == 1) {
            Director director = new Director();
            director.setId(identificacion.trim());
            result = daoEmpleado.saveDirector(director);
        }
        if (rol == 2) {
            Operario operario = new Operario();
            operario.setId(identificacion.trim());
            operario.setIdJefe(identificacionJefe.trim());
            daoEmpleado.saveOperario(operario);
        }
        if (rol == 3) {
            Auxiliar auxiliar = new Auxiliar();
            auxiliar.setId(identificacion.trim());
            auxiliar.setIdJefe(identificacionJefe.trim());
            auxiliar.setTrabajaEn(Integer.parseInt(lugarTrabajo));
            daoEmpleado.saveAuxiliar(auxiliar);
        }
        if (rol == 4) {
            Conductor conductor = new Conductor();
            conductor.setId(identificacion.trim());
            conductor.setLicencia(licencia.trim());
            daoEmpleado.saveConductor(conductor);
        }
        daoEmpleado = null;
        content.setResultOperation("El Empleado fue creado con exito.");
        this.clearStates();
        return "resultOperation";
    }

    private void validate() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (nombre.trim().length() > 15) {
            context.addMessage(null, new FacesMessage("Nombre no debe exceder los 15 caracteres."));
            countValidator = 1;
        }
        if (nombre2.trim().length() > 15) {
            context.addMessage(null, new FacesMessage("Nombre2 no debe exceder los 15 caracteres."));
            countValidator = 1;
        }
        if (apellido.trim().length() > 15) {
            context.addMessage(null, new FacesMessage("Apellido no debe exceder los 15 caracteres."));
            countValidator = 1;
        }
        if (apellido2.trim().length() > 15) {
            context.addMessage(null, new FacesMessage("Apellido2 no debe exceder los 15 caracteres."));
            countValidator = 1;
        }
        if (identificacion.trim().length() > 20) {
            context.addMessage(null, new FacesMessage("Identificacion no debe exceder los 20 caracteres."));
            countValidator = 1;
        }
        if (telefono.trim().length() > 20) {
            context.addMessage(null, new FacesMessage("Telefono no debe exceder los 20 caracteres."));
            countValidator = 1;
        }
        if (direccion.trim().length() > 50) {
            context.addMessage(null, new FacesMessage("Direccion no debe exceder los 50 caracteres."));
            countValidator = 1;
        }
        if (email.trim().length() > 50) {
            context.addMessage(null, new FacesMessage("Email no debe exceder los 50 caracteres."));
            countValidator = 1;
        }
        fechaNacimientoAno = fechaNacimientoAno.trim();
        fechaNacimientoMes = fechaNacimientoMes.trim();
        fechaNacimientoDia = fechaNacimientoDia.trim();
        if (fechaNacimientoAno.length() != 0 || fechaNacimientoMes.length() != 0 || fechaNacimientoDia.length() != 0) {
            if(fechaNacimientoAno.length() != 4 || fechaNacimientoMes.length() != 2 || fechaNacimientoDia.length() != 2){
               context.addMessage(null, new FacesMessage("Fecha Nacimiento debe tener el formato es aaaa-mm-dd")); 
               countValidator = 1;
            }else{
                try{
                   Integer.parseInt(fechaNacimientoAno);
                   int mes = Integer.parseInt(fechaNacimientoMes);
                   int dia = Integer.parseInt(fechaNacimientoDia);
                   
                   if(mes < 1 || mes > 12 || dia < 1 || dia > 31){
                       context.addMessage(null, new FacesMessage("Fecha Nacimiento, mes o dia fuera de rango."));
                   }
                   fechaNacimiento = fechaNacimientoAno + "-" +fechaNacimientoMes + "-" +fechaNacimientoDia;
                }catch(NumberFormatException e){
                    context.addMessage(null, new FacesMessage("Fecha Nacimiento debe de ser numerica."));
                    countValidator = 1;
                }
            }    
        }
        fechaIngresoAno = fechaIngresoAno.trim();
        fechaIngresoMes = fechaIngresoMes.trim();
        fechaIngresoDia = fechaIngresoDia.trim();
        if (fechaIngresoAno.length() != 0 || fechaIngresoMes.length() != 0 || fechaIngresoDia.length() != 0) {
            if(fechaIngresoAno.length() != 4 || fechaIngresoMes.length() != 2 || fechaIngresoDia.length() != 2){
               context.addMessage(null, new FacesMessage("Fecha Ingreso debe tener el formato es aaaa-mm-dd")); 
               countValidator = 1;
            }else{
                try{
                   Integer.parseInt(fechaIngresoAno);
                   int mes = Integer.parseInt(fechaIngresoMes);
                   int dia = Integer.parseInt(fechaIngresoDia);
                   
                   if(mes < 1 || mes > 12 || dia < 1 || dia > 31){
                       context.addMessage(null, new FacesMessage("Fecha ingreso, mes o dia fuera de rango."));
                   }
                   fechaIngreso = fechaIngresoAno + "-" +fechaIngresoMes + "-" +fechaIngresoDia;
                }catch(NumberFormatException e){
                    context.addMessage(null, new FacesMessage("Fecha ingreso debe de ser numerica."));
                    countValidator = 1;
                }
            }    
        }
        if (salario.trim().length() != 0) {
            try {
                Integer.parseInt(salario.trim());
            } catch (NumberFormatException e) {
                context.addMessage(null, new FacesMessage("Salario debe ser un numero entero."));
                countValidator = 1;
            }
        }
        if (login.trim().length() > 10) {
            context.addMessage(null, new FacesMessage("Login no debe exceder los 10 caracteres."));
            countValidator = 1;
        }
        if (password.trim().length() > 15) {
            context.addMessage(null, new FacesMessage("Password no debe exceder los 15 caracteres."));
            countValidator = 1;
        }
        if (passwordConfirmar.trim().length() > 15) {
            context.addMessage(null, new FacesMessage("Confirmar password no debe exceder los 15 caracteres."));
            countValidator = 1;
        }
        if (!password.trim().equals(passwordConfirmar)) {
            context.addMessage(null, new FacesMessage("Los password no coinciden."));
            countValidator = 1;
        }
        if (licencia.trim().length() > 20) {
            context.addMessage(null, new FacesMessage("Licencia no debe exceder los 20 caracteres."));
            countValidator = 1;
        }
    }
    
    void clearStates()
    {
        this.nombre = "";
        this.nombre2 = "";
        this.apellido = "";
        this.apellido2 = "";
        this.tipoId = "";
        this.identificacion = "";
        this.telefono = "";
        this.direccion = "";
        this.email = "";
        this.fechaNacimiento="";
        this.fechaNacimientoAno = "";
        this.fechaNacimientoMes = "";
        this.fechaNacimientoDia = "";
        this.fechaIngreso="";
        this.fechaIngresoAno = "";
        this.fechaIngresoMes = "";
        this.fechaIngresoDia = "";
        this.salario = "";
        this.cargo = "";
        this.login = "";
        this.password = "";
        this.passwordConfirmar = "";
        this.estado = true;
        this.licencia="";
        this.identificacionJefe="";
        this.lugarTrabajo="";
        this.isDisableLicencia = true;
        this.isDisableIdJefe = true;
        this.isDisableEstacion = true;
        this.countValidator = 0;
    }
    
    public void clearStatesEvent(ActionEvent e)
    {
        clearStates();
    }
}
