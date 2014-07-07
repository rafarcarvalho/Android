package br.com.rrc.SQLiteDAL;

import br.com.rrc.model.MDLSerie;
import br.com.rrc.model.Util;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DALSerie extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "DiarioTreino";
	private static final String TABLE = "tb_serie";
	private static final String KEY_PK_INT_CODIGO_SERIE = "pk_int_codigo_serie";
	private static final String KEY_FK_INT_CODIGO_EXERCICIO_GRUPO_DIVISAO = "fk_int_codigo_exercicio_grupo_divisao";
	private static final String KEY_FLT_CARGA = "flt_carga";
	private static final String KEY_INT_REPETICOES = "int_repeticoes";
	private static final String KEY_BIT_COMPLETOU = "bit_completou";
	private static final String KEY_DTT_INICIO = "dtt_inicio";
	private static final String KEY_DTT_FIM = "dtt_fim";
	
	public DALSerie(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		String sql = "CREATE TABLE IF NOT EXISTS tb_serie " +
				"( " +
						"pk_int_codigo_serie INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL " +
						",fk_int_codigo_exercicio_grupo_divisao INTEGER NOT NULL " +
						",flt_carga DOUBLE NOT NULL " +
						",int_repeticoes INTEGER " +
						",bit_completou BIT " +
						",dtt_inicio DATETIME " +
						",dtt_fim DATETIME " +
				"); ";


		db.execSQL(sql);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		db.execSQL("DROP TABLE IF EXISTS " + TABLE);

		this.onCreate(db);
	}
	
	public void Inserir(MDLSerie mdlSerie){

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_FK_INT_CODIGO_EXERCICIO_GRUPO_DIVISAO, mdlSerie.getFk_Int_Codigo_Exercicio_Grupo_Divisao()); 
		values.put(KEY_FLT_CARGA, mdlSerie.getFlt_Carga());
		values.put(KEY_INT_REPETICOES, mdlSerie.getInt_Repeticoes()); 
		values.put(KEY_BIT_COMPLETOU, mdlSerie.isBit_Completou());
		values.put(KEY_DTT_INICIO, Util.getDate(mdlSerie.getDtt_Inicio()));
		values.put(KEY_DTT_FIM, Util.getDate(mdlSerie.getDtt_Fim()));

		db.insert(TABLE, 
				null, 
				values); 

		db.close(); 
	}
}
