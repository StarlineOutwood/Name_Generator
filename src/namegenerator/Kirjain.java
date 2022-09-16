package namegenerator;

public class Kirjain {
	private double [] probability = new double [33]; //Tänne tulee trie-rakenne
	private String kirjain;
	private int id;
	
	public Kirjain(int id, String kirjain) {
		this.id = id;
		this.kirjain = kirjain;
	}
	
	public double GetProbability(int id) {
		return probability[id];
	}
	
	public void SetProbability(int id, double prob) {
		probability[id] = prob;
	}
	
	public String GetKirjain() {
		return kirjain;
	}
	
	public int GetId() {
		return id;
	}
}
