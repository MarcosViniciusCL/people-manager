/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people.manager.controller;

import java.awt.Font;
import people.manager.exception.ClienteNaoEncontradoException;
import people.manager.exception.CPFExistenteException;
import java.text.ParseException;
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
import people.manager.model.Cliente;
import people.manager.model.Endereco;
import people.manager.persistencia.ClienteDAO;

/**
 *
 * @author Marcos Vinícius
 * @version 0.1
 */
public class ControllerCliente {

    /**
     * Cadastra um novo cliente no banco de dados.
     *
     * @param nome @param sobrenome
     * @param celular @param telefone
     * @param email @param nascimento
     * @param cpf @param rg
     * @param rua @param numero
     * @param cep @param bairro
     * @param cidade @param estado
     * @return Cliente
     * @throws CPFExistenteException
     */
    public static Cliente cadastrarCliente(String nome, String sobrenome, String celular, String telefone, String email, Calendar nascimento, String cpf, String rg, String rua, String numero, String cep, String bairro, String cidade, String estado) throws CPFExistenteException {
        Cliente cliente;
        Cliente buscaCPF = ClienteDAO.buscaCPF(cpf);
        Cliente buscaRG = ClienteDAO.buscaRG(rg);
        if (buscaCPF != null || buscaRG != null) {
            throw new CPFExistenteException();
        }
        cliente = new Cliente(nome, sobrenome, celular, telefone, email, nascimento, cpf, rg, rua, numero, cep, bairro, cidade, estado);
        ClienteDAO.create(cliente);
        return cliente;
    }

    public static void desativarCliente(String cpf) throws ClienteNaoEncontradoException {
        Cliente cliente = ClienteDAO.buscaCPF(cpf);
        if (cliente == null) {
            throw new ClienteNaoEncontradoException();
        }
        cliente.desabilitar();
        ClienteDAO.edita(cliente);
    }

    public static Cliente editarCliente(int id, String nome, String sobrenome, String celular, String telefone, String email, Calendar nascimento, String cpf, String rg, String rua, String numero, String cep, String bairro, String cidade, String estado) throws ClienteNaoEncontradoException {
        Cliente cliente = ClienteDAO.buscaID(id);
        if (cliente == null) {
            throw new ClienteNaoEncontradoException();
        }
        cliente.setNome(nome);
        cliente.setSobrenome(sobrenome);
        cliente.setCelular(celular);
        cliente.setTelefone(telefone);
        cliente.setEmail(email);
        cliente.setNascimento(nascimento);
        cliente.setCpf(cpf);
        cliente.setRg(rg);
        Endereco end = new Endereco(rua, numero, cep, bairro, cidade, estado);
        cliente.setEnd(end);
        ClienteDAO.edita(cliente);
        return cliente;
    }

    /**
     * Busca de cliente por nome, cpf ou rg. A seleção da busca é feita pela
     * variavel sel onde 0 = NOME, 1 = CPF, 2 = RG, 3 = ID.
     *
     * @param sel - Seleciona qual será a forma de busca.
     * @param busca - Nome, CPF, RG ou ID.
     * @return ArrayList
     * @throws people.manager.exception.ClienteNaoEncontradoException
     */
    public static ArrayList buscarCliente(int sel, String busca) throws ClienteNaoEncontradoException {
        ArrayList clientes = new ArrayList();
        if (sel == 0) {
            clientes = ClienteDAO.buscaNome(busca);
            if (clientes.isEmpty()) {
                throw new ClienteNaoEncontradoException();
            }
            return clientes;
        }
        if (sel == 1) {
            clientes.add(ClienteDAO.buscaCPF(busca));
            if (clientes.get(0) == null) {
                throw new ClienteNaoEncontradoException();
            }
            return clientes;
        }
        if (sel == 2) {
            clientes.add(ClienteDAO.buscaRG(busca));
            if (clientes.get(0) == null) {
                throw new ClienteNaoEncontradoException();
            }
            return clientes;
        }
        if (sel == 3) {
            int idBusca = Integer.parseInt(busca);
            clientes.add(ClienteDAO.buscaID(idBusca));
            if (clientes.isEmpty() || clientes.get(0) == null) {
                throw new ClienteNaoEncontradoException();
            }
            return clientes;
        }
        return null;
    }

    public static int quantidadeClientes() {
        return ClienteDAO.quantidadeBanco();
    }

    /**
     * Retorna os clientes que estão fazendo aniversario no mês enviado como
     * parâmetro.
     *
     * @param mes
     * @return ArrayList
     */
    public static ArrayList aniversariantesMes(int mes) {
        int max = quantidadeClientes();
        ArrayList aniv = new ArrayList();
        Cliente c;
        for (int i = 0; i <= max; i++) {
            c = ClienteDAO.buscaID(i);
            if (c != null) {
                if (c.getNascimento().get(Calendar.MONTH) == mes) {
                    aniv.add(c);
                }
            }

        }
        return aniv;
    }

    /**
     * Retorna os clientes que deverão voltar a loja na quantidade de dias
     * especificados.
     *
     * @param dias
     * @return ArrayList - Clientes que deverão voltar a loja.
     * @throws ParseException
     */
    public static ArrayList proxAtendimento(int dias) throws ParseException {
        int max = quantidadeClientes();
        ArrayList ante = new ArrayList();
        Cliente c;
        for (int i = 1; i <= max; i++) {
            c = ClienteDAO.buscaID(i);
            if (c.diasProximoAtendimento() <= dias) {
                ante.add(c);
            }

        }
        return ante;
    }

    public static void removerCliente(String cpf) {
        ClienteDAO.remover(cpf);
    }

    /**
     * Criar uma tintefece grafica para pesquicar pelos clientes;
     *
     * @return 
     */
    public static Cliente buscarClienteInterface() {
        

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
    private static JTable table;
    private static Cliente cliente;
}
