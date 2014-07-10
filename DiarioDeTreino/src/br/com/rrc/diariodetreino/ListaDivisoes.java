package br.com.rrc.diariodetreino;

import java.util.List;

import br.com.rrc.SQLiteDAL.DALDivisao;
import br.com.rrc.model.MDLDivisao;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListaDivisoes extends ListActivity {

	private List<MDLDivisao> listaDivisoes;
	private int pk_int_codigo_treino;
	@Override
	public void onCreate(Bundle icicle){
		super.onCreate(icicle);

		//carregarLista();
	}
	
	private void carregarLista(){
		DALDivisao dalDivisao = new DALDivisao(ListaDivisoes.this);

		Bundle extras = getIntent().getExtras();
		
		pk_int_codigo_treino = extras.getInt("pk_Int_Codigo_Treino");
		
		//Toast.makeText(ListaDivisoes.this, "pk_Int_Codigo_Treino " + pk_int_codigo_treino, Toast.LENGTH_SHORT).show();
		
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
			startActivity(new Intent(this, NovaDivisaoActivity.class));
		}
		else{
			Toast.makeText(ListaDivisoes.this, "ID is " + listaDivisoes.get(position).getPk_Int_Codigo_Divisao(), Toast.LENGTH_SHORT).show();
			//startActivity(listaDivisoes.get(position).getPk_Int_Codigo_Divisao());
		}
	}
	
	private void startActivity(int pk_int_codigo_divisao){
		//Intent intent = new Intent(this, ListaDivisoes.class);
		//intent.putExtra("pk_int_codigo_divisao", pk_int_codigo_divisao);
		//startActivity(intent);
	}
}
