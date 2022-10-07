package namegenerator;

<<<<<<< HEAD
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
=======
import java.util.Random;
>>>>>>> 6f930749f0f6c5dd59502707d4786aa8a6c4b302

public class Nimeaja {
	private StringBuilder nimi;
	private Solmu juuri;
	private Solmu nyt;
	private int sanaryhmia;
	private String tiedosto;
<<<<<<< HEAD
	private List<Solmu> puu;
	
	/**
	 * muodostetaan niemäjä, jos meillä on jo tiedostossa valmiita tietoja
	 * @param nimi tiedoston nimi
	 */
	public Nimeaja(String nimi) {
		this.puu = new ArrayList<Solmu>();
		this.tiedosto = nimi;
		lueTiedosto(tiedosto);
		//Tähän tulee "Lue tämä tiedosto" missä on puun tiedot valmiina, josta voidaan luoda puu
	}
	
	/**
	 * Muodostetaan niemäja default tiedostolla
	 */
	public Nimeaja() {
		this.tiedosto="kirjaindata"; //Default tiedosto
=======
	
	/**
	 * Muodostetaan niemäjä
	 */
	public Nimeaja() {
		//Tähän tulee "Lue tämä tiedosto" missä on puun tiedot valmiina, josta voidaan luoda puu
>>>>>>> 6f930749f0f6c5dd59502707d4786aa8a6c4b302
	}
	
	/**
	 * Palauttaa sanaryhmien määrän (Eli luvun siitä, kuinka monta neljän kirjiamen joukkoa ollaan käsitelty puussa), ei välttämättä tarvitse
	 * @return sanaryhmien määrän
	 */
	public int GetSanaryhmia() {
		return sanaryhmia;
	}
	
<<<<<<< HEAD
	public String GetTiedosto() {
		return tiedosto;
	}
	
	public void SetTiedosto(String uusi) {
		tiedosto=uusi;
	}
	
=======
>>>>>>> 6f930749f0f6c5dd59502707d4786aa8a6c4b302
	/**
	 * Palauttaa tähän asti muodostetun nimen
	 * @return
	 */
	public StringBuilder GetNimi() {
		return nimi;
	}
	
<<<<<<< HEAD
	public void SetPuu(List<Solmu> uusi) {
		puu=uusi;
	}
	
	public List<Solmu> GetPuu(){
		return puu;
	}
	
=======
>>>>>>> 6f930749f0f6c5dd59502707d4786aa8a6c4b302
	/**
	 * Asettaa uuden nimen (kun kirjaimia lisätään
	 * @param uusi : uusi nimi
	 */
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
	
<<<<<<< HEAD
	public void SetJuuri(Solmu uusi) {
		juuri = uusi;
	}
	
	public Solmu GetJuuri() {
		return juuri;
	}
	
=======
>>>>>>> 6f930749f0f6c5dd59502707d4786aa8a6c4b302
	/**
	 * Lisää kirjaimen sanaan
	 * @param nimi : nimi johon lisätään
	 * @param vanhempi : minkä solmun lapsia tarkastellaan
	 */
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
	
    public void lueTiedosto(String nimi) {
<<<<<<< HEAD
    	List<Solmu> puu = new ArrayList<Solmu>();
        SetTiedosto(nimi);
        try (Scanner fi = new Scanner(new FileInputStream(new File(nimi)))) { // Jotta UTF8/ISO-8859 toimii
            while ( fi.hasNext() ) {
                try {
                    String s = fi.nextLine();
                    Solmu uusi = new Solmu();
                    puu =uusi.Parse(s, puu);
                    if (uusi.OnkoJuuri()) SetJuuri(uusi);
                    SetPuu(puu);
=======
        SetTiedostonNimi(nimi);
        try (Scanner fi = new Scanner(new FileInputStream(new File(getTiedostonNimi())))) { // Jotta UTF8/ISO-8859 toimii
            while ( fi.hasNext() ) {
                try {
                    String s = fi.nextLine();
                    ToDo uusi = new ToDo();
                    uusi.parse(s, true);
                    lisaa(uusi);
>>>>>>> 6f930749f0f6c5dd59502707d4786aa8a6c4b302
                } catch (NumberFormatException ex){
                    //
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Tiedosto ei aukea! " + ex.getMessage());
        }
    }
<<<<<<< HEAD
    
    public void talleta(String nimi) {
        try (PrintStream fo = new PrintStream(new FileOutputStream(nimi, false))){
            StringBuilder kaikkiRivit = new StringBuilder();
            for(Solmu solmu : GetPuu()) {
                kaikkiRivit.append(solmu.ToString() + "\n");                
            }
            fo.println(kaikkiRivit);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


	
	public static void main(String[] Args) {
		Nimeaja n = new Nimeaja();
		System.out.println("Nyt alkaa");
		n.lueTiedosto("testiLukuKirjoitus");
		System.out.println(n.GetJuuri().GetNimi());
		n.SetNyt(n.GetJuuri().GetLapsi(0));
		System.out.println(n.GetNyt().GetNimi());
		Solmu uusi = new Solmu("RavenClaw", n.GetJuuri());
		n.GetPuu().add(uusi);
		n.talleta(n.GetTiedosto());
=======

	
	public static void Main(String[] Args) {
		
>>>>>>> 6f930749f0f6c5dd59502707d4786aa8a6c4b302
	}
	
}









