package com.aps.telas;

import java.awt.Graphics;

import com.aps.main.Tela;

public abstract class TelaAtual {
	
	public static TelaAtual nivelAtual = null;
	
	
	protected Tela tela;
	
	public static int ID = 0;
	
	private int id;
	
	public TelaAtual(Tela tela){
		this.tela = tela;

	}
	
	public abstract void update();
	
	public abstract void render(Graphics g);
	

}
