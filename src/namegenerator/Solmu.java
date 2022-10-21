package namegenerator;

import java.util.ArrayList;
import java.util.List;

public class Solmu {
	private Solmu parent; //Solmun vanhempi
	private Solmu[] lapset;
	private int lapsia;
	private String nimi;
	private int kaynnit;
	private boolean juuri;
	private int id=0;
	private static int seuraavaID=0;
	
	/**
	 *Solmun alustus 
	 *
	 * @param nimi minä nimi solmulle annetaan (kirjain, jota solmu vastaa)
	 * @param parent kuka solmun vanhempi on
	 */
	public Solmu(String nimi, Solmu parent) {
		this.parent = parent;
		this.parent.SetLapsi(this);
		this.nimi = nimi;
		lapset = new Solmu[5];
		this.juuri = false;
        this.id = seuraavaID; 
        seuraavaID = seuraavaID+1;

	}
	
	/**
	 * Solmun alustus, kun ei ole mitään tietoja
	 */
	public Solmu() {
		lapset = new Solmu[5];
		seuraavaID = seuraavaID+1;
	}
	
	/**
	 *Solmun alustus ilman vanhempaa (Juurelle)
	 *
	 * @param nimi minä nimi solmulle annetaan (kirjain, jota solmu vastaa)
	 * @param parent kuka solmun vanhempi on
	 */
	public Solmu(String nimi) {
		this.nimi = nimi;
		lapset = new Solmu[5];
		this.juuri = true;
        this.id = seuraavaID; 
        seuraavaID++;
	}
	
	/**
	 * Hakee listan solmun lapsista
	 * @param merkki minkä kirjaimen nimimen lapsi halutaan löytää
	 * @return palauttaa lapsen, jos sellainen löytyy, jos ei, se palauttaa nullin
	 */
	public Solmu GetLapsiS(String merkki) {
		for (int i = 0; i<lapsia; i++) {
			if(lapset[i].GetNimi().equals(merkki)) return lapset[i];
		}
		return null;
	}
	
	/**
	 * Palauttaa solmun id:n
	 * @return solmun id
	 */
	public int GetID() {
		return id;
	}
	
	/**
	 * asettaa id:n solmulle (Kun luetaan tiedostosta)
	 * @param uusi uusi id
	 */
	public void SetID(int uusi) {
		id=uusi;
	}
	
	/**
	 * palauttaa tiedon, onko juuri juurisolmu
	 * @return onko juuri juurisolmu
	 */
	public boolean OnkoJuuri() {
		return juuri;
	}
	
	/**
	 * asettaa tiedon siitä, onko solmu juurisolmu
	 * @param onko solmu juurisolmu
	 */
	public void OnkoJuuri(boolean onko) {
		juuri=onko;
	}
	
	/**
	 * Asettaa solmulle nime
	 * @param uusi nimi
	 */
	public void SetNimi(String uusi) {
		nimi = uusi;
	}
	
	/**
	 * hakee lapsen kohdassa i
	 * @param i kohta, josta haetaan
	 * @return palauttaa halutun lapsisolmun
	 */
	public Solmu GetLapsi(int i) {
		return lapset[i];
	}
	
	/**
	 * hakee solmun nimen
	 * @return solmun nimi
	 */
	public String GetNimi() {
		return nimi;
	}
	
	/**
	 * hakee kuinka monta kertaa solmussa ollaan käyty
	 * @return Palauttaa käyntikerrat
	 */
	public int GetKaynnit() {
		return kaynnit;
	}
	
	/**
	 * Asettaa uuden määrän käynteja solmulle
	 * @param uusi : uusi määrä käyntejä
	 */
	public void SetKaynnit(int uusi) {
		kaynnit = uusi;
	}
	
	/**
	 * Palauttaa lapsien määrän
	 * @return lapsien määrä
	 */
	public int GetLapsia() {
		return lapsia;
	}
	
	/**
	 * Hakee solmun vanhemman id:n
	 * @return solmun vanhemman id
	 */
	public int GetParentID() {
		return parent.GetID();
	}
	
