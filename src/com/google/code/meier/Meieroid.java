package com.google.code.meier;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.code.trinkspiele.R;
import com.google.code.trinkspiele.Spieler;

public class Meieroid extends Activity {
	public static final String TAG = "Meieroid";
	public static final int ID_BEENDEN = 0;

	ImageView image, wuerfelEins, wuerfelZwei;
	boolean wuerfelSichtbar, wahlObLuegenOderNicht;
	Meier meier;
	Context context = this;

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.meier);

		wuerfelSichtbar = true;

		image = (ImageView) findViewById(R.id.meierImage);
		wuerfelEins = (ImageView) findViewById(R.id.wuerfelEinsImage);
		wuerfelZwei = (ImageView) findViewById(R.id.wuerfelZweiImage);
		meier = new Meier(this);
		image.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (wuerfelSichtbar) {
					image.setImageResource(R.drawable.wuerfelbecher_gross);
					wuerfelEins.setVisibility(4);
					wuerfelZwei.setVisibility(4);
					wuerfelSichtbar = false;
				} else {
					image.setImageResource(R.drawable.wuerfelbecher_klein);
					wuerfelEins.setVisibility(0);
					wuerfelZwei.setVisibility(0);
					wuerfelSichtbar = true;
				}
			}
		});

		spielStarten();
		
		Log.v(TAG, "Initialization ended successfully");
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			meier.wirklichBeendenDialog(this);
			return true;
		}
		return false;
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	menu.add(Menu.NONE, ID_BEENDEN, Menu.NONE, getString(R.string.beenden)).setIcon(R.drawable.ic_menu_close);;
    	return (super.onCreateOptionsMenu(menu));
    }
    
    @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return (applyMenuChoice(item) || super.onOptionsItemSelected(item));
    }
	
	private boolean applyMenuChoice(MenuItem item) {
		
		switch (item.getItemId()) {
		case ID_BEENDEN:
			meier.wirklichBeendenDialog(this);
			return true;
		}
		return (false);
	}
	
	public void spielStarten() {
		//Am Anfang wird zufällig ein Spieler gewählt
		Random generator = new Random();
		int randomNumber = generator.nextInt(Spieler.getSpielerNameArrayList().size());
		Spieler.setAktuellerSpielerIndex(randomNumber);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(context.getString(R.string.meier_viel_spass_mit_Meier));
		builder.setMessage(Spieler.getAktuellerSpieler() + " " 
				+ context.getString(R.string.meier_ist_dran_alle_anderen_spieler_haben_wegzuschauen));
		builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				ersterZug();
			}
		});
		builder.show();
	}
	
	public void ersterZug() {
		
		meier.wuerfeln();
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		builder.setTitle(Spieler.getAktuellerSpieler());
		//Wenn Meier gewürfelt wurde
		if (meier.getErgebnis() == 21) {
			meierAuswerten();
		}
		//sonst
		else {
			builder.setMessage(context.getString(R.string.meier_du_hast) + " " + meier.getErgebnis() + " " + context.getString(R.string.meier_gewuerfelt) + ".\n" + context.getString(R.string.meier_moechtest_du_gleich_zu_beginn_luegen) + "?");

			builder.setPositiveButton(getString(R.string.ja), new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					luegenDialog(meier.getErgebnis());
				}
			});
			builder.setNegativeButton(getString(R.string.nein), new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					naechsterSpielerDran();
				}
			});
			
			builder.show();
		}
	}
	
	public void weitererZug() {
		
		meier.wuerfeln();
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		String positiveButtonText;
		
		builder.setTitle(Spieler.getAktuellerSpieler());
		//Wenn Meier gewürfelt wurde
		if (meier.getErgebnis() == 21) {
			meierAuswerten();
		}
		//sonst
		else {
			//Falls aktuelle Zahl tiefer als vorherige
			if (meier.istHoeher(meier.getVorigeZahl(), meier.getErgebnis())) {
				builder.setMessage(context.getString(R.string.meier_du_hast) + " " + meier.getErgebnis() + " " + context.getString(R.string.meier_gewuerfelt) + ".\n" + 
							context.getString(R.string.meier_die_zahl_ist_tiefer_als_die_des_vorigen_spielers_du_musst_luegen) + "!");
				positiveButtonText = getString(R.string.ok);
				builder.setNegativeButton(null, null);
				wahlObLuegenOderNicht = false;
			}
			//Falls aktuelle Zahl höher als vorherige
			else if (meier.istHoeher(meier.getErgebnis(), meier.getVorigeZahl())) {
				builder.setMessage(context.getString(R.string.meier_du_hast) + " " + meier.getErgebnis() + context.getString(R.string.meier_gewuerfelt) + ".\n" + 
							context.getString(R.string.meier_die_zahl_ist_hoeher_als_die_des_letzten_spielers_moechtest_du_trotzdem_luegen) + "?");
				positiveButtonText = getString(R.string.ja);
				wahlObLuegenOderNicht = true;
				builder.setNegativeButton(getString(R.string.nein), new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						naechsterSpielerDran();
					}
				});
			}
			//Falls beide Zahlen gleich
			else {
				builder.setMessage(context.getString(R.string.meier_du_hast) + " " + meier.getErgebnis() + context.getString(R.string.meier_gewuerfelt) + ".\n" + 
							context.getString(R.string.meier_die_zahl_ist_gleich_hoch_wie_die_des_vorigen_spielers_du_musst_luegen) + "!");
				positiveButtonText = getString(R.string.ok);
				builder.setNegativeButton(null, null);
				wahlObLuegenOderNicht = false;
			}
			
			builder.setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					if (wahlObLuegenOderNicht)
						luegenDialog(meier.getErgebnis());
					else
						luegenDialog(meier.getVorigeZahl());
				}
			});
			
			builder.show();
		}
	}

	public void naechsterSpielerDran() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		Spieler.incrementAktuellerSpieler();
		builder.setMessage(Spieler.getAktuellerSpieler() + " " + context.getString(R.string.meier_ist_dran_alle_anderen_spieler_haben_wegzuschauen) + ".");
		builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				glaubeFrage();
			}
		});
		builder.show();
	}
	
	public void glaubeFrage() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		builder.setTitle(Spieler.getAktuellerSpieler());
		if (meier.getGelogeneZahl() == 0)
			builder.setMessage(context.getString(R.string.meier_glauben_sie) + " " + Spieler.getVorigerSpieler() + ", " + context.getString(R.string.meier_dass_er) + meier.getErgebnis() + " " + context.getString(R.string.meier_gewuerfelt_hat) + "?");
		else
			builder.setMessage(context.getString(R.string.meier_glauben_sie) + " " + Spieler.getVorigerSpieler() + ", " + context.getString(R.string.meier_dass_er) + meier.getGelogeneZahl() + " " + context.getString(R.string.meier_gewuerfelt_hat) + "?");
		
		builder.setPositiveButton(getString(R.string.ja), new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				
				meier.setGlaubtDassGelogen(false);
				
				if (meier.getGelogeneZahl() != 0) {
					meier.setErgebnis(meier.getGelogeneZahl());
					meier.setGelogeneZahl(0);
				}
				
				weitererZug();
			}
		});
		builder.setNegativeButton(getString(R.string.nein), new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				meier.setGlaubtDassGelogen(true);
				wurfAuswerten();
			}
		});
		builder.show();
	}
	
	public void wurfAuswerten() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		builder.setTitle(Spieler.getAktuellerSpieler());
		if (meier.getGelogeneZahl() == 0)
			builder.setMessage(context.getString(R.string.meier_falsch) + " " + Spieler.getVorigerSpieler() + " " + context.getString(R.string.meier_hatte_wirklich) + " " + meier.getErgebnis() + ", " + context.getString(R.string.meier_du_musst_trinken));
		else {
			builder.setMessage(context.getString(R.string.meier_richtig) +  " " + Spieler.getVorigerSpieler() + " " + context.getString(R.string.meier_hatte_in_wirklichkeit) + " " + meier.getErgebnis() + ", " + context.getString(R.string.meier_er_muss_trinken));
			Spieler.decrementAktuellerSpieler();
		}
		
		builder.setPositiveButton(getString(R.string.neues_spiel), new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				meier.setErgebnis(0);
				meier.setVorigeZahl(0);
				meier.setGelogeneZahl(0);
				
				aktuellerSpielerDran();
			}
		});
		builder.setNegativeButton(getString(R.string.zum_hauptmenue), new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				meier.wirklichBeendenDialog(context);	
			}
		});
		builder.show();
	}
	
	private void aktuellerSpielerDran() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(Spieler.getAktuellerSpieler() + " " + context.getString(R.string.meier_ist_dran_alle_anderen_spieler_haben_wegzuschauen) + ".");
		builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				ersterZug();
			}
		});
		builder.show();
	}
	
	public void luegenDialog(int startZahl) {
		
		final String[] charNumbers = meier.moeglicheLuegenZahlen(startZahl);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(context.getString(R.string.meier_waehle_eine_zahl));
		builder.setItems(charNumbers, new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				String s = (String) charNumbers[which];
				meier.setGelogeneZahl(Integer.parseInt(s));
				
				if (meier.getGelogeneZahl() == 21)
					meierAuswerten();
				else
					naechsterSpielerDran();
			}
		});
		builder.show();
	}
	
	public void meierAuswerten() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(Spieler.getAktuellerSpieler());
		
		if (meier.getErgebnis() == 21)
			builder.setMessage(context.getString(R.string.meier_du_hast_meier_gewuerfelt) + ".\n" + Spieler.getNaechsterSpieler() + " " + context.getString(R.string.meier_muss_trinken) + ".");
		else
			builder.setMessage(context.getString(R.string.meier_du_hast_meier_angesagt_und_gelogen_du_musst_trinken) + ".");
			
		builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				if (meier.getErgebnis() == 21)
					Spieler.incrementAktuellerSpieler();

				meier.setErgebnis(0);
				meier.setVorigeZahl(0);
				meier.setGelogeneZahl(0);
				
				aktuellerSpielerDran();
			}
		});
		
		builder.show();
	}
}
