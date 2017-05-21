package com.aps.telas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.aps.elementos_jogo.Assets;
import com.aps.elementos_jogo.Text;
import com.aps.jogo.Nivel;
import com.aps.main.Tela;
import com.aps.perifericos_entrada.MouseManager;
import com.aps.ui.Botao;
import com.aps.ui.Click;

public class CarregaNivel extends TelaAtual{
	private final int DOUBLETILESIZE = 64;
	private Nivel[] objNivel = new Nivel[5];
	private static StringBuilder builder;
	
	private final int xOffset = (Tela.WIDTH - DOUBLETILESIZE*5)/2;
	private final int yOffset = (Tela.HEIGHT - DOUBLETILESIZE*3)-100;
	
	private Botao voltar;
	
	//Construtor 
	public CarregaNivel(Tela tela) {
		super(tela);
		
		/*for(int i = 0; i < objNivel.length; i++)
			objNivel[i] = loadLevel("/levels/"+i+".txt");*/
		
		objNivel[0] = loadLevel("/niveis/0.txt");
		objNivel[1] = loadLevel("/niveis/1.txt");
		objNivel[2] = loadLevel("/niveis/2.txt");
		objNivel[3] = loadLevel("/niveis/3.txt");
		objNivel[4] = loadLevel("/niveis/4.txt");
		
		voltar = new Botao("VOLTAR", Tela.WIDTH/2, Tela.HEIGHT - 100, new Click(){

			@Override
			public void onClick() {
				TelaAtual.telaAtual = tela.getTelaMenu();
			}
			
		}, Assets.tamanho30);
	}
	
	//Efetuada a leitura, armazena os numeros encontrados num vetor de Strings
	private Nivel loadLevel(String path){
		
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
		
		return new Nivel(maze, player_row, player_col, this);
	}
	
