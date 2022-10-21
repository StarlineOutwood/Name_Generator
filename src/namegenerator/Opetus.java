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
	
	/**
	 * Opetuksen alustaminen
	 * @param taso millä tasolla puu rakennetaan
	 * @param kieli minkä kielinen puu tulee
	 */
	public Opetus(int taso, String kieli) {
		this.tiedosto = kieli;
		this.puu = new Trie(taso);
		puu.SetRoot(new Solmu(" "));
		puu.SetNow(puu.GetRoot());
		puu.GetTree().add(puu.GetRoot());
		this.juuri = puu.GetRoot();
		this.nyt = puu.GetRoot();
		this.taso = taso;
	}
	
	/**
	 * palauttaa tiedoston nimen
	 * @return tiedoson nimi
	 */
	public String GetTiedosto() {
		return tiedosto;
	}
	
	/**
	 * asettaa tiedoston nimen
	 * @param nimi uusi nimi tiedostolle
	 */
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
	 * halee Trie-rakenteen, johon sanat tallenetaan
	 * @return puu
	 */
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
	
	/**
	 * Palauttaa tason, jolla opetetaan
	 * @return taso
	 */
	public int GetTaso() {
		return taso;
	}
	
	/**
	 * Asettaa tason 
	 * @param uusi uusi taso
	 */
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
	
	/**
	 * Pilkkoo nimen palasiksi ja asettaa sen Trie-rakenteeseen
	 * @param nimi tiedoston nimi
	 * @param taso millä tasolla opetetaan
	 */
	public void OpetaNimi(String nimi, int taso) {
		nimi = nimi.toLowerCase();
		for(int i = 0; i<(nimi.length()-(taso-1)); i++) {
			String tutkittava = nimi.substring(i, i+taso);
			OpetaMerkkijono(tutkittava);
		}
	}
	
	/**
	 * Opettaa Trielle merkkijonon
	 * @param patka
	 */
	public void OpetaMerkkijono(String patka) {
		GetPuu().OpetaMerkkijono(patka);
	}
	
	/**
	 * Lukee tiedostoa
	 * @param nimi mitä teidostoa luetaan
	 * @param taso millä tasolla sitä luetaan
	 */
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
    
    /**
     * tallentaa valmiiksi saadin Trie-rakenten haluttuun tiedostoon
     * @param nimi tiedosto, johon tallennetaan
     */
    public void talleta(String nimi) {
        try (PrintStream fo = new PrintStream(new FileOutputStream(nimi, false))){
            StringBuilder kaikkiRivit = new StringBuilder();
            fo.println(GetPuu().ToString());
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * testailua 
     * @param args ei käytetä
     */
    public static void main(String args[]) {
		System.out.println("Kielivlinta Suomi, kirjoita: Suomi");
		System.out.println("Choose language English, Write: English");
		Scanner scan = new Scanner(System.in);
		String kieli = scan.next();
		if (!kieli.equals("Suomi") && !kieli.equals("English")) {
			System.out.println("Kirjoita kielivalinta isolla alkukirjaimella ja tarkista kirjoitusvirheet");
			System.out.println("Write the name with a capital letter and chech for spelling mistakes");
			return;
		}
    	Opetus ope = new Opetus(3, kieli);
    	ope.lueTiedosto(ope.GetTiedosto(), ope.GetTaso());
    	System.out.println(ope.GetJuuri().GetLapsi(0).GetNimi());
    	System.out.println(ope.GetJuuri().GetLapsi(0).GetLapsi(0).GetNimi());
    	ope.talleta("kirjaindata");
    }
	
}
