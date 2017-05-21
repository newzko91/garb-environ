package com.aps.main;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import com.aps.elementos_jogo.Elementos;
import com.aps.jogo.Nivel;
import com.aps.perifericos_entrada.Teclado;
import com.aps.perifericos_entrada.GerMouse;
import com.aps.telas.ComoJogar;
import com.aps.telas.NivelAtual;
import com.aps.telas.TelaNivel;
import com.aps.telas.TelaInicio;
import com.aps.telas.TelaMenu;
import com.aps.telas.TelaAtual;

public class Tela extends JFrame implements Runnable{
	
	public static final int WIDTH = 800, HEIGHT = 600;
	private Canvas canvas;
	private Thread thread;
	private boolean running = false;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private final int FPS = 60;
	private double TARGETTIME = 1000000000/FPS;
	private double delta = 0;
	
	private NivelAtual nivelAtual;
	private TelaNivel telaNivel;
	private TelaMenu telaMenu;
	private ComoJogar comoJogar;
	private TelaInicio telaInicio;
	
	private Teclado teclado;
	private GerMouse gerMouse;
	
	//Construtor
	public Tela()
	{
		setTitle("UNIP - APS");
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
		if(TelaAtual.currentState instanceof NivelAtual)
			teclado.update();
		
		if(TelaAtual.currentState != null)
			TelaAtual.currentState.update();
	}

	private void draw(){
		bs = canvas.getBufferStrategy();
		
		if(bs == null)
		{
			canvas.createBufferStrategy(4);
			return;
		}
		
		g = bs.getDrawGraphics();
		
		//-----------------------
		
		g.setColor(Color.GRAY);
		
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		for(int i = 0; i < Tela.WIDTH/Nivel.TILESIZE + 1; i++)
			for(int j = 0; j < Tela.HEIGHT/Nivel.TILESIZE + 1; j++)
				//g.drawImage(Elementos.floor2, i*Nivel.TILESIZE, j*Nivel.TILESIZE, null);
				g.drawImage(Elementos.floor3, i*600, j*353, null);
	
		if(TelaAtual.currentState != null)
			TelaAtual.currentState.render(g);
		
		//g.drawString(""+AVERAGEFPS, 10, 20);
		
		//---------------------
		g.dispose();
		bs.show();
	}
	
	private void init()
	{
		Elementos.init();
		telaMenu = new TelaMenu(this);
		nivelAtual = new NivelAtual(this);
		telaInicio = new TelaInicio(this);
		telaNivel = new TelaNivel(this);
		comoJogar = new ComoJogar(this);
		
		TelaAtual.currentState = telaInicio;
	}
	
	
	@Override
	public void run() {
		
		long now = 0;
		long lastTime = System.nanoTime();
		int frames = 0;
		long time = 0;
		
		init();
		Elementos.tema.play();
		
		while(running)
		{
			now = System.nanoTime();
			delta += (now - lastTime)/TARGETTIME;
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
	
	public TelaAtual getNivelAtual(){
		return nivelAtual;
	}
	public TelaAtual getLevelSelectorState(){
		return telaNivel;
	}
	public TelaAtual getTelaMenu(){
		return telaMenu;
	}
	
	public TelaAtual getComoJogar(){
		return comoJogar;
	}
	
	
}