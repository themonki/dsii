package Controlador;


import Entidades.Empleado;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author leonardo
 */
@ManagedBean
@SessionScoped
public class EmployeeHolder implements Serializable{
    private Empleado currentEmpleado;

    public void setCurrentEmpleado(Empleado currentEmpleado) {
        this.currentEmpleado = currentEmpleado;
    }

    public Empleado getCurrentEmpleado() {
        return currentEmpleado;
    }
}
