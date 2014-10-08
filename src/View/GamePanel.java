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
import Model.Ship;
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
    int keyPressLength = 0;
    Asteroid[] largeAsteroid = gameObjects.getLargeAsteroid();
    Ship ship = gameObjects.getShip();
    private int x;
    private int y;
 
    /*
    public class AL extends KeyAdapter {
        public void keyPressed(KeyEvent e){
            
            int keyCode = e.getKeyCode();
            if (keyCode == KeyEvent.VK_UP){
                
            }
            if (keyCode == KeyEvent.VK_DOWN){
                
            }
            if (keyCode == KeyEvent.VK_LEFT){
                
            }
            if (keyCode == KeyEvent.VK_RIGHT){
                
            }
        }
        public void keyReleased(KeyEvent e){
            
        }
    }*/
    
    public GamePanel(){
        setPreferredSize(gameDim);
        setBackground(Constants.BACKGROUND_COLOR);
        setFocusable(true);
        requestFocus();
        
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                GameControl.keyPress(e);
            }
            @Override
            public void keyReleased(KeyEvent e){
                GameControl.keyRelease(e);
            }
            @Override
            public void keyTyped(KeyEvent e){
                
            }
        });
    }
        
    public void run(){
        while(running){

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
            GameControl.advanceGame();
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
        dbg.setColor(Color.BLACK);
        dbg.fillRect(0,0, GWIDTH, GHEIGHT);
        
        //Draw Game
        draw(dbg);
    }
    
    private void draw(Graphics g) {
        
        GameControl.drawObjects(g);
        
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
