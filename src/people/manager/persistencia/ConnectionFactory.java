/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people.manager.persistencia;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marcos
 */
public class ConnectionFactory {

    private static final String DRIVE = "org.sqlite.JDBC";
    private static final String URL = "jdbc:sqlite:database.db";   //<-- MYSQL mysql://locahost:3306/database
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() {

        try {
            Class.forName(DRIVE);
            return DriverManager.getConnection(URL);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return null;

    }

    public static void closeConnection(Connection con) {

        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void closeConnection(Connection con, Statement stmt) {
        closeConnection(con);
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void closeConnection(Connection con, Statement stmt, ResultSet rs) {
        closeConnection(con, stmt);
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void criarBanco() {
        Connection con = null;
        try {
            FileWriter arquivo = new FileWriter(new File("./database.db"));
            arquivo.close();
            con = getConnection();
            Statement stmt;
            stmt = con.createStatement();
            String sql = "CREATE TABLE ATENDIMENTOS (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "DATA_AGENDAMENTO TEXT, "
                    + "DATA_ATENDIMENTO TEXT, "
                    + "COMENTARIO TEXT, "
                    + "ID_CLIENTE INTEGER, "
                    + "ID_FUNCIONARIO INTEGER, "
                    + "VALOR REAL, "
                    + "ATENDIMENTO TEXT);";
            stmt.executeUpdate(sql);
            sql = "CREATE TABLE CLIENTES (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NOME TEXT, "
                    + "ATIVO INTEGER, "
                    + "SOBRENOME TEXT, "
                    + "CELULAR TEXT, "
                    + "TELEFONE TEXT, "
                    + "EMAIL TEXT, "
                    + "IDADE INTEGER, "
                    + "NASCIMENTO TEXT, "
                    + "CPF TEXT, "
                    + "RG TEXT, "
                    + "ENDERECO TEXT, "
                    + "HISTORICO TEXT, "
                    + "SALDODEVEDOR REAL, "
                    + "ULTIMOATENDIMENTO TEXT, "
                    + "PROXIMOATENDIMENTO TEXT);";
            stmt.executeUpdate(sql);
            sql = "CREATE TABLE PRODUTOS (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NOME TEXT, "
                    + "CODIGOBARRA TEXT, "
                    + "VALOR REAL, "
                    + "QUANTIDADE INTEGER, "
                    + "VALORCOMPRA REAL, "
                    + "CATEGORIA TEXT);";
            stmt.executeUpdate(sql);
            sql = "CREATE TABLE USUARIOS (USUARIO TEXT, "
                    + "SENHA TEXT, "
                    + "NIVEL INTEGER);";
            stmt.executeUpdate(sql);
            sql = "CREATE TABLE VENDAS (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "DATA_VENDA TEXT, "
                    + "COMENTARIO TEXT, "
                    + "ID_CLIENTE INTEGER, "
                    + "ID_VENDEDOR INTEGER, "
                    + "PRODUTOS TEXT, "
                    + "VALOR REAL, "
                    + "FORMA_PAGAMENTO TEXT, "
                    + "VALOR_RECEBIDO REAL, "
                    + "VALOR_TROCO REAL, "
                    + "ESTADO TEXT);";
            stmt.executeUpdate(sql);
            sql = "CREATE TABLE VENDEDORES (ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NOME TEXT, "
                    + "ESTADO INTEGER, "
                    + "SOBRENOME TEXT, "
                    + "CELULAR TEXT, "
                    + "EMAIL TEXT, "
                    + "IDADE INTEGER, "
                    + "NASCIMENTO TEXT, "
                    + "DATA_CONTRATACAO TEXT, "
                    + "CPF TEXT, "
                    + "ENDERECO TEXT);";
            stmt.executeUpdate(sql);
            stmt.close();

        } catch (IOException | SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(con != null)
                closeConnection(con);
        }

    }
}
