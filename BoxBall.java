import java.awt.Color;
import java.awt.geom.*;
import java.util.Random;

/**
 * Write a description of class BoxBall here.
 *
 * @author Erik Cooke
 * @version 10.8.2019
 */
public class BoxBall
{
    // instance variables - replace the example below with your own
    private Ellipse2D.Double circle;
    private Color color;
    private int diameter;
    private int xPosition;
    private int yPosition;
    private final int groundPosition;
    private final int leftWallPosition;
    private final int topWallPosition;
    private final int rightWallPosition;
    private Canvas canvas;    
    private int ySpeed;
    private int xSpeed;
    

    /**
     * Constructor for objects of class BoxBall
     */
    public BoxBall(int ballDiameter, int groundPos, int leftPos, int topPos, int rightPos,
                    Canvas drawingCanvas)                    
    {
        // initialise instance variables
        diameter = ballDiameter;
        //color = ballColor;        
        groundPosition = groundPos;
        leftWallPosition = leftPos;
        topWallPosition = topPos;
        rightWallPosition = rightPos;
        canvas = drawingCanvas;
        Random rand = new Random();
        xPosition = rand.nextInt(rightWallPosition - diameter - leftWallPosition) + 1 + leftWallPosition;
        yPosition = rand.nextInt(groundPosition - topWallPosition) + 1 + topWallPosition;
        ySpeed = rand.nextInt(7) + 1;
        xSpeed = rand.nextInt(7) + 1;
        color = new Color(rand.nextInt(200), rand.nextInt(200), rand.nextInt(200));
    }

    /**
     * Draw this ball at its current position onto the canvas.
     *
     * @param  color 
     */
    public void draw()
    {
        canvas.setForegroundColor(color);
        canvas.fillCircle(xPosition, yPosition, diameter);
    }
    
    /**
     * Erase this ball at its current position.
     */
    public void erase()
    {
        canvas.eraseCircle(xPosition, yPosition, diameter);
    }
    
    /**
     * Move this ball according to its position and speed and redraw.
     */
    public void move()
    {
        //remove from canvas at the current position
        erase();
        
        //compute new position
        yPosition += ySpeed;
        xPosition += xSpeed;
        
        //has it hit anything?
        if(yPosition >= (groundPosition - diameter)) {
            yPosition = (int)(groundPosition - diameter);
            ySpeed = (ySpeed -(ySpeed * 2));
        }
        else if(yPosition <= (topWallPosition)) {
            yPosition = (int)(topWallPosition);
            ySpeed = (ySpeed -(ySpeed * 2));
        }
        if(xPosition >= (rightWallPosition - diameter)) {
            xPosition = (int)(rightWallPosition - diameter);
            xSpeed = (xSpeed -(xSpeed * 2));
        }
        else if(xPosition <= (leftWallPosition + 1)) {
            xPosition = (int)(leftWallPosition + 1);
            xSpeed = (xSpeed - (xSpeed * 2));
        }
        
        //draw again at new position
        draw();
    }
}
