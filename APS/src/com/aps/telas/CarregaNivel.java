package com.aps.telas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.aps.entrada.GerMouse;
import com.aps.grafico.Elementos;
import com.aps.grafico.Texto;
import com.aps.jogo.Nivel;
import com.aps.main.Tela;
import com.aps.ui.Botao;
import com.aps.ui.Click;

public class CarregaNivel extends Status{
	private final int DOUBLETILESIZE = 64;
	private Nivel[] nivels = new Nivel[5];
	private final int xOffset = (Tela.WIDTH - DOUBLETILESIZE*5)/2;
	private final int yOffset = (Tela.HEIGHT - DOUBLETILESIZE*3)-100;
	
	private Botao back;
	
	public CarregaNivel(Tela tela) {
		super(tela);
		
		for(int i = 0; i < nivels.length; i++)
			nivels[i] = loadLevel("/levels/"+i+".txt");
		
		back = new Botao("VOLTAR", Tela.WIDTH/2, Tela.HEIGHT - 100, new Click(){

			@Override
			public void onClick() {
				Elementos.theme.stop();
				Elementos.intro.play_more();
				Status.currentState = tela.getTelaMenu();
			}
			
		}, Elementos.font30);
		
		
	}
	
	private Nivel loadLevel(String path){
		
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
		
		return new Nivel(maze, player_row, player_col, this);
	}
	
	public static String loadFileAsString(String path){
		StringBuilder builder = new StringBuilder();
		try{
		InputStream in = CarregaNivel.class.getResourceAsStream(path);
		
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
		g.setFont(Elementos.font30);
		
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
		if(bounds0.contains(GerMouse.x, GerMouse.y)){
			if(GerMouse.left && nivels[0].isSolved()){
				((ControleNivel)tela.getControleNivel()).setNivel(nivels[counter-1]);
				Status.currentState = tela.getControleNivel();
			}
			g.drawImage(Elementos.outline2, bounds0.x, bounds0.y, null);
			if(nivels[0].isSolved())
				Texto.drawString(g, 1+"", xOffset + DOUBLETILESIZE/2,
						yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.YELLOW);
			else
				Texto.drawString(g,"X", xOffset + DOUBLETILESIZE/2,
						yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.YELLOW);
		}else{
			g.drawImage(Elementos.outline, bounds0.x, bounds0.y, null);
			if(nivels[0].isSolved())
				Texto.drawString(g, 1+"", xOffset + DOUBLETILESIZE/2,
						yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.GREEN);
				else
					Texto.drawString(g,"X", xOffset + DOUBLETILESIZE/2,
							yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.WHITE);
		}
		
		//Nivel 2
		if(bounds1.contains(GerMouse.x, GerMouse.y)){
			if(GerMouse.left && nivels[1].isSolved()){
				((ControleNivel)tela.getControleNivel()).setNivel(nivels[1]);
				Status.currentState = tela.getControleNivel();
			}
			g.drawImage(Elementos.outline2, bounds1.x, bounds1.y, null);
			if(nivels[1].isSolved())
				Texto.drawString(g, 2+"", xOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE,
						yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.YELLOW);
				  else
					Texto.drawString(g,"X", xOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE,
							yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.YELLOW);
			} else{
				g.drawImage(Elementos.outline, bounds1.x, bounds1.y, null);
				if(nivels[1].isSolved())
					Texto.drawString(g, 2+"", xOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE,
						yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.GREEN);
				else
					Texto.drawString(g,"X", xOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE,
							yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.WHITE);
		}
		
		//Nivel 3
		if(bounds2.contains(GerMouse.x, GerMouse.y)){
			if(GerMouse.left && nivels[2].isSolved()){
				((ControleNivel)tela.getControleNivel()).setNivel(nivels[2]);
				Status.currentState = tela.getControleNivel();
			}
			g.drawImage(Elementos.outline2, bounds2.x, bounds2.y, null);
			if(nivels[2].isSolved())
				Texto.drawString(g, 3+"", xOffset + DOUBLETILESIZE/2 + 2*DOUBLETILESIZE,
					yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.YELLOW);
			  else
				Texto.drawString(g,"X", xOffset + DOUBLETILESIZE/2 + 2*DOUBLETILESIZE,
						yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.YELLOW);
		} else{
			g.drawImage(Elementos.outline, bounds2.x, bounds2.y, null);
			if(nivels[2].isSolved())
				Texto.drawString(g, 3+"", xOffset + DOUBLETILESIZE/2 + 2*DOUBLETILESIZE,
					yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.GREEN);
			else
				Texto.drawString(g,"X", xOffset + DOUBLETILESIZE/2 + 2*DOUBLETILESIZE,
						yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.WHITE);
		}
		
		//Nivel 4
		if(bounds3.contains(GerMouse.x, GerMouse.y)){
			if(GerMouse.left && nivels[3].isSolved()){
				((ControleNivel)tela.getControleNivel()).setNivel(nivels[3]);
				Status.currentState = tela.getControleNivel();
			}
			g.drawImage(Elementos.outline2, bounds3.x, bounds3.y, null);
			if(nivels[3].isSolved())
				Texto.drawString(g, 4+"", xOffset + DOUBLETILESIZE/2 + 3*DOUBLETILESIZE,
					yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.YELLOW);
			  else
				Texto.drawString(g,"X", xOffset + DOUBLETILESIZE/2 + 3*DOUBLETILESIZE,
						yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.YELLOW);
		} else{
			g.drawImage(Elementos.outline, bounds3.x, bounds3.y, null);
			if(nivels[3].isSolved())
				Texto.drawString(g, 4+"", xOffset + DOUBLETILESIZE/2 + 3*DOUBLETILESIZE,
					yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.GREEN);
			else
				Texto.drawString(g,"X", xOffset + DOUBLETILESIZE/2 + 3*DOUBLETILESIZE,
						yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.WHITE);
		}
		
		//Nivel 5
		if(bounds4.contains(GerMouse.x, GerMouse.y)){
			if(GerMouse.left && nivels[4].isSolved()){
				((ControleNivel)tela.getControleNivel()).setNivel(nivels[4]);
				Status.currentState = tela.getControleNivel();
			}
			g.drawImage(Elementos.outline2, bounds4.x, bounds4.y, null);
			if(nivels[4].isSolved())
				Texto.drawString(g, 5+"", xOffset + DOUBLETILESIZE/2 + 4*DOUBLETILESIZE,
					yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.YELLOW);
			  else
				Texto.drawString(g,"X", xOffset + DOUBLETILESIZE/2 + 4*DOUBLETILESIZE,
						yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.YELLOW);
		} else{
			g.drawImage(Elementos.outline, bounds4.x, bounds4.y, null);
			if(nivels[4].isSolved())
				Texto.drawString(g, 5+"", xOffset + DOUBLETILESIZE/2 + 4*DOUBLETILESIZE,
					yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.GREEN);
			else
				Texto.drawString(g,"X", xOffset + DOUBLETILESIZE/2 + 4*DOUBLETILESIZE,
						yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.WHITE);
		}
		
	}
	
	
	public Nivel[] getLevels(){return nivels;}

}
