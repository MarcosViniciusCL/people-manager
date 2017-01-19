/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people.manager.view;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import people.manager.controller.Controller;
import people.manager.exception.ArquivoModificadoException;
import people.manager.model.Usuario;
import people.manager.persistencia.ArquivoDAO;
import people.manager.persistencia.ConnectionFactory;

/**
 *
 * @author marcos
 */
public class Main {

    private static TelaInicial ti;
    private static ArrayList<JFrame> janelas;

    public static void main(String[] args) throws ArquivoModificadoException, IOException {
        //Teste de instancia aberta;
        if (new File("lock").exists()) {
            JOptionPane.showMessageDialog(null, "O programa já está sendo executado em outra instancia.");
            System.exit(1);
        }
        
        Controller.criarLock();
        
        janelas = new ArrayList<>();
        TelaLogin t = new TelaLogin("Login");
        t.setLocationRelativeTo(null);
        t.setVisible(true);
        //Teste primeiro execução;
        if (!new File("database.db").exists()) {
            ConnectionFactory.criarBanco();
            Usuario user = new Usuario("CLEAR", "CLEAR", 1);
            TelaNovoUsuario tnu = new TelaNovoUsuario("Primeiro Usuario", user);
            tnu.setLocationRelativeTo(null);
            tnu.setVisible(true);
        }
    }

    public static void setTI(TelaInicial ti) {
        Main.ti = ti;
    }

    public static TelaInicial getTI() {
        return Main.ti;
    }

    public static void guardarJanela(JFrame jframe) {
        janelas.add(jframe);
    }

    public static void fecharTudo() {
        Main.janelas.forEach((j) -> {
            j.dispose();
        });
        janelas.clear();
    }

    public static void remover(JFrame j) {
        janelas.remove(j);
    }
}
