/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people.manager.controller;

import java.awt.Font;
import people.manager.exception.SemInternetException;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import people.manager.model.Produto;
import people.manager.model.Usuario;
import people.manager.persistencia.ArquivoDAO;
import people.manager.persistencia.ArquivoZIP;
import people.manager.persistencia.GerenciaProperties;
import people.manager.persistencia.ProdutoDAO;
import people.manager.persistencia.Update;
import people.manager.view.Main;
import people.manager.view.TelaInicial;
import people.manager.view.TelaTrocaPropriaSenha;

/**
 *
 * @author marcos
 */
public class Controller {

    private static Usuario user;
    private static ArrayList<String> arquivosErro;
    private static Properties configuracoes;

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
     * @throws people.manager.exception.SemInternetException
     */
    public static boolean temNet() throws SemInternetException {
        InetAddress endereco = null;
        try {
            endereco = InetAddress.getByName("www.google.com");
        } catch (UnknownHostException ex) {
            throw new SemInternetException();
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

    public static String calendarParaString(Calendar calendar) {
        if (calendar == null) {
            return "NULO";
        }
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return s.format(calendar.getTime());
    }

    public static Calendar stringParaCalendar(String data) {
        Calendar dataR = Calendar.getInstance();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            dataR.setTime(sdf.parse(data));
        } catch (ParseException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataR;
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
                        if (!categorias.contains(p.getCategoria())) {
                            categorias.add(p.getCategoria());
                        }
                    }
                    ArquivoDAO.remover("categ");
                    for (String str : categorias) {
                        ArquivoDAO.escritor("categ", str.toUpperCase() + ";");
                    }

                }

                if (s.equals("log")) {
                    System.out.println("Copiando backup");
                }
                if (!s.equals("categ")) {
                    ArquivoDAO.escritor(s, "****************************** ARQUIVO RECUPERADO ****************************");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        Controller.arquivosErro.clear();
    }

    public static boolean verificarAtualizacao() {
        return Update.verificarAtualização();
    }

    public static boolean baixarAtualixacao() {
        return Update.baixarAtualizacao();
    }

    public static boolean instalarAtualizacao() {
        try {
            ArquivoZIP.unzip(new File("PeopleManager.zip"), new File("PeopleManagerAt"));
            File jarOriginal = new File("PeopleManager.jar");
            jarOriginal.delete();
            ArquivoDAO.copiar("PeopleManagerAt/PeopleManager.jar", "PeopleManager.jar");
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Reinicia a aplicação de acordo ao tempo especificado.
     *
     * @param seg - Tempo em segundos para a aplicação reiniciar;
     * @param log - Ação que causou o reiniciamento da aplicação;
     */
    public static void reiniciar(int seg, String log) {
        new Thread() {
            @Override
            public void run() {
                JOptionPane.showMessageDialog(null, "A aplicação será reiniciada em " + seg + " segundos.");
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                Controller.aguardar(seg);
                TelaInicial ti = Main.getTI();
                Controller.novoLog(log);
                ti.logout();
            }
        }.start();
    }

    /**
     * Faz o programa esperar por um tempo especificado.
     *
     * @param seg
     */
    public static void aguardar(int seg) {
        int tempo = Integer.parseInt(seg + "000");
        try {
            Thread.sleep(tempo);
        } catch (InterruptedException ex) {
            Logger.getLogger(TelaTrocaPropriaSenha.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Ler o arquivo de configuração do disco e como atributo da classe;
     */
    public static void lerProperties() {
        try {
            Controller.configuracoes = GerenciaProperties.getProp("conf.properties");
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Retorna as configurações lidas;
     *
     * @return Properties
     */
    public static Properties getConfiguracao() {
        return Controller.configuracoes;
    }
    
    public static boolean senhaInterface() {
        

        //Criando JFrame;
        JDialog jf = new JDialog();
        jf.setModal(true);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setSize(510, 80);
        jf.setLocationRelativeTo(null);

        JPanel jpanel = new JPanel();
        jpanel.setLayout(null);
        JLabel jlabel = new JLabel("Senha:");
        jlabel.setBounds(10, 10, 50, 20);
        JPasswordField senha = new JPasswordField(250);
        senha.setFont(new Font("Tahoma", 0, 11));
        senha.setBounds(60, 10, 240, 20);
        jpanel.add(jlabel);
        jpanel.add(senha);

        jf.add(jpanel);

        //Criando botoes
        JButton botaoCancelar = new JButton("Cancelar");
        botaoCancelar.setBounds(400, 10, 96, 21);
        JButton botaoBusca = new JButton("OK");
        botaoBusca.setBounds(300, 10, 96, 21);
        jpanel.add(botaoBusca);
        jpanel.add(botaoCancelar);
        
        
        
        //Eventos do Jframe
        botaoBusca.addActionListener((java.awt.event.ActionEvent e) -> {
            getUser().testarSenha(new String(senha.getPassword()));
        });
        botaoCancelar.addActionListener((java.awt.event.ActionEvent e) -> {
            jf.dispose();
        });
        
        jf.setVisible(true);
        return Controller.resp_senha;
    }
    private static boolean resp_senha;

    /**
     * Cria arquivo que evita que o programa seja executado mais de uma instancia.
     */
    public static void criarLock(){
        ArquivoDAO.novoArquivo("./lock");
    }
    
    /**
     * Remover arquivo que impede a execussão de mais de uma instancia do programa.
     */
    public static void removerLock(){
        ArquivoDAO.remover("./lock");
    }
}

