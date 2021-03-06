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
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.java.appMobile.frontaki.R;
import com.java.appMobile.frontaki.helper.ConfiguracaoFirebase;
import com.java.appMobile.frontaki.helper.UsuarioFirebase;
import com.java.appMobile.frontaki.model.Usuario;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Classe...
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CadastroActivity extends AppCompatActivity {

    private EditText campoNome, campoEmail, campoSenha, campoTelefone;
    private Button botaoCadastrar;
    private ProgressBar progressBar;

    private Usuario usuario;

    private FirebaseAuth autenticacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        inicializarComponentes();

        //Cadastrar Usuario
        progressBar.setVisibility(View.GONE);
        botaoCadastrar.setOnClickListener(v -> {

            String textoNome = campoNome.getText().toString();
            String textoEmail = campoEmail.getText().toString();
            String textosenha = campoSenha.getText().toString();

            if (!textoNome.isEmpty()) {
                if (!textoEmail.isEmpty()) {
                    if (!textosenha.isEmpty()) {

                        usuario = new Usuario();
                        usuario.setNome(textoNome);
                        usuario.setEmail(textoEmail);
                        usuario.setSenha(textosenha);
                        cadastrar(usuario);

                    } else {
                        Toast.makeText(CadastroActivity.this,
                                "Preencha a senha!",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CadastroActivity.this,
                            "Preencha o email!",
                            Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(CadastroActivity.this,
                        "Preencha o nome!",
                        Toast.LENGTH_SHORT).show();
            }


        });


    }


    /**
     * Método responsável por cadastrar usuário com e-mail e senha
     * e fazer validações ao fazer o cadastro
     */
    public void cadastrar(final Usuario usuario){

        progressBar.setVisibility(View.VISIBLE);
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(
                this,
                task -> {

                    if (task.isSuccessful()) {

                        try {

                            progressBar.setVisibility(View.GONE);

                            //Salvar dados no firebase
                            String idUsuario = task.getResult().getUser().getUid();
                            usuario.setId(idUsuario);
                            usuario.salvar();


                            UsuarioFirebase.atualizarNomeUsuario(usuario.getNome());

                            Toast.makeText(CadastroActivity.this,
                                    "Cadastro com sucesso",
                                    Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    } else {

                        progressBar.setVisibility(View.GONE);

                        String erroExcecao;
                        try {
                            throw task.getException();
                        } catch (FirebaseAuthWeakPasswordException e) {
                            erroExcecao = "Digite uma senha mais forte!";
                        } catch (FirebaseAuthInvalidCredentialsException e) {
                            erroExcecao = "Por favor, digite um e-mail válido";
                        } catch (FirebaseAuthUserCollisionException e) {
                            erroExcecao = "Este conta já foi cadastrada";
                        } catch (Exception e) {
                            erroExcecao = "ao cadastrar usuário: " + e.getMessage();
                            e.printStackTrace();
                        }

                        Toast.makeText(CadastroActivity.this,
                                "Erro: " + erroExcecao,
                                Toast.LENGTH_SHORT).show();


                    }

                }
        );

    }

    public void inicializarComponentes() {

        campoNome = findViewById(R.id.editCadNome);
        campoEmail = findViewById(R.id.editCadEmail);
        campoSenha = findViewById(R.id.editCadSenha);
        botaoCadastrar = findViewById(R.id.buttonCadPremium);
        progressBar = findViewById(R.id.progressPremiumCad);

        campoNome.requestFocus();

    }


}
