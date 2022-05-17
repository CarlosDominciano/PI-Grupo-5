package com.mycompany.ingresse.logs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Logs {

    String nome_arquivo;
    LocalDateTime dataAtual;
    File arquivo;
    File diretorio;

    public Logs() {

    }

    public void info(String texto) throws IOException {
        gerar_Log("INFO", texto);
    }

    public void warning(String texto) throws IOException {
        gerar_Log("AVISO", texto);
    }

    public void severe(String texto) throws IOException {
        gerar_Log("SEVERO", texto);
    }

    private void gerar_Log(String nivel, String texto) throws IOException {
       // diretorio = new File("C:\\PI-Grupo-5");
        if (!diretorio.exists()) {
            diretorio.mkdir();
        }
        dataAtual = LocalDateTime.now();
        this.nome_arquivo = "Ingresse" + dataAtual.getDayOfMonth() + "_" + dataAtual.getMonthValue() + "_" + dataAtual.getYear();
       // arquivo = new File("C:\\PI-Grupo-5\\" + nome_arquivo + ".txt");

        if (!arquivo.exists()) {
            arquivo.createNewFile();
        }

        FileWriter fw = new FileWriter(arquivo, true);
        BufferedWriter bw = new BufferedWriter(fw);
        if (arquivo.length() == 0) {
            bw.write("@@@@@@@  Log Ingresse  @@@@@@");
            bw.newLine();
        }
//        if(arquivo.){
//            bw.write("aaaaaaaaaaaaaaaaaaaaaaaaa");
//        }
        bw.newLine();
        bw.write("[" + nivel + "]:" + texto);

        bw.close();
        fw.close();

    }
}
