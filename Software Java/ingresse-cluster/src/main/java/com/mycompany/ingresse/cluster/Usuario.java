/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ingresse.cluster;

/**
 *
 * @author diego.silva@VALEMOBI.CORP
 */
public class Usuario {
    private Integer idUsuario;
    private String nome;
    private String email_usuario;
    private String tipo_acesso;
    private Integer fkFilial;
    private String senha;
    
    public Integer getFkFilial() {
        return fkFilial;
    }

    public void setFkFilial(Integer fkFilial) {
        this.fkFilial = fkFilial;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", nome=" + nome + ", email_usuario=" + email_usuario + ", tipo_acesso=" + tipo_acesso + ", senha=" + senha + '}';
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public Usuario(){}

    public Usuario(Integer idUsuario, String nome, String email_usuario, String tipo_acesso, Integer fkFilial, String senha) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.email_usuario = email_usuario;
        this.tipo_acesso = tipo_acesso;
        this.fkFilial = fkFilial;
        this.senha = senha;
    }


    
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail_usuario() {
        return email_usuario;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }

    public String getTipo_acesso() {
        return tipo_acesso;
    }

    public void setTipo_acesso(String tipo_acesso) {
        this.tipo_acesso = tipo_acesso;
    }

}
