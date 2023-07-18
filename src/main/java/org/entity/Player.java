package org.entity;

import org.example.KeyHandler;
import org.example.MainJPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Player extends Entity{
    MainJPanel gp;
    KeyHandler keyHandler;
    public void getPlayerImages(){
        try{
            up = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/playerUp.png")));
            down = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/playerDown.png")));
            left = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/playerLeft.png")));
            right = ImageIO.read(Objects.requireNonNull(getClass().getResource("/player/playerRight.png")));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public Player(MainJPanel gp, KeyHandler keyHandler){
        this.gp = gp;
        this.keyHandler = keyHandler;
        setDefaultValues();
        getPlayerImages();
    }
    public void setDefaultValues(){
        x =100;
        y= 100;
        speed = 4;
        direction = "down";
    }
    public void update(){
        if(keyHandler.upPressed){
            direction = "up";
            y-= speed;
        }else if(keyHandler.downPressed) {
            direction = "down";
            y += speed;
        }else if(keyHandler.leftPressed){
            direction = "left";
            x-= speed;
        }else if(keyHandler.rightPressed) {
            direction = "right";
            x += speed;
        }
    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        switch (direction){
            case "up":
                image = up;
                break;
            case "down":
                image = down;
                break;
            case "left":
                image=left;
                break;
            case "right":
                image=right;
                break;
        }
        g2.drawImage(image, x, y, gp.finalSize, gp.finalSize,null);
    }
}
