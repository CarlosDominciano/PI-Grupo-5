/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ingressesofware;

import com.mycompany.ingresse.coleta.dados.Componentes;
import com.mycompany.ingresse.coleta.dados.Conexao;
import java.util.List;

/**
 *
 * @author diego.silva@VALEMOBI.CORP
 */
public class IngresseSoftware {

    public static void main(String[] args) {
        Componentes comps = new Componentes();
        Conexao connect = new Conexao();
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.setVisible(true);
        //connect.getJdbc().execute("INSERT INTO filial(email_corporativo,senha,cnpj) VALUES ('empresa@gmail.com','abobrinha','12345678901234')");
        System.out.println(comps.getHostname());
    }
}
