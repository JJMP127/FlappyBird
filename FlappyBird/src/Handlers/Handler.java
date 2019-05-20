package Handlers;

import java.io.IOException;

import GameScreens.GameOver;
import GameScreens.StartScreen;
import Images.Images;
import Main.Game;
import Saver.Saver;

public class Handler {
	
	Saver saver = new Saver(this);
	ScoreHandler scores = new ScoreHandler(this);
	Music music = new Music();
	GameOver gameOver = new GameOver(this);
	StartScreen startScreen = new StartScreen(this);
	Images images = new Images();
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
	
	public GameOver getGOver() {
		return this.gameOver;
	}
	
	public StartScreen getSScreen() {
		return this.startScreen;
	}
	
	public void restartGame() throws IOException {

		this.getGame().restartGame();
		this.gameOver = new GameOver(this);
		this.startScreen = new StartScreen(this);
		this.scores.verifyHighScore();
	}	
}
