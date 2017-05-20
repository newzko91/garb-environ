package states;

import java.awt.Color;
import java.awt.Graphics;

import gfx.Assets;
import gfx.Text;
import main.Window;
import sokoban.Level;
import ui.Button;
import ui.Click;

public class ComoJogar extends State{
	
	private final String NAME = "APS sdnmd sdnsaidis sodnisd osnds d";
	private String text = "";
	private int index = 0;
	private long time, lastTime;
	private Button voltar;
	
	public ComoJogar(Window window){
		super(window);
		
		voltar = new Button("VOLTAR", Window.WIDTH/2, Window.HEIGHT - 100, new Click(){

			@Override
			public void onClick() {
				State.currentState = window.getMenuState();
			}
			
		}, Assets.font30);
	}
	
	@Override
	public void update(){
		voltar.update();
	}

	@Override
	public void render(Graphics g) {
		voltar.render(g);
		g.setFont(Assets.font22);
		Text.drawString(g, text, Window.WIDTH/2, Window.HEIGHT/2, true, Color.WHITE);
		
	}
	
}