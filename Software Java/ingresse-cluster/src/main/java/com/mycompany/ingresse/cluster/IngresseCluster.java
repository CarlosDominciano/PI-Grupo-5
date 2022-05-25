/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.ingresse.cluster;

import com.github.britooo.looca.api.core.Looca;
import com.mycompany.ingresse.coleta.dados.Componentes;
import com.mycompany.ingresse.coleta.dados.Conexao;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

/**
 *
 * @author isaque.santos@VALEMOBI.CORP
 */
public class IngresseCluster {
    Usuario sessao;
    Totem maquina;
    String path;
    public static void main(String[] args) {
        IngresseCluster inicio = new IngresseCluster();
        inicio.login();
        
    }
    
        public void login() {
        Scanner leitorUser = new Scanner(System.in);
        Scanner leitorSenha = new Scanner(System.in);
        Scanner leitorPath = new Scanner(System.in);
        Conexao connect = new Conexao();
        System.out.println("Login: ");
        String login = leitorUser.nextLine();
        System.out.println("Senha: ");
        String senha = leitorSenha.nextLine();
        System.out.println("Caminho absoluto logs fisicos: ");
        path = leitorPath.nextLine();
        List<Usuario> user = connect.getJdbc().query(String.format("SELECT * FROM usuario WHERE email_usuario='%s'", login), new BeanPropertyRowMapper<>(Usuario.class));
        if (user.isEmpty() == false) {
            if (user.get(0).getSenha().equals(senha)) {
                if (user.get(0).getTipo_acesso().equals("suporte") || user.get(0).getTipo_acesso().equals("gerente")) {
                    this.sessao = user.get(0);
                    verificar();
                }
            }
        }
    }
        public void verificar(){
            Conexao connect = new Conexao();
            Componentes comps = new Componentes();
            List<Totem> verificacaoTotem = connect.getJdbc().query(String.format("SELECT * FROM totem WHERE id_processador='%s' AND serial_disco='%s' AND hostname='%s'",comps.getIdProcessador(),comps.getSerialDisco(), comps.getHostname()), new BeanPropertyRowMapper<>(Totem.class));
            if(sessao.getFkFilial() == verificacaoTotem.get(0).getFkFilial()){
                this.maquina = verificacaoTotem.get(0);
                menu();
            }
            if(verificacaoTotem.isEmpty()){
                System.out.println("Totem fora dos registros, realizando cadastro...");
                Double discoDouble = (double) (Math.round((comps.getDisco()/1000000000)*1.0/1.0));
                Double ramDouble = (double) (Math.round((comps.getRam()/1000000000)*1.0/1.0));
                connect.getJdbc().update("INSERT INTO totem(fkFilial, ram_total, espaco_disco, processador, data_compra, id_processador, serial_disco, hostname) VALUES (?,?,?,?,?,?,?,?)", sessao.getFkFilial(),ramDouble,discoDouble,comps.getProcessador(),comps.getDataTotem(),comps.getIdProcessador(), comps.getSerialDisco(),comps.getHostname());
                List<Totem> verificarNovoTotem = connect.getJdbc().query(String.format("SELECT * FROM totem WHERE id_processador='%s' AND serial_disco='%s' AND hostname='%s'",comps.getIdProcessador(),comps.getSerialDisco(), comps.getHostname()), new BeanPropertyRowMapper<>(Totem.class));
                this.maquina = verificarNovoTotem.get(0);
                menu();
            }
            if(verificacaoTotem.isEmpty() == false && verificacaoTotem.get(0).getFkFilial() != sessao.getFkFilial()){
                System.out.println("Acesso negado");
                login();
            }
        }
        
        public void menu(){
            Boolean continuar = true;
            while (continuar) {
                
                System.out.println("Iniciar monitoramento: 1");
                System.out.println("Enviar relatório: 2");
                System.out.println("Sair: 3");
                System.out.println("Selecione a opção desejada: ");

                Scanner leitor = new Scanner(System.in);
                Integer escolhaUser = leitor.nextInt();
                switch (escolhaUser) {
                    case 1:
                        autoMonitorar();
                        break;
                    case 2:
                        System.out.println("Enviando novo relatório...");
                        gerarNovoRelatorio(maquina);
                        break;
                    case 3:

                        continuar = false;
                        break;
                    default:
                        System.out.println("Digite uma opção válida");
                        System.out.println("-----------------------------");

                }
            }
        }
        
