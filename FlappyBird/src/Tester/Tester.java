package Tester;

import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;

import Saver.PlayerID;
import Saver.Saver;

public class Tester {

	public static void main(String[] s) throws IOException {
		
		Saver.SaveScore(200);
		
		List<PlayerID> list = Saver.getHSRecord();
		
		for(PlayerID p : list) {
			System.out.println(p.getName() + " .......... " + p.getScore());
		}
		
	}
	
}
