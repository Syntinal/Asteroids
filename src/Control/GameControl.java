/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;

import Model.Asteroid;
import Model.Bullet;
import static Model.Bullet.gameObjects;
import Model.Game;
import Model.Point;
import Model.Ship;
import Model.SpaceObjects;
import Model.Vector;
import asteroids.Asteroids;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Random;


/**
 *
 * @author Wittmann
 */
public class GameControl {
    
    public static Game gameObjects;

    
    public static int randomValue(int min, int max, boolean absoluteValue){
        Random randomGenerator = new Random();
        int randomValue;
        boolean sign;
        
        randomValue = randomGenerator.nextInt(max - min);
        randomValue += min;
        if (!absoluteValue){
            sign = randomGenerator.nextBoolean();

            if (sign){
                randomValue *= -1;
            }
        }
        
        return randomValue;
    }
    
    public static void createGameObjects(){
        //Create new Game Objects
        GameControl.gameObjects = new Game();
        
        //Set game objects as current objects
        Asteroids.setGameObjects(gameObjects);
        
        //Create Asteroids
        gameObjects.setLargeAsteroid(createAsteroids());
        
        //Create ship
        Ship ship = new Ship();
        gameObjects.setShip(ship);
        
        //Create bullets
        gameObjects.setBullets(createBullets());
        
    }

    private static Asteroid[] createAsteroids() {
        Asteroid[] largeAsteroid = new Asteroid[Constants.ASTEROID_LARGE_COUNT];
        
        for(int i = 0; i < Constants.ASTEROID_LARGE_COUNT; i++){
            if(i < (Constants.ASTEROID_LARGE_COUNT / 2)){
                largeAsteroid[i] = new Asteroid(true);
            }
            else{
                largeAsteroid[i] = new Asteroid(false);                
            }
            
        }
        
        return largeAsteroid;
    }
    
    private static Bullet[] createBullets(){
        Bullet[] bullets = new Bullet[Constants.SHIP_BULLET_COUNT];
        
        for(int i = 0; i < Constants.SHIP_BULLET_COUNT; i++){
            bullets[i] = new Bullet();
        }
        
        return bullets;
    }
     
    public static void advanceGame(){
        Asteroid[] largeAsteroid = gameObjects.getLargeAsteroid();
        Ship ship = gameObjects.getShip();
        Bullet[] bullets = gameObjects.getBullets();
        
        advance(largeAsteroid);
        advance(ship);
        advance(bullets);
        
    }
    
    private static void advance(Asteroid[] asteroid){
        Point newPoint = new Point();

        
        for (int i = 0; i < Constants.ASTEROID_LARGE_COUNT; i++){
            Point oldPoint = asteroid[i].getCenter();
            Vector oldVector = asteroid[i].getVector();

            wrapObject(oldPoint, oldVector, newPoint, asteroid[i].getRadius());
            asteroid[i].getCenter().setX(newPoint.getX());
            asteroid[i].getCenter().setY(newPoint.getY());
            
            //rotate asteroid
            asteroid[i].setRotation(asteroid[i].getRotation() + Constants.ASTEROID_LARGE_ROTATION_SPEED);
        }
    }
    
    private static void advance(Ship ship){     
        //get rotational thrust and rotate ship at increasing speed when button his held
        if (ship.getRotationalThrust() != 0){
            ship.setThrustLength(ship.getThrustLength() + 1);

            if (ship.getRotationalThrust() > 0){
                ship.setRotation(ship.getRotation() - ((ship.getThrustLength() * 3)));
            }
            else{
                ship.setRotation(ship.getRotation() + ((ship.getThrustLength() * 3)));
            }
        }
        else{
            ship.setThrustLength(0);
        }
        
        //Normalize ships current vector
        ship.getThrustVector().normalizeVector(ship.getCenter(), ship.getRotation());
        
        //Add thrust to noramalized vector
        ship.getThrustVector().increaseMagnitude(ship.getForwardThrust());
        
        //Add ships current vector to current momentum vector creating new path.
        ship.getVector().addVectors(ship.getThrustVector());
                    
        //move ship to new point by setting new point and moving ship to the vectors new location
        Point newPoint = new Point();
        Point oldPoint = ship.getCenter();
        Vector oldVector = ship.getVector();
        
        //Determine if ship has reached screen
        wrapObject(oldPoint, oldVector, newPoint, ship.getRadius());
        
        //Set ships new coords
        ship.getCenter().setX(newPoint.getX());
        ship.getCenter().setY(newPoint.getY());
    }
    
    private static void advance(Bullet[] bullet){ 
        for(int i = 0; i < Constants.SHIP_BULLET_COUNT; i++){
            if (!bullet[i].isDead()){
                if (outOfBounds(bullet[i].getCenter(), bullet[i].getRadius())){
                    bullet[i].setDead(true);
                    continue;
                }
                bullet[i].getVector().advancePoint(bullet[i].getCenter());
                //System.out.println(bullet[i].getCenter().getX());
                //System.out.println(bullet[i].getCenter().getY());
            }
        }
    }
    
