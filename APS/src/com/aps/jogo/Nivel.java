package com.aps.jogo;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import com.aps.entrada.Teclado;
import com.aps.grafico.Elementos;
import com.aps.main.Tela;
import com.aps.telas.CarregaNivel;
import com.aps.telas.Status;
import com.aps.ui.Botao;
import com.aps.ui.Click;


public class Nivel {
	
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
	
	private CarregaNivel carregaNivel;
	public static int ID = 0;
	private int id;
	
	private ArrayList<Botao> botaos = new ArrayList<Botao>();
	
	public Nivel(int[][] maze, int player_row, int player_col, CarregaNivel carregaNivel){
		this.carregaNivel = carregaNivel;
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
		xOffset = (Tela.WIDTH - maze[0].length*TILESIZE)/2;
		yOffset = (Tela.HEIGHT - maze.length*TILESIZE)/2;
		texture = Elementos.PlayerFront;
		
		botaos.add(new Botao("RECOMECAR", Tela.WIDTH/2 - 100, Tela.HEIGHT - 50, new Click(){

			@Override
			public void onClick() {
				reset();
				
			}},
				Elementos.font30));
		
		botaos.add(new Botao("VOLTAR", Tela.WIDTH/2 + 100, Tela.HEIGHT - 50, new Click(){

			public void onClick() {
				Status.currentState = carregaNivel;
				
			}},
				Elementos.font30));
		
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
		texture = Elementos.PlayerFront;
	}
	
	
	public void update(){
		time += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(Teclado.UP && time > DELAY){
			move(-1, 0);
			texture = Elementos.playerBack;
		}
		if(Teclado.LEFT && time > DELAY){
			move(0, -1);
			texture = Elementos.playerLeft;
		}
		if(Teclado.DOWN && time > DELAY){
			move(1, 0);
			texture = Elementos.PlayerFront;
		}
		if(Teclado.RIGHT && time > DELAY){
			move(0, 1);
			texture = Elementos.playerRight;
		}
		
		for(int i = 0; i < botaos.size(); i++)
			botaos.get(i).update();
		
		for(int row = 0; row < maze.length; row++)
			for(int col = 0; col < maze[row].length; col ++)
				if(maze[row][col] == 2)
					return;
		
		carregaNivel.getLevels()[id].setSolved(true);
		Elementos.success.play();
		Status.currentState = carregaNivel;
		
	}
	
	//Ao definir a movimentação do jogador e dos objetos impede que ocupem a mesma posição.
	private void move(int row, int col){
		if(maze[player_row + row][player_col + col] != 1 && maze[player_row + row][player_col + col] != 5){
			if(maze[player_row + row][player_col + col] == 2 || maze[player_row + row][player_col + col] == 4|| 
					maze[player_row + row][player_col + col] == 5){
				if(maze[player_row + row*2][player_col + col*2] == 1 ||
						maze[player_row + row*2][player_col + col*2] == 2 ||
						maze[player_row + row*2][player_col + col*2] == 4 ||
						maze[player_row + row*2][player_col + col*2] == 5)
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
				
				/*
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
				 */
			}
			player_row += row;
			player_col += col;
		}

		time = 0;
		
	} 
	
	public void render(Graphics g){
		
		for(int i = 0; i < botaos.size(); i++)
			botaos.get(i).render(g);
		
		for(int row = 0; row < maze.length; row++){
			for(int col = 0; col < maze[row].length; col ++){
				g.drawImage(Elementos.floor, xOffset + col*TILESIZE, yOffset + row*TILESIZE, null);
				if(maze[row][col] == 1)
					g.drawImage(Elementos.wall2, xOffset + col*TILESIZE, yOffset + row*TILESIZE, null);
				if(maze[row][col] == 2)
					g.drawImage(Elementos.boxOff, xOffset + col*TILESIZE, yOffset + row*TILESIZE, null);
				if(maze[row][col] == 3)
					g.drawImage(Elementos.spot, xOffset + col*TILESIZE, yOffset + row*TILESIZE, null);
				if(maze[row][col] == 4)
					g.drawImage(Elementos.boxOn, xOffset + col*TILESIZE, yOffset + row*TILESIZE, null);
				if(maze[row][col] == 5)
					g.drawImage(Elementos.wall, xOffset + col*TILESIZE, yOffset + row*TILESIZE, null);
			}
		}
		
		g.drawImage(texture, xOffset + player_col*TILESIZE, yOffset + player_row*TILESIZE, null);
		
		
	}
	
	public boolean isSolved(){return solved;}
	public void setSolved(boolean bool){solved = bool;}
}
