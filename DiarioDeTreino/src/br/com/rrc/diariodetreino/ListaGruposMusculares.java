package br.com.rrc.diariodetreino;

import java.util.ArrayList;
import java.util.List;

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
import br.com.rrc.SQLiteDAL.DALGrupoDivisao;
import br.com.rrc.SQLiteDAL.DALGrupoMuscular;
import br.com.rrc.model.MDLGrupoDivisao;
import br.com.rrc.model.MDLGrupoMuscular;

public class ListaGruposMusculares extends ListActivity {

	private List<MDLGrupoDivisao> listaGrupoDivisao = new ArrayList<MDLGrupoDivisao>();
	private int pk_int_codigo_divisao;
	private int pk_int_codigo_treino;
	private DALGrupoDivisao dalGrupoDivisao;
	private DALGrupoMuscular dalGrupoMuscular;

	@Override
	public void onCreate(Bundle icicle){
		super.onCreate(icicle);
		dalGrupoDivisao = new DALGrupoDivisao(ListaGruposMusculares.this);
		dalGrupoMuscular = new DALGrupoMuscular(ListaGruposMusculares.this);
		carregarLista();
	}

	private void carregarLista(){

		Bundle extras = getIntent().getExtras();

		pk_int_codigo_divisao = extras.getInt("pk_int_codigo_divisao");
		pk_int_codigo_treino = extras.getInt("pk_int_codigo_treino");

		List<MDLGrupoMuscular> listaGruposMusculares = new ArrayList<MDLGrupoMuscular>();

		//Toast.makeText(ListaGruposMusculares.this, pk_int_codigo_treino + " " + pk_int_codigo_divisao, Toast.LENGTH_SHORT).show();
		if(pk_int_codigo_divisao != 0)
			listaGrupoDivisao = dalGrupoDivisao.Consultar(pk_int_codigo_divisao);

		if(listaGrupoDivisao.isEmpty()){
			this.setListAdapter(new ArrayAdapter<String>(ListaGruposMusculares.this, 
					android.R.layout.simple_list_item_1, 
					new String[] { "Esta divisão não possui nenhum grupo muscular" }));
		}
		else{
			for(int i = 0; i < listaGrupoDivisao.size(); i++){
				listaGruposMusculares.add(dalGrupoMuscular.Consultar(listaGrupoDivisao.get(i).getFk_Int_Codigo_Grupo_Muscular()));
			}

			this.setListAdapter(new ArrayAdapter<MDLGrupoMuscular>(ListaGruposMusculares.this, 
					android.R.layout.simple_list_item_1, 
					listaGruposMusculares));
		}

		ListView list = getListView();
		registerForContextMenu(list);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id){
		if(listaGrupoDivisao.isEmpty()){
			chamarNovoGrupo();
		}
		else{
			Toast.makeText(ListaGruposMusculares.this, "ID is " + listaGrupoDivisao.get(position).getPk_Int_Codigo_Grupo_Divisao(), Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);

		if (!listaGrupoDivisao.isEmpty()){
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
		case 3:
			excluirGrupoDivisaoEFilhos(listaGrupoDivisao.get(info.position));
			break;
		default:

		}
		return super.onContextItemSelected(item);
	}

	protected void chamarNovoGrupo(){
		Intent intent = new Intent(this, NovoGrupoDivisao.class);
		intent.putExtra("pk_int_codigo_divisao", pk_int_codigo_divisao);
		intent.putExtra("pk_int_codigo_treino", pk_int_codigo_treino);
		startActivity(intent);
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 0, 0, "Novo").setIcon(android.R.drawable.ic_menu_add);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {      
		switch (item.getItemId()) {
		case 0:
			chamarNovoGrupo();
			break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(this, ListaDivisoes.class);
		intent.putExtra("pk_int_codigo_treino", pk_int_codigo_treino);
		startActivity(intent);
		finish();
	}

	protected void excluirGrupoDivisaoEFilhos(MDLGrupoDivisao mdlGrupoDivisao){
		dalGrupoDivisao.Excluir(mdlGrupoDivisao);
		carregarLista();
	}
}
