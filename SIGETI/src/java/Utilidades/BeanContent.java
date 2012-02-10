/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import Empleados.Controlador.EmployeeHolder;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;




/**
 *
 * @author leonardo
 */
@ManagedBean
@SessionScoped
public class BeanContent implements Serializable{
    
   
    private List<List<String>> menuOptions = null;
    private String resultOperation = null;
    
   

    public void beanContentInit() {
        
   
       FacesContext context = FacesContext.getCurrentInstance();
       EmployeeHolder empleadoHolder = (EmployeeHolder) context.getApplication().evaluateExpressionGet(context, "#{employeeHolder}", EmployeeHolder.class);
       if(empleadoHolder.getCurrentEmpleado() == null)
           return;
       int rol = empleadoHolder.getCurrentEmpleado().getRol();
       menuOptions = new ArrayList<List<String>>();
       
       if(rol == 0) //Administrador
       {
           List l1 = new ArrayList();
           l1.add("Gestionar Empleados");
           l1.add("1");
           List l2 = new ArrayList();
           l2.add("Gestionar Buses");
           l2.add("2");
            List l3 = new ArrayList();
           l3.add("Gestionar Tarjetas");
           l3.add("3");
           
           menuOptions.add(l1);
           menuOptions.add(l2);
           menuOptions.add(l3);
       }else if(rol == 1) //director
       {
           List l1 = new ArrayList();
           l1.add("Gestionar Empleados");
           l1.add("1");
           
           menuOptions.add(l1);
       }else if(rol == 2) //Operario
       {
           List l1 = new ArrayList();
           l1.add("Gestionar Empleados");
           l1.add("1");
            menuOptions.add(l1);
           
       }else if(rol == 3) //Auxiliar
       {
           
       }else if(rol == 4) //conductor
       {
           
       }
       
       
       
    }
      
    public List<List<String>> getMenuOptions()
    {
        if(menuOptions == null)
        {
            beanContentInit();
        }
        return menuOptions;
    }
    
    public String findLink(String l)
    {
        String link = null;
        if(l.equals("1"))
            link = "managerEmployees";
        else if(l.equals("2"))
            link = "managerBus";
        else if(l.equals("3"))
            link = "managerCards";
        
        return link;
    }

    public String getResultOperation() {
        return resultOperation;
    }

    public void setResultOperation(String resultOperation) {
        this.resultOperation = resultOperation;
    }
}
