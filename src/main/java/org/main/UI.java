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
    public boolean platePainted = false;
    public String message;
    boolean levelFinished = false;
    int messageCounter = 0;

    public UI(MainJPanel gp) throws IOException {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        bufferedImageBackground = ImageIO.read(getClass().getResourceAsStream("/objects/messageBackground.png"));
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);
        if (gp.gameState == gp.playState) {
            if (!gp.themePlay) {
                gp.playMusic(0);
            }
            if (messageOn) {
                g2.setColor(Color.black);
                g2.drawImage(bufferedImageBackground, (gp.finalSize * 2) + 80, 25, gp.screenWidth - 25 - ((gp.finalSize * 2) + 80), gp.finalSize * 2, null);
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, (gp.finalSize * 2) + 100, gp.finalSize + 40);
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
        } else if (gp.gameState == gp.fightState) {
            if (gp.reasonOfDialogue == 1) {
                gp.stopMusic();
                g2.setColor(Color.black);
                g2.drawImage(bufferedImageBackground, (gp.finalSize * 2) + 80, 25, gp.screenWidth - 25 - ((gp.finalSize * 2) + 80), gp.finalSize * 2, null);
                g2.setFont(g2.getFont().deriveFont(30F));
                drawFightScreen();
            }
        }
    }

    private void drawFightScreen() {
        g2.setColor(Color.WHITE);
        long l = System.nanoTime();
        if (!platePainted) {
            for (int i = 0; i < gp.screenWidth; i++) {
                for (int j = 0; j < gp.screenHeight; j++) {
                    if (l < System.nanoTime() - 1000) {
                        g2.fillRect((i * gp.finalSize), (j * gp.finalSize), gp.finalSize, gp.finalSize);
                        gp.repaint();
                        l = System.nanoTime();
                    }
                }
            }
        }
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        platePainted = false;
        g2.drawString("HEY! STOP RIGHT THERE!", (gp.finalSize * 2) + 100, gp.finalSize + 40);
    }

    public void drawPauseScreen() {
        g2.setColor(Color.BLACK);
        drawInCenterOfScreen("PAUSE");
    }

    private void drawInCenterOfScreen(String text) {
        int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = (gp.screenWidth / 2) - textLength / 2, y = gp.screenHeight / 2;
        g2.drawImage(bufferedImageBackground, x - 20, y - gp.finalSize, textLength + 40, gp.finalSize + 30, null);
        g2.drawString(text, x, y);
    }

    private void endGameMessage(Graphics2D g2) {
        g2.setColor(Color.YELLOW);
        g2.setFont(new Font("arial_80B", Font.BOLD, 44));
        drawInCenterOfScreen("LEVEL COMPLETED");
    }
}
