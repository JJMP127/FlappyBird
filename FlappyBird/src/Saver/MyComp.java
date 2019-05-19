package Saver;

import java.util.Comparator;

public class MyComp<Integer> implements Comparator<PlayerID>{

	@Override
	public int compare(PlayerID p1, PlayerID p2) {
		
		return p2.getScore() - p1.getScore();
	}
}
