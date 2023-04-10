/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package userinterface;


import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import static userinterface.GameScreen.GAME_OVER;


public class GameWindow extends JFrame{
    private GameScreen gs;
    private AudioInputStream musik ;
    
    
GameWindow(){
        super("RunAway Java Game");
        setSize(750, 350);
        setLocation(600, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gs = new GameScreen();
        add(gs);
        addKeyListener(gs);
    }

 public void suara()
    {

         URL url=this.getClass().getResource("Kirby dream land theme song.wav");
            try
            {
               Clip klip =AudioSystem.getClip();
               musik = AudioSystem.getAudioInputStream(url);                
               klip.open(musik);
               klip.loop(2);
            }
            catch (UnsupportedAudioFileException e)
            {}
            catch (IOException e)
            {}
            catch (LineUnavailableException e)
            {}
    }
public void StartGame(){
        suara(); 
        gs.StartGame();
}
    public static void main(String[] args) {
        GameWindow gw = new GameWindow();
        gw.StartGame();
        gw.setVisible(true);
    }

    }
    

