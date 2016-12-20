/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people.manager.persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import people.manager.model.Produto;

/**
 *
 * @author marcos
 */
public class ProdutoDAO {
    
    public static void create(Produto p){
        
        p.setId(quantidadeBanco()+1);
        
        Connection con = ConnectionFactory.getConnection();
        Statement stmt;
        
        try {
            stmt = con.createStatement();
            String sql = "INSERT INTO PRODUTOS(ID, NOME, CODIGOBARRA, CATEGORIA, VALOR, VALORCOMPRA, QUANTIDADE, CATEGORIA) VALUES("
                    + ""+p.getId()+","
                    + "'"+p.getNome()+"',"
                    + "'"+p.getCodigoBarra()+"',"
                    + "'"+p.getCategoria()+"',"
                    + ""+p.getValorVenda()+","
                    + ""+p.getValorCompra()+","
                    + ""+p.getQuantidade()+","
                    + "'"+p.getCategoria()+"');";
            
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
    }
    
    public static void remover(int id){
        Connection con = ConnectionFactory.getConnection();
        Statement stmt;
        try {
            stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM PRODUTOS WHERE ID='"+id+"'");
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
        
    }
    
    public static ArrayList listarTodos(){
        Connection con = ConnectionFactory.getConnection();
        Statement stmt;
        ArrayList produtos = null;
        
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUTOS");
            produtos = new ArrayList();
            while(rs.next()){
                Produto p = new Produto(rs.getString("NOME"), rs.getString("CATEGORIA"),rs.getString("CODIGOBARRA"), rs.getDouble("VALOR"), rs.getDouble("VALORCOMPRA"), rs.getInt("QUANTIDADE")); 
                p.setId(rs.getInt("ID"));
                produtos.add(p);
            }
            
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
        return produtos;
    }
    /**
     * Retorna a quantidade de produtos que tem no banco de dados.
     * @return int
     */
    public static int quantidadeBanco(){
        Connection con = ConnectionFactory.getConnection();
        Statement stmt;
        int tamanho = 0;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUTOS WHERE   ID = (SELECT MAX(ID)  FROM PRODUTOS)");
            tamanho = rs.getInt("ID");
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con);   
        }
        return tamanho;
    }
    /**
     * Busca o produto pelo nome e o retorna.
     * @param nome
     * @return ArrayList 
     */
    public static ArrayList buscaNome(String nome){
        Connection con = ConnectionFactory.getConnection();
        Statement stmt;
        ArrayList produtos = null;
        
        try {
            stmt = con.createStatement();
            String sql = "SELECT * FROM PRODUTOS WHERE NOME LIKE '"+nome+"%' OR NOME LIKE '%"+nome+"%'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.isClosed())
                return null;
            produtos = new ArrayList();
            while(rs.next()){
                Produto c = new Produto(rs.getString("NOME"), rs.getString("CATEGORIA"), rs.getString("CODIGOBARRA"), rs.getDouble("VALOR"), rs.getDouble("VALORCOMPRA"), rs.getInt("QUANTIDADE"));
                c.setId(rs.getInt("ID"));
                produtos.add(c);
            }
            
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
        return produtos;
    }
    
    public static Produto buscaID(int id){
        Connection con = ConnectionFactory.getConnection();
        Statement stmt;
        Produto produto = null;
        try {
            stmt = con.createStatement();
            String sql = "SELECT * FROM PRODUTOS WHERE ID = "+id+"";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.isClosed())
                return null;
            produto = new Produto(rs.getString("NOME"), rs.getString("CATEGORIA"),rs.getString("CODIGOBARRA"), rs.getDouble("VALORCOMPRA"), rs.getDouble("VALOR"), rs.getInt("QUANTIDADE"));
            produto.setId(rs.getInt("ID"));
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
        return produto;
    }
    
    public static Produto buscaCodBarra(String codigo){
        Connection con = ConnectionFactory.getConnection();
        Statement stmt;
        Produto produto = null;
        try {
            stmt = con.createStatement();
            String sql = "SELECT * FROM PRODUTOS WHERE CODIGOBARRA = "+codigo+"";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.isClosed())
                return null;
            produto = new Produto(rs.getString("NOME"), rs.getString("CATEGORIA"),rs.getString("CODIGOBARRA"), rs.getDouble("VALOR"), rs.getDouble("VALORCOMPRA"), rs.getInt("QUANTIDADE"));
            produto.setId(rs.getInt("ID"));
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
        return produto;
    }
    
    public static void edita(Produto p){
        Connection con = ConnectionFactory.getConnection();
        Statement stmt;
   
        try {
            stmt = con.createStatement();
            String sql = "UPDATE PRODUTOS set "
                    + "NOME = '"+p.getNome()+"', "
                    + "CODIGOBARRA = '"+p.getCodigoBarra()+"', "
                    + "CATEGORIA = '"+p.getCategoria()+"', "
                    + "VALOR = "+p.getValorVenda()+", "
                    + "VALORCOMPRA = "+p.getValorCompra()+", "
                    + "QUANTIDADE = "+p.getQuantidade()+" "
                    + "where ID="+p.getId()+";";
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
