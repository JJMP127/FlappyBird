package Backgrounds;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import Handlers.Handler;
import Images.Images;

public class Floor {

	Handler handler;
	private int xPos1, xPos2, yPos, SPEED = 3;
	private Rectangle floor1 = new Rectangle();
	private Rectangle floor2 = new Rectangle();
	
	public Floor(int x, int y, Handler handler) {
		
		this.handler = handler;
		
		this.xPos1 = x;
		this.xPos2 = this.xPos1 + 1000;
		this.yPos = y;
	}
	
	public void tick() {
		
		this.setxPos(this.getxPos() - this.SPEED);
		this.setxPos2(this.getxPos2() - this.SPEED);
		
		if(this.getxPos() + this.handler.getGame().getWidth() <= 0) 
			this.respawn1();
		else if(this.getxPos2() + this.handler.getGame().getWidth() <= 0)
			this.respawn2();
	}
	
	public void draw(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		
		this.floor1 = new Rectangle(this.getxPos(), this.getYpos(), this.handler.getGame().getWidth(), 200);
		this.floor2 = new Rectangle(this.getxPos2(), this.getYpos(), this.handler.getGame().getWidth(), 200);
		
		g2.drawImage(Images.floor, this.getxPos(), this.getYpos(), this.handler.getGame().getWidth() + 100, 200, null);
		g2.drawImage(Images.floor, this.getxPos2(), this.getYpos(), this.handler.getGame().getWidth() + 100, 200, null);
	}
	
	private void respawn1() {
		this.setxPos(1000);
	}
	
	private void respawn2() {
		this.setxPos2(1000);
	}

	public int getxPos() {
		return xPos1;
	}

	public void setxPos(int xPos) {
		this.xPos1 = xPos;
	}

	public int getxPos2() {
		return xPos2;
	}

	public void setxPos2(int xPos2) {
		this.xPos2 = xPos2;
	}

	public int getYpos() {
		return yPos;
	}

	public void setYpos(int ypos) {
		this.yPos = ypos;
	}

	public ArrayList<Rectangle> getFloor() {
		
		ArrayList<Rectangle> floor = new ArrayList<>();
		
		floor.add(this.floor1);
		floor.add(this.floor2);
		
		return floor;
	}	
}
