package com.ramonbl.pmdm_rec02;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
//import static com.ramonbl.pmdm_rec02.Act_Main.KEY_TIPO_USUARIO;

public class ActAnadirFase extends AppCompatActivity {

	//TODO: Vistas de la Activity

	EditText etAnadirNumeroFase, etAnadirCaracteristicasFase;

	Frag_HeaderUsuario fragHeaderUsuario;

	//Variables para los valores recibidos/enviado de/a bundle
	String stUsuarioTipoRecibido;
	String stUsuarioUsuarioRecibido;

	//Variables para datos extra
	//ArrayList<String> alProvincias;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_anadir_fase);


		//TODO: Bundle recibido de otra Activity
		Bundle bundle = this.getIntent().getExtras();
		if (bundle != null) {
			stUsuarioTipoRecibido = bundle.getString(Act_Main.KEY_USUARIO_TIPO);
			stUsuarioUsuarioRecibido = bundle.getString(Act_Main.KEY_USUARIO_USUARIO);

		}

		Log.e("ActAnadirFase", stUsuarioTipoRecibido);
		Log.e("ActAnadirFase", stUsuarioUsuarioRecibido);

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
		etAnadirNumeroFase = findViewById(R.id.etAnadirNumeroFase);
		etAnadirCaracteristicasFase = findViewById(R.id.etAnadirCaracteristicasFase);


	}

	public void mBtOnClick(View view) {

		switch (view.getId()) {
			case R.id.btGuardarFase:
				if (etAnadirNumeroFase.getText().toString().equals("")
						|| etAnadirCaracteristicasFase.getText().toString().equals("")) {

					Toast.makeText(getApplicationContext(),
							"Rellena los dos campos: fase y características ",
							Toast.LENGTH_LONG).show();
				} else {

					Toast.makeText(getApplicationContext(),
							"Características guardadas de la fase: " + etAnadirNumeroFase.getText().toString(),
							Toast.LENGTH_LONG).show();
				}

				break;
			case R.id.btAnadirFase_Volver:
				finish();
				break;
		}
	}
}
