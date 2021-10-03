package Modelo;

import Conexion.conexion;
import Entidad.TipoUser;
import Entidad.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;

public class UserDAO implements CRUD{
    PreparedStatement ps;
    ResultSet rs;
    
    Conexion.conexion con = new conexion();
    Connection acceso;
    
    public Entidad.User ValidarUser(String username, String pass){
        Entidad.User eu = new User();
        
        String sql = "SELECT * FROM user WHERE username=? AND password=? AND estadou=1";
        
        try {
            acceso = con.conectar();
            ps = acceso.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while(rs.next()){
                eu.setIdUser(rs.getInt(1));
                eu.setNombre(rs.getString(2));
                eu.setApellido(rs.getString(3));
                eu.setTelefono(rs.getString(4));
                eu.setUsername(rs.getString(5));
                eu.setPass(rs.getString(6));
                eu.setEstadou(rs.getInt(7));
                eu.setTipouser(rs.getInt(8));
            }
        } catch (Exception e) {
            System.out.println("error al validad user del login:  " + e);
        }
        
        return eu;
    }
    
    public void cargarTipo(JComboBox cbo){
        Entidad.TipoUser tu = new TipoUser();
        
        String sql = "SELECT descripciontippou FROM tipouseer WHERE idtipou=2";
        
        try {
            acceso = con.conectar();
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();
            cbo.addItem("Seleccione");
            
            while(rs.next()){
                cbo.addItem(rs.getString("descripciontippou"));
            }
            
        } catch (Exception e) {
            System.out.println("Error en combo tipo user: " + e);
        }
    }
    
    public String NroEmp(){
        String serie = "";
        String sql = "SELECT max(username) FROM user WHERE idtipou=2";
        
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
    
    @Override
    public java.util.List listar() {
        List<Entidad.User> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM user WHERE estadou=1 and idtipou=2";
        
        try {
            acceso = con.conectar();
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                User u = new User();
                u.setIdUser(rs.getInt(1));
                u.setNombre(rs.getString(2));
                u.setApellido(rs.getString(3));
                u.setTelefono(rs.getString(4));
                u.setUsername(rs.getString(5));
                u.setPass(rs.getString(6));
                u.setEstadou(rs.getInt(7));
                u.setTipouser(rs.getInt(8));
                lista.add(u);
            }
        } catch (Exception e) {
            System.out.println("error mostrar userdao " + e);
        }
        
        return lista;
    }

    @Override
    public int add(Object[] o) {
        int r = 0;
        String sql = "INSERT INTO user(nombreu,apellidou,teledonou,username,password,estadou,idtipou) VALUES(?,?,?,?,?,?,?)";
        
        try {
            acceso = con.conectar();
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            ps.setObject(4, o[3]);
            ps.setObject(5, o[4]);
            ps.setObject(6, o[5]);
            ps.setObject(7, o[6]);
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("error al ingresar empleado " + e);
        }
        
        return r;
    }

    @Override
    public int update(Object[] o) {
        int r = 0;
        String sql = "UPDATE user SET nombreu=?,apellidou=?,teledonou=? WHERE idu=?";
        
        try {
            acceso = con.conectar();
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            ps.setObject(3, o[2]);
            ps.setObject(4, o[3]);
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("error actualizar emp " + e);
        }
        
        return r;
    }

    @Override
    public int delete(Object[] o) {
        int r = 0;
        String sql = "UPDATE user SET estadou=? WHERE idu=?";
        
        try {
            acceso = con.conectar();
            ps = acceso.prepareStatement(sql);
            ps.setObject(1, o[0]);
            ps.setObject(2, o[1]);
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("error eliminar / desactivar emp " + e);
        }
        
        return r;
    }

}
