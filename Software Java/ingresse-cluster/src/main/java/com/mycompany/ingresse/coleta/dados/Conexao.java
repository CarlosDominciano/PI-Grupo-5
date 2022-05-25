/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ingresse.coleta.dados;

import java.util.List;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author isaque.santos@VALEMOBI.CORP
 */
public class Conexao {
    private BasicDataSource dataSourceAzure;
    private JdbcTemplate jdbcAzure;
    private BasicDataSource dataSourceLocal;
    private JdbcTemplate jdbcLocal;
    public Conexao(){
    this.dataSourceAzure = new BasicDataSource();
    dataSourceAzure​.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    dataSourceAzure​.setUrl("jdbc:sqlserver://ingresse-srv.database.windows.net:1433;database=ingresse-bd;user=ingresseAdmin@ingresse-srv;password={your_password_here};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");
    dataSource​Azure.setUsername("ingresseAdmin");
    dataSource​Azure.setPassword("2adsb#grupo5");
    this.jdbcAzure = new JdbcTemplate(dataSourceAzure);
    this.dataSourceLocal = new BasicDataSource();
    dataSourceLocal​.setDriverClassName("com.mysql.cj.jdbc.Driver");
    dataSourceLocal​.setUrl("jdbc:mysql://localhost:3306/ingresse-local?autoReconnect=true&useSSL=false");
    dataSource​Local.setUsername("root");
    dataSource​Local.setPassword("urubu100");
    this.jdbcLocal = new JdbcTemplate(dataSourceLocal);
    }

    public JdbcTemplate getJdbcAzure() {
        return jdbcAzure;
    }
    
    public JdbcTemplate getJdbcLocal() {
        return jdbcAzure;
    }
    


    
    
}
