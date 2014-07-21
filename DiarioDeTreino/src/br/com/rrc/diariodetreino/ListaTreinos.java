package br.com.rrc.diariodetreino;

import java.util.List;

import br.com.rrc.SQLiteDAL.DALDivisao;
import br.com.rrc.SQLiteDAL.DALTreino;
import br.com.rrc.SQLiteDAL.SQLiteHelper;
import br.com.rrc.model.MDLTreino;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;;

public class ListaTreinos extends ListActivity {

	List<MDLTreino> listaTreino = null;
	DALTreino daltreino;
	@Override
	public void onCreate(Bundle icicle){
		super.onCreate(icicle);
		
		new SQLiteHelper(ListaTreinos.this);
		
		daltreino = new DALTreino(ListaTreinos.this);
		carregarLista();
	}

	private void carregarLista(){

		listaTreino = daltreino.Consultar();

		if(listaTreino.isEmpty()){

			this.setListAdapter(new ArrayAdapter<String>(this, 
					android.R.layout.simple_list_item_1, 
					new String[] { "Nenhum treino encontrado" }));
		}
		else{
			this.setListAdapter(new ArrayAdapter<MDLTreino>(this, 
					android.R.layout.simple_list_item_1, 
					listaTreino));
		}

		ListView list = getListView();
		registerForContextMenu(list);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id){
		if(listaTreino.isEmpty()){
			startActivity(new Intent(this, NovoTreinoActivity.class));
			finish();
		}
		else{
			startActivity(listaTreino.get(position).getPk_Int_Codigo_Treino());
		}
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);

		if (!listaTreino.isEmpty()){
			menu.setHeaderTitle("Opções");
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
			if(listaTreino.isEmpty())
				carregarLista();
			else
				excluirTreinoEFilhos(listaTreino.get(info.position));
			break;
		default:

		}
		return super.onContextItemSelected(item);
	}

	private void startActivity(int pk_int_codigo_treino){
		Intent intent = new Intent(this, ListaDivisoes.class);
		intent.putExtra("pk_int_codigo_treino", pk_int_codigo_treino);
		startActivity(intent);
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 0, 0, "Novo").setIcon(android.R.drawable.ic_menu_add);
		menu.add(0, 1, 0, "Sair").setIcon(android.R.drawable.ic_lock_power_off);
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		switch (id) {
		case 0:
			startActivity(new Intent(this, NovoTreinoActivity.class)); 
			finish();
			break;

		case 1:
			System.exit(0);
			finish();
			break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		System.exit(0);
		finish();
	}

	protected void excluirTreinoEFilhos(MDLTreino mdlTreino){
		new DALDivisao(ListaTreinos.this).Excluir(mdlTreino.getPk_Int_Codigo_Treino());
		daltreino.Excluir(mdlTreino);
		carregarLista();
	}
}
