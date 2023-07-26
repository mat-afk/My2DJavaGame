package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;

    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;

    boolean checkDrawTime;

    public KeyHandler(GamePanel gp) { this.gp = gp; }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int code = e.getKeyCode();

        // Title State
        if(gp.gameState == gp.titleState) { titleState(code); }

        // Play State
        else if(gp.gameState == gp.playState) { playState(code); }

        // Pause State
        else if(gp.gameState == gp.pauseState) { pauseState(code); }

        // Dialogue State
        else if(gp.gameState == gp.dialogueState) { dialogueState(code); }

        // Character status State
        else if(gp.gameState == gp.characterState) { characterState(code); }
    }

    public void titleState(int code) {

        if(gp.ui.titleScreenState == 0) {
            if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0)
                    gp.ui.commandNum = 2;
            }

            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2)
                    gp.ui.commandNum = 0;
            }

            if(code == KeyEvent.VK_ENTER) {
                switch (gp.ui.commandNum) {
                    case 0 -> gp.ui.titleScreenState = 1;
                    case 1 -> {
                        // add later
                    }
                    case 2 -> System.exit(0);
                }
            }

        } else if(gp.ui.titleScreenState == 1) {
            if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0)
                    gp.ui.commandNum = 3;
            }

            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 3)
                    gp.ui.commandNum = 0;
            }

            if(code == KeyEvent.VK_ENTER) {
                switch (gp.ui.commandNum) {
                    case 0, 1, 2 -> {
                        // do some class specific stuff
                        gp.gameState = gp.playState;
                        gp.playMusic(0);
                    }
                    case 3 -> gp.ui.titleScreenState = 0;
                }
            }
        }
    }

    public void playState(int code) {
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = true;
        }

        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = true;
        }

        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }

        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }

        if(code == KeyEvent.VK_P) {
            gp.gameState = gp.pauseState;
        }

        if(code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }

        if(code == KeyEvent.VK_C) {
            gp.gameState = gp.characterState;
        }

        if(code == KeyEvent.VK_T) {
            checkDrawTime = !checkDrawTime;
        }
    }

    public void pauseState(int code) {
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.playState;
        }
    }

    public void dialogueState(int code) {
        if(code == KeyEvent.VK_ENTER) {
            gp.gameState = gp.playState;
        }
    }

    public void characterState(int code) {
        if(code == KeyEvent.VK_C) {
            gp.gameState = gp.playState;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = false; 
        }

        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = false;
        }

        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }

        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
    }
}
