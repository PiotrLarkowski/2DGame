package org.main;

import org.objects.ObjectBook;
import org.objects.ObjectGate;
import org.objects.ObjectTurnstile;

public class AssetSetter {
    MainJPanel gp;
    public AssetSetter(MainJPanel gp){
        this.gp = gp;
    }
    public void setObjects(){
        gp.object[0] = new ObjectBook();
        gp.object[0].worldX = 6 * gp.finalSize;
        gp.object[0].worldY = 3 * gp.finalSize;

        gp.object[1] = new ObjectBook();
        gp.object[1].worldX = (gp.maxWorldCol-2) * gp.finalSize;
        gp.object[1].worldY = (gp.maxWorldRow-2) * gp.finalSize;

        gp.object[2] = new ObjectGate();
        gp.object[2].worldX = 2 * gp.finalSize;
        gp.object[2].worldY = 4 * gp.finalSize;

        gp.object[3] = new ObjectTurnstile();
        gp.object[3].worldX = 4 * gp.finalSize;
        gp.object[3].worldY = 3 * gp.finalSize;
    }
}
