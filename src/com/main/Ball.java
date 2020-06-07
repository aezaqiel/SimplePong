package com.main;

import java.awt.Color;
import java.awt.Graphics;

public class Ball 
{
	public static final int SIZE = 18;
	
	private int x, y;
	private int xVelocity, yVelocity;
	private int speed = 2; // later make a menu for difficulty settings change this number
	
	public Ball()
	{
		reset();
	}
	
	public void reset()
	{
		x = Game.WIDTH / 2 - SIZE / 2;
		y = Game.HEIGHT / 2 - SIZE / 2;
		
		xVelocity = Game.sign(Math.random());
		yVelocity = Game.sign(Math.random());
	}
	
	public void changeYDir()
	{
		yVelocity *= -1;
	}
	public void changeXDir()
	{
		xVelocity *= -1;
	}

	public void draw(Graphics g) 
	{
		g.setColor(Color.white);
		g.fillRect(x, y, SIZE, SIZE);
	}

	public void update(Paddle p1, Paddle p2) 
	{
		x += xVelocity * speed;
		y += yVelocity * speed;
		
		if(y + SIZE >= Game.HEIGHT || y <= 0)
		{
			changeYDir();
		}
		
		if(x + SIZE >= Game.WIDTH)
		{
			p1.addScore();
			reset();
		}
		if(x <= 0)
		{
			p2.addScore();
			reset();
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
