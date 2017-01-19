/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people.manager.controller;

import java.awt.Font;
import people.manager.exception.ProdutoNaoEncontradoException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
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
import people.manager.model.Produto;
import people.manager.persistencia.ProdutoDAO;

/**
 *
 * @author Marcos Vinícius
 */
public class ControllerProduto {
    
    public static Produto cadastrarProduto(String nome, String categoria , String codigoBarra, Double valorVenda, Double valorCompra, Integer quantidade) {
        Produto produto;
        produto = new Produto(nome, categoria, codigoBarra, valorVenda, valorCompra, quantidade);
        ProdutoDAO.create(produto);
        return produto;
    }      
    
    public static void apagarProduto(int id) {
        ProdutoDAO.remover(id);
    }    
 
    public static Produto editarProduto(int id, String nome, String codigoBarra, String categoria , Double valorVenda, Double valorCompra, Integer quantidade) throws ClienteNaoEncontradoException{
        Produto produto = ProdutoDAO.buscaID(id);
        if(produto == null)
            throw new ClienteNaoEncontradoException();
        produto.setNome(nome);
        produto.setCodigoBarra(codigoBarra);
        produto.setQuantidade(quantidade);
        produto.setCategoria(categoria);
        produto.setValorVenda(valorVenda);
        produto.setValorCompra(valorCompra);
        ProdutoDAO.edita(produto);
        return produto;
    }
    

    /**
     * Busca de produto por nome ou id. 
     * A seleção da busca é feita pela variavel sel onde 0 = NOME, 1 = ID ou 2 = COD.BARRA.
     * @param sel - Seleciona qual será a forma de busca.
     * @param busca - Nome, ID ou Codigo de Barra.
     * @return ArrayList
     * @throws people.manager.exception.ProdutoNaoEncontradoException 
     */
    public static ArrayList buscarNome(int sel, String busca) throws ProdutoNaoEncontradoException {
        ArrayList produtos = new ArrayList();
        if(sel == 0){
            produtos = ProdutoDAO.buscaNome(busca);
            if(produtos == null || produtos.isEmpty())
                throw new ProdutoNaoEncontradoException();
            return produtos;            
        }
        if(sel == 1){
            int id = Integer.parseInt(busca);
            produtos.add(ProdutoDAO.buscaID(id));
            if(produtos.isEmpty() || produtos.get(0) == null)
                throw new ProdutoNaoEncontradoException();
            return produtos;             
        }
        if(sel == 2){
            produtos.add(ProdutoDAO.buscaCodBarra(busca));
            if(produtos.isEmpty() || produtos.get(0) == null)
                throw new ProdutoNaoEncontradoException();
            return produtos;             
        }
        return null;
    }
    
    public static Produto buscarID(int id) throws ParseException{
        return ProdutoDAO.buscaID(id);
    }
    /**
     * Retorna a quantidade total de produtos;
     * @return 
     */
    public static int quantidadeProdutos(){
        return ProdutoDAO.quantidadeBanco();
    }
    
    /**
     * Retorna a quantidade de produto no estoque;
     * @param id
     * @return 
     */
    public static Integer quantidadeEstoque(Integer id){
        Produto p = ProdutoDAO.buscaID(id);
        return p.getQuantidade();
    }
    
    /**
     * Subtrai o valor informado de um produto no estoque;
     * @param id
     * @param quantidadeR 
     */
    public static void removerEstoque(Integer id, Integer quantidadeR){
        Produto p = ProdutoDAO.buscaID(id);
        p.subtraiQuantidade(quantidadeR);
        ProdutoDAO.edita(p);
    }
    
    /**
     * Adiciona em estoque a quantidade enviada
     * @param id
     * @param quantidadeA
     */
    public static void adicionaEstoque(Integer id, Integer quantidadeA){
        Produto p = ProdutoDAO.buscaID(id);
        p.adicionarQuantidade(quantidadeA);
        ProdutoDAO.edita(p);
    }
    
    public static ArrayList hashMapParaArray(ArrayList<HashMap> produtos){
        ArrayList convertidos = new ArrayList();
        for (HashMap object : produtos) {
            Integer quantidade = Integer.parseInt(object.get("quantidade").toString());
            Double valorCompra = Double.parseDouble(object.get("valorCompra").toString());
            Double valorVenda = Double.parseDouble(object.get("valorVenda").toString());
            Produto prod = new Produto((String)object.get("nome"), (String)object.get("categoria"), (String)object.get("codigoBarra"), valorCompra, valorVenda, quantidade);
            prod.setId(Integer.parseInt(object.get("id").toString()));
            convertidos.add(prod);
        }
        return convertidos;
    }
    
    public static Produto buscarProdutoInterface() {
        

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
            ArrayList<Produto> produto = new ArrayList();
            try {
                produto = ControllerProduto.buscarNome(0, text.getText().trim());
            } catch (ProdutoNaoEncontradoException ex) {
                Logger.getLogger(ControllerProduto.class.getName()).log(Level.SEVERE, null, ex);
            }
            String[] colunas = {"ID", "PRODUTO", "QUANTIDADE", "COD. BARRA"};
            List<String[]> lista = new ArrayList<>();
            for (Produto produtoT : produto) {
 
                lista.add(new String[]{produtoT.getId().toString(), produtoT.getNome(), produtoT.getQuantidade().toString(), produtoT.getCodigoBarra()});
                
            }
            table = new JTable();
            DefaultTableModel model = new DefaultTableModel(lista.toArray(new String[lista.size()][]), colunas);
            table.setModel(model);
            jscrol.setViewportView(table);
        });
        botaoSeleciona.addActionListener((java.awt.event.ActionEvent e) -> {
            try {
                ArrayList a = ControllerProduto.buscarNome(1, (String)table.getValueAt(table.getSelectedRow(), 0));
                produtoMain = (Produto) a.get(0);
                jf.dispose();
            } catch (ProdutoNaoEncontradoException ex) {
                Logger.getLogger(ControllerProduto.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        botaoCancelar.addActionListener((java.awt.event.ActionEvent e) -> {
            jf.dispose();
        });
        
        jf.setVisible(true);
        return ControllerProduto.produtoMain;
    }
    private static JTable table;
    private static Produto produtoMain;
    
}
