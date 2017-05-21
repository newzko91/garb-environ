package com.aps.main;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import com.aps.elementos_jogo.Assets;
import com.aps.jogo.Nivel;
import com.aps.perifericos_entrada.KeyBoard;
import com.aps.perifericos_entrada.MouseManager;
import com.aps.telas.ComoJogar;
import com.aps.telas.Continuar;
import com.aps.telas.EtapaJogo;
import com.aps.telas.CarregaNivel;
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
	
	private EtapaJogo etapaJogo;
	private CarregaNivel carregaNivel;
	private TelaMenu telaMenu;
	private ComoJogar comoJogar;
	private TelaInicio telaInicio;
	private Continuar continuar;
	
	private KeyBoard keyBoard;
	private MouseManager mouseManager;
	
	//Construtor
	public Tela()
	{
		setTitle("UNIP - APS");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		canvas = new Canvas();
		keyBoard = new KeyBoard();
		mouseManager = new MouseManager();
		
		canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		canvas.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		canvas.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		canvas.setFocusable(true);
		
		add(canvas);
		addMouseMotionListener(mouseManager);
		addMouseListener(mouseManager);
		canvas.addMouseListener(mouseManager);
		canvas.addMouseMotionListener(mouseManager);
		canvas.addKeyListener(keyBoard);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Tela().start();
	}
	
	private void update(){
		if(TelaAtual.telaAtual instanceof EtapaJogo)
			keyBoard.update();
		
		if(TelaAtual.telaAtual != null)
			TelaAtual.telaAtual.update();
	}

	private void draw(){
		bs = canvas.getBufferStrategy();
		
		if(bs == null)
		{
			canvas.createBufferStrategy(4);
			return;
		}
		
		g = bs.getDrawGraphics();
		
		g.setColor(Color.GRAY);
		
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		for(int i = 0; i < Tela.WIDTH/Nivel.TILESIZE + 1; i++)
			for(int j = 0; j < Tela.HEIGHT/Nivel.TILESIZE + 1; j++)
				//g.drawImage(Assets.floor2, i*Nivel.TILESIZE, j*Nivel.TILESIZE, null);
				g.drawImage(Assets.floor3, i*800, j*600, null);
	
		if(TelaAtual.telaAtual != null)
			TelaAtual.telaAtual.render(g);
		
		g.dispose();
		bs.show();
	}
	
	private void init()
	{
		Assets.init();
		telaMenu = new TelaMenu(this);
		etapaJogo = new EtapaJogo(this);
		telaInicio = new TelaInicio(this);
		carregaNivel = new CarregaNivel(this);
		comoJogar = new ComoJogar(this);
		continuar = new Continuar(this);
		
		TelaAtual.telaAtual = telaInicio;
		
		Assets.tema.play_more();
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
	
	public TelaAtual getEtapaJogo(){
		return etapaJogo;
	}
	public TelaAtual getCarregaNivel(){
		return carregaNivel;
	}
	public TelaAtual getTelaMenu(){
		return telaMenu;
	}
	
	public TelaAtual getComoJogar(){
		return comoJogar;
	}
	
	public TelaAtual getContinuar(){
		return continuar;
	}
	
	
}