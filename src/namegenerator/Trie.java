package namegenerator;

import java.util.ArrayList;
import java.util.List;

public class Trie {
	private List<Solmu> kaikki;
	private Solmu root;
	private Solmu now;
	private int taso;
	
	public Trie(int taso) {
		kaikki = new ArrayList<Solmu>();
		this.taso = taso;
	}

	
	/**
	 * Palauttaa sen solmun, jota käsitellään nyt (Eli mikä poistuu käsiteltävästä stringipätkästä seuraavalla kierroksella)
	 * @return
	 */
	public Solmu GetNow() {
		return now;
	}
	
	/**
	 * Asetetaan uusi käsiteltävä solmu
	 * @param uusi : uusi käsiteltävä solmu
	 */
	public void SetNow(Solmu uusi) {
		now = uusi;
	}
	
	public int GetTaso() {
		return taso;
	}
	
	/**
	 * asettaa uuden juuren
	 * @param uusi uusi juuri
	 */
	public void SetRoot(Solmu uusi) {
		root = uusi;
	}
	
	public List<Solmu> GetTree(){
		return kaikki;
	}
	
	public void SetTree(List<Solmu> uusi) {
		kaikki = uusi;
	}
	
	/**
	 * palauttaa juuren
	 * @return juuri
	 */
	public Solmu GetRoot() {
		return root;
	}
	
	public void OpetaMerkkijono(String patka) {
		SetNow(GetRoot());
		GetRoot().SetKaynnit(GetRoot().GetKaynnit() + 1);
		for (int j = 0; j < patka.length();j++) {
			boolean lisatty = false;
			char kirjain = patka.charAt(j);
			if (GetNow().GetLapsia() > 0) {
				for (int i = 0; i < GetNow().GetLapsia(); i++) {
					char vertaus = GetNow().GetLapsi(i).GetNimi().charAt(0);
						if (Character.compare(vertaus, kirjain) == 0) {
							int loppu = GetNow().GetLapsia();
							SetNow(GetNow().GetLapsi(i));
							GetNow().SetKaynnit(GetNow().GetKaynnit() + 1);
							lisatty = true;
							i = loppu;
						}
				}
			}
			if (lisatty == false) {
				Solmu uusi = new Solmu(String.valueOf(kirjain), GetNow());
				SetNow(GetNow().GetLapsiS(String.valueOf(kirjain)));
				GetNow().SetKaynnit(GetNow().GetKaynnit() + 1);
				GetTree().add(uusi);
			}
		}
	}
	
	public String ToString() {
		StringBuilder sb = new StringBuilder();
		for (Solmu solmu : kaikki) {
			sb.append(solmu.ToString() + "\n");
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		
	}
	

}
