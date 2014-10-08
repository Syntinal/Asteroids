/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;
public class Game {
    
    private Asteroid[] largeAsteroid;
    private Asteroid[] mediumAsteroid;
    private Asteroid[] smallAsteroid;
    private Ship ship;
    private Bullet[] bullets;
    

    public Asteroid[] getLargeAsteroid() {
        return largeAsteroid;
    }

    public void setLargeAsteroid(Asteroid[] largeAsteroid) {
        this.largeAsteroid = largeAsteroid;
    }

    public Asteroid[] getMediumAsteroid() {
        return mediumAsteroid;
    }

    public void setMediumAsteroid(Asteroid[] mediumAsteroid) {
        this.mediumAsteroid = mediumAsteroid;
    }

    public Asteroid[] getSmallAsteroid() {
        return smallAsteroid;
    }

    public void setSmallAsteroid(Asteroid[] smallAsteroid) {
        this.smallAsteroid = smallAsteroid;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Bullet[] getBullets() {
        return bullets;
    }

    public void setBullets(Bullet[] bullets) {
        this.bullets = bullets;
    }
}
