package br.com.rrc.diariodetreino;

import br.com.rrc.SQLiteDAL.DALDivisao;
import br.com.rrc.model.MDLDivisao;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class NovaDivisaoActivity extends Activity {

	Spinner spnDivisoes;
	Button btnSalvarDivisao, btnCancelarDivisao;
	private int pk_int_codigo_treino;
	String letra;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nova_divisao);
		criarInicializarObjetos();

	}

	private void criarInicializarObjetos(){

		Bundle extras = getIntent().getExtras();

		pk_int_codigo_treino = extras.getInt("pk_int_codigo_treino");

		String[] divisoes = getResources().getString(R.string.divisoes).split(",");

		spnDivisoes = (Spinner)findViewById(R.id.spnDivisoes);

		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, divisoes);
		ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
		spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
		spnDivisoes.setAdapter(spinnerArrayAdapter);

		btnSalvarDivisao = (Button)findViewById(R.id.btnSalvarDivisao);
		btnSalvarDivisao.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DALDivisao dalDivisao = new DALDivisao(NovaDivisaoActivity.this);
				//Toast.makeText(NovaDivisaoActivity.this, (letra.charAt(0)) + "", Toast.LENGTH_SHORT).show();
				MDLDivisao mdlDivisao = dalDivisao.Consultar(pk_int_codigo_treino, letra.charAt(0));

				if(mdlDivisao == null){
					mdlDivisao = new MDLDivisao();
					mdlDivisao.setChr_Divisao(letra.charAt(0));
					mdlDivisao.setFk_Int_Codigo_Treino(pk_int_codigo_treino);
					dalDivisao.Inserir(mdlDivisao);
					Toast.makeText(NovaDivisaoActivity.this, "Salvo", Toast.LENGTH_SHORT).show();
					new Handler().postDelayed(new Runnable() {
						@Override
						public void run() {
							chamarListaDivisoes();
						}
					}, 2000);
				}
				else{
					Toast.makeText(NovaDivisaoActivity.this, "O treino já contém uma divisão " + letra, Toast.LENGTH_SHORT).show();
				}
			}
		});

		

		spnDivisoes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
				letra = parent.getItemAtPosition(posicao).toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});


		btnCancelarDivisao = (Button)findViewById(R.id.btnCancelarDivisao);
		btnCancelarDivisao.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				chamarListaDivisoes();
			}
		});
	}
	@Override
	public void onBackPressed() {
		chamarListaDivisoes();
	}
	
	private void chamarListaDivisoes(){
		Intent startActivity = new Intent(NovaDivisaoActivity.this, ListaDivisoes.class);
		startActivity.putExtra("pk_int_codigo_treino", pk_int_codigo_treino);
		startActivity(startActivity);
		finish();
	}
}
