package com.java.appMobile.frontaki.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toolbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.java.appMobile.frontaki.R;
import com.java.appMobile.frontaki.helper.ConfiguracaoFirebase;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth autenticacao;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Configura toolbar
        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);

        toolbar.setTitle("FrontAki");
        setSupportActionBar();

    //    //configuracoes de objetos
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

     //   //Configurar bottom navigation view
      //  configuraBottomNavigationView();
     //   FragmentManager fragmentManager = getSupportFragmentManager();
     //   FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

       // fragmentTransaction.replace(R.id.viewPager, new FeedFragment()).commit();

    }

    private void setSupportActionBar() {
    }

    //  @Override
 //   public boolean onOptionsItemSelected(MenuItem item) {

   //     switch (item.getItemId()){
     //       case R.id.menu_sair :
       //         deslogarUsuario();
         //       startActivity(new Intent(getApplicationContext(), LoginActivity.class));
           //     break;
       // }

       // return super.onOptionsItemSelected(item);
   //
    //}

    @Override
    public boolean onCreateOptionsMenu (Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);

        return super.onCreateOptionsMenu(menu);
    }

@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_sair:
deslogarUsuario();
startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            break;
        }

        return super.onOptionsItemSelected(item);
}

private void deslogarUsuario(){

        try {
autenticacao.signOut();
        }catch (Exception e) {
            e.printStackTrace();
        }
}


}
