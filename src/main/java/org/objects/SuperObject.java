package org.objects;

import org.main.MainJPanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    public int connectGateTurnstile =0;
    public boolean used = false;
    public String spellName;

    public void draw(Graphics2D g2, MainJPanel gp){
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        if (worldX + gp.finalSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.finalSize< gp.player.worldX + gp.player.screenX &&
                worldY + gp.finalSize> gp.player.worldY - gp.player.screenY &&
                worldY - gp.finalSize< gp.player.worldY + gp.player.screenY) {
            g2.drawImage(image, screenX, screenY, gp.finalSize, gp.finalSize, null);
        }
    }
}
