package DisplayScreen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;

import Backgrounds.Floor;
import Entities.Pipe;
import Handlers.Handler;
import Images.Images;

public class DisplayScreen extends JComponent{
	
	Handler handler;
	
	public DisplayScreen(Handler handler) {
		
		this.handler = handler;
	}
	
	public void paintComponent(Graphics g) {	
		
		Graphics2D g2 = (Graphics2D) g;

		g.drawImage(Images.background, 0, 0, 500, 1000, null);
		g.drawImage(Images.background, 500, 0, 500, 1000, null);
		
		this.handler.getGame().getPlayer().draw(g);
		
		for(Pipe p : this.handler.getGame().getPipes())
			p.draw(g);
		
		this.handler.getGame().getFloor().draw(g);
		
		this.handler.getGame().getScoreHandler().drawScore(g);
		this.handler.getGOver().displayGameOver(g);
		this.handler.getSScreen().displayStartScreen(g);
	}
}
