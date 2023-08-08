package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;
import java.util.Objects;

public class Sound {

    Clip clip;
    URL[] soundURL = new URL[30];
    public long clipTime;
    FloatControl fc;
    int volumeScale = 3;
    float volume;

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
        soundURL[9] = Objects.requireNonNull(getClass().getResource("/sound/cursor.wav"));
        soundURL[10] = Objects.requireNonNull(getClass().getResource("/sound/burning.wav"));
        soundURL[11] = Objects.requireNonNull(getClass().getResource("/sound/cuttree.wav"));
        soundURL[12] = Objects.requireNonNull(getClass().getResource("/sound/gameover.wav"));
        soundURL[13] = Objects.requireNonNull(getClass().getResource("/sound/stairs.wav"));
    }

    public void setFile(int i) {

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();

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
        clipTime = clip.getMicrosecondPosition();
        clip.stop();
    }

    public void resume() {
        clip.setMicrosecondPosition(clipTime);
        clip.start();
    }

    public void checkVolume() {

        switch(volumeScale) {
            case 0 -> volume = -80f;
            case 1 -> volume = -20f;
            case 2 -> volume = -12f;
            case 3 -> volume = -5f;
            case 4 -> volume = 1f;
            case 5 -> volume = 6f;
        }

        fc.setValue(volume);
    }
}
