/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people.manager.model;

/**
 *
 * @author marcos
 */
public class Produto {
    private Integer    id; 
    private String nome;
    private String codigoBarra;
    private String categoria;
    private Double valorVenda;
    private Double valorCompra;
    private Integer    quantidade;

    public Produto(String nome, String categoria, String codigoBarra, Double valorCompra, Double valorVenda, int quantidade) {
        this.id = 0;
        this.nome = nome;
        this.categoria = categoria;
        this.codigoBarra = codigoBarra;
        this.valorCompra = valorCompra;
        this.valorVenda = valorVenda;
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return this.nome; //To change body of generated methods, choose Tools | Templates.
    }
    
    public void vendeu(){
        this.quantidade -= 1;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setValorCompra(Double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public Double getValorCompra() {
        return valorCompra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public Double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(Double valor) {
        this.valorVenda = valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

}
