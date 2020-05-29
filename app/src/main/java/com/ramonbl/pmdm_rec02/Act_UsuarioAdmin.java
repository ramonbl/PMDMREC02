package com.ramonbl.pmdm_rec02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class Act_UsuarioAdmin extends AppCompatActivity {

	Frag_HeaderUsuario fragHeaderUsuario;

	String stUsuarioTipoRecibido;
	String stUsuarioUsuarioRecibido;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act__usuario_admin);

		Bundle bundle = this.getIntent().getExtras();
		if (bundle != null) {
			stUsuarioTipoRecibido = bundle.getString(Act_Main.KEY_USUARIO_TIPO);
			stUsuarioUsuarioRecibido=bundle.getString(Act_Main.KEY_USUARIO_USUARIO);
			Log.e("UsuarioActUsuarioAdmin ", stUsuarioUsuarioRecibido);
		}

		fragHeaderUsuario = new Frag_HeaderUsuario();

		Bundle bundleToFrag = new Bundle();
		bundleToFrag.putString(
				Act_Main.KEY_USUARIO_TIPO, stUsuarioTipoRecibido);
		bundleToFrag.putString(
				Act_Main.KEY_USUARIO_USUARIO, stUsuarioUsuarioRecibido);
		fragHeaderUsuario.setArguments(bundleToFrag);

		//Creamos transacción directamente
		getSupportFragmentManager().beginTransaction().add(R.id.fragHeader, fragHeaderUsuario).commit();
	}

	public void mBtOnClick(View view) {
		Intent intent;
		Bundle bundleEnviado;
		switch (view.getId()) {
			case R.id.btUsuarioAdminAnadirFase:

				intent = new Intent(view.getContext(), ActAnadirFase.class);
				bundleEnviado = new Bundle();
				bundleEnviado.putString(
						Act_Main.KEY_USUARIO_TIPO,
						stUsuarioTipoRecibido);
				bundleEnviado.putString(
						Act_Main.KEY_USUARIO_USUARIO,
						stUsuarioUsuarioRecibido);

				intent.putExtras(bundleEnviado);
				startActivity(intent);

				break;
			case R.id.btUsuarioAdminSeleccionarFaseProvincia:

				intent = new Intent(view.getContext(), ActSeleccionarFaseProvincia.class);
				bundleEnviado = new Bundle();
				bundleEnviado.putString(
						Act_Main.KEY_USUARIO_TIPO,
						stUsuarioTipoRecibido);
				bundleEnviado.putString(
						Act_Main.KEY_USUARIO_USUARIO,
						stUsuarioUsuarioRecibido);

				intent.putExtras(bundleEnviado);
				startActivity(intent);
				break;
		}
	}
}
