package application;
	
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.io.FileInputStream;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {
	
	private Spider spider; 
	private AnimationTimer animation; 
	private Label lbl; 
	private int count; 
	private Timeline timer;
	private int seconds; 
	private File backFile; 
	private Media background; 
	private MediaPlayer backPlayer; 
	
	private AudioClip squish, win; 

	public void start(Stage primaryStage) {
		try {
			Image imgBack = new Image("file:Wall.jpg");
			ImageView iviewBackground = new ImageView(imgBack);  
			
			Image imgCursor = new Image("file:Newspaper.png");
			ImageView iviewC = new ImageView(imgCursor);  
			
			Pane root = new Pane();
			Scene scene = new Scene(root,imgBack.getWidth(),imgBack.getHeight());
			
			Font f = Font.loadFont(new FileInputStream(new
					File("Rough42 Becker Regular.ttf")), 25);
			
			squish = new AudioClip("file:sounds/Squish.wav"); 
			win = new AudioClip("file:sounds/Win.wav"); 
			
			backFile = new File("sounds/Background.mp3"); 
			background = new Media(backFile.toURI().toString()); 
			backPlayer = new MediaPlayer(background); 
			backPlayer.setOnEndOfMedia(new Runnable(){
				public void run()
				{
					backPlayer.seek(Duration.ZERO); 
				}
			});
			backPlayer.play();
			
			seconds=3; 
			count=5; 
			spider = new Spider(imgBack.getWidth(),imgBack.getHeight()); 
			
			lbl = new Label("SPIDERS REMAINGNG: " + count); 
			lbl.setFont(f);
			lbl.setTextFill(Color.WHITE);
			lbl.setLayoutX(0);
			lbl.setLayoutY(0);
			
			root.getChildren().addAll(iviewBackground, spider.getNode(), lbl, iviewC); 
			primaryStage.setScene(scene);
			primaryStage.show();
			
			animation = new AnimationTimer(){
				public void handle(long val)
				{
					spider.move(); 
					
					if (spider.getY()>root.getHeight())
					{
						spider.setLocation(imgBack.getWidth(),imgBack.getHeight());
					}
				
				}	
			}; 
			animation.start();
			
			KeyFrame kf = new KeyFrame(Duration.millis(1000), new
					EventHandler<ActionEvent>() {
				
				public void handle(ActionEvent e)
				{
					seconds--; 
					
					if(seconds == 0)
					{
						animation.start();
						timer.stop(); 
						seconds = 3; 
						spider.setLocation(imgBack.getWidth(),imgBack.getHeight());
						spider.isAlive();
						spider.getNode(); 
					}
				}
			
			}); 
			
			timer = new Timeline(kf); 
			timer.setCycleCount(Timeline.INDEFINITE);
			
			scene.setOnMousePressed(new EventHandler<MouseEvent>() 
			{
				public void handle(MouseEvent e) {
					
					if (e.getX() >= spider.getX() &&
							e.getX() <= spider.getX() + spider.getWidth() &&
							e.getY() >= spider.getY() &&
							e.getY() <= spider.getY() + spider.getHeight())
							{
								count--;
								squish.play();
								spider.isDead(); // Tells spider class spider is squashed
								spider.getNode(); // returns squashed image
								animation.stop();
								timer.play(); // Play KeyFrame to “pause” game for 3 seconds
								lbl.setText("SPIDERS REMAINING: " + count);
								
								if(count == 0)
								{
									backPlayer.stop(); 
									win.play();
									Label over = new Label("Game Over!\nYou Win"); 
									over.setStyle("-fx-font-size: 40");
									over.setTextFill(Color.RED);
									over.setLayoutX(root.getWidth()/2 - over.getPrefWidth()/2 - 50);
									over.setLayoutY(root.getHeight()/2 - over.getPrefHeight()/2 - 50);
									
									root.getChildren().add(over); 
									root.getChildren().remove(spider.getNode()); 
									
								}
							}
				}
				});
				scene.setOnMouseMoved(new EventHandler<MouseEvent>() 
				{
					public void handle(MouseEvent e) {
					
						iviewC.setX(e.getX() - imgCursor.getWidth() / 2);
						iviewC.setY(e.getY() - imgCursor.getHeight() / 2);
					}
					});
			
			
		
	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
