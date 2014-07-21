package br.com.rrc.SQLiteDAL;

import java.util.ArrayList;
import java.util.List;

import br.com.rrc.model.MDLGrupoMuscular;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DALGrupoMuscular extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "DiarioTreino";
	private static final String TABLE = "tb_grupo_muscular";
	private static final String KEY_PK_INT_CODIGO_GRUPO_MUSCULAR = "pk_int_codigo_grupo_muscular";
	private static final String KEY_VCH_NOME = "vch_nome";

	static final String[] COLUMNS = {
		KEY_PK_INT_CODIGO_GRUPO_MUSCULAR,
		KEY_VCH_NOME
	};

	public DALGrupoMuscular(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		db.execSQL("DROP TABLE IF EXISTS " + TABLE);

		this.onCreate(db);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		
	}

	public void Inserir(MDLGrupoMuscular mdlGrupoMuscular){

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_VCH_NOME, mdlGrupoMuscular.getVch_Nome());		

		db.insert(TABLE, 
				null, 
				values); 

		db.close(); 
	}

	public List<MDLGrupoMuscular> Consultar(){

		List<MDLGrupoMuscular> retorno = new ArrayList<MDLGrupoMuscular>();		
		final String sql = "SELECT * FROM " + TABLE;	
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor c = db.rawQuery(sql, null);

		if(c.moveToFirst()){
			do{                
				retorno.add(new MDLGrupoMuscular(c.getInt(0),
						c.getString(1)));
			}while(c.moveToNext());
		}

		db.close();
		return retorno;
	}

	public MDLGrupoMuscular Consultar(int pk_int_codigo_grupo_muscular){

		SQLiteDatabase db = this.getReadableDatabase();

		Cursor c = db.query(TABLE, // a. table
				COLUMNS, // b. column names
				KEY_PK_INT_CODIGO_GRUPO_MUSCULAR + " = ?", // c. selections 
				new String[] { String.valueOf(pk_int_codigo_grupo_muscular) }, // d. selections args
				null, // e. group by
				null, // f. having
				null, // g. order by
				null); // h. limit

		if (c != null)
			c.moveToFirst();

		MDLGrupoMuscular mdlGrupoMuscular = new MDLGrupoMuscular(c.getInt(0), c.getString(1));

		db.close();
		return mdlGrupoMuscular;
	}

	public int Alterar(MDLGrupoMuscular mdlGrupoMuscular) {

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_VCH_NOME, mdlGrupoMuscular.getVch_Nome());

		int i = db.update(TABLE, //table
				values, // column/value
				KEY_PK_INT_CODIGO_GRUPO_MUSCULAR + " = ?", // selections
				new String[] { String.valueOf(mdlGrupoMuscular.getPk_Int_Codigo_Grupo_Muscular()) }); //selection args

		db.close();
		return i;
	}

	public void Excluir(MDLGrupoMuscular mdlGrupoMuscular) {

		SQLiteDatabase db = this.getWritableDatabase();

		db.delete(TABLE, //table name
				KEY_PK_INT_CODIGO_GRUPO_MUSCULAR + " = ?",  // selections
				new String[] { String.valueOf(mdlGrupoMuscular.getPk_Int_Codigo_Grupo_Muscular()) }); //selections args

		db.close();
	}

	public void inserirObjetosPadrao(){

		String[] grupos = {"ABDOMEM",
				"OMBROS/TRAPÉZIO",
				"DORSAL", 
				"PEITORAL", 
				"MEMBROS INFERIORES", 
				"BÍCEPS", 
		"TRÍCEPS"};
		
		SQLiteDatabase dbr = this.getReadableDatabase();
		SQLiteDatabase dbw = this.getWritableDatabase();
		ContentValues value = new ContentValues();
		Cursor c;

		for(int i = 0; i < grupos.length; i++){

			c = dbr.query(TABLE, // a. table
					COLUMNS, // b. column names
					KEY_VCH_NOME + " = ?", // c. selections 
					new String[] { String.valueOf(grupos[i]) }, // d. selections args
					null, // e. group by
					null, // f. having
					null, // g. order by
					null); // h. limit

			if (c != null){
				value.put(KEY_VCH_NOME, grupos[i]);
				dbw.insert(TABLE, null, value);
				value = new ContentValues();
			}
		}
		dbr.close();
		dbw.close(); 
	}
}
