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
public class Vendedor {
    private Integer id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private Integer idade;
    private String celular;
    private String email;
    private Calendar contratacao;
    private Calendar nascimento;
    private boolean estado;
    private Endereco endereco;

    public Vendedor(int id, String nome, String sobrenome, String cpf, int idade, String celular, String email, Calendar contratacao, Calendar nascimento, String rua, String numero, String cep, String bairro, String cidade, String estado) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.idade = idade;
        this.celular = celular;
        this.email = email;
        this.contratacao = contratacao;
        this.nascimento = nascimento;
        this.endereco = new Endereco(rua, numero, cep, bairro, cidade, estado);
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    
    public void desabilitar(){
        this.estado = false;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Calendar getContratacao() {
        return contratacao;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    
    public void setContratacao(Calendar contratacao) {
        this.contratacao = contratacao;
    }

    public boolean isAtivo() {
        return estado;
    }

    public void setAtivo(boolean estado) {
        this.estado = estado;
    }

    public Calendar getNascimento() {
        return nascimento;
    }

    public void setNascimento(Calendar nascimento) {
        this.nascimento = nascimento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
    
    
    
    
}
