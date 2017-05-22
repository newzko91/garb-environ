package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import gfx.Assets;
import gfx.Text;
import input.MouseManager;
import main.Window;
import sokoban.Level;
import ui.Button;
import ui.Click;

public class LevelSelectorState extends State{
	private final int DOUBLETILESIZE = 64;
	private Level[] levels = new Level[5];
	private final int xOffset = (Window.WIDTH - DOUBLETILESIZE*5)/2;
	private final int yOffset = (Window.HEIGHT - DOUBLETILESIZE*3)-100;
	
	private Button back;
	
	public LevelSelectorState(Window window) {
		super(window);
		
		for(int i = 0; i < levels.length; i++)
			levels[i] = loadLevel("/levels/"+i+".txt");
		
		back = new Button("VOLTAR", Window.WIDTH/2, Window.HEIGHT - 100, new Click(){

			@Override
			public void onClick() {
				Assets.tema.stop();
				Assets.intro.play_more();
				State.currentState = window.getMenuState();
			}
			
		}, Assets.font30);
		
		
	}
	
	private Level loadLevel(String path){
		
		String file = loadFileAsString(path);
		String[] numbers = file.split("\\s+");
		int cols = parseInt(numbers[0]);
		int rows = parseInt(numbers[1]);
		int player_col = parseInt(numbers[2]);
		int player_row = parseInt(numbers[3]);
		int[][] maze = new int[rows][cols];
		for(int row = 0; row < rows; row++)
			for(int col = 0; col < cols; col++)
				maze[row][col] = parseInt(numbers[(col + (row*cols)) + 4]); //o +4 pular os itens de LxA e posicao jogador
		
		return new Level(maze, player_row, player_col, this);
	}
	
