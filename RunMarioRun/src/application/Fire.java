package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Fire
{
	private Image imgFire; 
	private ImageView iviewFire;  
	private double xPos, yPos, width, height; 
	private int dir; 
	public final static int EAST =0, WEST =1; 
	
	public Fire()
	{
		
		imgFire = new Image("file:FireBall.png"); 
		iviewFire = new ImageView(imgFire); 
	
		dir = EAST; 
		xPos = -100; 
		yPos = -100; 
		
		iviewFire.setX(xPos);
		iviewFire.setY(yPos);
		
		width = iviewFire.getImage().getWidth();
		height = iviewFire.getImage().getHeight(); 
	}
	public int getDirection()
	{
		return dir;
	}
	public double getHeight()
	{
		return height; 
	}
	public double getWidth()
	{
		return width; 
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
		
		if (dir == EAST)
		{
			xPos += 5; 
		}
		else 
		{
			xPos -= 5; 
		}
		iviewFire.setX(xPos);
	}
	public void setPosition(double playerX, double playerY, int dir)
	{
		this.dir = dir; 
		if(this.dir == EAST)
		{
			xPos = playerX + 75; 
		}
		else 
		{
			xPos = playerX; 
		}
		yPos = playerY + 50; 
		
		iviewFire.setX(xPos);
		iviewFire.setY(yPos);
	}
	public void setX(int x) 
	{
		xPos = x; 
		iviewFire.setX(xPos);
	}
	public void setY(int y)
	{
		yPos = y; 
		iviewFire.setY(yPos);
	}
//	public boolean isOffScreen(double edge)
//	{
//		boolean offScreen = false; 
//		if (xPos>=edge || xPos+width<=0)
//		{
//			offScreen = true; 
//			fired = false; 
//		}
//		else 
//		{
//			offScreen = false; 
//		}
//		
//		return offScreen; 
//	}
	public ImageView getNode()
	{
			iviewFire.setImage(imgFire);
		
		return iviewFire; 
	}
	
	
}
