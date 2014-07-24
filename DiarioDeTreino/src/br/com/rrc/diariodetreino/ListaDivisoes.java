package br.com.rrc.diariodetreino;

import java.util.ArrayList;
import java.util.List;

import br.com.rrc.SQLiteDAL.DALDivisao;
import br.com.rrc.model.MDLDivisao;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.HeaderViewListAdapter;
import android.widget.ListView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Toast;

public class ListaDivisoes extends ListActivity {

	private List<MDLDivisao> listaDivisoes = new ArrayList<MDLDivisao>();
	private int pk_int_codigo_treino;
	DALDivisao dalDivisao;
	@Override
	public void onCreate(Bundle icicle){
		super.onCreate(icicle);
		dalDivisao = new DALDivisao(ListaDivisoes.this);
		this.setTitle("teste");
		carregarLista();
	}

	private void carregarLista(){

		Bundle extras = getIntent().getExtras();

		pk_int_codigo_treino = extras.getInt("pk_int_codigo_treino");
		//Toast.makeText(ListaDivisoes.this, "pk_int_codigo_treino: " + pk_int_codigo_treino, Toast.LENGTH_SHORT).show();
		if(pk_int_codigo_treino != 0)
			listaDivisoes = dalDivisao.Consultar(pk_int_codigo_treino, true);

		if(listaDivisoes.isEmpty()){
			this.setListAdapter(new ArrayAdapter<String>(ListaDivisoes.this, 
					android.R.layout.simple_list_item_1, 
					new String[] { "Este treino não possui nenhuma divisão" }));
		}
		else{
			this.setListAdapter(new ArrayAdapter<MDLDivisao>(ListaDivisoes.this, 
					android.R.layout.simple_list_item_1, 
					listaDivisoes));
		}

		ListView list = getListView();
		registerForContextMenu(list);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id){
		if(listaDivisoes.isEmpty()){
			chamarNovaDivisao();
		}
		else{
			//Toast.makeText(ListaDivisoes.this, "ID is " + listaDivisoes.get(position).getPk_Int_Codigo_Divisao(), Toast.LENGTH_SHORT).show();
			startActivity(listaDivisoes.get(position).getPk_Int_Codigo_Divisao());
		}
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);

		if (!listaDivisoes.isEmpty()){
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
			excluirDivisaoEFilhos(listaDivisoes.get(info.position));
			break;
		default:

		}
		return super.onContextItemSelected(item);
	}

	protected void chamarNovaDivisao(){
		Intent intent = new Intent(this, NovaDivisaoActivity.class);
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
			chamarNovaDivisao();
			break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		Intent intent = new Intent(this, ListaTreinos.class);
		startActivity(intent);
		finish();
	}

	protected void excluirDivisaoEFilhos(MDLDivisao mdlDivisao){
		dalDivisao.Excluir(mdlDivisao);
		carregarLista();
	}
	
	private void startActivity(int pk_int_codigo_divisao){
		Intent intent = new Intent(this, ListaGruposMusculares.class);
		intent.putExtra("pk_int_codigo_divisao", pk_int_codigo_divisao);
		intent.putExtra("pk_int_codigo_treino", pk_int_codigo_treino);
		startActivity(intent);
		finish();
	}
}
