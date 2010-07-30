package com.google.code.siebenSaeuft;

import android.content.Context;

import com.google.code.trinkspiele.R;
import com.google.code.trinkspiele.Wuerfelspiel;




public class SiebenSauftLogik extends Wuerfelspiel {
	private int quersumme, maxPunkteBisEnde, ergebnissWurf, gesamtErgebnis;
	boolean saufen = false;
	private String ausgabe;
	Context context;
	
	public SiebenSauftLogik(Context context)
	{
		super(context);
		this.context = context;
		quersumme = 0;
		ergebnissWurf = 0;
		maxPunkteBisEnde = 100;
		gesamtErgebnis = 0;
	}
	
	public int getQuersumme()
	{
		return quersumme;
	}
	public int getGesamtErgebnis() {
		return gesamtErgebnis;
	}
	
	public void setErgebnis(int ergebnis)
	{
		ergebnis = getErgebnis() + getCalculate();
		setErgebnis(ergebnis);
	}

	public  String prüfeObdurch7teilbar(int ergebnis)
	{
		String ausgabe = context.getString(R.string.siebensaeuft_diezahlist);
		if(ergebnis % 7 == 0)
			this.saufen = true;
		else
		{
			ausgabe += " "+context.getString(R.string.siebensaeuft_nicht);
			this.saufen = false;
		}
		ausgabe += " "+context.getString(R.string.siebensaeuft_durch7teilbar);
		return ausgabe;
	}
	public String prüfeObErgebnissDurch7Teilbar(int gesammtErgebniss)
	{
		String ausgabe = "";
		if(gesammtErgebniss % 7 == 0)
		{
			ausgabe += context.getString(R.string.siebensaeuft_punktestanddurch7);
			this.saufen = true;
		
		}
		else if(this.saufen == true)
		{
			this.saufen = true;
		}
		else {
			this.saufen = false;
		}
		return ausgabe;
	}
	public void pruefeQuersumme(int ergebnis)
	{
		int quersumme = 0;
		while(ergebnis > 0)
		{
			quersumme += ergebnis % 10;
			ergebnis = ergebnis/10;
		}
		this.quersumme = quersumme;
	}		
	
	public void calculate(int wuerfel1, int wuerfel2)
	{
		ergebnissWurf = wuerfel1 + wuerfel2;
	}
	public int getCalculate()
	{
		return ergebnissWurf;
	}
	public String saufOmat(int gesammtErg)
	{
		String ausgabe = "";
		
		if(quersumme == 7)
		{
			this.saufen = true;
			ausgabe += context.getString(R.string.siebensaeuft_quersumme7);
		}
		else if(this.saufen == true) //lässt eine schon bestehendes saufen == true bestehen
		{
			this.saufen = true;
		}
		else
		{
			this.saufen = false;
		}
		
		return ausgabe;
	}
	public boolean getSaufOmat()
	{
		return this.saufen;
	}
	
	public String mussGesoffenWerden(boolean saufen)
	{
		String ausgabe = "";
		if(saufen == true)
		{
			return ausgabe += context.getString(R.string.siebensaeuft_trinken);
			
		}
		else{
			return ausgabe += context.getString(R.string.siebensaeuft_nichttrinken);
		}
	}
	public String chat()
	{
		ausgabe = "";
		wuerfeln(2);
		calculate(getWuerfelZahl(0), getWuerfelZahl(1));
		ausgabe += context.getString(R.string.siebensaeuft_eswurde)+ " " + getCalculate() +" "+ context.getString(R.string.siebensaeuft_geworfen);
		gesamtErgebnis += getWuerfelZahl(0) + getWuerfelZahl(1);
		ausgabe +=context.getString(R.string.siebensaeuft_punktestandzurzeit) +" " + getGesamtErgebnis() + "\n";
		ausgabe += prüfeObdurch7teilbar(getErgebnis());
		ausgabe += prüfeObErgebnissDurch7Teilbar(getGesamtErgebnis());
		pruefeQuersumme(getGesamtErgebnis());
		ausgabe += saufOmat(getQuersumme());
		ausgabe += mussGesoffenWerden(this.saufen);
		ausgabe += context.getString(R.string.siebensaeuft_bittewuerfeln);

		if (maxPunkteBisEnde <= getGesamtErgebnis()) {
			ausgabe = context.getString(R.string.siebensaeuft_spielende);
		}
		return ausgabe;
	}
	
	

}
