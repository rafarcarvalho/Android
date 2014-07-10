package br.com.rrc.diariodetreino;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

public class NovaDivisaoActivity extends Activity {

	Spinner spnDivisoes;
	ImageButton btnSalvarDivisao, btnCancelarDivisao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nova_divisao);
		//criarInicializarObjetos();

	}

	private void criarInicializarObjetos(){

		String[] divisoes = getResources().getString(R.string.divisoes).split(",");

		spnDivisoes = (Spinner)findViewById(R.id.spnDivisoes);
		
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, divisoes);
		ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
		spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
		spnDivisoes.setAdapter(spinnerArrayAdapter);

		
	}
}
