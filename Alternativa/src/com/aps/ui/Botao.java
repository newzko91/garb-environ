package com.aps.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.aps.elementos_jogo.Texto;
import com.aps.perifericos_entrada.GerMouse;

public class Botao{
	
	private String text;
	private int x, y;
	private FontMetrics fm;
	private Rectangle bounds;
	private boolean hovering;
	private Click click;
	private Font font;
	
	public Botao(String text, int x, int y, Click click, Font font){
		this.text = text;
		this.x = x;
		this.y = y;
		this.click = click;
		hovering = false;
		this.font = font;
	}
	
	public void update(){		
		if(bounds != null && bounds.contains(GerMouse.x, GerMouse.y)){
			hovering = true;
			if(GerMouse.left)
				click.onClick();
		}else
			hovering = false;
	}
	
	public void render(Graphics g){
		g.setFont(font);
		fm = g.getFontMetrics();
		if(hovering)
			Texto.drawString(g, text, x, y, true, Color.RED);
		else
			Texto.drawString(g, text, x, y, true, Color.BLUE);
		bounds = new Rectangle(x - fm.stringWidth(text)/2, y - fm.getHeight()/2, fm.stringWidth(text), fm.getHeight());
	}
	
}
