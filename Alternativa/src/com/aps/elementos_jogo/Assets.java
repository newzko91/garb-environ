package com.aps.elementos_jogo;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import com.aps.main.Audio;

import javax.imageio.ImageIO;

import com.aps.jogo.Nivel;

public class Assets {
	
	public static Image playerLeft, playerBack, playerRight, PlayerFront;
	public static Image floor, floor2, floor3, floor4,wall, boxOn, boxOff, spot, outline, outline2;
	public static Font tamanho30, tamanho48, tamanho22, tamanho40;
	public static Audio tema, sucesso, movimento;
	
	
	public static void init()
	{
		playerLeft = loadImage("/jogador/left.png").getScaledInstance(Nivel.TILESIZE, Nivel.TILESIZE, BufferedImage.SCALE_DEFAULT);
		playerBack = loadImage("/jogador/back.png").getScaledInstance(Nivel.TILESIZE, Nivel.TILESIZE, BufferedImage.SCALE_DEFAULT);
		PlayerFront = loadImage("/jogador/front.png").getScaledInstance(Nivel.TILESIZE, Nivel.TILESIZE, BufferedImage.SCALE_DEFAULT);
		playerRight = loadImage("/jogador/right.png").getScaledInstance(Nivel.TILESIZE, Nivel.TILESIZE, BufferedImage.SCALE_DEFAULT);
		
		floor = loadImage("/imagens_outras/ground.png").getScaledInstance(Nivel.TILESIZE, Nivel.TILESIZE, BufferedImage.SCALE_DEFAULT);
		floor2 = loadImage("/imagens_outras/ground2.png").getScaledInstance(Nivel.TILESIZE, Nivel.TILESIZE, BufferedImage.SCALE_DEFAULT);
		floor3 = loadImage("/imagens_outras/ground3.png").getScaledInstance(800, 600, BufferedImage.SCALE_SMOOTH);
		floor4 = loadImage("/imagens_outras/ground4.png").getScaledInstance(Nivel.TILESIZE, Nivel.TILESIZE, BufferedImage.SCALE_DEFAULT);
		wall = loadImage("/imagens_outras/redBrick.png").getScaledInstance(Nivel.TILESIZE, Nivel.TILESIZE, BufferedImage.SCALE_DEFAULT);
		boxOn = loadImage("/imagens_outras/boxOn.png").getScaledInstance(Nivel.TILESIZE, Nivel.TILESIZE, BufferedImage.SCALE_DEFAULT);
		boxOff = loadImage("/imagens_outras/boxOff.png").getScaledInstance(Nivel.TILESIZE, Nivel.TILESIZE, BufferedImage.SCALE_DEFAULT);
		spot = loadImage("/imagens_outras/spot.png").getScaledInstance(Nivel.TILESIZE, Nivel.TILESIZE, BufferedImage.SCALE_DEFAULT);
		outline = loadImage("/imagens_outras/outline.png").getScaledInstance(64, 64, BufferedImage.SCALE_DEFAULT);
		outline2 = loadImage("/imagens_outras/outline2.png").getScaledInstance(64, 64, BufferedImage.SCALE_DEFAULT);
		
		tamanho48 = loadFont("res/fontes/SuperMario256.ttf", 48);
		tamanho22 = loadFont("res/fontes/SuperMario256.ttf", 22);
		tamanho30 = loadFont("res/fontes/SuperMario256.ttf", 30);
		tamanho40 = loadFont("res/fontes/SuperMario256.ttf", 30);
		
		tema = new Audio("/audio/theme.wav");
		sucesso = new Audio("/audio/sucesso.wav");
		movimento = new Audio("/audio/movimento.wav");
	
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
