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
    private final int outerBottom;
    private final int outerLeft;
    private final int outerTop;
    private final int outerRight;
    private Canvas canvas;    
    private int ySpeed;
    private int xSpeed;    
    /**
     * Constructor for objects of class BoxBall
     * @param ballDiameter how big should the ball be
     */
    public BoxBall(int xPosition, int yPosition, int diameter, Color ballColor,
                   int outerBottom, int outerLeft, int outerTop, int outerRight,
                   Canvas drawingCanvas, int xSpeed, int ySpeed)                    
    {
        // initialise instance variables
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.diameter = diameter;
        color = ballColor;        
        this.outerBottom = outerBottom;
        this.outerLeft = outerLeft;
        this.outerTop = outerTop;
        this.outerRight = outerRight;
        canvas = drawingCanvas;    
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;        
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
        if(yPosition >= (outerBottom - diameter)){
            yPosition = (int)(outerBottom - diameter);
            ySpeed = (ySpeed - (ySpeed * 2));
        }
        else if(yPosition <= outerTop) {
            yPosition = (int)(outerTop);
            ySpeed = (ySpeed - (ySpeed * 2));           
        }
        
        if(xPosition >= (outerRight - diameter)) {
            xPosition = (int)(outerRight - diameter);
            xSpeed = (xSpeed - (xSpeed * 2));
        }
        else if(xPosition <= (outerLeft + 1)) {
            xPosition = (int)(outerLeft + 1);
            xSpeed = (xSpeed - (xSpeed * 2));
        }
        //draw again at new position
        draw();
    }
}
