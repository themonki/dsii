/**
 * Clase controladora que conecta la vista con la lógia.
 * @author Leonardo Ríos
 */
package Controlador;

import Dao.DaoBus;
import Dao.DaoEmpleado;
import Dao.DaoEstacion;
import Entidades.Auxiliar;
import Entidades.Bus;
import Entidades.Conductor;
import Entidades.Director;
import Entidades.Empleado;
import Entidades.EstacionPrincipal;
import Entidades.Operario;
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

@ManagedBean
@SessionScoped
public class BeanEmployee implements Serializable {

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
    private String nuevoPassword;
    private String nuevoPasswordConfirmar;
    private boolean estado;
    private String estadoS;
    private String licencia;
    private String identificacionJefe;
    private Integer lugarTrabajo;
    private String conduceBus;
    //usado para control y calculos
    private boolean isDisableLicencia;
    private boolean isDisableIdJefe;
    private boolean isDisableEstacion;
    private boolean isRenderTableSearch;
    private int countValidator;
    private String action;
    private boolean avancedSearch;
    private boolean avancedSearchNombre;
    private boolean avancedSearchApellido;
    private boolean avancedSearchCargo;
    private boolean editIdentificacion;
    private boolean editPassword;
    private String loginOld;

    public void setConduceBus(String conduceBus) {
        this.conduceBus = conduceBus;
    }

    public String getConduceBus() {
        return conduceBus;
    }

    public void setEstadoS(String estadoS) {
        this.estadoS = estadoS;
    }

    public String getEstadoS() {
        return estadoS;
    }

    public void setNuevoPassword(String nuevoPassword) {
        this.nuevoPassword = nuevoPassword;
    }

    public void setNuevoPasswordConfirmar(String nuevoPasswordConfirmar) {
        this.nuevoPasswordConfirmar = nuevoPasswordConfirmar;
    }

    public String getNuevoPassword() {
        return nuevoPassword;
    }

    public String getNuevoPasswordConfirmar() {
        return nuevoPasswordConfirmar;
    }

    public void setEditPassword(boolean editPassword) {
        this.editPassword = editPassword;
    }

    public boolean isEditPassword() {
        return editPassword;
    }

    public void setEditIdentificacion(boolean editIdentificacion) {
        this.editIdentificacion = editIdentificacion;
    }

    public boolean isEditIdentificacion() {
        return editIdentificacion;
    }

    public void setAvancedSearchApellido(boolean avancedSearchApellido) {
        this.avancedSearchApellido = avancedSearchApellido;
    }

    public void setAvancedSearchCargo(boolean avancedSearchCargo) {
        this.avancedSearchCargo = avancedSearchCargo;
    }

    public void setAvancedSearchNombre(boolean avancedSearchNombre) {
        this.avancedSearchNombre = avancedSearchNombre;
    }

    public boolean isAvancedSearchApellido() {
        return avancedSearchApellido;
    }

    public boolean isAvancedSearchCargo() {
        return avancedSearchCargo;
    }

    public boolean isAvancedSearchNombre() {
        return avancedSearchNombre;
    }

    public void setAvancedSearch(boolean avancedSearch) {
        this.avancedSearch = avancedSearch;

        this.isRenderTableSearch = true;
    }

