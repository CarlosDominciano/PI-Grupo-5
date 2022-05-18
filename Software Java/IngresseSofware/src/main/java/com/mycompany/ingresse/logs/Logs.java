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

    public void info(String texto) {
        gerar_Log("INFO", texto);
    }

    public void warning(String texto) {
        gerar_Log("AVISO", texto);
    }

    public void severe(String texto) {
        gerar_Log("SEVERO", texto);
    }

    private void gerar_Log(String nivel, String texto) {
        try {
            diretorio = new File("/home/aluno/Desktop/PI-Grupo-5/");
            if (!diretorio.exists()) {
                diretorio.mkdir();
            }
            dataAtual = LocalDateTime.now();
            this.nome_arquivo = "Log Ingresse" + dataAtual.getHour() + "" + dataAtual.getDayOfMonth() + "" + dataAtual.getMonthValue() + "_" + dataAtual.getYear();
            arquivo = new File("/home/aluno/Desktop/PI-Grupo-5/" + nome_arquivo + ".txt");

            if (!arquivo.exists()) {
                arquivo.createNewFile();
            }

            FileWriter fw = new FileWriter(arquivo, true);
            BufferedWriter bw = new BufferedWriter(fw);
            if (arquivo.length() == 0) {
                bw.write("---------- Logs ingresse ----------");
                bw.newLine();
            }

            bw.newLine();
            bw.write("[" + nivel + "]:" + texto);

            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Error:" + e.getMessage());
        }
    }
}
