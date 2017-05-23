package com.aps.main;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import com.aps.entrada.Teclado;
import com.aps.entrada.GerMouse;
import com.aps.grafico.Elementos;
import com.aps.jogo.Nivel;
import com.aps.telas.ComoJogar;
import com.aps.telas.ControleNivel;
import com.aps.telas.CarregaNivel;
import com.aps.telas.BoasVindas;
import com.aps.telas.TelaMenu;
import com.aps.telas.Status;

public class Tela extends JFrame implements Runnable{
	
	public static final int WIDTH = 800, HEIGHT = 600;
	private Canvas canvas;
	private Thread thread;
	private boolean running = false;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private final int FPS = 60;
	private double NANOSECOND = 1000000000/FPS;
	private double delta = 0;
	
	private ControleNivel controleNivel;
	private CarregaNivel carregaNivel;
	private TelaMenu telaMenu;
	private BoasVindas boasVindas;
	private ComoJogar comoJogar;
	
	private Teclado teclado;
	private GerMouse gerMouse;
	
	public Tela()
	{
		setTitle("APS > JAVA | 2017");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		canvas = new Canvas();
		teclado = new Teclado();
		gerMouse = new GerMouse();
		
		canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		canvas.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		canvas.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		canvas.setFocusable(true);
		
		add(canvas);
		addMouseMotionListener(gerMouse);
		addMouseListener(gerMouse);
		canvas.addMouseListener(gerMouse);
		canvas.addMouseMotionListener(gerMouse);
		canvas.addKeyListener(teclado);
		setVisible(true);
	}
	
	

	public static void main(String[] args) {
		new Tela().start();
	}
	
	
	private void update(){
		if(Status.currentState instanceof ControleNivel)
			teclado.update();
		
		if(Status.currentState != null)
			Status.currentState.update();
	}

	private void draw(){
		bs = canvas.getBufferStrategy();
		
		if(bs == null)
		{
			canvas.createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		for(int i = 0; i < Tela.WIDTH/Nivel.TILESIZE + 1; i++){
			for(int j = 0; j < Tela.HEIGHT/Nivel.TILESIZE + 1; j++) {
				g.drawImage(Elementos.floor3, i*WIDTH, j*HEIGHT, null);
				
			}
		}
		
		
		if(Status.currentState != null) {
			Status.currentState.render(g);
		}
		
		/*
		if(State.currentState == levelSelectorState) {
			State.currentState.render(h);
		} */
		
			
		
		
		
		g.dispose(); 
		bs.show();
	}
	
	private void init()
	{
		Elementos.init();
		telaMenu = new TelaMenu(this);
		controleNivel = new ControleNivel(this);
		boasVindas = new BoasVindas(this);
		carregaNivel = new CarregaNivel(this);
		comoJogar = new ComoJogar(this);
		
		Status.currentState = boasVindas;
		Elementos.intro.play_more();
		
	}
	
	
	@Override
	public void run() {
		
		long now = 0;
		long lastTime = System.nanoTime();
		int frames = 0;
		long time = 0;
		
		init();
		
		while(running)
		{
			now = System.nanoTime();
			delta += (now - lastTime)/NANOSECOND;
			time += (now - lastTime);
			lastTime = now;
			
			if(delta >= 1)
			{		
				update();
				draw();
				delta --;
				frames ++;
			}
			if(time >= 1000000000)
			{
				frames = 0;
				time = 0;
			}
		}
		
		stop();
	}
	
	private void start(){
		
		thread = new Thread(this);
		thread.start();
		running = true;
		
		
	}
	private void stop(){
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public Status getControleNivel(){
		return controleNivel;
	}
	public Status getCarregaNivel(){
		Elementos.intro.stop();
		Elementos.theme.play_more();
		return carregaNivel;
	}
	
	public Status getTelaMenu(){
		return telaMenu;
	}
	
	public Status getComoJogar(){
		return comoJogar;
	}
	
	
}