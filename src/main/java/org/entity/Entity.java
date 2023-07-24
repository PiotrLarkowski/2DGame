package org.entity;

import org.main.MainJPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Entity {
    MainJPanel gp;
    public int worldX, worldY;
    public int speed;
    public Rectangle basicArea = new Rectangle(0,0 ,48, 48);
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, imgDef1, imgDef2,imgDef3,imgDef4;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;
    public int solidAreaDefaultX,solidAreaDefaultY;
    public boolean collisionOn = false;

    public Entity(MainJPanel gp){
        this.gp = gp;
    }

    public BufferedImage setup(String imagePath){
        BufferedImage image = null;
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
        }catch(Exception e){
            e.printStackTrace();
        }
        return image;
    }
}
