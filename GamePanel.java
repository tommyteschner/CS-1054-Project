// Tommy Teschner
// Project
// Dr. Mood

import javafx.scene.canvas.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import java.util.*;
import javafx.geometry.*;
import javafx.scene.paint.*;
import javafx.scene.image.*;

public class GamePanel extends GridPane 
{
   boolean topVisible = false;
   boolean bottomVisible = false;
   boolean leftVisible = false;
   boolean rightVisible = false;
   
   boolean ballVisible = true;
   
   Button top = new Button();
   Button bottom = new Button();
   Button left = new Button();
   Button right = new Button();
   
   Canvas circle = new Canvas(80, 80);
   
   public GamePanel() 
   {   
      initializeElements();
      updateBoard();
   }
   
   GraphicsContext gc = circle.getGraphicsContext2D();
   
   // set visibility of all values in one method, then updates the board
   public void setVisibility(boolean top, boolean bottom, boolean left, boolean right, boolean ball) 
   {
      topVisible = top;
      bottomVisible = bottom;
      leftVisible = left;
      rightVisible = right;
      ballVisible = ball;
      
      updateBoard();
   }
   
   // set visibility of only top button
   public void setVisibilityTop(boolean top) 
   {
      topVisible = top;
      updateBoard();
   }
   
   // set visibility of only bottom button
   public void setVisibilityBottom(boolean bottom) 
   {
      bottomVisible = bottom;
      updateBoard();
   }
   
   // set visibility of only left button
   public void setVisibilityLeft(boolean left) 
   {
      leftVisible = left;
      updateBoard();
   }
   
   // set visibility of only right button
   public void setVisibilityRight(boolean right) 
   {
      rightVisible = right;
      updateBoard();
   }
   
   // resets buttons all to false
   public void resetButtons() 
   {
      topVisible = false;
      bottomVisible = false;
      leftVisible = false;
      rightVisible = false;
      
      updateBoard();
   }
   
   // returns top button
   public Button getButtonTop() 
   {
      return top;
   }
   
   // returns bottom button
   public Button getButtonBottom() 
   {
      return bottom;
   }
   
   // returns left button
   public Button getButtonLeft() 
   {
      return left;
   }
   
   // returns right button
   public Button getButtonRight() 
   {
      return right;
   }
   
   // returns top button visibility
   public boolean getVisibilityTop() 
   {
      return topVisible;
   }
   
   // returns bottom button visibility
   public boolean getVisibilityBottom() 
   {
      return bottomVisible;
   }
   
   // returns left button visibility
   public boolean getVisibilityLeft() 
   {
      return leftVisible;
   }
   
   // returns right button visibility
   public boolean getVisibilityRight() 
   {
      return rightVisible;
   }
   
   // returns ball visibility
   public boolean getVisibilityBall() 
   {
      return ballVisible;
   }
   
   // sets the parameters of the GamePanel instance
   public void initializeElements() 
   {
      top.setPrefWidth(80);
      top.setPrefHeight(20);
      
      bottom.setPrefWidth(80);
      bottom.setPrefHeight(20);
      
      left.setPrefWidth(20);
      left.setPrefHeight(80);
      
      right.setPrefWidth(20);
      right.setPrefHeight(80);
      
      gc.fillOval(0, 0, 80, 80);
      
      add(top, 1, 0); 
      add(bottom, 1, 2);
      add(left, 2, 1);
      add(right, 0, 1);
      add(circle, 1, 1);
   }
   
   // updates the board by changing the button and ball visibility
   public void updateBoard() 
   {
      // top button visibility
      if(topVisible == true) 
      {
         top.setVisible(true);
      }
      else 
      {
         top.setVisible(false);
      }
      
      // bottom button visibility
      if(bottomVisible == true) 
      {
         bottom.setVisible(true);
      }
      else 
      {
         bottom.setVisible(false);
      }
      
      // left button visibility
      if(leftVisible == true) 
      {
         left.setVisible(true);
      }
      else 
      {
         left.setVisible(false);
      }
      
      // right button visibility
      if(rightVisible == true) 
      {
         right.setVisible(true);
      }
      else 
      {
         right.setVisible(false);
      }
      
      // ball visibility
      if(ballVisible == true) 
      {
         gc.setFill(c);
         gc.fillOval(0, 0, 80, 80);
      }
      else 
      {
         gc.clearRect(0, 0, 80, 80);
      }
   }
   
   Color c;
   
   // change color of balls method
   public void changeColor(int hue) 
   {
      c = Color.hsb(hue,1.0,1.0);
      updateBoard();
   }
}