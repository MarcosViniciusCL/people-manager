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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import people.manager.controller.Controller;
import people.manager.controller.ControllerProduto;
import people.manager.model.Venda;

/**
 *
 * @author marcos
 */
public class VendaDAO {
    public static void create(Venda v){
        
//        v.setId(quantidadeBanco()+1);
        
        Connection con = ConnectionFactory.getConnection();
        Statement stmt;
       
        
        String dataVenda = calendarParaString(v.getData());
        String produtos = criarStringJSON(v.getProdutos());
        
        try {
            stmt = con.createStatement();
            String sql = "INSERT INTO VENDAS(DATA_VENDA, COMENTARIO, ID_CLIENTE, ID_VENDEDOR, PRODUTOS, VALOR, FORMA_PAGAMENTO, VALOR_RECEBIDO, VALOR_TROCO, ESTADO) VALUES("
                    + "'"+dataVenda+"',"
                    + "'"+v.getComentario()+"',"
                    + ""+v.getIdCliente()+","
                    + ""+v.getIdVendedor()+","
                    + "'"+produtos+"',"
                    + ""+v.getValorVenda()+","
                    + "'"+v.getFormaPagamento()+"',"
                    + ""+v.getValorRecebido()+","
                    + ""+v.getValorTroco()+", "
                    + "'"+v.getEstado()+"');";
            
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
    }
    
        public static void edita(Venda v){
        Connection con = ConnectionFactory.getConnection();
        Statement stmt;
        
        String dataVenda = calendarParaString(v.getData());
        String produtos = criarStringJSON(v.getProdutos());
        try {
            stmt = con.createStatement();
            String sql = "UPDATE VENDAS set "
                    + "DATA_VENDA = '"+dataVenda+"', "
                    + "COMENTARIO = '"+v.getComentario()+"', "
                    + "ID_CLIENTE = "+v.getIdCliente()+", "
                    + "ID_VENDEDOR = "+v.getIdVendedor()+", "
                    + "PRODUTOS = '"+produtos+"', "
                    + "VALOR = "+v.getValorVenda()+", "
                    + "FORMA_PAGAMENTO = '"+v.getFormaPagamento()+"', "
                    + "VALOR_RECEBIDO = "+v.getValorRecebido()+", "
                    + "VALOR_TROCO = "+v.getValorTroco()+", "
                    + "ESTADO = '"+v.getEstado()+"' "
                    + "where ID="+v.getId()+";";
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public static void remover(int id){
        Connection con = ConnectionFactory.getConnection();
        Statement stmt;
        int tamanho = 0;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("DELETE FROM VENDAS WHERE ID='"+id+"'");
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
        
    }
    
    public static ArrayList buscaData(Calendar cal) throws ParseException{
        Connection con = ConnectionFactory.getConnection();
        Statement stmt;
        ArrayList vendas = null;
        String dataVenda = calendarParaString(cal);
        
        try {
            stmt = con.createStatement();
            String sql = "SELECT * FROM VENDAS WHERE DATA_VENDA='"+dataVenda+"'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.isClosed())
                return null;
            vendas = new ArrayList();
            while(rs.next()){
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Calendar dataVendaAtual = Calendar.getInstance();
		dataVendaAtual.setTime(sdf.parse(rs.getString("DATA_VENDA")));
                ArrayList arrayProdutos = criarObjectoJSON(rs.getString("PRODUTOS"));
                Venda v = new Venda(rs.getInt("ID"), Controller.stringParaCalendar(rs.getString("DATA_VENDA")), rs.getString("COMENTARIO"), rs.getInt("ID_CLIENTE"), rs.getInt("ID_VENDEDOR"), arrayProdutos, rs.getDouble("VALOR"), rs.getString("FORMA_PAGAMENTO"), rs.getDouble("VALOR_RECEBIDO"), rs.getDouble("VALOR_TROCO"), rs.getString("ESTADO"));
           
                vendas.add(v);
            }
            
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
        return vendas;
    }
    
    public static Venda buscaID(int id){
        Connection con = ConnectionFactory.getConnection();
        Statement stmt;
        Venda v = null;
        
        try {
            stmt = con.createStatement();
            String sql = "SELECT * FROM VENDAS WHERE ID='"+id+"'";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.isClosed())
                return null;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar dataVendaAtual = Calendar.getInstance();
            dataVendaAtual.setTime(sdf.parse(rs.getString("DATA_VENDA")));
            ArrayList arrayProdutos = criarObjectoJSON(rs.getString("PRODUTOS"));
            v = new Venda(rs.getInt("ID"), Controller.stringParaCalendar(rs.getString("DATA_VENDA")), rs.getString("COMENTARIO"), rs.getInt("ID_CLIENTE"), rs.getInt("ID_VENDEDOR"), arrayProdutos, rs.getDouble("VALOR"), rs.getString("FORMA_PAGAMENTO"), rs.getDouble("VALOR_RECEBIDO"), rs.getDouble("VALOR_TROCO"), rs.getString("ESTADO"));
            
            stmt.close();
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
        return v;
    }                

    
    public static ArrayList listarTodos(){
        Connection con = ConnectionFactory.getConnection();
        Statement stmt;
        ArrayList vendas = null;
        
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM VENDAS");
            vendas = new ArrayList();
            while(rs.next()){
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Calendar nascimento = Calendar.getInstance();
		nascimento.setTime(sdf.parse(rs.getString("DATA_VENDA")));
                ArrayList arrayProdutos = criarObjectoJSON(rs.getString("PRODUTOS"));
                Venda v = new Venda(rs.getInt("ID"), Controller.stringParaCalendar(rs.getString("DATA_VENDA")), rs.getString("COMENTARIO"), rs.getInt("ID_CLIENTE"), rs.getInt("ID_VENDEDOR"), arrayProdutos, rs.getDouble("VALOR"), rs.getString("FORMA_PAGAMENTO"), rs.getDouble("VALOR_RECEBIDO"), rs.getDouble("VALOR_TROCO"), rs.getString("ESTADO"));
                vendas.add(v);
            }
            
            stmt.close();
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
        return vendas;
    }
    
    public static int quantidadeBanco(){
        Connection con = ConnectionFactory.getConnection();
        Statement stmt;
        int tamanho = 0;
        try {
             stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT seq FROM sqlite_sequence WHERE name ='VENDAS'");
            tamanho = rs.getInt("seq");
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con);
            
        }
        return tamanho;
    }
    
    
    /**
     * Receber um calendar e retorna o valor do dia e hora em string
     * @param calendar
     * @return 
     */
    private static String calendarParaString(Calendar calendar){
        if(calendar == null)
            return "NULO";
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return s.format(calendar.getTime());
    }    
    /**
     * Converte um ArrayList em uma stringo usando a bibliote JSON
     * @param o
     * @return String
     */
    private static String criarStringJSON(ArrayList o){
        JSONObject json = new JSONObject();
        json.put("uniqueArrays", new JSONArray(o));
        return json.toString();
    }
    /**
     * Converte uma string, criada pelo JSON, para ArrayList
     * @param s
     * @return ArrayList
     */
    private static ArrayList criarObjectoJSON(String s){
        JSONObject json = new JSONObject(s);
        JSONArray a = json.optJSONArray("uniqueArrays");
        return ControllerProduto.hashMapParaArray((ArrayList) a.toList());
    }
}
