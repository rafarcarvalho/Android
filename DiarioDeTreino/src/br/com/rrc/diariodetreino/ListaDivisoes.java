package br.com.rrc.diariodetreino;

import java.util.ArrayList;
import java.util.List;

import br.com.rrc.SQLiteDAL.DALDivisao;
import br.com.rrc.model.MDLDivisao;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
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
	private boolean exclusao = false;
	DALDivisao dalDivisao;
	MDLDivisao mdlDivisao;
	@Override
	public void onCreate(Bundle icicle){
		super.onCreate(icicle);
		dalDivisao = new DALDivisao(ListaDivisoes.this);
		carregarLista();
	}

	private void carregarLista(){

		Bundle extras = getIntent().getExtras();

		pk_int_codigo_treino = extras.getInt("pk_int_codigo_treino");
		//Toast.makeText(ListaDivisoes.this, pk_int_codigo_treino + "", Toast.LENGTH_SHORT).show();
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
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id){

		if(exclusao){

			mdlDivisao = listaDivisoes.get(position);
			this.Confirm(ListaDivisoes.this, "Excluir", "Excluir a divisão " + mdlDivisao.getChr_Divisao() + " ?");
		}
		else{
			if(listaDivisoes.isEmpty()){
				chamarNovaDivisao();
			}
			else{
				Toast.makeText(ListaDivisoes.this, "ID is " + listaDivisoes.get(position).getPk_Int_Codigo_Divisao(), Toast.LENGTH_SHORT).show();
			}
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
		menu.add(0, 0, 0, "Novo").setIcon(android.R.drawable.ic_menu_add);
		if (!listaDivisoes.isEmpty())
			menu.add(0, 1, 1, "Excluir").setIcon(android.R.drawable.ic_menu_delete);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {      
		switch (item.getItemId()) {
		case 0:
			chamarNovaDivisao();
			break;

		case 1:
			excluirDivisão();
			break;

		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		if(exclusao){
			exclusao = false;
			mdlDivisao = null;
			carregarLista();
		}
		else{
			Intent intent = new Intent(this, ListaTreinos.class);
			startActivity(intent);
			finish();
		}
	}

	protected void excluirDivisão() {
		this.setListAdapter(new ArrayAdapter<MDLDivisao>(ListaDivisoes.this, 
				android.R.layout.simple_list_item_single_choice, 
				listaDivisoes));
		exclusao = true;
	}

	public boolean Confirm(Activity act, String Title, String ConfirmText) {

		AlertDialog dialog = new AlertDialog.Builder(act).create();
		dialog.setTitle(Title);
		dialog.setMessage(ConfirmText);
		dialog.setCancelable(false);
		dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Sim",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int buttonId) {
				exclusao = false;
				dalDivisao.Excluir(mdlDivisao);
				Toast.makeText(ListaDivisoes.this, "Excluido!", Toast.LENGTH_SHORT).show();
				carregarLista();
			}
		});
		dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Não",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int buttonId) {
				exclusao = false;
				mdlDivisao = null;
				carregarLista();
			}
		});
		dialog.setIcon(android.R.drawable.ic_dialog_alert);
		dialog.show();
		return true;
	}
}
