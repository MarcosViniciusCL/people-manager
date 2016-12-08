/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people.manager.controller;

import people.manager.exception.SemProdutoEstoqueException;
import java.text.ParseException;
import java.util.ArrayList;
import people.manager.exception.ClienteNaoEncontradoException;
import people.manager.model.Produto;
import people.manager.model.Venda;
import people.manager.persistencia.VendaDAO;

/**
 *
 * @author marcos
 */
public class ControllerVenda {
    
    public static Venda cadastrarVenda(String comentario, int idCliente, ArrayList produtos) throws ParseException, SemProdutoEstoqueException, ClienteNaoEncontradoException{
        Double valorTotal = valorTotal(produtos);
        try {
            ControllerCliente.buscarCliente(3 ,Integer.toString(idCliente));
        } catch (ClienteNaoEncontradoException ex) {
            throw ex;
        }
        ArrayList prod = temEstoque(produtos);
        if(!prod.isEmpty())
            throw new SemProdutoEstoqueException(prod);
        Venda venda = new Venda(comentario, idCliente, produtos, valorTotal);
        VendaDAO.create(venda);
        return venda;
    }
    
    public static ArrayList todasVendas(){
        return VendaDAO.listarTodos();
    }

    private static Double valorTotal(ArrayList<Produto> produtos){
        Double total = 0.0;
        for(Produto p : produtos){
            total += p.getValorVenda();
        }
        return total;
    }
    
    private static ArrayList temEstoque(ArrayList<Produto> produtos){
        ArrayList produtoSemQuantidade = new ArrayList();
        for(Produto p : produtos){
            if(p.getQuantidade() == 0)
                produtoSemQuantidade.add(p);
            
        }
        return produtoSemQuantidade;
    }
    
    /**
     * Retorna o valor total, em real, de vendas
     * @return Double
     * @throws ParseException
     */
    public static Double totalVendas() throws ParseException{
      Double total = 0.0;
      int max = VendaDAO.quantidadeBanco();
      Venda v;
      for(int i = 0; i <= max; i++){
          v = VendaDAO.buscaID(i);
          total += v.getValorVenda();
      }
      return total;
    }
}