        public void gerarNovoRelatorio(Totem vinculo){
            Componentes compos = new Componentes();
            Looca looca = new Looca();
            Conexao connect = new Conexao();
            SlackRelatorio slackRelatorio = new SlackRelatorio();
            Relatorio logAtual = new Relatorio(vinculo.getIdTotem(), compos.getProcessamento().intValue(), compos.regraTres(compos.getMemVolUso(), compos.getRam()), compos.regraTres(compos.getDiscoUso(), compos.getDisco()), compos.getQtdProcessos(), compos.getQtdServicos(), compos.getTemp());
             connect.getJdbc().update("INSERT INTO logs(fkTotem,pctg_processador,pctg_memoria_uso,pctg_disco_uso,qtd_processos,qtd_servicos,temp,data_hora) VALUES (?,?,?,?,?,?,?,?)", logAtual.getFkTotem(),logAtual.getPctgProcessador(),logAtual.getPctgMemoriaUso(),logAtual.getPctgDiscoUso(),logAtual.getQtdProcessos(),logAtual.getQtdServicos(),logAtual.getTemp(),logAtual.getDataHora());
             slackRelatorio.sendRelatorio(String.format("%s\n"
              + "_____________________\n"
              + "Relatório do totem:\n\n "
              + "Sistema: %s\n "
              + "Processador: %s\n "
              + "Processador em uso: %s\n "
              + "Memória em uso: %s\n "
              + "Disco em uso: %s\n "
              + "Quantidade de processos: %s\n "
              + "Quantidade de serviços: %s\n " 
              + "Temperatura: %s ",
              logAtual.getDataHora().toString(),
              looca.getSistema().toString(),
              looca.getProcessador().toString(),
              logAtual.getPctgProcessador().toString(),
              logAtual.getPctgMemoriaUso().toString(),
              logAtual.getPctgDiscoUso().toString(),
              logAtual.getQtdProcessos().toString(),
              logAtual.getQtdServicos().toString(),
              logAtual.getTemp().toString()));
        }

