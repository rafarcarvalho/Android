package br.com.rrc.SQLiteDAL;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.rrc.model.MDLSerie;
import br.com.rrc.model.Util;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DALSerie extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "DiarioTreino";
	private static final String TABLE = "tb_serie";
	private static final String KEY_PK_INT_CODIGO_SERIE = "pk_int_codigo_serie";
	private static final String KEY_FK_INT_CODIGO_GRUPO_DIVISAO = "fk_int_codigo_grupo_divisao";
	private static final String KEY_FK_INT_CODIGO_EXERCICIO = "fk_int_codigo_exercicio";
	private static final String KEY_FLT_CARGA = "flt_carga";
	private static final String KEY_INT_REPETICOES = "int_repeticoes";
	private static final String KEY_BIT_COMPLETOU = "bit_completou";
	private static final String KEY_DTT_INICIO = "dtt_inicio";
	private static final String KEY_DTT_FIM = "dtt_fim";

	static final String[] COLUMNS = {
		KEY_PK_INT_CODIGO_SERIE,
		KEY_FK_INT_CODIGO_GRUPO_DIVISAO,
		KEY_FK_INT_CODIGO_EXERCICIO,
		KEY_FLT_CARGA,
		KEY_INT_REPETICOES,
		KEY_BIT_COMPLETOU,
		KEY_DTT_INICIO,
		KEY_DTT_FIM
	};

	public DALSerie(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {


	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		db.execSQL("DROP TABLE IF EXISTS " + TABLE);

		this.onCreate(db);
	}

	public void Inserir(MDLSerie mdlSerie){

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_FK_INT_CODIGO_GRUPO_DIVISAO, mdlSerie.getFk_Int_Codigo_Grupo_Divisao()); 
		values.put(KEY_FK_INT_CODIGO_EXERCICIO, mdlSerie.getFk_Int_Codigo_Exercicio()); 
		values.put(KEY_FLT_CARGA, mdlSerie.getFlt_Carga());
		values.put(KEY_INT_REPETICOES, mdlSerie.getInt_Repeticoes()); 
		values.put(KEY_BIT_COMPLETOU, mdlSerie.isBit_Completou());
		if (mdlSerie.getDtt_Inicio() != null)
			values.put(KEY_DTT_INICIO, Util.getDate(mdlSerie.getDtt_Inicio()));

		if (mdlSerie.getDtt_Fim() != null)
			values.put(KEY_DTT_FIM, Util.getDate(mdlSerie.getDtt_Fim()));

		db.insert(TABLE, 
				null, 
				values); 

		db.close(); 
	}

	public List<MDLSerie> Consultar(){

		List<MDLSerie> retorno = new ArrayList<MDLSerie>();		
		final String sql = "SELECT * FROM " + TABLE;	
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor c = db.rawQuery(sql, null);

		if(c.moveToFirst()){
			do{                
				retorno.add(prepare(c));
			}while(c.moveToNext());
		}

		db.close();
		return retorno;
	}

	public MDLSerie Consultar(int fk_int_codigo_grupo_divisao, int fk_int_codigo_exercicio){

		//Log.d(String.valueOf(fk_int_codigo_grupo_divisao), String.valueOf(fk_int_codigo_exercicio));
		
		SQLiteDatabase db = this.getReadableDatabase();
		MDLSerie mdlSerie = null;
		Cursor c = db.query(TABLE, // a. table
				COLUMNS, // b. column names
				KEY_FK_INT_CODIGO_GRUPO_DIVISAO + " = ? AND " + KEY_FK_INT_CODIGO_EXERCICIO + " = ?", // c. selections 
				new String[] { String.valueOf(fk_int_codigo_grupo_divisao), String.valueOf(fk_int_codigo_exercicio) }, // d. selections args
				null, // e. group by
				null, // f. having
				null, // g. order by
				null); // h. limit

		if (c.moveToFirst()){

			mdlSerie = prepare(c);
		}
		db.close();
		return mdlSerie;
	}

	public MDLSerie Consultar(int pk_int_codigo_serie){

		SQLiteDatabase db = this.getReadableDatabase();

		Cursor c = db.query(TABLE, // a. table
				COLUMNS, // b. column names
				KEY_PK_INT_CODIGO_SERIE + " = ?", // c. selections 
				new String[] { String.valueOf(pk_int_codigo_serie) }, // d. selections args
				null, // e. group by
				null, // f. having
				null, // g. order by
				null); // h. limit

		if (c != null)
			c.moveToFirst();

		MDLSerie mdlSerie = prepare(c);

		db.close();
		return mdlSerie;
	}

	public int Alterar(MDLSerie mdlSerie) {

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_FK_INT_CODIGO_GRUPO_DIVISAO, mdlSerie.getFk_Int_Codigo_Grupo_Divisao()); 
		values.put(KEY_FK_INT_CODIGO_EXERCICIO, mdlSerie.getFk_Int_Codigo_Exercicio()); 
		values.put(KEY_FLT_CARGA, mdlSerie.getFlt_Carga());
		values.put(KEY_INT_REPETICOES, mdlSerie.getInt_Repeticoes()); 
		values.put(KEY_BIT_COMPLETOU, mdlSerie.isBit_Completou());
		values.put(KEY_DTT_INICIO, Util.getDate(mdlSerie.getDtt_Inicio()));
		values.put(KEY_DTT_FIM, Util.getDate(mdlSerie.getDtt_Fim()));

		int i = db.update(TABLE, //table
				values, // column/value
				KEY_PK_INT_CODIGO_SERIE + " = ?", // selections
				new String[] { String.valueOf(mdlSerie.getPk_Int_Codigo_Serie()) }); //selection args

		db.close();
		return i;
	}

	public void Excluir(MDLSerie mdlSerie) {

		SQLiteDatabase db = this.getWritableDatabase();

		db.delete(TABLE, //table name
				KEY_PK_INT_CODIGO_SERIE + " = ?",  // selections
				new String[] { String.valueOf(mdlSerie.getPk_Int_Codigo_Serie()) }); //selections args

		db.close();
	}

	protected MDLSerie prepare(Cursor c){
		
		Date dtt_inicio = c.getString(6) == null ? null : Util.getDate(c.getString(6));
		Date dtt_fim = c.getString(7) == null ? null : Util.getDate(c.getString(7));
		
		return new MDLSerie(c.getInt(0),
				c.getInt(1),
				c.getInt(2),
				c.getDouble(3),
				c.getInt(4),
				Boolean.parseBoolean(c.getString(5)),
				dtt_inicio,
				dtt_fim);
	}
}
