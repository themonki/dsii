/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Empleados.Controlador;

import Empleados.Dao.DaoEmpleado;
import Entidades.Auxiliar;
import Entidades.Conductor;
import Entidades.Director;
import Entidades.Empleado;
import Entidades.Operario;
import Utilidades.BeanContent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author leonardo
 */
@ManagedBean
@RequestScoped
public class BeanEmployee {

    private String nombre;
    private String nombre2;
    private String apellido;
    private String apellido2;
    private String tipoId;
    private String identificacion;
    private String telefono;
    private String direccion;
    private String email;
    private String fechaNacimientoAnio;
    private String fechaNacimientoMes;
    private String fechaNacimientoDia;
    private String fechaIngresoAnio;
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
    private Date fechaN = null;
    private Date fechaI = null;
    private DaoEmpleado daoEmpleado;
    private FacesContext context;

    public String getIdentificacionJefe() {
        return identificacionJefe;
    }

    public String getLugarTrabajo() {
        return lugarTrabajo;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public String getPasswordConfirmar() {
        return passwordConfirmar;
    }

    public void setPasswordConfirmar(String passwordConfirmar) {
        this.passwordConfirmar = passwordConfirmar;
    }

    public void setFechaIngresoAnio(String fechaIngresoAnio) {
        this.fechaIngresoAnio = fechaIngresoAnio;
    }

    public void setFechaIngresoDia(String fechaIngresoDia) {
        this.fechaIngresoDia = fechaIngresoDia;
    }

    public void setFechaIngresoMes(String fechaIngresoMes) {
        this.fechaIngresoMes = fechaIngresoMes;
    }

    public void setFechaNacimientoAnio(String fechaNacimientoAnio) {
        this.fechaNacimientoAnio = fechaNacimientoAnio;
    }

    public void setFechaNacimientoDia(String fechaNacimientoDia) {
        this.fechaNacimientoDia = fechaNacimientoDia;
    }

    public void setFechaNacimientoMes(String fechaNacimientoMes) {
        this.fechaNacimientoMes = fechaNacimientoMes;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }

    public boolean isEstado() {
        return estado;
    }

    public String getFechaIngresoAnio() {
        return fechaIngresoAnio;
    }

    public String getFechaIngresoDia() {
        return fechaIngresoDia;
    }

    public String getFechaIngresoMes() {
        return fechaIngresoMes;
    }

    public String getFechaNacimientoAnio() {
        return fechaNacimientoAnio;
    }

    public String getFechaNacimientoDia() {
        return fechaNacimientoDia;
    }

    public String getFechaNacimientoMes() {
        return fechaNacimientoMes;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getApellido() {
        return apellido;
    }

    public String getApellido2() {
        return apellido2;
    }

    public String getCargo() {
        return cargo;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEmail() {
        return email;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public String getTipoId() {
        return tipoId;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNombre2() {
        return nombre2;
    }

    public String getSalario() {
        return salario;
    }

    public String getTelefono() {
        return telefono;
    }

    public boolean getEstado() {
        return estado;
    }

    public boolean isDisableLicencia() {
        return isDisableLicencia;
    }

    public boolean isDisableIdJefe() {
        return isDisableIdJefe;
    }

    public boolean isDisableEstacion() {
        return isDisableEstacion;
    }

    public void setIdentificacionJefe(String identificacionJefe) {
        this.identificacionJefe = identificacionJefe;
    }

    public void setLugarTrabajo(String lugarTrabajo) {
        this.lugarTrabajo = lugarTrabajo;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
        if (cargo.equals("Conductor")) {
            isDisableLicencia = false;
        }
        if (cargo.equals("Auxiliar")) {
            isDisableIdJefe = false;
            isDisableEstacion = false;
        }
        if (cargo.equals("Operario")) {
            isDisableIdJefe = false;
        }
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public void setTipoId(String tipoId) {
        this.tipoId = tipoId;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEstado(boolean estado) {
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
            availableCargos.add(new SelectItem("Conductor"));
        }

        return availableCargos;
    }

    public List<SelectItem> getAvailableTipoId() {
        List<SelectItem> availableTipoId = new ArrayList<SelectItem>();
        availableTipoId.add(new SelectItem("CC"));

        return availableTipoId;
    }

    public String createUser() {
        context = FacesContext.getCurrentInstance();
        validate();
        if (context.getMessageList().size() > 0) {
            return null;
        }
        BeanContent content = (BeanContent) context.getApplication().evaluateExpressionGet(context, "#{beanContent}", BeanContent.class);
        int result;
        daoEmpleado = new DaoEmpleado();
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
        empleado.setFechaNacimiento(fechaN);
        empleado.setFechaIngreso(fechaI);
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
        empleado.setEstado(true); //estado
        result = daoEmpleado.saveEmpleado(empleado);
        if(result == 0){
            content.setResultOperation("El Empleado no pudo ser creado.");
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
            auxiliar.setTrabajaEn(Integer.parseInt(lugarTrabajo.trim()));
            daoEmpleado.saveAuxiliar(auxiliar);
        }
        if (rol == 4) {
            Conductor conductor = new Conductor();
            conductor.setId(identificacion.trim());
            conductor.setLicencia(licencia.trim());
            daoEmpleado.saveConductor(conductor);
        }

        content.setResultOperation("El Empleado fue creado con exito.");
        return "resultOperation";
    }

    private void validate() {
        if (nombre.length() > 15) {
            context.addMessage(null, new FacesMessage("Nombre, no debe exceder los 15 caracteres."));
        }
        if (nombre2.length() > 15) {
            context.addMessage(null, new FacesMessage("Nombre2, no debe exceder los 15 caracteres."));
        }
        if (apellido.length() > 15) {
            context.addMessage(null, new FacesMessage("Apellido, no debe exceder los 15 caracteres."));
        }
        if (apellido2.length() > 15) {
            context.addMessage(null, new FacesMessage("Apellido2, no debe exceder los 15 caracteres."));
        }
        if (identificacion.length() > 20) {
            context.addMessage(null, new FacesMessage("Identificacion, no debe exceder los 20 caracteres."));
        }
        if (telefono.length() > 20) {
            context.addMessage(null, new FacesMessage("Telefono, no debe exceder los 20 caracteres."));
        }
        if (direccion.length() > 50) {
            context.addMessage(null, new FacesMessage("Direccion, no debe exceder los 50 caracteres."));
        }
        if (email.length() > 50) {
            context.addMessage(null, new FacesMessage("Email, no debe exceder los 50 caracteres."));
        }
        if ((fechaNacimientoAnio.length() != 0) || (fechaNacimientoMes.length() != 0) || (fechaNacimientoDia.length() != 0)) {
            if ((fechaNacimientoAnio.length() != 4) || (fechaNacimientoMes.length() != 2) || (fechaNacimientoDia.length() != 2)) {
                context.addMessage(null, new FacesMessage("Fecha Nacimiento, el formato es aaaa-mm-dd"));
            } else {
                try {
                    System.out.println("anio " + fechaNacimientoAnio + " mes " + fechaNacimientoMes + " dia " + fechaNacimientoDia);
                    int anio = Integer.parseInt(fechaNacimientoAnio);
                    int mes = Integer.parseInt(fechaNacimientoMes);
                    int dia = Integer.parseInt(fechaNacimientoDia);
                    fechaN = new Date(anio, mes, dia);
                    System.out.println("fechaN " + fechaN);
                } catch (NumberFormatException e) {
                    context.addMessage(null, new FacesMessage("Fecha de ingreso, debes ser numerica o no es valida."));
                }
            }

        }
        /*if ((fechaIngresoAnio.length() != 0) || (fechaIngresoMes.length() != 0) || (fechaIngresoDia.length() != 0)) {
            if ((fechaIngresoAnio.length() != 4) || (fechaIngresoMes.length() != 2) || (fechaIngresoDia.length() != 2)) {
                context.addMessage(null, new FacesMessage("Fecha Ingreso", "El formato es aaaa-mm-dd"));
            } else {
                try {
                    System.out.println("anio " + fechaIngresoAnio + " mes " + fechaIngresoMes + " dia " + fechaIngresoDia);
                    int anio = Integer.parseInt(fechaIngresoAnio);
                    int mes = Integer.parseInt(fechaIngresoMes);
                    int dia = Integer.parseInt(fechaIngresoDia);
                    fechaI = new Date(anio, mes, dia);
                    System.out.println("fechaI " + fechaI);
                } catch (NumberFormatException e) {
                    context.addMessage(null, new FacesMessage("Fecha de ingreso, debes ser numerica o no es valida."));
                }
            }
        }*/
        if (salario.length() != 0) {
            try {
                Integer.parseInt(salario);
            } catch (NumberFormatException e) {
                context.addMessage(null, new FacesMessage("Salario, debe ser un numero entero."));
            }
        }
        if (login.length() > 10) {
            context.addMessage(null, new FacesMessage("Login, no debe exceder los 10 caracteres."));
        }
        if (password.length() > 15) {
            context.addMessage(null, new FacesMessage("Password, no debe exceder los 15 caracteres."));
        }
        if (passwordConfirmar.length() > 15) {
            context.addMessage(null, new FacesMessage("Confirmar password, no debe exceder los 15 caracteres."));
        }
        if (!password.equals(passwordConfirmar)) {
            context.addMessage(null, new FacesMessage("Los password no coinciden."));
        }
        /*if (licencia.length() > 20) {
            context.addMessage(null, new FacesMessage("Licencia, no debe exceder los 20 caracteres."));
        }*/
    }
}
