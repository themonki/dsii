package Dao;

import Entidades.Ruta;
import Utilidades.FachadaBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Edgar Andres Moncada
 */
public class DaoRuta {

    FachadaBD fachada;

    public DaoRuta() {
        fachada = new FachadaBD();
    }
    
    public List<Ruta> consultarAllRutas(){
        String sqlConsulta = "SELECT * from ruta WHERE estado = 't'";
        
        List<Ruta> nombreRutas = null;
        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sqlConsulta);
            nombreRutas = new ArrayList<Ruta>();
            while (table.next()) {
                Ruta ruta = new Ruta();
                ruta.setDescripcion(table.getString("descripcion"));
                ruta.setEstado(table.getBoolean("estado"));
                ruta.setNombre(table.getString("nombre"));
                nombreRutas.add(ruta);                
            }
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }       
       
       return nombreRutas;

    }

}