package com.java.appMobile.frontaki.fragment;


import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PerfilFragment extends Fragment {

    public GridView gridViewPerfil;
    private ProgressBar progressBar;
    private CircleImageView imagePerfil;
    private TextView textNome, textEmail, textDataAniv;
    private Button buttonAcaoPerfil;

    private DatabaseReference usuarioLogadoRef;
    private ValueEventListener valueEventListenerPerfil;
    private DatabaseReference postagensUsuarioRef;



    /*
    private void recuperarDadosUsuarioLogado(){

        usuarioLogadoRef = usuariosRef.child( usuarioLogado.getId() );
        valueEventListenerPerfil = usuarioLogadoRef.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Usuario usuario = dataSnapshot.getValue( Usuario.class );

                        String postagens = String.valueOf( usuario.getPostagens() );
                        String seguindo = String.valueOf( usuario.getSeguindo() );
                        String seguidores = String.valueOf( usuario.getSeguidores() );

                        //Configura valores recuperados
                        textPublicacoes.setText( postagens );
                        textSeguidores.setText( seguidores );
                        textSeguindo.setText( seguindo );

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
        );

    }

    private void recuperarFotoUsuario(){

        usuarioLogado = UsuarioFirebase.getDadosUsuarioLogado();

        //Recuperar foto do usuário
        String caminhoFoto = usuarioLogado.getCaminhoFoto();
        if( caminhoFoto != null ){
            Uri url = Uri.parse( caminhoFoto );
            Glide.with(getActivity())
                    .load( url )
                    .into( imagePerfil );
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        //Recuperar dados do usuario logado
        recuperarDadosUsuarioLogado();

        //Recuperar foto usuário
        recuperarFotoUsuario();

    }

    @Override
    public void onStop() {
        super.onStop();
        usuarioLogadoRef.removeEventListener( valueEventListenerPerfil );
    }*/
}
