package namegenerator;

import java.util.Random;

public class Nimeaja {
	private StringBuilder nimi;
	private Solmu juuri;
	private Solmu nyt;
	private int sanaryhmia;
	private String tiedosto;
	
	/**
	 * Muodostetaan niemäjä
	 */
	public Nimeaja() {
		//Tähän tulee "Lue tämä tiedosto" missä on puun tiedot valmiina, josta voidaan luoda puu
	}
	
	/**
	 * Palauttaa sanaryhmien määrän (Eli luvun siitä, kuinka monta neljän kirjiamen joukkoa ollaan käsitelty puussa), ei välttämättä tarvitse
	 * @return sanaryhmien määrän
	 */
	public int GetSanaryhmia() {
		return sanaryhmia;
	}
	
	/**
	 * Palauttaa tähän asti muodostetun nimen
	 * @return
	 */
	public StringBuilder GetNimi() {
		return nimi;
	}
	
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
        SetTiedostonNimi(nimi);
        try (Scanner fi = new Scanner(new FileInputStream(new File(getTiedostonNimi())))) { // Jotta UTF8/ISO-8859 toimii
            while ( fi.hasNext() ) {
                try {
                    String s = fi.nextLine();
                    ToDo uusi = new ToDo();
                    uusi.parse(s, true);
                    lisaa(uusi);
                } catch (NumberFormatException ex){
                    //
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Tiedosto ei aukea! " + ex.getMessage());
        }
    }

	
	public static void Main(String[] Args) {
		
	}
	
}









