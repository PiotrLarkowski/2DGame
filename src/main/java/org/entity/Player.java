package org.entity;

import org.main.KeyHandler;
import org.main.MainJPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Player extends Entity {
    MainJPanel gp;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;
    public int spellRemove = 0;
    boolean eventQueue[] = new boolean[10];

    public Player(MainJPanel gp, KeyHandler keyHandler) {
        this.gp = gp;
        this.keyHandler = keyHandler;
        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDefaultValues();
        getPlayerImages();

        screenX = gp.screenWidth / 2;
        screenY = gp.screenHeight / 2;
    }

    public void getPlayerImages() {
        try {

            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/novice/up1.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/novice/up2.png")));
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/novice/down1.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/novice/down2.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/novice/left1.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/novice/left2.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/novice/right1.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/novice/right2.png")));

            imgDef1 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/novice/defaultDown.png")));
            imgDef2 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/novice/defaultRight.png")));
            imgDef3 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/novice/defaultLeft.png")));
            imgDef4 = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/novice/defaultUp.png")));

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
            } else if (keyHandler.downPressed) {
                direction = "down";
            } else if (keyHandler.leftPressed) {
                direction = "left";
            } else if (keyHandler.rightPressed) {
                direction = "right";
            }
            collisionOn = false;
            gp.collisionChecker.checkTile(this);
            int objectIndex = gp.collisionChecker.checkObject(this, true);
            pickUpObject(objectIndex);
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
                System.out.println("WorldX:" + worldX + ",WorldY: " + worldY);
                if (!eventQueue[0] && (worldX>48 || worldY>48)) {
                    eventQueue[0] = true;
                    gp.ui.showMessage("Where I'm?");
                }
                if(!eventQueue[1] && (worldX>87&&worldX<101)&&worldY==180){
                    eventQueue[1] = true;
                    gp.ui.showMessage("I need to get out of here!");
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

    public void pickUpObject(int i) {
        if (i != 999) {
            String objectName = gp.object[i].name;
            switch (objectName) {
                case "Book":
                    gp.object[i] = null;
                    spellRemove++;
                    gp.ui.showMessage("You got a book");
                    break;
                case "Chest":
                    break;
                case "Turnstile":
                    for (int j = 0; j < gp.object.length; j++) {
                        if (gp.object[j] != null) {
                            int connectGateTurnstile = gp.object[j].connectGateTurnstile;
                            if (connectGateTurnstile == gp.object[i].connectGateTurnstile &&
                                    gp.object[j].name.equals("Gate")) {
                                gp.playEventMusic(1);
                                gp.object[j] = null;
                                break;
                            }
                        }
                    }
                    break;
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
