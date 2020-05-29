package com.ramonbl.pmdm_rec02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
//import static com.ramonbl.pmdm_rec02.Act_Main.KEY_TIPO_USUARIO;

public class Act_UsuarioAnonimo extends AppCompatActivity {

	public static final String KEY_FASE = "keyFase";

	//TODO: Vistas de la Activity
	Frag_HeaderUsuario fragHeaderUsuario;

	TextView tvSeleccionaProvincia;
	Spinner spinProvincias;
	Button btUsuarioAnonimo_VerFase, btUsuarioAnonimo_Salir;


	//Variables para los valores recibidos/enviado de/a bundle
	String stUsuarioTipoRecibido;
	String stUsuarioUsuarioRecibido;
	String stItemSelectedFromSpinProvincias;

	//Variables para datos extra
	//ArrayList<String> alProvincias;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act__usuario_anonimo);


		//TODO: Bundle recibido de otra Activity
		Bundle bundle = this.getIntent().getExtras();
		if (bundle != null) {

			stUsuarioTipoRecibido = bundle.getString(Act_Main.KEY_USUARIO_TIPO);
			stUsuarioUsuarioRecibido = bundle.getString(Act_Main.KEY_USUARIO_USUARIO);
			Log.e("User ActUsuarioAnonimo", stUsuarioUsuarioRecibido);
		}

		//TODO: Creación fragment para esta Activity

		//TODO: Bundle enviado a fragmento propio
		Bundle bundleToFrag = new Bundle(); //Creación Bundle para enviar al frag
		bundleToFrag.putString(
				Act_Main.KEY_USUARIO_TIPO, stUsuarioTipoRecibido); //añadimos info al bundle
		bundleToFrag.putString(
				Act_Main.KEY_USUARIO_USUARIO, stUsuarioUsuarioRecibido);

		//TODO: Añadir fragment a nuestra Activity
		fragHeaderUsuario = new Frag_HeaderUsuario(); //Creación fragment colocaremos en esta Activity
		fragHeaderUsuario.setArguments(bundleToFrag); //colocamos el bundle en el fragment

		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.replace(R.id.fragHeader, fragHeaderUsuario, null);
		fragmentTransaction.commit();

		//En este caso podríamos hacerlo directamente con:
		/*getSupportFragmentManager()
				.beginTransaction()
				.add(R.id.fragHeader, fragHeaderUsuario, null)
				.commit();*/

		// TODO: LÓGICA COMPONENTES

		// TODO: Asociación componentes
		tvSeleccionaProvincia = findViewById(R.id.tvSeleccionarProvincias);
		btUsuarioAnonimo_VerFase = findViewById(R.id.btUsuarioAnominoVerFase);
		btUsuarioAnonimo_Salir = findViewById(R.id.btUsuarioAnonimoSalir);

		// He usado archivo de recursos dataTo_spinUsuarioAnonimo_Provincias
		spinProvincias = findViewById(R.id.spinProvincias);

		/* TODO: Seleccionar uno de los dos adaptadores en función del recurso utilizado:
		 *  Adaptador para Spinner usando Archivo de recursos (Elementos FIJOS)
		 *  Adaptador para Spinner usando un ArrayList (Elementos NO FIJOS)
		 * */

//		 ArrayAdapter<CharSequence> adapterSpinProvincias=
//				 usarSpinProvinciasUsandoArchivoRecursos();

		ArrayAdapter<CharSequence> adapterSpinProvincias =
				usarSpinProvinciasUsandoArrayLIst();


		spinProvincias.setAdapter(adapterSpinProvincias);

		// TODO: Código para OnItemSelected en el spinProvincias
		spinProvincias.setOnItemSelectedListener(
				new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(
							AdapterView<?> parent,   // refers to:spinProvincias
							View view,                //The view within the AdapterView that was clicked
							int position,             //refers to: posición de la vista en el adaptador
							long id                  //la fila id del item que es seleccionado
					) {

						Object itemSelectedFromSpinProvincias = parent.getItemAtPosition(position);
						stItemSelectedFromSpinProvincias = itemSelectedFromSpinProvincias.toString();
						Toast.makeText(
								getApplicationContext(),
								"Seleccionado:" + stItemSelectedFromSpinProvincias,
								Toast.LENGTH_LONG)
								.show();
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						//CODE FOR onNothingSelected
					}
				} //FIN AdapterView.OnItemSelectedItem
		); //FIN setOnItemSelectedListener


	}


	public void mBtOnClick(View view) {

		switch (view.getId()) {
			case R.id.btUsuarioAnominoVerFase:
				Intent intent;
				Bundle bundleEnviado;

				intent = new Intent(view.getContext(), ActVerFase.class);
				bundleEnviado = new Bundle();
				bundleEnviado.putString(
						Act_Main.KEY_USUARIO_TIPO,
						stUsuarioTipoRecibido);
				bundleEnviado.putString(
						Act_Main.KEY_USUARIO_USUARIO,
						stUsuarioUsuarioRecibido);
				bundleEnviado.putString(
						Act_UsuarioAnonimo.KEY_FASE,
						stItemSelectedFromSpinProvincias);


				intent.putExtras(bundleEnviado);
				startActivity(intent);
				break;

			case R.id.btUsuarioAnonimoSalir:
				finish();
				break;
		}
	}

	private ArrayAdapter<CharSequence> usarSpinProvinciasUsandoArrayLIst() {
		ArrayList<String> alProvincias = new ArrayList<String>();
		alProvincias.add("A Coruña");
		alProvincias.add("Lugo");
		alProvincias.add("Ourense");
		alProvincias.add("Pontevedra");

		ArrayAdapter<CharSequence> adapterSpinProvincias =
				new ArrayAdapter(this,
						android.R.layout.simple_spinner_item,
						alProvincias);

		return adapterSpinProvincias;

	}

	private ArrayAdapter<CharSequence> usarSpinProvinciasUsandoArchivoRecursos() {
		ArrayAdapter<CharSequence> adapterSpinProvincias = //adaptador para spin usando archivo recursos
				ArrayAdapter.createFromResource(
						getApplicationContext(),                  //contexto
						R.array.dataTo_spinProvincias,            //origen recursos
						android.R.layout.simple_spinner_item);    //modelo de spinner

		return adapterSpinProvincias;
	}
}
