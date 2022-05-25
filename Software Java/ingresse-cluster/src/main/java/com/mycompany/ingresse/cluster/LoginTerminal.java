/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
public class LoginTerminal {
    private Usuario sessao;

    public LoginTerminal() {
        this.sessao = login();
    }

    public Usuario getSessao() {
        return sessao;
    }
    
    
    
    public Usuario login() {
        Scanner leitorUser = new Scanner(System.in);
        Scanner leitorSenha = new Scanner(System.in);
        Conexao connect = new Conexao();
        System.out.println("Login: ");
        String login = leitorUser.nextLine();
        System.out.println("Senha: ");
        String senha = leitorSenha.nextLine();

        List<Usuario> user = connect.getJdbcAzure().query(String.format("SELECT * FROM usuario WHERE email_usuario='%s'", login), new BeanPropertyRowMapper<>(Usuario.class));
        if (user.isEmpty() == false) {
            if (user.get(0).getSenha().equals(senha)) {
                if (user.get(0).getTipo_acesso().equals("suporte") || user.get(0).getTipo_acesso().equals("gerente")) {
                    Usuario sessao = user.get(0);
                    return sessao;
                }
            }
        }
        return null;
    }
}
