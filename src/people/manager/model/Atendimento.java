/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people.manager.model;

import java.util.Calendar;

/**
 *
 * @author marcos
 */
public class Atendimento {
    private int id;
    private Calendar dataAgendamento;
    private Calendar dataAtendimento;
    private String comentario;
    private Integer idCliente;
    private Integer idAtendente;
    private Double preco;
    private boolean atendido;

    public Atendimento(String comentario, Integer idAtendente, Integer idCliente, Calendar dataAtendimento, Double preco) {
        this.dataAgendamento = Calendar.getInstance();
        this.comentario = comentario;
        this.idAtendente = idAtendente;
        this.idCliente = idCliente;
        this.dataAtendimento = dataAtendimento;
        this.preco = preco;
        this.atendido = false;
    }

    @Override
    public String toString() {
        return "Atendimento{" + "id=" + id + ", data=" + dataAgendamento + ", comentario=" + comentario + ", atendente=" + idAtendente + '}';
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public boolean isAtendido() {
        return atendido;
    }

    public void setAtendido(boolean atendido) {
        this.atendido = atendido;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Calendar getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(Calendar dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public Calendar getDataAtendimento() {
        return dataAtendimento;
    }

    public void setDataAtendimento(Calendar dataAtendimento) {
        this.dataAtendimento = dataAtendimento;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdAtendente() {
        return idAtendente;
    }

    public void setAtendente(Integer atendente) {
        this.idAtendente = atendente;
    }
    
    
}
