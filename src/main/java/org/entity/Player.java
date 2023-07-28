package org.entity;

import org.main.KeyHandler;
import org.main.MainJPanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends ObjectEntity {
    KeyHandler keyHandler;
    public int[] timeToShowMessage = {0, 4, 8, 15, 25, 40};
    public int currentEvent = 1;
    public String[] currentDialogue = {
            "",
            "Where I'm?",
            "I need to get out of here!",
            "And what is that robe?!",
            "I thought I saw a rat",
            "I could get lost...",
            ""
    };
    public final int screenX;
    public final int screenY;
    public int avatarNumber = 0;
    boolean eventQueue[] = new boolean[10];

    public Player(MainJPanel gp, KeyHandler keyHandler) {
        super(gp);
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
        up1 = setup("/player/novice/up1");
        up2 = setup("/player/novice/up2");
        down1 = setup("/player/novice/down1");
        down2 = setup("/player/novice/down2");
        left1 = setup("/player/novice/left1");
        left2 = setup("/player/novice/left2");
        right1 = setup("/player/novice/right1");
        right2 = setup("/player/novice/right2");

        imgDef1 = setup("/player/novice/defaultDown");
        imgDef2 = setup("/player/novice/defaultRight");
        imgDef3 = setup("/player/novice/defaultLeft");
        imgDef4 = setup("/player/novice/defaultUp");
    }

    public void setDefaultValues() {
        worldX = 48;
        worldY = 48;
        speed = 4;
        direction = "down";
    }

    public void drawBars(Graphics2D g2) {
        BufferedImage image = setup("/player/emptyBar");
        fillBars(g2, image, true);
        fillBars(g2, image, false);
    }

    private void fillBars(Graphics2D g2, BufferedImage image, boolean healthMana) {
        int heightModerator = 0;
        if (healthMana) {
            g2.setColor(Color.red);
        } else {
            g2.setColor(Color.blue);
            heightModerator = 25;
        }
        g2.setFont(new Font("Arial", Font.PLAIN, 14));
        g2.drawString(gp.lifePercentage + " %", (gp.finalSize / 2) * 11, gp.screenHeight - (gp.finalSize * 2) + 12+ heightModerator);
        for (int i = 0; i < gp.lifePercentage; i++) {
            g2.fillRect((gp.finalSize + 4) + (i * 2), gp.screenHeight - (gp.finalSize * 2) + 2+ heightModerator, 2, 14);
        }
        g2.drawImage(image, gp.finalSize, gp.screenHeight - (gp.finalSize * 2)+ heightModerator, 208, 16, null);
    }

    public void drawAvatar(Graphics2D g2) {
        if (avatarNumber == 0) {
            BufferedImage image = setup("/player/avatar/noviceAvatar");
            g2.drawImage(image, 10, 10, gp.finalSize * 3, gp.finalSize * 3, null);
        }
    }

    public void update() {
        playerSeyMessage();
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
            int npcIndex = gp.collisionChecker.checkEntity(this, gp.npcArray);
            interactNPC(npcIndex);
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

    private void playerSeyMessage() {
        if (currentEvent != timeToShowMessage.length) {
            if (!eventQueue[currentEvent] && gp.getSecondOfPlay(System.currentTimeMillis()) > timeToShowMessage[currentEvent]) {
                eventQueue[0] = true;
                gp.ui.color = 1;
                gp.ui.showMessage(currentDialogue[currentEvent]);
                if (currentEvent != timeToShowMessage.length) {
                    currentEvent++;
                }
            }
        }
        if (!eventQueue[9] && (worldX > 2241 && worldX < 2261) && worldY > 2250) {
            eventQueue[9] = true;
            gp.ui.color = 1;
            gp.ui.showMessage("Finally");
        }
        if (worldX > 2241 && worldX < 2261 && worldY > 2300) {
            gp.gameState = gp.endState;
        }
    }


    public void pickUpObject(int i) {
        if (i != 999) {
            String objectName = gp.object[i].name;
            switch (objectName) {
                case "Book":
                    gp.spellBook.add(gp.object[i].spellName);
                    gp.object[i] = null;
                    gp.ui.color = 2;
                    gp.ui.showMessage("You got a book");
                    break;
                case "Chest":
                    gp.object[i] = null;
                    gp.ui.color = 2;
                    gp.ui.showMessage("You find a treasure");
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
                    if (!gp.object[i].used) {
                        gp.object[i].used = true;
                        gp.ui.color = 2;
                        gp.ui.showMessage("You hear open some gate");
                    }
                    break;
            }
        }
    }

    public void interactNPC(int i) {
        if (i != 999) {
            System.out.println("You are hitting someone");
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
