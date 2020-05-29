package com.ramonbl.pmdm_rec02;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class Frag_HeaderUsuario extends Fragment {
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//	private static final String ARG_PARAM1 = "param1";
//	private static final String ARG_PARAM2 = "param2";

	//TODO: ------- MIS OBJETOS -------------
	LinearLayout lyfragHeaderPrincipal;
	TextView tvBienvenidoUsuario;

	View vista; //servirá de puente para reconocer evento instancia botón

	private String stUsuarioTipoRecibido="";
	private String stUsuarioUsuarioRecibido="";

	public Frag_HeaderUsuario() {
		// Required empty public constructor
	}


	//TODO: Rename and change types and number of parameters para método cargar bundle del fragment
//	public static Frag_HeaderUsuario newInstance(String param1, String param2) {
//		Frag_HeaderUsuario fragment = new Frag_HeaderUsuario();
//		Bundle args = new Bundle();
//		args.putString(ARG_PARAM1, param1);
//		args.putString(ARG_PARAM2, param2);
//		fragment.setArguments(args);
//		return fragment;
//	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// TODO: Aquí haremos las inicializaciones y recogeremos los bundles
		if (getArguments() != null) { //comprobar que nos han pasado un bundle

			stUsuarioTipoRecibido = getArguments().getString(Act_Main.KEY_USUARIO_TIPO, "VD recibido en bundle");
			stUsuarioUsuarioRecibido = getArguments().getString(Act_Main.KEY_USUARIO_USUARIO, "VD " +
					"recibido en bundle");

		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// TODO: Aquí haremos todas las modificaciones visuales
		// Inflate the layout for this fragment
		vista = inflater.inflate(R.layout.frag_header_usuario, container, false);

		lyfragHeaderPrincipal = vista.findViewById(R.id.fragHeaderLayoutPrincipal);
		tvBienvenidoUsuario = vista.findViewById(R.id.tvBienvenidoUsuario);

		if(!stUsuarioTipoRecibido.isEmpty()){
			tvBienvenidoUsuario.setText("Hola " + stUsuarioUsuarioRecibido + " (tipo " +stUsuarioTipoRecibido + ")");
			if(stUsuarioTipoRecibido.equals("Anónimo")){
				lyfragHeaderPrincipal.setBackgroundColor(Color.GREEN);
			}		else	if(stUsuarioTipoRecibido.equals("Admin")){
				lyfragHeaderPrincipal.setBackgroundColor(Color.RED);
			}
		}
		return vista;
	}
}
