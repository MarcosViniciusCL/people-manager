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
    
    public static Venda cadastrarVenda(String comentario, int idCliente, int idVendedor, ArrayList produtos, String formaPagamento, Double valorRecebido, Double valorTroco) throws SemProdutoEstoqueException, ClienteNaoEncontradoException{
        Double valorTotal = valorTotal(produtos);
        try {
            ControllerCliente.buscarCliente(3 ,Integer.toString(idCliente));
        } catch (ClienteNaoEncontradoException ex) {
            throw ex;
        }
        ArrayList prod = temEstoque(produtos);
        if(!prod.isEmpty())
            throw new SemProdutoEstoqueException(prod);
        Venda venda = new Venda(comentario, idCliente, idVendedor, produtos, valorTotal, formaPagamento, valorRecebido, valorTroco);
        VendaDAO.create(venda);
        return venda;
    }
    
    public static ArrayList todasVendas(){
        return VendaDAO.listarTodos();
    }

    private static Double valorTotal(ArrayList<Produto> produtos){
        Double total = 0.0;
        total = produtos.stream().map((p) -> p.getValorVenda()).reduce(total, (accumulator, _item) -> accumulator + _item);
        return total;
    }
    
    private static ArrayList temEstoque(ArrayList<Produto> produtos){
        ArrayList produtoSemQuantidade = new ArrayList();
        produtos.stream().filter((p) -> (p.getQuantidade() == 0)).forEachOrdered((p) -> {
            produtoSemQuantidade.add(p);
        });
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