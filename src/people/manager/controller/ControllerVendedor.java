/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people.manager.controller;

import people.manager.exception.VendedorNaoEncontradoException;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import people.manager.exception.ClienteNaoEncontradoException;
import people.manager.model.Cliente;
import people.manager.model.Endereco;
import people.manager.model.Vendedor;
import people.manager.persistencia.VendedorDAO;

/**
 *
 * @author Marcos Vinícius
 */
public class ControllerVendedor {

    public static Vendedor cadastrarVendedor(String nome, String sobrenome, String cpf, int idade, String celular, String email, Calendar contratacao, Calendar nascimento, String rua, String numero, String cep, String bairro, String cidade, String estado) throws VendedorNaoEncontradoException {
        Vendedor vendedor;
        Vendedor buscaCPF = VendedorDAO.buscaCPF(cpf);
        
        if (buscaCPF != null) {
            throw new VendedorNaoEncontradoException();
        }
        vendedor = new Vendedor(0, nome, sobrenome, cpf, idade, celular, email, contratacao, nascimento, rua, numero, cep, bairro, cidade, estado);
        VendedorDAO.create(vendedor);
        return vendedor;
    }

    public static void desativarVendedor(String cpf) throws VendedorNaoEncontradoException {
        Vendedor vendedor = VendedorDAO.buscaCPF(cpf);
        if (vendedor == null) {
            throw new VendedorNaoEncontradoException();
        }
        vendedor.desabilitar();
        VendedorDAO.edita(vendedor);
    }

    public static Vendedor editarVendedor(int id, String nome, String sobrenome, String cpf, int idade, String celular, String email, Calendar contratacao, Calendar nascimento, String rua, String numero, String cep, String bairro, String cidade, String estado) throws ClienteNaoEncontradoException {
        Vendedor vendedor = VendedorDAO.buscaID(id);
        if (vendedor == null) {
            throw new ClienteNaoEncontradoException();
        }
        vendedor.setNome(nome);
        vendedor.setSobrenome(sobrenome);
        vendedor.setCelular(celular);
        vendedor.setEmail(email);
        vendedor.setNascimento(nascimento);
        vendedor.setCpf(cpf);
        Endereco end = new Endereco(rua, numero, cep, bairro, cidade, estado);
        vendedor.setEndereco(end);
        VendedorDAO.edita(vendedor);
        return vendedor;
    }
    
    /**
     * Busca de vendedor por nome, cpf ou rg. A seleção da busca é feita pela
     * variavel sel onde 0 = NOME, 1 = CPF, 2 = ID.
     *
     * @param sel - Seleciona qual será a forma de busca.
     * @param busca - Nome, CPF  ou ID.
     * @return ArrayList
     * @throws people.manager.exception.VendedorNaoEncontradoException
     */
    public static ArrayList buscarVendedor(int sel, String busca) throws VendedorNaoEncontradoException {
        ArrayList vendedor = new ArrayList();
        if (sel == 0) {
            vendedor = VendedorDAO.buscaNome(busca);
            if (vendedor.isEmpty()) {
                throw new VendedorNaoEncontradoException();
            }
            return vendedor;
        }
        if (sel == 1) {
            vendedor.add(VendedorDAO.buscaCPF(busca));
            if (vendedor.get(0) == null) {
                throw new VendedorNaoEncontradoException();
            }
            return vendedor;
        }
        if (sel == 2) {
            int idBusca = Integer.parseInt(busca);
            vendedor.add(VendedorDAO.buscaID(idBusca));
            if (vendedor.isEmpty() || vendedor.get(0) == null) {
                throw new VendedorNaoEncontradoException();
            }
            return vendedor;
        }
        return null;
    }
    
    
    public static Vendedor buscarVendedorInterface() {
        

        //Criando JFrame;
        JDialog jf = new JDialog();
        jf.setModal(true);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setSize(500, 250);
        jf.setLocationRelativeTo(null);

        JPanel jpanel = new JPanel();
        jpanel.setLayout(null);
        JLabel jlabel = new JLabel("Nome:");
        jlabel.setBounds(10, 10, 50, 20);
        JTextField text = new JTextField(250);
        text.setFont(new Font("Tahoma", 0, 11));
        text.setBounds(60, 10, 330, 20);
        jpanel.add(jlabel);
        jpanel.add(text);

        jf.add(jpanel);

        //Criando Jscrollpane
        JScrollPane jscrol = new JScrollPane();
        jscrol.setBounds(10, 50, 475, 120);
        jpanel.add(jscrol);

        //Criando botoes
        JButton botaoSeleciona = new JButton("Selecionar");
        botaoSeleciona.setBounds(260, 190, 110, 25);
        JButton botaoCancelar = new JButton("Cancelar");
        botaoCancelar.setBounds(375, 190, 110, 25);
        JButton botaoBusca = new JButton("Buscar");
        botaoBusca.setBounds(400, 10, 85, 21);
        jpanel.add(botaoBusca);
        jpanel.add(botaoCancelar);
        jpanel.add(botaoSeleciona);
        
        
        
        //Eventos do Jframe
        botaoBusca.addActionListener((java.awt.event.ActionEvent e) -> {
            ArrayList<Cliente> clientes = new ArrayList();
            try {
                clientes = ControllerCliente.buscarCliente(0, text.getText().trim());
            } catch (ClienteNaoEncontradoException ex) {
                Logger.getLogger(ControllerCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            String[] colunas = {"ID", "NOME", "IDADE", "CPF", "DEPITO", "ESTADO"};
            List<String[]> lista = new ArrayList<>();
            for (Cliente clienteT : clientes) {
                String est = "DESATIVO";
                if (clienteT.isAtivo()) {
                    est = "ATIVO";
                }
                lista.add(new String[]{clienteT.getId().toString(), clienteT.getNome() + " " + clienteT.getSobrenome(), clienteT.getIdade().toString(), clienteT.getCpf(), clienteT.getSaldoDevedor().toString(), est});
                
            }
            table = new JTable();
            DefaultTableModel model = new DefaultTableModel(lista.toArray(new String[lista.size()][]), colunas);
            table.setModel(model);
            jscrol.setViewportView(table);
        });
        botaoSeleciona.addActionListener((java.awt.event.ActionEvent e) -> {
            try {
                ArrayList a = ControllerCliente.buscarCliente(3, (String)table.getValueAt(table.getSelectedRow(), 0));
                cliente = (Cliente) a.get(0);
                jf.dispose();
            } catch (ClienteNaoEncontradoException ex) {
                Logger.getLogger(ControllerCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        botaoCancelar.addActionListener((java.awt.event.ActionEvent e) -> {
            jf.dispose();
        });
        
        jf.setVisible(true);
        return ControllerCliente.cliente;
    }
    
}
