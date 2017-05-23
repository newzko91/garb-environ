package com.aps.telas;

import java.awt.Color;
import java.awt.Graphics;

import com.aps.grafico.Elementos;
import com.aps.grafico.Texto;
import com.aps.main.Tela;

public class BoasVindas extends Status{
	
	private final String NAME = "NAO VOU MAIS JOGAR LIXO EM LOCAL INDEVIDO...";
	private String text = "";
	private int index = 0;
	private long time, lastTime;
	public BoasVindas(Tela tela){
		super(tela);
		time = 0;
		lastTime = System.currentTimeMillis();
	}
	
	@Override
	public void update() {
		time += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		if(time > 150){
			
			text = NAME;
			if(index < NAME.length()){
				index ++;

			}else{
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Status.currentState = tela.getTelaMenu();
			}
			time = 0;
		}
			
	}

	@Override
	public void render(Graphics g) {
		g.setFont(Elementos.font22);
		Texto.drawString(g, text, Tela.WIDTH/2, Tela.HEIGHT/2, true, Color.WHITE);
	}
	
}
