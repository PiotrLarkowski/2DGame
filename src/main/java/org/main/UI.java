package org.main;

import org.objects.ObjectBook;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UI {
    MainJPanel gp;
    Font arial_40;
    BufferedImage bufferedImage;
    BufferedImage bufferedImageBackground;
    public boolean messageOn = false;
    public String message;
    int messageCounter = 0;
    public UI(MainJPanel gp) throws IOException {
        this.gp = gp;
        arial_40 = new Font("Arial",Font.PLAIN,40);
        ObjectBook bookImage= new ObjectBook();
        bufferedImage = bookImage.image;
        bufferedImageBackground = ImageIO.read(getClass().getResourceAsStream("/objects/messageBackground.png"));
    }
    public void showMessage(String text){
        message = text;
        messageOn = true;
    }
    public void draw (Graphics2D g2){
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);
        g2.drawImage(bufferedImage, gp.finalSize, 45,gp.finalSize,gp.finalSize,null);
        g2.drawString("x "+gp.player.spellRemove,(gp.finalSize*2)+20,gp.finalSize+40);
        if(messageOn){
            g2.setColor(Color.black);
            g2.drawImage(bufferedImageBackground, (gp.finalSize*2)+80, 25,gp.screenWidth - 25-((gp.finalSize*2)+80),gp.finalSize*2,null);
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message,(gp.finalSize*2)+100,gp.finalSize+40);
            messageCounter++;
            if(messageCounter > 100){
                messageCounter=0;
                messageOn=false;
            }
        }
    }
}
