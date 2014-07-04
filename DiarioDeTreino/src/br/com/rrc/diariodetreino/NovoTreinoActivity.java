package br.com.rrc.diariodetreino;

import br.com.rrc.SQLiteDAL.DALTreino;
import br.com.rrc.model.MDLTreino;
import br.com.rrc.model.Util;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


public class NovoTreinoActivity extends Activity {

	EditText txtNomeAtleta, txtNomeTreino, txtNomeProjeto, txtDuracao, txtPausaSeries;
	ImageButton btnSalvar, btnExcluir;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_novo_treino);
		criarInicializarObjetos();

	}

	public void criarInicializarObjetos(){

		txtNomeAtleta = (EditText)findViewById(R.id.txtNomeAtleta);
		txtNomeTreino = (EditText)findViewById(R.id.txtNomeTreino);
		txtNomeProjeto = (EditText)findViewById(R.id.txtNomeProjeto);
		txtDuracao = (EditText)findViewById(R.id.txtDuracao);
		txtPausaSeries = (EditText)findViewById(R.id.txtPausaSeries);

		final AlertDialog.Builder alert = new AlertDialog.Builder(this);

		btnSalvar = (ImageButton)findViewById(R.id.btnSalvar);
		btnSalvar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if(Util.IsNullOrEmpty(txtNomeAtleta.getText().toString())) {
					/*alert.setMessage("Preencher nome do atleta!");
					alert.setNeutralButton("Ok", null);
					alert.show();*/
					
					Toast.makeText(NovoTreinoActivity.this, "Preencher nome do atleta!", Toast.LENGTH_SHORT).show();
				}
				else if(Util.IsNullOrEmpty(txtNomeTreino.getText().toString())){
					Toast.makeText(NovoTreinoActivity.this, "Preencher nome do treino!", Toast.LENGTH_SHORT).show();
					
				}
				else {
					DALTreino dalTreino = new DALTreino(NovoTreinoActivity.this);
					
					MDLTreino mdltreino = new MDLTreino();
					mdltreino.setVch_Nome_Atleta(txtNomeAtleta.getText().toString());
					mdltreino.setVch_Nome_Treino(txtNomeTreino.getText().toString());
					mdltreino.setVch_Projeto(txtNomeProjeto.getText().toString());
					mdltreino.setInt_Minutos_Duracao(Integer.parseInt(txtDuracao.getText().toString()));
					mdltreino.setInt_segundos_pausa_series(Integer.parseInt(txtPausaSeries.getText().toString()));
					
					
					dalTreino.Inserir(mdltreino);
					Toast.makeText(NovoTreinoActivity.this, "Salvo com sucesso!", Toast.LENGTH_SHORT).show();
				}
			}
		});

		btnExcluir = (ImageButton)findViewById(R.id.btnExcluir);
		btnExcluir.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 0, 0, "Voltar");    	
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {      
		int id = item.getItemId();
		if (id == 0) {
			startActivity(new Intent(this, MainActivity.class));
		}
		return super.onOptionsItemSelected(item);
	}

}
