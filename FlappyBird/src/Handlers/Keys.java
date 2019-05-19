package Handlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keys implements KeyListener{

	boolean[] keys;
	boolean[] cantPress;
	boolean[] justPressed;

	public Keys() {

		this.keys = this.createArray(256);
		this.cantPress = this.createArray(256);
		this.justPressed = this.createArray(256);

	}
	
	public void tick() {
		
		for(int i = 0; i < this.keys.length; i++) {
			
			if(this.cantPress[i] && !this.keys[i])
				this.cantPress[i] = false;
			
			else if(this.justPressed[i]) {
				
				this.cantPress[i] = true;
				this.justPressed[i] = false;
			}
			
			if(!this.cantPress[i] && this.keys[i])
				this.justPressed[i] = true;			
		}	
	}

	@Override
	public void keyPressed(KeyEvent e) {

		this.keys[e.getKeyCode()] = true;

	}

	@Override
	public void keyReleased(KeyEvent e) {

		this.keys[e.getKeyCode()] = false;

	}

	public boolean pressedKey(int keyCode) {

		return this.justPressed[keyCode];

	}

	@Override
	public void keyTyped(KeyEvent e) {}

	private boolean[] createArray(int size) {

		boolean[] array = new boolean[size];

		for(int i = 0; i < array.length; i++)
			array[i] = false;

		return array;
	}

}
