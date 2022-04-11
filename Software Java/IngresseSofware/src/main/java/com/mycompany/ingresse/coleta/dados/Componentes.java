/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ingresse.coleta.dados;

import com.github.britooo.looca.api.core.Looca;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author isaque.santos@VALEMOBI.CORP
 */
public class Componentes {

    private String processador;
    private Long ram;
    private Long disco;
    private List processos;
    private Integer qtdProcessos;
    private List servicosAtv;
    private String data;
    private Double processamento;
    private Long memVolUso;
    private Long memVolLivre;
    Looca componentes = new Looca();

    public Componentes() {
        this.processador = componentes.getProcessador().getIdentificador();
        this.ram = componentes.getMemoria().getTotal();
        this.disco = componentes.getGrupoDeDiscos().getTamanhoTotal();
        this.processos = componentes.getGrupoDeProcessos().getProcessos();
        this.qtdProcessos = componentes.getGrupoDeProcessos().getTotalProcessos();
        this.servicosAtv = componentes.getGrupoDeServicos().getServicosAtivos();
        this.data = dataHora();
        this.processamento = componentes.getProcessador().getUso();
        this.memVolUso = componentes.getMemoria().getEmUso();
        this.memVolLivre = componentes.getMemoria().getDisponivel();

    }

    private String dataHora() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String getProcessador() {
        return processador;
    }

    public Long getRam() {
        return ram;
    }

    public Long getDisco() {
        return disco;
    }

    public List getProcessos() {
        return processos;
    }

    public Integer getQtdProcessos() {
        return qtdProcessos;
    }

    public List getServicosAtv() {
        return servicosAtv;
    }

    public String getData() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public Double getProcessamento() {
        return processamento;
    }

    public Long getMemVolUso() {
        return memVolUso;
    }

    public Long getMemVolLivre() {
        return memVolLivre;
    }
    
    

    @Override
    public String toString() {
        return "Componentes{" + "processador=" + processador + ", ram=" + ram + ", disco=" + disco + ", processos=" + processos + ", qtdProcessos=" + qtdProcessos + ", servicosAtv=" + servicosAtv + ", data=" + data + ", processamento=" + processamento + ", memVolUso=" + memVolUso + ", memVolLivre=" + memVolLivre + ", componentes=" + componentes + '}';
    }

}
