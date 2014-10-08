/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import Control.Constants;
import Control.Draw;
import Control.GameControl;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author Wittmann
 */
public class Asteroid extends SpaceObjects{
    
    public Asteroid(boolean left){
        
        radius = Constants.ASTEROID_LARGE_RADIUS;
        sides = Constants.ASTEROID_LARGE_SIDE_COUNT;
        rotationSpeed = Constants.ASTEROID_LARGE_ROTATION_SPEED;
        
        //random intial position
        rotation = GameControl.randomValue(0, 45, true);
        
        this.xPoints = new int[6];
        this.yPoints = new int[6];
        
        int min = (int) (Constants.GAME_HEIGHT * .25);
        int max = (int) (Constants.GAME_HEIGHT * .75);
        
         
        
        if (left){
            this.getCenter().setX(-16);
            this.getVector().setDx(GameControl.randomValue(
                    Constants.ASTEROID_LARGE_VELOCITY_MIN, 
                    Constants.ASTEROID_LARGE_VELOCITY_MAX, 
                    true));
        }
        else{
            this.getCenter().setX(Constants.GAME_WIDTH + 10);
            this.getVector().setDx(-GameControl.randomValue(
                    Constants.ASTEROID_LARGE_VELOCITY_MIN, 
                    Constants.ASTEROID_LARGE_VELOCITY_MAX, 
                    true));
        }
        
        this.getCenter().setY(GameControl.randomValue(min, max, true));
        this.getVector().setDy(GameControl.randomValue(0, Constants.ASTEROID_LARGE_DY_MAX, false));
        this.setDead(false);
    }
    
    public void drawAsteroid(Graphics g){
        g.setColor(Constants.ASTEROID_COLOR);
        Draw.drawPolygon(g, center, rotation, sides, radius);
    }
}
