package people.manager.persistencia;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe responsável por realizar o update da aplicação. Faz download do
 * arquivo necessário e atualiza as bibliotecas.
 *
 * @author cassio
 */
public class Update {
    
    private static void download(String endereco, String arquivo) {
        int c;
        try {
            //cria URL
            URL url1 = new URL(endereco);
            //abre uma conexao na url criada àcima
            URLConnection con = url1.openConnection();
            //tenta conectar.
            con.connect();
            try ( //arquivo de saida
                    FileOutputStream fileOut = new FileOutputStream(arquivo)) {
                do {
                    //le o byte
                    c = con.getInputStream().read();
                    //escreve o byte no arquivo saida
                    fileOut.write(c);
                } while (c != -1);
            }

            System.out.println("Arquivo baixado com sucesso");

        } catch (IOException e) {
            System.out.println("erro");
        }
    }

    public static boolean verificarAtualização() {
//        download("https://github.com/jimmyfrasche/txt/blob/master/LICENSE", "./properties/Dinfo.properties");
        try {
            Properties propAtual = GerenciaProperties.getProp("info.properties");
            Properties propDown = GerenciaProperties.getProp("Dinfo.properties");
            if(Double.parseDouble(propDown.getProperty("versao")) > Double.parseDouble(propAtual.getProperty("versao")))
                return true;
        } catch (IOException ex) {
            Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;    
    }
    
    private static String gerarNome(String nomeArquivo){
        String[] s = nomeArquivo.split("/");
        return s[s.length-1];
    }
}
