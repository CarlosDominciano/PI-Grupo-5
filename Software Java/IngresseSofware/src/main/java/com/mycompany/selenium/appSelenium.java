/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.selenium;

import com.mycompany.ingresse.coleta.dados.Componentes;
import com.mycompany.ingresse.coleta.dados.Conexao;
import java.text.ParseException;

/**
 *
 * @author diego.silva@VALEMOBI.CORP
 */
public class appSelenium {
    
   Componentes comps = new Componentes();
    Conexao connect = new Conexao();

    public static void main(String[] args) throws ParseException {
        Selenium selenium = new Selenium();
        System.out.println("Buscando estréias de filmes...");
        selenium.automacaoSelenium();
       
    }
 
}
