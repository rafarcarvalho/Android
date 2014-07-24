package br.com.rrc.SQLiteDAL;

import java.util.ArrayList;
import java.util.List;

import br.com.rrc.model.MDLGrupoDivisao;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DALGrupoDivisao extends SQLiteOpenHelper {

	public DALGrupoDivisao(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "DiarioTreino";
	private static final String TABLE = "tb_grupo_divisao";
	private static final String KEY_PK_INT_CODIGO_GRUPO_DIVISAO = "pk_int_codigo_grupo_divisao";
	private static final String KEY_FK_INT_CODIGO_DIVISAO = "fk_int_codigo_divisao";
	private static final String KEY_FK_INT_CODIGO_GRUPO_MUSCULAR = "fk_int_codigo_grupo_muscular";

	static final String[] COLUMNS = {
		KEY_PK_INT_CODIGO_GRUPO_DIVISAO,
		KEY_FK_INT_CODIGO_DIVISAO,
		KEY_FK_INT_CODIGO_GRUPO_MUSCULAR
	};

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	public void Inserir(MDLGrupoDivisao mdlGrupoDivisao){

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_FK_INT_CODIGO_DIVISAO, mdlGrupoDivisao.getFk_Int_Codigo_Divisao());
		values.put(KEY_FK_INT_CODIGO_GRUPO_MUSCULAR, mdlGrupoDivisao.getFk_Int_Codigo_Grupo_Muscular());

		db.insert(TABLE, 
				null, 
				values); 

		db.close(); 
	}

	public List<MDLGrupoDivisao> Consultar(int fk_int_codigo_divisao){
		List<MDLGrupoDivisao> retorno = new ArrayList<MDLGrupoDivisao>();
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor c = db.query(TABLE, // a. table
				COLUMNS, // b. column names
				KEY_FK_INT_CODIGO_DIVISAO + " = ?", // c. selections 
				new String[] { String.valueOf(fk_int_codigo_divisao) }, // d. selections args
				null, // e. group by
				null, // f. having
				null, // g. order by
				null); // h. limit

		if(c.moveToFirst()){
			do{                
				retorno.add(new MDLGrupoDivisao(c.getInt(0), c.getInt(1), c.getInt(2)));
			}while(c.moveToNext());
		}

		db.close();
		return retorno;
	}

	public MDLGrupoDivisao Consultar(int fk_int_codigo_divisao, int fk_int_codigo_grupo_muscular){

		SQLiteDatabase db = this.getReadableDatabase();
		MDLGrupoDivisao mdlGrupoDivisao = null;
		Cursor c = 
				db.query(TABLE, // a. table
						COLUMNS, // b. column names
						KEY_FK_INT_CODIGO_DIVISAO + " = ? AND " + KEY_FK_INT_CODIGO_GRUPO_MUSCULAR + " = ?", // c. selections 
						new String[] { String.valueOf(fk_int_codigo_divisao), String.valueOf(fk_int_codigo_grupo_muscular) }, // d. selections args
						null, // e. group by
						null, // f. having
						null, // g. order by
						null); // h. limit

		if (c.moveToFirst()){

			mdlGrupoDivisao = new MDLGrupoDivisao(c.getInt(0), c.getInt(1), c.getInt(2));
		}
		db.close();
		return mdlGrupoDivisao;
	}

	public void Excluir(MDLGrupoDivisao mdlGrupoDivisao) {

		SQLiteDatabase db = this.getWritableDatabase();

		db.delete(TABLE, //table name
				KEY_PK_INT_CODIGO_GRUPO_DIVISAO + " = ?",  // selections
				new String[] { String.valueOf(mdlGrupoDivisao.getPk_Int_Codigo_Grupo_Divisao()) }); //selections args

		db.close();
	}

}
