package application;
	
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;


public class Main extends Application {
	
	private TextField txt; 
	private TextArea txtOutput; 
	private int check=0; 
	
	public void start(Stage primaryStage) {
		try {
			Pane root = new Pane();
			Scene scene = new Scene(root,400,600);
			
			Label title = new Label("Chat Log"); 
//			title.setAlignment(Pos.BOTTOM_LEFT);
//			title.setTextAlignment(TextAlignment.CENTER);
			title.setLayoutX(0);
			title.setLayoutY(0);
			
			Label msg = new Label("Message"); 
			msg.setLayoutX(0);
			msg.setLayoutY(510);
			
			txt = new TextField(); 
			txt.setLayoutX(100);
			txt.setLayoutY(510); 
			txt.setPrefSize(250, 10);
			txt.setFont(Font.font("Courier", 12));
			
			txtOutput = new TextArea(); 
			txtOutput.setPrefSize(400, 450); 
			txtOutput.setLayoutX(0);
			txtOutput.setLayoutY(30);
			txtOutput.setFont(Font.font("Courier", 12));
			
			
			Button send = new Button("Send"); 
			send.setLayoutX(100);
			send.setLayoutY(550);
			send.setPrefSize(250, 10);
			send.setOnAction(e -> click());
			
			
			root.getChildren().addAll(title, msg, txt, send, txtOutput);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void click()
	{
		check++;
		
		String input = txt.getText();
		
		if (check%2 == 0)
		{
			txtOutput.appendText(String.format("%30s", input + "\n"));
		}
		else 
		{
		
			txtOutput.appendText(input + "\n");
		}
		
	
		
		try
		{
			
			
			File dataFile = new File("message.txt"); 
			FileWriter out = new FileWriter (dataFile, true); 
			BufferedWriter writeFile = new BufferedWriter(out);
			
			writeFile.write(input);
			writeFile.newLine();
			
			writeFile.close(); 
			out.close(); 
		}
		catch (Exception e)
		{
			System.out.print(e.getMessage());
		}
		txt.clear();
		
	}
}
