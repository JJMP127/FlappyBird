package Handlers;

import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;

import Saver.PlayerID;
import Saver.Saver;

public class ScoreHandler {

	Handler handler;
	private int score;
	private int highScore;
	private PlayerID[] HSList;

	public ScoreHandler(Handler handler) {

		this.handler = handler;
		HSList = new PlayerID[5];
	}

	public void verifyHighScore() throws IOException {

		this.handler.getSaver();
		List<PlayerID> list = Saver.getHSRecord();

		int limit = (this.getHSList().length < list.size()) ? this.getHSList().length : list.size();

		for(int i = 0; i < limit; i++)
			this.HSList[i] = list.get(i);

		System.out.println("\nTop 5 High Scores:\n");

		for(int i = 0; i < limit; i++)
			System.out.println(this.getHSList()[i].getName() + " .......... " + this.getHSList()[i].getScore());
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

	public PlayerID[] getHSList() {
		return HSList;
	}

	public void setHSList(PlayerID[] hSList) {
		HSList = hSList;
	}
}
