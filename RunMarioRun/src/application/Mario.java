package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Mario 
{
	private Image imgRight, imgLeft; 
	private ImageView iviewPlayer; 
	public final static int RIGHT =0, LEFT = 1, UP = 2, DOWN =3; 
	private int dir; 
	private double xPos, yPos, width, height; 
	
	public Mario()
	{
		imgRight = new Image("file:MarioRIGHT.png");
		imgLeft = new Image("file:MarioLEFT.png");
		iviewPlayer = new ImageView(imgRight); 
		
		xPos =0; 
		yPos =0; 
		dir = RIGHT; 
		
		width = iviewPlayer.getImage().getHeight(); 
		height = iviewPlayer.getImage().getWidth();
	}	
	public double getHeight()
	{
		return height; 
	}
	public double getWidth()
	{
		return width; 
	}
	public int getDirection()
	{
		return dir; 
	}
	public ImageView getNode()
	{
		if (dir == RIGHT)
		{
			iviewPlayer.setImage(imgRight);
		}
		else if(dir == LEFT)
		{
			iviewPlayer.setImage(imgLeft);
		}
		return iviewPlayer; 
	}
	public double getX()
	{
		return xPos; 
	}
	public double getY()
	{
		return yPos; 
	}
//	public void killPlayer()
//	{
//		isDead = true; 
//	}
	public void moveRight()
	{
		xPos += 10; 
		dir = RIGHT; 
		iviewPlayer.setX(xPos); 
	}
	public void moveLeft()
	{
		xPos -= 10; 
		dir = LEFT; 
		iviewPlayer.setX(xPos); 
	}
	public void moveUp()
	{	
		yPos -= 10;
		iviewPlayer.setY(yPos); 
	}
	public void moveDown()
	{
		yPos += 10;
		iviewPlayer.setY(yPos); 
	}
	public void setLocation(double x, double y)
	{
			xPos = x; 
			yPos = y; 
			
			iviewPlayer.setX(xPos); 
			iviewPlayer.setY(yPos); 
	}
	public void setX( double x)
	{
		xPos =x; 
	}
	public void setY(double y)
	{
		yPos = y; 
	}

}
