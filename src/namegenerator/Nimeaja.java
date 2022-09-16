package namegenerator;

import java.util.Random;

public class Nimeaja {
	private StringBuilder nimi;
	private int kirjaimia;
	private Kirjaimet kirjaimet;
	
	private Nimeaja() {
		this.nimi = new StringBuilder("");
		this.kirjaimia = 0;
	}
	
	public StringBuilder GetNimi() {
		return nimi;
	}
	
	public void SetNimi(StringBuilder uusiNimi) {
		nimi = uusiNimi;
	}
	
	public int GetKirjaimia() {
		return kirjaimia;
	}
	
	public void SetKirjaimia(int uusi) {
		kirjaimia = uusi;
	}
	
	public Kirjain GetNykyinen() {
		return kirjaimet.GetNykyinen();
	}
	
	public void SetNykyinen(Kirjain uusi) {
		kirjaimet.SetNykyinen(uusi);
	}
	
	public StringBuilder LuoUusi() {
		Random r = new Random();
		boolean lopetus;
		do {
			int next = 0 + 28*r.nextInt(); //Tässä kohtaa arpoo, mikä seuraava kirjain on, ei vielä painotettu TODO: panota
			SetNimi(GetNimi().append(kirjaimet.GetNykyinen().GetKirjain())); //Asetetaan uudeksi nimeksi tämänhetkinen nimi + uusi kirjain
			SetKirjaimia(GetKirjaimia() + 1);
			if(GetKirjaimia() >= 2) {
				lopetus = r.nextBoolean() //Tässä päätetään lopetetaanko. Painottajat vika kirjain ja sanan pituus
			}
		}while (!lopetus || GetKirjaimia() < 15);
		return GetNimi();
	}
	
}









