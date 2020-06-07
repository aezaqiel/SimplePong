package com.main;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable
{
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 900;
	public static final int HEIGHT = WIDTH * 9 / 16;
	public static final String TITLE = "Pong";
	
	public boolean running = false;
	private Thread thread;
	
	private Ball ball;
	private Paddle paddleL;
	private Paddle paddleR;

	public Game()
	{
		canvasSetup();
		initialize();
		new Window(TITLE, this);
		
		this.addKeyListener(new Inputs(paddleL, paddleR));
		this.setFocusable(true);
	}

	private void initialize() 
	{
		ball = new Ball();
		
		paddleL = new Paddle(true);
		paddleR = new Paddle(false);
	}

	private void canvasSetup() 
	{
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
	}

	public void run() 
	{
		this.requestFocus();
		 
	    long lastTime = System.nanoTime();
	    double amountOfTicks = 120.0;
	    double ns = 1000000000 / amountOfTicks;
	    double delta = 0;
	    long timer = System.currentTimeMillis();
	    int frames = 0;
	    while(running) 
	    {
	    	 long now = System.nanoTime();
	    	 delta += (now - lastTime) / ns;
	    	 lastTime = now;
	    	 while(delta >= 1) 
	    	 {
	    		 update();
	    		 delta--;
	    	 }
	    draw();
	    frames++;

	    if(System.currentTimeMillis() - timer > 1000) 
	    {
	    	System.out.println("FPS: " + frames);
	    	timer += 1000;
	    	frames = 0;
	    } 
	    }
		
		stop();
	}
	
	public void draw()
	{
		BufferStrategy buffer = this.getBufferStrategy();
		
		if(buffer == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = buffer.getDrawGraphics();
		
		drawBackground(g);
		
		ball.draw(g);
		paddleL.draw(g);
		paddleR.draw(g);
		
		g.dispose();
		buffer.show();
	}
	
	public void update()
	{
		ball.update(paddleL, paddleR);
		
		paddleL.update(ball);
		paddleR.update(ball);
	}
	
	public void drawBackground(Graphics g)
	{
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.setColor(Color.white);
		Graphics2D g2d = (Graphics2D) g;
		Stroke  dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] {10}, 0);
		g2d.setStroke(dashed);
		g2d.drawLine(WIDTH/2, 0, WIDTH/2, HEIGHT);
	}
	
	public void start()
	{
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	public void stop()
	{
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
	}
	
	public static int sign(double d)
	{
		if(d >= 0.5)
			return 1;
		return -1;
	}
	
	public static int ensureRange(int val, int min, int max) 
	{
		return Math.min(Math.max(val, min), max);
	}
	
	public static void main(String[] args)
	{
		new Game();
	}

}
