/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people.manager.controller;

import people.manager.exception.ProdutoNaoEncontradoException;
import java.text.ParseException;
import java.util.ArrayList;
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
 
    public static Produto editarProduto(int id, String nome, String codigoBarra, Double valorVenda, Double valorCompra) throws ParseException, ClienteNaoEncontradoException{
        Produto produto = ProdutoDAO.buscaID(id);
        if(produto == null)
            throw new ClienteNaoEncontradoException();
        produto.setNome(nome);
        produto.setCodigoBarra(codigoBarra);
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
            if(produtos.isEmpty())
                throw new ProdutoNaoEncontradoException();
            return produtos;             
        }
        if(sel == 2){
            produtos.add(ProdutoDAO.buscaCodBarra(busca));
            if(produtos.isEmpty())
                throw new ProdutoNaoEncontradoException();
            return produtos;             
        }
        return null;
    }
    
    public static Produto buscarID(int id) throws ParseException{
        return ProdutoDAO.buscaID(id);
    }
    
    public static int quantidadeProdutos(){
        return ProdutoDAO.quantidadeBanco();
    }
    
}
