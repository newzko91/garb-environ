package com.aps.telas;

import java.awt.Color;
import java.awt.Graphics;

import com.aps.elementos_jogo.Elementos;
import com.aps.elementos_jogo.Texto;
import com.aps.main.Tela;
import com.aps.ui.Botao;
import com.aps.ui.Click;

public class ComoJogar extends TelaAtual{
	
	private final String identificacao = "COMO JOGAR";
	private Botao voltar;
	
	public ComoJogar(Tela tela){
		super(tela);
		
		voltar = new Botao("VOLTAR", Tela.WIDTH/2, Tela.HEIGHT - 100, new Click(){

			public void onClick() {
				TelaAtual.currentState = tela.getTelaMenu();
			}
			
		}, Elementos.tamanho30);
	}
	
	public void update(){
		voltar.update();
	}


	public void render(Graphics g) {
		voltar.render(g);
		g.setFont(Elementos.tamanho22);
		Texto.drawString(g, identificacao, Tela.WIDTH/2 - 300, Tela.HEIGHT - 560, true, Color.WHITE);
		
	}
	
}