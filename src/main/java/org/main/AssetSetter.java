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
        gp.object[0] = new ObjectTurnstile(2);
        gp.object[0].worldX = 6 * gp.finalSize;
        gp.object[0].worldY = 3 * gp.finalSize;

        gp.object[1] = new ObjectGate(2);
        gp.object[1].worldX = 18 * gp.finalSize;
        gp.object[1].worldY = 3 * gp.finalSize;

        gp.object[2] = new ObjectGate(1);
        gp.object[2].worldX = 2 * gp.finalSize;
        gp.object[2].worldY = 4 * gp.finalSize;

        gp.object[3] = new ObjectTurnstile(1);
        gp.object[3].worldX = 4 * gp.finalSize;
        gp.object[3].worldY = 3 * gp.finalSize;
    }
}
