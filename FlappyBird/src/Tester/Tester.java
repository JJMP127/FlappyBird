package Tester;

import java.io.IOException;
import java.util.List;
import Handlers.Handler;
import Saver.PlayerID;
import Saver.Saver;

public class Tester {

	static Handler handler = new Handler();

	public static void main(String[] s) throws IOException {

		Saver.SaveScore(200);

		List<PlayerID> list = Saver.getHSRecord();

		for(PlayerID p : list) {
			System.out.println(p.getName() + " .......... " + p.getScore());
		}

		handler.restartGame();

		for(PlayerID p : list) {
			System.out.println(p.getName() + " .......... " + p.getScore());
		}

		handler.restartGame();

		for(PlayerID p : list) {
			System.out.println(p.getName() + " .......... " + p.getScore());
		}
	}

}
