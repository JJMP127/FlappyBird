package Main;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import DisplayScreen.DisplayScreen;
import Entities.Pipe;
import Entities.Player;
import Handlers.Handler;
import Handlers.Keys;
import Handlers.ScoreHandler;
import Saver.Saver;

public class Game {

	Handler handler;
	ScoreHandler scores;
	String name;	
	JFrame mainFrame;
	JOptionPane pane;
	public Player player;
	public Pipe pipe1;
	public Pipe pipe2;
	public Pipe pipe3;
	public DisplayScreen display;
	private Keys keys;
	private boolean startGame = false;

	private int width;
	private int height;

	public Game(int width, int height, Handler handler, ScoreHandler scores) {
		
		this.handler = handler;
		this.scores = scores;
		this.name = "";
		this.width = width;
		this.height = height;
		player = new Player(this.handler);
		pipe1 = new Pipe(this.getWidth() - 100, this.handler);
		pipe2 = new Pipe(this.getPipes().get(this.getPipes().indexOf(this.pipe1)).getxPos() + 650, this.handler);
		pipe3 = new Pipe(this.getPipes().get(this.getPipes().indexOf(this.pipe2)).getxPos() + 650, this.handler);
		display = new DisplayScreen(this.handler);
		keys = new Keys();
	}

	public void runner() throws IOException {

		if(this.getKeys().pressedKey(KeyEvent.VK_SPACE)) this.startGame = true;

		if(this.startGame) {

			this.getPlayer().tick();
			for(Pipe p : this.getPipes()) p.tick();
		}

		this.getKeys().tick();
	}	

	@SuppressWarnings("static-access")
	public void start(String name) throws InterruptedException, IOException {
		
		this.scores.verifyHighScore();
		
		pane = new JOptionPane();
		
		this.name = pane.showInputDialog("Enter your name below:" , "Name");
		
		mainFrame = new JFrame(name);

		this.mainFrame.setSize(this.width, this.height);

		this.mainFrame.add(this.getDisplay());

		this.mainFrame.addKeyListener(this.getKeys());

		this.mainFrame.setVisible(true);

		this.mainFrame.setResizable(false);

		this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		while(!this.handler.getGame().getPlayer().getStatus()) {

			this.mainFrame.repaint(); 

			this.handler.getGame().runner();	

			Thread.sleep(7);
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Player getPlayer() {
		return this.player;
	}

	public DisplayScreen getDisplay() {
		return this.display;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Keys getKeys() {
		return this.keys;
	}
	
	public boolean getStartGame() {
		return this.startGame;
	}
	
	public void setStartGame(boolean start) {
		this.startGame = start;
	}

	public ArrayList<Pipe> getPipes(){

		ArrayList<Pipe> list = new ArrayList<>();

		list.add(this.pipe1);
		list.add(this.pipe2);
		list.add(this.pipe3);

		return list;
	}	
	
	public int getScore() {
		return this.scores.getScore();
	}
	
	public void setScore(int s) {
		this.scores.setScore(s);
	}
	
	public int getHighScore() {
		int result = 0;
		try {
			result = this.scores.getHSList()[0].getScore();
		}
		catch(Exception e) {
			
			return 0;
		}
		
		return result;
	}

}