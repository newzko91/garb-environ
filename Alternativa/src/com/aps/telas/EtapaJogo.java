package com.aps.telas;

import java.awt.Graphics;

import com.aps.jogo.Nivel;
import com.aps.main.Tela;

public class EtapaJogo extends TelaAtual{
	
	private Nivel nivel;
	
	public EtapaJogo(Tela tela) {
		super(tela);
	}
	
	@Override
	public void update() {
		nivel.update();
	}
	
	@Override
	public void render(Graphics g) {
		nivel.render(g);
	}
	
	public void setNivel(Nivel nivel){
		this.nivel = nivel;
	}
}
