package org.main;

import org.entity.ObjectEntity;
import org.entity.npcGoblin;
import org.entity.npcRedMage;
import org.objects.ObjectBook;
import org.objects.ObjectGate;
import org.objects.ObjectTurnstile;
import org.objects.SuperObject;

public class AssetSetter {
    MainJPanel gp;
    public AssetSetter(MainJPanel gp){
        this.gp = gp;
    }
    public void setObjects(){
        if(gp.levelNumber == 0) {
            gp.object[0] = new ObjectTurnstile(2);
            gp.object[0].worldX = 6 * gp.finalSize;
            gp.object[0].worldY = 3 * gp.finalSize;

            gp.object[1] = new ObjectGate(2);
            gp.object[1].worldX = 18 * gp.finalSize;
            gp.object[1].worldY = 3 * gp.finalSize;

            gp.object[2] = new ObjectGate(1);
            gp.object[2].worldX = gp.finalSize;
            gp.object[2].worldY = 15 * gp.finalSize;

            gp.object[3] = new ObjectTurnstile(1);
            gp.object[3].worldX = 12 * gp.finalSize;
            gp.object[3].worldY = 12 * gp.finalSize;

            gp.object[4] = new ObjectGate(3);
            gp.object[4].worldX = 9 * gp.finalSize;
            gp.object[4].worldY = 25 * gp.finalSize;

            gp.object[5] = new ObjectTurnstile(3);
            gp.object[5].worldX = 19 * gp.finalSize;
            gp.object[5].worldY = gp.finalSize;

            gp.object[6] = new ObjectGate(4);
            gp.object[6].worldX = 3 * gp.finalSize;
            gp.object[6].worldY = 30 * gp.finalSize;

            gp.object[7] = new ObjectTurnstile(4);
            gp.object[7].worldX = gp.finalSize;
            gp.object[7].worldY = 22 * gp.finalSize;

            gp.object[8] = new ObjectGate(5);
            gp.object[8].worldX = 42 * gp.finalSize;
            gp.object[8].worldY = 45 * gp.finalSize;

            gp.object[9] = new ObjectTurnstile(5);
            gp.object[9].worldX = 46 * gp.finalSize;
            gp.object[9].worldY = 29 * gp.finalSize;

            gp.object[10] = new ObjectGate(6);
            gp.object[10].worldX = 48 * gp.finalSize;
            gp.object[10].worldY = 22 * gp.finalSize;

            gp.object[11] = new ObjectTurnstile(6);
            gp.object[11].worldX = 34 * gp.finalSize;
            gp.object[11].worldY = 44 * gp.finalSize;
        }else if(gp.levelNumber == 1){

        }
    }
    public void setNPC(){
        if(gp.levelNumber == 0) {

        }else if(gp.levelNumber == 1){
            gp.npcArray[0] = new npcRedMage(gp, 0);
            gp.npcArray[0].worldX = gp.finalSize;
            gp.npcArray[0].worldY = gp.finalSize*3;
            gp.npcArray[0].direction="up";
        }
    }
}
