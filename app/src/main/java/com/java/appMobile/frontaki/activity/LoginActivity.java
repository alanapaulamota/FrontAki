package com.java.appMobile.frontaki.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.java.appMobile.frontaki.R;
import com.java.appMobile.frontaki.helper.ConfiguracaoFirebase;
import com.java.appMobile.frontaki.model.Usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import static android.widget.Toast.LENGTH_SHORT;

@Builder(builderMethodName = "loginBuilder")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginActivity extends AppCompatActivity {

    private EditText campoEmail, campoSenha;
    private Button botaoEntrar;
    private ProgressBar progressBar;

    private Usuario usuario;

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
  //      setContentView(R.layout.activity_login);

        verificarUsuarioLogado();
        inicializarComponentes();

        //Fazer login do usuario
        progressBar.setVisibility( View.GONE );
        botaoEntrar.setOnClickListener(v -> {

            String textoEmail = campoEmail.getText().toString();
            String textosenha = campoSenha.getText().toString();

            if (!textoEmail.isEmpty()) {
                if (!textosenha.isEmpty()) {

                    usuario = new Usuario();
                    usuario.setEmail(textoEmail);
                    usuario.setSenha(textosenha);
                    validarLogin(usuario);

                } else {
                    Toast.makeText(LoginActivity.this, "Preencha a senha!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(LoginActivity.this, "Preencha o e-mail!", LENGTH_SHORT).show();
            }

        });

    }

    public void verificarUsuarioLogado() {
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        if (autenticacao.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }


    public void validarLogin(Usuario usuario) {

        progressBar.setVisibility(View.VISIBLE);
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(task -> {

            if (task.isSuccessful()) {
                progressBar.setVisibility(View.GONE);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            } else {
                Toast.makeText(LoginActivity.this,
                        "Erro ao fazer login",
                        Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }

        });

    }

    public void abrirCadastro(View view) {
        Intent i = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(i);
    }

    public void inicializarComponentes() {

        campoEmail = findViewById(R.id.editLoginEmail);
        campoSenha = findViewById(R.id.editLoginSenha);
        botaoEntrar = findViewById(R.id.buttonLogin);
        progressBar = findViewById(R.id.progressLogin);

        campoEmail.requestFocus();


    }

}