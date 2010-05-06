package com.google.code.woody;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.code.trinkspiele.R;

public class woodroid extends Activity implements View.OnClickListener {
	private static final int ID_SPIELER_OPTIONEN = 0;
	private static final int ID_BEENDEN = 1;
	
	Button wuerfelnButton;
	TextView ausgabe, werIstWoodyLabel, aktuellerSpieler;
	ImageView wuerfelEinsImage, wuerfelZweiImage;
	String spielerName[];

	Woody woody;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.xml.woody);
		
		

		wuerfelnButton = (Button) findViewById(R.id.Button01);
		wuerfelnButton.setOnClickListener((OnClickListener) this);

		ausgabe = (TextView) findViewById(R.id.ausgabe);
		werIstWoodyLabel = (TextView) findViewById(R.id.WerIstWoodyLabel);
		aktuellerSpieler = (TextView) findViewById(R.id.aktuellerSpieler);
		wuerfelEinsImage = (ImageView) findViewById(R.id.wuerfelEinsImage);
		wuerfelZweiImage = (ImageView) findViewById(R.id.wuerfelZweiImage);
		spielerName = getResources().getStringArray(R.array.SpielerNamen);

		woody = new Woody();

		woody.getSpieler().setAktuellerSpielerIndex(0);
		woody.setWerIstWoody(1);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		if (prefs.getString("spielerHinzufuegen", "<unset>") != "")
			woody.getSpieler().spielerHinzufügen(prefs.getString("spielerHinzufuegen", "<unset>"));
	}
	
	@Override
	public void onClick(View v) {

		woody.wuerfeln(2);

		werIstWoodyLabel.setText("Wer ist woody: " + woody.getWerIstWoody());
		aktuellerSpieler.setText("Aktueller Spieler: "
				+ woody.getSpieler().getAktuellerSpieler());
		ausgabe.setText(woody.auswerten(woody.getWuerfelZahl(0)
				+ woody.getWuerfelZahl(1)));
		
		repaintImage();
		
		Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		long milliseconds = 1000;  
		vib.vibrate(milliseconds);
	}

	private void repaintImage() {

		if (woody.getWuerfelZahl(0) == 1)
			wuerfelEinsImage.setImageResource(R.drawable.w1);
		else if (woody.getWuerfelZahl(0) == 2)
			wuerfelEinsImage.setImageResource(R.drawable.w2);
		else if (woody.getWuerfelZahl(0) == 3)
			wuerfelEinsImage.setImageResource(R.drawable.w3);
		else if (woody.getWuerfelZahl(0) == 4)
			wuerfelEinsImage.setImageResource(R.drawable.w4);
		else if (woody.getWuerfelZahl(0) == 5)
			wuerfelEinsImage.setImageResource(R.drawable.w5);
		else if (woody.getWuerfelZahl(0) == 6)
			wuerfelEinsImage.setImageResource(R.drawable.w6);

		if (woody.getWuerfelZahl(1) == 1)
			wuerfelZweiImage.setImageResource(R.drawable.w1);
		else if (woody.getWuerfelZahl(1) == 2)
			wuerfelZweiImage.setImageResource(R.drawable.w2);
		else if (woody.getWuerfelZahl(1) == 3)
			wuerfelZweiImage.setImageResource(R.drawable.w3);
		else if (woody.getWuerfelZahl(1) == 4)
			wuerfelZweiImage.setImageResource(R.drawable.w4);
		else if (woody.getWuerfelZahl(1) == 5)
			wuerfelZweiImage.setImageResource(R.drawable.w5);
		else if (woody.getWuerfelZahl(1) == 6)
			wuerfelZweiImage.setImageResource(R.drawable.w6);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, ID_SPIELER_OPTIONEN, Menu.NONE, "Spieler Optionen");
		menu.add(Menu.NONE, ID_BEENDEN, Menu.NONE, "Beenden");
		return (super.onCreateOptionsMenu(menu));
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return (applyMenuChoice(item) || super.onOptionsItemSelected(item));
	}
	
	private boolean applyMenuChoice(MenuItem item) {
		switch (item.getItemId()) {
		case ID_SPIELER_OPTIONEN:
			startActivity(new Intent(this, EditPreferences.class));
			return (true);
		case ID_BEENDEN:
			System.exit(0);
			return (true);
		}
		return (false);
	}
	
	public void addPlayer() {
		
	}
}