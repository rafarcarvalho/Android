package br.com.rrc.diariodetreino;

import java.util.List;

import br.com.rrc.SQLiteDAL.DALGrupoDivisao;
import br.com.rrc.SQLiteDAL.DALGrupoMuscular;
import br.com.rrc.model.MDLGrupoDivisao;
import br.com.rrc.model.MDLGrupoMuscular;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class NovoGrupoDivisao extends ListActivity {

	Spinner spnGruposMusculares;
	Button btnSalvarGrupo, btnCancelarGrupo;
	private int pk_int_codigo_divisao;
	private int pk_int_codigo_treino;
	//private MDLGrupoMuscular mdlGrupoMuscular;
	List<MDLGrupoMuscular> listaGruposMusculares;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_novo_grupo_divisao);
		criarInicializarObjetos();

	}

	private void criarInicializarObjetos(){

		Bundle extras = getIntent().getExtras();

		pk_int_codigo_divisao = extras.getInt("pk_int_codigo_divisao");
		pk_int_codigo_treino = extras.getInt("pk_int_codigo_treino");
		
		listaGruposMusculares = new DALGrupoMuscular(NovoGrupoDivisao.this).Consultar();

		/*spnGruposMusculares = (Spinner)findViewById(R.id.spnGruposMusculares);

		ArrayAdapter<MDLGrupoMuscular> arrayAdapter = new ArrayAdapter<MDLGrupoMuscular>(this, android.R.layout.simple_spinner_item, gruposMusculares);
		ArrayAdapter<MDLGrupoMuscular> spinnerArrayAdapter = arrayAdapter;
		spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
		spnGruposMusculares.setAdapter(spinnerArrayAdapter);*/

		this.setListAdapter(new ArrayAdapter<MDLGrupoMuscular>(NovoGrupoDivisao.this, 
				android.R.layout.simple_list_item_1, 
				listaGruposMusculares));


		/*btnSalvarGrupo = (Button)findViewById(R.id.btnSalvarGrupo);
		btnSalvarGrupo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DALGrupoDivisao dalGrupoDivisao = new DALGrupoDivisao(NovoGrupoDivisao.this);
				MDLGrupoDivisao mdlGrupoDivisao = dalGrupoDivisao.Consultar(pk_int_codigo_divisao, mdlGrupoMuscular.getPk_Int_Codigo_Grupo_Muscular());

				//Toast.makeText(NovoGrupo.this,  mdlGrupoMuscular.getPk_Int_Codigo_Grupo_Muscular() + " " + pk_int_codigo_divisao, Toast.LENGTH_SHORT).show();

				if(mdlGrupoDivisao != null){
					Toast.makeText(NovoGrupoDivisao.this, "Esta divisão já contém o grupo " + mdlGrupoMuscular.getVch_Nome(), Toast.LENGTH_SHORT).show();
				}
				else{
					mdlGrupoDivisao = new MDLGrupoDivisao();
					mdlGrupoDivisao.setFk_Int_Codigo_Divisao(pk_int_codigo_divisao);
					mdlGrupoDivisao.setFk_Int_Codigo_Grupo_Muscular(mdlGrupoMuscular.getPk_Int_Codigo_Grupo_Muscular());
					dalGrupoDivisao.Inserir(mdlGrupoDivisao);
					Toast.makeText(NovoGrupoDivisao.this, "Salvo", Toast.LENGTH_SHORT).show();
					new Handler().postDelayed(new Runnable() {
						@Override
						public void run() {
							chamarListaGruposMusculares();
						}
					}, 2000);
				}
			}
		});*/

		/*spnGruposMusculares.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
				mdlGrupoMuscular = (MDLGrupoMuscular) parent.getItemAtPosition(posicao);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});*/

		/*btnCancelarGrupo = (Button)findViewById(R.id.btnCancelarGrupo);
		btnCancelarGrupo.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				chamarListaGruposMusculares();
			}
		});*/

		ListView list = getListView();
		registerForContextMenu(list);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id){
		if(listaGruposMusculares.isEmpty()){
			//chamarNovoGrupo();
		}
		else{
			//mdlGrupoMuscular = listaGruposMusculares.get(position);
		}
	}

	public void salvarNaDivisao(MDLGrupoMuscular mdlGrupoMuscular){
		
		DALGrupoDivisao dalGrupoDivisao = new DALGrupoDivisao(NovoGrupoDivisao.this);
		MDLGrupoDivisao mdlGrupoDivisao = dalGrupoDivisao.Consultar(pk_int_codigo_divisao, mdlGrupoMuscular.getPk_Int_Codigo_Grupo_Muscular());

		//Toast.makeText(NovoGrupo.this,  mdlGrupoMuscular.getPk_Int_Codigo_Grupo_Muscular() + " " + pk_int_codigo_divisao, Toast.LENGTH_SHORT).show();

		if(mdlGrupoDivisao != null){
			Toast.makeText(NovoGrupoDivisao.this, "Esta divisão já contém o grupo " + mdlGrupoMuscular.getVch_Nome(), Toast.LENGTH_SHORT).show();
		}
		else{
			mdlGrupoDivisao = new MDLGrupoDivisao();
			mdlGrupoDivisao.setFk_Int_Codigo_Divisao(pk_int_codigo_divisao);
			mdlGrupoDivisao.setFk_Int_Codigo_Grupo_Muscular(mdlGrupoMuscular.getPk_Int_Codigo_Grupo_Muscular());
			dalGrupoDivisao.Inserir(mdlGrupoDivisao);
			Toast.makeText(NovoGrupoDivisao.this, "Salvo", Toast.LENGTH_SHORT).show();

			chamarListaGruposMusculares();
		}
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);

		if (!listaGruposMusculares.isEmpty()){
			menu.setHeaderTitle("Mais");
			menu.setHeaderIcon(android.R.drawable.ic_menu_more);
			menu.add(0, 2, 0, "Incluir na divisão");
			menu.add(0, 3, 0, "Excluir");
		}

		getMenuInflater().inflate(R.menu.main, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {		
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		switch(item.getItemId()) {
		case 2:
			salvarNaDivisao(listaGruposMusculares.get(info.position));
			break;
		case 3:
			//excluirGrupoDivisaoEFilhos(listaGruposMusculares.get(info.position));
			break;
		default:

		}
		return super.onContextItemSelected(item);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {      
		switch (item.getItemId()) {
		case 0:
			//chamarNovoGrupo();
			break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 0, 0, "Novo").setIcon(android.R.drawable.ic_menu_add);
		return true;
	}

	@Override
	public void onBackPressed() {
		chamarListaGruposMusculares();
	}

	private void chamarListaGruposMusculares(){
		Intent startActivity = new Intent(NovoGrupoDivisao.this, ListaGruposMusculares.class);
		startActivity.putExtra("pk_int_codigo_divisao", pk_int_codigo_divisao);
		startActivity.putExtra("pk_int_codigo_treino", pk_int_codigo_treino);
		startActivity(startActivity);
		finish();
	}
}
