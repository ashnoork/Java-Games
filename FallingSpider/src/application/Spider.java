package application;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Spider
{
	private Image imgSpider, imgDead;
	private ImageView ivSpider; 
	private Random rnd; 
	private int xPos, yPos, speed; 
	private double width, height; 
	private boolean squash; 
	private double roomWidth, roomHeight; 
	
	public Spider(double sceneWidth, double sceneHeight)
	{
		rnd = new Random(); 
		speed = rnd.nextInt(10)+1; 
		
		roomWidth = sceneWidth; 
		roomHeight  = sceneHeight;
		
		xPos = rnd.nextInt((int)roomWidth - (int) width - 80); 
		yPos = (int) -height; 
		
		squash = false; 
		imgSpider = new Image("file:Spider_Crawl.gif");
		ivSpider = new ImageView(imgSpider); 
		imgDead = new Image("file:Blood_splat.png"); 
		ivSpider.setX(xPos);
		ivSpider.setY(yPos);
		
		width = imgSpider.getHeight();
		height = imgSpider.getHeight(); 
		 
	}
	public void setLocation(double frameWidth, double frameHeight)
	{
		speed = rnd.nextInt(10)+1; 
		xPos = rnd.nextInt((int)frameWidth - (int) width); 
		yPos = (int) -height; 
		
		ivSpider.setX(xPos);
		ivSpider.setY(yPos);
	}
	public void move()
	{
		yPos += speed; 
		ivSpider.setY(yPos);
	}
	public void setX(int x)
	{
		xPos = x; 
	}
	public void setY(int y)
	{
		yPos = y; 
	}
	public int getX()
	{
		return xPos; 
	}
	public int getY()
	{
		return yPos; 
	}
	public double getWidth()
	{
		return width; 
	}
	public double getHeight()
	{
		return height; 
	}
	public ImageView getNode()
	{
		if (squash == false)
		{
			ivSpider.setImage(imgSpider);
		}
		else if (squash == true)
		{
			ivSpider.setImage(imgDead);
			ivSpider.setX(roomWidth/2 - width -20);
			ivSpider.setY(roomHeight/2 - height -20);
		}
		
		return ivSpider; 
	}
	public void isDead()
	{
		squash = true;
	}
	public void isAlive()
	{
		squash = false; 
	}
	public boolean getSpiderState()
	{
		return squash; 
	}
}
