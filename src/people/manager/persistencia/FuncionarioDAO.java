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
import people.manager.model.Endereco;
import people.manager.model.Funcionario;

/**
 *
 * @author marcos
 */
public class FuncionarioDAO {
    
    /**
     * Adiciona um novo cliente no banco de dados.
     * @param c 
     */
    public static void create(Funcionario c){
        
//        c.setId(quantidadeBanco()+1);
        
        Connection con = ConnectionFactory.getConnection();
        Statement stmt;
        
        int ativo = 0;
        if(c.isAtivo())
            ativo = 1;
        
        String nascimento = calendarParaString(c.getNascimento());
        String contratacao = calendarParaString(c.getContratacao());
        String endereco = c.getEndereco().toString();
        
        try {
            stmt = con.createStatement();
            String sql = "INSERT INTO VENDEDORES(nome, ESTADO, sobrenome, celular, email, idade, nascimento, DATA_contratacao, cpf, endereco) VALUES("
                    + "'"+c.getNome()+"',"
                    + ""+ativo+","
                    + "'"+c.getSobrenome()+"',"
                    + "'"+c.getCelular()+"',"
                    + "'"+c.getEmail()+"',"
                    + ""+c.getIdade()+","
                    + "'"+nascimento+"',"
                    + "'"+contratacao+"',"
                    + "'"+c.getCpf()+"',"
                    + "'"+endereco+"');";
                    
            
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
    }
    
    public static void remover(String cpf){
        Connection con = ConnectionFactory.getConnection();
        Statement stmt;
        int tamanho = 0;
        try {
            stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM VENDEDORES WHERE CPF='"+cpf+"'");
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
        
    }
    
    public static ArrayList listarTodos() throws ParseException{
        Connection con = ConnectionFactory.getConnection();
        Statement stmt;
        ArrayList vendedores = null;
        
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM VENDEDORES");
            vendedores = new ArrayList();
            while(rs.next()){
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Calendar nascimento = Calendar.getInstance();
		nascimento.setTime(sdf.parse(rs.getString("NASCIMENTO")));
                Calendar contratacao = Calendar.getInstance();
		contratacao.setTime(sdf.parse(rs.getString("DATA_CONTRATACAO")));
                Funcionario v = new Funcionario(rs.getInt("ID"), rs.getString("NOME"), rs.getString("SOBRENOME"),  rs.getString("CPF"), rs.getInt("IDADE"), rs.getString("CELULAR"), rs.getString("EMAIL"), contratacao, nascimento, "RUA", "numero", "cep", "bairro", "cidade", "estado");
                if(rs.getString("ESTADO").equals("1"))
                    v.setAtivo(true);
                else
                    v.setAtivo(false);
                
                vendedores.add(v);
            }
            
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
        return vendedores;
    }
    
    public static int quantidadeBanco(){
        Connection con = ConnectionFactory.getConnection();
        Statement stmt;
        int tamanho = 0;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM VENDEDORES WHERE   ID = (SELECT MAX(ID)  FROM VENDEDORES)");
            tamanho = rs.getInt("ID");
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con);   
        }
        return tamanho;
    }
    
    
    /**
     * Busca pelo nome do cliente, diretamente do banco de dados.
     * @param nome
     * @return 
     */
    public static ArrayList buscaNome(String nome){
        Connection con = ConnectionFactory.getConnection();
        Statement stmt;
        ArrayList vendedores = null;
        
        try {
            stmt = con.createStatement();
            String sql = "SELECT * FROM VENDEDORES WHERE NOME LIKE '"+nome+"%' OR NOME LIKE '%"+nome+"%' OR SOBRENOME LIKE '"+nome+"%' OR SOBRENOME LIKE '%"+nome+"%' ";
            ResultSet rs = stmt.executeQuery(sql);
            vendedores = new ArrayList();
            while(rs.next()){
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Calendar nascimento = Calendar.getInstance();
                Calendar contratacao = Calendar.getInstance();
                try {
                    nascimento.setTime(sdf.parse(rs.getString("NASCIMENTO")));
                    contratacao.setTime(sdf.parse(rs.getString("DATA_CONTRATACAO")));
                } catch (ParseException ex) {
                    Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
   
                Funcionario v = new Funcionario(rs.getInt("ID"), rs.getString("NOME"), rs.getString("SOBRENOME"),  rs.getString("CPF"), rs.getInt("IDADE"), rs.getString("CELULAR"), rs.getString("EMAIL"), contratacao, nascimento, "RUA", "numero", "cep", "bairro", "cidade", "estado");
               
                if(rs.getString("ESTADO").equals("1"))
                    v.setAtivo(true);
                else
                    v.setAtivo(false);
                v.setId(rs.getInt("ID"));
                v.setEndereco(obterObjetoEndereco(rs.getString("ENDERECO")));
                vendedores.add(v);
            }
            
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
        return vendedores;
    }
    
    public static Funcionario buscaCPF(String cpf){
        Connection con = ConnectionFactory.getConnection();
        Statement stmt;
        Funcionario v = null;
        
        try {
            stmt = con.createStatement();
            String sql = "SELECT * FROM VENDEDORES WHERE CPF='"+cpf+"' ";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.isClosed())
                return null;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar nascimento = Calendar.getInstance();
            Calendar contratacao = Calendar.getInstance();
            try {
                nascimento.setTime(sdf.parse(rs.getString("NASCIMENTO")));
                contratacao.setTime(sdf.parse(rs.getString("DATA_CONTRATACAO")));
            } catch (ParseException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            v = new Funcionario(rs.getInt("ID"), rs.getString("NOME"), rs.getString("SOBRENOME"),  rs.getString("CPF"), rs.getInt("IDADE"), rs.getString("CELULAR"), rs.getString("EMAIL"), contratacao, nascimento, "RUA", "numero", "cep", "bairro", "cidade", "estado");
            if(rs.getString("ESTADO").equals("1"))
                v.setAtivo(true);
            else
                v.setAtivo(false);
            v.setEndereco(obterObjetoEndereco(rs.getString("ENDERECO")));
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
        return v;
    }
    
    public static Funcionario buscaID(int id){
        Connection con = ConnectionFactory.getConnection();
        Statement stmt;
        Funcionario v = null;
        
        try {
            stmt = con.createStatement();
            String sql = "SELECT * FROM VENDEDORES WHERE ID="+id+"";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.isClosed())
                return null;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar nascimento = Calendar.getInstance();
            Calendar contratacao = Calendar.getInstance();
            try {
                nascimento.setTime(sdf.parse(rs.getString("NASCIMENTO")));
                contratacao.setTime(sdf.parse(rs.getString("DATA_CONTRATACAO")));
            } catch (ParseException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            v = new Funcionario(rs.getInt("ID"), rs.getString("NOME"), rs.getString("SOBRENOME"),  rs.getString("CPF"), rs.getInt("IDADE"), rs.getString("CELULAR"), rs.getString("EMAIL"), contratacao, nascimento, "RUA", "numero", "cep", "bairro", "cidade", "estado");
            if(rs.getString("ESTADO").equals("1"))
                v.setAtivo(true);
            else
                v.setAtivo(false);
            v.setEndereco(obterObjetoEndereco(rs.getString("ENDERECO")));
            
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
        return v;
    }
    
    public static void edita(Funcionario v){
        Connection con = ConnectionFactory.getConnection();
        Statement stmt;
        
        int ativo = 0;
        if(v.isAtivo())
            ativo = 1;
        
        String nascimento = calendarParaString(v.getNascimento());
        String contratacao = calendarParaString(v.getContratacao());
        String endereco = v.getEndereco().toString();
        
        try {
            stmt = con.createStatement();
            String sql = "UPDATE VENDEDORES set "
                    + "NOME = '"+v.getNome()+"', "
                    + "ESTADO = "+ativo+", "
                    + "SOBRENOME = '"+v.getSobrenome()+"', "
                    + "CELULAR = '"+v.getCelular()+"', "
                    + "EMAIL = '"+v.getEmail()+"', "
                    + "IDADE = "+v.getIdade()+", "
                    + "NASCIMENTO = '"+nascimento+"', "
                    + "DATA_CONTRATACAO = '"+contratacao+"', "
                    + "CPF = '"+v.getCpf()+"', "
                    + "ENDERECO = '"+endereco+"' "
                    + "where ID="+v.getId()+";";
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
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

    private static String criarStringJSON(ArrayList o){
        JSONObject json = new JSONObject();
        json.put("uniqueArrays", new JSONArray(o));
        return json.toString();
    }
    
    private static ArrayList criarObjectoJSON(String s){
        JSONObject json = new JSONObject(s);
        JSONArray a = json.optJSONArray("uniqueArrays");
        return (ArrayList) a.toList();
    }
    
    private static Endereco obterObjetoEndereco(String endereco){
        if (endereco != null) {
            int index = endereco.indexOf("rua");
            String segmentada = endereco.substring(index, endereco.length()-1);
            String[] s = segmentada.split(",");
        
            Endereco end = new Endereco();
            end.setRua(s[0].substring(4));
            end.setNumero(s[1].substring(8));
            end.setCep(s[2].substring(5));
            end.setBairro(s[3].substring(8));
            end.setCidade(s[4].substring(8));
            end.setEstado(s[5].substring(8));
            return end;
        }
        return null;
    }
    
}
