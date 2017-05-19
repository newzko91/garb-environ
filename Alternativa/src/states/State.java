package states;

import java.awt.Graphics;

import main.TelaPrincipal;

public abstract class State {
	
	public static State currentState = null;
	
	protected TelaPrincipal telaPrincipal;
	
	public State(TelaPrincipal telaPrincipal){
		this.telaPrincipal = telaPrincipal;
	}
	
	public abstract void update();
	
	public abstract void render(Graphics g);
	
	
}
