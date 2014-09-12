/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;

import Model.Asteroid;
import Model.Game;
import asteroids.Asteroids;
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
        
        //Create Game Data
        gameObjects.setLargeAsteroid(createAsteroids());
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
        Game gameObjects = Asteroids.gameObjects;
        Asteroid[] largeAsteroid = gameObjects.getLargeAsteroid();
        
        for(int i =0; i < Constants.ASTEROID_LARGE_COUNT; i++){
            calculatePolygon(largeAsteroid[i]);
        }

        advanceAsteroid(largeAsteroid);
        
    }
    
    private static void advanceAsteroid(Asteroid[] asteroid){
        int newX;
        int newY;
        int xyMinBoundary = 0 + (int)(Constants.ASTEROID_LARGE_RADIUS / 2);
        int xMaxBoundary = Constants.GAME_WIDTH + (int)(Constants.ASTEROID_LARGE_RADIUS / 2);
        int yMaxBoundary = Constants.GAME_HEIGHT + (int)(Constants.ASTEROID_LARGE_RADIUS / 2);
        
        for (int i = 0; i < Constants.ASTEROID_LARGE_COUNT; i++){
            
            //check to see if asteroid has reached edge of screen.
            //if at edge then redraw on oposite side.
            if (asteroid[i].getX() > xMaxBoundary){
                newX = 0;
                newY = asteroid[i].getY() + asteroid[i].getDy();
            }
            else if (asteroid[i].getX() < -xyMinBoundary){
                newX = Constants.GAME_WIDTH;
                newY = asteroid[i].getY() + asteroid[i].getDy();
            }
            else if (asteroid[i].getY() > yMaxBoundary){
                newX = asteroid[i].getX() + asteroid[i].getDx();
                newY = 0;
            }
            else if (asteroid[i].getY() < -xyMinBoundary){
                newX = asteroid[i].getX() + asteroid[i].getDx();
                newY = Constants.GAME_HEIGHT;
            }
            else{
                newX = asteroid[i].getX() + asteroid[i].getDx();
                newY = asteroid[i].getY() + asteroid[i].getDy();
            }
            asteroid[i].setX(newX);
            asteroid[i].setY(newY);
            
            //rotate asteroid
            asteroid[i].setRotation(asteroid[i].getRotation() + Constants.ASTEROID_LARGE_ROTATION_SPEED);
            calculatePolygon(asteroid[i]);
        }
    }
    
    public static void calculatePolygon(Asteroid asteroid){
        int n = asteroid.getSides();
        int r = asteroid.getRadius();
        int x = asteroid.getX();
        int y = asteroid.getY();
        int t = asteroid.getRotation();
        int[] polygonXPoints = new int[n];
        int[] polygonYPoints = new int[n];
        
        for(int i = 0; i < n; i++){
            polygonXPoints[i] = (int) (x + r * Math.cos((((2 * Math.PI)/n)*i + t)));
        }
        asteroid.setxPoints(polygonXPoints);
        
        for(int i = 0; i < n; i++){
            polygonYPoints[i] = (int) (y + r * Math.sin((((2 * Math.PI)/n)*i + t)));
        }
        asteroid.setyPoints(polygonYPoints);
        
    }
}