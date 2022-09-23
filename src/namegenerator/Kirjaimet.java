package namegenerator;

public class Kirjaimet {
	private Kirjain [] kirjaimet;
	private Kirjain nykyinen; //tämän hetken "Ensimmäisen" kirjaimen id, eli paikka taulukossa
	private String tiedosto = "kirjaindata";
	
	private Kirjaimet() {
		
	}
	
	public Kirjain GetKirjain(int id) {
		return kirjaimet[id];
	}
	
	public void SetKirjain(Kirjain uusi) {
		kirjaimet[uusi.GetId()] = uusi;
	}
	
	public Kirjain GetNykyinen() {
		return nykyinen;
	}
	
	public void SetNykyinen(Kirjain uusi) {
		nykyinen = uusi;
	}
	
	public int GetId(Kirjain kirjain) {
		return kirjain.GetId();

	}
	
	public void LueTiedosto() {
		//TODO Lue tiedostoa luupissa, luo uusi kirjain ja lisää se kirjaimet-taulukkoon
	}
	
	public void Tallenna() {
		//TODO tallennetaan kirjaimet-taulukon sisältö tiedostoon muuttamalla ne stringeiksi ja kirjoittamalla
	}
}
