package Images;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Images {

	private BufferedImage spriteSheet;
	public static BufferedImage bird;
	public static BufferedImage birdTiltedUp;
	public static BufferedImage birdTiltedDown;
	public static BufferedImage lowerPipe;
	public static BufferedImage upperPipe;
	public static BufferedImage background;
	public static BufferedImage floor;
	public static BufferedImage scoreBoard;
	
	public static BufferedImage title;
	public static BufferedImage instructions;
	public static BufferedImage icon;

	public Images() {

		try {
			spriteSheet = ImageIO.read(getClass().getResourceAsStream("/Images/FlappyBird.png"));
			
			icon = ImageIO.read(getClass().getResourceAsStream("/Images/FlappyIcon.png"));

			background = spriteSheet.getSubimage(0, 0, 275, 512);

			lowerPipe = spriteSheet.getSubimage(168, 647, 52, 327);

			upperPipe = spriteSheet.getSubimage(112, 642, 52, 324);

			bird = spriteSheet.getSubimage(63, 980, 34, 25);
			
			birdTiltedUp = spriteSheet.getSubimage(120, 984, 34, 30);
			
			birdTiltedDown = spriteSheet.getSubimage(7, 980, 32, 32);
			
			scoreBoard = spriteSheet.getSubimage(321, 513, 227, 336);
			
			floor = spriteSheet.getSubimage(580, 0, 340, 80);
			
			title = spriteSheet.getSubimage(700, 175, 180, 60);
			
			instructions = spriteSheet.getSubimage(580, 175, 120, 140);


		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
