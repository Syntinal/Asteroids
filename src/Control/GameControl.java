/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;

import Model.Asteroid;
import Model.Game;
import Model.Point;
import Model.Ship;
import Model.SpaceObjects;
import Model.Vector;
import asteroids.Asteroids;
import java.awt.event.KeyEvent;
import java.util.Random;


/**
 *
 * @author Wittmann
 */
public class GameControl {
    
    public static Game gameObjects;
    public static int keyPressLength;
    
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
        
        Ship nose = new Ship();
        nose.getCenter().setX(ship.getxPoints()[2]);
        nose.getCenter().setY(ship.getyPoints()[2]);
        nose.setRadius(3);
        gameObjects.setNose(nose);
        
        
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
    
    
    public static void advanceGame(){
        Asteroid[] largeAsteroid = gameObjects.getLargeAsteroid();
        Ship ship = gameObjects.getShip();
        //Ship nose = gameObjects.getNose();
        
        advance(largeAsteroid);
        advance(ship);
        
    }
    
    private static void advance(Asteroid[] asteroid){
        Point newPoint = new Point();

        
        for (int i = 0; i < Constants.ASTEROID_LARGE_COUNT; i++){
            Point oldPoint = asteroid[i].getCenter();
            Vector oldVector = asteroid[i].getVector();

            wrapObject(oldPoint, oldVector, newPoint);
            asteroid[i].getCenter().setX(newPoint.getX());
            asteroid[i].getCenter().setY(newPoint.getY());
            
            //rotate asteroid
            asteroid[i].setRotation(asteroid[i].getRotation() + Constants.ASTEROID_LARGE_ROTATION_SPEED);
        }
    }
    
    private static void advance(Ship ship){
        Point newPoint = new Point();
        Point oldPoint = ship.getCenter();
        Vector oldVector = ship.getVector();
        
        wrapObject(oldPoint, oldVector, newPoint);
        
        ship.getCenter().setX(newPoint.getX());
        ship.getCenter().setY(newPoint.getY());
    }
    
    private static void wrapObject(Point oldPoint, Vector oldVector, Point newPoint){

        int xyMinBoundary = 0 + (int)(Constants.ASTEROID_LARGE_RADIUS / 2);
        int xMaxBoundary = Constants.GAME_WIDTH + (int)(Constants.ASTEROID_LARGE_RADIUS / 2);
        int yMaxBoundary = Constants.GAME_HEIGHT + (int)(Constants.ASTEROID_LARGE_RADIUS / 2);
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
    
    public static void keyPress(KeyEvent e){
        Ship ship = gameObjects.getShip();
        int keyCode = e.getKeyCode();
        keyPressLength++;
        System.out.println(keyPressLength);
        
        if (keyCode == KeyEvent.VK_UP){
            ship.forwardThrust(keyPressLength);
        }
        if (keyCode == KeyEvent.VK_DOWN){

        }
        if (keyCode == KeyEvent.VK_LEFT){
            ship.rotateShip(keyPressLength, -1);
        }
        if (keyCode == KeyEvent.VK_RIGHT){
            ship.rotateShip(keyPressLength, 1);
        }
    }
    
    public static void keyRelease(KeyEvent e){
        keyPressLength = 0;
    }
}