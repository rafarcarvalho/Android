package br.com.rrc.diariodetreino;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import br.com.rrc.SQLiteDAL.DALGrupoMuscular;
import br.com.rrc.model.MDLGrupoMuscular;

public class ListaGruposMusculares extends ListActivity {

	private List<MDLGrupoMuscular> listaGrupoMuscular = new ArrayList<MDLGrupoMuscular>();
	private int pk_int_codigo_grupo_muscular;
	private DALGrupoMuscular dalGrupoMuscular;
	
	@Override
	public void onCreate(Bundle icicle){
		super.onCreate(icicle);
		
	}
}
