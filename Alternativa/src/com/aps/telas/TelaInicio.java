package com.aps.telas;

import java.awt.Color;
import java.awt.Graphics;

import com.aps.elementos_jogo.Assets;
import com.aps.elementos_jogo.Text;
import com.aps.main.Tela;

public class TelaInicio extends TelaAtual{
	
	private final String NAME = "APS sdnmd sdnsaidis sodnisd osnds d";
	private String text = "";
	private int index = 0;
	private long time, lastTime;
	public TelaInicio(Tela tela){
		super(tela);
		time = 0;
		lastTime = System.currentTimeMillis();
	}
	
	public void update() {
		time += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		if(time > 150){
			
			text = NAME.substring(0, index);
			if(index < NAME.length()){
				index ++;

			}else{
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				TelaAtual.telaAtual = tela.getTelaMenu();
			}
			time = 0;
		}
			
	}

	@Override
	public void render(Graphics g) {
		g.setFont(Assets.tamanho22);
		Text.drawString(g, text, Tela.WIDTH/2, Tela.HEIGHT/2, true, Color.WHITE);
	}
	
}
