/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package people.manager.persistencia;

import people.manager.exception.ArquivoModificadoException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArquivoDAO {

    public static String leitor(String path) throws IOException, ArquivoModificadoException {
        try (BufferedReader buffRead = new BufferedReader(new FileReader(path))) {
            StringBuilder texto = new StringBuilder();
            String linha = buffRead.readLine();
            if (testeModificação(path, linha)) {
                throw new ArquivoModificadoException();
            }
            while (true) {
                linha = buffRead.readLine();
                if (linha == null) {
                    break;
                }
                texto.append(linha);
            }
            return texto.toString();
        }
    }

    public static void escritor(String path, String texto) throws IOException {
        OutputStream bytes = new FileOutputStream(path, true); // passado "true" para gravar no mesmo arquivo
        OutputStreamWriter chars = new OutputStreamWriter(bytes);

        try (BufferedWriter strings = new BufferedWriter(chars)) {
            if (lengthFile(path) == 0) {
                strings.write("::::::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
            }
            strings.write(texto + "\n");
        }
        long l = lastModified(path);
        escritorInicio(path, l + ":" + ArquivoDAO.lengthFile(path));
        setModified(path, l);
    }

    private static void escritorInicio(String path, String texto) throws IOException {
        File f = new File(path);
        // Seek to end of file
        try (RandomAccessFile raf = new RandomAccessFile(f, "rw")) {
            raf.seek(0);
            raf.writeBytes(texto);
        }
    }

    public static long lastModified(String path) {
        File arquivo = new File(path);
        if (arquivo.exists()) {
            return arquivo.lastModified();
        }
        return 0;
    }

    public static long lengthFile(String path) {
        File arquivo = new File(path);
        if (arquivo.exists()) {
            return arquivo.length();
        }
        return 0;
    }

    private static void setModified(String path, long d) {
        File arquivo = new File(path);
        if (arquivo.exists()) {
            arquivo.setLastModified(d);
        }
    }

    private static boolean testeModificação(String path, String test) {
        String[] s = test.split(":");
        long testMod = Long.parseLong(s[0]);
        long tamanhoArq = Long.parseLong(s[1]);
        long lastMod = lastModified(path);
        long lengthF = lengthFile(path);
        return testMod != lastMod || tamanhoArq != lengthF;
    }

    public static boolean isModificado(String path) {
        if (new File(path).exists()) {
            try (BufferedReader buffRead = new BufferedReader(new FileReader(path))) {
                StringBuilder texto = new StringBuilder();
                String linha = buffRead.readLine();
                if (testeModificação(path, linha)) {
                    return true;
                }
            } catch (IOException ex) {
                Logger.getLogger(ArquivoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public static void remover(String path) {
        File arquivo = new File(path);
        if (arquivo.exists()) {
            arquivo.delete();
        }
    }

    public static void copiar(String origem, String destino) {
        try {
            File origemV = new File(origem);
            FileInputStream fis = new FileInputStream(origemV);
            File destinoV = new File(destino);
            FileOutputStream fos = new FileOutputStream(destinoV);

            int count;
            byte[] bytes = new byte[1024];

            while ((count = fis.read(bytes)) >= 0) {
                fos.write(bytes, 0, count);
            }

        } catch (IOException ex) {

        }
    }
    
    public static File novoArquivo(String caminho){
        File arquivo = new File(caminho);
        try {
            arquivo.createNewFile();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ArquivoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ArquivoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arquivo;
    }
}
