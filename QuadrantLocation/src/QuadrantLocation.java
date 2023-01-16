import java.util.Random;
import java.util.Scanner;

public class QuadrantLocation 
{

	public static void main(String[] args)
	{
		
		int x=0, y=0, q=0; 
		
		Random rnd = new Random();
		
		while(x==0) 
		{
			x = rnd.nextInt(101) - 50; 
		}
		while(y==0) 
		{
			y = rnd.nextInt(101) - 50; 
		}
		
		if (x > 0 && y>0)
		{
			q = 1;  
		}
		else if (x > 0 && y<0)
		{
			q = 4;  
		}
		else if (x < 0 && y>0)
		{
			q = 2;  
		}
		else if (x < 0 && y<0)
		{
			q = 3;  
		}
		System.out.print("X:" + x);
		
		System.out.print("\nY:" + y);
		
		System.out.print("\nQuadrant:" +q);
	}

}
