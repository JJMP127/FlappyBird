package Saver;

public class PlayerID {

	private int score;
	private String name;
	
	public PlayerID(int s, String n) {
		
		this.score = s;
		this.name = n;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
