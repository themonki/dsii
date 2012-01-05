/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;


/**
 *
 * @author leonardo
 */
public class Usuario {

    private String id;
    private String tipoId;
    private String nombre;
    private String apellido;
    private String direccion;
    private String email;
    private String fechaNacimiento;
    private String telefono;
    private String password;
    private boolean estado;
    private String adquiereTarjeta;

    public Usuario() {
    }

    public Usuario(String id) {
        this.id = id;
    }

    public Usuario(String id, String password, boolean estado) {
        this.id = id;
        this.password = password;
        this.estado = estado;
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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public String getAdquiereTarjeta() {
        return adquiereTarjeta;
    }

    public void setAdquiereTarjeta(String adquiereTarjeta) {
        this.adquiereTarjeta = adquiereTarjeta;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario[ id=" + id 
                + " tipoId=" + tipoId
                + " nombre=" + nombre
                + " apellido=" + apellido
                + " direccion=" + direccion
                + " email=" + email
                + " fechaNacimiento=" + fechaNacimiento
                + " telefono=" + telefono
                + " password=" + password
                + " estado=" + estado
                + " adquiereTarjeta=" + adquiereTarjeta + " ]";
    }
}