	//Percorre o arquivo txt indicado no FOR acima e converte os números para String
	public static String loadFileAsString(String path){
		builder = new StringBuilder(); 
		try{
		InputStream is = CarregaNivel.class.getResourceAsStream(path);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		
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
	
	//Converte o elemento informado em Integer.
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
		g.setFont(Assets.tamanho30);
		
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
		
		//Nivel 1
		if(bounds0.contains(MouseManager.x, MouseManager.y)){
			if(MouseManager.left && objNivel[0].isSolved()){
				((EtapaJogo)tela.getTelaMenu()).setNivel(objNivel[0]);
				TelaAtual.telaAtual = tela.getTelaMenu();
			} g.drawImage(Assets.outline2, bounds0.x, bounds0.y, null);
			  if(objNivel[0].isSolved())
				Text.drawString(g, 1+"", xOffset + DOUBLETILESIZE/2,
					yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.RED);
			  
				Text.drawString(g,"X", xOffset + DOUBLETILESIZE/2,
						yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.RED);
		} else{
			g.drawImage(Assets.outline, bounds0.x, bounds0.y, null);
			if(objNivel[0].isSolved())
				Text.drawString(g, 1+"", xOffset + DOUBLETILESIZE/2,
					yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.GREEN);
			else
				Text.drawString(g,"X", xOffset + DOUBLETILESIZE/2,
						yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.BLUE);
		} 
		
		//Nivel 2
		if(bounds1.contains(MouseManager.x, MouseManager.y)){
			if(MouseManager.left && objNivel[1].isSolved()){
				TelaAtual.telaAtual = null;
				((EtapaJogo)tela.getTelaMenu()).setNivel(objNivel[1]);
				TelaAtual.telaAtual = tela.getTelaMenu();
			} g.drawImage(Assets.outline2, bounds1.x, bounds1.y, null);
			  if(objNivel[1].isSolved())
				Text.drawString(g, 2+"", xOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE,
					yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.RED);
			  else
				Text.drawString(g,"X", xOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE,
						yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.RED);
		} else{
			g.drawImage(Assets.outline, bounds1.x, bounds1.y, null);
			if(objNivel[1].isSolved())
				Text.drawString(g, 2+"", xOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE,
					yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.GREEN);
			else
				Text.drawString(g,"X", xOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE,
						yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.BLUE);
		}
		
		//Nivel 3
		if(bounds2.contains(MouseManager.x, MouseManager.y)){
			if(MouseManager.left && objNivel[2].isSolved()){
				((EtapaJogo)tela.getTelaMenu()).setNivel(objNivel[2]);
				TelaAtual.telaAtual = tela.getTelaMenu();
			} g.drawImage(Assets.outline2, bounds2.x, bounds2.y, null);
			  if(objNivel[2].isSolved())
				Text.drawString(g, 3+"", xOffset + DOUBLETILESIZE/2 + 2*DOUBLETILESIZE,
					yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.RED);
			  else
				Text.drawString(g,"X", xOffset + DOUBLETILESIZE/2 + 2*DOUBLETILESIZE,
						yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.RED);
		} else{
			g.drawImage(Assets.outline, bounds2.x, bounds2.y, null);
			if(objNivel[2].isSolved())
				Text.drawString(g, 3+"", xOffset + DOUBLETILESIZE/2 + 2*DOUBLETILESIZE,
					yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.GREEN);
			else
				Text.drawString(g,"X", xOffset + DOUBLETILESIZE/2 + 2*DOUBLETILESIZE,
						yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.BLUE);
		}
		
		//Nivel 4
		if(bounds3.contains(MouseManager.x, MouseManager.y)){
			if(MouseManager.left && objNivel[3].isSolved()){
				((EtapaJogo)tela.getTelaMenu()).setNivel(objNivel[3]);
				TelaAtual.telaAtual = tela.getTelaMenu();
			} g.drawImage(Assets.outline2, bounds3.x, bounds3.y, null);
			  if(objNivel[3].isSolved())
				Text.drawString(g, 4+"", xOffset + DOUBLETILESIZE/2 + 3*DOUBLETILESIZE,
					yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.RED);
			  else
				Text.drawString(g,"X", xOffset + DOUBLETILESIZE/2 + 3*DOUBLETILESIZE,
						yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.RED);
		} else{
			g.drawImage(Assets.outline, bounds3.x, bounds3.y, null);
			if(objNivel[3].isSolved())
				Text.drawString(g, 4+"", xOffset + DOUBLETILESIZE/2 + 3*DOUBLETILESIZE,
					yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.GREEN);
			else
				Text.drawString(g,"X", xOffset + DOUBLETILESIZE/2 + 3*DOUBLETILESIZE,
						yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.BLUE);
		} 
		
		//Nivel 5
		if(bounds4.contains(MouseManager.x, MouseManager.y)){
			if(MouseManager.left && objNivel[4].isSolved()){
				((EtapaJogo)tela.getTelaMenu()).setNivel(objNivel[4]);
				TelaAtual.telaAtual = tela.getTelaMenu();
			} g.drawImage(Assets.outline2, bounds4.x, bounds4.y, null);
			  if(objNivel[4].isSolved())
				Text.drawString(g, 5+"", xOffset + DOUBLETILESIZE/2 + 4*DOUBLETILESIZE,
					yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.RED);
			  else
				Text.drawString(g,"X", xOffset + DOUBLETILESIZE/2 + 4*DOUBLETILESIZE,
						yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.RED);
		} else{
			g.drawImage(Assets.outline, bounds4.x, bounds4.y, null);
			if(objNivel[4].isSolved())
				Text.drawString(g, 5+"", xOffset + DOUBLETILESIZE/2 + 4*DOUBLETILESIZE,
					yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.GREEN);
			else
				Text.drawString(g,"X", xOffset + DOUBLETILESIZE/2 + 4*DOUBLETILESIZE,
						yOffset + DOUBLETILESIZE/2 + DOUBLETILESIZE, true, Color.BLUE);
		} 
	
	}
				
	public Nivel[] getNivel(){
		return objNivel;
	}
}