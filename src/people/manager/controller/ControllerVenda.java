/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people.manager.controller;

import people.manager.exception.SemProdutoEstoqueException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import people.manager.exception.ClienteNaoEncontradoException;
import people.manager.model.Produto;
import people.manager.model.Venda;
import people.manager.persistencia.VendaDAO;

/**
 *
 * @author marcos
 */
public class ControllerVenda {
    
    public static Venda cadastrarVenda(Calendar dataVenda, String comentario, int idCliente, int idVendedor, ArrayList produtos, String formaPagamento, Double valorTotal, Double valorRecebido, Double valorTroco, String estado) throws SemProdutoEstoqueException, ClienteNaoEncontradoException{
//        Double valorTotal = valorTotal(produtos);
        try {
            ControllerCliente.buscarCliente(3 ,Integer.toString(idCliente));
        } catch (ClienteNaoEncontradoException ex) {
            throw ex;
        }
        ArrayList prod = temEstoque(produtos);
        if(!prod.isEmpty())
            throw new SemProdutoEstoqueException(prod);
        Venda venda = new Venda(0, dataVenda, comentario, idCliente, idVendedor, produtos, valorTotal, formaPagamento, valorRecebido, valorTroco, estado);
        VendaDAO.create(venda);
        return venda;
    }
    
    public static ArrayList todasVendas(){
        return VendaDAO.listarTodos();
    }
    
    public static ArrayList todasVendas(Calendar dataInicial, Calendar dataFinal){
        int max = VendaDAO.quantidadeBanco();
        ArrayList vendas = new ArrayList();
        for (int i = 1; i <= max; i++) {
            Venda venda = VendaDAO.buscaID(i);
            if(venda.getData().compareTo(dataInicial) > 0 && venda.getData().compareTo(dataFinal) < 0)
                vendas.add(venda);
        }
        return vendas;
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

    public static Venda buscaId(int id) {
        return VendaDAO.buscaID(id);
    }
}