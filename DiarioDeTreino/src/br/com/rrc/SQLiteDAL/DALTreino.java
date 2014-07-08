package br.com.rrc.SQLiteDAL;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import br.com.rrc.model.MDLTreino;
import br.com.rrc.model.Util;

public class DALTreino extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "DiarioTreino";
	private static final String TABLE = "tb_treino";
	private static final String KEY_PK_INT_CODIGO_TREINO = "pk_int_codigo_treino";
	private static final String KEY_VCH_NOME_ATLETA = "vch_nome_atleta";
	private static final String KEY_DTT_CRIACAO = "dtt_criacao";
	private static final String KEY_VCH_NOME_TREINO = "vch_nome_treino";
	private static final String KEY_VCH_PROJETO = "vch_projeto";
	private static final String KEY_INT_MINUTOS_DURACAO = "int_minutos_duracao";
	private static final String KEY_INT_SEGUNDOS_PAUSA_SERIES = "int_segundos_pausa_series";

	static final String[] COLUMNS = {
		KEY_PK_INT_CODIGO_TREINO,
		KEY_VCH_NOME_ATLETA,
		KEY_DTT_CRIACAO,
		KEY_VCH_NOME_TREINO,
		KEY_VCH_PROJETO,
		KEY_INT_MINUTOS_DURACAO,
		KEY_INT_SEGUNDOS_PAUSA_SERIES
	};

	public DALTreino(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);  
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		String sql = "CREATE TABLE IF NOT EXISTS tb_treino " +
				"( " +
				"pk_int_codigo_treino INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL " +      
				",vch_nome_atleta VARCHAR(50) NOT NULL " +
				",dtt_criacao DATETIME NOT NULL " +
				",vch_nome_treino VARCHAR(50) " +
				",vch_projeto VARCHAR(50) " +
				",int_minutos_duracao INTEGER " +
				",int_segundos_pausa_series INTEGER " +
				"); ";


		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		db.execSQL("DROP TABLE IF EXISTS " + TABLE);

		this.onCreate(db);
	}
	
	public void Inserir(MDLTreino mdlTreino){

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_VCH_NOME_ATLETA, mdlTreino.getVch_Nome_Atleta()); 
		values.put(KEY_DTT_CRIACAO, Util.getDateTime());
		//Log.d( Util.getDateTime(),KEY_DTT_CRIACAO);
		values.put(KEY_VCH_NOME_TREINO, mdlTreino.getVch_Nome_Treino()); 
		values.put(KEY_VCH_PROJETO, mdlTreino.getVch_Projeto());
		values.put(KEY_INT_MINUTOS_DURACAO, mdlTreino.getInt_Minutos_Duracao());
		values.put(KEY_INT_SEGUNDOS_PAUSA_SERIES, mdlTreino.getInt_segundos_pausa_series());

		db.insert(TABLE, 
				null, 
				values); 

		db.close(); 
	}

	public List<MDLTreino> Consultar(){

		List<MDLTreino> retorno = new ArrayList<MDLTreino>();		
		final String sql = "SELECT * FROM tb_treino";	
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor c = db.rawQuery(sql, null);

		if(c.moveToFirst()){
			do{                
				retorno.add(new MDLTreino(c.getInt(0), 
						c.getString(1), 
						Util.getDate(c.getString(2)), 
						c.getString(3), 
						c.getString(4), 
						c.getInt(5), 
						c.getInt(6)));
				//Log.d("consulta dtt_criacao",c.getString(2));
			}while(c.moveToNext());
		}

		db.close();
		return retorno;
	}

	public MDLTreino Consultar(int pk_int_codigo_treino){

		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = 
				db.query(TABLE, // a. table
						COLUMNS, // b. column names
						" pk_int_codigo_treino = ?", // c. selections 
						new String[] { String.valueOf(pk_int_codigo_treino) }, // d. selections args
						null, // e. group by
						null, // f. having
						null, // g. order by
						null); // h. limit

		if (cursor != null)
			cursor.moveToFirst();

		MDLTreino mdlTreino = new MDLTreino(cursor.getInt(0), 
				cursor.getString(1), 
				new Date(cursor.getLong(2)), 
				cursor.getString(2), 
				cursor.getString(3), 
				cursor.getInt(4), 
				cursor.getInt(5));
		
		db.close();
		return mdlTreino;
	}

	public int Alterar(MDLTreino mdlTreino) {

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_VCH_NOME_ATLETA, mdlTreino.getVch_Nome_Atleta()); 
		values.put(KEY_DTT_CRIACAO, new Date().toString());
		values.put(KEY_VCH_NOME_TREINO, mdlTreino.getVch_Nome_Treino()); 
		values.put(KEY_VCH_PROJETO, mdlTreino.getVch_Projeto());
		values.put(KEY_INT_MINUTOS_DURACAO, mdlTreino.getInt_Minutos_Duracao());
		values.put(KEY_INT_SEGUNDOS_PAUSA_SERIES, mdlTreino.getInt_segundos_pausa_series());

		int i = db.update(TABLE, //table
				values, // column/value
				KEY_PK_INT_CODIGO_TREINO + " = ?", // selections
				new String[] { String.valueOf(mdlTreino.getPk_Int_Codigo_Treino()) }); //selection args

		db.close();
		return i;
	}
	
	public void Excluir(MDLTreino mdlTreino) {
		 
        SQLiteDatabase db = this.getWritableDatabase();
 
        db.delete(TABLE, //table name
        		KEY_PK_INT_CODIGO_TREINO + " = ?",  // selections
                new String[] { String.valueOf(mdlTreino.getPk_Int_Codigo_Treino()) }); //selections args
 
        db.close();
    }
}
