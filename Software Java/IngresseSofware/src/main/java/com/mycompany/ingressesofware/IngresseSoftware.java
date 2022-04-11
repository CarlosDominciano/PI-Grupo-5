/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ingressesofware;

import com.mycompany.ingresse.coleta.dados.Componentes;
import com.mycompany.ingresse.coleta.dados.Conexao;

/**
 *
 * @author diego.silva@VALEMOBI.CORP
 */
public class IngresseSoftware {

    public static void main(String[] args) {
        Componentes comps = new Componentes();
        Conexao connect = new Conexao();
        
        System.out.println(comps.getData());
    }
}
