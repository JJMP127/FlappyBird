package Main;

import java.io.IOException;

import Handlers.Handler;

public class Main {
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		Handler handler = new Handler();
		
		handler.getGame().start("Flappy Bird");
	}
}
