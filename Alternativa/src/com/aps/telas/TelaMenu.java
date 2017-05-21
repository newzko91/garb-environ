package com.aps.telas;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import com.aps.elementos_jogo.Elementos;
import com.aps.elementos_jogo.Texto;
import com.aps.main.Tela;
import com.aps.ui.Botao;
import com.aps.ui.Click;

public class TelaMenu extends TelaAtual{
	
	private ArrayList<Botao> botao = new ArrayList<Botao>();
	
	public TelaMenu(Tela tela){
		super(tela);
		botao.add(new Botao("JOGAR", Tela.WIDTH/2, Tela.HEIGHT/2 - 50, new Click(){

			public void onClick() {
				TelaAtual.nivelAtual = tela.getLevelSelectorState();
			}}, Elementos.tamanho48));
		
		botao.add(new Botao("INSTRUCOES", Tela.WIDTH/2, Tela.HEIGHT/2 + 50, new Click(){

			public void onClick() {
				TelaAtual.nivelAtual = tela.getComoJogar();
			}}, Elementos.tamanho48));
		
		botao.add(new Botao("SAIR", Tela.WIDTH/2, Tela.HEIGHT/2 + 150, new Click(){

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
		Texto.drawString(g, "APS | JAVA", Tela.WIDTH/2, Tela.HEIGHT/2 - 200, true, Color.ORANGE);
		for(int i = 0; i < botao.size(); i++)
			botao.get(i).render(g);
	}

}
