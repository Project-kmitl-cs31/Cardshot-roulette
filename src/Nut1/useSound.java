package src.Nut1;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Timer;

public class useSound {

    
    public void playsound(String sound,double duration){
        Timer delay = new Timer((int) (1000*duration),e->{
            try{
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File(sound).getAbsoluteFile());
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
            }catch(Exception e1){
                e1.printStackTrace();
            }
        });
        delay.setRepeats(false);
        delay.start();
        
    }
}
