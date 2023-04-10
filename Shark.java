
package gameobj;

import animation.Animation;
import animation.Resource;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import java.awt.image.BufferedImage;
import static userinterface.GameScreen.*;


public class Shark{
    private float PosX;
    private float PosY;
    private float speedX;
    private float speedY;
    private int ground_S;
    private Animation characterSwim;
    private Rectangle react;
    private boolean isAllive = true;
    
    public Shark(){
        PosX = 0;
        PosY = 0;
        ground_S = 200;
        characterSwim = new Animation(50);
        characterSwim.addFrame(Resource.getResourceImage("src/assets/shark1.png"));
        characterSwim.addFrame(Resource.getResourceImage("src/assets/shark.png"));
        react = new Rectangle();
    }
  
    
    public void update(){
        characterSwim.updateFrame();
       if (PosY >= ground_S){
           speedY = 0;
           PosY =ground_S;
        }else{
           speedY+= GRAVITASI;
           PosY+=speedY;
        }  
          react.x = (int)PosX;
          react.y = (int)PosY;
          react.width = characterSwim.getFrame().getWidth()  -10;
          react.height = characterSwim.getFrame().getHeight() -40;
    }
    
    public Rectangle getBound(){
        return react;
    }
    
    public void graphics(Graphics g){
        g.drawImage( characterSwim.getFrame(), (int) PosX, (int) PosY , null);
       
    }
    
    public void mencolot(){
        if(PosY >= ground_S){
        speedY = -10.5f;
        PosY += speedY;
      }
    }

    public float getX() {
        return PosX;
    }

    public void setX(float x) {
        this.PosX = x;
    }

    public float getY() {
        return PosY;
    }

    public void setY(int y) {
        this.PosY = y;
    }


    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedx) {
        this.speedX = speedx;
    }
    
    
    
    public void setAllive(boolean alive){
        isAllive = alive;
    }
    
    public boolean getAllive(){
        return isAllive;
    }
}