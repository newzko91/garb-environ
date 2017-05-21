package com.aps.telas;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import com.aps.elementos_jogo.Assets;
import com.aps.elementos_jogo.Text;
import com.aps.main.Tela;
import com.aps.ui.Botao;
import com.aps.ui.Click;
import com.aps.telas.CarregaNivel;


public class Continuar extends TelaAtual{
	
	private final String titulo = "DESEJA JOGAR NOVAMENTE?";
	private CarregaNivel carregaNivel;
	
	private ArrayList<Botao> botao = new ArrayList<Botao>();
	
	public Continuar(Tela tela){
		super(tela);
		
		botao.add(new Botao("SIM", Tela.WIDTH/2 - 100, Tela.HEIGHT - 50, new Click(){

			public void onClick() {
				for(int i = 0; i < carregaNivel.getNivel().length; i++){
					carregaNivel.getNivel()[i].setSolved(false);
				}
				TelaAtual.telaAtual = tela.getCarregaNivel();
			}
			
		}, Assets.tamanho30));
		
		botao.add(new Botao("NÃO", Tela.WIDTH/2 + 100, Tela.HEIGHT - 50, new Click(){

			public void onClick() {
				System.exit(1);
			}}, Assets.tamanho30));
	}
	
	public void update(){
		for(int i = 0; i < botao.size(); i++)
			botao.get(i).update();
	}


	public void render(Graphics g) {
		for(int i = 0; i < botao.size(); i++)
			botao.get(i).render(g);
		
		g.setFont(Assets.tamanho22);
		Text.drawString(g, titulo, Tela.WIDTH/2 + 300, Tela.HEIGHT - 150, true, Color.WHITE);
		
	}
	
}
