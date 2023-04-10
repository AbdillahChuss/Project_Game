
package gameobj;

import java.awt.Graphics;
import java.awt.Rectangle;


public abstract class Enemey {
    public abstract Rectangle getBound();
    public abstract void draw(Graphics g);
    public abstract void update();
    public abstract boolean isOutOfScreen();
 
    public abstract boolean MelewatiCoral();
    public abstract boolean getScore();
    public abstract void setScore(boolean getScore);
}
