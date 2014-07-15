package br.com.rrc.diariodetreino;

import java.util.List;

import br.com.rrc.SQLiteDAL.DALDivisao;
import br.com.rrc.SQLiteDAL.DALTreino;
import br.com.rrc.model.MDLTreino;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
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
	DALTreino daltreino;
	private boolean exclusao = false;
	MDLTreino mdlTreino;
	@Override
	public void onCreate(Bundle icicle){
		super.onCreate(icicle);
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
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id){
		if(exclusao){
			exclusao = false;
			mdlTreino = listaTreino.get(position);
			this.Confirm(ListaTreinos.this, "Excluir", "Excluir o treino " + mdlTreino.getVch_Nome_Treino() + " ?");
		}
		else{
			if(listaTreino.isEmpty()){
				startActivity(new Intent(this, NovoTreinoActivity.class));
				finish();
			}
			else{
				startActivity(listaTreino.get(position).getPk_Int_Codigo_Treino());
			}
		}
	}

	private void startActivity(int pk_int_codigo_treino){
		Intent intent = new Intent(this, ListaDivisoes.class);
		intent.putExtra("pk_int_codigo_treino", pk_int_codigo_treino);
		startActivity(intent);
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!listaTreino.isEmpty())
			menu.add(0, 1, 1, "Excluir");
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		switch (id) {
		case R.id.novo_treino:
			startActivity(new Intent(this, NovoTreinoActivity.class)); 
			finish();
			break;

		case R.id.sair:
			System.exit(0);
			finish();
			break;
			
		case 1:
			excluirTreino();
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

	protected boolean Confirm(Activity act, String Title, String ConfirmText) {

		AlertDialog dialog = new AlertDialog.Builder(act).create();
		dialog.setTitle(Title);
		dialog.setMessage(ConfirmText);
		dialog.setCancelable(false);
		dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Sim",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int buttonId) {
				excluirTreinoEFilhos(mdlTreino);
				Toast.makeText(ListaTreinos.this, "Excluido!", Toast.LENGTH_SHORT).show();
				carregarLista();
			}
		});
		dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Não",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int buttonId) {
				mdlTreino = null;
				carregarLista();
			}
		});
		dialog.setIcon(android.R.drawable.ic_dialog_alert);
		dialog.show();
		return true;
	}	
	
	protected void excluirTreinoEFilhos(MDLTreino mdlTreino){
		new DALDivisao(ListaTreinos.this).Excluir(mdlTreino.getPk_Int_Codigo_Treino());
		daltreino.Excluir(mdlTreino);
	}
	
	protected void excluirTreino() {
		this.setListAdapter(new ArrayAdapter<MDLTreino>(ListaTreinos.this, 
				android.R.layout.simple_list_item_single_choice, 
				listaTreino));
		exclusao = true;
	}
}
