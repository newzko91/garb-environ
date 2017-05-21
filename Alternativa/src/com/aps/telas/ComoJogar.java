package com.aps.telas;

import java.awt.Color;
import java.awt.Graphics;

import com.aps.elementos_jogo.Elementos;
import com.aps.elementos_jogo.Texto;
import com.aps.main.Window;

import ui.Botao;
import ui.Click;

public class ComoJogar extends TelaAtual{
	
	private final String identificacao = "COMO JOGAR";
	private Botao voltar;
	
	public ComoJogar(Window window){
		super(window);
		
		voltar = new Botao("VOLTAR", Window.WIDTH/2, Window.HEIGHT - 100, new Click(){

			public void onClick() {
				TelaAtual.currentState = window.getTelaMenu();
			}
			
		}, Elementos.tamanho30);
	}
	
	public void update(){
		voltar.update();
	}


	public void render(Graphics g) {
		voltar.render(g);
		g.setFont(Elementos.tamanho22);
		Texto.drawString(g, identificacao, Window.WIDTH/2 - 300, Window.HEIGHT - 560, true, Color.WHITE);
		
	}
	
}