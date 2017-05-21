package com.aps.telas;

import java.awt.Graphics;

import com.aps.main.Window;

public abstract class TelaAtual {
	
	public static TelaAtual currentState = null;
	
	
	protected Window window;
	
	public static int ID = 0;
	
	private int id;
	
	public TelaAtual(Window window){
		this.window = window;

	}
	
	public abstract void update();
	
	public abstract void render(Graphics g);
	

}
