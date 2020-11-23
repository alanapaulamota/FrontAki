package com.java.appMobile.frontaki.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.java.appMobile.frontaki.helper.ConfiguracaoFirebase;
import com.java.appMobile.frontaki.model.Usuario;

import lombok.Data;

import static android.widget.Toast.LENGTH_SHORT;

@Data
public class LoginActivity extends AppCompatActivity {

    private EditText campoEmail, campoSenha;
    private Button botaoEntrar;
    private ProgressBar progressBar;

    private Usuario usuario;

    private FirebaseAuth autenticacao;

    public LoginActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
  //      setContentView(R.layout.activity_login);

        verificarUsuarioLogado();
 //       inicializarComponentes();

        //Fazer login do usuario
        progressBar.setVisibility( View.GONE );
        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoEmail = campoEmail.getText().toString();
                String textosenha = campoSenha.getText().toString();

                if( !textoEmail.isEmpty() ){
                    if( !textosenha.isEmpty() ){

     //                   usuario = new Usuario();
       //                 usuario.setEmail( textoEmail );
         //               usuario.setSenha( textosenha );
           //             validarLogin( usuario );

                    }else{
                        Toast.makeText(LoginActivity.this, "Preencha a senha!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LoginActivity.this, "Preencha o e-mail!", LENGTH_SHORT).show();
                }

            }
        });

    }
    public void verificarUsuarioLogado() {
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        if (autenticacao.getCurrentUser() != null) {
            //        startActivity(new Intent(getApplicationContext(), MainActivity.class));
            //       finish();
        }
    }
//
//    public void validarLogin(Usuario usuario) {

//        progressBar.setVisibility(View.VISIBLE);
//        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

//        autenticacao.signInWithEmailAndPassword(
//                usuario.getEmail(),
//                usuario.getSenha()
//        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {

    //            if (task.isSuccessful()) {
    //                  progressBar.setVisibility(View.GONE);
    //                  startActivity(new Intent(getApplicationContext(), MainActivity.class));
    //                  finish();
    //          } else {
//                    Toast.makeText(LoginActivity.this,
    //                     "Erro ao fazer login",
    //                      Toast.LENGTH_SHORT).show();
    //                 progressBar.setVisibility(View.GONE);
    //             }

    //           }
    //     });
//

//    }

    //    public void abrirCadastro(View view) {
    //     Intent i = new Intent(LoginActivity.this, CadastroActivity.class);
    //     startActivity(i);
    // }

    //   public void inicializarComponentes() {

    //     campoEmail = findViewById(R.id.editLoginEmail);
    //    campoSenha = findViewById(R.id.editLoginSenha);
    //    botaoEntrar = findViewById(R.id.buttonEntrar);
    //    progressBar = findViewById(R.id.progressLogin);

    //  campoEmail.requestFocus();


    //   }

}