package com.aps.telas;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import com.aps.grafico.Elementos;
import com.aps.grafico.Texto;
import com.aps.main.Tela;
import com.aps.ui.Botao;
import com.aps.ui.Click;

public class TelaMenu extends Status{
	

	
	private ArrayList<Botao> botaos = new ArrayList<Botao>();
	
	public TelaMenu(Tela tela){
		super(tela);
		botaos.add(new Botao("JOGAR", Tela.WIDTH/2, Tela.HEIGHT/2 - 50, new Click(){

			@Override
			public void onClick() {
				Status.currentState = tela.getCarregaNivel();
			}}, Elementos.font48));
		botaos.add(new Botao("INSTRUCOES", Tela.WIDTH/2, Tela.HEIGHT/2 + 50, new Click(){

			@Override
			public void onClick() {
				Status.currentState = tela.getComoJogar();
			}}, Elementos.font48));
		botaos.add(new Botao("SAIR", Tela.WIDTH/2, Tela.HEIGHT/2 + 150, new Click(){

			@Override
			public void onClick() {
				System.exit(1);
			}}, Elementos.font48));
	}
	
	@Override
	public void update() {
		for(int i = 0; i < botaos.size(); i++)
			botaos.get(i).update();
	}

	@Override
	public void render(Graphics g) {
		g.setFont(Elementos.font48);
		Texto.drawString(g, "APS | JAVA", Tela.WIDTH/2, Tela.HEIGHT/2 - 200, true, Color.ORANGE);
		for(int i = 0; i < botaos.size(); i++)
			botaos.get(i).render(g);
	}

}
