package com.google.code.woody;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.code.trinkspiele.R;
import com.google.code.trinkspiele.Spieler;

public class woodroid extends Activity implements View.OnClickListener {
	private static final int ID_BEENDEN = 1;
	
	Button wuerfelnButton;
	TextView ausgabe, werIstWoodyLabel, aktuellerSpieler;
	ImageView wuerfelEinsImage, wuerfelZweiImage;

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

		woody = new Woody();
		if (woody.getWerIstWoody() == "")
			showDialog(0);
	}
	
	
	public void onClick(View v) {
		
		if (woody.getNeuerWoody())
			showDialog(0);
		woody.setNeuerWoody(false);
		
		woody.wuerfeln(2);

		werIstWoodyLabel.setText("Wer ist woody: " + woody.getWerIstWoody());
		aktuellerSpieler.setText("Aktueller Spieler: "
				+ Spieler.getAktuellerSpieler());
		ausgabe.setText(woody.auswerten(woody.getWuerfelZahl(0)
				+ woody.getWuerfelZahl(1)));
		
		
		woody.paint(woody, wuerfelEinsImage, 0);
		woody.paint(woody, wuerfelZweiImage, 1);
	}
	
	protected Dialog onCreateDialog(int id) {
		Dialog dialog;
		switch (id) {
		case 0:
			dialog = neuenWoodyBestimmen();
			break;
		default:
			dialog = null;
		}
		return dialog;
	}
	
	private AlertDialog neuenWoodyBestimmen() {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Neuen Woody bestimmen");
		if (woody.getWerIstWoody() == "")
			builder.setMessage("Wählen sie zu Beginn einen Woody");
		builder.setItems(Spieler.convertArrayListToArray(), new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				woody.setWerIstWoody(which);
				werIstWoodyLabel.setText("Wer ist woody: " + woody.getWerIstWoody());
			}
		});
		AlertDialog alert = builder.create();
		return alert;
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
			System.exit(0);
			return (true);
		}
		return (false);
	}
}