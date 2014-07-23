package br.com.rrc.diariodetreino;

import java.util.List;

import br.com.rrc.SQLiteDAL.DALGrupoMuscular;
import br.com.rrc.model.MDLGrupoMuscular;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class NovoGrupo extends Activity {

	Spinner spnGruposMusculares;
	Button btnSalvarGrupo, btnCancelarGrupo;
	private int fk_int_codigo_divisao;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_novo_grupo);
		

	}
	
	private void criarInicializarObjetos(){

		Bundle extras = getIntent().getExtras();

		fk_int_codigo_divisao = extras.getInt("fk_int_codigo_divisao");

		List<MDLGrupoMuscular> gruposMusculares = new DALGrupoMuscular(NovoGrupo.this).Consultar();

		spnGruposMusculares = (Spinner)findViewById(R.id.spnGruposMusculares);

		ArrayAdapter<MDLGrupoMuscular> arrayAdapter = new ArrayAdapter<MDLGrupoMuscular>(this, android.R.layout.simple_spinner_item, gruposMusculares);
		ArrayAdapter<MDLGrupoMuscular> spinnerArrayAdapter = arrayAdapter;
		spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
		spnGruposMusculares.setAdapter(spinnerArrayAdapter);

		btnSalvarGrupo = (Button)findViewById(R.id.btnSalvarGrupo);
		btnSalvarGrupo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//TODO Logica salvar
			}
		});
	}
}
