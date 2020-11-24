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
import com.java.appMobile.frontaki.model.UsuarioPremium;

import java.util.Objects;

import lombok.Data;
import lombok.EqualsAndHashCode;

import static com.java.appMobile.frontaki.R.layout.activity_cadastro_membro;

/**
 * Classe...
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CadastroMembroActivity extends AppCompatActivity {

    private EditText campoNome, campoEmail, campoSenha, campoTelefone, campoCpf;
    private Button botaoCadastrarPremium;
    private ProgressBar progressBar;

    private UsuarioPremium usuarioPremium;

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_cadastro_membro);
        inicializarComponentes();

        //Cadastrar Usuario
        progressBar.setVisibility(View.GONE);
        botaoCadastrarPremium.setOnClickListener(v -> {

            String textoNome = campoNome.getText().toString();
            String textoEmail = campoEmail.getText().toString();
            String textosenha = campoSenha.getText().toString();
            String textoCpf = campoCpf.getText().toString();


            if (!textoNome.isEmpty()) {
                if (!textoEmail.isEmpty()) {
                    if (!textosenha.isEmpty()) {
                        if (!textoCpf.isEmpty()) {


                            usuarioPremium = new UsuarioPremium();
                            usuarioPremium.setNome(textoNome);
                            usuarioPremium.setEmail(textoEmail);
                            usuarioPremium.setSenha(textosenha);
                            usuarioPremium.setCpf(textoCpf);

                            cadastrarUserPremium(usuarioPremium);

                        } else {
                            Toast.makeText(CadastroMembroActivity.this,
                                    "Preencha a senha!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(CadastroMembroActivity.this,
                                "Preencha o email!",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CadastroMembroActivity.this,
                            "Preencha o nome!",
                            Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(CadastroMembroActivity.this,
                        "Preencha o cpf!",
                        Toast.LENGTH_SHORT).show();


            }


        });


    }


    private void cadastrarUserPremium(UsuarioPremium usuarioPremium) {
        progressBar.setVisibility(View.VISIBLE);
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuarioPremium.getEmail(),
                usuarioPremium.getSenha()
        ).addOnCompleteListener(
                this,
                task -> {

                    if (task.isSuccessful()) {

                        try {

                            progressBar.setVisibility(View.GONE);

                            //Salvar dados no firebase
                            String idUsuarioPremium = Objects.requireNonNull(task.getResult()).getUser().getUid();
                            usuarioPremium.setId(idUsuarioPremium);
                            usuarioPremium.salvar();


                            UsuarioFirebase.atualizarNomeUsuario(usuarioPremium.getNome());

                            Toast.makeText(CadastroMembroActivity.this,
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
                            throw Objects.requireNonNull(task.getException());
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

                        Toast.makeText(CadastroMembroActivity.this,
                                "Erro: " + erroExcecao,
                                Toast.LENGTH_SHORT).show();


                    }

                }
        );

    }


    private void inicializarComponentes() {
        campoNome = findViewById(R.id.editCadNome);
        campoEmail = findViewById(R.id.editCadEmail);
        campoSenha = findViewById(R.id.editCadSenha);
        campoCpf = findViewById(R.id.editCadPremiumCpf);
        botaoCadastrarPremium = findViewById(R.id.buttonCadPremium);
        progressBar = findViewById(R.id.progressPremiumCad);

        campoNome.requestFocus();
    }
}
