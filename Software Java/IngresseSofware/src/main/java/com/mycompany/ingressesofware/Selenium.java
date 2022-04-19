/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ingressesofware;

import com.sun.tools.javac.Main;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author diego.silva@VALEMOBI.CORP
 */

public class Selenium {
    public static void main(String[] args) {
      
        System.setProperty("webdriver.chrome.driver","chromedriver");
        WebDriver driver = new ChromeDriver();
         List<String> dataEstreia = new ArrayList();
         // List<WebElement> veggieList1 = new ArrayList<>();
        List nomeFilme = new ArrayList();
        driver.get("http://preshow.com.br/calendario-de-estreias/?busca=s&buscaFilmeCinesemana=&buscaFilmeMes=&buscaFilmeDataLancamentoDe=&buscaFilmeDataLancamentoAte=&buscaFilmeGenero=&buscaFilmeProgramacao=&buscaFilmePalavraChave=&submitButton=IR#norte");
//        driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input")).click();
//        driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input")).sendKeys("preshow calendario de estreias", Keys.ENTER);
//        driver.findElement(By.xpath("//*[@id=\"rso\"]/div[1]/div/div[1]/div/a/h3")).click();
                

        nomeFilme.add(driver.findElement(By.xpath("//*[@id=\"4444\"]/div[1]/div/div/div/div/div/div/div[2]/div/div[2]/div[1]/div[2]/h4")).getText());
//        dataEstreia.add(driver.findElement(By.xpath("//*[@id=\"4310\"]/div[1]/div/div/div/div/div/div/div[2]/div/div[2]/div[1]/div[1]/div/p")).getText());
        
//        System.out.println(nomeFilme + "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
//        System.out.println(dataEstreia);
        
       
    }
    
}
