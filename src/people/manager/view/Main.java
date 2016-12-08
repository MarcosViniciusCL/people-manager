/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people.manager.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.JFrame;
import people.manager.controller.ControllerCliente;
import people.manager.exception.ArquivoModificadoException;
import people.manager.persistencia.GerenciaProperties;
import people.manager.persistencia.Update;

/**
 *
 * @author marcos
 */
public class Main {
    private static TelaInicial ti;
    private static ArrayList<JFrame> janelas;
    
    public static void main(String[] args) throws ArquivoModificadoException, IOException {
           janelas = new ArrayList<>();
      
//           TelaLogin t = new TelaLogin("Login");
//           t.setLocationRelativeTo(null);
//           t.setVisible(true);
    }
    
    public static void setTI(TelaInicial ti){
        Main.ti = ti;
    }
    
    public static TelaInicial getTI(){
        return Main.ti;
    }
    
    public static void guardarJanela(JFrame jframe){
        janelas.add(jframe);
    }
    
    public static void fecharTudo(){
        for(JFrame j : Main.janelas){
            j.dispose();
        }
        janelas.clear();
    }
    
    public static void remover(JFrame j){
        janelas.remove(j);
    } 
}
