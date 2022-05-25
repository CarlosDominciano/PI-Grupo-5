/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.ingresse.cluster;

import com.mycompany.ingresse.coleta.dados.Conexao;
import java.util.List;
import java.util.Scanner;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

/**
 *
 * @author isaque.santos@VALEMOBI.CORP
 */
public class IngresseCluster {

    Usuario sessao;
    public static void main(String[] args) {
        Boolean continuar = true;
        LoginTerminal loginTerminal = new LoginTerminal();
        sessao = loginTerminal.getSessao();
        if(){
            while (continuar) {

                System.out.println("Adicionar totem: 1");
                System.out.println("Enviar relatório: 2");
                System.out.println("Iniciar monitoramento: 3");
                System.out.println("Sair: 4");
                System.out.println("Selecione a opção desejada: ");

                Scanner leitor = new Scanner(System.in);
                Integer escolhaUser = leitor.nextInt();
                switch (escolhaUser) {
                    case 1:
                        System.out.println("1");
                        break;
                    case 2:
                        System.out.println("2");
                        break;
                    case 3:
                        System.out.println("3");
                        break;
                    case 4:

                        continuar = false;
                        break;
                    default:
                        System.out.println("Digite uma opção válida");
                        System.out.println("-----------------------------");

                }
            }
        }    
    }

    
}
