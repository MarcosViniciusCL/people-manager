/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people.manager.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import people.manager.model.Produto;
import people.manager.model.Usuario;
import people.manager.persistencia.ArquivoDAO;
import people.manager.persistencia.ProdutoDAO;
import people.manager.persistencia.Update;

/**
 *
 * @author marcos
 */
public class Controller {

    private static Usuario user;
    private static ArrayList<String> arquivosErro;

    /**
     * Cria um codigo hash para cada String de entrada.
     *
     * @param senha
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String criaHash(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        String senhaCod = senha;

        MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
        byte messageDigestSenha[] = algorithm.digest(senhaCod.getBytes("UTF-8"));

        StringBuilder hexStringSenha = new StringBuilder();
        for (byte b : messageDigestSenha) {
            hexStringSenha.append(String.format("%02X", 0xFF & b));
        }
        return hexStringSenha.toString();
    }

    /**
     * Verifica se há conexão com a internet
     *
     * @return boolean
     */
    public static boolean temNet() {
        InetAddress endereco = null;
        try {
            endereco = InetAddress.getByName("www.google.com");
        } catch (UnknownHostException ex) {
            return false;
        }
        return true;

    }

    /**
     * Cria um novo log no arquivo.
     *
     * @param log
     */
    public static void novoLog(String log) {
        String gravar = calendarParaString(Calendar.getInstance()) + "-> Usuario '" + user.getUser() + "' " + log + ";";
        try {
            ArquivoDAO.escritor("log", gravar);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Cria um novo log de erro no arquivo.
     *
     * @param log
     * @param erro
     */
    public static void novoLog(String log, String erro) {
        if (erro.toUpperCase().equals("ERRO")) {
            String gravar = calendarParaString(Calendar.getInstance()) + "-> " + log.toUpperCase() + ";";
            try {
                ArquivoDAO.escritor("log", gravar);
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private static String calendarParaString(Calendar calendar) {
        if (calendar == null) {
            return "NULO";
        }
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return s.format(calendar.getTime());
    }

    public static void setUser(Usuario user) {
        Controller.user = user;
    }

    public static Usuario getUser() {
        return Controller.user;
    }

    /**
     * Verifica se algum arquivo do sistema foi alterado ilegalmente.
     *
     * @return boolean
     */
    public static boolean testeSistema() {
        arquivosErro = new ArrayList();

        if (ArquivoDAO.isModificado("log")) {
            arquivosErro.add("log");
        }
        if (ArquivoDAO.isModificado("categ")) {
            arquivosErro.add("categ");
        }

        if (arquivosErro.size() > 0) {
            Controller.novoLog("OS ARQUIVOS DO SISTEMA FORAM ALTERADOS, A APLICAÇÃO VAI TENTAR RECUMPERA-LOS", "ERRO");
            return true;
        }
        return false;
    }

    public static void recuperarSistema() {
        ArrayList<String> categorias = new ArrayList();
        try {
            for (String s : Controller.arquivosErro) {
                //se for o arquivo de categorias o sistema vai recria-lo a partir dos produtos cadastrados.
                if (s.equals("categ")) {
                    ArrayList<Produto> a = ProdutoDAO.listarTodos();
                    for (Produto p : a) { //Verificando quais categorias devem ser adicionadas
                        if (!categorias.contains(p.getCategoria()))
                            categorias.add(p.getCategoria());
                    }
                    ArquivoDAO.remover("categ");
                    for (String str : categorias){
                        ArquivoDAO.escritor("categ", str.toUpperCase()+";");
                    }

                }
                
                if (s.equals("log")) {
                    System.out.println("Copiando backup");
                }
                if(!s.equals("categ"))
                    ArquivoDAO.escritor(s, "****************************** ARQUIVO RECUPERADO ****************************");
            }
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        Controller.arquivosErro.clear();
    }
    
    public static boolean verificarAtualizacao(){
        return Update.verificarAtualização();
    }
    
}
