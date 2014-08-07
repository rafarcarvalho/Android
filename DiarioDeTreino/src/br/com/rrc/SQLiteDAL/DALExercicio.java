package br.com.rrc.SQLiteDAL;

import java.util.ArrayList;
import java.util.List;

import br.com.rrc.model.MDLExercicio;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DALExercicio extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "DiarioTreino";
	private static final String TABLE = "tb_exercicio";
	
	private static final String KEY_PK_INT_CODIGO_EXERCICIO = "pk_int_codigo_exercicio";       
	private static final String KEY_FK_INT_CODIGO_GRUPO_MUSCULAR = "fk_int_codigo_grupo_muscular";
	private static final String KEY_VCH_DESCRICAO = "vch_descricao";       
	private static final String KEY_VCH_MAQUINA = "vch_maquina";
	
	static final String[] COLUMNS = {
		KEY_PK_INT_CODIGO_EXERCICIO,
		KEY_FK_INT_CODIGO_GRUPO_MUSCULAR,
		KEY_VCH_DESCRICAO,
		KEY_VCH_MAQUINA
	};
	
	public DALExercicio(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {

	}
	
	public void Inserir(MDLExercicio mdlExercicio){

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_FK_INT_CODIGO_GRUPO_MUSCULAR, mdlExercicio.getFk_Int_Codigo_Grupo_Muscular());
		values.put(KEY_VCH_DESCRICAO, mdlExercicio.getVch_Descricao());
		values.put(KEY_VCH_MAQUINA, mdlExercicio.getVch_Maquina());

		db.insert(TABLE, 
				null, 
				values); 

		db.close(); 
	}
	
	public List<MDLExercicio> Consultar(int fk_int_codigo_grupo_muscular){
		List<MDLExercicio> retorno = new ArrayList<MDLExercicio>();
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor c = db.query(TABLE, // a. table
				COLUMNS, // b. column names
				KEY_FK_INT_CODIGO_GRUPO_MUSCULAR + " = ?", // c. selections 
				new String[] { String.valueOf(fk_int_codigo_grupo_muscular) }, // d. selections args
				null, // e. group by
				null, // f. having
				null, // g. order by
				null); // h. limit

		if(c.moveToFirst()){
			do{                
				retorno.add(prepare(c));
			}while(c.moveToNext());
		}

		db.close();
		return retorno;
	}
	
	public List<MDLExercicio> Consultar(){

		List<MDLExercicio> retorno = new ArrayList<MDLExercicio>();		
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
	
	public void Excluir(MDLExercicio mdlExercicio) {

		SQLiteDatabase db = this.getWritableDatabase();

		db.delete(TABLE, //table name
				KEY_PK_INT_CODIGO_EXERCICIO + " = ?",  // selections
				new String[] { String.valueOf(mdlExercicio.getPk_Int_Codigo_Exercicio()) }); //selections args

		db.close();
	}
	
	private MDLExercicio prepare(Cursor c){
		return new MDLExercicio(c.getInt(0), c.getInt(1), c.getString(2), c.getString(3)); 
	}
}
