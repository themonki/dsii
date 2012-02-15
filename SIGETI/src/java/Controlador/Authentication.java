/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Dao.DaoEmpleado;
import Entidades.Empleado;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpSession;

/**
 *
 * @author leonardo
 */
@ManagedBean
@SessionScoped
public class Authentication implements Serializable {

    private String loginU;
    private String passwordU;
    private boolean isLoggedIn;
    private int rol;

    public String login() {
        
        
        DaoEmpleado dao = new DaoEmpleado();

        Empleado empleado = new Empleado();
        empleado = dao.authenticateEmpleado(loginU, passwordU);

        FacesContext context = FacesContext.getCurrentInstance();

        if (empleado.getLogin() != null) {
            isLoggedIn = true;

            EmployeeHolder empleadoHolder = (EmployeeHolder) context.getApplication().evaluateExpressionGet(context, "#{employeeHolder}", EmployeeHolder.class);
            empleadoHolder.setCurrentEmpleado(empleado);

            rol = empleado.getRol();
            String page = "home";
            //System.out.println("page " + page);
            return page;
        } else {
            context.addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Login failed", null));
            return null;
        }
    }

    public void logout() {
        isLoggedIn = false;       
        FacesContext context = FacesContext.getCurrentInstance();
        EmployeeHolder empleadoHolder = (EmployeeHolder) context.getApplication().evaluateExpressionGet(context, "#{employeeHolder}", EmployeeHolder.class);
        empleadoHolder.setCurrentEmpleado(null);
        ExternalContext externalContext = context.getExternalContext();
        Object session = externalContext.getSession(false);
        HttpSession httpSession = (HttpSession) session;
        httpSession.invalidate();
        doRedirect("index.jsf");
    }

    public boolean getIsLoggedIn() {
        return isLoggedIn;
    }

    public String getLoginU() {
        return loginU;
    }

    public String getPasswordU() {
        return passwordU;
    }

    public void setIsLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public void setLoginU(String login) {
        this.loginU = login;
    }

    public void setPasswordU(String password) {
        this.passwordU = password;
    }

    public void verifyEmpleadoLogin(ComponentSystemEvent event) {
        if (!isLoggedIn) {
            doRedirect("index.jsf");
        }
    }

    public void verifyAdministradorLogin(ComponentSystemEvent event) {
        if (rol != 0) {
            doRedirect("index.jsf");
        }
    }

    public void verifyDirectorLogin(ComponentSystemEvent event) {
        if (rol != 1) {
            doRedirect("index.jsf");
        }
    }

    public void verifyOperarioLogin(ComponentSystemEvent event) {
        if (rol != 2) {
            doRedirect("index.jsf");
        }
    }

    public void verifyAuxiliarLogin(ComponentSystemEvent event) {
        if (rol != 3) {
            doRedirect("index.jsf");
        }
    }

    public void verifyConductorLogin(ComponentSystemEvent event) {
        if (rol != 4) {
            doRedirect("index.jsf");
        }
    }

    private void doRedirect(String url) {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().redirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
