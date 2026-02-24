package src.Nut1;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.Timer;

public class useSound {

    private Clip bgClip;

    public void playsound(String sound, double duration) {
        Timer delay = new Timer((int) (1000 * duration), e -> {
            try {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File(sound).getAbsoluteFile());
                Clip clip = AudioSystem.getClip();

                clip.open(audioInput);
                clip.start();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        delay.setRepeats(false);
        delay.start();

    }

    public void loopSound(String sound, String action) {
        try {
            if (action.equals("start")) {

                if (bgClip != null && bgClip.isRunning()) {
                    bgClip.stop();
                }
                File soundFile = new File(sound).getAbsoluteFile();
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundFile);
                bgClip = AudioSystem.getClip();
                bgClip.open(audioInput);
                bgClip.loop(Clip.LOOP_CONTINUOUSLY);

                FloatControl gainControl = (FloatControl) bgClip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-15.0f);
                bgClip.start();

            } else if (action.equals("stop")) {
                if (bgClip != null) {
                    bgClip.stop();
                    bgClip.close();
                }
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }
}
