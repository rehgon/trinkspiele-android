package com.google.code.siebenSaeuft;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.code.trinkspiele.R;
import com.google.code.trinkspiele.Trinkspiele;

public class siebensauft extends Activity {
	private static final int ID_BEENDEN = 0;
	private static final int ID_HELP = 1;
	Button wuerfeln;
	SiebenSauftLogik neuesSpiel; 
	ImageView wuerfel1, wuerfel2;
	TextView textfenster;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.siebensaeuft);
        neuesSpiel = new SiebenSauftLogik(getApplicationContext());
        textfenster=(TextView)findViewById(R.id.Textausgabe);
        wuerfel1=(ImageView)findViewById(R.id.ImageView01);
        wuerfel2=(ImageView)findViewById(R.id.ImageView02);
        wuerfeln=(Button)findViewById(R.id.Button01);
        wuerfeln.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String ausgabe = neuesSpiel.chat();
				neuesSpiel.paint(neuesSpiel, wuerfel1, 1);
				neuesSpiel.paint(neuesSpiel, wuerfel2, 1);
				if (ausgabe == getString(R.string.siebensaeuft_spielende)) {
					textfenster.setText(ausgabe);
					wuerfeln.setText(getString(R.string.siebensaeuft_neustart));
					neuesSpiel = new SiebenSauftLogik(getApplicationContext());
				}
				else{
					wuerfeln.setText(getString(R.string.siebensaeuft_wuerfeln));
					neuesSpiel.paint(neuesSpiel, wuerfel1, neuesSpiel.getWuerfelZahl(0));
					neuesSpiel.paint(neuesSpiel, wuerfel2, neuesSpiel.getWuerfelZahl(1));
					textfenster.setText(ausgabe);
				}
			}
		});
    }
    
    @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if ((keyCode == KeyEvent.KEYCODE_BACK)) {
	    	neuesSpiel.wirklichBeendenDialog(this);
	    	return true;
	    }
	    return false;
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	menu.add(Menu.NONE, ID_HELP, Menu.NONE, getString(R.string.hilfe)).setIcon(
			R.drawable.ic_menu_info);
		menu.add(Menu.NONE, ID_BEENDEN, Menu.NONE, getString(R.string.zum_hauptmenue)).setIcon(
			R.drawable.ic_menu_close);
		return (super.onCreateOptionsMenu(menu));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	return (applyMenuChoice(item) || super.onOptionsItemSelected(item));
    }

    private boolean applyMenuChoice(MenuItem item) {
    	switch (item.getItemId()) {
	case ID_BEENDEN:
		startActivity(new Intent(this, Trinkspiele.class));
		return true;
	case ID_HELP:
		neuesSpiel.createHelperDialog(this, neuesSpiel.help());

	}
	return false;
}
}
