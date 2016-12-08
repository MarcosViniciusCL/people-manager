/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people.manager.persistencia;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author marcos
 */
public class GerenciaProperties {
    /**
     * Pega o arquivo de propriedade da pasta.
     * @param arquivo
     * @return
     * @throws IOException 
     */
    public static Properties getProp(String arquivo) throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream("./properties/"+arquivo);
		props.load(file);
		return props;

    }
}
