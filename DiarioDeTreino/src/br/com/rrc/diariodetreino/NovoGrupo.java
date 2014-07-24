package br.com.rrc.diariodetreino;

import java.util.List;

import br.com.rrc.SQLiteDAL.DALGrupoDivisao;
import br.com.rrc.SQLiteDAL.DALGrupoMuscular;
import br.com.rrc.model.MDLGrupoDivisao;
import br.com.rrc.model.MDLGrupoMuscular;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class NovoGrupo extends Activity {

	Spinner spnGruposMusculares;
	Button btnSalvarGrupo, btnCancelarGrupo;
	private int pk_int_codigo_divisao;
	private MDLGrupoMuscular mdlGrupoMuscular;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_novo_grupo);
		criarInicializarObjetos();

	}
	
	private void criarInicializarObjetos(){

		Bundle extras = getIntent().getExtras();

		pk_int_codigo_divisao = extras.getInt("pk_int_codigo_divisao");

		List<MDLGrupoMuscular> gruposMusculares = new DALGrupoMuscular(NovoGrupo.this).Consultar();

		spnGruposMusculares = (Spinner)findViewById(R.id.spnGruposMusculares);

		ArrayAdapter<MDLGrupoMuscular> arrayAdapter = new ArrayAdapter<MDLGrupoMuscular>(this, android.R.layout.simple_spinner_item, gruposMusculares);
		ArrayAdapter<MDLGrupoMuscular> spinnerArrayAdapter = arrayAdapter;
		spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
		spnGruposMusculares.setAdapter(spinnerArrayAdapter);

		btnSalvarGrupo = (Button)findViewById(R.id.btnSalvarGrupo);
		btnSalvarGrupo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DALGrupoDivisao dalGrupoDivisao = new DALGrupoDivisao(NovoGrupo.this);
				MDLGrupoDivisao mdlGrupoDivisao = dalGrupoDivisao.Consultar(pk_int_codigo_divisao, mdlGrupoMuscular.getPk_Int_Codigo_Grupo_Muscular());
				
				//Toast.makeText(NovoGrupo.this,  mdlGrupoMuscular.getPk_Int_Codigo_Grupo_Muscular() + " " + pk_int_codigo_divisao, Toast.LENGTH_SHORT).show();
				
				if(mdlGrupoDivisao != null){
					Toast.makeText(NovoGrupo.this, "Esta divisão já contém o grupo " + mdlGrupoMuscular.getVch_Nome(), Toast.LENGTH_SHORT).show();
				}
				else{
					mdlGrupoDivisao = new MDLGrupoDivisao();
					mdlGrupoDivisao.setFk_Int_Codigo_Divisao(pk_int_codigo_divisao);
					mdlGrupoDivisao.setFk_Int_Codigo_Grupo_Muscular(mdlGrupoMuscular.getPk_Int_Codigo_Grupo_Muscular());
					dalGrupoDivisao.Inserir(mdlGrupoDivisao);
					Toast.makeText(NovoGrupo.this, "Salvo", Toast.LENGTH_SHORT).show();
					new Handler().postDelayed(new Runnable() {
						@Override
						public void run() {
							chamarListaGruposMusculares();
						}
					}, 2000);
				}
			}
		});
		
		spnGruposMusculares.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
				mdlGrupoMuscular = (MDLGrupoMuscular) parent.getItemAtPosition(posicao);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
		
		btnCancelarGrupo = (Button)findViewById(R.id.btnCancelarGrupo);
		btnCancelarGrupo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				chamarListaGruposMusculares();
			}
		});
	}
	
	@Override
	public void onBackPressed() {
		chamarListaGruposMusculares();
	}
	
	private void chamarListaGruposMusculares(){
		Intent startActivity = new Intent(NovoGrupo.this, ListaGruposMusculares.class);
		startActivity.putExtra("pk_int_codigo_divisao", pk_int_codigo_divisao);
		startActivity(startActivity);
		finish();
	}
}
