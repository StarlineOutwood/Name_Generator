package namegenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Opetus {
	private int lukumaara; //kuinka monta sanaa ollaan käyty läpi
	private List<Solmu> puu;
	private String tiedosto;
	private Solmu juuri;
	private Solmu nyt;
	private int sanaryhmia = 0;
	
	public Opetus() {
		this.tiedosto = "kirjaindata";
		this.juuri = new Solmu("juuri");
		this.nyt = juuri;
	}
	
	public String GetTiedosto() {
		return tiedosto;
	}
	
	public void SetTiedosto(String nimi) {
		tiedosto = nimi;
	}
	
	/**
	 * Palauttaa sen solmun, jota käsitellään nyt (Eli mikä poistuu käsiteltävästä stringipätkästä seuraavalla kierroksella)
	 * @return
	 */
	public Solmu GetNyt() {
		return nyt;
	}
	
	/**
	 * Asetetaan uusi käsiteltävä solmu
	 * @param uusi : uusi käsiteltävä solmu
	 */
	public void SetNyt(Solmu uusi) {
		nyt = uusi;
	}
	
	/**
	 * asettaa uuden juuren
	 * @param uusi uusi juuri
	 */
	public void SetJuuri(Solmu uusi) {
		juuri = uusi;
	}
	
	/**
	 * palauttaa juuren
	 * @return juuri
	 */
	public Solmu GetJuuri() {
		return juuri;
	}
	
	public void SetSanaryhmia(int uusi) {
		sanaryhmia = uusi;
	}
	
	public int GetSanaryhmia() {
		return sanaryhmia;
	}
	
	public void OpetaNimi(String nimi) {
		nimi.toLowerCase();
		for(int i = 0; i<(nimi.length()-1); i++) {
			String tutkittava = nimi.substring(i, i+2);
			OpetaMerkkijono(tutkittava);
		}
	}
	
	public void OpetaMerkkijono(String patka) {
		GetJuuri().SetKaynnit(GetJuuri().GetKaynnit() + 1);
		char kirjain = patka.charAt(0);
		for (int i = 0; i < GetNyt().GetLapsia(); i++) {
			char vertaus = GetNyt().GetLapsi(i).GetNimi().charAt(0);
				if (kirjain == vertaus) {
					SetNyt(GetNyt().GetLapsi(i));
					GetNyt().SetKaynnit(GetNyt().GetKaynnit() + 1);
				}
				else {
					Solmu uusi = new Solmu(String.valueOf(kirjain), GetNyt());
					puu.add(uusi);
				}
		}
	}
	
    public void lueTiedosto(String nimi) {
        SetTiedosto(nimi);
        try (Scanner fi = new Scanner(new FileInputStream(new File(nimi)))) { // Jotta UTF8/ISO-8859 toimii
            while ( fi.hasNext() ) {
                try {
                    String s = fi.nextLine();
                    String[] nimet = s.split(":");
                    for (String uusiNimi : nimet) {
                    	OpetaNimi(uusiNimi);
                    }
                } catch (NumberFormatException ex){
                    //
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Tiedosto ei aukea! " + ex.getMessage());
        }
    }
	
}