	/**
	 * Asettaa solmulle vanhemman
	 * @param uusi uusi vanhempi
	 */
	public void SetParent(Solmu uusi) {
		parent = uusi;
	}
	
	/**
	 * Asettaa lasten määrän
	 * @param uusi : uusi lasten määrä
	 */
	public void SetLapsia(int uusi) {
		lapsia = uusi;
	}
	
	/**
	 * Antaa solmulle uuden lapsen
	 * @param uusi : uusi lapsi
	 */
	public void SetLapsi(Solmu uusi) {
		if(lapsia+1==lapset.length) LisaaPaikkoja(uusi);
		else {
			lapset[lapsia] = uusi;
			SetLapsia(GetLapsia()+1);
		}
	}
	

	/**
	 * Lisää paikkoja solmujen taulukkoon
	 * @param uusi : uusi solmu, joka ei mahtunut edelliseen taulukkoon
	 */
	public void LisaaPaikkoja(Solmu uusi) {
		Solmu[] uusilista = new Solmu[lapsia+10];
		for (int i = 0; i < lapsia; i++) {
			uusilista[i] = lapset[i];
		}
		uusilista[lapsia] = uusi;
		lapsia++;
		lapset = uusilista;
	}
	
	/**
	 * Muokkaa solmun tiedot stringiksi
	 * @return solmun tiedot stringinä
	 * <example>
	 * <pre name="test">
	 * Solmu testi = new Solmu();
	 * testi.SetNimi("juuri");
	 * testi.SetKaynnit(5);
	 * testi.SetID(10);
	 * testi.OnkoJuuri(true);
     * System.out.println(testi.ToString().equals("juuri:5:10:true:"));
     * </pre>
     *</example>
	 */
	public String ToString() {
		StringBuilder sb = new StringBuilder(GetNimi()+ ":");
		sb.append(GetKaynnit() + ":"+ GetID() + ":");
		sb.append(OnkoJuuri()+ ":");
		if (!OnkoJuuri()) sb.append(GetParentID()+":");
		for (int i = 0; i < GetLapsia(); i++) {
			sb.append(GetLapsi(i).GetID()+":");
		}
		return sb.toString();
	}
	
	/**
	 * tekee annetusta stringistä solmun
	 * @param solmut se string josta solmu tehdään
	 * @param puu.GetPuu() puu johon solmu liitetään
	 * @return puu, johon uusi solmu on lisärry kokoon
	 * <example>
	 * <pre name="test">
	 * Trie puu = new Trie(3);
	 * Solmu uusi = new Solmu();
	 * String rimpsu = "juuri:5:10:true:";
	 * uusi.Parse(rimpsu, puu);
     * uusi.GetID() ~~~ 10;
     * </pre>
     *</example>
	 */
	public Trie Parse(String solmut, Trie puu) {
		String[] tiedot = solmut.split(":");
		this.SetNimi(tiedot[0]);
		this.SetKaynnit(Integer.parseInt(tiedot[1]));
		this.SetID(Integer.parseInt(tiedot[2]));
		this.OnkoJuuri(Boolean.parseBoolean(tiedot[3]));
		if (OnkoJuuri()) {
			puu.GetTree().add(this); 
			puu.SetRoot(this);
		}
		else {
			for (Solmu solmu : puu.GetTree()){
				if (solmu.GetID()==Integer.parseInt(tiedot[4])) {
					solmu.SetLapsi(this);
					this.SetParent(solmu);
					puu.GetTree().add(this);
					return puu;
				}
			}
		}
		return puu;
	}
	
	/**
	 * Maini, testaillaan luokan toimintaa
	 * @param Arg
	 */
	@SuppressWarnings("unused")
	public static void main(String[] Arg) {
		  Trie puu = new Trie(3);
		  Solmu uusi = new Solmu();
		  String rimpsu = "juuri:5:10:true:";
		  uusi.Parse(rimpsu, puu);
	      System.out.println(uusi.GetID() == 10);
	}

}
