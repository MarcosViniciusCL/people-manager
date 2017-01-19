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
import people.manager.model.Atendimento;
import people.manager.model.Endereco;

/**
 *
 * @author marcos
 */
public class AtendimentoDAO {

    /**
     * Adiciona um novo cliente no banco de dados.
     *
     * @param a
     */
    public static void create(Atendimento a) {

        a.setId(quantidadeBanco() + 1);

        Connection con = ConnectionFactory.getConnection();
        Statement stmt;

        String agendamento = calendarParaString(a.getDataAgendamento());
        String atendimento = calendarParaString(a.getDataAtendimento());

        try {
            stmt = con.createStatement();
            String sql = "INSERT INTO ATENDIMENTOS(ID, DATA_AGENDAMENTO, DATA_ATENDIMENTO, COMENTARIO, ID_CLIENTE, ID_FUNCIONARIO, VALOR, ATENDIMENTO) VALUES("
                    + "" + a.getId() + ","
                    + "'" + agendamento + "',"
                    + "'" + atendimento + "',"
                    + "'" + a.getComentario() + "',"
                    + "" + a.getIdCliente() + ","
                    + "" + a.getIdAtendente() + ","
                    + "" + a.getPreco() + ","
                    + "" + 0 + ");";

            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(AtendimentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
    }

    public static void remover(String id) {
        Connection con = ConnectionFactory.getConnection();
        Statement stmt;
        try {
            stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM ATENDIMENTOS WHERE ID='" + id + "'");
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }

    }

    public static ArrayList listarTodos() throws ParseException {
        Connection con = ConnectionFactory.getConnection();
        Statement stmt = null;
        ArrayList atendimentos = null;
        boolean atendido = false;

        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ATENDIMENTOS");
            atendimentos = new ArrayList();
            while (rs.next()) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Calendar agendamento = Calendar.getInstance();
                agendamento.setTime(sdf.parse(rs.getString("DATA_AGENDAMENTO")));
                Calendar atendimento = Calendar.getInstance();
                atendimento.setTime(sdf.parse(rs.getString("DATA_ATENDIMENTO")));
                Atendimento a = new Atendimento(rs.getString("COMENTARIO"), rs.getInt("ID_FUNCIONARIO"), rs.getInt("ID_CLIENTE"), atendimento, rs.getDouble("VALOR"));
                a.setDataAgendamento(agendamento);
                a.setId(rs.getInt("ID"));
                if (rs.getInt("ATENDIDO") == 1) {
                    atendido = true;
                }
                a.setAtendido(atendido);

                atendimentos.add(a);
            }

            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
        return atendimentos;
    }

    public static int quantidadeBanco() {
        Connection con = ConnectionFactory.getConnection();
        Statement stmt;
        int tamanho = 0;
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ATENDIMENTOS WHERE   ID = (SELECT MAX(ID)  FROM ATENDIMENTOS);");
            tamanho = rs.getInt("ID");
            stmt.close();
        } catch (SQLException ex) {
//            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con);

        }
        return tamanho;
    }

    public static Atendimento buscaID(int id) {
        Connection con = ConnectionFactory.getConnection();
        Statement stmt;
        Atendimento a = null;

        try {
            stmt = con.createStatement();
            String sql = "SELECT * FROM ATENDIMENTOS WHERE ID=" + id + "";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.isClosed()) {
                return null;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Calendar agendamento = Calendar.getInstance();
            agendamento.setTime(sdf.parse(rs.getString("DATA_AGENDAMENTO")));
            Calendar atendimento = Calendar.getInstance();
            atendimento.setTime(sdf.parse(rs.getString("DATA_ATENDIMENTO")));
            a = new Atendimento(rs.getString("COMENTARIO"), rs.getInt("ID_FUNCIONARIO"), rs.getInt("ID_CLIENTE"), atendimento, rs.getDouble("VALOR"));
            a.setDataAgendamento(agendamento);
            a.setId(rs.getInt("ID"));
            boolean atendido = false;
            if (rs.getInt("ATENDIMENTO") == 1) {
                atendido = true;
            }
            a.setAtendido(atendido);

            stmt.close();
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(AtendimentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con);
        }
        return a;
    }

    public static void edita(Atendimento a) {
        Connection con = ConnectionFactory.getConnection();
        Statement stmt;
        int atendido = 0;
        if (a.isAtendido()) {
            atendido = 1;
        }
        String agendamento = calendarParaString(a.getDataAgendamento());
        String atendimento = calendarParaString(a.getDataAtendimento());

        try {
            stmt = con.createStatement();
            String sql = "UPDATE ATENDIMENTOS set "
                    + "DATA_AGENDAMENTO = '" + agendamento + "', "
                    + "DATA_ATENDIMENTO = '" + atendimento + "', "
                    + "COMENTARIO = '" + a.getComentario() + "', "
                    + "ID_CLIENTE = " + a.getIdCliente() + ", "
                    + "ID_FUNCIONARIO = " + a.getIdAtendente() + ", "
                    + "VALOR = " + a.getPreco() + ", "
                    + "ATENDIMENTO = " + atendido + " "
                    + "where ID=" + a.getId() + ";";
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(AtendimentoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Receber um calendar e retorna o valor do dia e hora em string
     *
     * @param calendar
     * @return
     */
    private static String calendarParaString(Calendar calendar) {
        if (calendar == null) {
            return "NULO";
        }
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return s.format(calendar.getTime());
    }

    /**
     * Converte um ArrayList em uma stringo usando a bibliote JSON
     *
     * @param o
     * @return String
     */
    private static String criarStringJSON(ArrayList o) {
        JSONObject json = new JSONObject();
        json.put("uniqueArrays", new JSONArray(o));
        return json.toString();
    }

    /**
     * Converte uma string, criada pelo JSON, para ArrayList
     *
     * @param s
     * @return ArrayList
     */
    private static ArrayList criarObjectoJSON(String s) {
        JSONObject json = new JSONObject(s);
        JSONArray a = json.optJSONArray("uniqueArrays");
        return (ArrayList) a.toList();
    }

    private static Endereco obterObjetoEndereco(String endereco) {
        if (endereco != null) {
            int index = endereco.indexOf("rua");
            String segmentada = endereco.substring(index, endereco.length() - 1);
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
