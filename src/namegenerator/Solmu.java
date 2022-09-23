package namegenerator;

import java.util.List;

public class Solmu {
	private Solmu parent; //Solmun vanhempi
	private Solmu[] lapset;
	private int lapsia;
	private String nimi;
	private int kaynnit;
	
	public Solmu(String nimi, Solmu parent) {
		this.parent = parent;
		this.nimi = nimi;
		lapset = new Solmu[5];
	}
	
	public Solmu GetLapsi(String merkki) {
		for (int i = 0; i<lapsia; i++) {
			if(lapset[i].GetNimi() == merkki) return lapset[i];
		}
		return null;
	}
	
	public Solmu GetLapsi(int i) {
		return lapset[i];
	}
	
	public String GetNimi() {
		return nimi;
	}
	
	public int GetKaynnit() {
		return kaynnit;
	}
	
	public void SetKaynnit(int uusi) {
		kaynnit = uusi;
	}
	
	public int GetLapsia() {
		return lapsia;
	}
	
	public void SetLapsia(int uusi) {
		lapsia = uusi;
	}
	
	public void SetLapsi(Solmu uusi) {
		if(lapsia+1==lapset.length) LisaaPaikkoja(uusi);
		else {
			lapset[lapsia] = uusi;
			lapsia++;
		}
	}
	
	public void LisaaPaikkoja(Solmu uusi) {
		Solmu[] uusilista = new Solmu[lapsia+10];
		for (int i = 0; i < lapsia; i++) {
			uusilista[i] = lapset[i];
		}
		uusilista[lapsia] = uusi;
		lapsia++;
		lapset = uusilista;
	}

}
