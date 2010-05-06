package com.google.code.woody;



public class Woody extends Wuerfelspiel {

	private String werIstWoody;
	private Spieler spieler;
	
	public Woody() {
		super();
		werIstWoody = "";
		spieler = new Spieler("Woody");
		spieler.spielerSetzen();
	}
	
	public String getWerIstWoody() {
		return werIstWoody;
	}
	public void setWerIstWoody(int index) {
		werIstWoody = getSpieler().getSpielerName(index);
	}
	public Spieler getSpieler() {
		return spieler;
	}
	
	//Wertet den Wurf aus und liefert die Ausgabe als String zurück
	public String auswerten(int ergebnis) {
		
		String ausgabe = "";
		String WoodyMussTrinken = "Der Woody(" + getWerIstWoody() + ") muss trinken";
		String nichtsPassiert = "Nichts passiert, du musst weitergeben";
		String neuerWoody = "Es gibt einen neuen Woody";
		String pasch = super.getWuerfelZahl(0) + "er Pasch, du darfst " + super.getWuerfelZahl(0) + " Schlücke verteilen";
		
		switch (ergebnis) {
			case 2:
				ausgabe = pasch; 
				break;
			case 3:
				if (spieler.getAktuellerSpieler().equals(werIstWoody)) 
				{	
					neuenWoodyBestimmen();
					ausgabe = neuerWoody;
				} 
				else
					ausgabe = WoodyMussTrinken; 
				break;
			case 4:
				if (getWuerfelZahl(0) == 3 || getWuerfelZahl(1) == 3) 
				{
					if (spieler.getAktuellerSpieler().equals(werIstWoody)) 
					{
						neuenWoodyBestimmen();
						ausgabe = neuerWoody;
					} 
					else 
					{ 
						ausgabe = WoodyMussTrinken; 
					}
				} 
				else { ausgabe = pasch; } break;
			case 5:
				if (getWuerfelZahl(0) == 3 || getWuerfelZahl(1) == 3) {
					if (spieler.getAktuellerSpieler().equals(werIstWoody)) {
						neuenWoodyBestimmen();
						ausgabe = neuerWoody;
					} 
					else 
					ausgabe = WoodyMussTrinken;
				} 
				else 
				{
					ausgabe = nichtsPassiert;
					spieler.incrementAktuellerSpieler(); 
				} 
				break;
			case 6:
				if (getWuerfelZahl(0) == 3 && getWuerfelZahl(1) == 3) 
				{
					if (spieler.getAktuellerSpieler().equals(werIstWoody)) {
						ausgabe = pasch;
						neuenWoodyBestimmen();
						ausgabe = "3er Pasch, und es gibt einen neuen Woody";
					}
					else 
						ausgabe = "3er Pasch, und der Woody(" + getWerIstWoody() + ") muss einen trinken";
				}
				else if (getWuerfelZahl(0) == 3 || getWuerfelZahl(1) == 3) 
				{
					if (spieler.getAktuellerSpieler().equals(werIstWoody)) 
					{
						neuenWoodyBestimmen();
						ausgabe = neuerWoody;
					} 
					else
						ausgabe = WoodyMussTrinken;
				} 
				else 
				{
					ausgabe = nichtsPassiert;
					spieler.incrementAktuellerSpieler(); 
				} 
				break;
			case 7:
				if (getWuerfelZahl(0) == 3 || getWuerfelZahl(1) == 3) 
				{
					if (spieler.getAktuellerSpieler().equals(werIstWoody)) 
					{
						neuenWoodyBestimmen();
						ausgabe = neuerWoody;
					} 
					else 
					{
						ausgabe = "Dein linker Nachbar und der Woody(" + werIstWoody + ") müssen trinken"; 
					}
				} 
				else
					ausgabe = "Dein linker Nachbar muss einen trinken";
				break;
			case 8:
				if (getWuerfelZahl(0) == 3 || getWuerfelZahl(1) == 3) 
				{
					if (spieler.getAktuellerSpieler().equals(werIstWoody)) 
					{
						neuenWoodyBestimmen();
						ausgabe = neuerWoody;
					} 
					else
						ausgabe = WoodyMussTrinken;
				} 
				else if (getWuerfelZahl(0) == 4 && getWuerfelZahl(1) == 4)
					ausgabe = pasch;
				else 
				{
					ausgabe = nichtsPassiert;
					spieler.incrementAktuellerSpieler(); 
				} 
				break;
			case 9:
				if (getWuerfelZahl(0) == 3 || getWuerfelZahl(1) == 3) 
				{
					if (spieler.getAktuellerSpieler().equals(werIstWoody)) 
					{
						neuenWoodyBestimmen();
						ausgabe = neuerWoody;
					} 
					else 
						ausgabe = "Dein rechter Nachbar und der Woody(" + getWerIstWoody() + ") müssen trinken";
				} 
				else 
					ausgabe = "Dein rechter Nachbar muss einen trinken";
				break;
			case 10:
				if (getWuerfelZahl(0) == 5 && getWuerfelZahl(1) == 5)
					ausgabe = "5er Pasch, und alle nehmen einen Schluck";
				else
					ausgabe = "Alle nehmen einen Schluck... Cheers!!!";
				break;
			case 11:
				ausgabe = nichtsPassiert;
				spieler.incrementAktuellerSpieler(); break;
			case 12:
				ausgabe = "JACKPOT!!!, du darfst 12 Schlücke verteilen"; 
				break;
			default:
				ausgabe = "Critical Error!!!"; 
				break;
		} return ausgabe;
	}
	
	public String neuenWoodyBestimmen() {
		werIstWoody = spieler.getNaechsterSpieler();
		return spieler.getNaechsterSpieler();
	}
}


