package Saver;

import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.util.*;
import java.util.Map.Entry;

import Handlers.Handler;

public class Saver {

	static Handler handler;
	private static String fileName = "HighScore.txt";
	public static PlayerID currPlayer;

	public Saver(Handler handler) {

		Saver.handler = handler;
	}

	public static void SaveScore(int score) throws IOException {

		String path = fileName;

		try {
			Paths.get(path);
		}
		catch(Exception e) {

			File file = new File(path);
			System.out.println("File " + path + " not found. New file created.");
		}

		String savedScore = handler.getGame().getName() + ":" + score;

		editHSRecord(savedScore);

		if(score > currPlayer.getScore())
			System.out.println("\nNew High Score by " + handler.getGame().getName() + ": " + score + ". Saved in file HighScore.txt");
	}

	public static List<PlayerID> getHSRecord() throws IOException{
		
		List<String> list = getHSRecordHelper();

		List<PlayerID> convertedIDs = new LinkedList<>();

		for(String s : list) {

			String[] str = s.split(":");

			int score = Integer.parseInt(str[1]);

			String name = str[0];

			PlayerID newPlayer = new PlayerID(score, name);

			convertedIDs.add(newPlayer);
		}

		Collections.sort(convertedIDs, new MyComp<Integer>());

		List<PlayerID> result = new LinkedList<>();

		for(PlayerID p : convertedIDs) {

			boolean contains = false;

			String name = p.getName();

			for(PlayerID p2 : result) {

				if(p2.getName().equals(name))
					contains = true;
			}

			if(!contains)
				result.add(p);
		}

		return result;
	}

	private static void editHSRecord(String newScore) throws IOException {

		String path = fileName;

		BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));

		writer.write(newScore);
		writer.newLine();

		writer.close();		
	}

	private static List<String> getHSRecordHelper() throws IOException{

		List<String> list = new LinkedList<>();

		String line;

		String path = fileName;

		try {

			FileInputStream input = new FileInputStream(path);

			InputStreamReader inputReader = new InputStreamReader(input);

			BufferedReader reader = new BufferedReader(inputReader);

			while((line = reader.readLine()) != null) {

				list.add(line);
			}
		}
		catch(Exception e) {

			System.out.println("No high scores saved yet. Start playing to begin recording high scores.");

			return list;
		}

		return list;
	}

	public static PlayerID getCurrPlayer() {
		
		try {
			for(PlayerID p : getHSRecord()) {

				if(handler.getGame().getName().equals(p.getName()))
					return p;
			}
		}
		catch(Exception e) {}

		return new PlayerID(0, handler.getGame().getName());
	}
}
