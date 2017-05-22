package main;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import gfx.Assets;
import input.KeyBoard;
import input.MouseManager;
import sokoban.Level;
import states.GameState;
import states.LevelSelectorState;
import states.LoadingState;
import states.MenuState;
import states.State;
import states.ComoJogar;

public class Window extends JFrame implements Runnable{
	
	public static final int WIDTH = 800, HEIGHT = 600;
	private Canvas canvas;
	private Thread thread;
	private boolean running = false;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private final int FPS = 60;
	private double TARGETTIME = 1000000000/FPS;
	private double delta = 0;
	
	private GameState gameState;
	private LevelSelectorState levelSelectorState;
	private MenuState menuState;
	private LoadingState loadingState;
	private ComoJogar comoJogar;
	
	private KeyBoard keyBoard;
	private MouseManager mouseManager;
	
	public Window()
	{
		setTitle("APS > JAVA | 2017");
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
		new Window().start();
	}
	
	
	private void update(){
		if(State.currentState instanceof GameState)
			keyBoard.update();
		
		if(State.currentState != null)
			State.currentState.update();
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
		
		for(int i = 0; i < Window.WIDTH/Level.TILESIZE + 1; i++){
			for(int j = 0; j < Window.HEIGHT/Level.TILESIZE + 1; j++) {
				g.drawImage(Assets.floor3, i*WIDTH, j*HEIGHT, null);
				
			}
		}
		
		
		if(State.currentState != null) {
			State.currentState.render(g);
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
		Assets.init();
		menuState = new MenuState(this);
		gameState = new GameState(this);
		loadingState = new LoadingState(this);
		levelSelectorState = new LevelSelectorState(this);
		comoJogar = new ComoJogar(this);
		
		State.currentState = loadingState;
		Assets.intro.play_more();
		
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
	
	public State getGameState(){
		return gameState;
	}
	public State getLevelSelectorState(){
		Assets.intro.stop();
		Assets.tema.play_more();
		return levelSelectorState;
	}
	
	public State getMenuState(){
		return menuState;
	}
	
	public State getComoJogar(){
		return comoJogar;
	}
	
	
}