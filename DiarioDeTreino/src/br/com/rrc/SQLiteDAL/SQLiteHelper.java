package br.com.rrc.SQLiteDAL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {
	// Database Version
	private static final int DATABASE_VERSION = 1;
	// Database Name
	private static final String DATABASE_NAME = "DiarioTreino";
	SQLiteDatabase db;

	public SQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);  
		db = getWritableDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d("SQLiteHelper", "onCreate");
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

		sql = "CREATE TABLE IF NOT EXISTS tb_grupo_muscular " +
				"( " +
				"pk_int_codigo_grupo_muscular INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL " +       
				",vch_nome VARCHAR(50) " +
				"); ";

		db.execSQL(sql);

		sql = "CREATE TABLE IF NOT EXISTS tb_exercicio " +
				"( " +
				"pk_int_codigo_exercicio INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL " +       
				",fk_int_codigo_grupo_muscular INTEGER NOT NULL " +
				",vch_descricao VARCHAR(50) NOT NULL " +       
				",vch_maquina VARCHAR(50) " +
				"); ";

		db.execSQL(sql);


		//INSERTS PADRAO
		int fk_int_codigo_grupo_muscular = 1;
		sql = "INSERT or replace INTO tb_grupo_muscular (vch_nome) VALUES('ABDOMEM')";
		db.execSQL(sql);
		
		String[] exercicios_abdomem = {
				"Máquina",	
				"Reto Abdominal",
				"Inferior",
				"Superior",
				"Oblíquo",
				"Prancha"
		};

		for(int i = 0; i < exercicios_abdomem.length; i++){
			sql = "INSERT or replace INTO tb_exercicio (fk_int_codigo_grupo_muscular, vch_descricao) VALUES(" + fk_int_codigo_grupo_muscular + ", '" + exercicios_abdomem[i] +  "'); ";
			db.execSQL(sql);
		}
		
		
		fk_int_codigo_grupo_muscular = 2;
		sql = "INSERT or replace INTO tb_grupo_muscular (vch_nome) VALUES('OMBROS/TRAPÉZIO')";
		db.execSQL(sql);
		
		String[] exercicios_ombros = {
				"Elevação Lateral",	
				"Elevação Frontal",
				"Desenvolvimento",
				"Remada Alta",
				"Encolhimento",
				"Crucifixo Inverso",
				"Peck Deck Reversed",
				"Desemvolvimento Máquina"
		};

		for(int i = 0; i < exercicios_ombros.length; i++){
			sql = "INSERT or replace INTO tb_exercicio (fk_int_codigo_grupo_muscular, vch_descricao) VALUES(" + fk_int_codigo_grupo_muscular + ", '" + exercicios_ombros[i] +  "'); ";
			db.execSQL(sql);
		}
		
		fk_int_codigo_grupo_muscular = 3;
		sql = "INSERT or replace INTO tb_grupo_muscular (vch_nome) VALUES('DORSAL')";
		db.execSQL(sql);
		
		String[] exercicios_dorsal = {
				"Remada",
				"Remada Aberta",
				"Remada Fechada",
				"Pull Down Barra",
				"Pull Down Corda",
				"Barra Fixa",
				"Graviton",
				"Extensão Lombar",
				"Puxada Supinada",
				"Remada Curvada"
		};

		for(int i = 0; i < exercicios_dorsal.length; i++){
			sql = "INSERT or replace INTO tb_exercicio (fk_int_codigo_grupo_muscular, vch_descricao) VALUES(" + fk_int_codigo_grupo_muscular + ", '" + exercicios_dorsal[i] +  "'); ";
			db.execSQL(sql);
		}
		
		fk_int_codigo_grupo_muscular = 4;
		sql = "INSERT or replace INTO tb_grupo_muscular (vch_nome) VALUES('PEITORAL')";
		db.execSQL(sql);
		
		String[] exercicios_peitoral = {
				"Supino Máquina",	
				"Supino Reto",
				"Supino Inclinado",
				"Supino Declinado",
				"Peck Deck",
				"Crucifixo Inclinado",
				"Crucifixo",
				"Cross Over",
				"Pull Over",
				"Supino Halter",
				"Supino Halter Inclinado"
		};

		for(int i = 0; i < exercicios_peitoral.length; i++){
			sql = "INSERT or replace INTO tb_exercicio (fk_int_codigo_grupo_muscular, vch_descricao) VALUES(" + fk_int_codigo_grupo_muscular + ", '" + exercicios_peitoral[i] +  "'); ";
			db.execSQL(sql);
		}
		
		
		fk_int_codigo_grupo_muscular = 5;
		sql = "INSERT or replace INTO tb_grupo_muscular (vch_nome) VALUES('MEMBROS INFERIORES')";
		db.execSQL(sql);
		
		String[] exercicios_membros_inf = {
				"Extensora",
				"Extensora Unilateral",
				"Flexora Vertical",
				"Leg Press 45°",
				"Agachamento",
				"Afundo",
				"Avanço",
				"Stiff Halter",
				"Stiff Barra",
				"Adução",
				"Abdução",
				"Gemeos no Leg",
				"Gemeos Sentado",
				"Gemeos Smith",
				"Agachamento Smith"
		};

		for(int i = 0; i < exercicios_membros_inf.length; i++){
			sql = "INSERT or replace INTO tb_exercicio (fk_int_codigo_grupo_muscular, vch_descricao) VALUES(" + fk_int_codigo_grupo_muscular + ", '" + exercicios_membros_inf[i] +  "'); ";
			db.execSQL(sql);
		}
		
		fk_int_codigo_grupo_muscular = 6;
		sql = "INSERT or replace INTO tb_grupo_muscular (vch_nome) VALUES('BÍCEPS')";
		db.execSQL(sql);
		
		String[] exercicios_biceps = {
				"Rosca Simultânea",
				"Rosca Simultânea 45°",
				"Rosca Direta",
				"Rosca Scott",
				"Rosca Concentrada",
				"Rosca Martelo",
				"Rosca Cruz Unilateral",
				"Rosca Inversa"
		};

		for(int i = 0; i < exercicios_biceps.length; i++){
			sql = "INSERT or replace INTO tb_exercicio (fk_int_codigo_grupo_muscular, vch_descricao) VALUES(" + fk_int_codigo_grupo_muscular + ", '" + exercicios_biceps[i] +  "'); ";
			db.execSQL(sql);
		}
		
		fk_int_codigo_grupo_muscular = 7;
		sql = "INSERT or replace INTO tb_grupo_muscular (vch_nome) VALUES('TRÍCEPS')";
		db.execSQL(sql);

		String[] exercicios_triceps = {
				"Pulley",
				"Pulley Supinado",
				"Pulley Supinado Unilateral",
				"Francês",
				"Testa",
				"Coice",
				"Banco",
				"Paralela",
				"Extensão Polia Alta"
		};

		for(int i = 0; i < exercicios_triceps.length; i++){
			sql = "INSERT or replace INTO tb_exercicio (fk_int_codigo_grupo_muscular, vch_descricao) VALUES(" + fk_int_codigo_grupo_muscular + ", '" + exercicios_triceps[i] +  "'); ";
			db.execSQL(sql);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		db.execSQL("DROP TABLE IF EXISTS tb_treino");
		db.execSQL("DROP TABLE IF EXISTS tb_divisao");

		this.onCreate(db);
	}
}
