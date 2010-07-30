package com.google.code.assrennen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
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
		neuesSpiel = new AssRennenLogik(getApplicationContext());
		textFenster = (TextView) findViewById(R.id.TextAusgabe);
		karte = (ImageView) findViewById(R.id.ImageView01);
		naechsteKarte = (Button) findViewById(R.id.Button01);

		naechsteKarte.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (counter == 1) {
					counter = 0;
					restarting();
				} else {
					String ausgabe = neuesSpiel.assRennenkarteZiehen();

					neuesSpiel.paint(neuesSpiel, karte, neuesSpiel
							.getAktuellesKartenSymbol(), neuesSpiel
							.getAktuellerKartenWert());

					neuesSpiel.paint(neuesSpiel, karte, neuesSpiel
							.getAktuellesKartenSymbol(), neuesSpiel
							.getAktuellerKartenWert());

					textFenster.setText(ausgabe);
				}

				if (neuesSpiel.getAceCounter() == 4) {
					naechsteKarte.setText(getString(R.string.assrennen_neustart));
					counter = 1;
					neuesSpiel = new AssRennenLogik(getApplicationContext());
				} else if (counter == 1) {
					counter = 0;
					restarting();
				} else
					naechsteKarte.setText(getString(R.string.assrennen_nächstekarte));
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

	private void restarting() {
		naechsteKarte.setText("neue Karte");
		karte.setImageResource(R.drawable.deckblatt);
		textFenster.setText(getString(R.string.assrennen_neustart));
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