    void autoMonitorar(){
        Timer timer = new Timer();
        Long segundo = 1000L;
        Long minuto = segundo * 60;
        Long hora = minuto * 60;
        Logs logs = new Logs(path);
        Componentes comps = new Componentes();
        SlackIntegrationTest slackAlert = new SlackIntegrationTest();
        timer.scheduleAtFixedRate(new TimerTask(){
                @Override public void run(){

                    Integer porcentagem1 = comps.getProcessamento().intValue();
                    Integer porcentagem3 = comps.regraTres(comps.getMemVolUso(), comps.getRam());
                    Integer porcentagem5 = comps.regraTres(comps.getDiscoUso(), comps.getDisco());
                    Double temp = comps.getTemp();
                    
                    gerarNovoRelatorio(maquina);
                    System.out.println("CPU:\n");
                    System.out.println(String.format("%d%%\n", porcentagem1));
                    if(porcentagem1 > 20 && porcentagem1<=50){
                        System.out.println("\u001B[32m"+"|".repeat(porcentagem1)+"\u001B[37m"+"\n");
                    }
                    if(porcentagem1>50 && porcentagem1<=80){
                        System.out.println("\u001B[33m"+"|".repeat(porcentagem1)+"\u001B[37m"+"\n");
                    }
                    if(porcentagem1>80){
                        System.out.println("\u001B[31m"+"|".repeat(porcentagem1)+"\u001B[37m"+"\n");
                    }
                    if(porcentagem1<=20){
                        System.out.println("\u001B[34m"+"|".repeat(porcentagem1)+"\u001B[37m"+"\n");
                    }
                    System.out.println("RAM:\n");
                    System.out.println(String.format("%d%%\n", porcentagem3));
                    if(porcentagem3 > 20 && porcentagem3<=50){
                        System.out.println("\u001B[32m"+"|".repeat(porcentagem3)+"\u001B[37m"+"\n");
                    }
                    if(porcentagem3>50 && porcentagem3<=80){
                        System.out.println("\u001B[33m"+"|".repeat(porcentagem3)+"\u001B[37m"+"\n");
                    }
                    if(porcentagem3>80){
                        System.out.println("\u001B[31m"+"|".repeat(porcentagem3)+"\u001B[37m"+"\n");
                    }
                    if(porcentagem1<=20){
                        System.out.println("\u001B[34m"+"|".repeat(porcentagem3)+"\u001B[37m"+"\n");
                    }
                    System.out.println("Disco:\n");
                    System.out.println(String.format("%d%%\n", porcentagem5));
                    if(porcentagem5 > 20 && porcentagem5<=50){
                        System.out.println("\u001B[32m"+"|".repeat(porcentagem5)+"\u001B[37m"+"\n");
                    }
                    if(porcentagem5>50 && porcentagem5<=80){
                        System.out.println("\u001B[33m"+"|".repeat(porcentagem5)+"\u001B[37m"+"\n");
                    }
                    if(porcentagem5>80){
                        System.out.println("\u001B[31m"+"|".repeat(porcentagem5)+"\u001B[37m"+"\n");
                    }
                    if(porcentagem5<=20){
                        System.out.println("\u001B[34m"+"|".repeat(porcentagem5)+"\u001B[37m"+"\n");
                    }
                    if(comps.getTemp() > 20 && comps.getTemp()<=50){
                        System.out.println("Temp: \n");
                        System.out.println("\u001B[32m"+comps.getTemp().toString()+"\u001B[37m"+"ºC"+"\n");
                    }
                    if(comps.getTemp()>50 && comps.getTemp()<=80){
                        System.out.println("Temp: \n");
                        System.out.println("\u001B[33m"+comps.getTemp().toString()+"\u001B[37m"+"ºC"+"\n");
                    }
                    if(comps.getTemp()>80){
                        System.out.println("Temp: \n");
                        System.out.println("\u001B[31m"+comps.getTemp().toString()+"\u001B[37m"+"ºC"+"\n");
                    }
                    if(comps.getTemp()<=20){
                        System.out.println("Temp: \n");
                        System.out.println("\u001B[34m"+comps.getTemp().toString()+"\u001B[37m"+"ºC"+"\n");
                    }
                if (porcentagem1 > 20 && porcentagem1<=50) {
                    logs.alerta("O nível de processamento atingiu: " + porcentagem1);
                    if(porcentagem1>50 && porcentagem1<=80){
                        logs.erro("O nível de processamento atingiu: " + porcentagem1);
                    
                    }
                    if(porcentagem1>80){
                        logs.severo("O nível de processamento atingiu: " + porcentagem1); 

                    }
                        
                        slackAlert.sendMessageToSlack(String.format("Alerta: O nível de processamento (CPU) atingiu %d%%", porcentagem1));
                        
                        
                 } 
                    if(temp>=10.0){
                       
                        slackAlert.sendMessageToSlack(String.format("Alerta: A temperatura atingiu %.2f °C", temp));
                            
                       
                    }
                    if (porcentagem3 > 30 && porcentagem3<=40) {
                        if(porcentagem3>40 && porcentagem3<=60){
                            logs.alerta("A memória RAM atingiu: " + porcentagem3); 
                        }
                        if(porcentagem3>60 && porcentagem3<=80){
                            logs.erro("A memória RAM atingiu: " + porcentagem3); 
                        }
                        if(porcentagem3>80){
                            logs.severo("A memória RAM atingiu: " + porcentagem3); 
                        }
                        
                        slackAlert.sendMessageToSlack(String.format("Alerta: Memória ram atingiu %d%%",porcentagem3));
                        
                } 
                if (porcentagem5 > 40) {
                    if(porcentagem5>40 && porcentagem5<=60){
                        logs.alerta("O armazenamento atingiu: " + porcentagem5);  
                    }
                    if(porcentagem5>60 && porcentagem5<=80){
                        logs.erro("O armazenamento atingiu: " + porcentagem5); 
                    }
                    if(porcentagem5>80){
                        logs.severo("O armazenamento atingiu: " + porcentagem5); 
                    }
                    
                            slackAlert.sendMessageToSlack(String.format("Alerta: Armazenamento atingiu %d%%",porcentagem5));
                    
                }
                }
            }, segundo, minuto);
        }
        
        
}
