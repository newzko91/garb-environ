package com.aps.telas;

import java.awt.Color;
import java.awt.Graphics;

import com.aps.elementos_jogo.Assets;
import com.aps.elementos_jogo.Text;
import com.aps.main.Window;
import com.aps.ui.Botao;
import com.aps.ui.Click;

public class ComoJogar extends TelaAtual{
	
	private final String identificacao = "COMO JOGAR";
	private Botao voltar;
	
	public ComoJogar(Window window){
		super(window);
		
		voltar = new Botao("VOLTAR", Window.WIDTH/2, Window.HEIGHT - 100, new Click(){

			public void onClick() {
				TelaAtual.currentState = window.getMenuState();
			}
			
		}, Assets.tamanho30);
	}
	
	public void update(){
		voltar.update();
	}


	public void render(Graphics g) {
		voltar.render(g);
		g.setFont(Assets.tamanho22);
		Text.drawString(g, identificacao, Window.WIDTH/2 - 300, Window.HEIGHT - 560, true, Color.WHITE);
		
	}
	
}