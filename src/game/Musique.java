package game;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
 
public class Musique {
    public Clip audioClip;
 
    Musique(String path) {
        try {
            // Create audio input
            URL url = this.getClass().getClassLoader().getResource(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
            // Obtain clip
            audioClip = (Clip) AudioSystem.getClip();
            audioClip.open(audioStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    void play() {
        // Open stream and play
        try {
            if(!audioClip.isOpen())
                audioClip.open();
            audioClip.stop();
            audioClip.setMicrosecondPosition(0);
            audioClip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
