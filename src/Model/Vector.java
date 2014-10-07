/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;
import static java.lang.Math.*;


public class Vector {
    private Point point;
    private double dx;
    private double dy;
    
    public Vector(){
        point = new Point();
        dx = 0;
        dy = 0;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }
    
    public void normalizeVector(Point newPoint, int angle){
        point = newPoint;
        double cosA = cos(toRadians(angle));
        double sinA = sin(toRadians(angle));
        
        dx = cosA;
        dy = sinA;   
    }
    
    public void increaseMagnitude(double increase){
        dx *= increase;
        dy *= increase;
    }
    
    public void addVectors(Vector secVector){
        dx += secVector.getDx();
        dy += secVector.getDy();
    }
}
