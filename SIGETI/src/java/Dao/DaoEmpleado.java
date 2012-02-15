/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

/**
 * Clase encargada de realizar transacciones con la base de de datos repecto 
 * a lo que se refiere con empleados.
 * @author Leonardo Ríos
 */
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
import java.util.ArrayList;
import java.util.List;

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
     * Consulta si existe un empleado con el login dado.
     * @param login - String con el login del empleado.
     * @return Objeto empleado que representa el empleado encontrado
     *  si no existe en elmpleado todos sus atributos son null.
     */
    public Empleado findEmpleadoLogin(String login, boolean conInactivos) {
        String sqlConsulta = "SELECT * FROM empleado WHERE login = '" + login + "'";
        if(!conInactivos)
            sqlConsulta += " AND estado = true";
        
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
                empleado.setEmail(table.getString("email"));
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
     * Consulta si existe un empleado con el id dado.
     * @param login - String con el id del empleado
     * @return Objeto empleado que representa el empleado encontrado
     *  si no existe en el empleado todos sus atributos son null.
     */
    public Empleado findEmpleadoId(String id, boolean conInactivos) {
        String sqlConsulta = "SELECT * from empleado WHERE id = '" + id + "'";
        if(!conInactivos)
            sqlConsulta += " AND estado = true";
        
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
                empleado.setEmail(table.getString("email"));
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
     * Consulta si existe un empleado con las condiciones dadas.
     * @param condition - String con la condición que debe de tener la consuta.
     * @return Lsita de objetos empleado que representa los empleados encontrados
     *  si no existe nngún empleado la lista sera vacía.
     */
    public List<Empleado> findEmpleadoCondition(String condition) {
        String sqlConsulta = "SELECT * from empleado WHERE " + condition;
        //System.out.println(sqlConsulta);
        List<Empleado> empleados = new ArrayList<Empleado>();

        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sqlConsulta);

            while (table.next()) {
                Empleado empleado = new Empleado();
                empleado.setApellido(table.getString("apellido"));
                empleado.setDireccion(table.getString("direccion"));
                empleado.setEstado(table.getBoolean("estado"));
                empleado.setFechaIngreso(table.getString("fecha_ingreso"));
                empleado.setFechaNacimiento(table.getString("fecha_nacimiento"));
                empleado.setId(table.getString("id"));
                empleado.setLogin(table.getString("login"));
                empleado.setNombre(table.getString("nombre"));
                empleado.setPassword(table.getString("password"));
                empleado.setRol(table.getInt("rol"));
                empleado.setSalario(table.getInt("salario"));
                empleado.setTelefono(table.getString("telefono"));
                empleado.setTipoId(table.getString("tipo_id"));
                empleado.setNombre2(table.getString("nombre2"));
                empleado.setApellido2(table.getString("apellido2"));
                empleado.setEmail(table.getString("email"));

                empleados.add(empleado);
            }
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empleados;
    }

    /**
     * Consulta si existe un empleado con el login y el password dado.
     * @param login - String con el login del empleado
     * @param  password - String con el password del empleado
     * @return Objeto empleado que representa el empleado encontrado
     *  si no existe en empleado o la atenticacion falla todos sus atributos son null
     */
    public Empleado authenticateEmpleado(String login, String password) {
        String sqlConsulta = "SELECT * FROM empleado WHERE login = '" + login + "'"
                + " AND password = md5('" + password + "') AND estado = true";

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

    public boolean existEmpleadoLogin(String login) {
        String sqlConsulta = "SELECT * FROM empleado WHERE login = '" + login + "'";
        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sqlConsulta);

            if (table.next()) {
                return true;
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean existEmpleadoId(String id) {
        String sqlConsulta = "SELECT * FROM empleado WHERE id = '" + id + "'";
        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sqlConsulta);

            if (table.next()) {
                return true;
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean verifyPassword(String id, String password) {
        String sqlConsulta = "SELECT * FROM empleado WHERE id = '" + id + "' AND password = md5('" + password + "')";
        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sqlConsulta);

            if (table.next()) {
                return true;
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Guarda un empelado en la base de datos.
     * @param empleado - Empleado que se desea guardar.
     * @return Cantidad de inserciones en la base de datos, debe retornar 1
     */
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
            result = sentence.executeUpdate(sql_insert);
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(sql_insert);
        return result;
    }

    /**
     * Guarda un director en la base de datos.
     * @param director - Director que se desea guardar.
     * @return Cantidad de insercciones en la base de datos, debe retornar 1.
     */
    public int saveDirector(Director director) {

        String sql_insert = "INSERT INTO director VALUES('" + director.getId() + "')";
        int result = 0;
        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            result = sentence.executeUpdate(sql_insert);
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(sql_insert);
        return result;
    }

    /**
     * Guarda un operario en la base de datos.
     * @param operario - Operario que se desea guardar
     * @return Cantidad de inserciones en la base de datos, debe retornar 1
     */
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
            result = sentence.executeUpdate(sql_insert);
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(sql_insert);
        return result;
    }

    /**
     * Guarda un auxiliar en la base de datos.
     * @param auxiliar - Auxiliar que se desea guardar.
     * @return Cantidad de inserciones en la base de datos, debe retornar 1
     */
    public int saveAuxiliar(Auxiliar auxiliar) {

        String sql_insert = "INSERT INTO auxiliar (id";

        if (!auxiliar.getIdJefe().equals("")) {
            sql_insert += ",id_jefe";
        }
        if (auxiliar.getTrabajaEn() != -1) {
            sql_insert += ",trabaja_en";
        }
        sql_insert += ") VALUES ('" + auxiliar.getId() + "'";
        if (!auxiliar.getIdJefe().equals("")) {
            sql_insert += ",'" + auxiliar.getIdJefe() + "'";
        }
        if (auxiliar.getTrabajaEn() != -1) {
            sql_insert += "," + auxiliar.getTrabajaEn();
        }
        sql_insert += ")";

        int result = 0;
        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            result = sentence.executeUpdate(sql_insert);
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(sql_insert);
        return result;
    }

    /**
     * Guarda un conductor en la base de datos.
     * @param conductor - Conductor que se desea guardar.
     * @return Cantidad de inserciones en la base de datos, debe retornar 1.
     */
    public int saveConductor(Conductor conductor) {

        String sql_insert = "INSERT INTO conductor (id";

        if (!conductor.getLicencia().equals("")) {
            sql_insert += ",licencia";
        } 
        if(!conductor.getConduceBus().equals("")){
            sql_insert += ",conduce_bus";
        }
        sql_insert += " VALUES ('" + conductor.getId() + "'";    
        if (!conductor.getLicencia().equals("")) {
            sql_insert += ",'" + conductor.getLicencia() + "'";
        } 
        if(!conductor.getConduceBus().equals("")){
            sql_insert += "," + conductor.getConduceBus() + "'";
        }
        
        sql_insert += ")";
        
        int result = 0;
        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            result = sentence.executeUpdate(sql_insert);
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(sql_insert);
        return result;
    }

    /**
     * Busca un director en la base de datos dado su id.
     * @param id - String que representa el id del director a buscar
     * @return Objeto director que representa el director encontrado, 
     * si no existe director con ese id, todos los atributos del director retornado son null
     */
    public Director findDirectorId(String id, boolean conInactivos) {

        String sqlConsulta = "SELECT * FROM director NATURAL JOIN empleado WHERE director.id = '" + id + "'";
        if(!conInactivos)
            sqlConsulta += " AND estado = true";
        
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
                director.setId(table.getString("id"));
                director.setLogin(table.getString("login"));
                director.setNombre(table.getString("nombre"));
                director.setPassword(table.getString("password"));
                director.setRol(table.getInt("rol"));
                director.setSalario(table.getInt("salario"));
                director.setTelefono(table.getString("telefono"));
                director.setTipoId(table.getString("tipo_id"));
                director.setNombre2(table.getString("nombre2"));
                director.setApellido2(table.getString("apellido2"));
                director.setEmail(table.getString("email"));
            }
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return director;
    }

    /**
     * Busca un operario en la base de datos dado su id.
     * @param id - String que representa el id del operario a buscar
     * @return Objeto operario que representa el operario encontrado, 
     * si no existe operario con ese id, todos los atributos del operario retornado son null
     */
    public Operario findOpearioId(String id, boolean conInactivos) {
        String sqlConsulta = "SELECT * FROM operario NATURAL JOIN empleado WHERE operario.id = '" + id + "'";
        if(!conInactivos)
            sqlConsulta += " AND estado = true";
        
        Operario operario = new Operario();

        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sqlConsulta);

            while (table.next()) {
                operario.setApellido(table.getString("apellido"));
                operario.setDireccion(table.getString("direccion"));
                operario.setEstado(table.getBoolean("estado"));
                operario.setFechaIngreso(table.getString("fecha_ingreso"));
                operario.setFechaNacimiento(table.getString("fecha_nacimiento"));
                operario.setId(table.getString("id"));
                operario.setLogin(table.getString("login"));
                operario.setNombre(table.getString("nombre"));
                operario.setPassword(table.getString("password"));
                operario.setRol(table.getInt("rol"));
                operario.setSalario(table.getInt("salario"));
                operario.setTelefono(table.getString("telefono"));
                operario.setTipoId(table.getString("tipo_id"));
                operario.setNombre2(table.getString("nombre2"));
                operario.setApellido2(table.getString("apellido2"));
                operario.setEmail(table.getString("email"));
                operario.setIdJefe(table.getString("id_jefe"));
            }
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return operario;
    }

    public Auxiliar findAuxiliarId(String id,boolean conInactivos) {
        String sqlConsulta = "SELECT * FROM auxiliar NATURAL JOIN empleado WHERE auxiliar.id = '" + id + "'";
        if(!conInactivos)
            sqlConsulta += " AND estado = true";
        
        Auxiliar auxiliar = new Auxiliar();

        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sqlConsulta);

            while (table.next()) {
                auxiliar.setApellido(table.getString("apellido"));
                auxiliar.setDireccion(table.getString("direccion"));
                auxiliar.setEstado(table.getBoolean("estado"));
                auxiliar.setFechaIngreso(table.getString("fecha_ingreso"));
                auxiliar.setFechaNacimiento(table.getString("fecha_nacimiento"));
                auxiliar.setId(table.getString("id"));
                auxiliar.setLogin(table.getString("login"));
                auxiliar.setNombre(table.getString("nombre"));
                auxiliar.setPassword(table.getString("password"));
                auxiliar.setRol(table.getInt("rol"));
                auxiliar.setSalario(table.getInt("salario"));
                auxiliar.setTelefono(table.getString("telefono"));
                auxiliar.setTipoId(table.getString("tipo_id"));
                auxiliar.setNombre2(table.getString("nombre2"));
                auxiliar.setApellido2(table.getString("apellido2"));
                auxiliar.setEmail(table.getString("email"));
                auxiliar.setIdJefe(table.getString("id_jefe"));
                auxiliar.setTrabajaEn(table.getInt("trabaja_en"));
            }
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return auxiliar;
    }

    public Conductor findConductorId(String id, boolean conInactivos) {
        String sqlConsulta = "SELECT * FROM conductor NATURAL JOIN empleado WHERE conductor.id = '" + id + "'";
        if(!conInactivos)
            sqlConsulta += " AND estado = true";
        
        Conductor conductor = new Conductor();

        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sqlConsulta);

            while (table.next()) {
                conductor.setApellido(table.getString("apellido"));
                conductor.setDireccion(table.getString("direccion"));
                conductor.setEstado(table.getBoolean("estado"));
                conductor.setFechaIngreso(table.getString("fecha_ingreso"));
                conductor.setFechaNacimiento(table.getString("fecha_nacimiento"));
                conductor.setId(table.getString("id"));
                conductor.setLogin(table.getString("login"));
                conductor.setNombre(table.getString("nombre"));
                conductor.setPassword(table.getString("password"));
                conductor.setRol(table.getInt("rol"));
                conductor.setSalario(table.getInt("salario"));
                conductor.setTelefono(table.getString("telefono"));
                conductor.setTipoId(table.getString("tipo_id"));
                conductor.setNombre2(table.getString("nombre2"));
                conductor.setApellido2(table.getString("apellido2"));
                conductor.setEmail(table.getString("email"));
                conductor.setLicencia(table.getString("licencia"));
                conductor.setConduceBus(table.getString("conduce_bus"));
            }
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conductor;
    }

    /**
     * Busca todos los empleados en la base de datos.
     * @return Lsita de objetos empleados, 
     * si no existe alguno sera una lista vacia.
     */
    public List<Empleado> findAllEmpleado(boolean conInactivos) {
        String sqlConsulta = "SELECT * FROM empleado";
        if(!conInactivos)
            sqlConsulta += " WHERE estado = true";
        
        List<Empleado> empleados = new ArrayList<Empleado>();

        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sqlConsulta);

            while (table.next()) {
                Empleado empleado = new Empleado();
                empleado.setApellido(table.getString("apellido"));
                empleado.setDireccion(table.getString("direccion"));
                empleado.setEstado(table.getBoolean("estado"));
                empleado.setFechaIngreso(table.getString("fecha_ingreso"));
                empleado.setFechaNacimiento(table.getString("fecha_nacimiento"));
                empleado.setId(table.getString("id"));
                empleado.setLogin(table.getString("login"));
                empleado.setNombre(table.getString("nombre"));
                empleado.setPassword(table.getString("password"));
                empleado.setRol(table.getInt("rol"));
                empleado.setSalario(table.getInt("salario"));
                empleado.setTelefono(table.getString("telefono"));
                empleado.setTipoId(table.getString("tipo_id"));
                empleado.setNombre2(table.getString("nombre2"));
                empleado.setApellido2(table.getString("apellido2"));
                empleado.setEmail(table.getString("email"));

                empleados.add(empleado);
            }
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empleados;
    }

    /**
     * Encuentra todos los directores existentes en la base de datos
     * @return  Lista que contiene objetos Director, cad auno representa alguno encontrado
     * , si no existen directores el vector será vacio.
     */
    public List<Director> findAllDirector(boolean conInactivos) {
        String sqlConsulta = "SELECT * FROM director NATURAL JOIN empleado";
        if(!conInactivos)
            sqlConsulta += " WHERE estado = true";
        
        List<Director> directores = new ArrayList<Director>();

        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sqlConsulta);

            while (table.next()) {
                Director director = new Director();
                director.setApellido(table.getString("apellido"));
                director.setDireccion(table.getString("direccion"));
                director.setEstado(table.getBoolean("estado"));
                director.setFechaIngreso(table.getString("fecha_ingreso"));
                director.setFechaNacimiento(table.getString("fecha_nacimiento"));
                director.setId(table.getString("id"));
                director.setLogin(table.getString("login"));
                director.setNombre(table.getString("nombre"));
                director.setPassword(table.getString("password"));
                director.setRol(table.getInt("rol"));
                director.setSalario(table.getInt("salario"));
                director.setTelefono(table.getString("telefono"));
                director.setTipoId(table.getString("tipo_id"));
                director.setNombre2(table.getString("nombre2"));
                director.setApellido2(table.getString("apellido2"));
                director.setEmail(table.getString("email"));

                directores.add(director);
            }
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return directores;
    }

    /**
     * Encuentra todos los opearios existentes en la base de datos.
     * @return  Lista que contiene objetos Operario, cad auno representa alguno encontrado.
     * , si no existen operarios el vector será vacio.
     */
    public List<Operario> findAllOperario(boolean conInactivos) {
        String sqlConsulta = "SELECT * FROM operario NATURAL JOIN empleado";
        if(!conInactivos)
            sqlConsulta += " WHERE estado = true";
        
        List<Operario> operarios = new ArrayList<Operario>();

        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sqlConsulta);

            while (table.next()) {
                Operario operario = new Operario();
                operario.setApellido(table.getString("apellido"));
                operario.setDireccion(table.getString("direccion"));
                operario.setEstado(table.getBoolean("estado"));
                operario.setFechaIngreso(table.getString("fecha_ingreso"));
                operario.setFechaNacimiento(table.getString("fecha_nacimiento"));
                operario.setId(table.getString("id"));
                operario.setLogin(table.getString("login"));
                operario.setNombre(table.getString("nombre"));
                operario.setPassword(table.getString("password"));
                operario.setRol(table.getInt("rol"));
                operario.setSalario(table.getInt("salario"));
                operario.setTelefono(table.getString("telefono"));
                operario.setTipoId(table.getString("tipo_id"));
                operario.setNombre2(table.getString("nombre2"));
                operario.setApellido2(table.getString("apellido2"));
                operario.setEmail(table.getString("email"));
                operario.setIdJefe(table.getString("id_jefe"));

                operarios.add(operario);
            }
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return operarios;
    }

    public List<Auxiliar> findAllAuxiliar(boolean conInactivos) {
        String sqlConsulta = "SELECT * FROM auxiliar NATURAL JOIN empleado";
        if(!conInactivos)
            sqlConsulta += " WHERE estado = true";

        List<Auxiliar> auxiliares = new ArrayList<Auxiliar>();

        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sqlConsulta);

            while (table.next()) {
                Auxiliar auxiliar = new Auxiliar();
                auxiliar.setApellido(table.getString("apellido"));
                auxiliar.setDireccion(table.getString("direccion"));
                auxiliar.setEstado(table.getBoolean("estado"));
                auxiliar.setFechaIngreso(table.getString("fecha_ingreso"));
                auxiliar.setFechaNacimiento(table.getString("fecha_nacimiento"));
                auxiliar.setId(table.getString("id"));
                auxiliar.setLogin(table.getString("login"));
                auxiliar.setNombre(table.getString("nombre"));
                auxiliar.setPassword(table.getString("password"));
                auxiliar.setRol(table.getInt("rol"));
                auxiliar.setSalario(table.getInt("salario"));
                auxiliar.setTelefono(table.getString("telefono"));
                auxiliar.setTipoId(table.getString("tipo_id"));
                auxiliar.setNombre2(table.getString("nombre2"));
                auxiliar.setApellido2(table.getString("apellido2"));
                auxiliar.setEmail(table.getString("email"));
                auxiliar.setIdJefe(table.getString("id_jefe"));
                auxiliar.setTrabajaEn(table.getInt("trabaja_en"));

                auxiliares.add(auxiliar);
            }
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return auxiliares;
    }

    public int updateEmpleado(Empleado empleado) {
        String sql_update = "UPDATE empleado SET tipo_id='";

        sql_update += empleado.getTipoId() + "', nombre='" + empleado.getNombre() + "', apellido='";
        sql_update += empleado.getApellido() + "', login='" + empleado.getLogin();
        sql_update += "',estado=" + empleado.getEstado() + ", rol=" + empleado.getRol();

        if(!empleado.getPassword().equals(""))
        {
            sql_update += ",password=md5('" + empleado.getPassword() +"')";
        }
        if (!empleado.getFechaIngreso().equals("")) {
            sql_update += ",fecha_ingreso='" + empleado.getFechaIngreso() + "'";
        }
        if (!empleado.getFechaNacimiento().equals("")) {
            sql_update += ",fecha_nacimiento='" + empleado.getFechaNacimiento() + "'";
        }
        if (!empleado.getTelefono().equals("")) {
            sql_update += ",telefono='" + empleado.getTelefono() + "'";
        }
        if (empleado.getSalario() != -1) {
            sql_update += ",salario=" + empleado.getSalario();
        }
        if (!empleado.getNombre2().equals("")) {
            sql_update += ",nombre2='" + empleado.getNombre2() + "'";
        }
        if (!empleado.getApellido2().equals("")) {
            sql_update += ",apellido2='" + empleado.getApellido2() + "'";
        }
        if (!empleado.getDireccion().equals("")) {
            sql_update += ",direccion='" + empleado.getDireccion() + "'";
        }
        if (!empleado.getEmail().equals("")) {
            sql_update += ",email='" + empleado.getEmail() + "'";
        }
        sql_update += " WHERE id='" + empleado.getId() + "'";

        int result = 0;
        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            result = sentence.executeUpdate(sql_update);
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(sql_update);
        return result;
    }

    public int updateOperario(Operario operario) {
        boolean algo = false;
        String sql_update = "UPDATE operario SET ";
        if (!operario.getIdJefe().equals("")) {
            algo = true;
            sql_update += "id_jefe='" + operario.getIdJefe() + "'";
        }

        sql_update += " WHERE id='" + operario.getId() + "'";

        int result = 0;
        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            if (algo) {
                result = sentence.executeUpdate(sql_update);
            } else {
                result = 1;
            }
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(sql_update);
        return result;
    }

    public int updateAuxiliar(Auxiliar auxiliar) {
        boolean primero = true;
        boolean algo = false;
        String sql_update = "UPDATE auxiliar SET ";

        if (!auxiliar.getIdJefe().equals("")) {
            algo = true;
            sql_update += "id_jefe='" + auxiliar.getIdJefe() + "'";
            primero = false;
        }
        if (auxiliar.getTrabajaEn() != -1) {
            algo = true;
            if (!primero) {
                sql_update += ",";
            }
            sql_update += "trabaja_en=" + auxiliar.getTrabajaEn();
        }
        sql_update += " WHERE id='" + auxiliar.getId() + "'";

        int result = 0;
        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            if (algo) {
                result = sentence.executeUpdate(sql_update);
            } else {
                result = 1;
            }

            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(sql_update);
        return result;
    }

    public int updateConductor(Conductor conductor) {
        boolean algo = false;
        boolean primero = true;
        
        String sql_update = "UPDATE conductor SET ";

        if (!conductor.getLicencia().equals("")) {
            algo = true;
            sql_update += "licencia='" + conductor.getLicencia() + "'";
            primero = false;
        }
        if(!conductor.getConduceBus().equals("")){
            algo = true;
            sql_update += "conduce_bus='" + conductor.getConduceBus() + "'";
        }
        sql_update += " WHERE id='" + conductor.getId() + "'";
        int result = 0;
        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            if (algo) {
                result = sentence.executeUpdate(sql_update);
            } else {
                result = 1;
            }

            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(sql_update);
        return result;
    }
    
    public int eraseEmpleado(String id)
    {
        String sql_update = "UPDATE empleado SET estado = false WHERE id='" + id + "'";

        int result = 0;
        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
 
             result = sentence.executeUpdate(sql_update);

            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(sql_update);
        return result;
    }
}
