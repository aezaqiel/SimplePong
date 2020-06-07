package com.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Inputs extends KeyAdapter implements KeyListener
{
	private Paddle p1;
	boolean UP_PRESSED = false;
	boolean DOWN_PRESSED = false;
	
	private Paddle p2;
	boolean W_PRESSED = false;
	boolean S_PRESSED = false;
	
	public Inputs(Paddle pd1, Paddle pd2)
	{
		p1 = pd1;
		p2 = pd2;
	}
	
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_UP)
		{
			p2.changeDirection(-1);
			UP_PRESSED = true;
		}
		if(key == KeyEvent.VK_DOWN)
		{
			p2.changeDirection(1);
			DOWN_PRESSED = true;
		}
		if(key == KeyEvent.VK_W)
		{
			p1.changeDirection(-1);
			W_PRESSED = true;
		}
		if(key == KeyEvent.VK_S)
		{
			p1.changeDirection(1);
			S_PRESSED = true;
		}
	}

	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_UP)
		{
			UP_PRESSED = false;
		}
		if(key == KeyEvent.VK_DOWN)
		{
			DOWN_PRESSED = false;
		}
		if(key == KeyEvent.VK_W)
		{
			W_PRESSED = false;
		}
		if(key == KeyEvent.VK_S)
		{
			S_PRESSED = false;
		}
		
		if(UP_PRESSED == false && DOWN_PRESSED == false)
			p2.stop();
		if(W_PRESSED == false && S_PRESSED == false)
			p1.stop();
	}
}
