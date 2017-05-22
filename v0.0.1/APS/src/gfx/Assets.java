package gfx;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Audio;

import sokoban.Level;

public class Assets {
	
	public static Image playerLeft, playerBack, playerRight, PlayerFront;
	public static Image floor, floor2, floor3, floor4, wall, wall2, boxOn, boxOff, spot, outline, outline2;
	public static Image info;
	public static Font font22,font30,font48;
	public static Audio tema, sucesso, movimento, intro;
	
	public static void init()
	{
		tema = new Audio("/audio/theme.wav");
		sucesso = new Audio("/audio/sucesso.wav");
		movimento = new Audio("/audio/movimento.wav");
		intro = new Audio("/audio/intro.wav");
		
		playerLeft = loadImage("/player/left.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		playerBack = loadImage("/player/back.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		PlayerFront = loadImage("/player/front.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		playerRight = loadImage("/player/right.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		
		floor = loadImage("/blocks/ground4.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		floor2 = loadImage("/blocks/ground2.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		floor3 = loadImage("/blocks/ground3.png").getScaledInstance(800, 600, BufferedImage.SCALE_SMOOTH);
		floor4 = loadImage("/blocks/ground.png").getScaledInstance(800, 600, BufferedImage.SCALE_SMOOTH);
		wall = loadImage("/blocks/redBrick.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		wall2 = loadImage("/blocks/redBrick2.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		boxOn = loadImage("/blocks/boxOn.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		boxOff = loadImage("/blocks/boxOff.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		spot = loadImage("/blocks/spot.png").getScaledInstance(Level.TILESIZE, Level.TILESIZE, BufferedImage.SCALE_DEFAULT);
		outline = loadImage("/blocks/outline.png").getScaledInstance(64, 64, BufferedImage.SCALE_DEFAULT);
		outline2 = loadImage("/blocks/outline2.png").getScaledInstance(64, 64, BufferedImage.SCALE_DEFAULT);
		info = loadImage("/info/instrucoes.png").getScaledInstance(700, 500, BufferedImage.SCALE_DEFAULT);
		
		font48 = loadFont("res/fonts/SuperMario256.ttf", 48);
		font22 = loadFont("res/fonts/SuperMario256.ttf", 22);
		font30 = loadFont("res/fonts/SuperMario256.ttf", 30);
		
	}
	
	public static BufferedImage loadImage(String path)
	{
		try {
			return ImageIO.read(Assets.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Font loadFont(String path, int size){
		try {
			return Font.createFont(Font.TRUETYPE_FONT, new File(path)).deriveFont(Font.PLAIN, size);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
