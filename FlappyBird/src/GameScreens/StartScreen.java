package GameScreens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import Images.Images;

import Handlers.Handler;

public class StartScreen {

	Handler handler;

	private int titleWidth = 600, titleHeight = 200;

	public StartScreen(Handler handler) {

		this.handler = handler;
	}

	public void displayStartScreen(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		if(!this.handler.getGame().getStartGame()) {
			
			g2.setColor(new Color(255,255,255, 80));
			g2.fillRect(0, 0, this.handler.getGame().getWidth(), this.handler.getGame().getWidth());

			g2.drawImage(Images.title, this.handler.getGame().getWidth() / 2 - 300, this.handler.getGame().getHeight() / 6, this.titleWidth, this.titleHeight, null);
			
			g2.drawImage(Images.instructions, this.handler.getGame().getWidth() / 2 - 150, this.handler.getGame().getHeight() / 2, 300, 400, null);
			
		}
	}
}
