package com.aps.entrada;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.aps.grafico.Elementos;

public class GerMouse extends MouseAdapter{
	
	public static int x, y;
	public static boolean left;
	
	public GerMouse(){
		x = 0;
		y = 0;
	}
	
	@Override
	public void mousePressed(MouseEvent e){
		if(e.getButton() == MouseEvent.BUTTON1)
			left = true;
			Elementos.movimento.play();
	}
	
	@Override
	public void mouseReleased(MouseEvent e){
		if(e.getButton() == MouseEvent.BUTTON1)
			left = false;
	}
	
	@Override
	public void mouseMoved(MouseEvent e){
		x = e.getX();
		y = e.getY();
	}
	
}
