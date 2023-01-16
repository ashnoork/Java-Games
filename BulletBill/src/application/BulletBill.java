package application;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BulletBill 
{
	//creatign the required varaibles 
	private Image imgBullet; 
	private ImageView iviewBullet; 
	private int xPos, yPos, width, height;
	private Random rand; 
	
	//constructor that initializes variables 
	public BulletBill()
	{
		imgBullet = new Image("file:bulletWest.png");
		iviewBullet = new ImageView(imgBullet); 
		
		rand = new Random(); 
		
		xPos = -100; 
		yPos = -100; 
		
		width = (int) imgBullet.getWidth(); 
		height = (int) imgBullet.getHeight(); 
	}
	//returns the Imageview of the bullet
	public ImageView getImage()
	{
		return iviewBullet; 
	}
	//sets the xPos of the bullet to x psoition that is paseed in
	public void setX(int x)
	{
		xPos = x; 
		iviewBullet.setX(xPos);
	}
	//sets the ypos of the bullet to the y position passed in 
	public void setY(int y)
	{
		yPos = y; 
		iviewBullet.setY(yPos); 
	}
	//returns the x Position of the bullet
	public int getX()
	{
		return xPos; 
	}
	//returns the y Position of the bullet
	public int getY()
	{
		return yPos; 
	}
	//return height of the bullet image 
	public int getHeight()
	{
		return height; 
	}
	//return width of the bullet image
	public int getWidth()
	{
		return width; 
	}
	//move the bullet left based on the speed passed in
	public void move(int pixels)
	{
		xPos-= pixels; 
		iviewBullet.setX(xPos); 
	}
	//setting the loocation of the bullet
	public void setLocation(int frameWidth, int frameHeight)
	{
		//xPos id outside the room on the right side 
		xPos =  frameWidth + width;
		//random y position along the y axis from top to bottom on right side 
		yPos = rand.nextInt(frameHeight - (int) height );
		
		//setting the position of the image 
		iviewBullet.setX(xPos);
		iviewBullet.setY(yPos);
	}
}
