/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import Control.Constants;
import Control.Draw;
import java.awt.Graphics;

public class Bullet extends SpaceObjects{
    public static Game gameObjects;
    
    public Bullet(){
        dead = true;
        radius = Constants.SHIP_BULLET_RADIUS;
        sides = Constants.SHIP_BULLET_SIDES;
    }

    public void drawBullet(Graphics g){
        g.setColor(Constants.SHIP_BULLET_COLOR);
        Draw.drawPolygon(g, center, rotation, sides, radius);
    }
}
