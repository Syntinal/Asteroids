/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;

import Model.Point;
import java.awt.Color;
import java.awt.Graphics;
import static java.lang.Math.*;

/**
 *
 * @author Nathan
 */
public class Draw{
    
    public static void drawShip(Graphics g, Point center, int angle){
        
        Point xyPoints = new Point();
        int sides = 4;
        
        int[] x = new int[sides];
        int[] y = new int[sides];
        
        x[0] = (center.getX() + (6 * Constants.SHIP_SIZE)); //bow
        y[0] = (center.getY());
        
        x[1] = (center.getX() - (5 * Constants.SHIP_SIZE)); //starboard
        y[1] = (center.getY() + (3 * Constants.SHIP_SIZE));
        
        x[2] = (center.getX() - (1 * Constants.SHIP_SIZE)); //stern
        y[2] = (center.getY());
        
        x[3] = (center.getX() - (5 * Constants.SHIP_SIZE)); //port
        y[3] = (center.getY() - (3 * Constants.SHIP_SIZE));
        
        xyPoints.setxPoints(x);
        xyPoints.setyPoints(y);
        
        rotate(xyPoints, center, angle, sides);
        
        g.setColor(Constants.SHIP_COLOR);
        g.fillPolygon(xyPoints.getxPoints(), xyPoints.getyPoints(), sides);
    }
    
    public static void drawPolygon(Graphics g, Point center, int angle, int sides, int radius){
        int n = sides;
        int r = radius;
        int x = center.getX();
        int y = center.getY();
        int t = angle;
        int[] polygonXPoints = new int[n];
        int[] polygonYPoints = new int[n];
        
        for(int i = 0; i < n; i++){
            polygonXPoints[i] = (int) (x + r * Math.cos((((2 * Math.PI)/n)*i + t)));
        }
        
        for(int i = 0; i < n; i++){
            polygonYPoints[i] = (int) (y + r * Math.sin((((2 * Math.PI)/n)*i + t)));
        }

        g.drawPolygon(polygonXPoints, polygonYPoints, n);
        
    }
    
    private static void rotate(Point xyPoints, Point center, int angle, int sides){
        double cosA = cos(toRadians(angle));
        double sinA = sin(toRadians(angle));
        int[] xPoints = xyPoints.getxPoints();
        int[] yPoints = xyPoints.getyPoints();
        
        for(int i = 0; i < sides; i++){
            Point tmp = new Point();
            tmp.setX(xyPoints.getxPoints()[i] - center.getX());
            tmp.setY(xyPoints.getyPoints()[i] - center.getY());
            
            xPoints[i] = ((int) (tmp.getX() * cosA - tmp.getY() * sinA + center.getX()));
            yPoints[i] = ((int) (tmp.getX() * sinA + tmp.getY() * cosA + center.getY()));
        }
        
        xyPoints.setxPoints(xPoints);
        xyPoints.setyPoints(yPoints);
    }
}
