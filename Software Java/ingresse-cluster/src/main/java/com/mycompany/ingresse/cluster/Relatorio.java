/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ingresse.cluster;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author isaque.santos@VALEMOBI.CORP
 */
public class Relatorio {
    private Integer idLog;
    private Integer fkTotem;
    private Integer pctgProcessador;
    private Integer pctgMemoriaUso;
    private Integer pctgDiscoUso;
    private Integer qtdProcessos;
    private Integer qtdServicos;
    private Double temp;
    private Date dataHora;
    private DateFormat format;

    public Relatorio(Integer fkTotem, Integer pctgProcessador, Integer pctgMemoriaUso, Integer pctgDiscoUso, Integer qtdProcessos, Integer qtdServicos, Double temp) {
        this.fkTotem = fkTotem;
        this.pctgProcessador = pctgProcessador;
        this.pctgMemoriaUso = pctgMemoriaUso;
        this.pctgDiscoUso = pctgDiscoUso;
        this.qtdProcessos = qtdProcessos;
        this.qtdServicos = qtdServicos;
        this.temp = temp;
        this.format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.dataHora = new Date();
    }
    public Relatorio(){
    
    }

    public Integer getIdLog() {
        return idLog;
    }

    public void setIdLog(Integer idLog) {
        this.idLog = idLog;
    }

    
    public Integer getFkTotem() {
        return fkTotem;
    }

    public void setFkTotem(Integer fkTotem) {
        this.fkTotem = fkTotem;
    }

    public Integer getPctgProcessador() {
        return pctgProcessador;
    }

    public void setPctgProcessador(Integer pctgProcessador) {
        this.pctgProcessador = pctgProcessador;
    }

    public Integer getPctgMemoriaUso() {
        return pctgMemoriaUso;
    }

    public void setPctgMemoriaUso(Integer pctgMemoriaUso) {
        this.pctgMemoriaUso = pctgMemoriaUso;
    }

    public Integer getPctgDiscoUso() {
        return pctgDiscoUso;
    }

    public void setPctgDiscoUso(Integer pctgDiscoUso) {
        this.pctgDiscoUso = pctgDiscoUso;
    }

    public Integer getQtdProcessos() {
        return qtdProcessos;
    }

    public void setQtdProcessos(Integer qtdProcessos) {
        this.qtdProcessos = qtdProcessos;
    }

    public Integer getQtdServicos() {
        return qtdServicos;
    }

    public void setQtdServicos(Integer qtdServicos) {
        this.qtdServicos = qtdServicos;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public String getDataHora() {
        return format.format(dataHora);
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    @Override
    public String toString() {
        return "Relatorio{" + "fkTotem=" + fkTotem + ", pctgProcessador=" + pctgProcessador + ", pctgMemoriaUso=" + pctgMemoriaUso + ", pctgDiscoUso=" + pctgDiscoUso + ", qtdProcessos=" + qtdProcessos + ", qtdServicos=" + qtdServicos + ", temp=" + temp + ", dataHora=" + dataHora + ", format=" + format + '}';
    }

    
}
