/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entidades.Tarjeta;
import Entidades.TarjetaPersonalizada;
import Utilidades.FachadaBD;
import java.sql.*;
import java.util.Vector;

/**
 *
 * @author Felipe
 */
public class DaoCard {

    private FachadaBD fachada;

    public DaoCard() {
        fachada = new FachadaBD();
    }

    public int saveCard(Tarjeta tarjeta) {

        int result=0;
        String sql_insert = "INSERT INTO tarjeta  (pin,saldo,estado,tipo,costo,fecha_venta,estacion_venta)";
        
        sql_insert+="VALUES ("+ tarjeta.getPin()+"  ,"+tarjeta.getSaldo()+",true"+ ","+tarjeta.getTipo()+ ","+tarjeta.getCosto()+ ",'"+tarjeta.getFechaVenta()+ "',"+tarjeta.getEstacionVenta()+")";

      
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
        
        
        if (tarjeta.getTipo()==1 && result!=0)
        {
        saveCardCustom(tarjeta.getPin());
        }
        if (tarjeta.getTipo()==2 && result!=0)
        {
        saveCardGeneric(tarjeta.getPin());
        }
        
        
        
        
       
        return result;
        
    }
    
    private int saveCardCustom(String pin){
        
         int result=0;
        String sql_insert = "INSERT INTO tarjeta_personalizada   (pin,credito)";
        
        sql_insert+=" VALUES ("+pin+ ",3)";

      
        
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
       
        return result;
     
    }
        
    private int saveCardGeneric(String pin){
        
         int result=0;
        String sql_insert = "INSERT INTO tarjeta_generica  (pin)";
        
        sql_insert+=" VALUES ("+pin+ ")";

      
        
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
        
        return result;
     
    }
    public Vector  <Tarjeta>  findCardsFromName(String nombre ){
        
        String sqlConsulta = "SELECT * from tarjeta NATURAL JOIN usuario WHERE nombre LIKE '%"+nombre+"%'";
        
        
        int a;
        Vector <Tarjeta> tarjetas = new Vector<Tarjeta>();

        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sqlConsulta);
            

            while (table.next()) { 
                Tarjeta tarjeta = new Tarjeta();
               
                tarjeta.setPin(table.getString("pin"));
           
                tarjeta.setSaldo(Integer.parseInt(table.getString("saldo")));          
                tarjeta.setTipo(Integer.parseInt(table.getString("tipo")));
                tarjeta.setCosto(Integer.parseInt(table.getString("costo")));
                tarjeta.setFechaVenta(table.getString("fecha_venta"));
                
                if(table.getString("estado").equals("t")){
                    tarjeta.setEstado(true);
                
                }
                else {
                    tarjeta.setEstado(false);
                
                
                }
             
                
                String estacion=table.getString("estacion_venta");
                if(estacion ==null){
                
                    tarjeta.setEstacionVenta(0);
                }
                else{
                    tarjeta.setEstacionVenta(Integer.parseInt(table.getString("estacion_venta")));
                
                }
                
                tarjetas.add(tarjeta);
            }
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        return tarjetas;
    
    }
    
    public int reloadCard(String pin, int recarga,int estacion ){
        int valor =recarga ;
       
        TarjetaPersonalizada tarjeta = new TarjetaPersonalizada();
        
        tarjeta = findCardCustom(pin);
        if (tarjeta.getPin()==null){return 0;}
        if (tarjeta.getEstado()==false){return 0;}
        
        
        if(tarjeta.getCredito()<3){
            int temp = recarga;
            for (int i=0;i<temp;i++){
            
                tarjeta.setCredito(tarjeta.getCredito()+1);
                recarga--;
                if(tarjeta.getCredito()==3)break;
            
            }
         
        }      
        tarjeta.setSaldo(tarjeta.getSaldo()+recarga);
        int result= editCard(tarjeta);
        
        
        
        if (result !=0){
        String sql_insert = "INSERT INTO recarga (pin,fecha,hora,valor,id_estacion)";
        
         java.util.Date date = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String fecha = sdf.format(date);
       
        
        String [] hora =date.toString().split(" "); 
        
        
        sql_insert+=" VALUES ("+pin+ ",'"+fecha+"','"+hora[3]+"',"+valor+","+estacion+")";

      
        
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
        
        }
        
       
        
        return result;
        
    }
    
    
    public int logicalErase(String pin){
        
         
         int result=0;
        String sql_insert = "UPDATE tarjeta SET  estado=false WHERE  pin='"+pin+"'";
     
        
       
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
       
       
        
        return result;
     
    }
    
    public TarjetaPersonalizada findCardCustom (String pin){
     
        
        String sqlConsulta = "SELECT * from tarjeta_personalizada  natural join tarjeta WHERE pin = '" + pin+"'" ;
        TarjetaPersonalizada tarjeta = new TarjetaPersonalizada();
        
        int a;
        

        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sqlConsulta);
            

            while (table.next()) { 
               
                tarjeta.setPin(table.getString("pin"));
           
                tarjeta.setSaldo(Integer.parseInt(table.getString("saldo")));          
                tarjeta.setTipo(Integer.parseInt(table.getString("tipo")));
                tarjeta.setCosto(Integer.parseInt(table.getString("costo")));
                tarjeta.setFechaVenta(table.getString("fecha_venta"));
                tarjeta.setCredito(Integer.parseInt(table.getString("credito")));
                
                if(table.getString("estado").equals("t")){
                    tarjeta.setEstado(true);
                
                }
                else {
                    tarjeta.setEstado(false);
                
                
                }
             
                
                String estacion=table.getString("estacion_venta");
                if(estacion ==null){
                
                    tarjeta.setEstacionVenta(0);
                }
                else{
                    tarjeta.setEstacionVenta(Integer.parseInt(table.getString("estacion_venta")));
                
                }
            }
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        return tarjeta;
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
               
                tarjeta.setPin(table.getString("pin"));
           
                tarjeta.setSaldo(Integer.parseInt(table.getString("saldo")));          
                tarjeta.setTipo(Integer.parseInt(table.getString("tipo")));
                tarjeta.setCosto(Integer.parseInt(table.getString("costo")));
                tarjeta.setFechaVenta(table.getString("fecha_venta"));
                
                if(table.getString("estado").equals("t")){
                    tarjeta.setEstado(true);
                
                }
                else {
                    tarjeta.setEstado(false);
                
                
                }
             
                
                String estacion=table.getString("estacion_venta");
                if(estacion ==null){
                
                    tarjeta.setEstacionVenta(0);
                }
                else{
                    tarjeta.setEstacionVenta(Integer.parseInt(table.getString("estacion_venta")));
                
                }
            }
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        return tarjeta;
     }
     
     public  int editCard(Tarjeta tarjeta){
         
         
        int result=0;
        String sql_insert = "UPDATE  tarjeta  ";
        
        sql_insert+="SET saldo=  "+tarjeta.getSaldo() +" WHERE pin='"+ tarjeta.getPin()+"'";
        
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
         
         
        
         return result;
     
     
     
     }
     
     public  int editCard(TarjetaPersonalizada tarjeta){
         
         
        int result=0;
        String sql_insert = "UPDATE  tarjeta ";
        
        sql_insert+="SET saldo=  "+tarjeta.getSaldo()+" WHERE pin='"+ tarjeta.getPin()+"'";

      
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
        
        
        sql_insert = "UPDATE  tarjeta_personalizada ";
        
        sql_insert+="SET  credito="+tarjeta.getCredito()+" WHERE pin='"+ tarjeta.getPin()+"'";

      
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
         
         
        
         return result;
     
     
     
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
