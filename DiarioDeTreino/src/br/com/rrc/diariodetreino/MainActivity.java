package br.com.rrc.diariodetreino;

import android.support.v7.app.ActionBarActivity;
//import android.app.AlertDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {

	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);  

		/* try {
        	 db = openOrCreateDatabase("DiarioTreino.db", MODE_PRIVATE, null);
        	 new SQLiteHelper().CriarTabelas(db);
         }
         catch(SQLiteException s) {
	    	 AlertDialog.Builder alert = new AlertDialog.Builder(this);
	    	 alert.setMessage(s.getMessage());
	    	 alert.show();
	    }*/

		/*try {
			boolean help = new SQLiteHelper().AbreOuCriaBase();
		}
		catch(SQLiteException s) {
			AlertDialog.Builder alert = new AlertDialog.Builder(this);
			alert.setMessage(s.getMessage());
			alert.show();
		}*/


	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == R.id.novo_treino) {        	        	        	
			startActivity(new Intent(this, NovoTreinoActivity.class));        	
		}
		return super.onOptionsItemSelected(item);
	}

}
