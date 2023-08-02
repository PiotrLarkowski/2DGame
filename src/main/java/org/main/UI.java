package org.main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class UI {
    public int commandNumber = 0;
    MainJPanel gp;
    public Font inkFree, clarendon;
    BufferedImage bufferedImageBackground;
    Graphics2D g2;
    public int color = 1;
    public boolean messageOn = false;

    int pointer = 0;
    public String message;
    boolean levelFinished = false;
    int messageCounter = 0;

    public UI(MainJPanel gp) {
        this.gp = gp;
        try {
            InputStream inputStream = getClass().getResourceAsStream("/fonts/Inkfree.ttf");
            inkFree = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            inputStream = getClass().getResourceAsStream("/fonts/clarendon.ttf");
            clarendon = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            bufferedImageBackground = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/messageBackground.png")));
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showMessage(String text) {
        messageOn = true;
        message = text;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(inkFree);
        if (gp.gameState == gp.menuState){
            drawMenuScreen();
        }else if (gp.gameState == gp.playState) {
            if (!gp.themePlay) {
                gp.playMusic(0);
                gp.themePlay = true;
            }
            if (messageOn) {
                int yPositionModerator = 0;
                if (color == 1) {
                    g2.setColor(new Color(255, 255, 255, 200));
                    yPositionModerator = 1;
                } else if (color == 2) {
                    g2.setColor(new Color(255, 255, 0, 200));
                    yPositionModerator = 5;
                }
                g2.fillRoundRect(gp.finalSize * 4, gp.finalSize / 2 * yPositionModerator, 500, gp.finalSize * 2, 25, 25);
                g2.setStroke(new BasicStroke(3));
                g2.setColor(Color.BLACK);
                g2.drawRoundRect((gp.finalSize * 4) + 5, (gp.finalSize / 2 * yPositionModerator) + 5, 500 - 10, (gp.finalSize * 2) - 10, 25, 25);
                g2.setColor(Color.black);
                g2.setFont(g2.getFont().deriveFont(25F));
                if (color == 1) {
                    g2.drawString(message, (gp.finalSize * 2) + 120, gp.finalSize + 40);
                } else if (color == 2) {
                    g2.drawString(message, (gp.finalSize * 2) + 120, gp.finalSize + 40 * (yPositionModerator - 2));
                }
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
        } else if (gp.gameState == gp.fightState) {

        } else if (gp.gameState == gp.spellBookState) {
            drawSpellBook();
        }
    }

        public void  drawMenuScreen(){
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
            String text = "Arcania";
            int x = getXForCenterOfString(text);
            int y = 150;

            g2.setColor(new Color(128,128,128,100));
            g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
            g2.setColor(Color.BLACK);
            g2.drawString(text,x+5,y+5);

            g2.setColor(new Color(255,255,255,200));
            g2.drawString(text,x,y);

            int[] leftPositionsOfDrawing = {-100,-60,-160,100,190,250};
            int[] rightPositionsOfDrawing = {500,100,450,180,470,250};
            leftMagesMenuDraw(leftPositionsOfDrawing);
            rightMagesMenuDraw(rightPositionsOfDrawing);

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));
            text = "NEW GAME";
            if(commandNumber == 0){
                g2.drawLine(gp.screenWidth/2-100,320,500,320);
            }
            g2.drawString(text,getXForCenterOfString(text),y+150);
            text = "LOAD GAME";
            if(commandNumber == 1){
                g2.drawLine(gp.screenWidth/2-120,400,520,400);
            }
            g2.drawString(text,getXForCenterOfString(text),y+230);
            if(commandNumber == 2){
                g2.drawLine(gp.screenWidth/2-70,480,460,480);
            }
            text = "QUITE";
            g2.drawString(text,getXForCenterOfString(text),y+310);
        }

    private void leftMagesMenuDraw(int[] xPositionOfDrawing) {
        g2.drawImage(gp.player.right1,xPositionOfDrawing[0],xPositionOfDrawing[3],gp.finalSize*8,gp.finalSize*8,null);
        g2.drawImage(gp.player.right1,xPositionOfDrawing[1],xPositionOfDrawing[4],gp.finalSize*8,gp.finalSize*8,null);
        g2.drawImage(gp.player.right1,xPositionOfDrawing[2],xPositionOfDrawing[5],gp.finalSize*8,gp.finalSize*8,null);
    }

    private void rightMagesMenuDraw(int[] rightPositionsOfDrawing) {
        g2.drawImage(gp.player.left1, rightPositionsOfDrawing[0],rightPositionsOfDrawing[1], gp.finalSize * 8, gp.finalSize * 8, null);
        g2.drawImage(gp.player.left1, rightPositionsOfDrawing[2], rightPositionsOfDrawing[3], gp.finalSize * 8, gp.finalSize * 8, null);
        g2.drawImage(gp.player.left1, rightPositionsOfDrawing[4], rightPositionsOfDrawing[5], gp.finalSize * 8, gp.finalSize * 8, null);
    }

    public void drawSpellBook() {
        g2.setColor(new Color(255, 255, 255, 200));
        g2.fillRoundRect(8 * gp.finalSize, gp.finalSize, gp.finalSize * 7, gp.finalSize * 10, 35, 35);
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect((8 * gp.finalSize) + 5, (gp.finalSize) + 5, (gp.finalSize * 7) - 10, (gp.finalSize * 10) - 10, 35, 35);
        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        g2.drawLine(17 * (gp.finalSize / 2), 5 * (gp.finalSize / 2), 29 * (gp.finalSize / 2), 5 * (gp.finalSize / 2));
        g2.drawLine(47 * (gp.finalSize / 4), 4 * (gp.finalSize / 2), 47 * (gp.finalSize / 4), 21 * (gp.finalSize / 2));
        g2.drawString("Universal spells", 17 * (gp.finalSize / 2), 2 * gp.finalSize);
        g2.drawString("Fighting spells", 24 * (gp.finalSize / 2), 2 * gp.finalSize);
        for (int i = 0; i < gp.universalSpellBook.size(); i++) {
            if (pointer == i) {
                g2.drawString(" > ", 17 * (gp.finalSize / 2), (4 + i) * gp.finalSize);
            }
            g2.drawString(gp.universalSpellBook.get(i), 19 * (gp.finalSize / 2), (4 + i) * gp.finalSize);
        }
        if (gp.universalSpellBook.size() == 0) {
            g2.drawString("EMPTY", 19 * (gp.finalSize / 2), 4 * gp.finalSize);
        }
        if (gp.fightingSpellBook.size() == 0) {
            g2.drawString("EMPTY", 25 * (gp.finalSize / 2), 4 * gp.finalSize);
        } else {
            for (int i = 0; i < gp.fightingSpellBook.size(); i++) {
                g2.drawString(gp.fightingSpellBook.get(i), 25 * (gp.finalSize / 2), (4 + i) * gp.finalSize);
            }
        }
    }

    private void drawFightScreen() {
        g2.setColor(new Color(255, 255, 255));
        g2.fillRoundRect(gp.finalSize, gp.finalSize, gp.screenWidth - (2 * gp.finalSize), gp.screenHeight - gp.finalSize - (2 * gp.finalSize), 35, 35);
    }

    public void drawPauseScreen() {
        g2.setColor(Color.BLACK);
        drawInCenterOfScreen("PAUSE");
    }

    private void drawInCenterOfScreen(String text) {
        g2.setColor(new Color(255, 255, 255, 200));
        int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = (gp.screenWidth / 2) - textLength / 2, y = gp.screenHeight / 2;
        g2.fillRoundRect(x - 20, y - gp.finalSize, textLength + 40, gp.finalSize + 30, 25, 25);
        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(x - 20 + 5, y - gp.finalSize + 5, textLength + 40 - 10, gp.finalSize + 30 - 10, 25, 25);
        g2.drawString(text, x, y);
    }

    void endGameMessage(Graphics2D g2) {
        g2.setColor(Color.GRAY);
        g2.setFont(new Font("Arial", Font.BOLD, 38));
        int x = gp.screenWidth / 2, y = gp.screenHeight / 2;
        g2.fillRoundRect(x-5*gp.finalSize, y/2-gp.finalSize, 11*gp.finalSize, gp.finalSize * 9,20,20);
        g2.setColor(Color.BLACK);
        g2.fillRect(x-4*gp.finalSize, y/2, 9*gp.finalSize, gp.finalSize * 7);
        g2.setColor(Color.YELLOW);
        g2.drawString("LEVEL COMPLETED", x/2+gp.finalSize/2, y/2+40);
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(x-(3*gp.finalSize), y/2+gp.finalSize, 7*gp.finalSize, gp.finalSize,20,20);
        g2.setFont(new Font("Arial", Font.BOLD, 24));
        g2.setColor(Color.GRAY);
        g2.drawString("ENTER - NEXT LEVEL", x-(2*gp.finalSize), (gp.finalSize/2)*9);//48 - 96
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(x-(3*gp.finalSize), y/2+(3*gp.finalSize), 7*gp.finalSize, gp.finalSize,20,20);
        g2.setColor(Color.GRAY);
        g2.drawString("R - RELOAD", x-gp.finalSize, (gp.finalSize/2)*13);
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(x-(3*gp.finalSize), y/2+(5*gp.finalSize), 7*gp.finalSize, gp.finalSize,20,20);
        g2.setColor(Color.GRAY);
        g2.drawString("ESC - CLOSE", x-gp.finalSize, (gp.finalSize/2)*17);
    }
    public int getXForCenterOfString(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
    public int getYForCenterOfString(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getHeight();
        int y = gp.screenHeight/2 - length/2;
        return y;
    }
}
