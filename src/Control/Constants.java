/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;

import java.awt.Color;

/**
 *
 * @author Wittmann
 */
public class Constants {
    public static final int GAME_HEIGHT = 600;
    public static final int GAME_WIDTH = 700;
    public static final Color BACKGROUND_COLOR = Color.BLACK;    
    public static final int ASTEROID_LARGE_COUNT = 6;
    public static final int ASTEROID_LARGE_VELOCITY_MAX = 3;
    public static final int ASTEROID_LARGE_VELOCITY_MIN = 1;
    public static final int ASTEROID_LARGE_DY_MAX = 4;
    public static final int ASTEROID_LARGE_RADIUS = 40;
    public static final int ASTEROID_LARGE_SIDE_COUNT = 6;
    public static final int ASTEROID_LARGE_ROTATION_SPEED = 1; //higher = faster (<5)
    public static final Color ASTEROID_COLOR = Color.WHITE;
    
    public static final int SHIP_RADIUS = 10;
    public static final int SHIP_SIDE_COUNT = 3;
    public static final int SHIP_ROTATION_SPEED = 1;
    public static final int SHIP_INTIAL_ROTATION = 90;
    public static final int SHIP_SIZE = 2;
    public static final Color SHIP_COLOR = Color.GREEN;
    public static final int THRUST_RATE = 2;
    public static final int ROTATION_RATE = 2;
    public static final int SHIP_BULLET_RADIUS = 2;
    public static final int SHIP_BULLET_SIDES = 10;
    public static final int SHIP_BULLET_SPEED = 25;
    public static final int SHIP_BULLET_COUNT = 6;
    public static final Color SHIP_BULLET_COLOR = Color.RED;
    

    
}
