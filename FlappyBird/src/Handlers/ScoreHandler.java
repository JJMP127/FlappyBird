package Handlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Saver.PlayerID;
import Saver.Saver;

public class ScoreHandler {

	Handler handler;
	private int score;
	private int highScore;
	private List<PlayerID> HSList;

	public ScoreHandler(Handler handler) {

		this.handler = handler;
	}

	public void verifyHighScore() throws IOException {

		this.HSList = new ArrayList<>();
		this.handler.getSaver();
		List<PlayerID> list = Saver.getHSRecord();

		int limit = (5 < list.size()) ? 5 : list.size();

		int count = 0;
		for(PlayerID p : list) {
			if(count < limit)
				this.HSList.add(p);
		
			count++;
		}

		System.out.println("\nTop 5 High Scores:\n");

		for(PlayerID p : this.HSList)
			System.out.println(p.getName() + " .......... " + p.getScore());
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int s) {
		this.score = s;
	}

	public int getHighScore() {
		return highScore;
	}

	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}

	public List<PlayerID> getHSList() {
		return HSList;
	}
}
