package Handlers;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Music {

	private File audioFile;
	
	public void loadSound(String path) {
		
		try {
		audioFile = new File("Resources/" + path);
		AudioInputStream input = AudioSystem.getAudioInputStream(audioFile);  
		Clip clip = AudioSystem.getClip();
	    clip.open(input);
	    clip.start();
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
	}
}
