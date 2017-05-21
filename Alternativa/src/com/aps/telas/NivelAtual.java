package com.aps.telas;

import java.awt.Graphics;

import com.aps.jogo.Nivel;
import com.aps.main.Tela;

public class NivelAtual extends TelaAtual{
	
	private Nivel nivel;
	
	public NivelAtual(Tela tela) {
		super(tela);
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
