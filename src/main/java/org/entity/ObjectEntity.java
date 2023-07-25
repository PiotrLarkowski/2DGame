package org.entity;

import org.main.MainJPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class ObjectEntity {
    MainJPanel gp;
    public int actionLockCounter = 0;
    public int worldX, worldY;
    public int speed;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, imgDef1, imgDef2, imgDef3, imgDef4;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int npcStatus = 0;

    public ObjectEntity(MainJPanel gp) {
        this.gp = gp;
    }

    public BufferedImage setup(String imagePath) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(imagePath + ".png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    public void draw(Graphics2D g2) throws InterruptedException {
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        if (worldX + gp.finalSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.finalSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.finalSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.finalSize < gp.player.worldY + gp.player.screenY) {
            if (npcStatus == 0) {
                image = right1;
            }
            if (npcStatus == 1) {
                switch (direction) {
                    case "up" -> {
                        if (spriteNum == 1) {
                            image = up1;
                        }
                        if (spriteNum == 2) {
                            image = up2;
                        }
                    }
                    case "down" -> {
                        if (spriteNum == 1) {
                            image = down1;
                        }
                        if (spriteNum == 2) {
                            image = down2;
                        }
                    }
                    case "left" -> {
                        if (spriteNum == 1) {
                            image = left1;
                        }
                        if (spriteNum == 2) {
                            image = left2;
                        }
                    }
                    case "right" -> {
                        if (spriteNum == 1) {
                            image = right1;
                        }
                        if (spriteNum == 2) {
                            image = right2;
                        }
                    }
                }
            }
            g2.drawImage(image, screenX, screenY, gp.finalSize, gp.finalSize, null);
        }
    }

    public void setAction() {
    }

    public void update() {
        gp.npcArray[0].
                setAction();
        collisionOn = false;
        gp.collisionChecker.checkTile(this);
        gp.collisionChecker.checkObject(this, false);
        gp.collisionChecker.checkPlayer(this);
        if (!collisionOn) {
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }
        spriteCounter++;
        if (spriteCounter > 10) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }
}
