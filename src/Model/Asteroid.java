/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import Control.Constants;
import Control.GameControl;
import java.util.Random;

/**
 *
 * @author Wittmann
 */
public class Asteroid extends SpaceObjects{
    
    private int sides;
    private int radius;
    private int rotation;
    private int rotationSpeed;
    private int[] xPoints = new int[6];
    private int[] yPoints = new int[6];
    
    public Asteroid(boolean left){
        
        radius = Constants.ASTEROID_LARGE_RADIUS;
        sides = Constants.ASTEROID_LARGE_SIDE_COUNT;
        rotationSpeed = Constants.ASTEROID_LARGE_ROTATION_SPEED;
        
        //random intial position
        rotation = GameControl.randomValue(0, 45, true);
        
        int min = (int) (Constants.GAME_HEIGHT * .25);
        int max = (int) (Constants.GAME_HEIGHT * .75);
        
         
        
        if (left){
            this.setX(-16);
            this.setDx(GameControl.randomValue(
                    Constants.ASTEROID_LARGE_VELOCITY_MIN, 
                    Constants.ASTEROID_LARGE_VELOCITY_MAX, 
                    true));
        }
        else{
            this.setX(Constants.GAME_WIDTH + 10);
            this.setDx(-GameControl.randomValue(
                    Constants.ASTEROID_LARGE_VELOCITY_MIN, 
                    Constants.ASTEROID_LARGE_VELOCITY_MAX, 
                    true));
        }
        
        this.setY(GameControl.randomValue(min, max, true));
        this.setDy(GameControl.randomValue(0, Constants.ASTEROID_LARGE_DY_MAX, false));
        this.setDead(false);
        
        GameControl.calculatePolygon(this);
    }

    public int getSides() {
        return sides;
    }

    public void setSides(int sides) {
        this.sides = sides;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int[] getxPoints() {
        return xPoints;
    }

    public void setxPoints(int[] xPoints) {
        this.xPoints = xPoints;
    }

    public int[] getyPoints() {
        return yPoints;
    }

    public void setyPoints(int[] yPoints) {
        this.yPoints = yPoints;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public int getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(int rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }
}
