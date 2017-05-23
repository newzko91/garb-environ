package com.aps.telas;

import java.awt.Graphics;

import com.aps.main.Tela;

public abstract class Status {
	
	public static Status currentState = null;
	
	protected Tela tela;
	
	public Status(Tela tela){
		this.tela = tela;
	}
	
	public abstract void update();
	
	public abstract void render(Graphics g);
	
	
}
