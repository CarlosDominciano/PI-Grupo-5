/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ingresse.cluster;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.github.britooo.looca.api.core.Looca;
import com.mycompany.ingresse.coleta.dados.Conexao;

public class Logs {

    private String nome_arquivo;
    private LocalDateTime dataAtual;
    private File arquivo;
    private File diretorio;
    private Looca comps = new Looca();
    private Conexao connect = new Conexao();
    private String path;

    public Logs(String path) {
        this.path = path;
    }

    public void info(String texto) {
        gerarLog("INFO", texto);
    }

    public void alerta(String texto) {
        gerarLog("ALERTA", texto);
    }

    public void erro(String texto) {
        gerarLog("ERRO", texto);
    }

    public void severo(String texto) {
        gerarLog("SEVERO", texto);
    }

    private void gerarLog(String nivel, String texto) {
        try {
            diretorio = new File(path);
            // /home/aluno/Desktop/Grupo-5-Ingresse/
            if (!diretorio.exists()) {
                diretorio.mkdir();
            }
            dataAtual = LocalDateTime.now();
            //this.nome_arquivo = "ingresse" + dataAtual.getHour() + "" + dataAtual.getDayOfMonth() + "" + dataAtual.getMonthValue() + "_" + dataAtual.getYear();
            this.nome_arquivo = String.format("ingresse_cache");
            //arquivo = new File(path + nome_arquivo + ".txt");
            arquivo = new File(path + "_" + nome_arquivo + ".txt");
            // /home/aluno/Desktop/Grupo-5-Ingresse/

            if (!arquivo.exists()) {
                arquivo.createNewFile();
            }

            FileWriter fw = new FileWriter(arquivo, true);
            BufferedWriter bw = new BufferedWriter(fw);
            if (arquivo.length() == 0) {
                bw.write("---------- Log da maquina ----------");
                bw.newLine();
                bw.write("Sistema operacional: " + comps.getSistema().getSistemaOperacional());
                bw.newLine();
                //bw.write("---------------------------------------------------------");
            }

            bw.newLine();
            // Date Time Tipo Texto
            bw.write("[" + dataAtual.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + "][" + nivel + "]: " + texto);
            bw.newLine();

            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Error:" + e.getMessage());
        }
    }
}
