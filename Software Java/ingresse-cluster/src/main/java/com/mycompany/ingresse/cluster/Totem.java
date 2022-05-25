/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ingresse.cluster;

import java.util.Date;

/**
 *
 * @author isaque.santos@VALEMOBI.CORP
 */
public class Totem {
    private Integer idTotem;
    private Integer fkFilial;
    private Double ramTotal;
    private Double espacoDisco;
    private String processador;
    private Date dataCompra;
    private String idProcessador;
    private String serialDisco;
    private String hostname; 

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getSerialDisco() {
        return serialDisco;
    }

    public void setSerialDisco(String serialDisco) {
        this.serialDisco = serialDisco;
    }

    public Totem() {
    }
    

    public Totem(Integer idTotem, Integer fkFilial, Double ramTotal, Double espacoDisco, String processador, Date dataCompra, String idProcessador) {
        this.idTotem = idTotem;
        this.fkFilial = fkFilial;
        this.ramTotal = ramTotal;
        this.espacoDisco = espacoDisco;
        this.processador = processador;
        this.dataCompra = dataCompra;
        this.idProcessador = idProcessador;
    }

    @Override
    public String toString() {
        return "Totem{" + "idTotem=" + idTotem + ", fkFilial=" + fkFilial + ", ramTotal=" + ramTotal + ", espacoDisco=" + espacoDisco + ", processador=" + processador + ", dataCompra=" + dataCompra + ", idProcessador=" + idProcessador + '}';
    }
    
    
    
    public Integer getIdTotem() {
        return idTotem;
    }

    public void setIdTotem(Integer idTotem) {
        this.idTotem = idTotem;
    }

    public Integer getFkFilial() {
        return fkFilial;
    }

    public void setFkFilial(Integer fkFilial) {
        this.fkFilial = fkFilial;
    }

    public Double getRamTotal() {
        return ramTotal;
    }

    public void setRamTotal(Double ramTotal) {
        this.ramTotal = ramTotal;
    }

    public Double getEspacoDisco() {
        return espacoDisco;
    }

    public void setEspacoDisco(Double espacoDisco) {
        this.espacoDisco = espacoDisco;
    }

    public String getProcessador() {
        return processador;
    }

    public void setProcessador(String processador) {
        this.processador = processador;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public String getIdProcessador() {
        return idProcessador;
    }

    public void setIdProcessador(String idProcessador) {
        this.idProcessador = idProcessador;
    }
    
}
