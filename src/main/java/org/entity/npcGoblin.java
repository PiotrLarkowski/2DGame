package org.entity;

import org.main.MainJPanel;

import java.util.Random;

public class npcGoblin extends ObjectEntity {
    public npcGoblin(MainJPanel gp) {
        super(gp);

        direction = "left";
        speed = 4;
        getImages();
    }
    public void getImages() {
        up1 = setup("/player/opponent/goblin/up1");
        up2 = setup("/player/opponent/goblin/up2");
        down1 = setup("/player/opponent/goblin/down1");
        down2 = setup("/player/opponent/goblin/down2");
        left1 = setup("/player/opponent/goblin/left1");
        left2 = setup("/player/opponent/goblin/left2");
        right1 = setup("/player/opponent/goblin/right1");
        right2 = setup("/player/opponent/goblin/right2");
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
