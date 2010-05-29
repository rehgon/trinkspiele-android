package com.google.code.woody;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
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
import com.google.code.trinkspiele.Spieler;

public class woodroid extends Activity implements View.OnClickListener {
	private static final int ID_NEUER_WOODY = 0;
	private static final int ID_HELP = 1;
	private static final int ID_BEENDEN = 2;

	Button wuerfelnButton;
	TextView ausgabe, werIstWoodyLabel, aktuellerSpieler;
	ImageView wuerfelEinsImage, wuerfelZweiImage;
	AlertDialog.Builder dialog;

	private SensorManager mSensorManager;
	private float mAccel; // acceleration apart from gravity
	private float mAccelCurrent; // current acceleration including gravity
	private float mAccelLast; // last acceleration including gravity

	Woody woody;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.woody);

		wuerfelnButton = (Button) findViewById(R.id.Button01);
		wuerfelnButton.setText(getText(R.string.woody_wuerfeln));
		wuerfelnButton.setOnClickListener((OnClickListener) this);
		
		ausgabe = (TextView) findViewById(R.id.ausgabe);
		
		werIstWoodyLabel = (TextView) findViewById(R.id.WerIstWoodyLabel);
		aktuellerSpieler = (TextView) findViewById(R.id.aktuellerSpieler);
		
		wuerfelEinsImage = (ImageView) findViewById(R.id.wuerfelEinsImage);
		wuerfelZweiImage = (ImageView) findViewById(R.id.wuerfelZweiImage);

		woody = new Woody(getApplicationContext());
		
		//Setzt den aktuellen Spieler am Anfang zufällig
		werIstWoodyLabel.setText(getString(R.string.woody_wer_ist_woody) + ": ");
		aktuellerSpieler.setText(getString(R.string.woody_aktueller_spieler) + ": " + woody.spielerAmAnfangBestimmen());
		
		ausgabe.setText(getString(R.string.woody_zu_beginn_muss_ein_woody_gewaehlt_werden__));
		wuerfelnButton.setText(getString(R.string.woody_woody_bestimmen));

		
		// Sensor Initialisierung
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mSensorManager.registerListener(mSensorListener, mSensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
		mAccel = 0.00f;
		mAccelCurrent = SensorManager.GRAVITY_EARTH;
		mAccelLast = SensorManager.GRAVITY_EARTH;
	}

	@Override
	protected void onResume() {
		super.onResume();
		mSensorManager.registerListener(mSensorListener, mSensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
		
		//wieder löschen
		ausgabe.setText(ausgabe.getText());
		
	}

	@Override
	protected void onStop() {
		mSensorManager.unregisterListener(mSensorListener);
		super.onStop();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if ((keyCode == KeyEvent.KEYCODE_BACK)) {
	    	woody.wirklichBeendenDialog(this);
	    	return true;
	    }
	    return false;
	}


	public void onClick(View v) {
		handleEvent();
	}

	public void handleEvent() {

		//Setzt einen neuen Woody wenn es entweder noch keinen gibt oder falls der Woody eine 3 geworfen hat
		if (woody.getNeuerWoody() || woody.getWerIstWoody() == "") {
			neuenWoodyBestimmen();
			woody.setNeuerWoody(false);
			wuerfelnButton.setText(getString(R.string.woody_wuerfeln));
		} 
		else {

			woody.wuerfeln(2);
			
			//Aktualisiert die Labels, sowie die Ausgabe
			werIstWoodyLabel
					.setText(getString(R.string.woody_wer_ist_woody) + ": " + woody.getWerIstWoody());
			aktuellerSpieler.setText(getString(R.string.woody_aktueller_spieler) + ": "
					+ Spieler.getAktuellerSpieler());
			ausgabe.setText(woody.auswerten(woody.getWuerfelZahl(0)
					+ woody.getWuerfelZahl(1)));

			woody.paint(woody, wuerfelEinsImage, woody.getWuerfelZahl(0));
			woody.paint(woody, wuerfelZweiImage, woody.getWuerfelZahl(1));

			// Wird noch vor dem AlertDialog angezeigt
			if (woody.getNeuerWoody()) {
				wuerfelnButton.setText(getString(R.string.woody_neuen_woody_bestimmen));
			}
		}
	}

	//Zeigt einen Dialog, wo man einen neuen Woody bestimmen muss
	private void neuenWoodyBestimmen() {

		String items[] = Spieler.convertArrayListToArray();
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		if (woody.getWerIstWoody() == "")
			builder.setTitle(getString(R.string.woody_woody_bestimmen));
		else
			builder.setTitle(getString(R.string.woody_neuen_woody_bestimmen));
		builder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				woody.setWerIstWoody(which);
				werIstWoodyLabel.setText(getString(R.string.woody_wer_ist_woody) + ": "
						+ woody.getWerIstWoody());
				ausgabe.setText(getString(R.string.woody_woody_ist) + " " + woody.getWerIstWoody() + ". " + 
						getString(R.string.woody___nur_eng__its) + Spieler.getAktuellerSpieler() + getString(R.string.woody_ist_dran_mit_wuerfeln));
			}
		});
		builder.show();
	}

	//Erstellt die Optionen für das Menü
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, ID_NEUER_WOODY, Menu.NONE, getString(R.string.woody_neuen_woody_bestimmen)).setIcon(R.drawable.ic_menu_refresh);
		menu.add(Menu.NONE, ID_HELP, Menu.NONE, getString(R.string.hilfe)).setIcon(R.drawable.ic_menu_info);
		menu.add(Menu.NONE, ID_BEENDEN, Menu.NONE, getString(R.string.zum_hauptmenue)).setIcon(R.drawable.ic_menu_close);
		return (super.onCreateOptionsMenu(menu));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return (applyMenuChoice(item) || super.onOptionsItemSelected(item));
	}

	//Bestimmt, was passiert, wenn auf eine bestimmte Option im Menü geklickt wird
	private boolean applyMenuChoice(MenuItem item) {
		switch (item.getItemId()) {
		case ID_NEUER_WOODY:
			neuenWoodyBestimmen();
			return true;
		case ID_BEENDEN:
			woody.wirklichBeendenDialog(this);
			return true;
		case ID_HELP:
			woody.createHelperDialog(this, getString(R.string.woody_help_message));
		}
		return false;
	}

	// Sensor Definierung
	private final SensorEventListener mSensorListener = new SensorEventListener() {
		
		long lastTimeStamp = System.currentTimeMillis();

		public void onSensorChanged(SensorEvent se) {
			float x = se.values[0];
			float y = se.values[1];
			float z = se.values[2];
			mAccelLast = mAccelCurrent;
			mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
			float delta = mAccelCurrent - mAccelLast;
			mAccel = mAccel * 0.9f + delta; // perform low-cut filter
			
			// Führt einen Spielzug aus wenn man schüttelt, danach kann man 2 Sekunden lang nicht mehr schütteln
			if (mAccel > 3 && !woody.getNeuerWoody() && (System.currentTimeMillis() - lastTimeStamp) > 2000) {
				lastTimeStamp = System.currentTimeMillis();
				handleEvent();
			}
		}

		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}
	};
}