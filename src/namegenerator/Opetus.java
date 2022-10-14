package namegenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Opetus {
	private int lukumaara; //kuinka monta sanaa ollaan käyty läpi
	private Trie puu;
	private String tiedosto;
	private Solmu juuri;
	private Solmu nyt;
	private int sanaryhmia = 0;
	private int taso;
	
	public Opetus(int taso) {
		this.tiedosto = "nimet";
		this.puu = new Trie(taso);
		this.juuri = puu.GetRoot();
		this.nyt = puu.GetRoot();
		this.taso = taso;
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
	
	public Trie GetPuu() {
		return puu;
	}
	
	/**
	 * Asetetaan uusi käsiteltävä solmu
	 * @param uusi : uusi käsiteltävä solmu
	 */
	public void SetNyt(Solmu uusi) {
		nyt = uusi;
	}
	
	public int GetTaso() {
		return taso;
	}
	
	public void SetTaso(int uusi) {
		taso = uusi;
		lueTiedosto(tiedosto, taso);
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
	
	public void OpetaNimi(String nimi, int taso) {
		nimi = nimi.toLowerCase();
		for(int i = 0; i<(nimi.length()-(taso-1)); i++) {
			String tutkittava = nimi.substring(i, i+taso);
			OpetaMerkkijono(tutkittava);
		}
	}
	
	public void OpetaMerkkijono(String patka) {
		GetPuu().OpetaMerkkijono(patka);
	}
	
    public void lueTiedosto(String nimi, int taso) {
        SetTiedosto(nimi);
        try (Scanner fi = new Scanner(new FileInputStream(new File(nimi)))) { // Jotta UTF8/ISO-8859 toimii
            while ( fi.hasNext() ) {
                try {
                    String s = fi.nextLine();
                    String[] nimet = s.split(":");
                    for (String uusiNimi : nimet) {
                    	OpetaNimi(uusiNimi, taso);
                    }
                } catch (NumberFormatException ex){
                    //
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Tiedosto ei aukea! " + ex.getMessage());
        }
    }
    
    public void talleta(String nimi) {
        try (PrintStream fo = new PrintStream(new FileOutputStream(nimi, false))){
            StringBuilder kaikkiRivit = new StringBuilder();
            fo.println(GetPuu().ToString());
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static void main(String args[]) {
    	Opetus ope = new Opetus(3);
    	ope.lueTiedosto(ope.GetTiedosto(), ope.GetTaso());
    	System.out.println(ope.GetJuuri().GetLapsi(0).GetNimi());
    	System.out.println(ope.GetJuuri().GetLapsi(0).GetLapsi(0).GetNimi());
    	ope.talleta("kirjaindata");
    }
	
}