	public static String loadFileAsString(String path){
		StringBuilder builder = new StringBuilder();
		try{
		InputStream in = LevelSelectorState.class.getResourceAsStream(path);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
		String line;
		while((line = br.readLine()) != null){
			builder.append(line+ "\n");
		}
		br.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return builder.toString();
	}
	
	public static int parseInt(String numero){
		try{
			return Integer.parseInt(numero);
		}catch(NumberFormatException e){
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public void update(){
		back.update();
	}
	@Override
	public void render(Graphics g){
		back.render(g);
		int counter = 1;
		g.setFont(Assets.font30);
		
		Rectangle bounds0 = new Rectangle(xOffset,
				yOffset + DOUBLETILESIZE, DOUBLETILESIZE, DOUBLETILESIZE);
		
		Rectangle bounds1 = new Rectangle(xOffset + DOUBLETILESIZE,
				yOffset + DOUBLETILESIZE, DOUBLETILESIZE, DOUBLETILESIZE);
		
		Rectangle bounds2 = new Rectangle(xOffset + 2*DOUBLETILESIZE,
				yOffset + DOUBLETILESIZE, DOUBLETILESIZE, DOUBLETILESIZE);
		
		Rectangle bounds3 = new Rectangle(xOffset + 3*DOUBLETILESIZE,
				yOffset + DOUBLETILESIZE, DOUBLETILESIZE, DOUBLETILESIZE);
		
		Rectangle bounds4 = new Rectangle(xOffset + 4*DOUBLETILESIZE,
				yOffset + DOUBLETILESIZE, DOUBLETILESIZE, DOUBLETILESIZE);
		
		//Nive 1
		if(bounds0.contains(MouseManager.x, MouseManager.y)){
			if(MouseManager.left && levels[0].isSolved()){
				((GameState)window.getGameState()).setLevel(levels[counter-1]);
				State.currentState = window.getGameState();
			}
			g.drawImage(Assets.outline2, bounds0.x, bounds0.y, null);
			if(levels[0].isSolved())
				Text.drawString(g, 1+"", xOffset + DOUBLETILESIZE/2,
						yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.YELLOW);
			else
				Text.drawString(g,"X", xOffset + DOUBLETILESIZE/2,
						yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.YELLOW);
		}else{
			g.drawImage(Assets.outline, bounds0.x, bounds0.y, null);
			if(levels[0].isSolved())
				Text.drawString(g, 1+"", xOffset + DOUBLETILESIZE/2,
						yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.GREEN);
				else
					Text.drawString(g,"X", xOffset + DOUBLETILESIZE/2,
							yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.WHITE);
		}
		
		//Nivel 2
		if(bounds1.contains(MouseManager.x, MouseManager.y)){
			if(MouseManager.left && levels[1].isSolved()){
				((GameState)window.getGameState()).setLevel(levels[1]);
				State.currentState = window.getGameState();
			}
			g.drawImage(Assets.outline2, bounds1.x, bounds1.y, null);
			if(levels[1].isSolved())
				Text.drawString(g, 2+"", xOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE,
						yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.YELLOW);
				  else
					Text.drawString(g,"X", xOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE,
							yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.YELLOW);
			} else{
				g.drawImage(Assets.outline, bounds1.x, bounds1.y, null);
				if(levels[1].isSolved())
					Text.drawString(g, 2+"", xOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE,
						yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.GREEN);
				else
					Text.drawString(g,"X", xOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE,
							yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.WHITE);
		}
		
		//Nivel 3
		if(bounds2.contains(MouseManager.x, MouseManager.y)){
			if(MouseManager.left && levels[2].isSolved()){
				((GameState)window.getGameState()).setLevel(levels[2]);
				State.currentState = window.getGameState();
			}
			g.drawImage(Assets.outline2, bounds2.x, bounds2.y, null);
			if(levels[2].isSolved())
				Text.drawString(g, 3+"", xOffset + DOUBLETILESIZE/2 + 2*DOUBLETILESIZE,
					yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.YELLOW);
			  else
				Text.drawString(g,"X", xOffset + DOUBLETILESIZE/2 + 2*DOUBLETILESIZE,
						yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.YELLOW);
		} else{
			g.drawImage(Assets.outline, bounds2.x, bounds2.y, null);
			if(levels[2].isSolved())
				Text.drawString(g, 3+"", xOffset + DOUBLETILESIZE/2 + 2*DOUBLETILESIZE,
					yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.GREEN);
			else
				Text.drawString(g,"X", xOffset + DOUBLETILESIZE/2 + 2*DOUBLETILESIZE,
						yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.WHITE);
		}
		
		//Nivel 4
		if(bounds3.contains(MouseManager.x, MouseManager.y)){
			if(MouseManager.left && levels[3].isSolved()){
				((GameState)window.getGameState()).setLevel(levels[3]);
				State.currentState = window.getGameState();
			}
			g.drawImage(Assets.outline2, bounds3.x, bounds3.y, null);
			if(levels[3].isSolved())
				Text.drawString(g, 4+"", xOffset + DOUBLETILESIZE/2 + 3*DOUBLETILESIZE,
					yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.YELLOW);
			  else
				Text.drawString(g,"X", xOffset + DOUBLETILESIZE/2 + 3*DOUBLETILESIZE,
						yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.YELLOW);
		} else{
			g.drawImage(Assets.outline, bounds3.x, bounds3.y, null);
			if(levels[3].isSolved())
				Text.drawString(g, 4+"", xOffset + DOUBLETILESIZE/2 + 3*DOUBLETILESIZE,
					yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.GREEN);
			else
				Text.drawString(g,"X", xOffset + DOUBLETILESIZE/2 + 3*DOUBLETILESIZE,
						yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.WHITE);
		}
		
		//Nivel 5
		if(bounds4.contains(MouseManager.x, MouseManager.y)){
			if(MouseManager.left && levels[4].isSolved()){
				((GameState)window.getGameState()).setLevel(levels[4]);
				State.currentState = window.getGameState();
			}
			g.drawImage(Assets.outline2, bounds4.x, bounds4.y, null);
			if(levels[4].isSolved())
				Text.drawString(g, 5+"", xOffset + DOUBLETILESIZE/2 + 4*DOUBLETILESIZE,
					yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.YELLOW);
			  else
				Text.drawString(g,"X", xOffset + DOUBLETILESIZE/2 + 4*DOUBLETILESIZE,
						yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.YELLOW);
		} else{
			g.drawImage(Assets.outline, bounds4.x, bounds4.y, null);
			if(levels[4].isSolved())
				Text.drawString(g, 5+"", xOffset + DOUBLETILESIZE/2 + 4*DOUBLETILESIZE,
					yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.GREEN);
			else
				Text.drawString(g,"X", xOffset + DOUBLETILESIZE/2 + 4*DOUBLETILESIZE,
						yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.WHITE);
		}
		
	}
	
	
	public Level[] getLevels(){return levels;}

}
