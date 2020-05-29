package com.ramonbl.pmdm_rec02;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
//import static com.ramonbl.pmdm_rec02.Act_Main.KEY_TIPO_USUARIO;

public class ActVerFase extends AppCompatActivity {

	//TODO: Vistas de la Activity

	ImageView ivProvincia;
	TextView tvMostrarFase;

	Frag_HeaderUsuario fragHeaderUsuario;

	//Variables para los valores recibidos/enviado de/a bundle
	String stUsuarioTipoRecibido;
	String stUsuarioUsuarioRecibido;
	String stProvinciaRecibida;

	//Variables para datos extra
	//ArrayList<String> alProvincias;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_ver_fase);


		//TODO: Bundle recibido de otra Activity
		Bundle bundle = this.getIntent().getExtras();
		if (bundle != null) {
			stUsuarioTipoRecibido = bundle.getString(Act_Main.KEY_USUARIO_TIPO);
			stUsuarioUsuarioRecibido = bundle.getString(Act_Main.KEY_USUARIO_USUARIO);
			stProvinciaRecibida = bundle.getString(Act_UsuarioAnonimo.KEY_FASE);

		}

		Log.e("ActVerFase", stUsuarioTipoRecibido);
		Log.e("ActVerFase", stUsuarioUsuarioRecibido);
		Log.e("ActVerFase", stProvinciaRecibida);

//		//TODO: Creación fragment para esta Activity
		fragHeaderUsuario = new Frag_HeaderUsuario(); //Creación fragment colocaremos en esta Activity

		//TODO: Bundle enviado a fragmento propio
		Bundle bundleToFrag = new Bundle(); //Creación Bundle para enviar al frag
		bundleToFrag.putString(
				Act_Main.KEY_USUARIO_TIPO, stUsuarioTipoRecibido); //añadimos info al bundle
		bundleToFrag.putString(
				Act_Main.KEY_USUARIO_USUARIO, stUsuarioUsuarioRecibido);
		fragHeaderUsuario.setArguments(bundleToFrag); //colocamos el bundle en el fragment

		//TODO: Añadir fragment a nuestra Activity
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(R.id.fragHeader, fragHeaderUsuario, null);
		fragmentTransaction.commit();


		// TODO: LÓGICA COMPONENTES

		// TODO: Asociación componentes
		ivProvincia = findViewById(R.id.ivProvincia);
		tvMostrarFase = findViewById(R.id.tvMostrarFase);

		switch (stProvinciaRecibida){
			case "A Coruña":
				ivProvincia.setImageResource(R.drawable.coruna);
				break;
			case "Lugo":
				ivProvincia.setImageResource(R.drawable.lugo);
				break;
			case "Ourense":
				ivProvincia.setImageResource(R.drawable.orense);
				break;
			case "Pontevedra":
				ivProvincia.setImageResource(R.drawable.pontevedra);
				break;
		}


	}
}
