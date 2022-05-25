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
import java.util.ArrayList;
import java.util.logging.Level;


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
        this.ram = 0L;
        this.disco = 0L;
        this.processos = new ArrayList();
        this.qtdProcessos = 0;
        this.servicosAtv = new ArrayList();
        this.data = dataHora();
        this.processamento = 0.0;
        this.memVolUso = 0L;
        this.memVolLivre = 0L;
        this.hostname = this.os.getNetworkParams().getHostName();
        this.discoUso = 0L;
        this.temp = 0.0;
        this.idProcessador = componentes.getProcessador().getId();
        this.qtdServicos = 0;
        this.serialDisco = componentes.getGrupoDeDiscos().getDiscos().get(0).getSerial();
    }

    public String getHostname() {
        return this.hostname;
    }

    public String getSerialDisco() {
        return this.serialDisco;
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
        return getDisco() - calc;
    }

    public Long getDiscoUso() {
        return calcDisco();
    }

    public void setDiscoUso(Long discoUso) {
        this.discoUso = discoUso;
    }

    public Integer getQtdServicos() {
        return componentes.getGrupoDeServicos().getTotalDeServicos();
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
        return this.idProcessador;
    }

    public void setIdProcessador(String idProcessador) {
        this.idProcessador = idProcessador;
    }

    public Double getTemp() {
        return componentes.getTemperatura().getTemperatura();
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public String getProcessador() {
        return this.processador;
    }

    public Long getRam() {
        return componentes.getMemoria().getTotal();
    }

    public Long getDisco() {
        return componentes.getGrupoDeDiscos().getTamanhoTotal();
    }

    public List getProcessos() {
        return componentes.getGrupoDeProcessos().getProcessos();
    }

    public Integer getQtdProcessos() {
        return componentes.getGrupoDeProcessos().getTotalProcessos();
    }

    public List getServicosAtv() {
        return componentes.getGrupoDeServicos().getServicosAtivos();
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
        return componentes.getProcessador().getUso();
    }

    public Long getMemVolUso() {
        return componentes.getMemoria().getEmUso();
    }

    public Long getMemVolLivre() {
        return componentes.getMemoria().getDisponivel();
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
