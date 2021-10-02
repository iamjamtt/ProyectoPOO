package Modelo;

import Conexion.conexion;
import Entidad.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ProductoDAO implements CRUD{

    PreparedStatement ps;
    ResultSet rs;
    
    Conexion.conexion con = new conexion();
    Connection acceso;

    public String codPro(){
        String serie = "";
        
        String sql = "SELECT max(codigopro) FROM producto";
        
        try {
            acceso = con.conectar();
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                serie = rs.getString(1);
            }
        } catch (Exception e) {
        }
        
        return serie;
    }
    
    public int consStock(int id){
        int s = 0;
        String sql = "SELECT stockpro FROM producto WHERE idpro="+id;
        
        try {
            acceso = con.conectar();
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                s = rs.getInt("stockpro");
            }
            
        } catch (Exception e) {
            System.out.println("error consultar stock producto " + e);
        }
        return s;
    }
    
    //para las ventas lo haremos de una manera diferente
    public Entidad.Producto consultarproDif(String b){
        Entidad.Producto p = new Producto();
        
        String sql = "SELECT * FROM producto WHERE estadopro=1 AND codigopro=?";
   
        try {
            acceso = con.conectar();
            ps = acceso.prepareStatement(sql);
            ps.setString(1, b);
            rs = ps.executeQuery();
            while(rs.next()){
                p.setIdpro(rs.getInt(1));
                p.setCodpro(rs.getString(2));
                p.setNompro(rs.getString(3));
                p.setPrepro(rs.getDouble(4));
                p.setStock(rs.getInt(5));
                p.setEstpro(rs.getInt(6));
            }
        } catch (Exception e) {
            System.out.println("error consultar datos del PRODUCTO 2da forma " + e);
        }
        
        
        return p;
    }
    
    public DefaultTableModel mostrar(String b){
        String []titulos={"ID","CODIGO","NOMBRE","PRECIO","STOCK"};
        DefaultTableModel m = new DefaultTableModel(null, titulos);
        Object[] o = new Object[5];
        
        String sql = "SELECT idpro,codigopro,nombrepro,preciopro,stockpro FROM producto WHERE estadopro=1 AND (codigopro LIKE '%" + b + "%' OR nombrepro LIKE '%" + b + "%')";
   
        try {
            acceso = con.conectar();
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                o[0] = rs.getInt(1);
                o[1] = rs.getString(2);
                o[2] = rs.getString(3);
                o[3] = rs.getString(4);
                o[4] = rs.getString(5);
                
                m.addRow(o);
            }
        } catch (Exception e) {
            System.out.println("error mostrar (codigo y nombre) prodao " + e);
        }
        
        
        return m;
    }
    
    
    @Override
    public List listar() {
        List<Entidad.Producto> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM producto WHERE estadopro=1";
        
        try {
            acceso = con.conectar();
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Entidad.Producto p = new Producto();
                p.setIdpro(rs.getInt(1));
                p.setCodpro(rs.getString(2));
                p.setNompro(rs.getString(3));
                p.setPrepro(rs.getDouble(4));
                p.setStock(rs.getInt(5));
                p.setEstpro(rs.getInt(6));
                lista.add(p);
            }
        } catch (Exception e) {
            System.out.println("error mostrar productodaoo " + e);
        }
        
        return lista;
    }

    @Override
    public int add(Object[] o) {
        int r = 0;
        String sql = "INSERT INTO producto(codigopro,nombrepro,preciopro,stockpro,estadopro) VALUES(?,?,?,?,?)";
        
        try {
            acceso = con.conectar();
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            ps.setObject(4, o[3]);
            ps.setObject(5, o[4]);
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("error al ingresar prodcto " + e);
        }
        
        return r;
    }

    @Override
    public int update(Object[] o) {
        int r = 0;
        String sql = "UPDATE producto SET nombrepro=?,preciopro=?,stockpro=? WHERE idpro=?";
        
        try {
            acceso = con.conectar();
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            ps.setObject(4, o[3]);
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("error actualizar productooo " + e);
        }
        
        return r;
    }

    @Override
    public int delete(Object[] o) {
        int r = 0;
        String sql = "UPDATE producto SET estadopro=? WHERE idpro=?";
        
        try {
            acceso = con.conectar();
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("error eliminar / desactivar producto " + e);
        }
        
        return r;
    }
    
}
