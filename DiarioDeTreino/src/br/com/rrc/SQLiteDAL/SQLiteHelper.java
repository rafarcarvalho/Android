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
				"); " +

					"CREATE TABLE IF NOT EXISTS tb_divisao " +
					"( " +
					"pk_int_codigo_divisao INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL " +       
					",fk_int_codigo_treino INTEGER NOT NULL " +       
					",chr_divisao CHAR(1) " +
					"); " +

					"CREATE TABLE IF NOT EXISTS tb_status_dificuldade " +
					"( " +
					"pk_int_codigo_status_dificuldade INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL " +       
					",vch_descricao VARCHAR(50) NOT NULL " +       
					",vch_imagem VARCHAR(50) " + 
					"); " + 

					"CREATE TABLE IF NOT EXISTS tb_exercicio " +
					"( " +
					"pk_int_codigo_exercicio INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL " +       
					",fk_int_codigo_grupo_muscular INTEGER NOT NULL " +       
					",vch_descricao VARCHAR(50) NOT NULL " +       
					",vch_maquina VARCHAR(50) " +
					"); " +

					"CREATE TABLE IF NOT EXISTS tb_grupo_muscular " +
					"( " +
					"pk_int_codigo_grupo_muscular INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL " +       
					",vch_nome VARCHAR(50) " +
					"); " +

					"CREATE TABLE IF NOT EXISTS tb_registro_execucao " +
					"( " +
					"pk_int_codigo_registro_execucao INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL " +       
					",fk_int_codigo_divisao INTEGER NOT NULL " +       
					",fk_int_codigo_grupo_muscular INTEGER NOT NULL " +       
					",fk_int_codigo_exercicio INTEGER NOT NULL " +       
					",fk_int_codigo_status_dificuldade INTEGER " +       
					",int_ordem INTEGER " +       
					",vch_serie_repeticao VARCHAR(50) " +       
					",flt_carga DOUBLE " +       
					",flt_peso_antes_treino DOUBLE " +       
					",flt_peso_depois_treino DOUBLE " +       
					",bit_aumento_carga BIT " +       
					",bit_reducao_carga BIT " +       
					",vch_nota VARCHAR(500) " +       
					",dtt_execucao DATETIME " +       
					",dtt_inicio_execucao DATETIME " +       
					",dtt_fim_execucao DATETIME " +
					"); ";
 
        
        db.execSQL(sql);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        
        db.execSQL("DROP TABLE IF EXISTS tb_treino");
        db.execSQL("DROP TABLE IF EXISTS tb_divisao");
        db.execSQL("DROP TABLE IF EXISTS tb_status_dificuldade");
        db.execSQL("DROP TABLE IF EXISTS tb_exercicio");
        db.execSQL("DROP TABLE IF EXISTS tb_grupo_muscular");
        db.execSQL("DROP TABLE IF EXISTS tb_registro_execucao");
        
        this.onCreate(db);
    }
 
}
