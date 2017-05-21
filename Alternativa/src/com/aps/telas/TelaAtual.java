package com.aps.telas;

import java.awt.Graphics;

import com.aps.main.Tela;

public abstract class TelaAtual {
	
	public static TelaAtual telaAtual = null;
	
	protected Tela tela;
	
	public TelaAtual(Tela tela){
		this.tela = tela;
	}
	
	public abstract void update();
	
	public abstract void render(Graphics g);
	

}
