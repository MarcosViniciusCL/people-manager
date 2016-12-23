/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people.manager.controller;

import people.manager.exception.ImpossivelRemoverException;
import people.manager.exception.HorarioCheioException;
import java.util.ArrayList;
import people.manager.exception.AtendimentoNaoEncontradoException;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import people.manager.model.Atendimento;
import people.manager.model.Cliente;
import people.manager.model.Funcionario;
import people.manager.persistencia.AtendimentoDAO;

/**
 *
 * @author marcos
 */
public class ControllerAtendimento {

    public static void criar(String comentario, Integer idFuncionario, Integer idCliente, Calendar dataAtendimento, Double valor) throws HorarioCheioException {
        if (!funcionarioLivre(idFuncionario, dataAtendimento)) {
            throw new HorarioCheioException();
        }
        Atendimento a = new Atendimento(comentario, idFuncionario, idCliente, dataAtendimento, valor);
        AtendimentoDAO.create(a);
    }

    /**
     * Retorna os atendimento que estão entre o intevalo de data inicial e
     * final.
     *
     * @param dataInicial
     * @param dataFinal
     * @return
     */
    public static ArrayList buscar(Calendar dataInicial, Calendar dataFinal) {
        int quant = AtendimentoDAO.quantidadeBanco();
        Atendimento obj;
        ArrayList elementos = new ArrayList();

        for (int i = 0; i <= quant; i++) {
            try {
                obj = buscarAtendimentoID(i);
                if (obj != null && obj.getDataAtendimento().compareTo(dataInicial) >= 0 && obj.getDataAtendimento().compareTo(dataFinal) <= 0) {
                    elementos.add(obj);
                }
            } catch (AtendimentoNaoEncontradoException ex) {
            }
        }
        return elementos;
    }

    public static Atendimento buscarAtendimentoID(int busca) throws AtendimentoNaoEncontradoException {
        Atendimento at = AtendimentoDAO.buscaID(busca);
        return at;
    }

    public static ArrayList buscaFiltro(Calendar dataInicial, Calendar dataFinal, Cliente cliente, Funcionario funcionario) throws AtendimentoNaoEncontradoException {
        if (dataInicial != null && dataFinal == null && cliente != null && funcionario == null) {
            ArrayList<Atendimento> to = buscar(dataInicial);
            ArrayList<Atendimento> env = new ArrayList();
            to.stream().filter((obj) -> (Objects.equals(obj.getIdCliente(), cliente.getId()))).forEachOrdered((obj) -> {
                env.add(obj);
            });
            return env;
        } else if (dataInicial != null && dataFinal == null && cliente == null && funcionario != null) {
            ArrayList<Atendimento> to = buscar(dataInicial);
            ArrayList<Atendimento> env = new ArrayList();
            to.stream().filter((obj) -> (Objects.equals(obj.getIdAtendente(), funcionario.getId()))).forEachOrdered((obj) -> {
                env.add(obj);
            });
            return env;
        } else if (dataInicial != null && dataFinal != null && cliente != null && funcionario == null) {
            ArrayList<Atendimento> to = buscar(dataInicial, dataFinal);
            ArrayList<Atendimento> env = new ArrayList();
            to.stream().filter((obj) -> (Objects.equals(obj.getIdCliente(), cliente.getId()))).forEachOrdered((obj) -> {
                env.add(obj);
            });
            return env;
        } else if (dataInicial != null && dataFinal != null && cliente == null && funcionario != null) {
            ArrayList<Atendimento> to = buscar(dataInicial, dataFinal);
            ArrayList<Atendimento> env = new ArrayList();
            to.stream().filter((obj) -> (Objects.equals(obj.getIdAtendente(), funcionario.getId()))).forEachOrdered((obj) -> {
                env.add(obj);
            });
            return env;
        } else if(dataInicial != null && dataFinal != null && cliente != null && funcionario != null){
            ArrayList<Atendimento> to = buscar(dataInicial, dataFinal);
            ArrayList<Atendimento> env = new ArrayList();
            to.stream().filter((obj) -> (Objects.equals(obj.getIdAtendente(), funcionario.getId())) && Objects.equals(obj.getIdCliente(), cliente.getId())).forEachOrdered((obj) -> {
                env.add(obj);
            });
            return env;
        } else{
            return new ArrayList();
        }
    }

