package application;
	
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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


public class Main extends Application {
	
	private Mario mario; 
	private AnimationTimer animation; 
	private boolean moveRight, moveLeft, moveDown, moveUp; 
	private Image bowser, coinimg;
	private ImageView bowserview, bview, coinview; 
	private ArrayList<Fire> fire = new ArrayList<Fire>(); 
	private ArrayList<ImageView> coin = new ArrayList<ImageView>(); 
	private Timeline bulletTimer, goombaTimer, coinTimer; 
	private int fireCount, gCount, coinCount;
	private ArrayList<Goomba> goomba = new ArrayList<Goomba>();  
	private int xDir, yDir; 
	private Random rnd; 
	private int score;
	private Label lblScore; 
	private File backFile; 
	private Media background; 
	private MediaPlayer backPlayer; 
	private AudioClip collect, shoot; 

	public void start(Stage primaryStage) {
		try {
				
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
			
			collect = new AudioClip("file:coin.wav"); 
			shoot = new AudioClip("file:fireball.wav");
			
			rnd = new Random(); 
			 
			xDir = rnd.nextInt(5) + 1; 
			yDir = rnd.nextInt(5) + 1; 
			
			bowser = new Image("file:Bowser.png"); 
			
			bview = new ImageView(bowser);
			bview.setFitHeight(80);
			bview.setPreserveRatio(true);
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("HAHA!\nTry and stop me Mario");
			alert.setHeaderText(null);
			alert.setTitle("Bowser");
			alert.setGraphic(bview);
			alert.showAndWait(); 
			
			Pane root = new Pane();
			Scene scene = new Scene(root,900,600);
			
			fireCount = -1; 
			score =0; 
			gCount = -1; 
			coinCount = -1; 
			mario = new Mario(); 
			
			lblScore = new Label("SCORE: " + score); 
			lblScore.setStyle("-fx-font-size: 25");
			lblScore.setTextFill(Color.WHITE);
			lblScore.setLayoutX(scene.getWidth() - 150);
			lblScore.setLayoutY(15);
			
			bowserview = new ImageView(bowser);
			bowserview.setFitHeight(200);
			bowserview.setPreserveRatio(true);
			
			coinimg = new Image("file:Coin.png"); 
			
			
			root.getChildren().addAll(mario.getNode(), bowserview, lblScore);
			
			primaryStage.setScene(scene);
			primaryStage.show();
			KeyFrame kfBullet = new KeyFrame(Duration.millis(10), new
					EventHandler<ActionEvent>() {
						public void handle(ActionEvent e) {
						
						for (int i=0; i<fire.size(); i++)
						{
							fire.get(i).move(); 
							fire.get(i).getNode(); 
							
							for (int j=0; j<fire.size(); j++)
							{
								for (int m=0; m<goomba.size(); m++)
								{
									if(fire.get(j).getNode().getBoundsInParent().intersects(goomba.get(m).getNode().getBoundsInParent()))
									{
										root.getChildren().removeAll(fire.get(j).getNode(), goomba.get(m).getNode()); 
										goomba.remove(m);
										gCount--; 
										fireCount--; 
									}
								}
							}
							
						
						}
						}
			}); 
			
			bulletTimer = new Timeline(kfBullet); 
			bulletTimer.setCycleCount(Timeline.INDEFINITE);
			
			KeyFrame kfGoomba = new KeyFrame(Duration.millis(3000), new
					EventHandler<ActionEvent>() {
						public void handle(ActionEvent e) {
						
							gCount++; 
							goomba.add(gCount, new Goomba()); 
							//goomba.get(gCount).setLocation(0, 0);
							root.getChildren().addAll(goomba.get(gCount).getNode());
						}
			}); 
			goombaTimer = new Timeline(kfGoomba); 
			goombaTimer.setCycleCount(Timeline.INDEFINITE);
			goombaTimer.play();
			
			KeyFrame kfCoin = new KeyFrame(Duration.millis(4000), new
					EventHandler<ActionEvent>() {
						public void handle(ActionEvent e) {
						
							coinview = new ImageView(coinimg); 
							int x = rnd.nextInt((int) (scene.getWidth() - coinimg.getWidth())); 
							int y = rnd.nextInt((int) (scene.getHeight() - coinimg.getHeight())); 
							coinCount++; 
							//System.out.print(coinCount);
							//index out of bounds??
							coin.add(coinCount, coinview);
							coin.get(coinCount).setX(x); 
							coin.get(coinCount).setY(y); 
							root.getChildren().add(coin.get(coinCount));
						}
			}); 
			coinTimer = new Timeline(kfCoin); 
			coinTimer.setCycleCount(Timeline.INDEFINITE);
			coinTimer.play();
			
			scene.setOnKeyPressed(new EventHandler<KeyEvent>() {			
				public void handle(KeyEvent e)
				{
					
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
							if(e.getCode() == KeyCode.SPACE)
							{
								fireCount++; 
								//shoot.setVolume();
								shoot.play();
								fire.add(fireCount, new Fire()); 
								fire.get(fireCount).setPosition(mario.getX(), mario.getY(), mario.getDirection());
								root.getChildren().addAll(fire.get(fireCount).getNode());
								bulletTimer.play(); 
								
							}
						}
					
				}
			});
			scene.setOnKeyReleased(new EventHandler<KeyEvent>(){
				public void handle(KeyEvent e) {
					
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
			
			//Instantiate the AnimationTimer
			animation = new AnimationTimer()
			{
				public void handle(long val)
				{
					
					if (moveRight == true)
					{
						mario.moveRight();
						if(mario.getX() + mario.getWidth() > scene.getWidth())
						{
							mario.setX(scene.getWidth() - mario.getWidth());
						}
					}
					else if(moveLeft ==  true)
					{
						mario.moveLeft();
						if(mario.getX()<0) {
							mario.setX(0);
						}
					}
					else if(moveDown == true)
					{
						mario.moveDown(); 
						if(mario.getY() +  mario.getHeight() + 20 > scene.getHeight())
						{
							mario.setY(scene.getHeight() - mario.getHeight() - 25);
						}
					}
					else if(moveUp == true)
					{
						mario.moveUp();
						if(mario.getY()<0)
						{
							mario.setY(0); 
						}
					}
					mario.getNode();
					
					
					for (int i=0; i<goomba.size(); i++)
					{
						goomba.get(i).move();
						goomba.get(i).getNode(); 
						
						//if the ball hits the left or the right
						if(goomba.get(i).getX() + goomba.get(i).getWidth() >= root.getWidth())
						{
							goomba.get(i).xDir = 1; 
							
						}
						else if(goomba.get(i).getX()<=0)
						{
							goomba.get(i).xDir = 2; 
						}
						//if the ball hits the top or bottom 
						else if (goomba.get(i).getY() < 0 )
						{
							goomba.get(i).yDir = 1; 
						}
						else if(goomba.get(i).getY() + goomba.get(i).getHeight() >= root.getHeight())
						{
							goomba.get(i).yDir = 2; 
						}
						
						if(goomba.get(i).getNode().getBoundsInParent().intersects(mario.getNode().getBoundsInParent()))
						{
							
//							goomba.get(i).getNode().setLayoutX(-200);
							root.getChildren().remove( goomba.get(i).getNode()); 
							goomba.remove(i);
							gCount--; 
							score -= 20; 
							lblScore.setText("SCORE: " + score);
								
						}
							
						
					}
				
					for (int k = 0; k< coin.size(); k++)
					{
						if(coin.get(k).getBoundsInParent().intersects(mario.getNode().getBoundsInParent()))
						{
							//collect.setVolume(50f);
							collect.play();
							//coin.get(k).setLayoutX(-200);
							root.getChildren().remove(coin.get(k)); 
							coin.remove(k);
							coinCount--; 
							score += 10; 
							lblScore.setText("SCORE: " + score);
								
						}
					}
					 
					
					if(score<0)
					{
						animation.stop(); 
						bulletTimer.stop();
						goombaTimer.stop();
						coinTimer.stop(); 
						
						Platform.runLater(new Runnable() {
							public void run()
							{
								
									Alert alert = new Alert(AlertType.INFORMATION);
									alert.setHeaderText(null);
									alert.setTitle("Game Over");
									alert.setContentText("You LOSE. Game Over!\nBetter luck next Time");
									alert.showAndWait();
									System.exit(0);
								
							}
							});
					}
					else if(score > 70)
					{
						animation.stop(); 
						bulletTimer.stop();
						goombaTimer.stop();
						coinTimer.stop(); 
						
						Platform.runLater(new Runnable() {
							public void run()
							{
								
									Alert alert = new Alert(AlertType.INFORMATION);
									alert.setHeaderText(null);
									alert.setTitle("WIN");
									alert.setContentText("Congratulations!\nYou WIN!");
									alert.showAndWait();
									System.exit(0);
								
							}
							});
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
