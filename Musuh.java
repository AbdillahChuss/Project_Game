
package gameobj;

import animation.Animation;
import animation.Resource;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import userinterface.GameScreen;
import static userinterface.GameScreen.GROUND;
import static userinterface.GameScreen.score;


public class Musuh extends Enemey{
    private BufferedImage coral;
    private int posX, posY, i = 0, width, height, jarak;
    private Rectangle react;
    private Shark shark;
    private boolean getScore = false;

    public Musuh(Shark s){
       this.shark = s;
       coral = Resource.getResourceImage("src/assets/coral.png");
       react = new Rectangle();
   
    }
    
    
    
    @Override
    public void update(){               
          for(int i = 0; i<=score; i+=100){
            posX -= 1;              
        }
        posX -= 2;
        react.x = posX + (coral.getWidth() - width)/2;
        react.y = (int)GROUND - coral.getHeight() + (coral.getHeight()-height)/2;
        react.width = coral.getWidth();
        react.height = coral.getHeight();
    }
    
    
    
    @Override
    public Rectangle getBound(){
        return react;
    }
    
    @Override
    public void draw(Graphics g){
        g.drawImage(coral, posX, posY, null);
    
    }

    public int setPosX(int posX) {
        this.posX = posX;
        return posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    } 
    
    public void setImage(BufferedImage image){
        this.coral = image;
   
    }

    @Override
    public boolean isOutOfScreen() { 
      return (posX + coral.getWidth() < 0);
      
    }



    @Override
    public boolean MelewatiCoral() {
        return (shark.getX() > posX);
      
    }
 

    @Override
    public boolean getScore() {
       return getScore;
    }

    @Override
    public void setScore(boolean getScore) {
          this.getScore = getScore;
    }

  

}
