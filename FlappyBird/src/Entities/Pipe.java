package Entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import Handlers.Handler;

public class Pipe {

	Handler handler;
	
	private int xPos;
	private int yPosLower;
	private int yPosUpper;
	private int width;
	private int heightLower;
	private int heightUpper;
	private final int SPEED = 3;
	private int respawn = 1850;
	private Rectangle LowerPipe = new Rectangle();
	private Rectangle UpperPipe = new Rectangle();
	private Random rand = new Random();
	
	private int OGPosition;
	private Rectangle scoreUpdater;
	private int movedRect;
	private int movedRectSize;

	public Pipe(int x, Handler handler) {

		this.handler = handler;
		this.OGPosition = x;
		this.xPos = x;
		this.yPosLower = this.rand.nextInt(700) + 250;
		this.yPosUpper = 0;
		this.width = 200;
		this.heightLower = 1000 - this.yPosLower;
		this.heightUpper = this.getyPosLower() - 220;
		this.movedRect = this.getHeightUpper();
		this.movedRectSize = 220;
	}
	
	public void tick() {
		
		this.setxPos(this.getxPos() - this.SPEED);	
		
		this.respawn();
		
		if(this.handler.getGame().getPlayer().getPlayer().intersects(this.getScoreUpdater())) {
			this.setMovedRect(0);
			this.setMovedRectSize(0);
			
			this.handler.getGame().setScore(this.handler.getGame().getScore() + 1);
		}
	}
	
	public void draw(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		
		this.LowerPipe = new Rectangle(this.getxPos(), this.getyPosLower(), this.getWidth(), this.getHeightLower());
		this.UpperPipe = new Rectangle(this.getxPos(), this.getyPosUpper(), this.getWidth(), this.getHeightUpper());
		
		this.scoreUpdater = new Rectangle(this.getxPos() + this.getWidth(), this.getMovedRect(), 20, this.getMovedRectSize());
		
		g2.setColor(Color.GREEN);
		g2.fill(this.LowerPipe);
		g2.fill(this.UpperPipe);
		
		g2.setColor(Color.BLACK);
		g2.draw(this.LowerPipe);
		g2.draw(this.UpperPipe);	
		g2.draw(this.scoreUpdater);
	}
	
	public void respawn() {
		
		if(this.getxPos() + this.getWidth() <= 0) {
			
			this.setxPos(this.respawn - this.getWidth());
			this.setyPosLower(this.rand.nextInt(700) + 250);
			this.setHeightLower(this.handler.getGame().getHeight() - this.yPosLower);
			this.setHeightUpper(this.getyPosLower() - 220);
			this.scoreUpdater.setBounds(this.getxPos() + this.getWidth(), this.getHeightUpper(), 20, 220);
			this.setMovedRect(this.getHeightUpper());
			this.setMovedRectSize(220);
			
		}
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPosLower() {
		return yPosLower;
	}

	public void setyPosLower(int yPosLower) {
		this.yPosLower = yPosLower;
	}

	public int getyPosUpper() {
		return yPosUpper;
	}

	public void setyPosUpper(int yPosUpper) {
		this.yPosUpper = yPosUpper;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeightLower() {
		return heightLower;
	}

	public void setHeightLower(int heightLower) {
		this.heightLower = heightLower;
	}

	public int getHeightUpper() {
		return heightUpper;
	}

	public void setHeightUpper(int heightUpper) {
		this.heightUpper = heightUpper;
	}

	public Rectangle getLowerPipe() {
		return LowerPipe;
	}

	public void setLowerPipe(Rectangle lowerPipe) {
		LowerPipe = lowerPipe;
	}

	public Rectangle getUpperPipe() {
		return UpperPipe;
	}

	public void setUpperPipe(Rectangle upperPipe) {
		UpperPipe = upperPipe;
	}
	
	public int getOGPosition() {
		return this.OGPosition;
	}
	
	public int getMovedRect() {
		return this.movedRect;
	}
	
	public void setMovedRect(int m) {
		this.movedRect = m;
	}
	
	public int getMovedRectSize() {
		return this.movedRectSize;
	}
	
	public void setMovedRectSize(int m) {
		this.movedRectSize = m;
	}
	
	public Rectangle getScoreUpdater() {
		return this.scoreUpdater;
	}
	
	public void restart() {
		for(Pipe p : this.handler.getGame().getPipes())
			p.setxPos(p.getOGPosition());
	}

}
