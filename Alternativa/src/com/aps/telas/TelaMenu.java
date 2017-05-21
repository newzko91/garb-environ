package com.aps.telas;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import com.aps.elementos_jogo.Assets;
import com.aps.elementos_jogo.Text;
import com.aps.main.Tela;
import com.aps.ui.Botao;
import com.aps.ui.Click;

public class TelaMenu extends TelaAtual{
	
	private ArrayList<Botao> botao = new ArrayList<Botao>();
	
	public TelaMenu(Tela tela){
		super(tela);
		botao.add(new Botao("JOGAR", Tela.WIDTH/2, Tela.HEIGHT/2 - 50, new Click(){

			public void onClick() {
				TelaAtual.telaAtual = tela.getCarregaNivel();
			}}, Assets.tamanho48));
		
		botao.add(new Botao("INSTRUCOES", Tela.WIDTH/2, Tela.HEIGHT/2 + 50, new Click(){

			public void onClick() {
				TelaAtual.telaAtual = tela.getComoJogar();
			}}, Assets.tamanho48));
		
		botao.add(new Botao("SAIR", Tela.WIDTH/2, Tela.HEIGHT/2 + 150, new Click(){

			public void onClick() {
				System.exit(1);
			}}, Assets.tamanho48));
	}
	
	public void update() {
		for(int i = 0; i < botao.size(); i++)
			botao.get(i).update();
	}

	public void render(Graphics g) {
		g.setFont(Assets.tamanho48);
		Text.drawString(g, "APS | JAVA", Tela.WIDTH/2, Tela.HEIGHT/2 - 200, true, Color.ORANGE);
		for(int i = 0; i < botao.size(); i++)
			botao.get(i).render(g);
	}

}
