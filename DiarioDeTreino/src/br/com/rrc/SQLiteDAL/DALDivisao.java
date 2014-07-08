package br.com.rrc.SQLiteDAL;

import java.util.ArrayList;
import java.util.List;

import br.com.rrc.model.MDLDivisao;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DALDivisao extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "DiarioTreino";
	private static final String TABLE = "tb_divisao";
	private static final String KEY_PK_INT_CODIGO_DIVISAO = "pk_int_codigo_divisao";
	private static final String KEY_FK_INT_CODIGO_TREINO = "fk_int_codigo_treino";
	private static final String KEY_CHR_DIVISAO = "chr_divisao";
	
	static final String[] COLUMNS = {
		KEY_PK_INT_CODIGO_DIVISAO,
		KEY_FK_INT_CODIGO_TREINO,
		KEY_CHR_DIVISAO
	};
	
	public DALDivisao(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		String sql = "CREATE TABLE IF NOT EXISTS tb_divisao " +
					"( " +
					       "pk_int_codigo_divisao INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL " +       
					       ",fk_int_codigo_treino INTEGER NOT NULL " +
					       ",chr_divisao CHAR(1) " +
					"); ";


		db.execSQL(sql);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		db.execSQL("DROP TABLE IF EXISTS " + TABLE);

		this.onCreate(db);
	}
	
	public void Inserir(MDLDivisao mdlDivisao){

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_FK_INT_CODIGO_TREINO, mdlDivisao.getFk_Int_Codigo_Treino()); 
		values.put(KEY_CHR_DIVISAO, mdlDivisao.getChr_Divisao() + "");		

		db.insert(TABLE, 
				null, 
				values); 

		db.close(); 
	}
	
	public List<MDLDivisao> Consultar(){

		List<MDLDivisao> retorno = new ArrayList<MDLDivisao>();		
		final String sql = "SELECT * FROM " + TABLE;	
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor c = db.rawQuery(sql, null);

		if(c.moveToFirst()){
			do{                
				retorno.add(new MDLDivisao(c.getInt(0),
											c.getInt(1),
											c.getString(2).charAt(0)));
			}while(c.moveToNext());
		}

		db.close();
		return retorno;
	}
	
	public MDLDivisao Consultar(int pk_int_codigo_divisao){

		SQLiteDatabase db = this.getReadableDatabase();

		Cursor c = db.query(TABLE, // a. table
						COLUMNS, // b. column names
						KEY_FK_INT_CODIGO_TREINO + " = ?", // c. selections 
						new String[] { String.valueOf(pk_int_codigo_divisao) }, // d. selections args
						null, // e. group by
						null, // f. having
						null, // g. order by
						null); // h. limit

		if (c != null)
			c.moveToFirst();

		MDLDivisao mdlDivisao =(new MDLDivisao(c.getInt(0),
				c.getInt(1),
				c.getString(2).charAt(0)));
		
		db.close();
		return mdlDivisao;
	}
	
	public int Alterar(MDLDivisao mdlDivisao) {

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_FK_INT_CODIGO_TREINO, mdlDivisao.getFk_Int_Codigo_Treino()); 
		values.put(KEY_CHR_DIVISAO, mdlDivisao.getChr_Divisao() + "");

		int i = db.update(TABLE, //table
				values, // column/value
				KEY_FK_INT_CODIGO_TREINO + " = ?", // selections
				new String[] { String.valueOf(mdlDivisao.getPk_Int_Codigo_Divisao()) }); //selection args

		db.close();
		return i;
	}
	
	public void Excluir(MDLDivisao mdlDivisao) {
		 
        SQLiteDatabase db = this.getWritableDatabase();
 
        db.delete(TABLE, //table name
        		KEY_FK_INT_CODIGO_TREINO + " = ?",  // selections
                new String[] { String.valueOf(mdlDivisao.getPk_Int_Codigo_Divisao()) }); //selections args
 
        db.close();
    }
}
