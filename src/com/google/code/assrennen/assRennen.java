package com.google.code.assrennen;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.code.trinkspiele.R;
import com.google.code.trinkspiele.Trinkspiele;

public class assRennen extends Activity {
	private static final int ID_BEENDEN = 0;
	private static final int ID_HELP = 1;
	int counter = 0;
	Button naechsteKarte;
	AssRennenLogik neuesSpiel;
	ImageView karte;
	TextView textFenster;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assrennen);
        neuesSpiel = new AssRennenLogik();
        textFenster = (TextView)findViewById(R.id.TextAusgabe);
        karte = (ImageView)findViewById(R.id.ImageView01);
        naechsteKarte = (Button)findViewById(R.id.Button01);
        
        naechsteKarte.setOnClickListener(new OnClickListener() {
			
        	
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(counter==1)
				{	
					counter = 0;
					restarting();
				}
				else
				{
					String ausgabe = neuesSpiel.assRennenkarteZiehen();
					neuesSpiel.paint(neuesSpiel, karte, neuesSpiel.getAktuellesKartenSymbol(), neuesSpiel.getAktuellerKartenWert());
					textFenster.setText(ausgabe);
				}
				
				if(neuesSpiel.getAceCounter()==4)
				{
					naechsteKarte.setText("Neustart");
					counter = 1;
					neuesSpiel = new AssRennenLogik();
				}
				else if(counter==1)
				{	
					counter = 0;
					restarting();
				}
				else
					naechsteKarte.setText("nächste Karte");
			}
		});
        
        }
       
        
      
        private void restarting()
        {
			naechsteKarte.setText("neue Karte");
			karte.setImageResource(R.drawable.deckblatt);
			textFenster.setText("restart");
        }

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			menu.add(Menu.NONE, ID_HELP, Menu.NONE, "Hilfe").setIcon(R.drawable.info);
			menu.add(Menu.NONE, ID_BEENDEN, Menu.NONE, "Zum Hauptmenü").setIcon(R.drawable.close);
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
        
    