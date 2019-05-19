package Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import Handlers.Handler;
import Saver.Saver;

public class Player {

	Handler handler;

	private int xPos;
	private double yPos;
	private double velY;
	private int size;
	private boolean falling = true;
	private boolean jumping = false;
	private double GRAVITY = 0.1;
	private Rectangle2D.Double player = new Rectangle2D.Double();
	private boolean lost = false;

	public Player(Handler handler) {

		this.handler = handler;
		this.xPos = 150;
		this.yPos = 475;
		this.velY = 0;
		this.size = 40;
	}

	public void jump() {

		this.jumping = true;
		this.falling = false;
		this.setVelY(4.0);
	}

	public void gravityEffect() {

		if(this.falling) {
			this.yPos = (int) (this.yPos + this.velY);
			this.velY += this.GRAVITY;
		}
	}

	public void tick() throws IOException {

		if(jumping) this.GRAVITY = 0.09;
		else this.GRAVITY = 0.05;

		this.gravityEffect();

		this.move();
		this.checkCollision();

		if(this.jumping && this.getVelY() <= 0) {

			this.jumping = false;
			this.falling = true;
		}

		else if(this.jumping) {

			this.setVelY( this.getVelY() - this.getGravity());
			this.setyPos((int) (this.getyPos() - this.getVelY()));
		}

		if(this.falling) {

			this.setyPos((int) (this.getyPos() + this.getVelY()));
			this.setVelY(this.getVelY() + this.getGravity());
		}
	}

	public void draw(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		Ellipse2D.Double bird = new Ellipse2D.Double(this.getxPos(), this.getyPos(), this.getSize() + 10, this.getSize() + 10);

		this.player = new Rectangle2D.Double(this.getxPos() + 5, this.getyPos() + 5, this.getSize(), this.getSize());

		g2.setColor(Color.WHITE);
		g2.draw(player);

		g2.setColor(Color.YELLOW);
		g2.fill(bird);
		g2.setColor(Color.BLACK);
		g2.draw(bird);
	}

	public void move() {

		if(this.handler.getGame().getKeys().pressedKey(KeyEvent.VK_SPACE))  this.jump();
	}

	public void checkCollision() throws IOException {

		for(Pipe p : this.handler.getGame().getPipes()) {
			if(this.getPlayer().intersects(p.getLowerPipe()) || this.player.intersects(p.getUpperPipe())) {

				this.handler.getSaver().SaveScore(this.handler.getGame().getScore());

				this.handler.getGame().setStartGame(false);
				this.handler.restartGame();
			}
		}
	}

	public Rectangle getPlayer() {
		return player.getBounds();
	}

	public int getxPos() {
		return xPos;
	}

	public double getyPos() {
		return yPos;
	}

	private void setyPos(double d) {
		this.yPos = d;
	}

	public int getSize() {
		return size;
	}

	public double getVelY() {		
		return this.velY;
	}

	private void setVelY(double d) {
		this.velY = d;
	}

	public double getGravity() {
		return GRAVITY;
	}

	public boolean getStatus() {

		return lost;
	}

	public void restart() {
		this.xPos = 150;
		this.yPos = 475;
	}
}
