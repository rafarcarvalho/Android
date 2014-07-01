package br.com.rrc.diariodetreino;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class NovoTreinoActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_treino);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	menu.add(0, 0, 0, "Voltar");    	
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {      
        int id = item.getItemId();
        if (id == 0) {
        	startActivity(new Intent(this, MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

}
