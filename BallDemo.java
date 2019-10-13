import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Bill Crosbie
 * @version 2015-March-BB
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class BallDemo   
{
    private Canvas myCanvas;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
    }

    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.drawLine(50, ground, 550, ground);

        // crate and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while(!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }
    
     /**
      * Draws a box based on the size of the canvas
      * and then creates a number of balls to bounce around inside
      * @param numberOfBalls how many balls to spawn in the box
      */
     public void boxBounce(int numberOfBalls)
     {          
         myCanvas.setVisible(true);
         
         int buffer = 10;
         Dimension size = myCanvas.getSize();         
         int verticalSide = (int)size.getHeight() - buffer;
         int horizontalSide = (int)size.getWidth() - buffer;
         //draw the box
         //bottom line
         myCanvas.drawLine(buffer, verticalSide, horizontalSide, verticalSide);
         //left side
         myCanvas.drawLine(buffer, buffer, buffer, verticalSide);
         //top line
         myCanvas.drawLine(buffer, buffer, horizontalSide, buffer);
         //right side
         myCanvas.drawLine(horizontalSide, buffer, horizontalSide, verticalSide);
         
         Random rand = new Random();

         ArrayList<BoxBall> boxBall = new ArrayList<BoxBall>();
         
         for(int i = numberOfBalls;i > 0;i--) {
             int diameter = rand.nextInt(16) + 10; //random number between 10 and 25 pixels for ball diameter
             int xPosition = rand.nextInt(horizontalSide - diameter - buffer) + 1 + buffer; //random int for the x position inside of box
             int yPosition = rand.nextInt(verticalSide - diameter - buffer) + 1 + buffer;  //random int for the y position inside of box
             //random number from -7 to 7 for the starting speed of the ball
             int xSpeed = rand.nextInt(15) -7;
             if(xSpeed == 0) {xSpeed = 7;} //speed can't be 0
             int ySpeed = rand.nextInt(15) -7; 
             if(ySpeed == 0) {ySpeed = 7;}  //speed can't be 0
             //random ballcolor
             Color ballColor = new Color(rand.nextInt(200), rand.nextInt(200), rand.nextInt(200));
             //Generate a new ball then draw it then add it to the ArrayList boxBall
             BoxBall ball = new BoxBall(xPosition, yPosition, diameter, ballColor, verticalSide, buffer, buffer,
                                        horizontalSide, myCanvas, xSpeed, ySpeed);
             ball.draw();
             boxBall.add(ball);
         }
         
         // make ball move
         boolean finished = false;
         while(!finished) {
             myCanvas.wait(50);
             for(BoxBall ball : boxBall){
                 ball.move();
             }
         }
     }
}
