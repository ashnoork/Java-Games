/* NAME: Ashnoor
 * DATE: May 26, 2022
 * COURSE: ICS 4U1
 * PROGRAM DESCRIPTION: The game is Magikoopa vs. Bullet Bill. As the game starts we see Magikoopa in the left corner of the room. 
 * The objective is to avoid the bullets generated from the right side of the screen. This is done by controlling the player using 
 * up, down,left and right keys. The bullets are generated at a random position along the y axis outside of the room. For each bulle
 * that you avoid, the player gets 10 points as soon as it leaves the room. There is background music playing throughout the game. 
 * Every 100 points, a laugh sound is played. When Magikoopa collides with a bullet a lsoing sound is played and Magikoopa dies. 
 * The image changes and an alert is displayed showing the score and the message of game being over. 
 * 
*/


package application;
	
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class Main extends Application {
	
	private Magikoopa mk; 
	private AnimationTimer animation; 
	private boolean moveLeft, moveRight, moveUp, moveDown, collide; 
	private int speed, bulletCount, bulletSpeed, score; 
	private Timeline bulletTimer; 
	private Label title, lblScore; 
	private MediaPlayer backPlayer; 
	private File backFile; 
	private Media background; 
	private ArrayList<BulletBill> bullet = new ArrayList<BulletBill>(); 
	private Font fTitle, fScore; 
	private AudioClip gameOver, laugh; 
	private ImageView mkAlert; 

	public void start(Stage primaryStage) {
		try {
			
			//playing background music Media Player 
			backFile = new File("Background.mp3"); 
			background = new Media(backFile.toURI().toString()); 
			backPlayer = new MediaPlayer(background); 
			backPlayer.setVolume(0.5);
			backPlayer.setOnEndOfMedia(new Runnable(){
				public void run()
				{
					backPlayer.seek(Duration.ZERO); 
				}
			});
			backPlayer.play();
			
			//creating other sounds in the game using audioclip
			gameOver = new AudioClip("file:GameOver.wav"); 
			laugh = new AudioClip("file:Laugh.wav"); 
			
			//creating the image for the background 
			Image imgBack = new Image("file:game-background.png");
			ImageView iviewBackground = new ImageView(imgBack); 
			
			//loading the song and setting its size to the height and width 
			Pane root = new Pane();
			Scene scene = new Scene(root, imgBack.getWidth(),imgBack.getHeight());
			
			//creating font for displayign teh title and score 
			fTitle =  Font.loadFont(new FileInputStream(new
					File("Mufferaw Regular.ttf")), 40); 
			
			fScore =  Font.loadFont(new FileInputStream(new
					File("Mufferaw Regular.ttf")), 30); 
			
			//variable to check if magikoopa has died 
			collide = false; 
			
			//setting variables 
			speed = 8;
			score =0; 
			bulletSpeed = 10; 
			bulletCount = -1; 
			
			//calling the Magikoopa class 
			mk = new Magikoopa(); 
			
			//creating the label for the title 
			title = new Label("Magikoopa vs. Bullet Bill");
			title.setFont(fTitle);
			title.setLayoutX(scene.getWidth()/2 - 200);
			title.setLayoutY(0);
			
			//creating the label for the score 
			lblScore = new Label("SCORE: " + score); 
			lblScore.setFont(fScore);
			lblScore.setLayoutX(scene.getWidth()/2 - 50);
			lblScore.setLayoutY(40);
			
			//adding objects to the pane 
			root.getChildren().addAll(iviewBackground, mk.getImage(), title, lblScore); 
			
			//show the scene
			primaryStage.setTitle("Magikoopa vs. Bullet Bill");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			//when the user presses a key this method is called
			scene.setOnKeyPressed(new EventHandler<KeyEvent>() {			
				public void handle(KeyEvent e)
				{
						//checking if keys are pressed and based on the key setting the corresponding 
						//variable to true. 
					
						if(e.getEventType() == KeyEvent.KEY_PRESSED)
						{
							if (e.getCode() == KeyCode.RIGHT)
							{
								
								moveRight = true; 
							}
							else if (e.getCode() == KeyCode.LEFT)
							{
								moveLeft = true;
							}
							else if (e.getCode() == KeyCode.UP)
							{
								moveUp = true; 
								
							}
							else if (e.getCode() == KeyCode.DOWN)
							{
								moveDown = true; 
								
							}
						}
					
				}
			});
			
			//when a key is released 
			scene.setOnKeyReleased(new EventHandler<KeyEvent>(){
				public void handle(KeyEvent e) {
					
					//if a key is released setting the corresponding variable to false
					
					if (e.getCode() == KeyCode.RIGHT)
					{
						moveRight = false;
					}
					else if (e.getCode() == KeyCode.LEFT)
					{
						moveLeft = false;

					}
					else if (e.getCode() == KeyCode.UP)
					{
						moveUp = false;
						
					}
					else if (e.getCode() == KeyCode.DOWN)
					{
						moveDown = false;
						
					}				
			}
		}); 
			
			//keyframe to generate the bullets every 300 milliseconds
			KeyFrame kfBullet = new KeyFrame(Duration.millis(300), new
					EventHandler<ActionEvent>() {
						public void handle(ActionEvent e) {
						
							//every 300 milliseconds a new bullet class is added to the bullet arrayList. 
							bulletCount++; 
							bullet.add(bulletCount, new BulletBill()); 
							
							//calling the ste location method which sets it to a random location 
							bullet.get(bulletCount).setLocation((int) scene.getWidth(), (int)scene.getHeight());
							
							//adding each bullet generated to the pane 
							root.getChildren().addAll(bullet.get(bulletCount).getImage());
						}
			}); 
			//adding the keyframe to the bullet timer and playing it. 
			bulletTimer = new Timeline(kfBullet); 
			bulletTimer.setCycleCount(Timeline.INDEFINITE);
			bulletTimer.play();
			
			//animation to move the Magikoopa and the bullet 
			animation = new AnimationTimer()
			{
				public void handle(long val)
				{
					
					//based on the move varibale that is true, calling the corresponding method from the 
					//Magikoopa class and moving the image 
					if (moveRight == true)
					{
						mk.moveRight(speed);
						
						//checking if we hit the right side of the room and resetting the location 
						if(mk.getX() + mk.getWidth() +100  > scene.getWidth())
						{
							mk.setX((int)scene.getWidth() - mk.getWidth() -100);
						}
					}
					else if(moveLeft ==  true)
					{
						
						mk.moveLeft(speed);
						//checking if we hit the left side of the room and resetting the location 
						if(mk.getX()<0) {
							mk.setX(0);
						}
					}
					else if(moveDown == true)
					{
						//checking if we hit the bottom of the room and resetting the location 
						mk.moveDown(speed); 
						if(mk.getY() +  mk.getHeight() + 55 > scene.getHeight())
						{
							mk.setY((int)scene.getHeight() - mk.getHeight() - 55);
						}
					}
					else if(moveUp == true)
					{
						mk.moveUp(speed);
						//checking if we hit the top of the room and resetting the location 
						if(mk.getY()<0)
						{
							mk.setY(0); 
						}
					}
					//getting the ImageView from the Magikoopa class 
					mk.getImage(); 
					
					//runnign a loop through the arraylist 
					for (int i=0; i<bullet.size(); i++)
					{
						//moving each bullet and getting its image 
						bullet.get(i).move(bulletSpeed);
						bullet.get(i).getImage(); 
						
						//checking if the bullet is outside the room on the left side 
						if(bullet.get(i).getX()< - bullet.get(i).getWidth())
						{
							//removing the image and reducing the  bulletCount
							root.getChildren().remove(bullet.get(i).getImage());
							bullet.remove(i); 
							bulletCount--; 
							//increasing the score and changing the label
							score += 10; 
							lblScore.setText("SCORE: " + score);
							
							//everytime the score is 100 and Magikoopa is not dead playing the laugh sound
							if (score%100 == 0 && collide == false)
							{
								laugh.play(); 
							}
						}
						
						//checking if the bullet collides with Magikoopa 
						if(bullet.get(i).getImage().getBoundsInParent().intersects(mk.getImage().getBoundsInParent()))
						{
							//setting to true since the palyer is dead 
							collide = true; 
							
							//stopping all the timers and music 
							backPlayer.stop(); 
							bulletTimer.stop(); 
							animation.stop(); 
							
							//playing the losing sound
							gameOver.play(); 
							
							//getting the dead image 
							mk.dead = true; 
							mk.getImage(); 
							
							
							Platform.runLater(new Runnable() {
								public void run()
								{
										//based on the direction getting the image for the alter 
										if (mk.getDir() == mk.EAST)
										{
											mkAlert = new ImageView(new Image("file:deadEast.png")); 
										}
										else if(mk.getDir() == mk.WEST)
										{
											mkAlert = new ImageView(new Image("file:deadWest.png")); 
										}
										//displaying an alert showing game over message and the final score 
										ImageView icon = mk.getImage(); 
										Alert alert = new Alert(AlertType.INFORMATION);
										alert.setHeaderText(null);
										alert.setGraphic(mkAlert);
										alert.setTitle("GAME OVER!");
										alert.setContentText("Game Over!\nYour score was: " + score + "!");
										alert.showAndWait();
										System.exit(0);
									
								}
								});
								
						}
					}
					
				}
			};
			animation.start();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
