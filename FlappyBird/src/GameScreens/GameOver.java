package GameScreens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.Random;

import Handlers.Handler;
import Images.Images;

public class GameOver {

	Handler handler;
	private int ypos = -700;
	private int aplha = 0;
	private boolean canRestart = false;

	public GameOver(Handler handler) {

		this.handler = handler;
	}

	public void displayGameOver(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		if(this.handler.getGame().getPlayer().getCrashed()) {

			this.ypos += 10;

			try {
				this.handler.getGame().getScoreHandler().verifyHighScore();
			} catch (IOException e) {}

			g2.setColor(new Color(255,255,255, this.aplha +=1));
			g2.fillRect(0, 0, this.handler.getGame().getWidth(), this.handler.getGame().getHeight());
			if(this.aplha >= 125) this.aplha = 125;

			g2.drawImage(Images.scoreBoard, 240, this.ypos - 100, 500, 700, null);

			String[] scores = this.handler.getGame().getScoreHandler().getBoard();

			g2.setFont(new Font("IMPACT", Font.BOLD, 40));
			g2.setColor(Color.DARK_GRAY);
			for(int i = 0, j = 0, z = 325; i < scores.length; i++, j += 65, z = 360) {

				if(Saver.Saver.currPlayer.getName().equals(scores[i].substring(0, Saver.Saver.currPlayer.getName().length())))
					g2.setColor(Color.WHITE);

				else g2.setColor(Color.DARK_GRAY);
				g2.drawString(scores[i], z, j + this.ypos + 55);
			}
			
			int x = 0;
			String scoreDisplayed = String.valueOf(this.handler.getGame().getScore());
			
			if(scoreDisplayed.length() == 1)
				x = 470;
			else if(scoreDisplayed.length() == 2)
				x = 450;
			else x = 430;
			
			g2.setFont(new Font("IMPACT", Font.BOLD, 70));
			g2.setColor(Color.DARK_GRAY);
			g2.drawString(scoreDisplayed, x, 450 + this.ypos + 30);
			g2.setColor(Color.WHITE);
			g2.drawString(scoreDisplayed, x, 450 + this.ypos + 26);
		}

		if(this.ypos >= 200) {
			this.ypos = 200;
			this.canRestart = true;
		}
	}

	public boolean canRestart() {
		return canRestart;
	}

	public void setCanRestart(boolean canRestart) {
		this.canRestart = canRestart;
	}
}
