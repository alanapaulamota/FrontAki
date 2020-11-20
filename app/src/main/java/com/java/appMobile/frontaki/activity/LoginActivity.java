package com.java.appMobile.frontaki.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import static com.java.appMobile.frontaki.R.layout.activity_login;

public class LoginActivity extends AppCompatActivity {

    private Object CadastroActivity;

    public LoginActivity(Object cadastroActivity) {
        CadastroActivity = cadastroActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_login);
    }

    /**
     * @param view
     * Parâmetro que abre a tela de cadastro
     */
    public void abrirCadastro(View view){
        Intent i = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity( i );
    }

}
