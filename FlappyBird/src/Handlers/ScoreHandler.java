package Handlers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Saver.PlayerID;
import Saver.Saver;

public class ScoreHandler {

	Handler handler;
	String[] board;
	private int score;
	private int highScore;
	private List<PlayerID> HSList;

	public ScoreHandler(Handler handler) {

		this.handler = handler;
	}

	public void verifyHighScore() throws IOException {

		StringBuilder builder = new StringBuilder();

		this.HSList = new ArrayList<>();
		List<PlayerID> list = Saver.getHSRecord();

		int limit = (5 < list.size()) ? 5 : list.size();

		int count = 0;
		for(PlayerID p : list) {
			if(count < limit)
				this.HSList.add(p);

			count++;
		}

		builder.append("\nTop 5 High Scores:;");

		for(PlayerID p : this.HSList)
			builder.append(p.getName() + " .......... " + p.getScore() + ";");
		
		board = builder.toString().split(";");			
	}

	public void drawScore(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		if(!this.handler.getGame().getPlayer().getCrashed() && this.handler.getGame().getStartGame()) {
			
			g2.setFont(new Font("IMPACT", Font.BOLD, 100));
			g2.setColor(Color.DARK_GRAY);
			g2.drawString(this.handler.getGame().getScore() + "", this.handler.getGame().getWidth() - this.handler.getGame().getWidth()/2 - 25, this.handler.getGame().getHeight()/7 - 10);
			
			g2.setColor(Color.WHITE);
			g2.drawString(this.handler.getGame().getScore() + "", this.handler.getGame().getWidth() - this.handler.getGame().getWidth()/2 - 25,this.handler.getGame().getHeight()/7 - 20);
		}
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

	public String[] getBoard() {
		return board;
	}

	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}

	public List<PlayerID> getHSList() {
		return HSList;
	}
}
