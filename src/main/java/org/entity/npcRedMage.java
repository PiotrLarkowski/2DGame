package org.entity;

import org.main.MainJPanel;

import java.util.Random;

public class npcRedMage extends ObjectEntity {
    public npcRedMage(MainJPanel gp, int speed) {
        super(gp);

        direction = "left";
        this.speed = speed;
        getImages();
    }
    public void getImages() {
        up1 = setup("/player/opponent/redMage/up1");
        up2 = setup("/player/opponent/redMage/up2");
        down1 = setup("/player/opponent/redMage/down1");
        down2 = setup("/player/opponent/redMage/down2");
        left1 = setup("/player/opponent/redMage/left1");
        left2 = setup("/player/opponent/redMage/left2");
        right1 = setup("/player/opponent/redMage/right1");
        right2 = setup("/player/opponent/redMage/right2");

        imgDef1 = setup("/player/opponent/redMage/defaultDown");
        imgDef2 = setup("/player/opponent/redMage/defaultRight");
        imgDef3 = setup("/player/opponent/redMage/defaultLeft");
        imgDef4 = setup("/player/opponent/redMage/defaultUp");
    }
    public void setAction(){
        Random random = new Random();
        actionLockCounter++;
        if(actionLockCounter == 60){
            int i = random.nextInt(100)+1;
            if(i<=35){
                direction = "up";
            }else if(i>35&&i<=50){
                direction = "right";
            }else if(i>50&&i<=70){
                direction = "left";
            }else if(i>70&&i<=90){
                direction = "down";
            }
            actionLockCounter = 0;
        }
    }
}
