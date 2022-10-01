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
	
	public Solmu() {
		lapset = new Solmu[5];
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
	public Solmu GetLapsi(String merkki) {
		for (int i = 0; i<lapsia; i++) {
			if(lapset[i].GetNimi() == merkki) return lapset[i];
		}
		return null;
	}
	
	public int GetID() {
		return id;
	}
	
	public void SetID(int uusi) {
		id=uusi;
	}
	
	public boolean OnkoJuuri() {
		return juuri;
	}
	
	public void OnkoJuuri(boolean onko) {
		juuri=onko;
	}
	
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
	
	public int GetParentID() {
		return parent.GetID();
	}
	
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
	
	public String ToString() {
		StringBuilder sb = new StringBuilder(GetNimi()+ ":");
		sb.append(GetKaynnit() + ":"+ GetID() + ":");
		sb.append(OnkoJuuri()+ ":");
		if (!OnkoJuuri()) sb.append(GetParentID()+":");
		for (int i = 0; i < lapsia; i++) {
			sb.append(GetLapsi(i).GetID()+":");
		}
		return sb.toString();
	}
	
	public List<Solmu> Parse(String solmut, List<Solmu> puu) {
		String[] tiedot = solmut.split(":");
		this.SetNimi(tiedot[0]);
		this.SetKaynnit(Integer.parseInt(tiedot[1]));
		this.SetID(Integer.parseInt(tiedot[2]));
		this.OnkoJuuri(Boolean.parseBoolean(tiedot[3]));
		if (OnkoJuuri()) puu.add(this);
		else {
			for (Solmu solmu : puu){
				if (solmu.GetID()==Integer.parseInt(tiedot[4])) {
					solmu.SetLapsi(this);
					this.SetParent(solmu);
					puu.add(this);
				}
			}
		}
		return puu;
	}
	
	/**
	 * Maini, testaillaan luokan toimintaa
	 * @param Arg
	 */
	public static void main(String[] Arg) {
		List<Solmu> lista = new ArrayList<Solmu>();
		Solmu juuri = new Solmu("Tuvat");
		lista.add(juuri);
		Solmu lapsi1 = new Solmu("Gryffindor", juuri);
		Solmu lapsi2 = new Solmu("Slytherin", juuri);
		lista.add(lapsi1);
		lista.add(lapsi2);
		
		Solmu oppilas1 = new Solmu("Ron", lapsi1);
		Solmu oppilas2 = new Solmu("Harry", lapsi1);
		Solmu oppilas3 = new Solmu("Hermione", lapsi1);
		Solmu oppilas4 = new Solmu("Neville", lapsi1);
		Solmu oppilas5 = new Solmu("Ginny", lapsi1);
		Solmu oppilas6 = new Solmu("Fred", lapsi1);
		
		Solmu lemmikki1 = new Solmu("Hedwig", oppilas2);
		
		System.out.println(juuri.GetLapsi(0).GetNimi());
		System.out.println(juuri.GetLapsi(1).GetNimi());
		System.out.println(lapsi1.GetLapsi(0).GetNimi());
		System.out.println(lapsi1.GetLapsia());
		System.out.println(lapsi1.GetLapsi(5).GetNimi());
		System.out.println(oppilas1.GetID());
		System.out.println(oppilas2.GetID());
		System.out.println(lapsi1.ToString());
		
		Solmu uusi = new Solmu();
		lista = uusi.Parse("Huffelpuf:0:17:false:0:", lista);
		System.out.println(uusi.GetNimi());
		
		
		
	}

}
