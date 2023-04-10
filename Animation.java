/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package animation;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author FaLL-Nou
 */
public class Animation {
    private final List<BufferedImage> frames;
    private int framesIndex = 0;
    private int afterTime;
    private long previousTime;
    
    public Animation(int afterTime){
        this.afterTime = afterTime;
        frames = new ArrayList<BufferedImage>();
    }
    
    public void updateFrame(){
        if(System.currentTimeMillis() - previousTime > afterTime){
        framesIndex ++;
        if(framesIndex >= frames.size()){
            framesIndex = 0;
        }
            previousTime = System.currentTimeMillis();
        }
    }
    
    public void addFrame(BufferedImage frame){
        frames.add(frame);
    }
    
    public BufferedImage getFrame(){
        if(!frames.isEmpty()){
            return frames.get(framesIndex);
        }
        
        return null;
    }
}
