/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ingresse.coleta.dados;

import oshi.SystemInfo;
import oshi.software.os.OperatingSystem;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.Disco;
import com.github.britooo.looca.api.group.discos.Volume;
import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import java.time.format.DateTimeFormatter;
import oshi.software.os.OSFileStore;
import java.util.List;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;


/**
 *
 * @author isaque.santos@VALEMOBI.CORP
 */
public class Componentes {

    private String idProcessador;
    private Long discoUso;
    private Double temp;
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
    private String hostname;
    private Integer qtdServicos;
    private String serialDisco;
    private Looca componentes = new Looca();
    private final OperatingSystem os = new SystemInfo().getOperatingSystem();

    public Componentes() {
        this.processador = componentes.getProcessador().getIdentificador();
        this.ram = componentes.getMemoria().getTotal();
        this.disco = componentes.getGrupoDeDiscos().getTamanhoTotal();
        this.processos = componentes.getGrupoDeProcessos().getProcessos();
        this.qtdProcessos = 
                componentes.getGrupoDeProcessos().getTotalProcessos();
        this.servicosAtv = componentes.getGrupoDeServicos().getServicosAtivos();
        this.data = dataHora();
        this.processamento = componentes.getProcessador().getUso();
        this.memVolUso = componentes.getMemoria().getEmUso();
        this.memVolLivre = componentes.getMemoria().getDisponivel();
        this.hostname = this.os.getNetworkParams().getHostName();
        this.discoUso = calcDisco();
        this.temp = componentes.getTemperatura().getTemperatura();
        this.idProcessador = componentes.getProcessador().getId();
        this.qtdServicos = componentes.getGrupoDeServicos().getTotalDeServicos();
        this.serialDisco = componentes.getGrupoDeDiscos().getDiscos().get(0).getSerial();
    }

    public String getHostname() {
        return hostname;
    }

    public String getSerialDisco() {
        return serialDisco;
    }

    public void setSerialDisco(String serialDisco) {
        this.serialDisco = serialDisco;
    }
    
    
    
    private Long calcDisco(){
        List<Volume> vols = componentes.getGrupoDeDiscos().getVolumes();
        Long calc = 000L;
        for (Volume vol : vols) {
            calc = calc + vol.getDisponivel();
        }
        return disco - calc;
    }

    public Long getDiscoUso() {
        return discoUso;
    }

    public void setDiscoUso(Long discoUso) {
        this.discoUso = discoUso;
    }

    public Integer getQtdServicos() {
        return qtdServicos;
    }

    public void setQtdServicos(Integer qtdServicos) {
        this.qtdServicos = qtdServicos;
    }

    
    private String dataHora() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String getIdProcessador() {
        return idProcessador;
    }

    public void setIdProcessador(String idProcessador) {
        this.idProcessador = idProcessador;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
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
    
    public String getDataTotem() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
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
    public Integer regraTres(Long valorMenor, Long valorTotal){
        Long result = (valorMenor*100/valorTotal);
        return Integer.valueOf(result.toString());
    }
    
    

    @Override
    public String toString() {
        return "Componentes{" + "processador=" + processador + ", ram=" + ram + ", disco=" + disco + ", processos=" + processos + ", qtdProcessos=" + qtdProcessos + ", servicosAtv=" + servicosAtv + ", data=" + data + ", processamento=" + processamento + ", memVolUso=" + memVolUso + ", memVolLivre=" + memVolLivre + ", componentes=" + componentes + '}';
    }

}
