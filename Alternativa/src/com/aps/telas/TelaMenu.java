package com.aps.telas;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import com.aps.elementos_jogo.Assets;
import com.aps.elementos_jogo.Text;
import com.aps.main.Window;

import ui.Botao;
import ui.Click;

public class TelaMenu extends TelaAtual{
	
	private ArrayList<Botao> botao = new ArrayList<Botao>();
	
	public TelaMenu(Window window){
		super(window);
		botao.add(new Botao("JOGAR", Window.WIDTH/2, Window.HEIGHT/2 - 50, new Click(){

			public void onClick() {
				TelaAtual.currentState = window.getLevelSelectorState();
			}}, Assets.tamanho48));
		
		botao.add(new Botao("INSTRUCOES", Window.WIDTH/2, Window.HEIGHT/2 + 50, new Click(){

			public void onClick() {
				TelaAtual.currentState = window.getComoJogar();
			}}, Assets.tamanho48));
		
		botao.add(new Botao("SAIR", Window.WIDTH/2, Window.HEIGHT/2 + 150, new Click(){

			public void onClick() {
				System.exit(1);
			}}, Assets.tamanho48));
	}
	
	@Override
	public void update() {
		for(int i = 0; i < botao.size(); i++)
			botao.get(i).update();
	}

	@Override
	public void render(Graphics g) {
		g.setFont(Assets.tamanho48);
		Text.drawString(g, "APS | JAVA", Window.WIDTH/2, Window.HEIGHT/2 - 200, true, Color.ORANGE);
		for(int i = 0; i < botao.size(); i++)
			botao.get(i).render(g);
	}

}
