/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people.manager.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import people.manager.controller.Controller;
import people.manager.controller.ControllerCliente;
import people.manager.exception.SemInternetException;
import people.manager.model.Cliente;
import people.manager.model.Usuario;
import people.manager.persistencia.ConnectionFactory;
import people.manager.tools.PDF;

/**
 *
 * @author marcos
 */
public class TelaInicial extends javax.swing.JFrame {

    private final Usuario user;

    /**
     * Creates new form TelaInicial
     *
     * @param title
     * @param user
     */
    public TelaInicial(String title, Usuario user) {
        super(title);
        this.user = user;
        botaoX();
        initComponents();
        Controller.lerProperties(); //<- Carrega o arquivo de configurações do disco; 
        verificacaoSistema();
        temAniversariante();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu6 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabelMensagem = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu11 = new javax.swing.JMenu();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu10 = new javax.swing.JMenu();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem19 = new javax.swing.JMenuItem();

        jMenu6.setText("jMenu6");

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/people/manager/view/8428_32x32.png"))); // NOI18N
        jButton2.setToolTipText("Busca de clientes");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/people/manager/view/6943_32x32.png"))); // NOI18N
        jButton3.setToolTipText("Abrir caixa para uma nova venda");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(659, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 752, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 404, Short.MAX_VALUE)
        );

        jMenu1.setText("Arquivo");

