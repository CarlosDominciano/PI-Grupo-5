/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ingressesofware;

import com.github.britooo.looca.api.core.Looca;
import com.mycompany.ingresse.coleta.dados.Componentes;
import com.mycompany.ingresse.coleta.dados.Conexao;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author diego.silva@VALEMOBI.CORP
 */
public class IngresseSoftware {

    public static void main(String[] args) throws ParseException {
        Componentes comps = new Componentes();
        Conexao connect = new Conexao();
        TelaLogin telaLogin = new TelaLogin();
        TelaLoginVM telaLoginVm = new TelaLoginVM();   
        SlackIntegrationTest slackAlert = new SlackIntegrationTest();
        SlackRelatorio slackRelatorio = new SlackRelatorio();
        SlackToken slackToken = new SlackToken();
        
        slackAlert.sendMessageToSlack("Iniciando...");
        slackRelatorio.sendRelatorio("Iniciando...");
        slackToken.sendToken("Iniciando...");
        Scanner leitor = new Scanner(System.in);
        
        System.out.println("Como você deseja acessar o ingresseSoftware?\n"
                + "1 - Interface gráfica\n"
                + "2 - Terminal\n"
                + "3 - Executando na vm (menos recurso)\n"
                + "Digite sua escolha: ");
        Integer changeTerminalOrSwing = leitor.nextInt();
        if (changeTerminalOrSwing == 1){
            telaLogin.setVisible(true);
        } else if(changeTerminalOrSwing == 2) {
            System.out.println("executando monitoria terminal...");
        } else if(changeTerminalOrSwing == 3){
            System.out.println("executando monitoria vm...");
            telaLoginVm.setVisible(true);
            
        } else {
            System.out.println("Digite um numero válido");
        }
        
        //Usuario usando = new Usuario(3,"Jose Silva","Jose@gmail.com","suporte",1,"teste");
       
        //connect.getJdbc().execute("INSERT INTO filial(email_corporativo,senha,cnpj) VALUES ('empresa@gmail.com','abobrinha','12345678901234')");
        //System.out.println(connect.getJdbc().queryForList("SELECT * FROM filial"));
        //System.out.println(comps.getHostname());

        //System.out.println(comps.getData());
        //telaLogin.setVisible(true);
          //System.out.println("temperatura: " + comps.getTemp());

        //connect.getJdbc().execute("INSERT INTO filial(email_corporativo,senha,cnpj) VALUES ('empresa@gmail.com','abobrinha','12345678901234')");
        //System.out.println(comps.getHostname());
         //Looca looca = new Looca();
        //System.out.println(looca.getSistema());
        
      
//        Selenium selenium = new Selenium();
//        
//       selenium.automacaoSelenium();
//        TelaPrincipal telaPrincipal = new TelaPrincipal(usando);
//        telaPrincipal.setVisible(true);
        //Double result = comps.getDisco().doubleValue()/1000000000;
        //Double result2 = (double) (Math.round(result*1.0/1.0));
        //System.out.println(comps.getDataTotem());
        
        // teste de stress
//        for (int i = 0; i < 10000000; i++) {
//            System.out.println(i);
//            System.out.println(i);
//            System.out.println(i);
//            System.out.println(i);
//            System.out.println(i);
//            System.out.println(i);
//            System.out.println(i);
//            System.out.println(i);
//            System.out.println(i);
//            System.out.println(i);
//            System.out.println(i);
//
//        }
    }
}
