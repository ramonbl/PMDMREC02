package com.ramonbl.pmdm_rec02;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class Act_Main extends AppCompatActivity {

	public static final String KEY_USUARIO_TIPO = "keyUsuarioTipo";
	public static final String KEY_USUARIO_USUARIO = "keyUsuarioUsuario";

	LinearLayout actMainLayoutPrincipal, actMainLayoutDatos;
	TextView tvLogin;
	RadioGroup rgUsuarioTipo;
	RadioButton rbUsuarioTipoAnonimo, rbUsuarioTipoAdmin;
	EditText etUsuarioUsuario;
	EditText etUsuarioPassword;
	Button btLoginEnviar;

	Frag_HeaderUsuario fragHeaderUsuario;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_main);

		actMainLayoutDatos = findViewById(R.id.lyActMainPrincipal);
		tvLogin = findViewById(R.id.tvLogin);
		rgUsuarioTipo = findViewById(R.id.rgUsuarioTipo);
		rbUsuarioTipoAnonimo = findViewById(R.id.rbUsuarioTipoAnonimo);
		rbUsuarioTipoAdmin = findViewById(R.id.rbUsuarioTipoAdmin);
		etUsuarioUsuario = findViewById(R.id.etUsuarioUsuario);
		etUsuarioPassword = findViewById(R.id.etUsuarioPassword);
		btLoginEnviar = findViewById(R.id.btLoginEnviar);

		fragHeaderUsuario = new Frag_HeaderUsuario();

		/*En FragmentManager, empezamos transacción añadiendo un fragment en el lugar del fragHeader*/
		getSupportFragmentManager().beginTransaction().add(R.id.fragHeader, fragHeaderUsuario).commit();
		/*Si quisiésemos reemplazarlo por otro sería:
		 * getSupportFragmentManager().beginTransaction().replace(R.id.fragHeader, fragHeader).commit();
		 * */

		if (rbUsuarioTipoAnonimo.isChecked()) {
			etUsuarioUsuario.setText("Anonymous");
		}

	}

	public void mRbOnClick(View view) {
		if (view.getId() == R.id.rbUsuarioTipoAdmin) {
			activaEditTexts(etUsuarioUsuario, etUsuarioPassword);
		} else if (view.getId() == R.id.rbUsuarioTipoAnonimo) {
			desactivaEditTexts(etUsuarioUsuario, etUsuarioPassword);
		}
	}

	private void desactivaEditTexts(EditText etUsuarioUsuario, EditText etUsuarioPassword) {

		etUsuarioUsuario.setText("Anonymous");
		etUsuarioUsuario.setEnabled(false);
		etUsuarioPassword.setEnabled(false);
		etUsuarioUsuario.setVisibility(View.GONE);
		etUsuarioPassword.setVisibility(View.GONE);
	}

	private void activaEditTexts(EditText etUsuarioUsuario, EditText etUsuarioPassword) {


		etUsuarioUsuario.setText("");
		etUsuarioUsuario.setHint("Escribe el nombre de usuario (admin)");
		etUsuarioPassword.setText("");
		etUsuarioUsuario.setVisibility(View.VISIBLE);
		etUsuarioPassword.setVisibility(View.VISIBLE);
		etUsuarioUsuario.setEnabled(true);
		etUsuarioPassword.setEnabled(true);

	}

	public void mBtOnClick(View view) {
		boolean estaValidado;

		switch (view.getId()) {
			case R.id.btLoginEnviar:

				if (!etUsuarioUsuario.getText().toString().equals(""))
					Log.e("onClick Enviar: ", "enviado usuario: " + etUsuarioUsuario.getText().toString());
				else
					Log.e("onClick Enviar: ", "enviado usuario: VACÍO");
				estaValidado = validaUsuario(view);

				if (estaValidado)
					irActivityDelUsuario(view);
				else
					Toast.makeText(
							this, "Usuario o Contraseña Incorrecta", Toast.LENGTH_LONG).show();
				break;
		}

	}

	private boolean validaUsuario(View view) {
		if (!etUsuarioUsuario.getText().toString().equals(""))
			Log.e("validando usuario: ", etUsuarioUsuario.getText().toString());
		else
			Log.e("validando usuario: ", "VACÍO");
		if (rbUsuarioTipoAnonimo.isChecked() ||
				(rbUsuarioTipoAdmin.isChecked()
						&&
						etUsuarioUsuario.getText().toString().equals("admin") &&
						etUsuarioPassword.getText().toString().equals("abc123.")
				)) {
			Log.e("valido: ", "true");
			return true;

		} else {
			Log.e("valido: ", "false");
			return false;
		}
	}

	/*Va a una activity en función del tipo de usuario*/
	private void irActivityDelUsuario(View view) {
		Intent intent;
		Bundle bundleEnviado;
		if (rbUsuarioTipoAnonimo.isChecked()) {
			intent = new Intent(view.getContext(), Act_UsuarioAnonimo.class);
			bundleEnviado = new Bundle();
			bundleEnviado.putString(KEY_USUARIO_TIPO, rbUsuarioTipoAnonimo.getText().toString());
			bundleEnviado.putString(KEY_USUARIO_USUARIO, etUsuarioUsuario.getText().toString());
			intent.putExtras(bundleEnviado);
			startActivity(intent);
		} else if (rbUsuarioTipoAdmin.isChecked()) {

			intent = new Intent(view.getContext(), Act_UsuarioAdmin.class);
			bundleEnviado = new Bundle();
			bundleEnviado.putString(KEY_USUARIO_TIPO, rbUsuarioTipoAdmin.getText().toString());
			bundleEnviado.putString(KEY_USUARIO_USUARIO, etUsuarioUsuario.getText().toString());

			intent.putExtras(bundleEnviado);
			startActivity(intent);

		}
	}


}
