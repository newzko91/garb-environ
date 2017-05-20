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
	
	private Button voltar;
	
	//Construtor 
	public LevelSelectorState(Window window) {
		super(window);
		
		for(int i = 0; i < levels.length; i++)
			levels[i] = loadLevel("/levels/"+i+".txt");
		
		voltar = new Button("VOLTAR", Window.WIDTH/2, Window.HEIGHT - 100, new Click(){

			@Override
			public void onClick() {
				State.currentState = window.getMenuState();
			}
			
		}, Assets.font30);
	}
	
	private Level loadLevel(String path){
		
		String file = loadFileAsString(path);
		String[] numbers = file.split("\\s+"); //quebra o arquivo por espacos e armazena no vetor 
		int cols = parseInt(numbers[0]);
		int rows = parseInt(numbers[1]);
		int player_col = parseInt(numbers[2]);
		int player_row = parseInt(numbers[3]);
		int[][] maze = new int[rows][cols];
		for(int row = 0; row < rows; row++)
			for(int col = 0; col < cols; col++)
				maze[row][col] = parseInt(numbers[(col + (row*cols)) + 4]);
		
		return new Level(maze, player_row, player_col, this);
	}
	
	//percorre o arquivo txt
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
		voltar.update();
	}
	
	@Override
	public void render(Graphics g){
		voltar.render(g);
		int counter = 1;
		g.setFont(Assets.font30);
		
			for(int i = 0; i < 5; i++){
				Rectangle bounds = new Rectangle(xOffset + i*DOUBLETILESIZE,
						yOffset + DOUBLETILESIZE, DOUBLETILESIZE, DOUBLETILESIZE);
				if(bounds.contains(MouseManager.x, MouseManager.y)){
					if(MouseManager.left && levels[counter-1].isSolved()){
						((GameState)window.getGameState()).setLevel(levels[counter-1]);
						State.currentState = window.getGameState();
					}
					g.drawImage(Assets.outline2, bounds.x, bounds.y, null);
					if(levels[counter-1].isSolved())
						Text.drawString(g, counter+"", xOffset + DOUBLETILESIZE/2 + i*DOUBLETILESIZE,
							yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.RED);
					else
						Text.drawString(g,"?", xOffset + DOUBLETILESIZE/2 + i*DOUBLETILESIZE,
								yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.RED);
				}else{
					g.drawImage(Assets.outline, bounds.x, bounds.y, null);
					if(levels[counter-1].isSolved())
						Text.drawString(g, counter+"", xOffset + DOUBLETILESIZE/2 + i*DOUBLETILESIZE,
							yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.GREEN);
					else
						Text.drawString(g,"?", xOffset + DOUBLETILESIZE/2 + i*DOUBLETILESIZE,
								yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.BLUE);
				}
				counter ++;
			}	
	}
	
	public Level[] getLevels(){return levels;}
}
