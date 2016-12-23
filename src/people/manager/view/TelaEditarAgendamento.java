/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people.manager.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import people.manager.controller.Controller;
import people.manager.controller.ControllerAtendimento;
import people.manager.controller.ControllerCliente;
import people.manager.controller.ControllerFuncionario;
import people.manager.exception.AtendimentoNaoEncontradoException;
import people.manager.exception.ClienteNaoEncontradoException;
import people.manager.exception.ImpossivelRemoverException;
import people.manager.exception.VendedorNaoEncontradoException;
import people.manager.model.Atendimento;
import people.manager.model.Cliente;
import people.manager.model.Funcionario;

/**
 *
 * @author marcos
 */
public class TelaEditarAgendamento extends javax.swing.JFrame {

    private Cliente cliente;
    private Funcionario funcionario;
    private final Atendimento agend;

    /**
     * Creates new form TelaNovoAgendamento
     *
     * @param title
     * @param agend
     */
    public TelaEditarAgendamento(String title, Atendimento agend) {
        super(title);
        initComponents();
        this.agend = agend;
        jTextFieldIDAtendimento.setText(agend.getId()+"");
        jTextFieldIDCliente.setText(agend.getIdCliente()+"");
        jTextFieldIDClienteActionPerformed(null);
        jTextFieldIDFunc.setText(agend.getIdAtendente()+"");
        jTextFieldIDFuncActionPerformed(null);
        jTextFieldValor.setText(String.format("%.2f", agend.getPreco()));
        jTextAreaComentario.setText(agend.getComentario());
        jDateChooser1.setCalendar(agend.getDataAtendimento());
        jSpinnerHora.getModel().setValue(Controller.calendarParaString(agend.getDataAtendimento()).substring(11, 16));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldIDCliente = new javax.swing.JTextField();
        jTextFieldNomeCliente = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jSpinnerHora = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldIDFunc = new javax.swing.JTextField();
        jTextFieldNomeFuncionario = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaComentario = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldValor = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldIDAtendimento = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("ID. Cliente:");

        jTextFieldIDCliente.setEditable(false);
        jTextFieldIDCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIDClienteActionPerformed(evt);
            }
        });

        jTextFieldNomeCliente.setEditable(false);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/people/manager/view/search(1).png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Data. Aten:");

        jSpinnerHora.setModel(new javax.swing.SpinnerListModel(new String[] {"00:00", "00:01", "00:02", "00:03", "00:04", "00:05", "00:06", "00:07", "00:08", "00:09", "00:10", "00:11", "00:12", "00:13", "00:14", "00:15", "00:16", "00:17", "00:18", "00:19", "00:20", "00:21", "00:22", "00:23", "00:24", "00:25", "00:26", "00:27", "00:28", "00:29", "00:30", "00:31", "00:32", "00:33", "00:34", "00:35", "00:36", "00:37", "00:38", "00:39", "00:40", "00:41", "00:42", "00:43", "00:44", "00:45", "00:46", "00:47", "00:48", "00:49", "00:50", "00:51", "00:52", "00:53", "00:54", "00:55", "00:56", "00:57", "00:58", "00:59", "01:00", "01:01", "01:02", "01:03", "01:04", "01:05", "01:06", "01:07", "01:08", "01:09", "01:10", "01:11", "01:12", "01:13", "01:14", "01:15", "01:16", "01:17", "01:18", "01:19", "01:20", "01:21", "01:22", "01:23", "01:24", "01:25", "01:26", "01:27", "01:28", "01:29", "01:30", "01:31", "01:32", "01:33", "01:34", "01:35", "01:36", "01:37", "01:38", "01:39", "01:40", "01:41", "01:42", "01:43", "01:44", "01:45", "01:46", "01:47", "01:48", "01:49", "01:50", "01:51", "01:52", "01:53", "01:54", "01:55", "01:56", "01:57", "01:58", "01:59", "02:00", "02:01", "02:02", "02:03", "02:04", "02:05", "02:06", "02:07", "02:08", "02:09", "02:10", "02:11", "02:12", "02:13", "02:14", "02:15", "02:16", "02:17", "02:18", "02:19", "02:20", "02:21", "02:22", "02:23", "02:24", "02:25", "02:26", "02:27", "02:28", "02:29", "02:30", "02:31", "02:32", "02:33", "02:34", "02:35", "02:36", "02:37", "02:38", "02:39", "02:40", "02:41", "02:42", "02:43", "02:44", "02:45", "02:46", "02:47", "02:48", "02:49", "02:50", "02:51", "02:52", "02:53", "02:54", "02:55", "02:56", "02:57", "02:58", "02:59", "03:00", "03:01", "03:02", "03:03", "03:04", "03:05", "03:06", "03:07", "03:08", "03:09", "03:10", "03:11", "03:12", "03:13", "03:14", "03:15", "03:16", "03:17", "03:18", "03:19", "03:20", "03:21", "03:22", "03:23", "03:24", "03:25", "03:26", "03:27", "03:28", "03:29", "03:30", "03:31", "03:32", "03:33", "03:34", "03:35", "03:36", "03:37", "03:38", "03:39", "03:40", "03:41", "03:42", "03:43", "03:44", "03:45", "03:46", "03:47", "03:48", "03:49", "03:50", "03:51", "03:52", "03:53", "03:54", "03:55", "03:56", "03:57", "03:58", "03:59", "04:00", "04:01", "04:02", "04:03", "04:04", "04:05", "04:06", "04:07", "04:08", "04:09", "04:10", "04:11", "04:12", "04:13", "04:14", "04:15", "04:16", "04:17", "04:18", "04:19", "04:20", "04:21", "04:22", "04:23", "04:24", "04:25", "04:26", "04:27", "04:28", "04:29", "04:30", "04:31", "04:32", "04:33", "04:34", "04:35", "04:36", "04:37", "04:38", "04:39", "04:40", "04:41", "04:42", "04:43", "04:44", "04:45", "04:46", "04:47", "04:48", "04:49", "04:50", "04:51", "04:52", "04:53", "04:54", "04:55", "04:56", "04:57", "04:58", "04:59", "05:00", "05:01", "05:02", "05:03", "05:04", "05:05", "05:06", "05:07", "05:08", "05:09", "05:10", "05:11", "05:12", "05:13", "05:14", "05:15", "05:16", "05:17", "05:18", "05:19", "05:20", "05:21", "05:22", "05:23", "05:24", "05:25", "05:26", "05:27", "05:28", "05:29", "05:30", "05:31", "05:32", "05:33", "05:34", "05:35", "05:36", "05:37", "05:38", "05:39", "05:40", "05:41", "05:42", "05:43", "05:44", "05:45", "05:46", "05:47", "05:48", "05:49", "05:50", "05:51", "05:52", "05:53", "05:54", "05:55", "05:56", "05:57", "05:58", "05:59", "06:00", "06:01", "06:02", "06:03", "06:04", "06:05", "06:06", "06:07", "06:08", "06:09", "06:10", "06:11", "06:12", "06:13", "06:14", "06:15", "06:16", "06:17", "06:18", "06:19", "06:20", "06:21", "06:22", "06:23", "06:24", "06:25", "06:26", "06:27", "06:28", "06:29", "06:30", "06:31", "06:32", "06:33", "06:34", "06:35", "06:36", "06:37", "06:38", "06:39", "06:40", "06:41", "06:42", "06:43", "06:44", "06:45", "06:46", "06:47", "06:48", "06:49", "06:50", "06:51", "06:52", "06:53", "06:54", "06:55", "06:56", "06:57", "06:58", "06:59", "07:00", "07:01", "07:02", "07:03", "07:04", "07:05", "07:06", "07:07", "07:08", "07:09", "07:10", "07:11", "07:12", "07:13", "07:14", "07:15", "07:16", "07:17", "07:18", "07:19", "07:20", "07:21", "07:22", "07:23", "07:24", "07:25", "07:26", "07:27", "07:28", "07:29", "07:30", "07:31", "07:32", "07:33", "07:34", "07:35", "07:36", "07:37", "07:38", "07:39", "07:40", "07:41", "07:42", "07:43", "07:44", "07:45", "07:46", "07:47", "07:48", "07:49", "07:50", "07:51", "07:52", "07:53", "07:54", "07:55", "07:56", "07:57", "07:58", "07:59", "08:00", "08:01", "08:02", "08:03", "08:04", "08:05", "08:06", "08:07", "08:08", "08:09", "08:10", "08:11", "08:12", "08:13", "08:14", "08:15", "08:16", "08:17", "08:18", "08:19", "08:20", "08:21", "08:22", "08:23", "08:24", "08:25", "08:26", "08:27", "08:28", "08:29", "08:30", "08:31", "08:32", "08:33", "08:34", "08:35", "08:36", "08:37", "08:38", "08:39", "08:40", "08:41", "08:42", "08:43", "08:44", "08:45", "08:46", "08:47", "08:48", "08:49", "08:50", "08:51", "08:52", "08:53", "08:54", "08:55", "08:56", "08:57", "08:58", "08:59", "09:00", "09:01", "09:02", "09:03", "09:04", "09:05", "09:06", "09:07", "09:08", "09:09", "09:10", "09:11", "09:12", "09:13", "09:14", "09:15", "09:16", "09:17", "09:18", "09:19", "09:20", "09:21", "09:22", "09:23", "09:24", "09:25", "09:26", "09:27", "09:28", "09:29", "09:30", "09:31", "09:32", "09:33", "09:34", "09:35", "09:36", "09:37", "09:38", "09:39", "09:40", "09:41", "09:42", "09:43", "09:44", "09:45", "09:46", "09:47", "09:48", "09:49", "09:50", "09:51", "09:52", "09:53", "09:54", "09:55", "09:56", "09:57", "09:58", "09:59", "10:00", "10:01", "10:02", "10:03", "10:04", "10:05", "10:06", "10:07", "10:08", "10:09", "10:10", "10:11", "10:12", "10:13", "10:14", "10:15", "10:16", "10:17", "10:18", "10:19", "10:20", "10:21", "10:22", "10:23", "10:24", "10:25", "10:26", "10:27", "10:28", "10:29", "10:30", "10:31", "10:32", "10:33", "10:34", "10:35", "10:36", "10:37", "10:38", "10:39", "10:40", "10:41", "10:42", "10:43", "10:44", "10:45", "10:46", "10:47", "10:48", "10:49", "10:50", "10:51", "10:52", "10:53", "10:54", "10:55", "10:56", "10:57", "10:58", "10:59", "11:00", "11:01", "11:02", "11:03", "11:04", "11:05", "11:06", "11:07", "11:08", "11:09", "11:10", "11:11", "11:12", "11:13", "11:14", "11:15", "11:16", "11:17", "11:18", "11:19", "11:20", "11:21", "11:22", "11:23", "11:24", "11:25", "11:26", "11:27", "11:28", "11:29", "11:30", "11:31", "11:32", "11:33", "11:34", "11:35", "11:36", "11:37", "11:38", "11:39", "11:40", "11:41", "11:42", "11:43", "11:44", "11:45", "11:46", "11:47", "11:48", "11:49", "11:50", "11:51", "11:52", "11:53", "11:54", "11:55", "11:56", "11:57", "11:58", "11:59", "12:00", "12:01", "12:02", "12:03", "12:04", "12:05", "12:06", "12:07", "12:08", "12:09", "12:10", "12:11", "12:12", "12:13", "12:14", "12:15", "12:16", "12:17", "12:18", "12:19", "12:20", "12:21", "12:22", "12:23", "12:24", "12:25", "12:26", "12:27", "12:28", "12:29", "12:30", "12:31", "12:32", "12:33", "12:34", "12:35", "12:36", "12:37", "12:38", "12:39", "12:40", "12:41", "12:42", "12:43", "12:44", "12:45", "12:46", "12:47", "12:48", "12:49", "12:50", "12:51", "12:52", "12:53", "12:54", "12:55", "12:56", "12:57", "12:58", "12:59", "13:00", "13:01", "13:02", "13:03", "13:04", "13:05", "13:06", "13:07", "13:08", "13:09", "13:10", "13:11", "13:12", "13:13", "13:14", "13:15", "13:16", "13:17", "13:18", "13:19", "13:20", "13:21", "13:22", "13:23", "13:24", "13:25", "13:26", "13:27", "13:28", "13:29", "13:30", "13:31", "13:32", "13:33", "13:34", "13:35", "13:36", "13:37", "13:38", "13:39", "13:40", "13:41", "13:42", "13:43", "13:44", "13:45", "13:46", "13:47", "13:48", "13:49", "13:50", "13:51", "13:52", "13:53", "13:54", "13:55", "13:56", "13:57", "13:58", "13:59", "14:00", "14:01", "14:02", "14:03", "14:04", "14:05", "14:06", "14:07", "14:08", "14:09", "14:10", "14:11", "14:12", "14:13", "14:14", "14:15", "14:16", "14:17", "14:18", "14:19", "14:20", "14:21", "14:22", "14:23", "14:24", "14:25", "14:26", "14:27", "14:28", "14:29", "14:30", "14:31", "14:32", "14:33", "14:34", "14:35", "14:36", "14:37", "14:38", "14:39", "14:40", "14:41", "14:42", "14:43", "14:44", "14:45", "14:46", "14:47", "14:48", "14:49", "14:50", "14:51", "14:52", "14:53", "14:54", "14:55", "14:56", "14:57", "14:58", "14:59", "15:00", "15:01", "15:02", "15:03", "15:04", "15:05", "15:06", "15:07", "15:08", "15:09", "15:10", "15:11", "15:12", "15:13", "15:14", "15:15", "15:16", "15:17", "15:18", "15:19", "15:20", "15:21", "15:22", "15:23", "15:24", "15:25", "15:26", "15:27", "15:28", "15:29", "15:30", "15:31", "15:32", "15:33", "15:34", "15:35", "15:36", "15:37", "15:38", "15:39", "15:40", "15:41", "15:42", "15:43", "15:44", "15:45", "15:46", "15:47", "15:48", "15:49", "15:50", "15:51", "15:52", "15:53", "15:54", "15:55", "15:56", "15:57", "15:58", "15:59", "16:00", "16:01", "16:02", "16:03", "16:04", "16:05", "16:06", "16:07", "16:08", "16:09", "16:10", "16:11", "16:12", "16:13", "16:14", "16:15", "16:16", "16:17", "16:18", "16:19", "16:20", "16:21", "16:22", "16:23", "16:24", "16:25", "16:26", "16:27", "16:28", "16:29", "16:30", "16:31", "16:32", "16:33", "16:34", "16:35", "16:36", "16:37", "16:38", "16:39", "16:40", "16:41", "16:42", "16:43", "16:44", "16:45", "16:46", "16:47", "16:48", "16:49", "16:50", "16:51", "16:52", "16:53", "16:54", "16:55", "16:56", "16:57", "16:58", "16:59", "17:00", "17:01", "17:02", "17:03", "17:04", "17:05", "17:06", "17:07", "17:08", "17:09", "17:10", "17:11", "17:12", "17:13", "17:14", "17:15", "17:16", "17:17", "17:18", "17:19", "17:20", "17:21", "17:22", "17:23", "17:24", "17:25", "17:26", "17:27", "17:28", "17:29", "17:30", "17:31", "17:32", "17:33", "17:34", "17:35", "17:36", "17:37", "17:38", "17:39", "17:40", "17:41", "17:42", "17:43", "17:44", "17:45", "17:46", "17:47", "17:48", "17:49", "17:50", "17:51", "17:52", "17:53", "17:54", "17:55", "17:56", "17:57", "17:58", "17:59", "18:00", "18:01", "18:02", "18:03", "18:04", "18:05", "18:06", "18:07", "18:08", "18:09", "18:10", "18:11", "18:12", "18:13", "18:14", "18:15", "18:16", "18:17", "18:18", "18:19", "18:20", "18:21", "18:22", "18:23", "18:24", "18:25", "18:26", "18:27", "18:28", "18:29", "18:30", "18:31", "18:32", "18:33", "18:34", "18:35", "18:36", "18:37", "18:38", "18:39", "18:40", "18:41", "18:42", "18:43", "18:44", "18:45", "18:46", "18:47", "18:48", "18:49", "18:50", "18:51", "18:52", "18:53", "18:54", "18:55", "18:56", "18:57", "18:58", "18:59", "19:00", "19:01", "19:02", "19:03", "19:04", "19:05", "19:06", "19:07", "19:08", "19:09", "19:10", "19:11", "19:12", "19:13", "19:14", "19:15", "19:16", "19:17", "19:18", "19:19", "19:20", "19:21", "19:22", "19:23", "19:24", "19:25", "19:26", "19:27", "19:28", "19:29", "19:30", "19:31", "19:32", "19:33", "19:34", "19:35", "19:36", "19:37", "19:38", "19:39", "19:40", "19:41", "19:42", "19:43", "19:44", "19:45", "19:46", "19:47", "19:48", "19:49", "19:50", "19:51", "19:52", "19:53", "19:54", "19:55", "19:56", "19:57", "19:58", "19:59", "20:00", "20:01", "20:02", "20:03", "20:04", "20:05", "20:06", "20:07", "20:08", "20:09", "20:10", "20:11", "20:12", "20:13", "20:14", "20:15", "20:16", "20:17", "20:18", "20:19", "20:20", "20:21", "20:22", "20:23", "20:24", "20:25", "20:26", "20:27", "20:28", "20:29", "20:30", "20:31", "20:32", "20:33", "20:34", "20:35", "20:36", "20:37", "20:38", "20:39", "20:40", "20:41", "20:42", "20:43", "20:44", "20:45", "20:46", "20:47", "20:48", "20:49", "20:50", "20:51", "20:52", "20:53", "20:54", "20:55", "20:56", "20:57", "20:58", "20:59", "21:00", "21:01", "21:02", "21:03", "21:04", "21:05", "21:06", "21:07", "21:08", "21:09", "21:10", "21:11", "21:12", "21:13", "21:14", "21:15", "21:16", "21:17", "21:18", "21:19", "21:20", "21:21", "21:22", "21:23", "21:24", "21:25", "21:26", "21:27", "21:28", "21:29", "21:30", "21:31", "21:32", "21:33", "21:34", "21:35", "21:36", "21:37", "21:38", "21:39", "21:40", "21:41", "21:42", "21:43", "21:44", "21:45", "21:46", "21:47", "21:48", "21:49", "21:50", "21:51", "21:52", "21:53", "21:54", "21:55", "21:56", "21:57", "21:58", "21:59", "22:00", "22:01", "22:02", "22:03", "22:04", "22:05", "22:06", "22:07", "22:08", "22:09", "22:10", "22:11", "22:12", "22:13", "22:14", "22:15", "22:16", "22:17", "22:18", "22:19", "22:20", "22:21", "22:22", "22:23", "22:24", "22:25", "22:26", "22:27", "22:28", "22:29", "22:30", "22:31", "22:32", "22:33", "22:34", "22:35", "22:36", "22:37", "22:38", "22:39", "22:40", "22:41", "22:42", "22:43", "22:44", "22:45", "22:46", "22:47", "22:48", "22:49", "22:50", "22:51", "22:52", "22:53", "22:54", "22:55", "22:56", "22:57", "22:58", "22:59", "23:00", "23:01", "23:02", "23:03", "23:04", "23:05", "23:06", "23:07", "23:08", "23:09", "23:10", "23:11", "23:12", "23:13", "23:14", "23:15", "23:16", "23:17", "23:18", "23:19", "23:20", "23:21", "23:22", "23:23", "23:24", "23:25", "23:26", "23:27", "23:28", "23:29", "23:30", "23:31", "23:32", "23:33", "23:34", "23:35", "23:36", "23:37", "23:38", "23:39", "23:40", "23:41", "23:42", "23:43", "23:44", "23:45", "23:46", "23:47", "23:48", "23:49", "23:50", "23:51", "23:52", "23:53", "23:54", "23:55", "23:56", "23:57", "23:58", "23:59", "24:00", "24:01", "24:02", "24:03", "24:04", "24:05", "24:06", "24:07", "24:08", "24:09", "24:10", "24:11", "24:12", "24:13", "24:14", "24:15", "24:16", "24:17", "24:18", "24:19", "24:20", "24:21", "24:22", "24:23", "24:24", "24:25", "24:26", "24:27", "24:28", "24:29", "24:30", "24:31", "24:32", "24:33", "24:34", "24:35", "24:36", "24:37", "24:38", "24:39", "24:40", "24:41", "24:42", "24:43", "24:44", "24:45", "24:46", "24:47", "24:48", "24:49", "24:50", "24:51", "24:52", "24:53", "24:54", "24:55", "24:56", "24:57", "24:58", "24:59"}));
        jSpinnerHora.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel3.setText("Hora:");

        jLabel4.setText("ID. Func:");

        jTextFieldIDFunc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIDFuncActionPerformed(evt);
            }
        });

        jTextFieldNomeFuncionario.setEditable(false);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/people/manager/view/search(1).png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Cancelar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Gravar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel5.setText("Comentario:");

        jTextAreaComentario.setColumns(20);
        jTextAreaComentario.setRows(5);
        jScrollPane1.setViewportView(jTextAreaComentario);

        jLabel6.setText("Valor:");

        jTextFieldValor.setText("0,00");
        jTextFieldValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldValorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldIDFunc, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                            .addComponent(jTextFieldIDCliente))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextFieldNomeCliente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextFieldNomeFuncionario)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel3)
                                        .addGap(6, 6, 6)
                                        .addComponent(jSpinnerHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTextFieldValor, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jTextFieldIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jTextFieldIDFunc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldNomeFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jSpinnerHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4)))
        );

        jLabel7.setText("ID:");

        jTextFieldIDAtendimento.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldIDAtendimento, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldIDAtendimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.cliente = ControllerCliente.buscarClienteInterface();
        if (cliente != null) {
            jTextFieldIDCliente.setText(cliente.getId() + "");
            jTextFieldNomeCliente.setText(cliente.getNome());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.funcionario = ControllerFuncionario.buscarFuncionarioInterface();
        if (funcionario != null) {
            jTextFieldIDFunc.setText(funcionario.getId() + "");
            jTextFieldNomeFuncionario.setText(funcionario.getNome());
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (cliente == null) {
            JOptionPane.showMessageDialog(null, "Selecione o cliente.");
        } else if (funcionario == null) {
            JOptionPane.showMessageDialog(null, "Selecione o funcionario.");
        } else if (jDateChooser1.getCalendar() == null) {
            JOptionPane.showMessageDialog(null, "Selecione a data atendimento.");
        } else {
            try {
                String horaS = jSpinnerHora.getValue() + "";
                String[] hora = horaS.split(":");
                Calendar data = jDateChooser1.getCalendar();
                String dataS = Controller.calendarParaString(data);
                dataS = dataS.substring(0, 10);
                dataS = dataS + " " + hora[0] + ":" + hora[1] + ":00";
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                data.setTime(sdf.parse(dataS));
                ControllerAtendimento.editar(jTextFieldIDAtendimento.getText().trim(),jTextAreaComentario.getText().trim(), Integer.parseInt(jTextFieldIDFunc.getText().trim()), Integer.parseInt(jTextFieldIDCliente.getText().trim()), data, Double.parseDouble(jTextFieldValor.getText().trim().replace(",", ".")));
                JOptionPane.showMessageDialog(null, "Agendamento cadastrado.");
                dispose();
            } catch (ParseException | AtendimentoNaoEncontradoException ex) {
                Logger.getLogger(TelaEditarAgendamento.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ImpossivelRemoverException ex) {
                JOptionPane.showMessageDialog(null, "Esse atendimento já foi marcado como concluido.\nNão será possivel edita-lo.");
            }

        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextFieldValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldValorActionPerformed

    private void jTextFieldIDClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIDClienteActionPerformed
        if(!jTextFieldIDCliente.getText().trim().equals("")){
            try {
                cliente = (Cliente) ControllerCliente.buscarCliente(3, jTextFieldIDCliente.getText().trim()).get(0);
                jTextFieldNomeCliente.setText(cliente.getNome()+" "+cliente.getSobrenome());
            } catch (ClienteNaoEncontradoException ex) {
                Logger.getLogger(TelaEditarAgendamento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jTextFieldIDClienteActionPerformed

    private void jTextFieldIDFuncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIDFuncActionPerformed
        if(!jTextFieldIDFunc.getText().trim().equals("")){
            try {
                funcionario = (Funcionario) ControllerFuncionario.buscarFuncionario(2, jTextFieldIDFunc.getText().trim()).get(0);
                jTextFieldNomeFuncionario.setText(funcionario.getNome()+" "+funcionario.getSobrenome());
            } catch (VendedorNaoEncontradoException ex) {
                JOptionPane.showMessageDialog(null, "Funcionario não encontrado.");
                Logger.getLogger(TelaEditarAgendamento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jTextFieldIDFuncActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinnerHora;
    private javax.swing.JTextArea jTextAreaComentario;
    private javax.swing.JTextField jTextFieldIDAtendimento;
    private javax.swing.JTextField jTextFieldIDCliente;
    private javax.swing.JTextField jTextFieldIDFunc;
    private javax.swing.JTextField jTextFieldNomeCliente;
    private javax.swing.JTextField jTextFieldNomeFuncionario;
    private javax.swing.JTextField jTextFieldValor;
    // End of variables declaration//GEN-END:variables
}
