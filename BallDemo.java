import java.awt.Color;
import java.util.ArrayList;
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
      * Draws a box and then creates a ball to bounce around inside
      */
     public void boxBounce(int numberOfBalls)
     {
         int verticalSide = 400;
         int horizontalSide = 500;
         
         myCanvas.setVisible(true);
         
         //draw the box
         //bottom line
         myCanvas.drawLine(50, verticalSide + 50, horizontalSide + 50, verticalSide + 50);
         //left side
         myCanvas.drawLine(50, 50, 50, verticalSide + 50);
         //top line
         myCanvas.drawLine(50, 50, horizontalSide + 50, 50);
         //right side
         myCanvas.drawLine(horizontalSide + 50, 50, horizontalSide + 50, verticalSide + 50);
         
         ArrayList<BoxBall> boxBall = new ArrayList<BoxBall>();
         for(int i = numberOfBalls;i >0;i--) {
             BoxBall ball = new BoxBall(20, Color.BLUE, 450, 50, 50, 550, myCanvas);
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
