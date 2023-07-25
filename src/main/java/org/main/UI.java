package org.main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UI {
    MainJPanel gp;
    Font arial_40;
    BufferedImage bufferedImageBackground;
    Graphics2D g2;
    public boolean messageOn = false;
    public String message;
    boolean levelFinished = false;
    int messageCounter = 0;

    public UI(MainJPanel gp) throws IOException {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        bufferedImageBackground = ImageIO.read(getClass().getResourceAsStream("/objects/messageBackground.png"));
    }

    public void showMessage(String text) {
        messageOn = true;
        message = text;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);
        if (gp.gameState == gp.playState) {
            if(!gp.themePlay){
                gp.playMusic(0);
            }
            if (messageOn) {
                g2.setColor(new Color(255,255,255,200));
                g2.fillRoundRect(gp.finalSize*4, gp.finalSize/2, (int) g2.getFontMetrics().getStringBounds(message, g2).getWidth()-10,gp.finalSize*2,25,25);
                g2.setStroke(new BasicStroke(5));
                g2.setColor(Color.BLACK);
                g2.drawRoundRect((gp.finalSize*4)+5, (gp.finalSize/2)+5, (int) (g2.getFontMetrics().getStringBounds(message, g2).getWidth()-10)-10,(gp.finalSize*2)-10,25,25);
                g2.setColor(Color.black);
                g2.setFont(g2.getFont().deriveFont(25F));
                g2.drawString(message, (gp.finalSize * 2) + 120, gp.finalSize + 40);
                messageCounter++;
                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        } else if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
            gp.stopMusic();
        } else if (gp.gameState == gp.endState) {
            endGameMessage(g2);
            gp.stopMusic();
            gp.stopGameThread();
        }else if (gp.gameState == gp.fightState){
            if(gp.reasonOfDialogue == 1){
                levelFinished = true;
                gp.stopMusic();
                g2.setColor(Color.black);
                g2.drawImage(bufferedImageBackground, (gp.finalSize * 2) + 80, 25, gp.screenWidth - 25 - ((gp.finalSize * 2) + 80), gp.finalSize * 2, null);
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString("HEY! STOP RIGHT THERE!", (gp.finalSize * 2) + 100, gp.finalSize + 40);
                drawFightScreen();
            }
        }
    }

    private void drawFightScreen() {

    }

    public void drawPauseScreen() {
        g2.setColor(Color.BLACK);
        drawInCenterOfScreen( "PAUSE");
    }

    private void drawInCenterOfScreen(String text) {
        g2.setColor(new Color(255,255,255,200));
        int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = (gp.screenWidth / 2)-textLength/2, y = gp.screenHeight/2;
        g2.fillRoundRect(x-20, y-gp.finalSize, textLength + 40, gp.finalSize+30, 25,25);
        g2.setStroke(new BasicStroke(5));
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(x-20+5, y-gp.finalSize+5, textLength + 40-10, gp.finalSize+30-10, 25,25);
        g2.drawString(text, x, y);
    }

    private void endGameMessage(Graphics2D g2) {
        g2.setColor(Color.YELLOW);
        g2.setFont(new Font("arial_80B", Font.BOLD, 44));
        drawInCenterOfScreen( "LEVEL COMPLETED");
    }
}
