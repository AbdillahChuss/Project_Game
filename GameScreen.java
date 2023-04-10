/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package userinterface;

import animation.Resource;
import gameobj.Background;
import gameobj.EnemyManager;
import gameobj.Musuh;
import gameobj.Shark;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

/**
 *
 * @author FaLL-Nou
 */
public class GameScreen extends JPanel implements Runnable, KeyListener{
    public static final int FirstState = 0;
    public static final int PLAY = 1;
    public static final int GAME_OVER = 2;
    public static final float GRAVITASI = 0.2F;
    public static final float GROUND = 300 ;
    public static int score;

    private Thread thread;
    private Shark s;
    private Background bg;
    private EnemyManager em;
    private Musuh m;
    public int gameState = FirstState;
    private BufferedImage imgOver;
    private AudioClip scoreUpSound;
    private boolean isKeyPressed;
    
  

    
    public GameScreen(){
       thread = new Thread(this);
       s = new Shark();
       m = new Musuh(s);
       s.setX(50);
       s.setY(80);
       bg = new Background();
       em = new EnemyManager(s, this);
       imgOver = Resource.getResourceImage("src/assets/gameover_text.png");
        try {
           scoreUpSound =  Applet.newAudioClip(new URL("file","","src/assets/scoreup.wav"));
        } catch (MalformedURLException ex) {
            Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public void StartGame(){
        thread.start();
    }

    @Override
    public void run() {
                int fps = 100;
		long msPerFrame = 1000 * 1000000 / fps;
		long lastTime = 0;
		long elapsed;
		
		int msSleep;
		int nanoSleep;

		long endProcessGame;
		long lag = 0;
                
            while(true){
                    update();
                    repaint();
                    endProcessGame = System.nanoTime();
                    elapsed = (lastTime + msPerFrame - System.nanoTime());
                    msSleep = (int) (elapsed / 1000000);
                    nanoSleep = (int) (elapsed % 1000000);
                    if (msSleep <= 0) {
                          lastTime = System.nanoTime();
                           continue;
                        }
                          try {
                             Thread.sleep(msSleep, nanoSleep);
                        } catch (InterruptedException e) {
                              e.printStackTrace();
                        }
                       lastTime = System.nanoTime();
            }
    }
    
    public void update(){
        switch(gameState){
            case PLAY:
                s.update();
                bg.Bgupdate();
                em.update();
                if(!s.getAllive()){
                    gameState = GAME_OVER;
                }
                break;
        }
    }
    
    public void resetGame(){
       score = 0;
       s.setAllive(true);
       s.setX(50);
       s.setY(80);
       em.reset();
       
    }
   
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.setColor(Color.decode("#f7f7f7"));
	g.fillRect(0, 0, getWidth(), getHeight());

        switch(gameState){
            case FirstState:
                s.graphics(g);
                g.setColor(Color.decode("#000000"));
                g.drawString("Press Space To Play", 500, 100);
          
                break;
            case PLAY:
                bg.draw(g);
                s.graphics(g);
                em.draw(g);
                g.drawString("HI "+String.valueOf(score),500, 20);
                break;
            case GAME_OVER:
                bg.draw(g);
                s.graphics(g);
                em.draw(g);
                g.drawImage(imgOver, 100, 50, null);
                break;
          
        }   
        
    }
    
    public void plusScore(int scoree){
        this.score += scoree;
        if(score % 100 == 0){
          scoreUpSound.play();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(!isKeyPressed){
        isKeyPressed = true;
        switch(e.getKeyCode()){
            case KeyEvent.VK_SPACE:
                if(gameState == FirstState){
                    gameState = PLAY;
                }else if(gameState == PLAY){
                    s.mencolot();
                   
                }else if(gameState == GAME_OVER){
                    resetGame();
                    gameState = PLAY;      
                    
                }
                break;
        }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {  
       isKeyPressed = false;
    }
}
