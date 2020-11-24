package com.java.appMobile.frontaki.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.java.appMobile.frontaki.helper.ConfiguracaoFirebase;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
@Data
public class Usuario implements Serializable {

    private String id;
    private String nome;
    private String email;
    private String senha;
    private String caminhoFoto;


    public void salvar(){
        DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebase();
        DatabaseReference usuariosRef = firebaseRef.child("usuarios").child( getId() );
        usuariosRef.setValue( this );
    }

    @Exclude
    public String getSenha() {
        return senha;
    }
}


