/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people.manager.persistencia;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import people.manager.controller.Controller;
import people.manager.model.Produto;
import people.manager.model.Usuario;

/**
 *
 * @author marcos
 */
public class UsuarioDAO {
    
    public static void create(Usuario u){
             
        Connection con = ConnectionFactory.getConnection();
        Statement stmt;
        
        try {
            stmt = con.createStatement();
            String sql = "INSERT INTO USUARIOS(USUARIO, SENHA, NIVEL) VALUES("
                    + "'"+u.getUser()+"',"
                    + "'"+u.getSenha()+"',"
                    + ""+u.getLevel()+");";
            
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
    }
    
    public static void remover(String user){
        Connection con = ConnectionFactory.getConnection();
        Statement stmt;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("DELETE FROM USUARIOS WHERE USUARIO='"+user+"'");
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
        
    }
    
    public static Usuario obterUsuario(String user){
        Connection con = ConnectionFactory.getConnection();
        Statement stmt;
        Usuario u = null;
        try {
            stmt = con.createStatement();
            String sql = "SELECT * FROM USUARIOS WHERE USUARIO = '"+user+"'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.isClosed())
                return null;
            u = new Usuario(rs.getString("USUARIO"), rs.getString("SENHA"), rs.getInt("NIVEL"));
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
        return u;
    }
    
    public static void edita(Usuario u){
        Connection con = ConnectionFactory.getConnection();
        Statement stmt;
   
        try {
            stmt = con.createStatement();
            String sql = "UPDATE USUARIOS set "
                    + "SENHA = '"+u.getSenha()+"', "
                    + "NIVEL = "+u.getLevel()+" "
                    + "where USUARIO='"+u.getUser()+"';";
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
