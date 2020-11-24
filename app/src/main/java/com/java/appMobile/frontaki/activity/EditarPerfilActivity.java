package com.java.appMobile.frontaki.activity;

import android.os.Build;
import android.os.Bundle;
import android.widget.Toolbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.java.appMobile.frontaki.R;

import java.util.Objects;

public class EditarPerfilActivity extends AppCompatActivity {

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
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_24

        );
    }

    private void setSupportActionBar() {
    }

}