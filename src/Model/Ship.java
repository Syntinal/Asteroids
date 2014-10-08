/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import Control.Constants;
import Control.Draw;
import Control.GameControl;
import java.awt.Graphics;

/**
 *
 * @author Wittmann
 */
public class Ship extends SpaceObjects{
    private Vector thrustVector;
    private int forwardThrust;
    private int rotationalThrust;
    private int thrustLength;
    
    
    public Ship(){
        thrustVector = new Vector();
        radius = Constants.SHIP_RADIUS;
        sides = Constants.SHIP_SIDE_COUNT;
        rotationSpeed = 0;
        thrustLength = 0;
        
        //random intial position
        rotation = - Constants.SHIP_INTIAL_ROTATION;
        
        this.xPoints = new int[3];
        this.yPoints = new int[3];
        
        this.center.setY((int) Constants.GAME_HEIGHT / 2);
        this.center.setX((int) Constants.GAME_WIDTH / 2);
        this.vector.setDx(0);
        this.vector.setDy(0);
        this.vector.setPoint(center);
        this.setDead(false);
        
    }

    public Vector getThrustVector() {
        return thrustVector;
    }

    public void setThrustVector(Vector thrustVector) {
        this.thrustVector = thrustVector;
    }

    public int getForwardThrust() {
        return forwardThrust;
    }

    public void setForwardThrust(int forwardThrust) {
        this.forwardThrust = forwardThrust;
    }

    public int getRotationalThrust() {
        return rotationalThrust;
    }

    public void setRotationalThrust(int rotationalThrust) {
        this.rotationalThrust = rotationalThrust;
    }

    public int getThrustLength() {
        return thrustLength;
    }

    public void setThrustLength(int thrustLength) {
        this.thrustLength = thrustLength;
    }
  
    public void forwardThrust(boolean thrustOn){
        if (thrustOn){
            forwardThrust = Constants.THRUST_RATE;
        }
        else{
            forwardThrust = 0;
        }
    }
    
    public void rotateShip(boolean right, boolean rotate){
        if (rotate){
            if(right){
                rotationalThrust = 0;
                rotationalThrust = Constants.SHIP_ROTATION_SPEED;
            }
            else{
                rotationalThrust = 0;
                rotationalThrust = -Constants.SHIP_ROTATION_SPEED;
            }
        }
        else{
            rotationalThrust = 0;
        }
    }
    
    public void drawShip(Graphics g){
        g.setColor(Constants.SHIP_COLOR);
        Draw.drawShip(g,center, rotation);
    }
}
