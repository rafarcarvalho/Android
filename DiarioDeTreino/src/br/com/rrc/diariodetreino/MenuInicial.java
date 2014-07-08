package br.com.rrc.diariodetreino;

import java.util.List;

import br.com.rrc.SQLiteDAL.DALTreino;
import br.com.rrc.model.MDLTreino;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;;

public class MenuInicial extends ListActivity {

	List<MDLTreino> listaTreino = null;

	@Override
	public void onCreate(Bundle icicle){
		super.onCreate(icicle);

		DALTreino daltreino = new DALTreino(MenuInicial.this);

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
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id){
		Toast.makeText(MenuInicial.this, "ID is " + listaTreino.get(position).getPk_Int_Codigo_Treino(), Toast.LENGTH_SHORT).show();
	}

	private void startActivity(int layoutid){
		//Intent intent = new Intent(this, ActivityLayoutIdGenerica.class);
		//intent.putExtra("layoutResId", layoutid);
		//startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == R.id.novo_treino) {        	        	        	
			startActivity(new Intent(this, NovoTreinoActivity.class));        	
		}
		return super.onOptionsItemSelected(item);
	}
}
