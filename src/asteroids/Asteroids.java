/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package asteroids;
import Control.Constants;
import Control.GameControl;
import Model.Game;
import View.GamePanel;
import javax.swing.JFrame;

public class Asteroids extends JFrame{
    GamePanel gp;
    public static Game gameObjects;
    
    public Asteroids(){
        gp = new GamePanel();
        setSize(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        add(gp);
    }
    
    public static void main(String[] args){
        GameControl.createGameObjects();
        Asteroids m = new Asteroids();
    }

    public static void setGameObjects(Game gameObjects) {
        Asteroids.gameObjects = gameObjects;
    }
}
