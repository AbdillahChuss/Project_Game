
package gameobj;

import animation.Animation;
import animation.Resource;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import userinterface.GameScreen;
import static userinterface.GameScreen.score;

public class EnemyManager{
    private List<Enemey> enemies;
    private BufferedImage corall, seaweed;
    private Random random;
    private Shark shark; 
    private GameScreen gs;
    private int jarak;
    
    public EnemyManager(Shark s, GameScreen gs) {
       this.shark = s;
       this.gs = gs;
       enemies = new ArrayList<Enemey>();
       corall = Resource.getResourceImage("src/assets/coral.png");
       seaweed = Resource.getResourceImage("src/assets/seaweed.png");
       random = new Random();
       enemies.add(MusuhRandom());
       
    }
    
    public void update(){
        
        for(Enemey e : enemies){
            e.update();
            if(e.MelewatiCoral() && !e.getScore()){
                gs.plusScore(10);
                e.setScore(true);
            }
            if(e.getBound().intersects(shark.getBound())){
                shark.setAllive(false);
            }
        }
        Enemey enemy = enemies.get(0);
        if(enemy.isOutOfScreen()){
            enemies.remove(enemy);
            enemies.add(MusuhRandom());
        }
    }
    
    public void reset(){
        enemies.clear();
        enemies.add(MusuhRandom());
    }

    public void draw(Graphics g) {
      for(Enemey e : enemies){
        e.draw(g);
      }
    }
    
    
    private Musuh MusuhRandom(){
        Musuh m = new Musuh(shark);
        for(int i = 0; i<=100; i++){
            jarak += m.setPosX(400);
        }
        m.setPosX(500);  
        m.setPosY(250);
        if(random.nextBoolean()){
            m.setImage(corall);     
        }
        else{
           m.setImage(seaweed);
        }
        
        return m;
    }
}
