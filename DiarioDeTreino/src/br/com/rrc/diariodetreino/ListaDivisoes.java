package br.com.rrc.diariodetreino;

import java.util.ArrayList;
import java.util.List;

import br.com.rrc.SQLiteDAL.DALDivisao;
import br.com.rrc.model.MDLDivisao;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListaDivisoes extends ListActivity {

	private List<MDLDivisao> listaDivisoes = new ArrayList<MDLDivisao>();
	private int pk_int_codigo_treino;
	@Override
	public void onCreate(Bundle icicle){
		super.onCreate(icicle);

		carregarLista();
	}

	private void carregarLista(){
		DALDivisao dalDivisao = new DALDivisao(ListaDivisoes.this);

		Bundle extras = getIntent().getExtras();

		pk_int_codigo_treino = extras.getInt("pk_int_codigo_treino");

		if(pk_int_codigo_treino != 0)
			listaDivisoes = dalDivisao.Consultar(pk_int_codigo_treino, true);

		if(listaDivisoes.isEmpty()){
			this.setListAdapter(new ArrayAdapter<String>(this, 
					android.R.layout.simple_list_item_1, 
					new String[] { "Este treino não possui nenhuma divisão" }));
		}
		else{
			this.setListAdapter(new ArrayAdapter<MDLDivisao>(this, 
					android.R.layout.simple_list_item_1, 
					listaDivisoes));
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id){
		if(listaDivisoes.isEmpty()){
			chamarNovaDivisao();
		}
		else{
			Toast.makeText(ListaDivisoes.this, "ID is " + listaDivisoes.get(position).getPk_Int_Codigo_Divisao(), Toast.LENGTH_SHORT).show();
		}
	}
	
	protected void chamarNovaDivisao(){
		Intent intent = new Intent(this, NovaDivisaoActivity.class);
		intent.putExtra("pk_int_codigo_treino", pk_int_codigo_treino);
		startActivity(intent);
		finish();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 0, 0, "Nova Divisão");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {      
		int id = item.getItemId();
		if (id == 0) {
			chamarNovaDivisao();
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent(this, ListaTreinos.class);
		startActivity(intent);
		finish();
	}
}
