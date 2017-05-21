package com.aps.perifericos_entrada;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.aps.elementos_jogo.Assets;

public class MouseManager extends MouseAdapter{
	
	public static int x, y;
	public static boolean left;
	
	public MouseManager(){
		x = 0;
		y = 0;
	}
	
	public void mousePressed(MouseEvent e){
		if(e.getButton() == MouseEvent.BUTTON1)
			left = true;
			Assets.movimento.play();
		
	}
	
	public void mouseReleased(MouseEvent e){
		if(e.getButton() == MouseEvent.BUTTON1)
			left = false;
	}
	
	public void mouseMoved(MouseEvent e){
		x = e.getX();
		y = e.getY();
	}
}
