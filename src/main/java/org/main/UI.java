package org.main;

import org.objects.ObjectBook;

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
        arial_40 = new Font("Arial",Font.PLAIN,40);
        bufferedImageBackground = ImageIO.read(getClass().getResourceAsStream("/objects/messageBackground.png"));
    }
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
    public void draw (Graphics2D g2){
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);
        if(gp.gameState == gp.playState){
            if(messageOn){
                g2.setColor(Color.black);
                g2.drawImage(bufferedImageBackground, (gp.finalSize*2)+80, 25,gp.screenWidth - 25-((gp.finalSize*2)+80),gp.finalSize*2,null);
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message,(gp.finalSize*2)+100,gp.finalSize+40);
                messageCounter++;
                if(messageCounter > 120){
                    messageCounter=0;
                    messageOn=false;
                }
            }
        }else if(gp.gameState == gp.pauseState){

        }else if(gp.gameState == gp.endState){
            endGameMessage(g2);
            gp.stopMusic();
            gp.stopGameThread();
        }
    }

    private void endGameMessage(Graphics2D g2) {
        g2.setColor(Color.YELLOW);
        g2.setFont(new Font("arial_80B",Font.BOLD,44));
        String text = "LEVEL COMPLETED";
        int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        g2.drawImage(bufferedImageBackground, (gp.screenWidth/2 - textLength/2)-20, (gp.screenHeight/2)-gp.finalSize-10,textLength+40,(gp.finalSize*2)+40,null);
        g2.drawString(text,(gp.screenWidth/2 - textLength/2),(gp.screenHeight/2));
        g2.drawString("CONGRATULATIONS",(gp.screenWidth/2 - textLength/2)-10,(gp.screenHeight/2)+40);
    }
}
