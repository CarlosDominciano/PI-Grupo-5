/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ingresse.coleta.dados;

import java.util.List;

/**
 *
 * @author isaque.santos@VALEMOBI.CORP
 */
public class IngresseColetaDados {

    public static void main(String[] args) {
        Componentes comps = new Componentes();
        Conexao connect = new Conexao();
        
        System.out.println(comps.getData());
    }
}
