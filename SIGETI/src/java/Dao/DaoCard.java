/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entidades.Tarjeta;
import Entidades.TarjetaGenerica;
import Entidades.TarjetaPersonalizada;
import Utilidades.FachadaBD;
import java.sql.*;

/**
 *
 * @author Felipe
 */
public class DaoCard {

    private FachadaBD fachada;

    public DaoCard() {
        fachada = new FachadaBD();
    }

    public int saveCard(TarjetaPersonalizada tarjeta) {

        int result=0;
        String sql_insert = "INSERT INTO tarjeta  (pin,saldo,estado,tipo,costo,fecha_venta,estacion_venta)";
        
        sql_insert+="VALUES ("+ tarjeta.getPin()+"  ,"+tarjeta.getSaldo()+",true"+ ","+tarjeta.getTipo()+ ","+tarjeta.getCosto()+ ","+"null"+ ","+"null"+")";

      
         System.err.println(sql_insert);
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
        System.err.println(sql_insert);
        return result;
        
    }
    
    private int saveCardCustom(String pin){
        
         int result=0;
        String sql_insert = "INSERT INTO tarjeta_personalizada   (pin,credito)";
        
        sql_insert+="VALUES ("+pin+ ",3)";

      
         System.err.println("ENTRE A PERSONA"+sql_insert);
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
        System.err.println(sql_insert);
        return result;
     
    }
        
    private int saveCardGeneric(String pin){
        
         int result=0;
        String sql_insert = "INSERT INTO tarjeta_generica  (pin)";
        
        sql_insert+="VALUES ("+pin+ ")";

      
         System.err.println("ENTRE A GENERICA"+sql_insert);
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
        System.err.println(sql_insert);
        return result;
     
    }
     public int saveCard(TarjetaGenerica tarjeta) {

        int result=0;
        String sql_insert = "INSERT INTO tarjeta  (pin,saldo,estado,tipo,costo,fecha_venta,estacion_venta)";
        
        sql_insert+="VALUES ("+ tarjeta.getPin()+"  ,"+tarjeta.getSaldo()+",true"+ ","+tarjeta.getTipo()+ ","+tarjeta.getCosto()+ ","+"null"+ ","+tarjeta.getEstacionVenta()+")";

       
       
       
         System.err.println(sql_insert);
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
        
        
       System.err.println("antes de condciion ");
        if( tarjeta.getTipo()==2){
           saveCardGeneric( tarjeta.getPin());
       
       
     
        }
       
     
        if( tarjeta.getTipo()==1){
           saveCardCustom(tarjeta.getPin());
       
       
    
        }
        
        System.err.println("despues de condciion ");
        return result;
        
    }
     
     public Tarjeta findCard (String pin){
     
        
        String sqlConsulta = "SELECT * from tarjeta WHERE pin = '" + pin+"'" ;
        Tarjeta tarjeta = new Tarjeta();
        
        int a;
        

        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sqlConsulta);
            

            while (table.next()) { 
                 System.err.println("Entre:::" );
                tarjeta.setPin(table.getString("pin"));
                 System.err.println("pase:::" + tarjeta.getPin() );
                tarjeta.setSaldo(Integer.parseInt(table.getString("saldo")));          
                tarjeta.setTipo(Integer.parseInt(table.getString("tipo")));
                tarjeta.setCosto(Integer.parseInt(table.getString("costo")));
                tarjeta.setFechaVenta(table.getString("fecha_venta"));
                
                String estacion=table.getString("estacion_venta");
                if(estacion ==null){
                
                    tarjeta.setEstacionVenta(0);
                }
                else{
                    tarjeta.setEstacionVenta(Integer.parseInt(table.getString("fecha_venta")));
                
                }
            }
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.err.println("AQUIIIIIIIIIIIIIII:::" +pin);
        return tarjeta;
     }
     
     
     public String findCardFromUser(String cedula ){
         String sqlConsulta = "SELECT adquiere_tarjeta from usuario WHERE id = '" + cedula+"'" ;
         
          int a;
          String pin="";
        

        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sqlConsulta);
            

            while (table.next()) { 
                
                
                pin=table.getString("adquiere_tarjeta");
                
            }
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
         
         
         
         return pin ;
     
     
     }
    
    
}
