package com.aps.telas;

import java.awt.Graphics;

import com.aps.jogo.Nivel;
import com.aps.main.Window;

public class NivelAtual extends TelaAtual{
	
	private Nivel nivel;
	
	public NivelAtual(Window window) {
		super(window);
	}
	
	public void update() {
		nivel.update();
	}

	public void render(Graphics g) {
		nivel.render(g);
	}
	
	public void setNivel(Nivel nivel){
		this.nivel = nivel;
	}
}
