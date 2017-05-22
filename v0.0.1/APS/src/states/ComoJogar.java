package states;

import java.awt.Color;
import java.awt.Graphics;

import gfx.Assets;
import gfx.Text;
import main.Window;
import ui.Button;
import ui.Click;

public class ComoJogar extends State{
	
	private final String identificacao = "COMO JOGAR";
	private Button voltar;
	
	public ComoJogar(Window window){
		super(window);
		
		voltar = new Button("VOLTAR", Window.WIDTH/2, Window.HEIGHT - 100, new Click(){

			public void onClick() {
				State.currentState = window.getMenuState();
			}
			
		}, Assets.font30);
	}
	
	public void update(){
		voltar.update();
	}


	public void render(Graphics g) {
		voltar.render(g);
		g.setFont(Assets.font22);
		Text.drawString(g, identificacao, Window.WIDTH/2 - 300, Window.HEIGHT - 560, true, Color.WHITE);
		g.drawImage(Assets.info, Window.WIDTH-750, Window.HEIGHT-600, null);
		
	}
	
}