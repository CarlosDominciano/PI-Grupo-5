/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ingressesofware;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author diego.silva@VALEMOBI.CORP
 */
public class DiasRestante {
 
 private static DateFormat df = new SimpleDateFormat ("dd/MM/yyyy");
    /** Calcula o número de dias entre duas datas. */
    public static long calcular (String inicio, String fim) throws ParseException {
        Date dtInicial = df.parse (inicio);
        Date dtFinal = df.parse (fim);
        return 
             (dtFinal.getTime() - dtInicial.getTime() + 3600000L) / 86400000L;
    }
    

}


