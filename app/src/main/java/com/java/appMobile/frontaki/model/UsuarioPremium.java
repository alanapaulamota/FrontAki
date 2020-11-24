package com.java.appMobile.frontaki.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
@Data
public class UsuarioPremium implements Serializable {

    private String id;
    private String nome;
    private String email;
    private String telefone;
    private String senha;
    private String cpf;
    private String caminhoFoto;


    public void salvar() {
    }


}


