package com.google.code.woody;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
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
import com.google.code.trinkspiele.Trinkspiele;

public class woodroid extends Activity implements View.OnClickListener {
	private static final int ID_NEUER_WOODY = 0;
	private static final int ID_BEENDEN = 1;

	Button wuerfelnButton;
	TextView ausgabe, werIstWoodyLabel, aktuellerSpieler;
	ImageView wuerfelEinsImage, wuerfelZweiImage;
	AlertDialog.Builder dialog;

	WoodyShaker woodyShaker;

	Woody woody;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.woody);

		wuerfelnButton = (Button) findViewById(R.id.Button01);
		wuerfelnButton.setOnClickListener((OnClickListener) this);
		ausgabe = (TextView) findViewById(R.id.ausgabe);
		werIstWoodyLabel = (TextView) findViewById(R.id.WerIstWoodyLabel);
		aktuellerSpieler = (TextView) findViewById(R.id.aktuellerSpieler);
		wuerfelEinsImage = (ImageView) findViewById(R.id.wuerfelEinsImage);
		wuerfelZweiImage = (ImageView) findViewById(R.id.wuerfelZweiImage);

		woody = new Woody();
		
		//Setzt den aktuellen Spieler am Anfang zufällig
		aktuellerSpieler.setText("Aktueller Spieler: " + woody.spielerAmAnfangBestimmen());
		
		ausgabe.setText("Zu Beginn muss ein Woody gewählt werden, auf den Button klicken um fortzufahren");
		wuerfelnButton.setText("neuen Woody wählen");

		
		// Sensor Initialisierung
		woodyShaker = new WoodyShaker();
		woodyShaker.initialize(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		woodyShaker.getMSensorManager().registerListener(woodyShaker.getMSensorEventListener(), woodyShaker.getMSensorManager()
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onStop() {
		woodyShaker.getMSensorManager().unregisterListener(woodyShaker.getMSensorEventListener());
		super.onStop();
	}

	public void onClick(View v) {
		handleEvent();
	}
	
	public Woody getWoody() {
		return woody;
	}

	public void handleEvent() {

		//Setzt einen neuen Woody wenn es entweder noch keinen gibt oder falls der Woody eine 3 geworfen hat
		if (woody.getNeuerWoody() || woody.getWerIstWoody() == "") {
			neuenWoodyBestimmen();
			woody.setNeuerWoody(false);
			wuerfelnButton.setText("würfeln");
		} else {
			
			woody.wuerfeln(2);

			werIstWoodyLabel
					.setText("Wer ist woody: " + woody.getWerIstWoody());
			aktuellerSpieler.setText("Aktueller Spieler: "
					+ Spieler.getAktuellerSpieler());
			ausgabe.setText(woody.auswerten(woody.getWuerfelZahl(0)
					+ woody.getWuerfelZahl(1)));

			woody.paint(woody, wuerfelEinsImage, 0);
			woody.paint(woody, wuerfelZweiImage, 1);

			// Wird noch vor dem AlertDialog angezeigt
			if (woody.getNeuerWoody()) {
				wuerfelnButton.setText("neuen Woody bestimmen");
			}
		}
	}

	private void neuenWoodyBestimmen() {

		String items[] = Spieler.convertArrayListToArray();
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		if (woody.getWerIstWoody() == "")
			builder.setTitle("Woody bestimmen");
		else
			builder.setTitle("Neuen Woody bestimmen");
		builder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				woody.setWerIstWoody(which);
				werIstWoodyLabel.setText("Wer ist woody: "
						+ woody.getWerIstWoody());
				ausgabe.setText("Woody ist " + woody.getWerIstWoody() + ". " + 
						Spieler.getAktuellerSpieler() + " ist dran mit würfeln");
			}
		});
		builder.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, ID_NEUER_WOODY, Menu.NONE, "Neuen Woody wählen");
		menu.add(Menu.NONE, ID_BEENDEN, Menu.NONE, "Zum Hauptmenü");
		return (super.onCreateOptionsMenu(menu));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return (applyMenuChoice(item) || super.onOptionsItemSelected(item));
	}

	private boolean applyMenuChoice(MenuItem item) {
		switch (item.getItemId()) {
		case ID_NEUER_WOODY:
			neuenWoodyBestimmen();
			return true;
		case ID_BEENDEN:
			startActivity(new Intent(this, Trinkspiele.class));
			return true;
		}
		return false;
	}
}