/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ingressesofware;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author diego.silva@VALEMOBI.CORP
 */
public class CalendarioFilmes {

    public static void main(String[] args) throws ParseException {

        String capitaoAmerica = "20/03/2022";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        java.util.Date dataAviso = sdf.parse(capitaoAmerica);
        cal.setTime(dataAviso);
        cal.add(Calendar.DAY_OF_MONTH, -7);
        dataAviso = cal.getTime();
        SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();

        java.util.Date dateHoje = calendar.getTime();
        String dateHojeFormatado = dtf.format(dateHoje);

        System.out.println("Hoje é: " + dateHojeFormatado );
        System.out.println("A data do aviso do lançamento é: " + sdf.format(dataAviso));
        System.out.println("A data do lançamento é: " + capitaoAmerica);
        
        if (dateHojeFormatado.equals(capitaoAmerica)){
            System.out.println("alerta");
        } 
        
        String hojeExemplo = "13/03/2022";
        
        if (hojeExemplo.equals(sdf.format(dataAviso))){
            
            System.out.println("Alerta!!!\nCapitão américa será lançado em breve");
        } 
        
     
    }
}
