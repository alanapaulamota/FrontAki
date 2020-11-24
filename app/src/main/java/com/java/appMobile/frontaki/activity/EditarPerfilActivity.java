package com.java.appMobile.frontaki.activity;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseUser;
import com.java.appMobile.frontaki.R;
import com.java.appMobile.frontaki.helper.UsuarioFirebase;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditarPerfilActivity extends AppCompatActivity {

    private CircleImageView imageEditPerfil;
    private TextView textAlterarFoto;
    private TextInputEditText editNomePerfil,editEmailPerfil, editTelefonePerfil, editSenhaPerfil;
    private Button buttonSalvarAlteracoes;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        //Configura toolbar
        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);

        toolbar.setTitle("Editar Perfil");
        setSupportActionBar();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);

        inicializarComponentes();

        FirebaseUser usuarioPerfil = UsuarioFirebase.getUsuarioAtual();
        editNomePerfil.setText(usuarioPerfil.getDisplayName());
        editEmailPerfil.setText(usuarioPerfil.getEmail());
        editTelefonePerfil.setText(usuarioPerfil.getPhoneNumber());
    }

    private void setSupportActionBar() {
    }

    public void inicializarComponentes(){

        imageEditPerfil = findViewById(R.id.imageEditPerfil);
        textAlterarFoto = findViewById(R.id.textAlterarFoto);
        editNomePerfil = findViewById(R.id.editNomePerfil);
        editEmailPerfil = findViewById(R.id.editEmailPerfil);
        editTelefonePerfil = findViewById(R.id.editTelefonePerfil);
        editSenhaPerfil = findViewById(R.id.editSenhaPerfil);
        buttonSalvarAlteracoes = findViewById(R.id.buttonSalvarAlteracoes);
        editEmailPerfil.setFocusable(false);

    }


}