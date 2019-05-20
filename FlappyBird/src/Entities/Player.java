package Entities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import Images.Images;
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
	private boolean crashed = false;
	private double GRAVITY = 0.1;
	private Rectangle2D.Double player = new Rectangle2D.Double();

	public Player(Handler handler) {

		this.handler = handler;
		this.xPos = 150;
		this.yPos = 475;
		this.velY = 0;
		this.size = 70;
	}

	public void jump() {

		if(!this.getCrashed()) {

			this.jumping = true;
			this.falling = false;
			this.setVelY(4.0);

			this.handler.getMusic().playJumpSound();
		}
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

		this.jumpMechanism();
	}

	public void draw(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		if(!this.crashed) {

			if(!this.handler.getGame().getStartGame()) {
				g2.drawImage(Images.bird, (int) this.getxPos(), (int) this.getyPos(),(int) (this.getSize() + 20), (int) (this.getSize() + 5), null);
				this.setyPos(475);
			}

			else {

				if(this.jumping && this.getVelY() > 2)
					g2.drawImage(Images.birdTiltedUp, (int) this.getxPos(), (int) this.getyPos(),(int) (this.getSize() + 20), (int) (this.getSize() + 5), null);

				else if(jumping && this.getVelY() > 0 && this.getVelY() <= 2)
					g2.drawImage(Images.bird, (int) this.getxPos(), (int) this.getyPos(),(int) (this.getSize() + 20), (int) (this.getSize() + 5), null);

				else g2.drawImage(Images.birdTiltedDown, (int) this.getxPos(), (int) this.getyPos(),(int) (this.getSize() + 20), (int) (this.getSize() + 5), null);
			}

			this.player = new Rectangle2D.Double(this.getxPos() + 15, this.getyPos() + 15, this.getSize() - 10, this.getSize() - 10);
		}
	}

	public void move() {

		if(this.handler.getGame().getKeys().pressedKey(KeyEvent.VK_SPACE))  this.jump();
	}

	public void checkCollision() throws IOException {

		for(Pipe p : this.handler.getGame().getPipes()) 
			if(this.getPlayer().intersects(p.getLowerPipe()) || this.player.intersects(p.getUpperPipe())) {

				this.crash();
				Saver.SaveScore(this.handler.getGame().getScore());
			}

		for(Rectangle f : this.handler.getGame().getFloor().getFloor())
			if(this.getPlayer().intersects(f)) {

				this.crash();
				Saver.SaveScore(this.handler.getGame().getScore());
			}
	}

	private void jumpMechanism() {

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

	private void crash() {

		this.falling = false;
		this.setCrashed(true);
		this.player = new Rectangle2D.Double();
		this.handler.getMusic().playCrash();
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

	public boolean getCrashed() {
		return this.crashed;
	}

	public void setCrashed(boolean crash) {
		this.crashed = crash;
	}
}