        jMenuItem4.setText("Importar BD");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem3.setText("Exportar BD");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Sair");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Clientes");

        jMenuItem7.setText("Cadastrar");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuItem8.setText("Buscar");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        jMenuItem5.setText("Remover");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem14.setText("Aniversáriantes");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem14);

        jMenuBar1.add(jMenu2);

        jMenu8.setText("Compras");

        jMenuItem6.setText("Vender");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem6);

        jMenuBar1.add(jMenu8);

        jMenu3.setText("Produtos");

        jMenuItem9.setText("Cadastrar");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem9);

        jMenuItem10.setText("Buscar");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem10);

        jMenu9.setText("Estoque");

        jMenuItem12.setText("Adic./Rem.");
        jMenuItem12.setToolTipText("Adicionar ou remover quantidade ao estoque.");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem12);

        jMenu3.add(jMenu9);

        jMenuBar1.add(jMenu3);

        jMenu11.setText("Agenda");

        jMenuItem20.setText("Agendar");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem20);

        jMenuItem21.setText("Gerenciar");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem21);

        jMenuBar1.add(jMenu11);

        jMenu4.setText("Funcionarios");

        jMenuItem11.setText("Cadastrar");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem11);

        jMenuItem13.setText("Buscar");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem13);

        jMenuBar1.add(jMenu4);

        jMenu10.setText("Usuario");

        jMenuItem15.setText("Novo");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem15);

        jMenuItem16.setText("Remover");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem16);

        jMenuItem17.setText("Trocar Senha");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem17);

        jMenuItem18.setText("Logout");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem18);

        jMenuBar1.add(jMenu10);

        jMenu5.setText("Ferramentas");

        jMenuItem22.setText("Gerar PDF");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem22);

        jMenuBar1.add(jMenu5);

        jMenu7.setText("Sobre");

        jMenuItem19.setText("Verificar Atualização");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem19);

        jMenuBar1.add(jMenu7);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelMensagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Controller.novoLog("fechou a aplicação");
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        JFileChooser jfc = new JFileChooser();
        jfc.showDialog(null, "Salvar");
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new FileNameExtensionFilter("Banco de Dados", "db"));
        jfc.setAcceptAllFileFilterUsed(false);
        jfc.showDialog(null, "Abrir");
        File arq = jfc.getSelectedFile();
        System.out.println(arq.getPath());
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        TelaBuscaCliente tb = new TelaBuscaCliente("Procurar Cliente");
        Main.guardarJanela(tb);
        tb.setLocationRelativeTo(null);
        tb.setVisible(true);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        JFrame telaCadastro = new TelaCadastroCliente("Cadastro Cliente");
        Main.guardarJanela(telaCadastro);
        telaCadastro.setLocationRelativeTo(null);
        telaCadastro.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        TelaRemoverCliente tr = new TelaRemoverCliente("Remover Cliente");
        Main.guardarJanela(tr);
        tr.setLocationRelativeTo(null);
        tr.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        TelaAniversariante ta = new TelaAniversariante("Aniversáriantes");
        Main.guardarJanela(ta);
        ta.setLocationRelativeTo(null);
        ta.setVisible(true);
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        if (this.user.getLevel() == 0) {
            JOptionPane.showMessageDialog(null, "Você não tem privilégios para remover um usuario.\nInforme a um administrador.");
        }
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        TelaNovoUsuario tnu = new TelaNovoUsuario("Novo Usuario", this.user);
        Main.guardarJanela(tnu);
        tnu.setLocationRelativeTo(null);
        tnu.setVisible(true);
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    public void reiniciar() {
        String comando = "java -jar " + new File("").getAbsolutePath() + "/home/marcos/NetBeansProjects/PeopleManager/dist/PeopleManager.jar";
        try {
            Process processo = Runtime.getRuntime().exec(comando);
        } catch (IOException ex) {
            Logger.getLogger(TelaInicial.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void logout() {
        TelaLogin tl = new TelaLogin("Login");
        tl.setLocationRelativeTo(null);
        tl.setVisible(true);
        Main.fecharTudo();
    }
    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        int resp = JOptionPane.showConfirmDialog(null, "Você quer realmente sair?");
        if (resp == 0) {
            Controller.novoLog("fez logout no sistema");
            logout();
            this.dispose();
        }
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        if (this.user.getLevel() == 0) {
            JOptionPane.showMessageDialog(null, "Você só pode alterar sua própria senha.");
            TelaTrocaPropriaSenha ttps = new TelaTrocaPropriaSenha("Alterar senha", this.user);
            Main.guardarJanela(ttps);
            ttps.setLocationRelativeTo(null);
            ttps.setVisible(true);
        } else {
            TelaTrocaSenha tts = new TelaTrocaSenha("Alterar Senha");
            Main.guardarJanela(tts);
            tts.setLocationRelativeTo(null);
            tts.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        TelaCadastroProduto tcp = new TelaCadastroProduto("Cadastro Produto");
        Main.guardarJanela(tcp);
        tcp.setLocationRelativeTo(null);
        tcp.setVisible(true);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        TelaBuscaProduto tbc = new TelaBuscaProduto("Busca de Produto");
        Main.guardarJanela(tbc);
        tbc.setLocationRelativeTo(null);
        tbc.setVisible(true);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        TelaCaixa tc = new TelaCaixa("Caixa");
        tc.setLocationRelativeTo(null);
        Main.guardarJanela(tc);
        tc.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jMenuItem8ActionPerformed(evt);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jMenuItem6ActionPerformed(evt);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        verificarAtualizacao();
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        TelaCadastroFuncionario tcf = new TelaCadastroFuncionario("Cadastro Funcionários");
        tcf.setLocationRelativeTo(null);
        tcf.setVisible(true);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        TelaAdicRemEstoque tare = new TelaAdicRemEstoque("Gerenciar Estoque");
        Main.guardarJanela(tare);
        tare.setLocationRelativeTo(null);
        tare.setVisible(true);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        TelaBuscaVendedores tbv = new TelaBuscaVendedores("Busca Funcionarios");
        Main.guardarJanela(tbv);
        tbv.setLocationRelativeTo(null);
        tbv.setVisible(true);
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        TelaNovoAgendamento tna = new TelaNovoAgendamento("Novo Agendamento");
        Main.guardarJanela(tna);
        tna.setLocationRelativeTo(null);
        tna.setVisible(true);
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        TelaGerenciarAgendamento tea = new TelaGerenciarAgendamento("Exibição de Agenda");
        Main.guardarJanela(tea);
        tea.setLocationRelativeTo(null);
        tea.setVisible(true);
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        new Thread() {
            @Override
            public void run() {
                PDF.criarPDFVendas();
            }
        }.start();
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    /**
     * É executado quando o botão X da janela é pressionado.
     */
    private void botaoX() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int resposta = JOptionPane.showConfirmDialog(null, "Deseja fechar o programa?", "Finalizar", JOptionPane.YES_NO_OPTION);
                if (resposta == 0) {
                    Controller.novoLog("fechou a aplicação");
                    System.exit(0);
                }

            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabelMensagem;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables

    /**
     * Verifica no servidor se há alguma nova atualização para ser baixada. Cria
     * banco de dados caso não exista.
     */
    private void verificacaoSistema() {
        new Thread() {
            @Override
            public void run() {
                //************** Testando a existencia do banco de dados **************
                if (!new File("database.db").exists()) {
                    ConnectionFactory.criarBanco();
                }
                if (Controller.getConfiguracao().getProperty("atualizar_inicializacao").equals("true")) {
                    verificarAtualizacao();
                }

            }
        }.start();
    }

    /**
     * Verifica se há uma nova atualização disponivel.
     */
    private void verificarAtualizacao() {
        new Thread() {
            @Override
            public void run() {
                //************** Testando Atualização de software ***********
                Controller.aguardar(5);
                try {
                    jLabelMensagem.setText("O sistema está verificando atualizações...");
                    Controller.aguardar(5);
                    Controller.temNet();
                    if (Controller.verificarAtualizacao()) {
                        jLabelMensagem.setText("Há uma nova atualização disponivel.");
                        Thread.sleep(3000);
                        jLabelMensagem.setText("Baixando nova atualização...");
                        Thread.sleep(3000);
                        if (Controller.instalarAtualizacao()) {
                            jLabelMensagem.setText("A atualização foi instalada, o programa deve ser reiniciado.");
                            Thread.sleep(3000);
                        } else {
                            jLabelMensagem.setText("Houve um erro na instalação da atualização. Tente mais tarde.");
                            Thread.sleep(3000);
                        }
                    } else {
                        jLabelMensagem.setText("O sistema já está atualizado.");
                        Thread.sleep(3000);

                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(TelaInicial.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SemInternetException ex) {
                    JOptionPane.showMessageDialog(null, "Não há conexão com a internet.\nImpossivel verificar atualização.");
                    Logger.getLogger(TelaInicial.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    jLabelMensagem.setText(null);
                }
            }
        }.start();
    }

    private void temAniversariante() {
        new Thread() {
            @Override
            public void run() {
                Controller.aguardar(10);
                ArrayList<Cliente> aniv = ControllerCliente.aniversariantesDia();
                if (aniv.size() >= 3) {
                    JOptionPane.showMessageDialog(null, "Há " + aniv.size() + " pessoas fazendo aniversário hoje. \nAbra a janela de aniversáriantes para ver mais informação.");
                } else if (aniv.size() == 2) {
                    JOptionPane.showMessageDialog(null, aniv.get(0).getNome() + " e " + aniv.get(1).getNome() + " estão fazendo aniversário hoje. \nAbra a janela de aniversáriantes para ver mais informação.");
                } else if (aniv.size() == 1) {
                    JOptionPane.showMessageDialog(null, aniv.get(0).getNome() + " está fazendo aniversário hoje. \nAbra a janela de aniversáriantes para ver mais informação.");
                } else {

                }
            }
        }.start();

    }
}
