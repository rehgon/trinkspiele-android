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
					repaintImage();
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
       
        private void repaintImage()
        {
        	if(neuesSpiel.kartenHalter[0].contains("Herz Ass"))
        		karte.setImageResource(R.drawable.herzass);
        	else if(neuesSpiel.kartenHalter[0].contains("Herz König"))
        		karte.setImageResource(R.drawable.herz_koenig);
        	else if(neuesSpiel.kartenHalter[0].contains("Herz Dame"))
        		karte.setImageResource(R.drawable.herz_dame);
        	else if(neuesSpiel.kartenHalter[0].contains("Herz Bube"))
        		karte.setImageResource(R.drawable.herz_bube);
        	else if(neuesSpiel.kartenHalter[0].contains("Herz 10"))
        		karte.setImageResource(R.drawable.herz10);
        	else if(neuesSpiel.kartenHalter[0].contains("Herz 9"))
        		karte.setImageResource(R.drawable.herz9);
        	else if(neuesSpiel.kartenHalter[0].contains("Herz 8"))
        		karte.setImageResource(R.drawable.herz8c);
        	else if(neuesSpiel.kartenHalter[0].contains("Herz 7"))
        		karte.setImageResource(R.drawable.herz7);
        	else if(neuesSpiel.kartenHalter[0].contains("Herz 6"))
        		karte.setImageResource(R.drawable.herz6);
        	else if(neuesSpiel.kartenHalter[0].contains("Herz 5"))
        		karte.setImageResource(R.drawable.herz5);
        	else if(neuesSpiel.kartenHalter[0].contains("Herz 4"))
        		karte.setImageResource(R.drawable.herz4);
        	else if(neuesSpiel.kartenHalter[0].contains("Herz 3"))
        		karte.setImageResource(R.drawable.herz3);
        	else if(neuesSpiel.kartenHalter[0].contains("Herz 2"))
        		karte.setImageResource(R.drawable.herz2);
        	else if (neuesSpiel.kartenHalter[0].contains("Pik Ass"))
        		karte.setImageResource(R.drawable.pikass);
        	else if (neuesSpiel.kartenHalter[0].contains("Pik 2"))
        		karte.setImageResource(R.drawable.pik2);
        	else if(neuesSpiel.kartenHalter[0].contains("Pik 3"))
        		karte.setImageResource(R.drawable.pik3);
        	else if(neuesSpiel.kartenHalter[0].contains("Pik 4"))
        		karte.setImageResource(R.drawable.pik4);
        	else if(neuesSpiel.kartenHalter[0].contains("Pik 5"))
        		karte.setImageResource(R.drawable.pik5);
        	else if(neuesSpiel.kartenHalter[0].contains("Pik 6"))
        		karte.setImageResource(R.drawable.pik6);
        	else if(neuesSpiel.kartenHalter[0].contains("Pik 7"))
        		karte.setImageResource(R.drawable.pik7);
        	else if(neuesSpiel.kartenHalter[0].contains("Pik 8"))
        		karte.setImageResource(R.drawable.pik8);
        	else if(neuesSpiel.kartenHalter[0].contains("Pik 9"))
        		karte.setImageResource(R.drawable.pik9);
        	else if(neuesSpiel.kartenHalter[0].contains("Pik 10"))
        		karte.setImageResource(R.drawable.pik10);
        	else if(neuesSpiel.kartenHalter[0].contains("Pik König"))
        		karte.setImageResource(R.drawable.pik_koenig);
        	else if(neuesSpiel.kartenHalter[0].contains("Pik Dame"))
        		karte.setImageResource(R.drawable.pik_dame);
        	else if (neuesSpiel.kartenHalter[0].contains("Pik Bube"))
        		karte.setImageResource(R.drawable.pik_bube);
        	else if(neuesSpiel.kartenHalter[0].contains("Kreuz Ass"))
        		karte.setImageResource(R.drawable.kreuzass);
        	else if (neuesSpiel.kartenHalter[0].contains("Kreuz König"))
        		karte.setImageResource(R.drawable.kreuz_koenig);
        	else if (neuesSpiel.kartenHalter[0].contains("Kreuz Dame"))
        		karte.setImageResource(R.drawable.kreuz_dame);
        	else if (neuesSpiel.kartenHalter[0].contains("Kreuz Bube"))
        		karte.setImageResource(R.drawable.kreuz_bube);
        	else if(neuesSpiel.kartenHalter[0].contains("Kreuz 10"))
        		karte.setImageResource(R.drawable.kreuz10);
        	else if(neuesSpiel.kartenHalter[0].contains("Kreuz 9"))
        		karte.setImageResource(R.drawable.kreuz9);
        	else if (neuesSpiel.kartenHalter[0].contains("Kreuz 8"))
        		karte.setImageResource(R.drawable.kreuz8);
        	else if(neuesSpiel.kartenHalter[0].contains("Kreuz 7"))
        		karte.setImageResource(R.drawable.kreuz7);
        	else if(neuesSpiel.kartenHalter[0].contains("Kreuz 6"))
        		karte.setImageResource(R.drawable.kreuz6);
        	else if(neuesSpiel.kartenHalter[0].contains("Kreuz 5"))
        		karte.setImageResource(R.drawable.kreuz5);
        	else if(neuesSpiel.kartenHalter[0].contains("Kreuz 4"))
        		karte.setImageResource(R.drawable.kreuz4);
        	else if(neuesSpiel.kartenHalter[0].contains("Kreuz 3"))
        		karte.setImageResource(R.drawable.kreuz3);
        	else if(neuesSpiel.kartenHalter[0].contains("Kreuz 2"))
        		karte.setImageResource(R.drawable.kreuz2);
        	else if(neuesSpiel.kartenHalter[0].contains("Karo Ass"))
        		karte.setImageResource(R.drawable.karoass);
        	else if(neuesSpiel.kartenHalter[0].contains("Karo König"))
        		karte.setImageResource(R.drawable.karo_koenig);
        	else if(neuesSpiel.kartenHalter[0].contains("Karo Dame"))
        		karte.setImageResource(R.drawable.karo_dame);
        	else if(neuesSpiel.kartenHalter[0].contains("Karo Bube"))
        		karte.setImageResource(R.drawable.karo_bube);
        	else if(neuesSpiel.kartenHalter[0].contains("Karo 10"))
        		karte.setImageResource(R.drawable.karo10);
        	else if(neuesSpiel.kartenHalter[0].contains("Karo 9"))
        		karte.setImageResource(R.drawable.karo9);
        	else if(neuesSpiel.kartenHalter[0].contains("Karo 8"))
        		karte.setImageResource(R.drawable.karo8);
        	else if(neuesSpiel.kartenHalter[0].contains("Karo 7"))
        		karte.setImageResource(R.drawable.karo7);
        	else if(neuesSpiel.kartenHalter[0].contains("Karo 6"))
        		karte.setImageResource(R.drawable.karo6);
        	else if(neuesSpiel.kartenHalter[0].contains("Karo 5"))
        		karte.setImageResource(R.drawable.karo5);
        	else if(neuesSpiel.kartenHalter[0].contains("Karo 4"))
        		karte.setImageResource(R.drawable.karo4);
        	else if(neuesSpiel.kartenHalter[0].contains("Karo 3"))
        		karte.setImageResource(R.drawable.karo3);
        	else if(neuesSpiel.kartenHalter[0].contains("Karo 2"))
            	karte.setImageResource(R.drawable.karo2);
        }
        private void restarting()
        {
			naechsteKarte.setText("neue Karte");
			karte.setImageResource(R.drawable.deckblatt);
			textFenster.setText("restart");
        }

		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			menu.add(Menu.NONE, ID_BEENDEN, Menu.NONE, "Zum Hauptmenü");
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
			}
			return false;
		}
}
        
    