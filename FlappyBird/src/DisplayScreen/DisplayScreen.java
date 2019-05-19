package DisplayScreen;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

import Entities.Pipe;
import Handlers.Handler;

public class DisplayScreen extends JComponent{
	
	Handler handler;
	
	public DisplayScreen(Handler handler) {
		
		this.handler = handler;
	}
	
	public void paintComponent(Graphics g) {	

		g.setColor(Color.BLUE);
		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		
		this.handler.getGame().getPlayer().draw(g);
		
		for(Pipe p : this.handler.getGame().getPipes())
			p.draw(g);
		
		this.handler.getGame().getScoreHandler().drawScore(g);
	}
}
