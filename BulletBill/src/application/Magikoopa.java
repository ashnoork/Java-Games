package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Magikoopa {
	
	//creating teh required variables 
	private int xPos, yPos, width, height, direction; 
	private Image imgEast, imgWest, imgDeadEast, imgDeadWest; 
	private ImageView iviewMK; 
	public final static int EAST =0, WEST =1; 
	public boolean dead; 
	
	//creating the constructor and initializing the variables 
	public Magikoopa()
	{
		xPos = 0; 
		yPos =0; 
		
		imgEast = new Image("file:koopaEast.png"); 
		imgWest = new Image("file:koopaWest.png");
		imgDeadEast = new Image("file:deadEast.png"); 
		imgDeadWest = new Image("file:deadWest.png"); 
		iviewMK = new ImageView(imgEast); 
		
		iviewMK.setX(xPos);
		iviewMK.setY(yPos);
		
		direction = EAST; 
		 
		dead = false; 
	}
	
	//set the x position to the passed in value 
	public void setX(int x)
	{
		xPos = x; 
		iviewMK.setX(xPos);
	}
	//set the y position to the passed in value 
	public void setY(int y)
	{
		yPos = y; 
		iviewMK.setY(yPos);
	}
	//return the x position of the Magikoopa
	public int getX()
	{
		return xPos; 
	}
	//return the y position of the Magikoopa
	public int getY()
	{
		return yPos; 
	}
	//called when up key is pressed to move up
	public void moveUp(int pixels)
	{
		yPos-= pixels; 
		iviewMK.setY(yPos);
	}
	//called when down key is pressed to move down
	public void moveDown(int pixels)
	{
		yPos += pixels; 
		iviewMK.setY(yPos);
	}
	//called when left key is pressed and moves left 
	public void moveLeft(int pixels)
	{
		direction = WEST; 
		xPos -= pixels; 
		iviewMK.setX(xPos);
	}
	//called when right key is pressed and moves right
	public void moveRight(int pixels)
	{
		direction = EAST; 
		xPos += pixels; 
		iviewMK.setX(xPos);
	}
	//called when magikoopa collides with a bullet so that the image can be changed 
	public void kill()
	{
		dead = true; 
	}
	//getting the based on direction and if Magikoopa is alive 
	public ImageView getImage()
	{
		if (dead == true)
		{
			if (direction == EAST)
			{
				iviewMK.setImage(imgDeadEast);
			}
			else if (direction == WEST)
			{
				iviewMK.setImage(imgDeadWest);
			}
		}
		else 
		{
			if (direction == EAST)
			{
				iviewMK.setImage(imgEast);
			}
			else if (direction == WEST)
			{
				iviewMK.setImage(imgWest);
			}
		}
		return iviewMK; 
	}
	//returns the height of Magikoopa image 
	public int getHeight()
	{
		return height; 
	}
	//rteurns the width of magikooopa image 
	public int getWidth()
	{
		return width; 
	}
	//returns the direction of Magikoopa 
	public int getDir()
	{
		return direction; 
	}
}
