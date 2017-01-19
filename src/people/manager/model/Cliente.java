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
public class Cliente {
    private Integer id;
    //------DADOS PESSOAIS---------
    private String nome;
    private boolean ativo;
    private String sobrenome;
    private String celular;
    private String telefone;
    private String email;
    private Integer idade;
    private Calendar nascimento;
    private String cpf;
    private String rg;
    private Endereco end;
    //------DADOS COMERCIAIS-------
    private ArrayList historico; 
    private Double saldoDevedor;
    private Calendar ultimoAtendimento;
    private Calendar proximoAtendimento;

    public Cliente(String nome, String sobrenome, String celular, String telefone, String email, Calendar nascimento, String cpf, String rg, String rua, String numero, String cep, String bairro,String cidade,String estado) {
        this.id = 0;
        this.historico = new ArrayList();
        this.nome = nome;
        this.ativo = true;
        this.sobrenome = sobrenome;
        this.celular = celular;
        this.telefone = telefone;
        this.email = email;
        this.nascimento = nascimento;
        this.cpf = cpf;
        this.rg = rg;
        this.saldoDevedor = 0.0;
        this.end = new Endereco(rua, numero, cep, bairro, cidade, estado);
        this.gerarIdade();
    }
    /**
     * Mostra quantos dias faltam para o cliente voltar a loja.
     * @return int - Dias que faltam
     */
    public int diasProximoAtendimento(){
        int dias;
        Calendar dataAtual = Calendar.getInstance();
        dias = proximoAtendimento.get(Calendar.DAY_OF_YEAR) - dataAtual.get(Calendar.DAY_OF_YEAR);
        if(dias >= 0)
            return dias;
        return -1;
    }
    /**
     * Adiciona a data do ultimo atendimento ao atributo ultimoAtendimento
     */
    private void gerarUltimoAtendimento(){
        if(!historico.isEmpty()){
            Atendimento a = (Atendimento) historico.get(historico.size()-1);
            this.ultimoAtendimento = a.getDataAtendimento();
        }
    }
    /**
     * Adiciona a data que o cliente deverá voltar. 
     * @param proximoAtendimento
     * @return Calendar
     */
    public Calendar proximoAtendimento(Calendar proximoAtendimento){
        this.proximoAtendimento = proximoAtendimento;
        return this.proximoAtendimento;
    }
    /**
     * Adiciona um novo atendimento.
     * Caso o cliente esteja desativado é lançado uma exception impedindo. 
     * o cadastro de novo atendimento.
     * @param comentario
     * @param preco
     * @throws ClienteDesativadoException 
     */        
//    public void novoAtendimento(String comentario,Double preco) throws ClienteDesativadoException{
//        if(this.ativo){
//            Atendimento at = new Atendimento(comentario, "PADRÃO", preco);
//            historico.add(at);
//            this.gerarUltimoAtendimento();
//        }else {
//            throw new ClienteDesativadoException();
//       }
//    }
    /**
     * Desalita o cliente
     * @return boolean - Retona o estado do cliente após alteração.
     */
    public boolean desabilitar(){
        this.ativo = false;
        return this.ativo;
    }
    /**
     * Habilita o cliente.
     * @return boolean - Retorna o estado do cliente após alteração.
     */
    public boolean habilitar(){
        this.ativo = true;
        return this.ativo;
    }
    
   
    /**
     * Modifica o atributo idade da classe de acordo com 
     *  o ano atual e seu nascimento.
     */
    private void gerarIdade(){
//        String dataAtual = Calendar.getInstance().toString();
 //       int index = dataAtual.indexOf("YEAR=");
 //       int anoAtual = Integer.parseInt(dataAtual.substring(index+5, index+9));
 //       int anoNasc = this.nascimento.get(1);
        this.idade = Calendar.getInstance().get(Calendar.YEAR) - this.nascimento.get(Calendar.YEAR);
    }
    private String calendarParaString(Calendar calendar){
        if(calendar == null)
            return "NULO";
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return s.format(calendar.getTime());
    }
    
    public boolean isAniversariante(){
        Calendar dataAtual = Calendar.getInstance();
        return this.nascimento.get(Calendar.MONTH) == dataAtual.get(Calendar.MONTH) &&  this.nascimento.get(Calendar.DAY_OF_MONTH) == dataAtual.get(Calendar.DAY_OF_MONTH);
    }
    
    
  

// ---------------------------------- Gets & Sets ----------------------------------------------
    public String getCelular() {    
        return celular;
    }

    public String getEmail() {
        return email;
    }
    
    public ArrayList getHistorico(){
        return this.historico;
    }
    
    public void setHistorico(ArrayList historico){
        this.historico = historico;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Endereco getEnd() {
        return end;
    }

    public void setEnd(String rua, String numero, String cep, String bairro, String cidade, String estado) {
        if(this.end == null)
            this.end = new Endereco(rua, numero, cep, bairro, cidade, estado);
        else{
            this.end.setRua(rua);
            this.end.setNumero(numero);
            this.end.setCep(cep);
            this.end.setBairro(bairro);
            this.end.setCidade(cidade);
            this.end.setEstado(estado);
        }
    }
    
    public void setEnd(Endereco end){
        this.end = end;
    }

    public boolean isAtivo() {
        return ativo;
    }
    
    public void setAtivo(boolean valor) {
        this.ativo = valor;
    }

    public Double getSaldoDevedor() {
        return saldoDevedor;
    }

    public void setSaldoDevedor(Double saldoDevedor) {
        this.saldoDevedor = saldoDevedor;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefone() {
        return telefone;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id){
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    
    public Calendar getUltimoAtendimento() {
        return ultimoAtendimento;
    }
    
    public String getUltimoAtendimentoString() {
        return this.calendarParaString(ultimoAtendimento);
    }
    
    public Calendar getProximoAtendimento() {
        return proximoAtendimento;
    }
    public String getProximoAtendimentoString() {
        return this.calendarParaString(proximoAtendimento);
    }

    public void setProximoAtendimento(Calendar proximoAtendimento) {
        this.proximoAtendimento = proximoAtendimento;
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

    public Integer getIdade() {
        return idade;
    }

    public Calendar getNascimento() {
        return nascimento;
    }
    public String getNascimentoString() {
        return this.calendarParaString(nascimento);
    }

    public void setNascimento(Calendar nascimento) {
        this.nascimento = nascimento;
        this.gerarIdade();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }
    
    
    
    
}
