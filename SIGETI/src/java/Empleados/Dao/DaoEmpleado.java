/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Empleados.Dao;

import Entidades.Auxiliar;
import Entidades.Conductor;
import Entidades.Director;
import Entidades.Empleado;
import Entidades.Operario;
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

    public DaoEmpleado() {
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
    public Empleado findEmpleadoLogin(String login) {
        String sqlConsulta = "SELECT * FROM empleado WHERE login = '" + login + "'";

        Empleado empleado = new Empleado();

        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sqlConsulta);

            while (table.next()) {
                empleado.setApellido(table.getString("apellido"));
                empleado.setDireccion(table.getString("direccion"));
                empleado.setEstado(table.getBoolean("estado"));
                empleado.setFechaIngreso(table.getString("fecha_ingreso"));
                empleado.setFechaNacimiento(table.getString("fecha_nacimiento"));
                empleado.setId(table.getString("id"));
                empleado.setLogin(login);
                empleado.setNombre(table.getString("nombre"));
                empleado.setPassword(table.getString("password"));
                empleado.setRol(table.getInt("rol"));
                empleado.setSalario(table.getInt("salario"));
                empleado.setTelefono(table.getString("telefono"));
                empleado.setTipoId(table.getString("tipo_id"));
                empleado.setNombre2(table.getString("nombre2"));
                empleado.setApellido2(table.getString("apellido2"));
            }
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
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
    public Empleado findEmpleadoId(String id) {
        String sqlConsulta = "SELECT * from empleado WHERE id = '" + id + "'";
        Empleado empleado = new Empleado();

        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sqlConsulta);

            while (table.next()) {
                empleado.setApellido(table.getString("apellido"));
                empleado.setDireccion(table.getString("direccion"));
                empleado.setEstado(table.getBoolean("estado"));
                empleado.setFechaIngreso(table.getString("fecha_ingreso"));
                empleado.setFechaNacimiento(table.getString("fecha_nacimiento"));
                empleado.setId(id);
                empleado.setLogin(table.getString("login"));
                empleado.setNombre(table.getString("nombre"));
                empleado.setPassword(table.getString("password"));
                empleado.setRol(table.getInt("rol"));
                empleado.setSalario(table.getInt("salario"));
                empleado.setTelefono(table.getString("telefono"));
                empleado.setTipoId(table.getString("tipo_id"));
                empleado.setNombre2(table.getString("nombre2"));
                empleado.setApellido2(table.getString("apellido2"));
            }
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empleado;
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
    public Empleado authenticateEmpleado(String login, String password) {
        String sqlConsulta = "SELECT * FROM empleado WHERE login = '" + login + "'"
                + " AND password = md5('" + password + "')";

        Empleado empleado = new Empleado();

        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sqlConsulta);

            while (table.next()) {
                empleado.setApellido(table.getString("apellido"));
                empleado.setDireccion(table.getString("direccion"));
                empleado.setEstado(table.getBoolean("estado"));
                empleado.setFechaIngreso(table.getString("fecha_ingreso"));
                empleado.setFechaNacimiento(table.getString("fecha_nacimiento"));
                empleado.setId(table.getString("id"));
                empleado.setLogin(login);
                empleado.setNombre(table.getString("nombre"));
                empleado.setPassword(table.getString("password"));
                empleado.setRol(table.getInt("rol"));
                empleado.setSalario(table.getInt("salario"));
                empleado.setTelefono(table.getString("telefono"));
                empleado.setTipoId(table.getString("tipo_id"));
                empleado.setNombre2(table.getString("nombre2"));
                empleado.setApellido2(table.getString("apellido2"));
            }
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empleado;
    }

    public int saveEmpleado(Empleado empleado) {
        String sql_insert = "INSERT INTO empleado (id,tipo_id,"
                + "nombre,apellido,login,password,estado,rol";

        if (!empleado.getFechaIngreso().equals("")) {
            sql_insert += ",fecha_ingreso";
        }
        if (!empleado.getFechaNacimiento().equals("")) {
            sql_insert += ",fecha_nacimiento";
        }
        if (!empleado.getTelefono().equals("")) {
            sql_insert += ",telefono";
        }
        if (empleado.getSalario() != -1) {
            sql_insert += ",salario";
        }
        if (!empleado.getNombre2().equals("")) {
            sql_insert += ",nombre2";
        }
        if (!empleado.getApellido2().equals("")) {
            sql_insert += ",apellido2";
        }
        if (!empleado.getDireccion().equals("")) {
            sql_insert += ",direccion";
        }
        if (!empleado.getEmail().equals("")) {
            sql_insert += ",email";
        }
        sql_insert += ") VALUES ('" + empleado.getId()
                + "','" + empleado.getTipoId()
                + "','" + empleado.getNombre()
                + "','" + empleado.getApellido()
                + "','" + empleado.getLogin()
                + "',MD5('" + empleado.getPassword()
                + "')," + empleado.getEstado()
                + "," + empleado.getRol();
        if (!empleado.getFechaIngreso().equals("")) {
            sql_insert += ",'" + empleado.getFechaIngreso() + "'";
        }
        if (!empleado.getFechaNacimiento().equals("")) {
            sql_insert += ",'" + empleado.getFechaNacimiento() + "'";
        }
        if (!empleado.getTelefono().equals("")) {
            sql_insert += ",'" + empleado.getTelefono() + "'";
        }
        if (empleado.getSalario() != -1) {
            sql_insert += "," + empleado.getSalario();
        }
        if (!empleado.getNombre2().equals("")) {
            sql_insert += ",'" + empleado.getNombre2() + "'";
        }
        if (!empleado.getApellido2().equals("")) {
            sql_insert += ",'" + empleado.getApellido2() + "'";
        }
        if (!empleado.getDireccion().equals("")) {
            sql_insert += ",'" + empleado.getDireccion() + "'";
        }
        if (!empleado.getEmail().equals("")) {
            sql_insert += ",'" + empleado.getEmail() + "'";
        }
        sql_insert += ")";

        int result = 0;
        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            //result = sentence.executeUpdate(sql_insert);
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(sql_insert);
        return 1; //result
    }

    public int saveDirector(Director director) {

        String sql_insert = "INSERT INTO director VALUES('" + director.getId() + "')";
        int result = 0;
        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            //result = sentence.executeUpdate(sql_insert);
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(sql_insert);
        return 1;//result;
    }

    public int saveOperario(Operario operario) {
        String sql_insert = "INSERT INTO operario (id";
        if (!operario.getIdJefe().equals("")) {
            sql_insert += ",id_jefe) VALUES('" + operario.getId() + "','" + operario.getIdJefe() + "')";
        } else {
            sql_insert += ") VALUES ('" + operario.getId() + "')";
        }
        int result = 0;
        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            //result = sentence.executeUpdate(sql_insert);
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(sql_insert);
        return 1;//result;;
    }

    public int saveAuxiliar(Auxiliar auxiliar) {
        
        String sql_insert = "INSERT INTO auxiliar (id";
        
        if(!auxiliar.getIdJefe().equals("")){
            sql_insert += ",id_jefe";
        }
        if(auxiliar.getTrabajaEn() != -1){
            sql_insert += ",trabaja_en";
        }
        sql_insert += ") VALUES ('" + auxiliar.getId() + "'";
        if(!auxiliar.getIdJefe().equals("")){
            sql_insert += ",'" + auxiliar.getIdJefe() + "'";
        }
        if(auxiliar.getTrabajaEn() != -1){
            sql_insert += "," + auxiliar.getTrabajaEn();
        }
        sql_insert += ")";
        
        int result = 0;
        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            //result = sentence.executeUpdate(sql_insert);
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(sql_insert);
        return 1;//result;;
    }

    public int saveConductor(Conductor conductor) {
        
        String sql_insert = "INSERT INTO conductor (id";
        
        if(!conductor.getLicencia().equals("")){
            sql_insert += ",licencia) VALUES ('" + 
                    conductor.getId() + "','" + conductor.getLicencia() + "')";
        }else{
            sql_insert += ") VALUES ('" + conductor.getId() + "')";
        }
        int result = 0;
        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            //result = sentence.executeUpdate(sql_insert);
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(sql_insert);
        return 1;//result;;
    }
    
    public Director findDirectorId(String id){
        
        String sqlConsulta = "SELECT * FROM director NATURAL JOIN empleado WHERE director.id = '" + id + "'";
        
        Director director = new Director();

        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sqlConsulta);

            while (table.next()) {
                director.setApellido(table.getString("apellido"));
                director.setDireccion(table.getString("direccion"));
                director.setEstado(table.getBoolean("estado"));
                director.setFechaIngreso(table.getString("fecha_ingreso"));
                director.setFechaNacimiento(table.getString("fecha_nacimiento"));
                director.setId(table.getString(id));
                director.setLogin(table.getString("login"));
                director.setNombre(table.getString("nombre"));
                director.setPassword(table.getString("password"));
                director.setRol(table.getInt("rol"));
                director.setSalario(table.getInt("salario"));
                director.setTelefono(table.getString("telefono"));
                director.setTipoId(table.getString("tipo_id"));
                director.setNombre2(table.getString("nombre2"));
                director.setApellido2(table.getString("apellido2"));
            }
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return director;
    }
}
