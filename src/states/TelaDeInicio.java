package states;

import java.awt.Color;
import java.awt.Graphics;

import gfx.Assets;
import gfx.Text;
import main.TelaPrincipal;

public class TelaDeInicio extends State{
	
	private final String NAME = "APS Jogo em JAVA";
	private String text = "";
	private int index = 0;
	private long time, lastTime;
	public TelaDeInicio(TelaPrincipal telaPrincipal){
		super(telaPrincipal);
		time = 0;
		lastTime = System.currentTimeMillis();
	}
	
	public void update() {
		time += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		if(time > 150){
			
			text = NAME;//.substring(0, index);
			if(index < NAME.length()){
				index ++;

			}else{
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				State.currentState = telaPrincipal.getMenuState();
			}
			time = 0;
		}
			
	}
	
	public void render(Graphics g) {
		g.setFont(Assets.fontArial);
		Text.drawString(g, text, TelaPrincipal.WIDTH/2-20, TelaPrincipal.HEIGHT/2-20, true, Color.WHITE);
	}
	
	/*
	public void render(Graphics g) {
		g.setFont(Assets.font30);
		Text.drawString(g, text, Window.WIDTH/2, Window.HEIGHT/2, true, Color.WHITE);
	}*/
	
}
