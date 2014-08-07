package br.com.rrc.diariodetreino;

import java.util.List;

import br.com.rrc.SQLiteDAL.DALExercicio;
import br.com.rrc.SQLiteDAL.DALGrupoDivisao;
import br.com.rrc.model.MDLExercicio;
import br.com.rrc.model.MDLGrupoDivisao;
import br.com.rrc.model.MDLGrupoMuscular;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class NovoExercicioGrupoDivisao extends ListActivity {

	private int pk_int_codigo_grupo_divisao;
	private int pk_int_codigo_divisao;
	private int pk_int_codigo_treino;
	List<MDLExercicio> listaExercicios;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		criarInicializarObjetos();

	}


	private void criarInicializarObjetos(){
		Bundle extras = getIntent().getExtras();

		pk_int_codigo_grupo_divisao = extras.getInt("pk_int_codigo_grupo_divisao");
		pk_int_codigo_divisao = extras.getInt("pk_int_codigo_divisao");
		pk_int_codigo_treino = extras.getInt("pk_int_codigo_treino");
		
		
		listaExercicios = new DALExercicio(NovoExercicioGrupoDivisao.this).Consultar();

		this.setListAdapter(new ArrayAdapter<MDLExercicio>(NovoExercicioGrupoDivisao.this, 
				android.R.layout.simple_list_item_1, 
				listaExercicios));

		ListView list = getListView();
		registerForContextMenu(list);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id){
		if(listaExercicios.isEmpty()){
			//chamarNovoGrupo();
		}
		else{
			//mdlGrupoMuscular = listaGruposMusculares.get(position);
		}
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);

		if (!listaExercicios.isEmpty()){
			menu.setHeaderTitle("Mais");
			menu.setHeaderIcon(android.R.drawable.ic_menu_more);
			menu.add(0, 3, 0, "Excluir");
		}

		getMenuInflater().inflate(R.menu.main, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {		
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		switch(item.getItemId()) {
		case 2:
			salvarNoGrupo(listaExercicios.get(info.position));
			break;
		case 3:
			//excluirGrupoDivisaoEFilhos(listaGruposMusculares.get(info.position));
			break;
		default:

		}
		return super.onContextItemSelected(item);
	}

	public void salvarNoGrupo(MDLExercicio mdlExercicio){


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
		Intent startActivity = new Intent(NovoExercicioGrupoDivisao.this, ListaGruposMusculares.class);
		startActivity.putExtra("pk_int_codigo_divisao", pk_int_codigo_divisao);
		startActivity.putExtra("pk_int_codigo_treino", pk_int_codigo_treino);
		startActivity(startActivity);
		finish();
	}
}
