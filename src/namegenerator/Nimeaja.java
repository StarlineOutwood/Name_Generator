package namegenerator;

import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Nimeaja {
	private StringBuilder nimi;
	private Solmu juuri;
	private Solmu nyt;
	private int sanaryhmia;
	private String tiedosto;
	private Trie puu;
	
	/**
	 * muodostetaan niemäjä, jos meillä on jo tiedostossa valmiita tietoja
	 * @param nimi tiedoston nimi
	 */
	public Nimeaja(String nimi, int taso) {
		this.puu = new Trie(taso);
		this.juuri = puu.GetRoot();
		this.nyt = puu.GetRoot();
		this.tiedosto = nimi;
		lueTiedosto(tiedosto);
		//Tähän tulee "Lue tämä tiedosto" missä on puun tiedot valmiina, josta voidaan luoda puu
	}
	
	/**
	 * Muodostetaan niemäja default tiedostolla
	 */
	public Nimeaja() {
		this.tiedosto="kirjaindata"; //Default tiedosto
	}
	
	/**
	 * Palauttaa sanaryhmien määrän (Eli luvun siitä, kuinka monta neljän kirjiamen joukkoa ollaan käsitelty puussa), ei välttämättä tarvitse
	 * @return sanaryhmien määrän
	 */
	public int GetSanaryhmia() {
		return sanaryhmia;
	}
	
	/**
	 * hakee tiedoston nimen
	 * @return tiedoston nimi
	 */
	public String GetTiedosto() {
		return tiedosto;
	}
	
	/**
	 * asettaa tiedoston nimen
	 * @param uusi uusi tiedoston nimi
	 */
	public void SetTiedosto(String uusi) {
		tiedosto=uusi;
	}
	
	/**
	 * Palauttaa tähän asti muodostetun nimen
	 * @return
	 */
	public StringBuilder GetNimi() {
		return nimi;
	}
	
	public void SetTrie(Trie uusi) {
		puu=uusi;
		SetNyt(uusi.GetNow());
		SetJuuri(uusi.GetRoot());
	}
	
	/**
	 * hakee listan, jossa on kaikki solmut
	 * @return lista, jossa on solmut eli puu
	 */
	public Trie GetTrie(){
		return puu;
	}
	
	/**
	 * Asettaa uuden nimen (kun kirjaimia lisätään
	 * @param uusi : uusi nimi
	 */
	public void SetNimi(StringBuilder uusi) {
		nimi=uusi;
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
		GetTrie().SetNow(uusi);
		nyt = uusi;
	}
	
	/**
	 * asettaa uuden juuren
	 * @param uusi uusi juuri
	 */
	public void SetJuuri(Solmu uusi) {
		GetTrie().SetRoot(uusi);
		juuri = uusi;
	}
	
	/**
	 * palauttaa juuren
	 * @return juuri
	 */
	public Solmu GetJuuri() {
		return juuri;
	}
	
	/***
	 * Muodostaa nimen
	 * @return uusi nimi
	 * <example>
	 * <pre name="test">
	 * Nimeaja testi = new Nimeaja("Fi", 3);
	 * testi.Opi(3, "Fi");
	 * String nimi = testi.LuoUusi(5, 3);
	 * nimi = nimi.toLowerCase();
	 * for(int i = 0; i < nimi.length() - 2; i++) {
			testi.SetNyt(testi.GetJuuri().GetLapsiS(String.valueOf(nimi.charAt(i))));
			testi.SetNyt(testi.GetNyt().GetLapsiS(String.valueOf(nimi.charAt(i+1))));
			testi.SetNyt(testi.GetNyt().GetLapsiS(String.valueOf(nimi.charAt(i+2))));
	 * }
	 * </pre>
	 * </example>
	 */
	public String LuoUusi(int pituus, int taso) {
		SetNimi(new StringBuilder(""));
		SetNyt(GetJuuri());
		KirjaimenLisays(GetNimi(), GetJuuri());
		for(int i = 0; i < taso-1; i++) {
			KirjaimenLisays(GetNimi(), GetNyt());
		}
		for(int i = 1; i < pituus - (taso-1); i++) {
			SetNyt(GetJuuri());
			for (int j = 0; j < taso-1; j++) {
				String now = String.valueOf(GetNimi().charAt(i+j));
				SetNyt(GetNyt().GetLapsiS(now));
				if (GetNyt() == null) {
					String output = GetNimi().substring(0,1).toUpperCase() + GetNimi().substring(1);
					return output;
				}
			}
			if (GetNyt() == null) {
				String output = GetNimi().substring(0,1).toUpperCase() + GetNimi().substring(1);
				return output;
			}
			KirjaimenLisays(GetNimi(), GetNyt());
		}
		String output = GetNimi().substring(0,1).toUpperCase() + GetNimi().substring(1);
		return output;	
	}
	
	/**
	 * Lisää kirjaimen sanaan
	 * @param nimi : nimi johon lisätään
	 * @param vanhempi : minkä solmun lapsia tarkastellaan
	 * Testailtu mainissa
	 */
	public void KirjaimenLisays(StringBuilder nimi, Solmu vanhempi) {
		Random random = new Random();
		int luku = random.nextInt(0, vanhempi.GetKaynnit()) +1;
		int aloitus = 0;
		for (int i = 0; i < vanhempi.GetLapsia();i++) {
			aloitus += vanhempi.GetLapsi(i).GetKaynnit();
			if (luku <= aloitus) {
				nimi.append(vanhempi.GetLapsi(i).GetNimi());
				SetNyt(vanhempi.GetLapsi(i));
				SetNimi(nimi);
				return;
			}
		}
	}
	
	/**
	 * Lukee tiedoston ja luo sen pohjalta solmut
	 * @param nimi Tämän tiedoston lukee
	 * <example>
     * <pre name="test">
     * Opetus ope = new Opetus(3, "testiLukuKirjoitus");
     * Solmu yks = new Solmu();
     * yks.parse("juuri:5:10:true:", ope.GetPuu());
     * ope.talleta("testiLukuKirjoitus");
     * Nimeaja ope2 = new Nimeaja("testiLukuKirjoitus", 3);
     * ope2.lueTiedosto(ope2.GetTiedosto());
     * ope2.GetJuuri().GetKaynnit() ~~~ 5;
     * </pre>
     * </example>
	 */
    public Trie lueTiedosto(String nimi) {
        SetTiedosto(nimi);
        try (Scanner fi = new Scanner(new FileInputStream(new File(nimi)))) { // Jotta UTF8/ISO-8859 toimii
            while ( fi.hasNext() ) {
                try {
                    String s = fi.nextLine();
                    Solmu uusi = new Solmu();
                    uusi.Parse(s, GetTrie());
                    if (uusi.OnkoJuuri()) SetJuuri(uusi);
                } catch (NumberFormatException ex){
                    //
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Tiedosto ei aukea! " + ex.getMessage());
        }
        return GetTrie();
    }
    
    /**
     * Tallentaa kaikki solmut tiedostoon
     * @param nimi mihin tiedostoon tallennetaan
     * <example>
     * <pre name="test">
     * Opetus ope = new Opetus(3, "testiLukuKirjoitus");
     * Solmu yks = new Solmu();
     * yks.parse("juuri:5:10:true:", ope.GetPuu());
     * ope.talleta("testiLukuKirjoitus");
     * Nimeaja ope2 = new Nimeaja("testiLukuKirjoitus", 3);
     * ope2.lueTiedosto(ope2.GetTiedosto());
     * ope2.talleta(ope2.GetTiedosto());
     * ope2.lueTiedosto(ope2.GetTiedosto());
     * ope.GetJuuri().GetKaynnit() ~~~ 5;
     * </pre>
     * </example>
     */
    public void talleta(String nimi) {
        try (PrintStream fo = new PrintStream(new FileOutputStream(nimi, false))){
            fo.println(GetTrie().ToString());
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * opettaa tideostoon tietyn tasoisen Trie-rakenteen
     * @param taso minkä tasoinen puu tehdään
     * @param kieli Millä kielellä puu tehdään
     */
    public void Opi(int taso, String kieli) {
		Opetus ope = new Opetus(taso, kieli);
		if (kieli.equals("Fi")) ope.lueTiedosto("Suomi", taso);
		if (kieli.equals("En")) ope.lueTiedosto("English", taso);
		ope.talleta(kieli);
    }


	/**
	 * Testailua, että kaikki toimii
	 * @param Args args ei käytetä
	 */
	public static void main(String[] Args) {
		boolean jatketaanko = true;
		boolean oikea = true;
		System.out.println("Kielivlinta Suomi, kirjoita: Fi");
		System.out.println("Choose language English, Write: En");
		Scanner scan = new Scanner(System.in);
		String kieli = scan.next();
		kieli = kieli.substring(0,1).toUpperCase() + kieli.substring(1);
		if (!kieli.equals("Fi") && !kieli.equals("En")) {
			System.out.println("Tarkista kirjoitusvirheet");
			System.out.println("Check for spelling mistakes");
			return;
		}
		
		while (jatketaanko) {
			oikea = true;
			if (kieli.equals("Fi")) System.out.println("Minkä pituisia nimiä haluat? Anna kirjainten määrä positiivisena kokonaislukuna");
			if (kieli.equals("En")) System.out.println("How long names do you want? Give the number of characters as a positive whole number");
			String pituusS = scan.next();
			int pituus = Integer.valueOf(pituusS);
			if (pituus > 1) {
				while (oikea) {
					if (kieli.equals("Fi")) System.out.println("Kuinka piykän markovin ketjun haluat? Anna positiivinen kokonaisluku (Suositellaan 3 tai 4)");
					if (kieli.equals("En")) System.out.println("How long markov chan do you want? give a positive whole number (4 or 5 is recommended)");
					String tasoS = scan.next();
					int taso = Integer.valueOf(tasoS);
					if (taso > 1) {
						oikea = false;
						Nimeaja n = new Nimeaja();
						n.Opi(taso, kieli);
						n.SetTrie(new Trie(taso));
						n.SetTrie(n.lueTiedosto(kieli));
						for (int i = 0; i < 20; i++) {
							System.out.println(n.LuoUusi(pituus, taso));
						}
						if (kieli.equals("Fi")) System.out.println("Haluatko lisää nimiä? Y/N");
						if (kieli.equals("En")) System.out.println("Do you want more names? Y/N");
						String jatketaan = scan.next();
						if (jatketaan.equalsIgnoreCase("N")) jatketaanko = false;
					}
				}
			}
		}
	}
	
}









