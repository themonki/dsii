/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Empleados.Dao;

import Entidades.Empleado;
import Utilidades.FachadaBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author leonardo
 */
public class DaoEmpleado {
    
    private FachadaBD fachada;
    
    public DaoEmpleado()
    {
        fachada = new FachadaBD();
    }
    
    /**
     * Método: findEmpleadoLogin - consulta si existe un empleado
     * con el login dado
     * @param login - String con el login del empleado
     * @return Empleado - Objeto empleado que representa el empleado encontrado
     *  si no existe en elmpleado todos sus atributos son null
     * @author Leonardo Ríos
     */
    public Empleado findEmpleadoLogin(String login)
    {
        String sqlConsulta = "SELECT * FROM empleado WHERE login = '" + login + "'";
        
        Empleado empleado = new Empleado();
        
        try
        {
            Connection conn= fachada.conectar();
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sqlConsulta);
         
         while(table.next())
         {
             empleado.setApellido(table.getString("apellido"));
             empleado.setDireccion(table.getString("direccion"));
             empleado.setEstado(table.getBoolean("estado"));
             empleado.setFechaIngreso(table.getDate("fecha_ingreso"));
             empleado.setFechaNacimiento(table.getDate("fecha_nacimiento"));
             empleado.setId(table.getString("id"));
             empleado.setLogin(login);
             empleado.setNombre(table.getString("nombre"));
             empleado.setPassword(table.getString("password"));
             empleado.setRol(table.getInt("rol"));
             empleado.setSalario(table.getInt("salario"));
             empleado.setTelefono(table.getString("telefono"));
             empleado.setTipoId(table.getString("tipo_id"));
         }
         fachada.cerrarConexion(conn);
        }catch(SQLException se)
        {
            se.printStackTrace();
        }catch(Exception e)
        {
            e.printStackTrace();
        }       
        return empleado;
    }
    
        /**
     * Método: consultarEmpleadoId - consulta si existe un empleado
     * con el id dado
     * @param login - String con el id del empleado
     * @return Empleado - Objeto empleado que representa el empleado encontrado
     *  si no existe en elmpleado todos sus atributos son null
     * @author Leonardo Ríos
     */
    public Empleado findEmpleadoId(String id)
    {
        return null;
    }
    
    /**
     * Método: authenticateEmpleado - consulta si existe un empleado
     * con el login y el password dado
     * @param login - String con el login del empleado
     * @param  password - String con el password del empleado
     * @return Empleado - Objeto empleado que representa el empleado encontrado
     *  si no existe en empleado o la atenticacion falla todos sus atributos son null
     * @author Leonardo Ríos
     */
    public Empleado authenticateEmpleado(String login, String password)
    {
        String sqlConsulta = "SELECT * FROM empleado WHERE login = '" + login + "'"
                + " AND password = md5('"+password+"')";
        
        Empleado empleado = new Empleado();
        
        try
        {
            Connection conn= fachada.conectar();
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sqlConsulta);
         
         while(table.next())
         {
             empleado.setApellido(table.getString("apellido"));
             empleado.setDireccion(table.getString("direccion"));
             empleado.setEstado(table.getBoolean("estado"));
             empleado.setFechaIngreso(table.getDate("fecha_ingreso"));
             empleado.setFechaNacimiento(table.getDate("fecha_nacimiento"));
             empleado.setId(table.getString("id"));
             empleado.setLogin(login);
             empleado.setNombre(table.getString("nombre"));
             empleado.setPassword(table.getString("password"));
             empleado.setRol(table.getInt("rol"));
             empleado.setSalario(table.getInt("salario"));
             empleado.setTelefono(table.getString("telefono"));
             empleado.setTipoId(table.getString("tipo_id"));
         }
         fachada.cerrarConexion(conn);
        }catch(SQLException se)
        {
            se.printStackTrace();
        }catch(Exception e)
        {
            e.printStackTrace();
        }       
        return empleado;
    }
}