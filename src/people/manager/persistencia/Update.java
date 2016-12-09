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

    private static boolean download(String endereco, String arquivo) {
        int c;
        try {
            //cria URL
            URL url = new URL(endereco);
            //abre uma conexao na url criada àcima
            InputStream is = url.openStream();
            try (FileOutputStream fileOut = new FileOutputStream(arquivo)) {
                int umByte;
                while ((umByte = is.read()) != -1) {
                    fileOut.write(umByte);
                }
            }

            System.out.println("Arquivo baixado com sucesso");

        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static boolean verificarAtualização() {
        if (download("http://peoplemanager.esy.es/peoplemanager/dist/info.properties", "./properties/Dinfo.properties")) {
            try {
                Properties propAtual = GerenciaProperties.getProp("info.properties");
                Properties propDown = GerenciaProperties.getProp("Dinfo.properties");
                if (Double.parseDouble(propDown.getProperty("versao")) > Double.parseDouble(propAtual.getProperty("versao"))) {
                    return true;
                }
            } catch (IOException ex) {
                Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    private static String gerarNome(String nomeArquivo) {
        String[] s = nomeArquivo.split("/");
        return s[s.length - 1];
    }

    public static boolean baixarAtualizacao() {
        return download("http://peoplemanager.esy.es/peoplemanager/dist/PeopleManager.zip", "PeopleManager.zip");
    }
}
