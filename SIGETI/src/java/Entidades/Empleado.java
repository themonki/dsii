/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;

/**
 *
 * @author leonardo
 */
public class Empleado implements Serializable{

    protected String id;
    private String tipoId;
    private String fechaIngreso;
    private Integer salario;
    private String telefono;
    private String direccion;
    private String nombre;
    private String nombre2;
    private String apellido;
    private String apellido2;
    private String email;
    private String fechaNacimiento;
    private Integer rol;
    private String login;
    private String password;
    private boolean estado;

    public Empleado() {
    }

    public Empleado(String id) {
        this.id = id;
    }

    public Empleado(String id, String login, String password, String nombre, String apellido, boolean estado) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.estado = estado;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getApellido2() {
        return apellido2;
    }

    public String getEmail() {
        return email;
    }

    public boolean isEstado() {
        return estado;
    }

    public String getNombre2() {
        return nombre2;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipoId() {
        return tipoId;
    }

    public void setTipoId(String tipoId) {
        this.tipoId = tipoId;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Integer getSalario() {
        return salario;
    }

    public void setSalario(Integer salario) {
        this.salario = salario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getRol() {
        return rol;
    }

    public void setRol(Integer rol) {
        this.rol = rol;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Empleado[ id=" + id + " tipoId=" + tipoId 
                + " fechaIngreso=" + fechaIngreso 
                + " salario=" + salario
                + " telefono=" + telefono
                + " direccion=" + direccion
                + " nombre=" + nombre
                + " apellido=" + apellido
                + " fechaNacimiento=" + fechaNacimiento
                + " rol=" + rol
                + " login=" + login
                + " password=" + password
                + " estado=" + estado +" ]";
    }
}
