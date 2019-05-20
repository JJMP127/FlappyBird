package Handlers;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Music {

	private File audioFile;
	private Thread curJumpThread;
	
	public void playJumpSound() {
		
		this.playSound("jump.wav", -20);
	}
	
	public void playAddPoint() {
		
		this.playSound("point.wav", -20);
	}
	
	public void playCrash() {
		
		this.playSound("crash.wav", -20);
	}
	
	public void playBackground() {
		
		this.playSound("background.wav", -25);
	}
	
	private synchronized void playSound(String path, float decreaseVol) {

		audioFile = new File("Resources/Sounds/" + path);

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
