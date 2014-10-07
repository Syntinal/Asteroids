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
    
    public Ship(){
        thrustVector = new Vector();
        radius = Constants.SHIP_RADIUS;
        sides = Constants.SHIP_SIDE_COUNT;
        rotationSpeed = Constants.SHIP_ROTATION_SPEED;
        
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
    public void forwardThrust(int thrustLength){
        double thrust = 0;
        thrust = (1 + (thrustLength / 50));

         
        
        thrustVector.normalizeVector(center, rotation);
        thrustVector.increaseMagnitude(thrust);
        
        vector.addVectors(thrustVector);
    }
    
    public void rotateShip(int rotateLength, int direction){
        rotation += (int) (direction * (Constants.ROTATION_RATE + (rotateLength * 2)));
    }
    
    public void drawShip(Graphics g){
        Draw.drawShip(g,center, rotation);
    }
}