    public boolean isAvancedSearch() {
        return avancedSearch;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setRenderTableSearch(boolean isRenderTableSearch) {
        this.isRenderTableSearch = isRenderTableSearch;
    }

    public boolean isRenderTableSearch() {
        //System.err.println("get rendetTableSearch " + this.isRenderTableSearch);
        return isRenderTableSearch;
    }

    public void setDisableEstacion(boolean isDisableEstacion) {
        this.isDisableEstacion = isDisableEstacion;
    }

    public void setDisableIdJefe(boolean isDisableIdJefe) {
        this.isDisableIdJefe = isDisableIdJefe;
    }

    public void setDisableLicencia(boolean isDisableLicencia) {
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

    public Integer getLugarTrabajo() {
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

    public void setIdentificacionJefe(String identificacionJefe) {
        //System.out.println("set id jefe");
        this.identificacionJefe = identificacionJefe;
    }

    public void setLugarTrabajo(Integer lugarTrabajo) {

        this.lugarTrabajo = lugarTrabajo;
    }

    public void setApellido(String apellido) {
        //System.out.println("set apellido");
        this.apellido = apellido;
    }

    public void setApellido2(String apellido2) {
        //System.out.println("set apelldio2");
        this.apellido2 = apellido2;
    }

    public void setCargo(String cargo) {
        //System.out.println("set cargo");
        this.cargo = cargo;
        if (this.cargo.equals("Auxiliar")) {
            this.isDisableEstacion = false;
            this.isDisableIdJefe = false;
            this.isDisableLicencia = true;
        } else if (this.cargo.equals("Operario")) {
            this.isDisableEstacion = true;
            this.isDisableIdJefe = false;
            this.isDisableLicencia = true;
        } else if (this.cargo.equals("Conductor")) {
            this.isDisableEstacion = true;
            this.isDisableIdJefe = true;
            this.isDisableLicencia = false;
        } else {
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
        //System.out.println("set identificacion");
        this.identificacion = identificacion;

        this.isRenderTableSearch = true;//debe ser provicional
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
        availableTipoId.add(new SelectItem("CE"));

        return availableTipoId;
    }

    public List<SelectItem> getAvailableEstacionPrincipal() {
        List<SelectItem> availableEstacion = new ArrayList<SelectItem>();

        DaoEstacion daoEstacion = new DaoEstacion();
        List<EstacionPrincipal> estaciones = daoEstacion.findAllEstacionPrincipal();
        daoEstacion = null;

        availableEstacion.add(new SelectItem(-1, "Sin Estación"));
        for (int i = 0; i < estaciones.size(); i++) {
            EstacionPrincipal estacion = estaciones.get(i);
            availableEstacion.add(new SelectItem(estacion.getId(), estacion.getNombre()));
        }
        return availableEstacion;
    }

    public List<SelectItem> getAvailableJefe() {

        List<SelectItem> availableJefe = new ArrayList<SelectItem>();

        availableJefe.add(new SelectItem("", "Sin Jefe"));
        DaoEmpleado daoEmpleado = new DaoEmpleado();
        if (this.cargo.equals("Operario")) {
            List<Director> directores = daoEmpleado.findAllDirector(false);
            for (int i = 0; i < directores.size(); i++) {
                Director director = directores.get(i);
                String nombreDir = director.getNombre() + " " + director.getApellido();
                availableJefe.add(new SelectItem(director.getId(), nombreDir));
            }
        } else if (this.cargo.equals("Auxiliar")) {
            List<Operario> operarios = daoEmpleado.findAllOperario(false);
            for (int i = 0; i < operarios.size(); i++) {
                Operario operario = operarios.get(i);
                String nombreDir = operario.getNombre() + " " + operario.getApellido();
                availableJefe.add(new SelectItem(operario.getId(), nombreDir));
            }
        }
        daoEmpleado = null;

        return availableJefe;
    }

    public List<SelectItem> getAvailableEstado() {
        List<SelectItem> availableCargo = new ArrayList<SelectItem>();
        availableCargo.add(new SelectItem(true, "Activo"));
        availableCargo.add(new SelectItem(false, "Inactivo"));
        return availableCargo;
    }

    public List<SelectItem> getAvailableOperario() {
        List<SelectItem> availableOperarios = new ArrayList<SelectItem>();
        DaoEmpleado daoEmpleado = new DaoEmpleado();

        List<Operario> operarios = daoEmpleado.findAllOperario(false);
        for (int i = 0; i < operarios.size(); i++) {
            Operario operario = operarios.get(i);
            String nombreOper = operario.getNombre() + " " + operario.getApellido();
            availableOperarios.add(new SelectItem(operario.getId(), nombreOper));
        }

        return availableOperarios;
    }

    public List<SelectItem> getAvailableBus() {
        DaoBus daoBus = new DaoBus();
        List<Bus> buses = daoBus.consultarBusesSinConductor();
        List<SelectItem> availabeBus = new ArrayList<SelectItem>();
        availabeBus.add(new SelectItem("","Sin Bus"));
        if(!this.conduceBus.equals("")){
            availabeBus.add(new SelectItem(this.conduceBus,this.conduceBus));
        }
        for(int i=0;i<buses.size();i++){
            Bus bus = buses.get(i);
            availabeBus.add(new SelectItem(bus.getMatricula(),bus.getMatricula()));
        }
        return availabeBus;
    }

    public String createUser() {
        this.validate();
        this.validateExist(true);
        if (this.countValidator > 0) {
            this.countValidator = 0;
            return null;
        }
        FacesContext context = FacesContext.getCurrentInstance();
        BeanContent content = (BeanContent) context.getApplication().evaluateExpressionGet(context, "#{beanContent}", BeanContent.class);
        int result;
        DaoEmpleado daoEmpleado = new DaoEmpleado();
        Empleado empleado = this.createEmpleado();
        result = daoEmpleado.saveEmpleado(empleado);
        if (result == 0) {
            content.setResultOperation("El Empleado no pudo ser creado.");
            content.setImage("./resources/fail.png");
            this.clearStates();
            return "resultOperation";
        }

        int rol = empleado.getRol();
        if (rol == 1) {
            Director director = new Director();
            director.setId(identificacion.trim());
            result = daoEmpleado.saveDirector(director);
        }
        if (rol == 2) {
            Operario operario = new Operario();
            operario.setId(identificacion.trim());
            operario.setIdJefe(identificacionJefe.trim());
            result = daoEmpleado.saveOperario(operario);
        }
        if (rol == 3) {
            Auxiliar auxiliar = new Auxiliar();
            auxiliar.setId(identificacion.trim());
            auxiliar.setIdJefe(identificacionJefe.trim());
            auxiliar.setTrabajaEn(lugarTrabajo);
            result = daoEmpleado.saveAuxiliar(auxiliar);
        }
        if (rol == 4) {
            Conductor conductor = new Conductor();
            conductor.setId(identificacion.trim());
            conductor.setLicencia(licencia.trim());
            conductor.setConduceBus(conduceBus);
            result = daoEmpleado.saveConductor(conductor);
        }
        daoEmpleado = null;
        if (result != 0) {
            content.setResultOperation("El Empleado fue creado con exito.");
            content.setImage("./resources/ok.png");
            this.clearStates();
        }else{
            content.setResultOperation("Esto es un error GRAVE, se actualizó la"
                    + " tabla empleado pero no la tabla específica del rol.");
            content.setImage("./resources/fail.png");
            this.clearStates();
        }
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
            if (fechaNacimientoAno.length() != 4 || fechaNacimientoMes.length() != 2 || fechaNacimientoDia.length() != 2) {
                context.addMessage(null, new FacesMessage("Fecha Nacimiento debe tener el formato es aaaa-mm-dd"));
                countValidator = 1;
            } else {
                try {
                    Integer.parseInt(fechaNacimientoAno);
                    int mes = Integer.parseInt(fechaNacimientoMes);
                    int dia = Integer.parseInt(fechaNacimientoDia);

                    if (mes < 1 || mes > 12 || dia < 1 || dia > 31) {
                        context.addMessage(null, new FacesMessage("Fecha Nacimiento, mes o dia fuera de rango."));
                    }
                    fechaNacimiento = fechaNacimientoAno + "-" + fechaNacimientoMes + "-" + fechaNacimientoDia;
                } catch (NumberFormatException e) {
                    context.addMessage(null, new FacesMessage("Fecha Nacimiento debe de ser numerica."));
                    countValidator = 1;
                }
            }
        }
        fechaIngresoAno = fechaIngresoAno.trim();
        fechaIngresoMes = fechaIngresoMes.trim();
        fechaIngresoDia = fechaIngresoDia.trim();
        if (fechaIngresoAno.length() != 0 || fechaIngresoMes.length() != 0 || fechaIngresoDia.length() != 0) {
            if (fechaIngresoAno.length() != 4 || fechaIngresoMes.length() != 2 || fechaIngresoDia.length() != 2) {
                context.addMessage(null, new FacesMessage("Fecha Ingreso debe tener el formato es aaaa-mm-dd"));
                countValidator = 1;
            } else {
                try {
                    Integer.parseInt(fechaIngresoAno);
                    int mes = Integer.parseInt(fechaIngresoMes);
                    int dia = Integer.parseInt(fechaIngresoDia);

                    if (mes < 1 || mes > 12 || dia < 1 || dia > 31) {
                        context.addMessage(null, new FacesMessage("Fecha ingreso, mes o dia fuera de rango."));
                    }
                    fechaIngreso = fechaIngresoAno + "-" + fechaIngresoMes + "-" + fechaIngresoDia;
                } catch (NumberFormatException e) {
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

    private void validateExist(boolean all) {
        FacesContext context = FacesContext.getCurrentInstance();
        DaoEmpleado daoEmpleado = new DaoEmpleado();
        if (all) {
            boolean existId = daoEmpleado.existEmpleadoId(this.identificacion.trim());
            if (existId) {
                context.addMessage(null, new FacesMessage("Ya existe un empleado con la identificación proporcionada."));
                countValidator = 1;
            }

            boolean existLogin = daoEmpleado.existEmpleadoLogin(this.login.trim());
            if (existLogin) {
                context.addMessage(null, new FacesMessage("Ya existe un empleado con el login proporcionado, por favor seleccione otro."));
                countValidator = 1;
            }
        } else {
            boolean existLogin = daoEmpleado.existEmpleadoLogin(this.login.trim());
            if (existLogin) {
                context.addMessage(null, new FacesMessage("Ya existe un empleado con el login proporcionado, por favor seleccione otro."));
                countValidator = 1;
            }
        }
        daoEmpleado = null;
    }

    private void validateEdit() {
        this.passwordConfirmar = this.password;
        this.validate();
        if (!this.login.equals(this.loginOld)) {
            this.validateExist(false);
        }
        if (this.editPassword) {
            FacesContext context = FacesContext.getCurrentInstance();
            DaoEmpleado daoEmpleado = new DaoEmpleado();
            boolean verifyPassword = daoEmpleado.verifyPassword(this.identificacion, this.password.trim());
            if (!verifyPassword) {
                context.addMessage(null, new FacesMessage("El password proporcionado no es correcto."));
                countValidator = 1;
            }
            if (!this.nuevoPassword.trim().equals(this.nuevoPasswordConfirmar.trim())) {
                context.addMessage(null, new FacesMessage("Los nuevos password no coinciden."));
                countValidator = 1;
            }
        }
    }

    void clearStates() {
        this.nombre = "";
        this.nombre2 = "";
        this.apellido = "";
        this.apellido2 = "";
        this.tipoId = "";
        this.identificacion = "";
        this.telefono = "";
        this.direccion = "";
        this.email = "";
        this.fechaNacimiento = "";
        this.fechaNacimientoAno = "";
        this.fechaNacimientoMes = "";
        this.fechaNacimientoDia = "";
        this.fechaIngreso = "";
        this.fechaIngresoAno = "";
        this.fechaIngresoMes = "";
        this.fechaIngresoDia = "";
        this.salario = "";
        this.cargo = "";
        this.login = "";
        this.password = "";
        this.passwordConfirmar = "";
        this.estado = true;
        this.estadoS = "Activo";
        this.licencia = "";
        this.identificacionJefe = "";
        this.lugarTrabajo = -1;
        this.isDisableLicencia = true;
        this.isDisableIdJefe = true;
        this.isDisableEstacion = true;
        this.countValidator = 0;
        this.conduceBus = "";
    }

    public void statesForNew(ActionEvent e) {
        this.clearStates();
        FacesContext context = FacesContext.getCurrentInstance();
        EmployeeHolder empleadoHolder = (EmployeeHolder) context.getApplication().evaluateExpressionGet(context, "#{employeeHolder}", EmployeeHolder.class);
        int rol = empleadoHolder.getCurrentEmpleado().getRol();
        if(rol==2)//operario
        {
            this.isDisableEstacion = false;
            this.isDisableIdJefe = false;
            this.cargo = "Auxiliar";
        } 
    }

    public void statesForFind(ActionEvent e) {
        this.isRenderTableSearch = false;
        this.avancedSearch = false;
        this.avancedSearchNombre = false;
        this.avancedSearchApellido = false;
        this.avancedSearchCargo = false;
        this.clearStates();
        this.action = "Detalle";
    }

    public void statesForFindReturn(ActionEvent e) {
        this.clearStates();
    }

    public void statesForErase(ActionEvent e) {
        this.isRenderTableSearch = false;
        this.clearStates();
        this.action = "Eliminar";
    }

    public void statesForEdit(ActionEvent e) {
        this.isRenderTableSearch = false;
        this.avancedSearch = false;
        this.avancedSearchNombre = false;
        this.avancedSearchApellido = false;
        this.avancedSearchCargo = false;
        this.editIdentificacion = false;
        this.editPassword = false;
        this.nuevoPassword = "";
        this.nuevoPasswordConfirmar = "";
        this.clearStates();
        this.action = "Editar";
    }

    public List<Empleado> getFindEmployee() {
        FacesContext context = FacesContext.getCurrentInstance();
        EmployeeHolder empleadoHolder = (EmployeeHolder) context.getApplication().evaluateExpressionGet(context, "#{employeeHolder}", EmployeeHolder.class);
        int rol = empleadoHolder.getCurrentEmpleado().getRol();

        if (rol == 2) {
            this.avancedSearchCargo = true;
        }

        DaoEmpleado daoEmpleado = new DaoEmpleado();
        List<Empleado> empleados = null;

        String opcion = "";
        if (this.avancedSearch) {

            if (this.avancedSearchNombre) {
                opcion += "nombre LIKE '%" + this.nombre + "%'";
                if (this.avancedSearchApellido) {
                    opcion += " AND apellido LIKE '%" + this.apellido + "%'";
                    if (this.avancedSearchCargo) {
                        int rolF = this.findRol(this.cargo);
                        opcion += " AND rol = " + rolF;
                    }
                } else if (this.avancedSearchCargo) {
                    int rolF = this.findRol(this.cargo);
                    opcion += " AND rol = " + rolF;
                }

            } else if (this.avancedSearchApellido) {
                opcion += "apellido LIKE '%" + this.apellido + "%'";
                if (this.avancedSearchCargo) {
                    int rolF = this.findRol(this.cargo);
                    opcion += " AND rol = " + rolF;
                }
            } else if (this.avancedSearchCargo) {
                int rolF = this.findRol(this.cargo);
                opcion += "rol = " + rolF;
            }

            if (opcion.equals("")) {
                if (rol == 2)//opeario
                {
                    empleados = daoEmpleado.findEmpleadoCondition("rol = 3");
                } else {
                    empleados = daoEmpleado.findAllEmpleado(true);
                }

            } else {
                empleados = daoEmpleado.findEmpleadoCondition(opcion);
            }
        } else {
            if (this.identificacion.trim().equals("")) {
                if (rol == 2)//Operario
                {
                    empleados = daoEmpleado.findEmpleadoCondition("rol = 3");//busca solo auxiliares
                } else {
                    empleados = daoEmpleado.findAllEmpleado(true);
                }
            } else {
                if (rol == 2) {
                    empleados = daoEmpleado.findEmpleadoCondition("id = '" + this.identificacion.trim() + "' AND rol = 3");//buscar auxiliar con un id dado
                } else {
                    Empleado empleado = daoEmpleado.findEmpleadoId(this.identificacion.trim(), true);
                    empleados = new ArrayList<Empleado>();
                    empleados.add(empleado);
                }
            }
        }

        daoEmpleado = null;

        if (empleados.isEmpty() || empleados.get(0).getId() == null) {
            context.addMessage(null, new FacesMessage("No existe empleado con los datos proporcionados."));
            return null;
        } else {
            return empleados;
        }
    }

    public String getLinkAction() {
        String link = "";
        if (this.action.equals("Detalle")) {
            this.prepareDataEmployee();
            link = "detailEmployee";
        } else if (this.action.equals("Eliminar")) {
            this.prepareDataEmployee();
            link = "eraseEmployee";
        } else if (this.action.equals("Editar")) {
            this.prepareDataEmployee();
            link = "editEmployee";
        }

        return link;
    }

    private Empleado getCurrentEmpleado() {
        FacesContext context = FacesContext.getCurrentInstance();
        Application app = context.getApplication();
        Empleado empleado = (Empleado) app.evaluateExpressionGet(context, "#{employee}", Empleado.class);
        return empleado;
    }

    private void prepareDataEmployee() {
        Empleado empleado = this.getCurrentEmpleado();

        this.nombre = empleado.getNombre();
        this.nombre2 = empleado.getNombre2();
        if (this.nombre2 == null) {
            this.nombre2 = "";
        }
        this.apellido = empleado.getApellido();
        this.apellido2 = empleado.getApellido2();
        if (this.apellido2 == null) {
            this.apellido2 = "";
        }
        this.tipoId = empleado.getTipoId();
        this.identificacion = empleado.getId();
        this.telefono = empleado.getTelefono();
        if (this.telefono == null) {
            this.telefono = "";
        }
        this.direccion = empleado.getDireccion();
        if (this.direccion == null) {
            this.direccion = "";
        }
        this.email = empleado.getEmail();
        if (this.email == null) {
            this.email = "";
        }
        this.fechaNacimiento = empleado.getFechaNacimiento();
        this.fechaIngreso = empleado.getFechaIngreso();
        this.salario = Integer.toString(empleado.getSalario());

        int rol = empleado.getRol();
        String cargoObtenido = "";
        DaoEmpleado daoEmpleado = new DaoEmpleado();
        switch (rol) {
            case 0: {
                cargoObtenido = "Administrador";
                break;
            }
            case 1: {
                cargoObtenido = "Director";
                break;
            }
            case 2: {
                cargoObtenido = "Operario";
                Operario operario = daoEmpleado.findOpearioId(this.identificacion, true);
                this.identificacionJefe = operario.getIdJefe();
                this.isDisableIdJefe = false;
                operario = null;
                break;
            }
            case 3: {
                cargoObtenido = "Auxiliar";
                Auxiliar auxiliar = daoEmpleado.findAuxiliarId(this.identificacion, true);
                this.identificacionJefe = auxiliar.getIdJefe();
                this.lugarTrabajo = auxiliar.getTrabajaEn();
                this.isDisableEstacion = false;
                this.isDisableIdJefe = false;
                auxiliar = null;
                break;
            }
            case 4: {
                cargoObtenido = "Conductor";
                Conductor conductor = daoEmpleado.findConductorId(this.identificacion, true);
                this.licencia = conductor.getLicencia();
                if(this.licencia == null){
                    this.licencia = "";
                }
                this.conduceBus = conductor.getConduceBus();
                if(this.conduceBus == null){
                    this.conduceBus = "";
                }
                this.isDisableLicencia = false;
                conductor = null;
            }
        }
        this.cargo = cargoObtenido;
        this.login = empleado.getLogin();
        this.loginOld = this.login;
        this.estado = empleado.getEstado();
        if (this.estado) {
            this.estadoS = "Activo";
        } else {
            this.estadoS = "Inactivo";
        }

        if (this.fechaIngreso != null) {
            String[] partes = this.fechaIngreso.split("-");
            this.fechaIngresoAno = partes[0];
            this.fechaIngresoMes = partes[1];
            this.fechaIngresoDia = partes[2];
        } else {
            this.fechaIngreso = "";
        }
        if (this.fechaNacimiento != null) {
            String[] partes = this.fechaNacimiento.split("-");
            this.fechaNacimientoAno = partes[0];
            this.fechaNacimientoMes = partes[1];
            this.fechaNacimientoDia = partes[2];
        } else {
            this.fechaNacimiento = "";
        }
    }

    private Integer findRol(String cargo) {
        Integer rolFind = -1;
        if (cargo.equals("Administrador")) {
            rolFind = 0;
        } else if (cargo.equals("Director")) {
            rolFind = 1;
        } else if (cargo.equals("Operario")) {
            rolFind = 2;
        } else if (cargo.equals("Auxiliar")) {
            rolFind = 3;
        } else if (cargo.equals("Conductor")) {
            rolFind = 4;
        }

        return rolFind;
    }

    public String updateUser() {
        this.validateEdit();
        if (this.countValidator > 0) {
            this.countValidator = 0;
            return null;
        }
        FacesContext context = FacesContext.getCurrentInstance();
        BeanContent content = (BeanContent) context.getApplication().evaluateExpressionGet(context, "#{beanContent}", BeanContent.class);
        int result;
        DaoEmpleado daoEmpleado = new DaoEmpleado();
        Empleado empleado = this.createEmpleado();
        if (!this.editPassword) {
            empleado.setPassword("");
        } else {
            empleado.setPassword(this.nuevoPassword);
        }
        result = daoEmpleado.updateEmpleado(empleado);
        if (result == 0) {
            content.setResultOperation("El Empleado no pudo ser actualizado.");
            content.setImage("./resources/fail.png");
            this.clearStates();
            return "resultOperation";
        }
        int rol = empleado.getRol();
        if (rol == 2) {
            Operario operario = new Operario();
            operario.setId(identificacion.trim());
            operario.setIdJefe(identificacionJefe.trim());
            daoEmpleado.updateOperario(operario);
        }
        if (rol == 3) {
            Auxiliar auxiliar = new Auxiliar();
            auxiliar.setId(identificacion.trim());
            auxiliar.setIdJefe(identificacionJefe.trim());
            auxiliar.setTrabajaEn(lugarTrabajo);
            daoEmpleado.updateAuxiliar(auxiliar);
        }
        if (rol == 4) {
            Conductor conductor = new Conductor();
            conductor.setId(identificacion.trim());
            conductor.setLicencia(licencia.trim());
            conductor.setConduceBus(conduceBus);
            daoEmpleado.updateConductor(conductor);
        }
        daoEmpleado = null;
        content.setResultOperation("El Empleado fue actualizado con éxito.");
        content.setImage("./resources/ok.png");
        this.clearStates();
        return "resultOperation";
    }

    public String eraseUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        BeanContent content = (BeanContent) context.getApplication().evaluateExpressionGet(context, "#{beanContent}", BeanContent.class);
        int result;

        DaoEmpleado daoEmpelado = new DaoEmpleado();
        result = daoEmpelado.eraseEmpleado(this.identificacion);
        daoEmpelado = null;

        if (result == 0) {
            content.setResultOperation("El Empleado no se pudo eliminar.");
            content.setImage("./resources/fail.png");
            this.clearStates();
            return "resultOperation";
        } else {
            content.setResultOperation("El Empleado se eliminó correctamente.");
            content.setImage("./resources/ok.png");
            this.clearStates();
            return "resultOperation";
        }
    }

    private Empleado createEmpleado() {
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
        if (salario.trim().equals("")) {
            empleado.setSalario(-1);
        } else {
            empleado.setSalario(Integer.parseInt(salario.trim()));
        }
        int rol = this.findRol(this.cargo);
        empleado.setRol(rol);
        empleado.setLogin(login.trim());
        empleado.setPassword(password.trim());
        empleado.setEstado(estado);

        return empleado;
    }
}
