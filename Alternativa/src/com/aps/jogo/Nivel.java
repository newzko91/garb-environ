package com.aps.jogo;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import com.aps.elementos_jogo.Elementos;
import com.aps.main.Tela;
import com.aps.perifericos_entrada.Teclado;
import com.aps.telas.TelaNivel;
import com.aps.ui.Botao;
import com.aps.ui.Click;
import com.aps.telas.TelaAtual;

public class Nivel {
	
	public static final int TILESIZE = 48;
	
	private int[][] maze;
	private int[][] copy;
	
	private int xOffset, yOffset;
	private int plaStartRow, plaStartCol;
	private int player_row, player_col;
	private Image texture;
	
	private long time, lastTime, aviso = 3000;
	private final int DELAY = 200;

	private boolean solved;
	private TelaNivel telaNivel;
	public static int ID = 0;
	private int id;
	
	private ArrayList<Botao> botao = new ArrayList<Botao>();
	
	public Nivel(int[][] maze, int player_row, int player_col, TelaNivel telaNivel){
		this.telaNivel = telaNivel;
		this.maze = maze;
		ID++; 
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
		texture = Elementos.jogadorFrente;
		
		botao.add(new Botao("RESTART", Tela.WIDTH/2 - 100, Tela.HEIGHT - 50, new Click(){

			@Override
			public void onClick() {
				reset();
				
			}},
				Elementos.tamanho30));
		
		botao.add(new Botao("BACK", Tela.WIDTH/2 + 100, Tela.HEIGHT - 50, new Click(){

			@Override
			public void onClick() {
				TelaAtual.currentState = telaNivel;
				
			}},
				Elementos.tamanho30));
		
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
		texture = Elementos.jogadorFrente;
	}
	
	
	public void update(){
		time += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(Teclado.UP && time > DELAY){ // se o tempo for maior que o DELAY ai sim ele move o objeto.
			move(-1, 0);
			texture = Elementos.jogadorCostas;
		}
		if(Teclado.LEFT && time > DELAY){
			move(0, -1);
			texture = Elementos.jogadorE;
		}
		if(Teclado.DOWN && time > DELAY){
			move(1, 0);
			texture = Elementos.jogadorFrente;
		}
		if(Teclado.RIGHT && time > DELAY){
			move(0, 1);
			texture = Elementos.jogadorD;
		}
		
		for(int i = 0; i < botao.size(); i++)
			botao.get(i).update();
	
		
		for(int row = 0; row < maze.length; row++)
			for(int col = 0; col < maze[row].length; col ++)
				if(maze[row][col] == 2)
					return;
		
		telaNivel.getLevels()[id].setSolved(true);
		Elementos.sucesso.play();
		TelaAtual.currentState = telaNivel; //quando solucionado igual a verdadeiro, vai pra tela dos niveis.
		
	}
	
	
	private void move(int row, int col){
		if(maze[player_row + row][player_col + col] != 1){
			if(maze[player_row + row][player_col + col] == 2 || maze[player_row + row][player_col + col] == 4){
				if(maze[player_row + row*2][player_col + col*2] == 1 ||
						maze[player_row + row*2][player_col + col*2] == 2 ||
						maze[player_row + row*2][player_col + col*2] == 4)
					return; //take you out of the method
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

	//desenhar os elementos na tela
	public void render(Graphics g){
		
		for(int i = 0; i < botao.size(); i++)
			botao.get(i).render(g);
		
		for(int row = 0; row < maze.length; row++){
			for(int col = 0; col < maze[row].length; col ++){
				//desenha a figura do piso em toda a extensão
				g.drawImage(Elementos.floor4, xOffset + col*TILESIZE, yOffset + row*TILESIZE, null);
				if(maze[row][col] == 1)
					g.drawImage(Elementos.wall, xOffset + col*TILESIZE, yOffset + row*TILESIZE, null);
				if(maze[row][col] == 2)
					g.drawImage(Elementos.boxOff, xOffset + col*TILESIZE, yOffset + row*TILESIZE, null);
				if(maze[row][col] == 3)
					g.drawImage(Elementos.spot, xOffset + col*TILESIZE, yOffset + row*TILESIZE, null);
				if(maze[row][col] == 4)
					g.drawImage(Elementos.boxOn, xOffset + col*TILESIZE, yOffset + row*TILESIZE, null);
			}
		}
		
		g.drawImage(texture, xOffset + player_col*TILESIZE, yOffset + player_row*TILESIZE, null);
		
		
	}
	
	public boolean isSolved(){
		return solved;
	}
	
	public void setSolved(boolean bool){
		solved = bool;
	}
}
