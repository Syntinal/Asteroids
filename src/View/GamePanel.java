/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import Control.Constants;
import Control.GameControl;
import Model.Asteroid;
import Model.Game;
import asteroids.Asteroids;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;



/**
 *
 * @author Wittmann
 */
public class GamePanel extends JPanel implements Runnable{
    //Double buffering
    private Image dbImage;
    private Graphics dbg;
    
    //JPanel variables
    static final int GWIDTH = Constants.GAME_WIDTH;
    static final int GHEIGHT = Constants.GAME_HEIGHT;
    static final Dimension gameDim = new Dimension(GWIDTH, GHEIGHT);
    
    //Game variables
    private Thread game;
    private volatile boolean running = false;
    Game gameObjects = Asteroids.gameObjects;
    Asteroid[] largeAsteroid = gameObjects.getLargeAsteroid();
    private int x;
    private int y;
      
    
    public GamePanel(){
        setPreferredSize(gameDim);
        setBackground(Color.WHITE);
        setFocusable(true);
        requestFocus();
        
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                
            }
            @Override
            public void keyReleased(KeyEvent e){
                
            }
            @Override
            public void keyTyped(KeyEvent e){
                
            }
        });
    }
        
    public void run(){
        while(running){
            GameControl.advanceGame();
            
            gameUpdate();
            gameRender();
            paintScreen();
            
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void gameUpdate(){
        if(running && game != null){
            //update game state
        }
    }
    
    private void gameRender(){
        if(dbImage == null){
            dbImage = createImage(GWIDTH, GHEIGHT);
            if(dbImage == null){
                System.err.println("dbImage is still null.");
            }
            else{
                dbg = dbImage.getGraphics();
            }
        }
        //Clear Screen
        dbg.setColor(Color.WHITE);
        dbg.fillRect(0,0, GWIDTH, GHEIGHT);
        
        //Draw Game
        draw(dbg);
    }
    
    private void draw(Graphics g) {
        
        g.setColor(Color.red);
        for (int i = 0; i < Constants.ASTEROID_LARGE_COUNT; i++){
            g.drawPolygon(largeAsteroid[i].getxPoints(), largeAsteroid[i].getyPoints(), largeAsteroid[i].getSides());
        }
    }
    
    private void paintScreen(){
        Graphics g;
        try{
            g = this.getGraphics();
            if(dbImage!= null && g != null){
                g.drawImage(dbImage, 0, 0, null);
            }

            g.dispose();
        }catch(Exception e){
            System.err.println(e);
        }
    }
    
    public void addNotify(){
        super.addNotify();
        startGame();
    }
    
    private void startGame(){
        if(game == null || !running){
            game = new Thread(this);
            game.start();
            running = true;
        }
    }
    
    public void stopGame(){
        if(running){
            running = false;
        }
    }
    
    private void log(String s){
        System.out.println(s);
    }


    
}
