package sokoban;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import gfx.Assets;
import input.KeyBoard;
import main.Window;
import states.LevelSelectorState;
import states.State;
import ui.Button;
import ui.Click;


public class Level {
	
	public static final int TILESIZE = 48;
	
	private int[][] maze;
	private int[][] copy;
	
	private int player_row, player_col;
	private Image texture;
	private int xOffset, yOffset;
	private int plaStartRow, plaStartCol;
	
	private long time, lastTime;
	private final int DELAY = 150;

	private boolean solved;	
	
	private LevelSelectorState levelSelectorState;
	public static int ID = 0;
	private int id;
	
	private ArrayList<Button> buttons = new ArrayList<Button>();
	
	public Level(int[][] maze, int player_row, int player_col, LevelSelectorState levelSelectorState){
		this.levelSelectorState = levelSelectorState;
		this.maze = maze;
		ID ++;
		id = ID;
		copy = new int[maze.length][maze[0].length];
		for(int row = 0; row < maze.length; row++){
			for(int col = 0; col < maze[row].length; col ++)
				copy[row][col] = maze[row][col];
		plaStartRow = player_row;
		plaStartCol = player_col;
		this.player_row = player_row;
		this.player_col = player_col;
		if(ID == 1)
			solved = true;
		else
			solved = false;
		xOffset = (Window.WIDTH - maze[0].length*TILESIZE)/2;
		yOffset = (Window.HEIGHT - maze.length*TILESIZE)/2;
		texture = Assets.PlayerFront;
		
		buttons.add(new Button("RECOMECAR", Window.WIDTH/2 - 100, Window.HEIGHT - 50, new Click(){

			@Override
			public void onClick() {
				reset();
				
			}},
				Assets.font30));
		
		buttons.add(new Button("VOLTAR", Window.WIDTH/2 + 100, Window.HEIGHT - 50, new Click(){

			public void onClick() {
				State.currentState = levelSelectorState;
				
			}},
				Assets.font30));
		
		time = 0;
		lastTime = System.currentTimeMillis();
		}
	}
	
	private void reset(){
		for(int row = 0; row < maze.length; row++)
			for(int col = 0; col < maze[row].length; col ++)
				maze[row][col] = copy[row][col];
		
		player_row = plaStartRow;
		player_col = plaStartCol;
		texture = Assets.PlayerFront;
	}
	
	
	public void update(){
		time += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(KeyBoard.UP && time > DELAY){
			move(-1, 0);
			texture = Assets.playerBack;
		}
		if(KeyBoard.LEFT && time > DELAY){
			move(0, -1);
			texture = Assets.playerLeft;
		}
		if(KeyBoard.DOWN && time > DELAY){
			move(1, 0);
			texture = Assets.PlayerFront;
		}
		if(KeyBoard.RIGHT && time > DELAY){
			move(0, 1);
			texture = Assets.playerRight;
		}
		
		for(int i = 0; i < buttons.size(); i++)
			buttons.get(i).update();
		
		for(int row = 0; row < maze.length; row++)
			for(int col = 0; col < maze[row].length; col ++)
				if(maze[row][col] == 2)
					return;
		
		levelSelectorState.getLevels()[id].setSolved(true);
		Assets.sucesso.play();
		State.currentState = levelSelectorState;
		
	}
	
	//Ao definir a movimentação do jogador e dos objetos impede que ocupem a mesma posição.
	private void move(int row, int col){
		if(maze[player_row + row][player_col + col] != 1){
			if(maze[player_row + row][player_col + col] == 2 || maze[player_row + row][player_col + col] == 4){
				if(maze[player_row + row*2][player_col + col*2] == 1 ||
						maze[player_row + row*2][player_col + col*2] == 2 ||
						maze[player_row + row*2][player_col + col*2] == 4)
					return;
				if(maze[player_row + row][player_col + col] == 4){
					maze[player_row + row][player_col + col] = 3;			
					if(maze[player_row + row*2][player_col + col*2] == 3)
						maze[player_row + row*2][player_col + col*2] = 4;
					else
						maze[player_row + row*2][player_col + col*2] = 2;
				}else{
					maze[player_row + row][player_col + col] = 0;
					if(maze[player_row + row*2][player_col + col*2] == 3)
						maze[player_row + row*2][player_col + col*2] = 4;
					else
						maze[player_row + row*2][player_col + col*2] = 2;
					
				}
				
				
			}
			player_row += row;
			player_col += col;
		}
		time = 0;
	}
	
	public void render(Graphics g){
		
		for(int i = 0; i < buttons.size(); i++)
			buttons.get(i).render(g);
		
		for(int row = 0; row < maze.length; row++){
			for(int col = 0; col < maze[row].length; col ++){
				g.drawImage(Assets.floor, xOffset + col*TILESIZE, yOffset + row*TILESIZE, null);
				if(maze[row][col] == 1)
					g.drawImage(Assets.wall2, xOffset + col*TILESIZE, yOffset + row*TILESIZE, null);
				if(maze[row][col] == 2)
					g.drawImage(Assets.boxOff, xOffset + col*TILESIZE, yOffset + row*TILESIZE, null);
				if(maze[row][col] == 3)
					g.drawImage(Assets.spot, xOffset + col*TILESIZE, yOffset + row*TILESIZE, null);
				if(maze[row][col] == 4)
					g.drawImage(Assets.boxOn, xOffset + col*TILESIZE, yOffset + row*TILESIZE, null);
			}
		}
		
		g.drawImage(texture, xOffset + player_col*TILESIZE, yOffset + player_row*TILESIZE, null);
		
		
	}
	
	public boolean isSolved(){return solved;}
	public void setSolved(boolean bool){solved = bool;}
}
