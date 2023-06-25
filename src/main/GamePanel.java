package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
    
    // Configurações da tela
    final int originalTileSize = 16;  // 16x16
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;  // 48x48
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;  // 768px
    public final int screenHeight = tileSize * maxScreenRow;  // 576px

    // World Configuration
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    int FPS = 60;

    // System
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Sound sound = new Sound();
    Sound music = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    Thread gameThread;

    // Entity and object
    public Player player = new Player(this, keyH);
    public SuperObject[] obj = new SuperObject[10];

    public GamePanel() {

        this.setPreferredSize(new DimensionUIResource(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame () {
        aSetter.setObject();

        playMusic(0);
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {

        double drawInterval = 1000000000 / (double) FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;

        while(gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
            }

            if(timer >= 1000000000) {
                timer = 0;
            }
        }
    }

    public void update() {

        player.update();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);

        for(SuperObject so : obj) {
            if(so != null) {
                so.draw(g2, this);
            }
        }

        player.draw(g2);

        g2.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    /*
    public void stopMusic() {
        music.stop();
    }
    */

    public void playSoundEffect(int i) {
        sound.setFile(i);
        sound.play();
    }
}
