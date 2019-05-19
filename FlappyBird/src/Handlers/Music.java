package Handlers;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import Main.Main;

public class Music {

	private File audioFile;
	private Thread curJumpThread;
	
	public void playJumpSound() {
		
		this.playSound("jump.wav", 0);
	}
	
	public void playAddPoint() {
		
		this.playSound("point.wav", 0);
	}
	
	public void playCrash() {
		
		this.playSound("crash.wav", 0);
	}
	
	public void playBackground() {
		
		this.playSound("background.wav", -10.0f);
	}
	
	private synchronized void playSound(String path, float decreaseVol) {

		audioFile = new File("Resources/" + path);

		curJumpThread = new Thread(new Runnable() {

			public void run() {

				try {

					Clip clip = AudioSystem.getClip();
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(audioFile);
					clip.open(inputStream);
					FloatControl control =  (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					control.setValue(decreaseVol);
					clip.start(); 
				} catch (Exception e) {}
			}
		});

		curJumpThread.start();
	}
}
