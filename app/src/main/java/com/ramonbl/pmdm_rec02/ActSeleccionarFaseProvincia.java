package com.ramonbl.pmdm_rec02;

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
import android.widget.Toast;
//import static com.ramonbl.pmdm_rec02.Act_Main.KEY_TIPO_USUARIO;

public class ActSeleccionarFaseProvincia extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

	//TODO: Vistas de la Activity
	Spinner spinProvincias;
	Spinner spinFases;
	String provincias[] = {"A Coruña", "Lugo", "Ourense", "Pontevedra"};

	/*Hago identificativos para mostrar que spinners están anidados*/
	String fasesACoruna[] = {"C0", "C1", "C2", "C3"};
	String fasesLugo[] = {"L0", "L1", "L2", "L3"};
	String fasesOurense[] = {"O0", "O1", "O2", "O3"};
	String fasesPontevedra[] = {"P0", "P1", "P2", "P3"};

	ArrayAdapter<String> adaptProvincias, adaptFasesACoruna, adaptFasesLugo, adaptFasesOurense, adaptFasesPontevedra;

	Button btSeleccionarFaseProvincia_Guardar, btSeleccionarFaseProvincia_Volver;

	Frag_HeaderUsuario fragHeaderUsuario;

	//Variables para los valores recibidos/enviado de/a bundle
	String stUsuarioTipoRecibido;
	String stUsuarioUsuarioRecibido;

	//Variables para datos extra
	String stProvincia, stFase;
	//ArrayList<String> alProvincias;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_seleccionar_fase_provincia);


		//TODO: Bundle recibido de otra Activity
		Bundle bundle = this.getIntent().getExtras();
		if (bundle != null) {
			stUsuarioTipoRecibido = bundle.getString(Act_Main.KEY_USUARIO_TIPO);
			stUsuarioUsuarioRecibido = bundle.getString(Act_Main.KEY_USUARIO_USUARIO);

		}

		Log.e("ActSelecFaseProvincia", stUsuarioTipoRecibido);
		Log.e("ActSelecFaseProvincia", stUsuarioUsuarioRecibido);

//		TODO: Creación fragment para esta Activity
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
		spinProvincias = findViewById(R.id.spinProvincias);
		spinFases = findViewById(R.id.spinFases);

		spinProvincias.setOnItemSelectedListener(this);
		spinFases.setOnItemSelectedListener(this);

		adaptProvincias = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, provincias);
		adaptFasesACoruna = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
				fasesACoruna);
		adaptFasesLugo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
				fasesLugo);
		adaptFasesOurense = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item, fasesOurense);
		adaptFasesPontevedra = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_dropdown_item, fasesPontevedra);
		spinProvincias.setAdapter(adaptProvincias);
		spinFases.setAdapter(adaptFasesACoruna);


	}

	public void mBtOnClick(View view) {

		switch (view.getId()) {
			case R.id.btSeleccionarFaseProvincia_Guardar:
				Toast.makeText(getApplicationContext(),
						"La provincia " + stProvincia + " pasa a la fase: " + stFase,
						Toast.LENGTH_LONG).show();

				break;
			case R.id.btSeleccionarFaseProvincia_Volver:
				finish();
				break;
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		Log.e("ActSelecFaseProvincia", "Posición Item: " + position);
		Log.e("ActSelecFaseProvincia", "Valor Item: " + provincias[position]);

		switch (parent.getId()) {
			case R.id.spinProvincias:
				switch (position) {
					case 0:
						spinFases.setAdapter(adaptFasesACoruna);
						break;
					case 1:
						spinFases.setAdapter(adaptFasesLugo);
						break;
					case 2:
						spinFases.setAdapter(adaptFasesOurense);
						break;
					case 3:
						spinFases.setAdapter(adaptFasesPontevedra);
						break;
				}
				stProvincia= parent.getItemAtPosition(position).toString();
				Log.e("ActSelecFaseProvincia", "Valor Provincia: " + stProvincia);
				break;

			case R.id.spinFases:
				stFase=parent.getItemAtPosition(position).toString();
				Log.e("ActSelecFaseProvincia", "Valor Fase: " + stFase);
		}


	}//FIN onItemSelected

	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}
}
