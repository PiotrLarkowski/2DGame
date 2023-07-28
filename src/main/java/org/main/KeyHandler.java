package org.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean downPressed, leftPressed, upPressed, rightPressed;
    MainJPanel gp;
    int pointer = 0;

    public KeyHandler(MainJPanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(gp.gameState == gp.spellBookState){
            if(code == KeyEvent.VK_S){
                if(gp.ui.pointer == gp.spellBook.size()-1){
                    gp.ui.pointer = 0;
                }else{
                    gp.ui.pointer++;
                }
            }else if(code == KeyEvent.VK_W){
                if(gp.ui.pointer == 0){
                    gp.ui.pointer = gp.spellBook.size()-1;
                }else{
                    gp.ui.pointer--;
                }
            }
        }
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        } else if (code == KeyEvent.VK_S) {
            downPressed = true;
        } else if (code == KeyEvent.VK_A) {
            leftPressed = true;
        } else if (code == KeyEvent.VK_D) {
            rightPressed = true;
        } else if (code == KeyEvent.VK_P) {
            if (gp.gameState == gp.playState) {
                gp.gameState = gp.pauseState;
            } else {
                gp.gameState = gp.playState;
            }
        } else if (code == KeyEvent.VK_K) {
            if (gp.gameState == gp.spellBookState) {
                gp.gameState = gp.playState;
            } else {
                gp.gameState = gp.spellBookState;
            }
        }else if (code == KeyEvent.VK_F) {
            gp.lifePercentage -= 10;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        } else if (code == KeyEvent.VK_S) {
            downPressed = false;
        } else if (code == KeyEvent.VK_A) {
            leftPressed = false;
        } else if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
}
