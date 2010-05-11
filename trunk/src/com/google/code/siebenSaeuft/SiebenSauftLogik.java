package com.google.code.siebenSaeuft;

import com.google.code.trinkspiele.Wuerfelspiel;




public class SiebenSauftLogik extends Wuerfelspiel {
	private int quersumme, maxPunkteBisEnde, ergebnissWurf, gesamtErgebnis;
	boolean saufen = false;
	private String ausgabe;
	public SiebenSauftLogik()
	{
		super();
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
		String ausgabe = "Die geworfene Zahl ist ";
		if(ergebnis % 7 == 0)
			this.saufen = true;
		else
		{
			ausgabe += "nicht ";
			this.saufen = false;
		}
		ausgabe += "durch 7 teilbar\n";
		return ausgabe;
	}
	public String prüfeObErgebnissDurch7Teilbar(int gesammtErgebniss)
	{
		String ausgabe = "";
		if(gesammtErgebniss % 7 == 0)
		{
			ausgabe +="Der Punktestand ist durch 7 teilbar,\n";
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
			ausgabe += "Quersumme ist 7\n";
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
			return ausgabe += " Es muss getrunken werden\n";
			
		}
		else{
			return ausgabe += "Es muss nicht getrunken werden\n";
		}
	}
	public String chat()
	{
		ausgabe = "";
		wuerfeln(2);
		calculate(getWuerfelZahl(0), getWuerfelZahl(1));
		ausgabe += "Es wurde eine " + getCalculate() + " geworfen.\n";
		gesamtErgebnis += getWuerfelZahl(0) + getWuerfelZahl(1);
		ausgabe +="Punkte Stand zur Zeit: " + getGesamtErgebnis() + "\n";
		ausgabe += prüfeObdurch7teilbar(getErgebnis());
		ausgabe += prüfeObErgebnissDurch7Teilbar(getGesamtErgebnis());
		pruefeQuersumme(getGesamtErgebnis());
		ausgabe += saufOmat( getQuersumme());
		ausgabe += mussGesoffenWerden(this.saufen);
		ausgabe += "Bitte würfeln\n";

		if (maxPunkteBisEnde <= getGesamtErgebnis()) {
			ausgabe = "Spiel zu Ende\nDanke fürs spielen";
		}
		return ausgabe;
	}
	
	

}
