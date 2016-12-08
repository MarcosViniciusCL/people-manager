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
    private static int id_gerador = 0;
    private int id;
    private Calendar data;
    private String comentario;
    private String atendente;
    private Double preco;

    public Atendimento(String comentario, String atendente, Double preco) {
        this.id = ++id_gerador;
        this.data = Calendar.getInstance();
        this.comentario = comentario;
        this.atendente = atendente;
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Atendimento{" + "id=" + id + ", data=" + data + ", comentario=" + comentario + ", atendente=" + atendente + '}';
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getData() {
        return data;
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

    public String getAtendente() {
        return atendente;
    }

    public void setAtendente(String atendente) {
        this.atendente = atendente;
    }
    
    
}
