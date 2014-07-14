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

public class ListaTreinos extends ListActivity {

	List<MDLTreino> listaTreino = null;

	@Override
	public void onCreate(Bundle icicle){
		super.onCreate(icicle);

		carregarLista();
	}

	private void carregarLista(){
		DALTreino daltreino = new DALTreino(ListaTreinos.this);

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
		if(listaTreino.isEmpty()){
			startActivity(new Intent(this, NovoTreinoActivity.class));
			finish();
		}
		else{
			//Toast.makeText(ListaTreinos.this, "ID is " + listaTreino.get(position).getPk_Int_Codigo_Treino(), Toast.LENGTH_SHORT).show();
			startActivity(listaTreino.get(position).getPk_Int_Codigo_Treino());
		}
	}

	private void startActivity(int pk_int_codigo_treino){
		Intent intent = new Intent(this, ListaDivisoes.class);
		intent.putExtra("pk_int_codigo_treino", pk_int_codigo_treino);
		startActivity(intent);
		finish();
		//Toast.makeText(ListaTreinos.this, "pk_int_codigo_treino " + pk_int_codigo_treino, Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		switch (id) {
		case R.id.novo_treino:
			startActivity(new Intent(this, NovoTreinoActivity.class)); 
			break;

		case R.id.sair:
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
}
