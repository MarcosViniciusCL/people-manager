/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people.manager.model;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import people.manager.controller.Controller;
import people.manager.exception.UsuarioNaoExisteException;
import people.manager.persistencia.UsuarioDAO;

/**
 *
 * @author marcos
 */
public class Usuario {

    private String user;
    private String senha;
    private Integer level;

    public Usuario(String user, String senha, Integer level) {
        this.user = user;
        this.senha = senha;
        this.level = level;
    }

    /**
     *
     * @param senhaTest
     * @return
     */
    public boolean testarSenha(String senhaTest) {

        String senhaHash = null;
        try {
            senhaHash = Controller.criaHash(senhaTest);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.senha.equals(senhaHash);
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

}
