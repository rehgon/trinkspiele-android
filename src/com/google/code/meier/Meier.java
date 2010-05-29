package com.google.code.meier;
import java.util.Random;

import android.content.Context;

import com.google.code.trinkspiele.Spieler;
import com.google.code.trinkspiele.Wuerfelspiel;


public class Meier extends Wuerfelspiel{
	
	private int wuerfel1, wuerfel2, ergebnis, vorigeZahl, gelogeneZahl;
	private Random zufallsZahl = new Random();
	private boolean zuBeginnLuegen, zahlZuTief, glaubtDassGelogen;
	
	public Meier(Context context) {
		super(context);
		wuerfel1 = 0;
		wuerfel2 = 0;
		ergebnis = 0;
		vorigeZahl = 0;
		zuBeginnLuegen = false;
		zahlZuTief = false;
		glaubtDassGelogen = false;
	}
	public int getErgebnis() {
		return ergebnis;
	}
	public void setErgebnis(int ergebnis) {
		this.ergebnis = ergebnis;
	}
	public int getVorigeZahl() {
		return vorigeZahl;
	}
	public void setVorigeZahl(int vorigeZahl) {
		this.vorigeZahl = vorigeZahl;
	}
	public int getGelogeneZahl() {
		return gelogeneZahl;
	}
	public void setGelogeneZahl(int zahl) {
		gelogeneZahl = zahl;
	}
	
	public void wuerfeln() {
		vorigeZahl = ergebnis;
		wuerfel1 = wuerfelzahlGenerieren();
		wuerfel2 = wuerfelzahlGenerieren();
		if (wuerfel1 >= wuerfel2)
			ergebnis = (wuerfel1 * 10) + wuerfel2;
		else
			ergebnis = (wuerfel2 * 10) + wuerfel1;
	}
	
	public boolean hatGelogen() {
		 	if(!istHoeher(ergebnis, this.vorigeZahl))
		 		return true;
		 	else
		 		return false;
	 }
	 
	public boolean istHoeher(int jetztigeZahl, int vorigeZahl) {
		 if (istPaschOderMeier(jetztigeZahl) == true && istPaschOderMeier(vorigeZahl) == true) { //2x Pasch
			 if (vorigeZahl == 21)
				 return false;
			 else if (jetztigeZahl == 21) //if Ergebnis == Meier
				 return true;
			 else if (jetztigeZahl> vorigeZahl)
				 return true;
			 else //ergebnis <= vorigeZahl
				 return false;
		 }
		 else if (istPaschOderMeier(jetztigeZahl) == true && istPaschOderMeier(vorigeZahl) == false)
			 return true;
		 else if (istPaschOderMeier(jetztigeZahl) == false && istPaschOderMeier(vorigeZahl) == true)
			 return false;
		 else {
			 if (jetztigeZahl > vorigeZahl)
				 return true;
			 else
				 return false;
		 } 
	 }
	 
	private int wuerfelzahlGenerieren() {
		return zufallsZahl.nextInt(6) + 1;
	}
	
	public boolean istPaschOderMeier(int zahl) {
		if (zahl == 21)
			return true;
		else if ((zahl / 10) == (zahl % 10))
			return true;
		else 
			return false;
	}
	
	public String wuerfelZug() {
		String ausgabe = "Du hast " + getErgebnis() + " gewürfelt. ";
		if (getVorigeZahl() == 0) {
			ausgabe += "Möchtest du gleich zu Beginn lügen?";
			zuBeginnLuegen = true;
		}
		else if (getVorigeZahl() > getErgebnis()) {
			ausgabe += "Die Zahl ist tiefer als die des vorigen Spielers, du musst lügen";
			zahlZuTief = true;
		}
		else if (getVorigeZahl() < getErgebnis()) {
			ausgabe += "Die Zahl ist höher als die des vorigen Spielers, möchtest du trotzdem lügen?";
			zahlZuTief = false;
		}
		return ausgabe;
	}
	
	public String glaubeFrage() {
		String ausgabe = "Glauben sie " + Spieler.getVorigerSpieler() + ", dass er ";
		if (gelogeneZahl == 0) { ausgabe += getVorigeZahl();   }
		else 				   { ausgabe += getGelogeneZahl(); }
		ausgabe += " gewürfelt hat?";
		return ausgabe;
	}
	
	public String naechsterSpielerDran() {
		return Spieler.getAktuellerSpieler() + " ist dran";
	}
	
	public String wurfAuswerten() {
		
		String ausgabe = "";
		if (glaubtDassGelogen) {
			if (gelogeneZahl == 0)
				ausgabe = "Falsch, der Spieler hat die Wahrheit gesagt";
			else
				ausgabe = "Richtig, der Spieler hat gelogen";
		}
		else
			setErgebnis(getGelogeneZahl());
		return ausgabe;
	}
	
