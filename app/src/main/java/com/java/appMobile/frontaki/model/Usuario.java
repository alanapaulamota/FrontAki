package com.java.appMobile.frontaki.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Usuario implements Serializable {

    private String id;
    private String nome;
    private String email;
    private String telefone;
    private String senha;
    private String caminhoFoto;

    public Usuario() {
    }


    public void salvar() {
    }


}


