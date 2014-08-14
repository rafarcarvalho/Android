package br.com.rrc.diariodetreino;

import java.util.List;

import br.com.rrc.SQLiteDAL.DALExercicio;
import br.com.rrc.SQLiteDAL.DALSerie;
import br.com.rrc.model.MDLExercicio;
import br.com.rrc.model.MDLSerie;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class NovoExercicioDivisao extends Activity {

	private int pk_int_codigo_divisao;
	private int pk_int_codigo_treino;
	private int fk_int_codigo_grupo_muscular;
	List<MDLExercicio> listaExercicios;
	private int fk_int_codigo_exercicio;
	String exercicio;
	Spinner spnExercicio;
	EditText txtCarga, txtRepeticoes;
	Button btnSalvarSerie, btnCancelarSerie;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nova_serie);
		criarInicializarObjetos();

	}

	private void criarInicializarObjetos(){

		Bundle extras = getIntent().getExtras();

		pk_int_codigo_divisao = extras.getInt("pk_int_codigo_divisao");
		pk_int_codigo_treino = extras.getInt("pk_int_codigo_treino");
		fk_int_codigo_grupo_muscular = extras.getInt("fk_int_codigo_grupo_muscular");

		listaExercicios = new DALExercicio(NovoExercicioDivisao.this).Consultar(fk_int_codigo_grupo_muscular);

		spnExercicio = (Spinner)findViewById(R.id.spnExercicio);
		ArrayAdapter<MDLExercicio> arrayAdapter = new ArrayAdapter<MDLExercicio>(NovoExercicioDivisao.this, android.R.layout.simple_spinner_item, listaExercicios);
		ArrayAdapter<MDLExercicio> spinnerArrayAdapter = arrayAdapter;
		spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
		spnExercicio.setAdapter(spinnerArrayAdapter);

		spnExercicio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
				fk_int_codigo_exercicio = listaExercicios.get(posicao).getPk_Int_Codigo_Exercicio();
				exercicio = listaExercicios.get(posicao).getVch_Descricao();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		txtCarga = (EditText)findViewById(R.id.txtCarga);
		txtRepeticoes = (EditText)findViewById(R.id.txtRepeticoes);

		btnSalvarSerie = (Button)findViewById(R.id.btnSalvarSerie);
		btnSalvarSerie.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DALSerie dalSerie = new DALSerie(NovoExercicioDivisao.this);
				MDLSerie mdlSerie = dalSerie.Consultar(pk_int_codigo_divisao, fk_int_codigo_exercicio);

				if(mdlSerie == null){
					mdlSerie = new MDLSerie();

					mdlSerie.setFlt_Carga(Double.parseDouble(txtCarga.getText().toString()));
					mdlSerie.setInt_Repeticoes(Integer.parseInt(txtRepeticoes.getText().toString()));
					mdlSerie.setFk_Int_Codigo_Exercicio(fk_int_codigo_exercicio);
					mdlSerie.setFk_Int_Codigo_Grupo_Divisao(pk_int_codigo_divisao);

					dalSerie.Inserir(mdlSerie);
					Toast.makeText(NovoExercicioDivisao.this, "Salvo", Toast.LENGTH_SHORT).show();

					chamarListaGruposMusculares();

				}
				else{
					Toast.makeText(NovoExercicioDivisao.this, "O exercício " + exercicio + " já foi adicionado ao treino." , Toast.LENGTH_LONG).show();
				}
			}
		});

		btnCancelarSerie = (Button)findViewById(R.id.btnCancelarSerie);
		btnCancelarSerie.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				chamarListaGruposMusculares();
			}
		});
	}

	public void salvarNoGrupo(MDLExercicio mdlExercicio){


	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {      
		switch (item.getItemId()) {
		case 0:
			//chamarNovoExrcicio();
			break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 0, 0, "Novo Exercício").setIcon(android.R.drawable.ic_menu_add);
		return true;
	}

	@Override
	public void onBackPressed() {
		chamarListaGruposMusculares();
	}

	private void chamarListaGruposMusculares(){
		Intent startActivity = new Intent(NovoExercicioDivisao.this, ListaGruposMusculares.class);
		startActivity.putExtra("pk_int_codigo_divisao", pk_int_codigo_divisao);
		startActivity.putExtra("pk_int_codigo_treino", pk_int_codigo_treino);
		startActivity(startActivity);
		finish();
	}
}
