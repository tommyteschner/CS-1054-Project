// Tommy Teschner
// Project
// Dr. Mood

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import java.util.*;
import javafx.geometry.*;
import javafx.scene.paint.*;
import javafx.scene.image.*;
import javafx.event.*;
import javafx.animation.*;
import javafx.scene.input.*;
import javafx.scene.text.*;

public class Project extends Application 
{   
   BorderPane root = new BorderPane();
   
   // where the GamePanels will be added to
   FlowPane game = new FlowPane();
   
   // AnimationHandler for color changing
   AnimationHandler ah = new AnimationHandler();
   
   // Array List of GamePanels
   ArrayList<GamePanel> gp = new ArrayList<>();
   
   // main label
   Label text = new Label("");
   
   public void start(Stage stage) throws Exception 
   {
      // set gaps in the Flow Pane so each Game Panel has enough space
      game.setVgap(15);
      game.setHgap(15);
      
      // label centering
      root.setAlignment(text, Pos.CENTER);
      
      // create 16 GamePanels by adding them to the ArrayList
      // assign ButtonListeners for every button
      for(int i=0; i<16; i++) 
      {
         gp.add(new GamePanel());
         game.getChildren().add(gp.get(i));
         gp.get(i).getButtonTop().setOnAction(new ButtonListener());
         gp.get(i).getButtonBottom().setOnAction(new ButtonListener());
         gp.get(i).getButtonLeft().setOnAction(new ButtonListener());
         gp.get(i).getButtonRight().setOnAction(new ButtonListener());
      }
      
      // set the (0, 2) slot to be inactive at start-up
      gp.get(8).setVisibility(false, false, false, false, false);
      
      //top component is the label
      root.setTop(text);
      
      // center component is game FlowPane, which holds the ArrayList
      root.setCenter(game);
      
      // left component to center the middle component
      root.setLeft(new Label("\t     "));
      
      // set background color of the root
      Color c = Color.web("#f4f4f4");
      root.setBackground(new Background(new BackgroundFill(c, CornerRadii.EMPTY, Insets.EMPTY)));
      
      // check available moves based on the starting board
      moveChecker();
      
      // set the text based on balls and possible moves
      text.setText("Balls Left: "+ballCounter()+"\tPossible Moves: "+buttonCounter());
      
      ah.start();
      
      Scene scene = new Scene(root, 600, 600);
      stage.setScene(scene);
      stage.setTitle("Ball Game - Tommy Teschner");
      stage.setResizable(false);
      stage.show();
   }
   
   public void moveChecker() 
   {  
      // disables all buttons at the start of every move
      // prepares for new move check after button click
      for(int i=0; i<16; i++) 
      {
         gp.get(i).resetButtons();
      }
      
      // vertical checks going up
      for(int i=0; i<4; i++) 
      {
         if(gp.get(8+i).getVisibilityBall() == true 
         && gp.get(4+i).getVisibilityBall() == true 
         && gp.get(0+i).getVisibilityBall() == false) 
         {
            gp.get(8+i).setVisibilityBottom(true);
         
         }
         
         if(gp.get(12+i).getVisibilityBall() == true 
         && gp.get(8+i).getVisibilityBall() == true 
         && gp.get(4+i).getVisibilityBall() == false) 
         {
            gp.get(12+i).setVisibilityBottom(true);
         
         }
      }
      
      // vertical checks going down
      for(int i=0; i<4; i++) 
      {
         if(gp.get(0+i).getVisibilityBall() == true 
         && gp.get(4+i).getVisibilityBall() == true 
         && gp.get(8+i).getVisibilityBall() == false) 
         {
            gp.get(0+i).setVisibilityTop(true);
         }
         
         if(gp.get(4+i).getVisibilityBall() == true 
         && gp.get(8+i).getVisibilityBall() == true 
         && gp.get(12+i).getVisibilityBall() == false) 
         {
            gp.get(4+i).setVisibilityTop(true);
         
         }
      }
      
      // horizontal checks going right
      for(int i=0; i<4; i++) 
      {  
         // increment to decide what element to modify based on the i value 
         int increment = 0;
         
         if(i==0) {
            increment = 0;
         }
         else if(i==1) {
            increment=4;
         }
         else if(i==2) {
            increment=8;
         }
         else if(i==3) {
            increment=12;
         }
         
         if(gp.get(0+increment).getVisibilityBall() == true 
         && gp.get(1+increment).getVisibilityBall() == true 
         && gp.get(2+increment).getVisibilityBall() == false) 
         {
            gp.get(0+increment).setVisibilityRight(true);
         }
        
         if(gp.get(1+increment).getVisibilityBall() == true && gp.get(2+increment).getVisibilityBall() == true 
         && gp.get(3+increment).getVisibilityBall() == false) 
         {
            gp.get(1+increment).setVisibilityRight(true);
         }
      }
      
      // horizontal checks going left
      for(int i=0; i<4; i++) 
      {
         // increment to decide what element to modify based on the i value
         int increment = 0;
         
         if(i==0) 
         {
            increment = 0;
         }
         else if(i==1) 
         {
            increment=4;
         }
         else if(i==2) 
         {
            increment=8;
         }
         else if(i==3) 
         {
            increment=12;
         }
         
         if(gp.get(2+increment).getVisibilityBall() == true 
         && gp.get(1+increment).getVisibilityBall() == true 
         && gp.get(0+increment).getVisibilityBall() == false) 
         {
            gp.get(2+increment).setVisibilityLeft(true);
         }
         
         if(gp.get(3+increment).getVisibilityBall() == true 
         && gp.get(2+increment).getVisibilityBall() == true 
         && gp.get(1+increment).getVisibilityBall() == false) 
         {
            gp.get(3+increment).setVisibilityLeft(true);
         }
      }
   }
   
