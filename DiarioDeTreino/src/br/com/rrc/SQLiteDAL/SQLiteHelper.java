package br.com.rrc.SQLiteDAL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 
public class SQLiteHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "DiarioTreino";
 
    public SQLiteHelper(Context context) {
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
		
		sql = "CREATE TABLE IF NOT EXISTS tb_divisao " +
				"( " +
				"pk_int_codigo_divisao INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL " +       
				",fk_int_codigo_treino INTEGER NOT NULL " +
				",chr_divisao CHAR(1) " +
				"); ";

		db.execSQL(sql);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        
        db.execSQL("DROP TABLE IF EXISTS tb_treino");
        db.execSQL("DROP TABLE IF EXISTS tb_divisao");
        
        this.onCreate(db);
    }
 
}
