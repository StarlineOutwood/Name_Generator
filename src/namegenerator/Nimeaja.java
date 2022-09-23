package namegenerator;

import java.util.Random;

public class Nimeaja {
	private StringBuilder nimi;
	private Solmu juuri;
	private Solmu nyt;
	private int sanaryhmia;
	
	public Nimeaja() {
		//Muodostettaisiin nimeäjä, jos sille olisi tarvella
	}
	
	public int GetSanaryhmia() {
		return sanaryhmia;
	}
	
	public StringBuilder GetNimi() {
		return nimi;
	}
	
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
	}
	
}









