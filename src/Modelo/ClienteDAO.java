package Modelo;

import Conexion.conexion;
import Entidad.Cliente;
import Entidad.NroCta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ClienteDAO implements CRUD{
    PreparedStatement ps;
    ResultSet rs;
    
    Conexion.conexion con = new conexion();
    Connection acceso;
    
    public int consultar(int id){
        String sql = "SELECT * FROM nrocta WHERE estadocta=1 AND idcli="+id;
        int p = 0;
        
        try {
            acceso = con.conectar();
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                p = rs.getInt("idcli");
            }
            
        } catch (Exception e) {
            System.out.println("error consultar cta " + e);
        }
        
        return p;
    }
    
    public String consultarDni(String dniIng){
        String sql = "SELECT dnicli FROM cliente WHERE dnicli='"+dniIng+"' AND estadocli=1";
        String dni = "";
        
        try {
            acceso = con.conectar();
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                dni = rs.getString("dnicli");
            }
            
        } catch (Exception e) {
            System.out.println("error consultar cta " + e);
        }
        
        return dni;
    }
    
    public int consultarIDIngresado(String dni){
        String sql = "SELECT idcli FROM cliente WHERE dnicli='"+dni+"' AND estadocli=1";
        int p = 0;
        
        try {
            acceso = con.conectar();
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                p = rs.getInt("idcli");
            }
            
        } catch (Exception e) {
            System.out.println("error consultar id ingresado cta " + e);
        }
        
        return p;
    }
    
    public String NroCta(){
        String serie = "";
        String sql = "SELECT max(codigocta) FROM nrocta";
        
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
    
    public Object[] consultarcta(String b){
        Object[] o = new Object[5];
        
        String sql = "SELECT * FROM nrocta WHERE estadocta=1 AND codigocta='" + b + "'";
   
        try {
            acceso = con.conectar();
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                o[0] = rs.getInt(1);
                o[1] = rs.getString(2);
                o[2] = rs.getDouble(3);
                o[3] = rs.getInt(4);
                o[4] = rs.getInt(5);
                
                return o;
            }
        } catch (Exception e) {
            System.out.println("error consultar datos del cta " + e);
        }
        
        
        return null;
    }
    
    //inner join
    public DefaultTableModel consultarctaInnerJoin(String b){
        String []titulos={"ID","CODIGO","MONTO","DNI","NOMBRE","APELLIDO"};
        DefaultTableModel m = new DefaultTableModel(null, titulos);
        Object[] o = new Object[6];
        
        String sql = "SELECT n.idnrocta,n.codigocta,n.montocta,c.dnicli,c.nombrecli,c.apellidocli FROM nrocta n INNER JOIN cliente c ON n.idcli=c.idcli WHERE c.estadocli=1 AND n.estadocta=1 AND (n.codigocta LIKE '%" + b + "%' OR c.nombrecli LIKE '%" + b +"%')";
   
        try {
            acceso = con.conectar();
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                o[0] = rs.getInt(1);
                o[1] = rs.getString(2);
                o[2] = rs.getDouble(3);
                o[3] = rs.getString(4);
                o[4] = rs.getString(5);
                o[5] = rs.getString(6);
                
                m.addRow(o);
            }
        } catch (Exception e) {
            System.out.println("error consultar datos del cta en el nuevo modulo" + e);
        }

        return m;
    }
    
    //para las ventas lo haremos de una manera diferente
    public Entidad.NroCta consultarctaDif(String b){
        Entidad.NroCta n = new NroCta();
        
        String sql = "SELECT * FROM nrocta WHERE estadocta=1 AND codigocta=?";
   
        try {
            acceso = con.conectar();
            ps = acceso.prepareStatement(sql);
            ps.setString(1, b);
            rs = ps.executeQuery();
            while(rs.next()){
                n.setIdnrocta(rs.getInt(1));
                n.setCodigocta(rs.getString(2));
                n.setMontocta(rs.getDouble(3));
                n.setEstadocta(rs.getInt(4));
                n.setIdcli(rs.getInt(5));
            }
        } catch (Exception e) {
            System.out.println("error consultar datos del cta 2da forma " + e);
        }
        
        
        return n;
    }
    
    public Object[] consultarcli(int id){
        Object[] o = new Object[3];
        
        String sql = "SELECT idcli,nombrecli,apellidocli FROM cliente WHERE estadocli=1 AND idcli=" + id;
   
        try {
            acceso = con.conectar();
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                o[0] = rs.getInt(1);
                o[1] = rs.getString(2);
                o[2] = rs.getString(3);
                
                return o;
            }
        } catch (Exception e) {
            System.out.println("error consultar cli " + e);
        }
        
        
        return null;
    }  
    
    //otra forma de conultar al cliente
    public Entidad.Cliente consultarcliDif(int id){
        Entidad.Cliente c = new Cliente();
        
        String sql = "SELECT idcli,dnicli,nombrecli,apellidocli FROM cliente WHERE estadocli=1 AND idcli=?";
   
        try {
            acceso = con.conectar();
            ps = acceso.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                c.setIdcli(rs.getInt(1));
                c.setDni(rs.getString(2));
                c.setNombrecli(rs.getString(3));
                c.setApellidocli(rs.getString(4));
            }
        } catch (Exception e) {
            System.out.println("error consultar cli otra forma " + e);
        }
        
        
        return c;
    }
    
    public String consultarcta2(String b){
        String o = "";
        
        String sql = "SELECT * FROM nrocta WHERE estadocta=1 AND codigocta='" + b + "'";
   
        try {
            acceso = con.conectar();
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                o = rs.getString("codigocta");
                return o;
            }
        } catch (Exception e) {
            System.out.println("error mostrar ctadao " + e);
        }
        
        return null;
    }
    
    public DefaultTableModel mostrar(String b){
        String []titulos={"ID","DNI","NOMBRE","APELLIDO","TELEFONO","DIRECCION"};
        DefaultTableModel m = new DefaultTableModel(null, titulos);
        Object[] o = new Object[6];
        
        String sql = "SELECT idcli,dnicli,nombrecli,apellidocli,telefonocli,dieccioncli FROM cliente WHERE estadocli=1 AND (dnicli LIKE '%" + b + "%' OR nombrecli LIKE '%" + b + "%')";
   
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
                o[5] = rs.getString(6);
                
                m.addRow(o);
            }
        } catch (Exception e) {
            System.out.println("error mostrar ctadao " + e);
        }
        
        
        return m;
    }
    
    public int add2(Object[] o) {
        int r = 0;
        String sql = "INSERT INTO nrocta(codigocta,montocta,estadocta,idcli) VALUES(?,?,?,?)";
        
        try {
            acceso = con.conectar();
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            ps.setObject(4, o[3]);
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("error al ingresar nrocta " + e);
        }
        
        return r;
    }
    
    @Override
    public List listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int add(Object[] o) {
        int r = 0;
        String sql = "INSERT INTO cliente(dnicli,nombrecli,apellidocli,telefonocli,dieccioncli,estadocli) VALUES(?,?,?,?,?,?)";
        
        try {
            acceso = con.conectar();
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            ps.setObject(4, o[3]);
            ps.setObject(5, o[4]);
            ps.setObject(6, o[5]);
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("error al ingresar cliente " + e);
        }
        
        return r;
    }

    @Override
    public int update(Object[] o) {
        int r = 0;
        String sql = "UPDATE cliente SET dnicli=?,nombrecli=?,apellidocli=?,telefonocli=?,dieccioncli=? WHERE idcli=?";
        
        try {
            acceso = con.conectar();
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            ps.setObject(4, o[3]);
            ps.setObject(5, o[4]);
            ps.setObject(6, o[5]);
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("error actualizar cli " + e);
        }
        
        return r;
    }

    @Override
    public int delete(Object[] o) {
        int r = 0;
        String sql = "UPDATE cliente SET estadocli=? WHERE idcli=?";
        
        try {
            acceso = con.conectar();
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("error eliminar / desactivar cli " + e);
        }
        
        return r;
    }
    
    public int updatecta(Object[] o) {
        int r = 0;
        String sql = "UPDATE nrocta SET montocta=? WHERE idnrocta=?";
        
        try {
            acceso = con.conectar();
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("error actualizar monto del cta cli " + e);
        }
        
        return r;
    }
}
