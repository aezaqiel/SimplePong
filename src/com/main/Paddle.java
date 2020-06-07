package com.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Paddle 
{
	private int x, y;
	private int velocity;
	private int speed = 5;
	private int width = 16, height = 100;
	private int score = 0;
	private Color color;
	private boolean Left;
	
	public Paddle(boolean Left)
	{
		this.Left = Left;
		
		if(Left)
			x = 0;
		else
			x = Game.WIDTH - width;
		
		y = Game.HEIGHT / 2 - height /2;
		
	}
	
	public void addScore()
	{
		score++;
	}

	public void draw(Graphics g) 
	{
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
		
		int sx;
		String scoreText = Integer.toString(score);
		Font font = new Font("Roboto", Font.PLAIN, 50);
		
		int strWidth = g.getFontMetrics(font).stringWidth(scoreText);
		int padding = 25;
		
		if(Left)
			sx = Game.WIDTH / 2 - padding - strWidth;
		else
			sx = Game.WIDTH / 2 + padding;
		
		g.setFont(font);
		g.drawString(scoreText, sx, 50);
	}

	public void update(Ball ball) 
	{
		y = Game.ensureRange(y += velocity, 0, Game.HEIGHT - height);
		
		int ballX = ball.getX();
		int ballY = ball.getY();
		
		if(Left)
		{
			if(ballX <= width && ballY + ball.SIZE >= y && ballY <= y + height)
				ball.changeXDir();
		}else
		{
			if(ballX + ball.SIZE >= Game.WIDTH - width && ballY + ball.SIZE >= y && ballY <= y + height)
				ball.changeXDir();
		}
		
	}

	public void changeDirection(int i) 
	{
		velocity = speed * i;
	}
	
	public void stop()
	{
		velocity = 0;
	}
}