    public static void drawObjects(Graphics g){
        Asteroid[] largeAsteroid = gameObjects.getLargeAsteroid();
        Ship ship = gameObjects.getShip();
        Bullet[] bullets = gameObjects.getBullets();
        
        //Draw Asteroids
        for (int i = 0; i < Constants.ASTEROID_LARGE_COUNT; i++){
            largeAsteroid[i].drawAsteroid(g);
        }
        
        //Draw Ship
        ship.drawShip(g);   
        
        //Draw bullet
        for (int i = 0; i < Constants.SHIP_BULLET_COUNT; i++){
            if (!bullets[i].isDead()){
                bullets[i].drawBullet(g);
            }         
        }
    }
    
    private static void wrapObject(Point oldPoint, Vector oldVector, Point newPoint, int radius){

        int xyMinBoundary = 0 + (int)(radius / 2);
        int xMaxBoundary = Constants.GAME_WIDTH + (int)(radius / 2);
        int yMaxBoundary = Constants.GAME_HEIGHT + (int)(radius / 2);
        int x = oldPoint.getX();
        int y = oldPoint.getY();
        int dx = (int) oldVector.getDx();
        int dy = (int) oldVector.getDy();
        
        //check to see if asteroid has reached edge of screen.
        //if at edge then redraw on oposite side.
            if (x > xMaxBoundary){
                newPoint.setX(0);
                newPoint.setY(y + dy);
            }
            else if (x < -xyMinBoundary){
                newPoint.setX(Constants.GAME_WIDTH);
                newPoint.setY(y + dy);
            }
            else if (y > yMaxBoundary){
                newPoint.setX(x + dx);
                newPoint.setY(0);
            }
            else if (y < -xyMinBoundary){
                newPoint.setX(x + dx);
                newPoint.setY(Constants.GAME_HEIGHT);
            }
            else{
                newPoint.setX(x + dx);
                newPoint.setY(y +dy);
            }
    }
    
    private static boolean outOfBounds(Point point, int radius){
        int xyMinBoundary = 0 + (int)(radius / 2);
        int xMaxBoundary = Constants.GAME_WIDTH + (int)(radius / 2);
        int yMaxBoundary = Constants.GAME_HEIGHT + (int)(radius / 2);
        int x = point.getX();
        int y = point.getY();
        boolean outOfBounds = false;
        
        if (x > xMaxBoundary || x < -xyMinBoundary ||
            y > yMaxBoundary || y < -xyMinBoundary){
            outOfBounds = true;
        }
        return outOfBounds;
    }
    
    public static void bulletFire(){
        Ship ship = gameObjects.getShip();
        Point point = new Point();
        Vector vector = new Vector();
        int rotation = 0;
        
        //extract ship vector and point and assign it to new bullet.
        vector.setDx((int) ship.getVector().getDx());
        vector.setDy((int) ship.getVector().getDy());
        point.setX(ship.getCenter().getX());
        point.setY(ship.getCenter().getY());
        rotation = ship.getRotation();
        
        Bullet[] bullets = gameObjects.getBullets();
        
        //cycle through array of to find a dead bullet to fire
        for(int i = 0; i < Constants.SHIP_BULLET_COUNT; i++){
            if (bullets[i].isDead()){
                
                //set bullets to ships data
                bullets[i].setCenter(point);
                bullets[i].setRotation(rotation);
                bullets[i].setDead(false);
                
                //Create new normalized vector in direction ship is pointing
                vector.normalizeVector(point, rotation);
                
                //Fire bullet by increasing magnitude of vector
                vector.increaseMagnitude(Constants.SHIP_BULLET_SPEED);
                
                //Create new vector with vector addition to create new trajectory
                bullets[i].getVector().addVectors(vector);
                bullets[i].setVector(vector);
                
                //allows only one bullet to be fired per button press
                i = Constants.SHIP_BULLET_COUNT;
            }
        }
    }
    
    public static void keyPress(KeyEvent e){
        Ship ship = gameObjects.getShip();
        int keyCode = e.getKeyCode();
 
        if (keyCode == KeyEvent.VK_UP){
            ship.forwardThrust(true);
        }
        if (keyCode == KeyEvent.VK_DOWN){
            //No reverse
        }
        if (keyCode == KeyEvent.VK_LEFT){
            ship.rotateShip(true, true);
        }
        if (keyCode == KeyEvent.VK_RIGHT){
            ship.rotateShip(false, true);
        }
        if (keyCode == KeyEvent.VK_SPACE){
            bulletFire();
        }
    }
    
    public static void keyRelease(KeyEvent e){
        Ship ship = gameObjects.getShip();
        int keyCode = e.getKeyCode();
        
        if (keyCode == KeyEvent.VK_UP){
            ship.forwardThrust(false);
        }
        if (keyCode == KeyEvent.VK_DOWN){
            //No reverse
        }
        if (keyCode == KeyEvent.VK_LEFT){
            ship.rotateShip(true, false);
        }
        if (keyCode == KeyEvent.VK_RIGHT){
            ship.rotateShip(true, false);
        }
    }
}