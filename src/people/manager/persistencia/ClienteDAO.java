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
import people.manager.model.Cliente;
import org.json.*;
import people.manager.model.Endereco;

/**
 *
 * @author marcos
 */
public class ClienteDAO {
    /**
     * Adiciona um novo cliente no banco de dados.
     * @param c 
     */
    public static void create(Cliente c){
        
//        c.setId(quantidadeBanco()+1);
        
        Connection con = ConnectionFactory.getConnection();
        Statement stmt;
        
        int ativo = 0;
        if(c.isAtivo())
            ativo = 1;
        
        String nascimento = calendarParaString(c.getNascimento());
        String ultAten = calendarParaString(c.getUltimoAtendimento());
        String proxAten = calendarParaString(c.getProximoAtendimento());
        String historico = criarStringJSON(c.getHistorico());
        String endereco = c.getEnd().toString();
        
        try {
            stmt = con.createStatement();
            String sql = "INSERT INTO CLIENTES(nome, ativo, sobrenome, celular, telefone, email, idade, nascimento, cpf, rg, endereco, historico, saldodevedor, ultimoatendimento, proximoatendimento) VALUES("
                    + "'"+c.getNome()+"',"
                    + ""+ativo+","
                    + "'"+c.getSobrenome()+"',"
                    + "'"+c.getCelular()+"',"
                    + "'"+c.getTelefone()+"',"
                    + "'"+c.getEmail()+"',"
                    + ""+c.getIdade()+","
                    + "'"+nascimento+"',"
                    + "'"+c.getCpf()+"',"
                    + "'"+c.getRg()+"',"
                    + "'"+endereco+"',"
                    + "'"+historico+"',"
                    + ""+c.getSaldoDevedor()+","
                    + "'"+ultAten+"',"
                    + "'"+proxAten+"');";
            
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            stmt.executeUpdate("DELETE FROM CLIENTES WHERE CPF='"+cpf+"'");
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
        
    }
    
    public static ArrayList listarTodos() throws ParseException{
        Connection con = ConnectionFactory.getConnection();
        Statement stmt;
        ArrayList clientes = null;
        
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CLIENTES");
            clientes = new ArrayList();
            while(rs.next()){
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Calendar nascimento = Calendar.getInstance();
		nascimento.setTime(sdf.parse(rs.getString("NASCIMENTO")));
                Cliente c = new Cliente(rs.getString("NOME"), rs.getString("SOBRENOME"), rs.getString("CELULAR"), rs.getString("TELEFONE"), rs.getString("EMAIL"), nascimento, rs.getString("CPF"), rs.getString("RG"), "RUA", "numero", "cep", "bairro", "cidade", "estado");
                if(rs.getString("ATIVO").equals("1"))
                    c.setAtivo(true);
                else
                    c.setAtivo(false);
                clientes.add(c);
            }
            
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
        return clientes;
    }
    
    public static int quantidadeBanco(){
        Connection con = ConnectionFactory.getConnection();
        Statement stmt;
        int tamanho = 0;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT seq FROM sqlite_sequence WHERE name ='CLIENTES'");
            tamanho = rs.getInt("seq");
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        ArrayList clientes = null;
        
        try {
            stmt = con.createStatement();
            String sql = "SELECT * FROM CLIENTES WHERE NOME LIKE '"+nome+"%' OR NOME LIKE '%"+nome+"%' OR SOBRENOME LIKE '"+nome+"%' OR SOBRENOME LIKE '%"+nome+"%' ";
            ResultSet rs = stmt.executeQuery(sql);
            clientes = new ArrayList();
            while(rs.next()){
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Calendar nascimento = Calendar.getInstance();
                try {
                    nascimento.setTime(sdf.parse(rs.getString("NASCIMENTO")));
                } catch (ParseException ex) {
                    Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                Cliente c = new Cliente(rs.getString("NOME"), rs.getString("SOBRENOME"), rs.getString("CELULAR"), rs.getString("TELEFONE"), rs.getString("EMAIL"), nascimento, rs.getString("CPF"), rs.getString("RG"), "RUA", "numero", "cep", "bairro", "cidade", "estado");
                if(rs.getString("ATIVO").equals("1"))
                    c.setAtivo(true);
                else
                    c.setAtivo(false);
                c.setId(rs.getInt("ID"));
                c.setEnd(obterObjetoEndereco(rs.getString("ENDERECO")));
                c.setHistorico(criarObjectoJSON(rs.getString("HISTORICO")));
                clientes.add(c);
            }
            
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
        return clientes;
    }
    
    public static Cliente buscaCPF(String cpf){
        Connection con = ConnectionFactory.getConnection();
        Statement stmt;
        Cliente c = null;
        if(cpf == null)
            return null;
        
        try {
            stmt = con.createStatement();
            String sql = "SELECT * FROM CLIENTES WHERE CPF='"+cpf+"' ";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.isClosed())
                return null;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar nascimento = Calendar.getInstance();
            try {
                nascimento.setTime(sdf.parse(rs.getString("NASCIMENTO")));
            } catch (ParseException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            c = new Cliente(rs.getString("NOME"), rs.getString("SOBRENOME"), rs.getString("CELULAR"), rs.getString("TELEFONE"), rs.getString("EMAIL"), nascimento, rs.getString("CPF"), rs.getString("RG"), "RUA", "numero", "cep", "bairro", "cidade", "estado");
            if(rs.getString("ATIVO").equals("1"))
                c.setAtivo(true);
            else
                c.setAtivo(false);
            c.setEnd(obterObjetoEndereco(rs.getString("ENDERECO")));
            c.setHistorico(criarObjectoJSON(rs.getString("HISTORICO")));
            c.setId(rs.getInt("ID"));
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
        return c;
    }
    public static Cliente buscaRG(String rg) {
        Connection con = ConnectionFactory.getConnection();
        Statement stmt;
        Cliente c = null;
        if(rg == null)
            return null;
        
        try {
            stmt = con.createStatement();
            String sql = "SELECT * FROM CLIENTES WHERE RG='"+rg+"' ";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.isClosed())
                return null;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar nascimento = Calendar.getInstance();
            try {
                nascimento.setTime(sdf.parse(rs.getString("NASCIMENTO")));
            } catch (ParseException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            c = new Cliente(rs.getString("NOME"), rs.getString("SOBRENOME"), rs.getString("CELULAR"), rs.getString("TELEFONE"), rs.getString("EMAIL"), nascimento, rs.getString("CPF"), rs.getString("RG"), "RUA", "numero", "cep", "bairro", "cidade", "estado");
            if(rs.getString("ATIVO").equals("1"))
                c.setAtivo(true);
            else
                c.setAtivo(false);
            c.setEnd(obterObjetoEndereco(rs.getString("ENDERECO")));
            c.setHistorico(criarObjectoJSON(rs.getString("HISTORICO")));
            c.setId(rs.getInt("ID"));
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
        return c;
    }
    
    public static Cliente buscaID(int id){
        Connection con = ConnectionFactory.getConnection();
        Statement stmt;
        Cliente c = null;
        
        try {
            stmt = con.createStatement();
            String sql = "SELECT * FROM CLIENTES WHERE ID="+id+"";
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.isClosed())
                return null;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar nascimento = Calendar.getInstance();
            try {
                nascimento.setTime(sdf.parse(rs.getString("NASCIMENTO")));
            } catch (ParseException ex) {
                Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            c = new Cliente(rs.getString("NOME"), rs.getString("SOBRENOME"), rs.getString("CELULAR"), rs.getString("TELEFONE"), rs.getString("EMAIL"), nascimento, rs.getString("CPF"), rs.getString("RG"), "RUA", "numero", "cep", "bairro", "cidade", "estado");
            if(rs.getString("ATIVO").equals("1"))
                c.setAtivo(true);
            else
                c.setAtivo(false);
            c.setEnd(obterObjetoEndereco(rs.getString("ENDERECO")));
            c.setHistorico(criarObjectoJSON(rs.getString("HISTORICO")));
            c.setId(rs.getInt("ID"));
            
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
        return c;
    }
    
    public static void edita(Cliente c){
        Connection con = ConnectionFactory.getConnection();
        Statement stmt;
        
        int ativo = 0;
        if(c.isAtivo())
            ativo = 1;
        
        String nascimento = calendarParaString(c.getNascimento());
        String ultAten = calendarParaString(c.getUltimoAtendimento());
        String proxAten = calendarParaString(c.getProximoAtendimento());
        String historico = criarStringJSON(c.getHistorico());
        String endereco = c.getEnd().toString();
        
        try {
            stmt = con.createStatement();
            String sql = "UPDATE CLIENTES set "
                    + "NOME = '"+c.getNome()+"', "
                    + "ATIVO = "+ativo+", "
                    + "SOBRENOME = '"+c.getSobrenome()+"', "
                    + "CELULAR = '"+c.getCelular()+"', "
                    + "TELEFONE = '"+c.getTelefone()+"', "
                    + "EMAIL = '"+c.getEmail()+"', "
                    + "IDADE = "+c.getIdade()+", "
                    + "NASCIMENTO = '"+nascimento+"', "
                    + "CPF = '"+c.getCpf()+"', "
                    + "RG = '"+c.getRg()+"', "
                    + "ENDERECO = '"+endereco+"', "
                    + "HISTORICO = '"+historico+"', "
                    + "SALDODEVEDOR = "+c.getSaldoDevedor()+", "
                    + "ULTIMOATENDIMENTO = '"+ultAten+"', "
                    + "PROXIMOATENDIMENTO = '"+proxAten+"' "
                    + "where ID="+c.getId()+";";
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
