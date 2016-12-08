/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people.manager.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author marcos
 */
public class Venda {
    private Integer     id; 
    private Calendar    data;
    private String      comentario;
    private Integer     idCliente;
    private ArrayList   produtos;
    private Double     valorVenda;

    public Venda(String comentario, Integer idCliente, ArrayList produtos, Double valorVenda) {
        this.id = 0;
        this.data = Calendar.getInstance();
        this.comentario = comentario;
        this.idCliente = idCliente;
        this.produtos = produtos;
        this.valorVenda = valorVenda;
    }
    
    private String calendarParaString(Calendar calendar){
        if(calendar == null)
            return "NULO";
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return s.format(calendar.getTime());
    } 

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Calendar getData() {
        return data;
    }
    
    public String getDataString() {
        return this.calendarParaString(data);
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public ArrayList getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList produtos) {
        this.produtos = produtos;
    }

    public Double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(Double valorVenda) {
        this.valorVenda = valorVenda;
    }
    
    
    
    
    
    
    
}