	/*
	public void spielStarten() {
		boolean weiterWuerfeln;

		int beenden = 1;
		do {
			try {
				do {
					// Wird nur zu Beginn einer neuen Runde ausgeführt
					if (getVorigeZahl() == 0) {
						wuerfeln();
						if (getErgebnis() == 21)
							throw new Exception("Meier Exception");
						int yON = JOptionPane.showConfirmDialog(null, "Sie haben " + getErgebnis() + " gewürfelt\n" + 
								"Müchten sie gleich zu Beginn lügen?", "Trotzdem lügen?", JOptionPane.YES_NO_OPTION);
						if (yON == 0) {
							String input = JOptionPane.showInputDialog("Geben sie eine gedachte Zahl ein:");
							gelogeneZahl = Integer.parseInt(input);
							if (gelogeneZahl == 21) {
								JOptionPane.showMessageDialog(null, "Sie haben Meier angesagt und gelogen, also müssen sie trinken");
								throw new Exception("no Meier Eception");
							}
							while(((gelogeneZahl / 10) < (gelogeneZahl % 10)) && (gelogeneZahl % 10 != gelogeneZahl / 10) && (gelogeneZahl != 21)) {
									input = JOptionPane.showInputDialog("Diese Zahl ist ungültig. Die Zehnerstelle " + 
											" muss hüher als die Einerstelle sein (ausser bei Pasch)");
									gelogeneZahl = Integer.parseInt(input);
							}
							if (gelogeneZahl < ergebnis) {
								ergebnis = gelogeneZahl;
								gelogeneZahl = 0;
							}
						}
						JOptionPane.showMessageDialog(null, "Der nächste Spieler ist dran");
					}
					
					int eingabe;
					 // Je nachdem, ob der Spieler die Wahrheit sagt 
					//oder gelogen hat, wird die wahre oder die gelogene Zahl angezeigt
					if (gelogeneZahl > 0) { 
						eingabe = JOptionPane.showConfirmDialog(null, "Glauben sie dem vorigen Spieler, dass er " + getGelogeneZahl() + " gewürfelt hat?",
								"Glauben sie dem Spieler?", JOptionPane.YES_NO_OPTION);
					}
					else {
						eingabe = JOptionPane.showConfirmDialog(null, "Glauben sie dem vorigen Spieler, dass er " + getErgebnis() + " gewürfelt hat?",
								"Glauben sie dem Spieler?", JOptionPane.YES_NO_OPTION);
					}
					// Wenn "Nein", dann wird überprüft ob der Spieler die Wahrhheit sagt oder nicht und die Runde wird beendet, sonst gehts weiter
					if (eingabe == 1) {
						weiterWuerfeln = false;
						if (getGelogeneZahl() > 0)
							JOptionPane.showMessageDialog(null, "Sie hatten Recht, ihr Gegner hatte nur " + getErgebnis() + ". Er muss trinken");
						else
							JOptionPane.showMessageDialog(null, "Sie hatten Unrecht, ihr Gegner hatte wirklich " + getErgebnis() + "\n Sie müssen trinken");
						setVorigeZahl(0);
						setErgebnis(0);
						setGelogeneZahl(0);
					}
					else {
						if (gelogeneZahl > 0)
							setErgebnis(gelogeneZahl);
						weiterWuerfeln = true;
						wuerfeln();
						if (getErgebnis() == 21)
							throw new Exception("Meier Exception");
						JOptionPane.showMessageDialog(null, "Sie haben " + getErgebnis() + " gewürfelt");
						if(!istHoeher()) {
							String input = JOptionPane.showInputDialog("Leider ist ihre Zahl tiefer, als die ihres Gegners, sie müssen Lügen.\n" +
									"Geben sie eine gedachte Zahl, hüher als " + getVorigeZahl() + " ein: ");
							gelogeneZahl = Integer.parseInt(input);
							if (gelogeneZahl == 21) {
								JOptionPane.showMessageDialog(null, "Sie haben Meier angesagt und gelogen, also müssen sie trinken");
								throw new Exception("no Meier Eception");
							}
							while ((gelogeneZahl <= vorigeZahl)  || ((gelogeneZahl != 21) && ((gelogeneZahl / 10) < (gelogeneZahl % 10)) 
									&& ((gelogeneZahl % 10) != (gelogeneZahl / 10)))) {
								input = JOptionPane.showInputDialog("Diese Zahl ist ungültig. Ihre Eingabe muss hüher als " + getVorigeZahl() + " sein" +
										"\nund die Zehnerstelle muss hüher als die Einerstelle sein (ausser bei Pasch)");
								gelogeneZahl = Integer.parseInt(input);
							}
							JOptionPane.showMessageDialog(null, "Der nüchste Spieler ist dran");
						}
						else {
							int yON = JOptionPane.showConfirmDialog(null, "Müchten sie trotzdem lügen?", "Lügen?", JOptionPane.YES_NO_OPTION);
							if (yON == 0) {
								String input = JOptionPane.showInputDialog("Geben sie eine gedachte Zahl ein:");
								gelogeneZahl = Integer.parseInt(input);
								if (gelogeneZahl == 21) {
									JOptionPane.showMessageDialog(null, "Sie haben Meier angesagt und gelogen, also müssen sie trinken");
									throw new Exception("no Meier Eception");
								}
								while ((gelogeneZahl <= ergebnis)  || ((gelogeneZahl != 21) && ((gelogeneZahl / 10) < (gelogeneZahl % 10)) 
										&& ((gelogeneZahl % 10) != (gelogeneZahl / 10)))) {
									input = JOptionPane.showInputDialog("Diese Zahl ist ungültig. Ihre Eingabe muss hüher als " + getErgebnis() + " sein" +
											"\nund die Zehnerstelle muss hüher als die Einerstelle sein (ausser bei Pasch)");
									gelogeneZahl = Integer.parseInt(input);
								}
								if (gelogeneZahl < ergebnis) {
									ergebnis = gelogeneZahl;
									gelogeneZahl = 0;
								}
							}	
							JOptionPane.showMessageDialog(null, "Der nüchste Spieler ist dran");
						}
					}
				} while (weiterWuerfeln);
			}
			catch (Exception e) {}
			beenden = JOptionPane.showConfirmDialog(null, "Müchten sie noch eine Runde spielen?", "Noch ne Runde?", JOptionPane.YES_NO_OPTION);
		} while (beenden == 0);
		JOptionPane.showMessageDialog(null, "Vielen Dank fürs spielen");
		new MainFrame().setVisible(true);
	}
	*/
}
