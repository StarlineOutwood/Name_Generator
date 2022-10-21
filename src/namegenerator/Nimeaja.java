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
	 */
	public String LuoUusi(int pituus, int taso) {
		SetNimi(new StringBuilder(""));
		SetNyt(GetJuuri());
		KirjaimenLisays(GetNimi(), GetJuuri());
		for(int i = 0; i < taso-1; i++) {
			KirjaimenLisays(GetNimi(), GetNyt());
		}
		for(int i = 1; i < pituus - 2; i++) {
			SetNyt(GetJuuri());
			for (int j = 0; j < taso-1; j++) {
				String now = String.valueOf(GetNimi().charAt(i+j));
				SetNyt(GetNyt().GetLapsiS(now));
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
	 * Testailua, että kaikki toimii
	 * @param Args args ei käytetä
	 */
	public static void main(String[] Args) {
		System.out.println("Kielivlinta Suomi, kirjoita: Suomi");
		System.out.println("Choose language English, Write: English");
		Scanner scan = new Scanner(System.in);
		String kieli = scan.next();
		if (!kieli.equals("Suomi") && !kieli.equals("English")) {
			System.out.println("Kirjoita kielivalinta isolla alkukirjaimella ja tarkista kirjoitusvirheet");
			System.out.println("Write the name with a capital letter and chech for spelling mistakes");
			return;
		}
		int taso = 3;
		Opetus ope = new Opetus(taso, kieli);
		ope.lueTiedosto(kieli, taso);
		ope.talleta("kirjaindata");
		Nimeaja n = new Nimeaja();
		n.SetTrie(new Trie(taso));
		n.SetTrie(n.lueTiedosto("kirjaindata"));
		for (int i = 0; i < 5; i++) {
			System.out.println(n.LuoUusi(5, taso));
			System.out.println(n.LuoUusi(7, taso));
			System.out.println(n.LuoUusi(9, taso));
			System.out.println(n.LuoUusi(8, taso));
		}
	}
	
}









