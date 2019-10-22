import java.awt.Color;
import java.awt.geom.*;
import java.util.Random;

/**
 * Creates a ball at the x and y coordinates, sets its color, diameter, and speed.
 * Ball can be moved by repeated calls to the move() method and it will bounce off of walls of box.
 *
 * @author Erik Cooke
 * @version 2019.10.8
 * 
 */
public class BoxBall
{   
    private Ellipse2D.Double circle;
    private Color color;
    private int diameter;
    private int xPosition;
    private int yPosition;
    private final int outerBottom;
    private final int outerLeft;
    private final int outerTop;
    private final int outerRight;
    private Canvas canvas;    
    private int ySpeed;
    private int xSpeed;
    
    /**
     * Constructor for objects of class BoxBall.
     * @param xPosition starting x position of ball.
     * @param yPosition starting y position of ball.
     * @param diameter sets ball to this diameter.
     * @param color sets ball to this color.
     * @param outerBottom location of the bottom line for the outer box on the canvas.
     * @param outerLeft location of the left line for the outer box on the canvas.
     * @param outerTop location of the top line for the outer box on the canvas.
     * @param outerRight location of the right line for the outer box on the canvas.
     * @param canvas holds the Canvas object.
     * @param ySpeed sets the amount ball will move on the y axis with each tick.
     * @param xSpeed sets the amount ball will move on the x axis with each tick.
     */
    public BoxBall(int xPosition, int yPosition, int diameter, Color color,
                   int outerBottom, int outerLeft, int outerTop, int outerRight,
                   Canvas canvas, int xSpeed, int ySpeed)                    
    {
        // initialise instance variables
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.diameter = diameter;
        this.color = color;        
        this.outerBottom = outerBottom;
        this.outerLeft = outerLeft;
        this.outerTop = outerTop;
        this.outerRight = outerRight;
        this.canvas = canvas;    
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;        
    }

    /**
     * Draw this ball at its current position onto the canvas.
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
     * If it hits a wall make it bounce off.
     */
    public void move()
    {
        //remove from canvas at the current position
        erase();
        
        //compute new position
        yPosition += ySpeed;
        xPosition += xSpeed;
        
        //has it hit anything?
        if(yPosition >= (outerBottom - diameter)){
            yPosition = (int)(outerBottom - diameter);
            ySpeed = -ySpeed;
        }
        else if(yPosition <= outerTop) {
            yPosition = (int)(outerTop);
            ySpeed = -ySpeed;           
        }
        
        if(xPosition >= (outerRight - diameter)) {
            xPosition = (int)(outerRight - diameter);
            xSpeed = -xSpeed;
        }
        else if(xPosition <= (outerLeft + 1)) {
            xPosition = (int)(outerLeft + 1);
            xSpeed = -xSpeed;
        }
        //draw again at new position
        draw();
    }
}
