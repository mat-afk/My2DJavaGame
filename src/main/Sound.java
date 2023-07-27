package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;
import java.util.Objects;

public class Sound {

    Clip clip;
    URL[] soundURL = new URL[30];

    public Sound() {

        soundURL[0] = Objects.requireNonNull(getClass().getResource("/sound/BlueBoyAdventure.wav"));
        soundURL[1] = Objects.requireNonNull(getClass().getResource("/sound/coin.wav"));
        soundURL[2] = Objects.requireNonNull(getClass().getResource("/sound/powerup.wav"));
        soundURL[3] = Objects.requireNonNull(getClass().getResource("/sound/unlock.wav"));
        soundURL[4] = Objects.requireNonNull(getClass().getResource("/sound/fanfare.wav"));
        soundURL[5] = Objects.requireNonNull(getClass().getResource("/sound/hitmonster.wav"));
        soundURL[6] = Objects.requireNonNull(getClass().getResource("/sound/receivedamage.wav"));
        soundURL[7] = Objects.requireNonNull(getClass().getResource("/sound/swingweapon.wav"));
        soundURL[8] = Objects.requireNonNull(getClass().getResource("/sound/levelup.wav"));
    }

    public void setFile(int i) {

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
