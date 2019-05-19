package Handlers;

import java.io.IOException;

import Entities.Pipe;
import Main.Game;
import Saver.Saver;

public class Handler {
	
	Saver saver = new Saver(this);
	ScoreHandler scores = new ScoreHandler(this);
	Music music = new Music();
	Game game = new Game(1000,1000, this, this.scores);
	
	public Game getGame() {
		return this.game;
	}
	
	public Saver getSaver() {
		return this.saver;
	}
	
	public Music getMusic() {
		return this.music;
	}
	
	public void restartGame() throws IOException {
		this.game.setStartGame(false);
		this.getGame().getPlayer().restart();
		for(Pipe p : this.getGame().getPipes())
			p.restart();
		this.game.setScore(0);
		this.scores.verifyHighScore();
	}	
}
