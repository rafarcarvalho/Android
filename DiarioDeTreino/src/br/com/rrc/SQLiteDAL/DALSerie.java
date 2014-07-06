package br.com.rrc.SQLiteDAL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DALSerie extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "DiarioTreino";
	private static final String TABLE = "tb_serie";
	
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
	
	
}
