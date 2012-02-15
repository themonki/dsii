        /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import Controlador.EmployeeHolder;
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
public class BeanContent implements Serializable {

    private List<List<String>> menuOptions = null;
    private List<List<String>> menuClaimsOptions = null;
    private String resultOperation = null;
    private boolean pair = false;
    private String image;

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public boolean getPair() {
        return pair;
    }

    public void setPair(boolean pair) {
        this.pair = pair;

    }

    public void beanContentInit() {

        FacesContext context = FacesContext.getCurrentInstance();
        EmployeeHolder empleadoHolder = (EmployeeHolder) context.getApplication().evaluateExpressionGet(context, "#{employeeHolder}", EmployeeHolder.class);
        if (empleadoHolder.getCurrentEmpleado() == null) {
            return;
        }
        int rol = empleadoHolder.getCurrentEmpleado().getRol();
        menuOptions = new ArrayList<List<String>>();
        menuClaimsOptions = new ArrayList<List<String>>();

        if (rol == 0) //Administrador
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
            
            List l4 = new ArrayList();
            l4.add("Gestionar Reclamos");
            l4.add("4");
            List l5 = new ArrayList();
            l5.add("Gestionar Estaciones");
            l5.add("5");
            
            List l6 = new ArrayList();
            l6.add("Gestionar Rutas");
            l6.add("6");

            System.err.println("l4: " + l4.get(0) + "l4: " + l4.get(1));
            
           
            List c3 = new ArrayList();
            c3.add("Crear Medida");
            c3.add("3");
            List c4 = new ArrayList();
            c4.add("Consultar Reclamo");
            c4.add("4");
            List c5 = new ArrayList();
            c5.add("Estado Mi Reclamo");
            c5.add("5");

           

            
            menuClaimsOptions.add(c3);
            menuClaimsOptions.add(c4);
            menuClaimsOptions.add(c5);
           
                    
            menuOptions.add(l1);
            menuOptions.add(l2);
            menuOptions.add(l3);
            menuOptions.add(l4);
            menuOptions.add(l5);
            menuOptions.add(l6);

        } else if (rol == 1) //director
        {
            List l1 = new ArrayList();
            l1.add("Gestionar Empleados");
            l1.add("1");
            List l2 = new ArrayList();
            l2.add("Gestionar Buses");
            l2.add("2");
            List l5 = new ArrayList();
            l5.add("Gestinar Estaciones");
            l5.add("5");
            List l6 = new ArrayList();
            l6.add("Gestionar Rutas");
            l6.add("6");


            menuOptions.add(l1);
            menuOptions.add(l2);
            menuOptions.add(l5);
            menuOptions.add(l6);

        } else if (rol == 2) //Operario
        {
            List l1 = new ArrayList();
            l1.add("Gestionar Empleados");
            l1.add("1");
            List l4 = new ArrayList();
            l4.add("Gestionar Reclamos");
            l4.add("4");

            

            List c2 = new ArrayList();
            c2.add("Editar Reclamo");
            c2.add("2");
            List c3 = new ArrayList();
            c3.add("Crear Medida");
            c3.add("3");
            List c4 = new ArrayList();
            c4.add("Consultar Reclamo");
            c4.add("4");
            

            menuOptions.add(l1);
            menuOptions.add(l4);

            menuClaimsOptions.add(c2);
            menuClaimsOptions.add(c3);
            menuClaimsOptions.add(c4);
           



        } else if (rol == 3) //Auxiliar
        {
            
            
            List l3 = new ArrayList();
            l3.add("Gestionar Tarjetas");
            l3.add("3");
            List l4 = new ArrayList();
            l4.add("Gestionar Reclamos");
            l4.add("4");

            List c1 = new ArrayList();
            c1.add("Crear Reclamo");
            c1.add("1");

            menuOptions.add(l3);
            menuOptions.add(l4);
            menuClaimsOptions.add(c1);


        } else if (rol == 4) //conductor
        {
        }

    }

    public List<List<String>> getMenuOptions() {
        if (menuOptions == null) {
            beanContentInit();
        }
        return menuOptions;
    }

    public List<List<String>> getMenuClaimsOptions() {

        if (menuClaimsOptions == null) {

            beanContentInit();
        }

        return menuClaimsOptions;

    }

    public String findLinkClaims(String l) {
        String link = null;
        if (l.equals("1")) {
            link = "newClaim";

        } else if (l.equals("2")) {
            link = "findClaim";

        } else if (l.equals("3")) {
            link = "newMeasure";

        } else if (l.equals("4")) {
            link = "findClaim";
            
        } else if (l.equals("5")) {
            link = "findClaim";

        }

        return link;
    }

    public String findLink(String l) {
        String link = null;
        if (l.equals("1")) {
            link = "managerEmployees";
        } else if (l.equals("2")) {
            link = "managerBus";
        } else if (l.equals("3")) {
            link = "managerCards";
        } else if (l.equals("4")) {
            link = "managerClaims";
        } else if (l.equals("5")) {
            link = "managerEstaciones";
        }else if (l.equals("6")) {
            link = "managerRoutes";
        }
        return link;
    }

    public String getResultOperation() {
        return resultOperation;
    }

    public void setResultOperation(String resultOperation) {
        this.resultOperation = resultOperation;
    }
}
