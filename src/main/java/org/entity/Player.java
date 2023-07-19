package org.entity;

import org.example.KeyHandler;
import org.example.MainJPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Player extends Entity {
    MainJPanel gp;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;

    public Player(MainJPanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;
        setDefaultValues();
        getPlayerImages();

        screenX = gp.screenWidth/2;
        screenY = gp.screenHeight/2;
    }

    public void getPlayerImages() {
        try {
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/playerUp1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/playerUp2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/playerDown1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/playerDown2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/playerLeft1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/playerLeft2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/playerRight1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/playerRight2.png")));
            imgDef = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/playerDefault.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setDefaultValues() {
        worldX = 48;
        worldY = 48;
        speed = 4;
        direction = "down";
    }

    public void update() {
        if (keyHandler.downPressed || keyHandler.upPressed || keyHandler.leftPressed || keyHandler.rightPressed) {
            if (keyHandler.upPressed) {
                direction = "up";
                worldY -= speed;
            } else if (keyHandler.downPressed) {
                direction = "down";
                worldY += speed;
            } else if (keyHandler.leftPressed) {
                direction = "left";
                worldX -= speed;
            } else if (keyHandler.rightPressed) {
                direction = "right";
                worldX += speed;
            }
            spriteCounter++;
            if (spriteCounter > 20) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screenX, screenY, gp.finalSize, gp.finalSize, null);
    }
}
