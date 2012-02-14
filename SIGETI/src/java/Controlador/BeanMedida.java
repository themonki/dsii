/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import javax.annotation.ManagedBean;

/**
 *
 * @author yerminson
 */
@ManagedBean
public class BeanMedida {
    
        private Integer id;
    private String accion;

    public BeanMedida() {
    }

  

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
}
