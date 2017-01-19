/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people.manager.controller;

import people.manager.exception.UsuarioNaoExisteException;
import people.manager.exception.UsuarioExistenteException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import people.manager.model.Usuario;
import people.manager.persistencia.UsuarioDAO;

/**
 *
 * @author marcos
 */
public class ControllerUsuario {
    
    public static void adicionarUsuario(String user, String senha, Integer nivel) throws UsuarioExistenteException{
        Usuario usuario = UsuarioDAO.obterUsuario(user);
        if(usuario != null)
            throw new UsuarioExistenteException();
        String senhaHash = null;
        try {
            senhaHash = Controller.criaHash(senha);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Usuario u = new Usuario(user, senhaHash, nivel);
        UsuarioDAO.create(u);
    }
    
    /**
     *
     * @param user
     * @return
     * @throws UsuarioNaoExisteException
     */
    public static Usuario obterUsuario(String user) throws UsuarioNaoExisteException {
        Usuario u = UsuarioDAO.obterUsuario(user);
        if(u == null)
            throw new UsuarioNaoExisteException();
        return u;
    }
    
    public static void editarUsuario(String user, String senha, Integer nivel){
        Usuario u = UsuarioDAO.obterUsuario(user);
        try {
            u.setSenha(Controller.criaHash(senha));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(ControllerUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        u.setLevel(nivel);
        UsuarioDAO.edita(u);
    }
    
    
}
