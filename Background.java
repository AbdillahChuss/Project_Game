
package gameobj;

import animation.Resource;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;
import static userinterface.GameScreen.GROUND;


public class Background {
    private BufferedImage bgimg, bgimg2;
    private List<Bg> listBg;
    private Random random;
    
    public Background(){
         random = new Random();
         bgimg = Resource.getResourceImage("src/assets/4157355.jpg");
         bgimg2 = Resource.getResourceImage("src/assets/785.jpg");
         listBg = new ArrayList<Bg>();
         int bground = 600 / bgimg2.getWidth() + 2;
         System.out.println(bground);
         for(int i =0; i < bground; i++){
             Bg bg = new Bg();
             bg.posX = (int) (i * bgimg2.getWidth());
             bg.img = getBg();
             listBg.add(bg);
         }
    }
    
    public void Bgupdate(){
        for(Bg bg : listBg){
            bg.posX -=5;
        }
        Bg bgFirstElement = listBg.get(0);
        if(bgFirstElement.posX + bgimg.getWidth() < 0){
            bgFirstElement.posX =listBg.get(listBg.size() - 1).posX + bgimg2.getWidth();
            listBg.add(bgFirstElement);
            listBg.remove(0);
        }
    }
    
    public void draw(Graphics g){
        for(Bg bg: listBg){
        g.drawImage(bg.img, bg.posX, (int) 0, null);
        }
    }
    
    private BufferedImage getBg(){
        int r = random.nextInt(2);
        switch(r){
            case 0:
                return bgimg2;
            default:
                return bgimg2;
        }
    }
    
    private class Bg{
        int posX;
        BufferedImage img;
    }
}