   // returns number of balls visible on the board
   public int ballCounter() 
   {
      int balls = 0; 
      
      for(int i=0; i<16; i++) 
      {
         if(gp.get(i).getVisibilityBall() == true) 
         {
            balls++;
         }
      }
      return balls;
   }
   
   // returns the number of possible moves/number of visible buttons on the board
   public int buttonCounter() 
   {
      int buttons = 0;
      for(int i=0; i<16; i++) 
      {
         if(gp.get(i).getVisibilityTop() == true || gp.get(i).getVisibilityBottom() == true
         || gp.get(i).getVisibilityLeft() == true || gp.get(i).getVisibilityRight() == true) 
         {
            buttons++;
         }
         
      }
      return buttons;
   }
   
   // method for updating label with new statistics
   public void updateLabel() 
   {
      text.setText("Balls Left: "+ballCounter()+"\tPossible Moves: "+buttonCounter());
      
      if(buttonCounter() == 0) 
      {
         text.setText("Balls Left: "+ballCounter()+"\tYou lose!");
      }
      
      if(ballCounter() == 1) 
      { 
         text.setText("Balls Left: "+ballCounter()+"\tYou win!");
      }
   }
   
   public class ButtonListener implements EventHandler<ActionEvent> 
   {
      public void handle(ActionEvent e) 
      {  
         // handles verticle buttons   
         for(int i=0; i<16; i++) 
         {
            if(e.getSource() == gp.get(i).getButtonTop()) 
            {   
               gp.get(i).setVisibility(false, false, false, false, false);
               gp.get(i+4).setVisibility(false, false, false, false, false);
               gp.get(i+8).setVisibility(false, false, false, false, true);
            }
            
            if(e.getSource() == gp.get(i).getButtonBottom())
            {    
               gp.get(i).setVisibility(false, false, false, false, false);
               gp.get(i-4).setVisibility(false, false, false, false, false);
               gp.get(i-8).setVisibility(false, false, false, false, true);
            }
         }
         
         // handles horizontal buttons 
         for(int i=0; i<16; i++) 
         {
            if(e.getSource() == gp.get(i).getButtonRight()) 
            {   
               gp.get(i).setVisibility(false, false, false, false, false);
               gp.get(i+1).setVisibility(false, false, false, false, false);
               gp.get(i+2).setVisibility(false, false, false, false, true);
            }
            
            if(e.getSource() == gp.get(i).getButtonLeft()) 
            {   
               gp.get(i).setVisibility(false, false, false, false, false);
               gp.get(i-1).setVisibility(false, false, false, false, false);
               gp.get(i-2).setVisibility(false, false, false, false, true);
            }
         }
         
         moveChecker();
         updateLabel();
      }   
   }
   
   int color = 0;
   
   public class AnimationHandler extends AnimationTimer
   {
      private long lastUpdate = 0;
      
      public void handle(long now) 
      {
         if(color == 360) 
         {
            color = 0;
         }
         else 
         {
            color++;
         }
         
         // rainbow wave effect
         for(int i=0; i<16; i++) 
         {
            gp.get(i).changeColor(color+(10*i));
         }
      }
   }

   public static void main(String[] args) 
   {
      launch(args);
   }
}