    public static ArrayList buscar(Calendar data) throws AtendimentoNaoEncontradoException {
        int quant = AtendimentoDAO.quantidadeBanco();
        Atendimento obj;
        ArrayList elementos = new ArrayList();
        for (int i = 0; i <= quant; i++) {
            obj = buscarAtendimentoID(i);
            if (obj != null && obj.getDataAtendimento().get(Calendar.DAY_OF_YEAR) == data.get(Calendar.DAY_OF_YEAR) && obj.getDataAtendimento().get(Calendar.MONTH) == data.get(Calendar.MONTH) && obj.getDataAtendimento().get(Calendar.YEAR) == data.get(Calendar.YEAR)) {
                elementos.add(obj);
            }
        }
        return elementos;
    }

    /**
     * Busca pelo ID do funcionario;
     *
     * @param id
     * @return
     */
    public static ArrayList buscar(Integer id) {
        int quant = AtendimentoDAO.quantidadeBanco();
        Atendimento obj;
        ArrayList elementos = new ArrayList();
        for (int i = 0; i <= quant; i++) {
            try {
                obj = buscarAtendimentoID(i);
                if (obj != null && Objects.equals(obj.getIdAtendente(), id)) {
                    elementos.add(obj);
                }
            } catch (AtendimentoNaoEncontradoException ex) {
                Logger.getLogger(ControllerAtendimento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return elementos;
    }

    /**
     * Busca pelo ID do cliente;
     *
     * @param cliente
     * @return
     */
    public static ArrayList buscar(Cliente cliente) {
        int quant = AtendimentoDAO.quantidadeBanco();
        Atendimento obj;
        ArrayList elementos = new ArrayList();
        for (int i = 0; i <= quant; i++) {
            try {
                obj = buscarAtendimentoID(i);
                if (obj != null && Objects.equals(obj.getIdCliente(), cliente.getId())) {
                    elementos.add(obj);
                }
            } catch (AtendimentoNaoEncontradoException ex) {
                Logger.getLogger(ControllerAtendimento.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return elementos;
    }

    public static boolean funcionarioLivre(Integer id, Calendar data) {
        ArrayList<Atendimento> atend = buscar(id);
        for (Atendimento obj : atend) {
            int anoObj = obj.getDataAtendimento().get(Calendar.YEAR);
            int mesObj = obj.getDataAtendimento().get(Calendar.MONTH);
            int diaObj = obj.getDataAtendimento().get(Calendar.DAY_OF_YEAR);
            int horaObj = obj.getDataAtendimento().get(Calendar.HOUR_OF_DAY);
            int minObj = obj.getDataAtendimento().get(Calendar.MINUTE);
            int ano = data.get(Calendar.YEAR);
            int mes = data.get(Calendar.MONTH);
            int dia = data.get(Calendar.DAY_OF_YEAR);
            int hora = data.get(Calendar.HOUR_OF_DAY);
            int min = data.get(Calendar.MINUTE);
//            int valor = Integer.parseInt(Controller.getConfiguracao().getProperty("tempo.min_entre_consul"));
            if (anoObj == ano && mesObj == mes && diaObj == dia && horaObj == hora && minObj == min) {
                return false;
            }
        }
        return true;
    }

    public static void remover(String id) throws ImpossivelRemoverException, AtendimentoNaoEncontradoException {
        Atendimento at = buscarAtendimentoID(Integer.parseInt(id));
        if( at == null || at.isAtendido())
            throw new ImpossivelRemoverException();
        AtendimentoDAO.remover(id);
    }
    
    public static void editar(Atendimento a){
        AtendimentoDAO.edita(a);
    }

    private static List filtrar(Calendar dataInicial, Calendar dataFinal, ArrayList<Atendimento> array) {
        ArrayList elementos = new ArrayList();
        for (Atendimento obj : array) {
            if (obj != null && obj.getDataAtendimento().compareTo(dataInicial) >= 0 && obj.getDataAtendimento().compareTo(dataFinal) <= 0) {
                elementos.add(obj);
            }
        }
        return elementos;
    }
}
