package Modelo;

import Conexion.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VentasDAO {
    PreparedStatement ps;
    ResultSet rs;
    
    Conexion.conexion con = new conexion();
    Connection acceso;
    
    int r=0;
    
    public String consultarCodigoVen(){
        String serie = "";
        String sql = "SELECT max(codigoven) FROM ventas";
        
        try {
            acceso = con.conectar();
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                serie = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("error en consiltar codigo e ventas " + e);
        }
        
        return serie;
    }
    
    public int obtIdVentas(){
        int id = 0;
        
        String sql = "SELECT MAX(idven) FROM ventas";
        
        try {
            acceso = con.conectar();
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("error en obtner idventas ultimo " + e);
        }
        
        return id;
    }
    
    public int retornarCantidad(String cod){
        int cant = 0;
        String sql = "SELECT stockpro FROM producto WHERE codigopro=?";
        
        try {
            acceso = con.conectar();
            ps = acceso.prepareStatement(sql);
            ps.setString(1, cod);
            rs = ps.executeQuery();
            while(rs.next()){
                cant = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("error en obtner la cantidad de stock " + e);
        }
        
        return cant;
    }
    
    public int actualizarStock(String cod, int cantIngresada, int cantObtenida){
        int desfinal = cantObtenida - cantIngresada;
        
        String sql = "UPDATE producto SET stockpro=? WHERE codigopro=?";
        
        try {
            acceso = con.conectar();
            ps = acceso.prepareStatement(sql);
            ps.setInt(1, desfinal);
            ps.setString(2, cod);
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("error al disminuir stock " + e);
        }
        
        return r;
    }
    
    public int descSaldo(String cod, double saldoIngresada, double saldo){
        double saldoFinal = saldo - saldoIngresada;
        
        String sql = "UPDATE nrocta SET montocta=? WHERE codigocta=?";
        
        try {
            acceso = con.conectar();
            ps = acceso.prepareStatement(sql);
            ps.setDouble(1, saldoFinal);
            ps.setString(2, cod);
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("error al disminuir saldo cta " + e);
        }
        
        return r;
    }
    
    public int guardarVentas(Entidad.Ventas v){
       
        String sql = "INSERT INTO ventas(idnrocta,idu,codigoven,fechaopeven,montoven,estadoven) VALUES(?,?,?,?,?,?)";
        
        try {
            acceso = con.conectar();
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, v.getIdnrocta());
            ps.setObject(2, v.getIdu());
            ps.setObject(3, v.getCodven());
            ps.setObject(4, v.getFecven());
            ps.setObject(5, v.getMonven());
            ps.setObject(6, v.getEstven());
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("error al ingresar ventas " + e);
        }
        
        return r;
    }
    
    public int guardarDetalleVentas(Entidad.Detalle d){
       
        String sql = "INSERT INTO detalle(idpro,idven,cantidaddet,precioventadet) VALUES(?,?,?,?)";
        
        try {
            acceso = con.conectar();
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, d.getIdpro());
            ps.setObject(2, d.getIdven());
            ps.setObject(3, d.getCantdet());
            ps.setObject(4, d.getPreciodet());
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("error al ingresar detalle de ventas " + e);
        }
        
        return r;
    }
}
