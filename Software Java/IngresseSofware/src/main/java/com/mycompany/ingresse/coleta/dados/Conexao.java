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
    private BasicDataSource dataSource;
    private JdbcTemplate jdbc;
    public Conexao(){
    this.dataSource = new BasicDataSource();
    dataSource​.setDriverClassName("org.h2.Driver");
    dataSource​.setUrl("jdbc:h2:file:./meu_banco");
    dataSource​.setUsername("sa");
    dataSource​.setPassword("");
    this.jdbc = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getJdbc() {
        return jdbc;
    }


    
    
}
