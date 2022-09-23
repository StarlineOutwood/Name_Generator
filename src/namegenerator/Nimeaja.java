package namegenerator;

import java.util.Random;

public class Nimeaja {
	private StringBuilder nimi;
<<<<<<< HEAD
	private Solmu juuri;
	private Solmu nyt;
	private int sanaryhmia;
	
	public Nimeaja() {
		//Muodostettaisiin nimeäjä, jos sille olisi tarvella
	}
	
	public int GetSanaryhmia() {
		return sanaryhmia;
=======
	private int kirjaimia;
	private Kirjaimet kirjaimet;
	
	private Nimeaja() {
		this.nimi = new StringBuilder("");
		this.kirjaimia = 0;
>>>>>>> a271411d463b6cb2af90e2ceab026fa6461e9f8f
	}
	
	public StringBuilder GetNimi() {
		return nimi;
	}
	
<<<<<<< HEAD
	public void SetNimi(StringBuilder uusi) {
		nimi=uusi;
	}
	
	/***
	 * Muodostaa nimen
	 * @return uusi nimi
	 */
	public StringBuilder LuoUusi() {
		nimi = new StringBuilder("");
		KirjaimenLisays(nimi, juuri);
		KirjaimenLisays(nimi, GetNyt());
		KirjaimenLisays(nimi, GetNyt());
		return nimi;
	}
	
	public Solmu GetNyt() {
		return nyt;
	}
	
	public void SetNyt(Solmu uusi) {
		nyt = uusi;
	}
	
	public void KirjaimenLisays(StringBuilder nimi, Solmu vanhempi) {
		Random random = new Random();
		int luku = random.nextInt(GetSanaryhmia(), 0) +1;
		int aloitus = 0;
		for (int i = 0; i < vanhempi.GetLapsia();i++) {
			aloitus += vanhempi.GetLapsi(i).GetKaynnit();
			if (luku <= aloitus) {
				nimi.append(vanhempi.GetLapsi(i).GetNimi());
				SetNyt(vanhempi.GetLapsi(i));
				i = vanhempi.GetLapsia();
			}
		}
=======
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
>>>>>>> a271411d463b6cb2af90e2ceab026fa6461e9f8f
	}
	
}









