package application;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Goomba {

	public double xPos, yPos; 
	private double width, height; 
	private Image imgGoomba; 
	private ImageView ivGoomba; 
	private Random rnd; 
	public int xDir, yDir; 
	private int speed; 
	
	public Goomba()
	{
		rnd = new Random();	
		xDir = rnd.nextInt(2) + 1; 
		yDir = rnd.nextInt(2) + 1;  
		speed = 5; 
		
		imgGoomba = new Image("file:Goomba.png"); 
		ivGoomba = new ImageView(imgGoomba); 
		
		xPos =0; 
		yPos=0; 
		
		
		ivGoomba.setX(xPos);
		ivGoomba.setY(yPos);
		
		width =ivGoomba.getFitWidth();
		height =ivGoomba.getFitHeight();	
	}
	
	public double getHeight()
	{
		return height; 
	}
	public double getWidth()
	{
		return width; 
	}
	public ImageView getNode()
	{
		return ivGoomba; 
	}
	public double getX()
	{
		return xPos; 
	}
	public double getY()
	{
		return yPos; 
	}
	public void move()
	{
		if(xDir == 1)
		{
			 xPos -= speed; 
		}
		else 
		{
			xPos += speed; 
		}
		if(yDir == 1)
		{
			yPos += speed; 
		}
		else
		{
			yPos -= speed; 
		}
	
		ivGoomba.setX(xPos); 
		ivGoomba.setY(yPos);
	}
	public void setX(double x)
	{
		xPos = x; 
		ivGoomba.setX(xPos);
	}
	public void setY(double y)
	{
		yPos = y; 
		ivGoomba.setY(yPos);
	}


}

	