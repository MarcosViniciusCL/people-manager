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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.JTextField;
import people.manager.model.Cliente;
import people.manager.model.Endereco;
import people.manager.persistencia.ClienteDAO;
import sun.awt.X11.XConstants;

/**
 *
 * @author Marcos Vinícius
 * @version 0.1
 */
public class ControllerCliente {
    /**
     * Cadastra um novo cliente no banco de dados.
     * @param nome     @param sobrenome
     * @param celular  @param telefone
     * @param email    @param nascimento
     * @param cpf      @param rg
     * @param rua      @param numero
     * @param cep      @param bairro
     * @param cidade   @param estado
     * @return Cliente
     * @throws CPFExistenteException
     */
    public static Cliente cadastrarCliente(String nome, String sobrenome, String celular, String telefone, String email, Calendar nascimento, String cpf, String rg, String rua, String numero, String cep, String bairro,String cidade,String estado) throws CPFExistenteException{
        Cliente cliente;
        Cliente buscaCPF = ClienteDAO.buscaCPF(cpf);
        Cliente buscaRG = ClienteDAO.buscaRG(rg);
        if(buscaCPF != null ||buscaRG  != null)
            throw new CPFExistenteException();
        cliente = new Cliente(nome, sobrenome, celular, telefone, email, nascimento, cpf, rg, rua, numero, cep, bairro, cidade, estado);
        ClienteDAO.create(cliente);
        return cliente;
    }      
    
    public static void desativarCliente(String cpf) throws ClienteNaoEncontradoException{
        Cliente cliente = ClienteDAO.buscaCPF(cpf);
        if(cliente == null)
            throw new ClienteNaoEncontradoException();
        cliente.desabilitar();
        ClienteDAO.edita(cliente);
    }    
 
    public static Cliente editarCliente(int id, String nome, String sobrenome, String celular, String telefone, String email, Calendar nascimento, String cpf, String rg, String rua, String numero, String cep, String bairro,String cidade,String estado) throws ClienteNaoEncontradoException{
        Cliente cliente = ClienteDAO.buscaID(id);
        if(cliente == null)
            throw new ClienteNaoEncontradoException();
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
     * Busca de cliente por nome, cpf ou rg. 
     * A seleção da busca é feita pela variavel sel onde 0 = NOME, 1 = CPF, 2 = RG, 3 = ID.
     * @param sel - Seleciona qual será a forma de busca.
     * @param busca - Nome, CPF, RG ou ID.
     * @return ArrayList 
     * @throws people.manager.exception.ClienteNaoEncontradoException 
     */
    public static ArrayList buscarCliente(int sel, String busca) throws ClienteNaoEncontradoException{
        ArrayList clientes = new ArrayList();
        if(sel == 0){
            clientes = ClienteDAO.buscaNome(busca);
            if(clientes.isEmpty())
                throw new ClienteNaoEncontradoException();
            return clientes;            
        }
        if(sel == 1){
            clientes.add(ClienteDAO.buscaCPF(busca));
            if(clientes.get(0) == null)
                throw new ClienteNaoEncontradoException();
            return clientes;             
        }
        if(sel == 2){
            clientes.add(ClienteDAO.buscaRG(busca));
            if(clientes.get(0) == null)
                throw new ClienteNaoEncontradoException();
            return clientes;             
        }
        if(sel == 3){
            int idBusca = Integer.parseInt(busca);
            clientes.add(ClienteDAO.buscaID(idBusca));
            if(clientes.isEmpty())
                throw new ClienteNaoEncontradoException();
            return clientes;             
        }
        return null;
    }
    
    public static int quantidadeClientes(){
        return ClienteDAO.quantidadeBanco();
    }
    
    /**
     * Retorna os clientes que estão fazendo aniversario no mês enviado como parâmetro.
     * @param mes
     * @return ArrayList 
     */
    public static ArrayList aniversariantesMes(int mes) {
        int max = quantidadeClientes();
        ArrayList aniv = new ArrayList();
        Cliente c;
        for (int i =1; i <= max; i++){
            c = ClienteDAO.buscaID(i);
            if (c != null) 
                if(c.getNascimento().get(Calendar.MONTH) == mes)
                    aniv.add(c);
            
        }
        return aniv;
    }
    
    /**
     * Retorna os clientes que deverão voltar a loja na quantidade de dias especificados.
     * @param dias
     * @return ArrayList - Clientes que deverão voltar a loja.
     * @throws ParseException 
     */
    public static ArrayList proxAtendimento(int dias) throws ParseException{
        int max = quantidadeClientes();
        ArrayList ante = new ArrayList();
        Cliente c;
        for (int i =1; i <= max; i++){
            c = ClienteDAO.buscaID(i);
            if(c.diasProximoAtendimento() <= dias)
                ante.add(c);
            
        }
        return ante;
    }

    public static void removerCliente(String cpf) {
        ClienteDAO.remover(cpf);
    }
    
    public static Cliente buscarClienteInterface(){
        Cliente c = null;
        JFrame jf = new JFrame("Busca Cliente");
        
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setSize(500, 250);
        JPanel jpanel = new JPanel();
        jpanel.setLayout(null);
        JLabel jlabel = new JLabel("Nome:");
        jlabel.setBounds(10, 10, 50, 20);
        JTextField text = new JTextField(200);
        text.setFont(new Font("Tahoma", 0, 11));
        text.setBounds(100, 10, 350, 20);
        jpanel.add(jlabel);
        jpanel.add(text);
        
        //Evente campo de texto
        text.addFocusListener(new java.awt.event.FocusAdapter() {
            public void keyEvent(java.awt.event.FocusEvent e) {
		System.out.println("focusLost()"); // TODO Auto-generated Event stub focusLost()
            }
	});
            
        
        
        JScrollBar jscrol = new JScrollBar();
        
        JTable table = new JTable();
        
        jf.add(jpanel);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
        
        return c;
    }
}
