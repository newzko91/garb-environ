package com.aps.telas;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import com.aps.elementos_jogo.Elementos;
import com.aps.elementos_jogo.Texto;
import com.aps.main.Window;
import com.aps.ui.Botao;
import com.aps.ui.Click;

public class TelaMenu extends TelaAtual{
	
	private ArrayList<Botao> botao = new ArrayList<Botao>();
	
	public TelaMenu(Window window){
		super(window);
		botao.add(new Botao("JOGAR", Window.WIDTH/2, Window.HEIGHT/2 - 50, new Click(){

			public void onClick() {
				TelaAtual.currentState = window.getLevelSelectorState();
			}}, Elementos.tamanho48));
		
		botao.add(new Botao("INSTRUCOES", Window.WIDTH/2, Window.HEIGHT/2 + 50, new Click(){

			public void onClick() {
				TelaAtual.currentState = window.getComoJogar();
			}}, Elementos.tamanho48));
		
		botao.add(new Botao("SAIR", Window.WIDTH/2, Window.HEIGHT/2 + 150, new Click(){

			public void onClick() {
				System.exit(1);
			}}, Elementos.tamanho48));
	}
	
	@Override
	public void update() {
		for(int i = 0; i < botao.size(); i++)
			botao.get(i).update();
	}

	@Override
	public void render(Graphics g) {
		g.setFont(Elementos.tamanho48);
		Texto.drawString(g, "APS | JAVA", Window.WIDTH/2, Window.HEIGHT/2 - 200, true, Color.ORANGE);
		for(int i = 0; i < botao.size(); i++)
			botao.get(i).render(g);
	}

}
