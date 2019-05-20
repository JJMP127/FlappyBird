package Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;
import Images.Images;

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
	private int scoreRectY;
	private int scoreRectSize;

	public Pipe(int x, Handler handler) {

		this.handler = handler;
		this.OGPosition = x;
		this.xPos = x;
		this.yPosLower = this.rand.nextInt(560) + 220;
		this.yPosUpper = this.yPosLower - 1220;
		this.width = 200;
		this.heightLower = 1000;
		this.heightUpper = 1000;
		this.scoreRectY = this.getyPosLower() - 220;
		this.scoreRectSize = 220;
	}

	public void tick() {

		this.setxPos(this.getxPos() - this.SPEED);	
		
		if(this.getxPos() + this.getWidth() <= 0) 
			this.respawn();

		this.addPoint();
	}

	public void draw(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		this.LowerPipe = new Rectangle(this.getxPos(), this.getyPosLower(), this.getWidth(), this.getHeightLower());
		this.UpperPipe = new Rectangle(this.getxPos(), this.getyPosUpper(), this.getWidth(), this.getHeightUpper());
		this.scoreUpdater = new Rectangle(this.getxPos() + this.getWidth(), this.getScoreRectY(), 20, this.getScoreRectSize());

		g2.drawImage(Images.lowerPipe, this.getxPos(), this.getyPosLower(), this.getWidth(), this.getHeightLower(), null);
		g2.drawImage(Images.upperPipe, this.getxPos(), this.getyPosUpper(), this.getWidth(), this.getHeightUpper(), null);
	}

	public void respawn() {

		this.setxPos(this.respawn - this.getWidth());
		this.setyPosLower(this.rand.nextInt(560) + 220);
		this.setyPosUpper(this.getyPosLower() - 1220);
		this.setHeightLower(this.heightLower);
		this.setHeightUpper(this.heightUpper);
		this.setScoreRectSize(220);
		this.setScoreRectY(this.getyPosLower() - 220);
}

public void addPoint() {

	if(this.handler.getGame().getPlayer().getPlayer().intersects(this.getScoreUpdater())) {
		this.setScoreRectY(0);
		this.setScoreRectSize(0);
		this.handler.getMusic().playAddPoint();
		int score = this.handler.getGame().getScore();
		this.handler.getGame().setScore(this.handler.getGame().getScore() + 1);
		
		if(this.handler.getGame().getScore() > score + 1)
			this.handler.getGame().setScore(this.handler.getGame().getScore() - 1);
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

public int getScoreRectY() {
	return this.scoreRectY;
}

public void setScoreRectY(int m) {
	this.scoreRectY = m;
}

public int getScoreRectSize() {
	return this.scoreRectSize;
}

public void setScoreRectSize(int m) {
	this.scoreRectSize = m;
}

public Rectangle getScoreUpdater() {
	return this.scoreUpdater;
}

public void restart() {
	for(Pipe p : this.handler.getGame().getPipes())
		p.setxPos(p.getOGPosition());
}

}
