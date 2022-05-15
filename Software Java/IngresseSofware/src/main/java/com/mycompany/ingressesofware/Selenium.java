/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ingressesofware;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import com.mycompany.ingresse.coleta.dados.Conexao;

/**
 *
 * @author diego.silva@VALEMOBI.CORP
 */
public class Selenium {
        List<String> listaDataEstreia = new ArrayList();
        List<String> listaFilmeEstreia = new ArrayList();
        Conexao connect = new Conexao();
    void automacaoSelenium() throws ParseException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        WebDriver driver = new ChromeDriver();
      
        
        driver.get("http://preshow.com.br/calendario-de-estreias/?busca=s&buscaFilmeCinesemana=&buscaFilmeMes=&buscaFilmeDataLancamentoDe=&buscaFilmeDataLancamentoAte=&buscaFilmeGenero=&buscaFilmeProgramacao=&buscaFilmePalavraChave=&submitButton=IR#norte");

        List<WebElement> nomeFilme = driver.findElements(By.tagName("h4"));
        List<WebElement> dataFilme = driver.findElements(By.tagName("p"));

        for (WebElement filme : nomeFilme) {
            if (!filme.getText().contains("Programa finalizado") && !filme.getText().contains("FALE COM A GENTE") && !filme.getText().contains("CADASTRE SEU EMAIL E RECEBA NOSSA NEWSLETTER") && !filme.getText().contains("MAPA DO SITE")) {
                listaFilmeEstreia.add(filme.getText());
                System.out.println(filme.getText());

            }

        }

        for (WebElement data : dataFilme) {

            if (data.getText().contains(".0")
                    && !data.getText().contains("a")
                    || data.getText().contains(".1")
                    && !data.getText().contains("a")
                    && !data.getText().contains("e")) {
                // tratar dados 
                String dataTratada = data.getText() + "/2022";
                if (dataTratada.contains(".")) {
                    dataTratada = dataTratada.replace(".", "/");
                }
                listaDataEstreia.add(dataTratada);

                System.out.println(dataTratada);
            }


            /*Fim da execução do programa*/
        }
//        System.out.println("Tamanho lista data: " + listaDataEstreia.size());
//        System.out.println("Tamanho lista filmes: " + listaFilmeEstreia.size());
//        System.out.println("------------------------------------------------");
//        System.out.println("Lista de datas: " + listaDataEstreia);
//        System.out.println("Lista de filmes: " + listaFilmeEstreia);
//        System.out.println("Programa finalizado");
        
        System.out.println("------------------------------------------------");
        
        
        for (int i = 0; i < listaDataEstreia.size(); i++) {
            System.out.println(String.format("Filmes: %s "
                    + "- Data:  %s", listaFilmeEstreia.get(i), listaDataEstreia.get(i)));
            
         
        }
        
           DiasRestante diasRestante = new DiasRestante();
        Selenium selenium = new Selenium();
        String proximasEstreias = "20/03/2022";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat diaSemanaFormat = new SimpleDateFormat("dd/MM/yyyy");
        // cal se refere a data da estreia/aviso(-7)
        Calendar cal = Calendar.getInstance();
        
        //calendar se refere a data de hoje
        java.util.Date dateHoje = cal.getTime();
        String dateHojeFormatado = dtf.format(dateHoje);

        java.util.Date dataAviso = sdf.parse(proximasEstreias);
                  
            java.util.Date diaSemanaAviso = sdf.parse(proximasEstreias);
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(diaSemanaAviso);
            int diaDaSemana = gc.get(GregorianCalendar.DAY_OF_WEEK);
      
        cal.setTime(dataAviso);

 
        cal.add(cal.DAY_OF_MONTH, -7);

        dataAviso = cal.getTime();
       
        
        Integer qtdFilmesAno = listaDataEstreia.size();
        long dias = 0;
        // exibir todas as datas com menos de 7 dias e saber qual é o indice do erro
        // exibir todos os filme indice compativel com a data
        for (int i = 0; i < qtdFilmesAno; i++) {
            dias = diasRestante.calcular(dateHojeFormatado, listaDataEstreia.get(i));
            
              if (dias <= 7 && dias > 0) {
                  System.out.println("Faltam " + dias + " dias para a estreia de " + listaFilmeEstreia.get(i));
                  System.out.println("Estréia " + listaDataEstreia.get(i));
                connect.getJdbc().update("INSERT INTO lancamento_futuro(nomeFilme,dataFilme,dias_para_lancamento) VALUES (?,?,?)",listaFilmeEstreia.get(i),listaDataEstreia.get(i),dias);
                 
            }
           
        }

        System.out.println("Hoje é: " + dateHojeFormatado);
        System.out.println("A data do aviso do lançamento é: " + sdf.format(dataAviso));
        System.out.println("A data do lançamento é: " + proximasEstreias);

        if (dateHojeFormatado.equals(proximasEstreias)) {
            System.out.println("alerta");
        }

        // String hojeExemplo = "13/03/2022";

//        if (hojeExemplo.equals(sdf.format(dataAviso))){;
//            
//            System.out.println("Alerta!!!\nCapitão américa será lançado em breve");
//        } 
        
        
    }

//    public static void main(String[] args) throws IOException, InterruptedException {
//       
//    }
}
